package screens.list;

import abstracts.AbsDevice;
import abstracts.AbsDeviceEnd;
import abstracts.AbsDeviceNetwork;
import abstracts.AbsScreen;
import cache.DevicesCache;

import java.util.HashSet;
import java.util.Set;

public class ListDevicesScreen extends AbsScreen {

    @Override
    public void drawMenu() {
        System.out.println("1. Listar dispositivos únicos");
        //System.out.println("2. Listar dispositivos formato rede");
        System.out.println("0. Voltar");
    }

    public ListDevicesScreen() {
        this.screen(true);
    }

    @Override
    public void handleOption(int option) {
        System.out.println(option);
        switch (option) {
            case 1:
                // this.listDevices();
                DevicesCache d = new DevicesCache();
                for (AbsDevice device : d.getCache().values()) {
                    System.out.println(device);
                }
                super.pressEnterToContinue();
                return;
            case -2:
                // this.listPackets();
                Set<String> alreadyPrinted = new HashSet<>();
                for (AbsDevice device : new DevicesCache().getCache().values()) {
                    if (device instanceof AbsDeviceNetwork) {
                        Set<String> listed = listDevices(alreadyPrinted, device);
                        ((AbsDeviceNetwork) device).getPorts().values().forEach(System.out::println);
                        alreadyPrinted.addAll(listed);
                    }
                }
                for (AbsDevice device : new DevicesCache().getCache().values()) {
                    if (device instanceof AbsDeviceEnd) {
                        if (!alreadyPrinted.contains(device.getMac().toString())) {
                            System.out.println(device);
                            if (((AbsDeviceEnd) device).getConnectedDevice() != null) {
                                alreadyPrinted.add(((AbsDeviceEnd) device).getConnectedDevice().getMac().toString());
                            }
                        }
                    }
                }
                super.pressEnterToContinue();
                return;
            case 0:
                // this.goBack();
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    public Set<String> listDevices(Set<String> alreadyPrinted, AbsDevice device) {
        Set<String> listed = alreadyPrinted;
        if (device instanceof AbsDeviceEnd) {
            listed.add(device.getMac().toString());
            System.out.println(((AbsDeviceEnd) device).getConnectedDevice());
            if (((AbsDeviceEnd) device).getConnectedDevice() != null)
                listed.add(((AbsDeviceEnd) device).getConnectedDevice().getMac().toString());
        } else if (device instanceof AbsDeviceNetwork) {
            listed.add(device.getMac().toString());
            System.out.println(device);
            for (AbsDevice end : ((AbsDeviceNetwork) device).getPorts().values()) {
                System.out.println(end);
                listed.add(end.getMac().toString());
                if (end instanceof AbsDeviceNetwork) {
                    Set<String> listDevices = listDevices(listed, end);
                    listed.addAll(listDevices);
                }
            }
        }
        return listed;
    }
}
