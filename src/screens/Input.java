package screens;

import classes.addresses.IP;
import classes.addresses.Mac;

import java.util.Scanner;

public class Input{

    public IP readIP(){
        Scanner scanner = new Scanner(System.in);
        IP finalIP = null;
        while (true) {
            System.out.println("IP: ");
            String ip = scanner.nextLine();
            try {
                finalIP = new IP(ip);
                break;
            } catch (Exception e) {
                System.out.println("IP inválido");
            }
            if (finalIP != null) {
                break;
            }
        }
        return finalIP;
    }

    public Mac readMac(){
        Scanner scanner = new Scanner(System.in);
        Mac finalMac = null;
        while (true) {
            System.out.println("MAC: ");
            String mac = scanner.nextLine();
            try {
                finalMac = new Mac(mac);
                break;
            } catch (Exception e) {
                System.out.println("MAC inválido");
            }
            if (finalMac != null) {
                break;
            }
        }
        return finalMac;
    }

}
