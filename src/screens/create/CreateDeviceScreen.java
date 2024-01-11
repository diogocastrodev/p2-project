package screens.create;

import abstracts.AbsScreen;
import cache.MacCache;
import classes.addresses.IP;
import classes.addresses.Mac;
import classes.devices.Device;
import classes.devices.Hub;
import classes.devices.Router;
import classes.devices.Switch;
import classes.exceptions.InvalidArgumentException;
import classes.exceptions.InvalidOptionException;
import test_tools.TestTools;

import java.util.Scanner;

public class CreateDeviceScreen extends AbsScreen {

    @Override
    public void drawMenu() {
        System.out.println("1 - Criar um Dispositivo de fim (Computador)");
        System.out.println("2 - Router");
        System.out.println("3 - Switch");
        System.out.println("4 - Hub");
        System.out.println("0 - Sair"); 
    }

    public CreateDeviceScreen() {
        this.screen(true);
    }

    @Override
    public void handleOption(int option) throws InvalidOptionException {
        if (option == 0) {
            return;
        }
        if (option < 0 || option > 4) {
            throw new InvalidOptionException();
        }
        Scanner scanner = new Scanner(System.in);
        IP ip = null;
        while (true) {
            System.out.println("IP:");
            System.out.println("Estático (1) ou Dinâmico (2)?");
            int ipOption = scanner.nextInt();
            if (ipOption == 1) {
                while (true) {
                    System.out.println("IP:");
                    String ipReader = scanner.next();
                    try {
                        ip = new IP(ipReader);
                        break;
                    } catch (Exception e) {
                        System.out.println("IP inválido!");
                    }
                }

                break;
            } else if (ipOption == 2) {
                ip = new IP();
                break;
            } else {
                System.out.println("Opção inválida!");
            }
        }
        Mac mac = null;
        try {
            while (true) {
                mac = new TestTools().generateMac();
                if (new MacCache().contains(mac.toString())) {
                    // Criar um mac novo
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Mac inválido!");
        }
        System.out.println("Mac: " + mac.toString());
        int nPortas = 0;
        if (option > 1) {
            while (true) {
                System.out.println("Número de portas:");
                nPortas = scanner.nextInt();
                if (nPortas > 1) {
                    break;
                } else {
                    System.out.println("Número de portas inválido!");
                }
            }
        }

        switch(option) {
            case 1:
                    System.out.println("Nome para o dispositivo:");
                    String name = scanner.next();
                    try {
                        new Device(ip, mac, name);
                    } catch (InvalidArgumentException e) {
                        System.out.println("Argumento inválido!");
                    }
                    break;
            case 2:
                    try {
                        new Router(mac, ip, nPortas);
                    } catch (InvalidArgumentException e) {
                        System.out.println("Argumento inválido!");
                    }
                    break;
            case 3:
                    try {
                        new Switch(mac, ip, nPortas);
                    } catch (InvalidArgumentException e) {
                        System.out.println("Argumento inválido!");
                    }
                    break;
            case 4:
                    try {
                        new Hub(mac, ip, nPortas);
                    } catch (InvalidArgumentException e) {
                        System.out.println("Argumento inválido!");
                    }
                    break;
            case 0:
                    break;
            default:
                throw new InvalidOptionException();
        }

    }


}
