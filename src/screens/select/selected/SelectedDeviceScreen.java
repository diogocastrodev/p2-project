package screens.select.selected;

import abstracts.AbsDevice;
import abstracts.AbsDeviceEnd;
import abstracts.AbsDeviceNetwork;
import abstracts.AbsScreen;
import classes.devices.Hub;
import classes.devices.Router;
import classes.devices.Switch;
import classes.exceptions.InvalidOptionException;

import java.util.Scanner;

public class SelectedDeviceScreen extends AbsScreen {
    private AbsDevice device;

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
        System.out.println("0 - Voltar");
    }

    public SelectedDeviceScreen (AbsDevice device) {
        this.device = device;
        this.screen(false);
    }

    public void setDevice(AbsDevice device) {
        this.device = device;
    }

    @Override
    public void handleOption(int option) throws InvalidOptionException {

    }
}
