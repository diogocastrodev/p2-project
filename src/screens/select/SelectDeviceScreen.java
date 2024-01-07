package screens.select;

import abstracts.AbsDevice;
import abstracts.AbsScreen;
import cache.DevicesCache;
import classes.addresses.Mac;
import classes.exceptions.InvalidOptionException;

import java.util.Map;

public class SelectDeviceScreen extends AbsScreen {
    @Override
    public void drawMenu() {
        // List all devices
        Map<String, AbsDevice> c = new DevicesCache().getCache();
        int i = 1;
        for (String mac : c.keySet()) {
            AbsDevice device = c.get(mac);
            System.out.println(i + ". " + super.convertStatus(device.getStatus()) + " " + device.getMac() + " -> " + device.getIP());
            i++;
        }
        System.out.println("0. Voltar");
    }

    @Override
    public void handleOption(int option) throws InvalidOptionException {
        if (option > 0 && option <= new DevicesCache().getCache().size()) {
            // TODO: Select device
        } else if (option == 0) {
        } else {
            throw new InvalidOptionException();
        }
    }
}
