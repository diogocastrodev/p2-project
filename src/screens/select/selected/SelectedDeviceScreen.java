package screens.select.selected;

import abstracts.AbsDevice;
import abstracts.AbsDeviceEnd;
import abstracts.AbsDeviceNetwork;
import abstracts.AbsScreen;
import cache.DevicesCache;
import classes.addresses.IP;
import classes.data.DataManager;
import classes.devices.Hub;
import classes.devices.Router;
import classes.devices.Switch;
import classes.exceptions.InvalidOptionException;
import classes.logger.Logger;
import classes.packages.Packet;
import classes.protocols.ARP;
import classes.protocols.ICMP;
import classes.protocols.TCP;
import enums.Operation;
import enums.Protocols;
import others.Consts;
import screens.Input;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SelectedDeviceScreen extends AbsScreen {
    private AbsDevice device;

    private Scanner scanner;

    @Override
    public void drawMenu() {
        System.out.println("");
        System.out.println("");
        if (device instanceof AbsDeviceEnd)
            System.out.println("-- Dispositivo final --");
        else if (device instanceof AbsDeviceNetwork)
            if (device instanceof Hub)
                System.out.println("-- Hub --");
            else if (device instanceof Switch)
                System.out.println("-- Switch--");
            else if (device instanceof Router)
                System.out.println("-- Router --");
            else
                System.out.println("-- Dispositivo de rede --");
        if (device instanceof AbsDeviceEnd)
            System.out.println("Nome: " + ((AbsDeviceEnd) device).getName());
        System.out.println("Status: " + super.convertStatus(device.getStatus()) + " " + device.getStatus().toString());
        System.out.println("IP: " + device.getIP());
        System.out.println("MAC: " + device.getMac());
        if (device instanceof AbsDeviceEnd) {
            if (((AbsDeviceEnd) device).getConnectedDevice() == null
                    || ((AbsDeviceEnd) device).getConnectedDevice().getMac() == null)
                System.out.println("Ligado a: " + "Vazio");
            else
                System.out.println("Ligado a: " + ((AbsDeviceEnd) device).getConnectedDevice().getMac());
        }
        if (device instanceof AbsDeviceNetwork)
            System.out.println("Número de portas: " + ((AbsDeviceNetwork) device).getPortsAmount());
        if (device instanceof AbsDeviceNetwork) {
            for (int i = 0; i < ((AbsDeviceNetwork) device).getPortsAmount(); i++) {
                try {
                    System.out.println("\tPorta " + (i + 1) + ": " + ((AbsDeviceNetwork) device).getPort(i).getMac());
                } catch (Exception e) {
                    System.out.println("\tPorta " + (i + 1) + ": " + "Vazio");
                }
            }
        }
        System.out.println("");
        this.drawHeader();
        System.out.println("1 - Enviar pacote");
        System.out.println("2 - Fazer ligação");
        System.out.println("3 - Desfazer ligação");
        System.out.println("4 - Alterar IP");
        if (device instanceof AbsDeviceEnd)
            System.out.println("5 - Alterar nome");
        if (Consts.allowDHCP && device instanceof AbsDeviceNetwork)
            System.out.println("5 - Definições DHCP");
        System.out.println("0 - Voltar");
    }

    public SelectedDeviceScreen (AbsDevice device) {
        this.scanner = new Scanner(System.in);
        this.device = device;
        this.screen(false);
    }

    public void setDevice(AbsDevice device) {
        this.device = device;
    }

    @Override
    public void handleOption(int option) throws InvalidOptionException {
        switch (option) {
            case 1:
                this.sendPacket();
                break;
            case 2:
                this.connect();
                break;
            case 3:
                this.disconnect();
                break;
            case 4:
                this.changeIP();
                return;
            case 5:
                if (device instanceof AbsDeviceEnd)
                    this.changeName();
                else if (Consts.allowDHCP && device instanceof AbsDeviceNetwork)
                    this.dhcpSettings();
                else
                    throw new InvalidOptionException();
                break;
            case 0:
                break;
            default:
                throw new InvalidOptionException();
        }
    }

    private void sendPacket() {
        if (device instanceof AbsDeviceEnd) {
            if (((AbsDeviceEnd) device).getConnectedDevice() == null) {
                System.out.println("Não há dispositivo conectado");
                super.pressEnterToContinue();
                return;
            }
        } else if (device instanceof AbsDeviceNetwork) {
            if (((AbsDeviceNetwork) device).getPorts().keySet().size() == ((AbsDeviceNetwork) device).getPortsAmount()) {
                System.out.println("Não há portas disponíveis");
                super.pressEnterToContinue();
                return;
            }
        }
        String dhcp = "";
        if (Consts.allowDHCP) {
            dhcp = "/DHCP";
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Tipo de Protocolo (ARP/ICMP/TCP" + dhcp + "): ");
        String protocol = scanner.nextLine().trim().toUpperCase();
        Protocols protocolType = Protocols.valueOf(protocol);
        Packet packet = null;
        switch (protocolType) {
            case ARP:
                System.out.println("IP de destino: ");
                IP destinationIP = new Input().readIP();
                ARP arp = new ARP(Operation.Request, device.getMac(), device.getIP(), null, destinationIP);
                packet = new Packet(arp, Protocols.ARP);
                break;
            case ICMP:
                System.out.println("IP de destino: ");
                IP destinationIPICMP = new Input().readIP();
                System.out.println("Dados: ");
                Object dataICMP = scanner.next();
                ICMP icmp = new ICMP(device.getIP(), destinationIPICMP, dataICMP, 1, 0);
                packet = new Packet(icmp, Protocols.ICMP);
                break;
            case TCP:
                System.out.println("IP de destino: ");
                IP destinationIPTCP = new Input().readIP();
                System.out.println("Porta de origem: ");
                int sourcePort = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Porta de destino: ");
                int destinationPort = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Dados: ");
                Object data = scanner.next();
                // Generate random number
                int random = (int) (Math.random() * 2500);
                try {
                    TCP tcp = new TCP(device.getIP(), destinationIPTCP, sourcePort, destinationPort, random,1, data);
                    packet = new Packet(tcp, Protocols.TCP);
                } catch (Exception e) {
                    System.out.println("Erro ao enviar pacote");
                }
                break;
            case DHCP:
                if (Consts.allowDHCP) {
                    // TODO: DHCP
                }
                break;
            default:
                System.out.println("Protocolo inválido!");
                break;
        }
        if (packet != null) {
            System.out.println("Enviando: " + packet);
            Packet p = device.sendPacket(packet, device);
            String pac = "";
            if (p != null) {
                pac = p.toString();
            } else {
                pac = "Vazio";
            }
            System.out.println("Recebido: " + pac);
            new Logger().save();
        }

    }

    private void connect() {
        if (device instanceof AbsDeviceEnd) {
            if (((AbsDeviceEnd) device).getConnectedDevice() != null) {
                System.out.println("Já há um dispositivo conectado");
                super.pressEnterToContinue();
                return;
            }
        } else if (device instanceof AbsDeviceNetwork) {
            if (((AbsDeviceNetwork) device).getEmptyPorts().size() == 0) {
                System.out.println("Não há portas disponíveis");
                super.pressEnterToContinue();
                return;
            }
        }
        List<AbsDevice> connectable = new ArrayList<>();
        for (AbsDevice device : new DevicesCache().getCache().values()) {
            if (device instanceof AbsDeviceEnd) {
                if (((AbsDeviceEnd) device).getConnectedDevice() == null && !device.equals(this.device)) {
                    connectable.add(device);
                }
            } else if (device instanceof AbsDeviceNetwork) {
                if (((AbsDeviceNetwork) device).getEmptyPorts().size() > 0 && !device.equals(this.device)) {
                    connectable.add(device);
                }
            }
        }
        if (connectable.size() == 0) {
            System.out.println("Não há dispositivos disponíveis");
            super.pressEnterToContinue();
            return;
        }
        System.out.println("Dispositivos disponíveis: ");
        for (int i = 0; i < connectable.size(); i++) {
            System.out.println((i + 1) + " - " + connectable.get(i).getMac());
        }
        System.out.println("0 - Cancelar");
        System.out.println("Digite o número do dispositivo que deseja conectar: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        if (option == 0)
            return;
        int port = 0;
        if (this.device instanceof AbsDeviceNetwork) {
            while (true) {
                System.out.println("Digite a porta que deseja conectar: ");
                port = scanner.nextInt();
                scanner.nextLine();
                if (port > 0 && port <= ((AbsDeviceNetwork) this.device).getPortsAmount()) {
                    try {
                        if (((AbsDeviceNetwork) this.device).getPort(port) != null) {
                            System.out.println("Porta ocupada");
                            continue;
                        }
                        break;
                    } catch (Exception e) {
                        System.out.println("Porta ocupada");
                    }
                } else {
                    System.out.println("Porta inválida");
                }
            }
        }
        AbsDevice deviceToConnect = connectable.get(option - 1);
        try {
            if (device instanceof AbsDeviceEnd) {
                if (deviceToConnect instanceof AbsDeviceEnd)
                    ((AbsDeviceEnd) device).setConnectedDevice((AbsDeviceEnd) deviceToConnect);
                else if (deviceToConnect instanceof AbsDeviceNetwork) {
                    while (true) {
                        System.out.println("Portas disponíveis: ");
                        List<Integer> emptyPorts = ((AbsDeviceNetwork) deviceToConnect).getEmptyPorts();
                        for (int p : emptyPorts) {
                            System.out.print(p + " ");
                            if (p != emptyPorts.get(emptyPorts.size() - 1))
                                System.out.print(", ");
                        }
                        System.out.println();
                        System.out.println("Digite a porta que deseja conectar: ");
                        port = scanner.nextInt();
                        scanner.nextLine();
                        if (port > 0 && port <= ((AbsDeviceNetwork) deviceToConnect).getPortsAmount()) {
                            try {
                                if (((AbsDeviceNetwork) deviceToConnect).getPort(port) != null) {
                                    System.out.println("Porta ocupada");
                                    continue;
                                }
                                break;
                            } catch (Exception e) {
                                System.out.println("Porta ocupada");
                            }
                        } else {
                            System.out.println("Porta inválida");
                        }
                    }
                    ((AbsDeviceEnd) this.device).setConnectedDevice((AbsDeviceNetwork) deviceToConnect, port - 1);
                }
            } else if (this.device instanceof AbsDeviceNetwork) {
                if (deviceToConnect instanceof AbsDeviceEnd) {
                    ((AbsDeviceNetwork) this.device).setPort(port - 1, (AbsDeviceEnd) deviceToConnect);
                    return;
                }
                int portDest = 0;
                while (true) {
                    System.out.println("Portas disponíveis: ");
                    List<Integer> emptyPorts = ((AbsDeviceNetwork) deviceToConnect).getEmptyPorts();
                    for (int p : emptyPorts) {
                        System.out.print(p + " ");
                        if (p != emptyPorts.get(emptyPorts.size() - 1))
                            System.out.print(", ");
                    }
                    System.out.println("");
                    System.out.println("Digite a porta que deseja conectar: ");
                    portDest = scanner.nextInt();
                    scanner.nextLine();
                    if (portDest > 0 && portDest <= ((AbsDeviceNetwork) device).getPortsAmount()) {
                        try {
                            if (((AbsDeviceNetwork) device).getPort(portDest) != null) {
                                System.out.println("Porta ocupada");
                                continue;
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Porta ocupada");
                        }
                    } else {
                        System.out.println("Porta inválida");
                    }
                }
                if (deviceToConnect instanceof AbsDeviceEnd)
                    ((AbsDeviceNetwork) device).setPort(port - 1, (AbsDeviceEnd) deviceToConnect);
                else if (deviceToConnect instanceof AbsDeviceNetwork)
                    ((AbsDeviceNetwork) device).setPort(port - 1, (AbsDeviceNetwork) deviceToConnect, portDest - 1);
            }
        } catch (Exception e) {
            System.out.println("Erro ao conectar");
        }
        new DataManager().saveDevices();
    }

    private void disconnect() {
        if (device instanceof AbsDeviceEnd) {
            if (((AbsDeviceEnd) device).getConnectedDevice() == null) {
                System.out.println("Não há dispositivo conectado");
                super.pressEnterToContinue();
                return;
            }
        } else if (device instanceof AbsDeviceNetwork) {
            if (((AbsDeviceNetwork) device).getEmptyPorts().size() == ((AbsDeviceNetwork) device).getPortsAmount()) {
                System.out.println("Não há portas disponíveis");
                super.pressEnterToContinue();
                return;
            }
        }
        if (device instanceof AbsDeviceEnd)
            try {
                AbsDevice connected = ((AbsDeviceEnd) device).getConnectedDevice();
                if (connected instanceof AbsDeviceEnd) {
                    ((AbsDeviceEnd) device).forceSetConnectedDevice(null);
                    ((AbsDeviceEnd) connected).forceSetConnectedDevice(null);
                } else if (connected instanceof AbsDeviceNetwork) {
                    for (int i = 0; i < ((AbsDeviceNetwork) connected).getPortsAmount(); i++) {
                        AbsDevice d = ((AbsDeviceNetwork) connected).getPort(i);
                        if (d != null && d.equals(device)) {
                            ((AbsDeviceNetwork) connected).removePort(i);
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Não há dispositivo conectado");
            }
        else if (device instanceof AbsDeviceNetwork) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Qual porta deseja desconectar? ");
            int port = scanner.nextInt();
            scanner.nextLine();
            try {
                ((AbsDeviceNetwork) device).removePort(port-1);
                System.out.println("Desconectado com sucesso!");
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Não há dispositivo conectado");
            }
        }
        new DataManager().saveDevices();
        super.pressEnterToContinue();
    }

    private void changeIP() {
        if (device instanceof AbsDeviceEnd) {
            IP ip = null;
            if (Consts.allowDHCP) {
                while (true) {
                    System.out.println("IP:");
                    System.out.println("Estático (1) ou Dinâmico (2)?");
                    int ipOption = scanner.nextInt();
                    scanner.nextLine();
                    if (ipOption == 1) {
                        ip = new Input().readIP();
                        break;
                    } else if (ipOption == 2) {
                        ip = new IP();
                        // TODO: DHCP
                        break;
                    } else {
                        System.out.println("Opção inválida!");
                    }
                }
            } else {
                ip = new Input().readIP();
            }
            device.setIP(ip);
        } else if (device instanceof AbsDeviceNetwork) {
            IP ip = new Input().readIP();
            device.setIP(ip);
        }
        new DataManager().saveDevices();
    }

    private void changeName() {
        if (!(device instanceof AbsDeviceEnd))
            return;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o novo nome: ");
        String name = scanner.nextLine();
        ((AbsDeviceEnd) device).setName(name);
        new DataManager().saveDevices();
    }

    private void dhcpSettings() {
        // TODO: DHCP settings
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o novo IP: ");
        String ip = scanner.nextLine();
        System.out.println("Digite o novo MAC: ");
        String mac = scanner.nextLine();
        System.out.println("Digite o novo nome: ");
        String name = scanner.nextLine();
        // ((AbsDeviceNetwork) device).setDHCP(ip, mac, name);
    }
}
