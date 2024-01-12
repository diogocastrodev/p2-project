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
import others.Consts;
import screens.Input;

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
        // TODO: Send a packet to another device
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o IP de destino: ");
        String ip = scanner.nextLine();
        System.out.println("Digite o MAC de destino: ");
        String mac = scanner.nextLine();
        System.out.println("Digite o conteúdo do pacote: ");
        String content = scanner.nextLine();
        new Logger().save();
    }

    private void connect() {
        // TODO: Connect to another device
        // When connect to another device, send dhcp to the device
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o MAC do dispositivo a ser conectado: ");
        String mac = scanner.nextLine();
        new DataManager().saveDevices();
    }

    private void disconnect() {
        if (device instanceof AbsDeviceEnd)
            try {
                ((AbsDeviceEnd) device).setConnectedDevice(null);
            } catch (Exception e) {
                System.out.println("Não há dispositivo conectado");
            }
        else if (device instanceof AbsDeviceNetwork) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Qual porta deseja desconectar? ");
            int port = scanner.nextInt();
            scanner.nextLine();
            try {
                ((AbsDeviceNetwork) device).setPort(port, null);
            } catch (Exception e) {
                System.out.println("Não há dispositivo conectado");
            }
        }
        new DataManager().saveDevices();
    }

    private void changeIP() {
        // TODO: CHANGE IP (ask static or DHCP) and only static for network
        if (device instanceof AbsDeviceEnd) {
            // TODO: DHCP or static
            IP ip = null;
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
