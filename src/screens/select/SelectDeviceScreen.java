package screens.select;

import abstracts.AbsDevice;
import abstracts.AbsScreen;
import cache.DevicesCache;
import classes.exceptions.InvalidOptionException;
import screens.select.selected.SelectedDeviceScreen;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SelectDeviceScreen extends AbsScreen {
    private List<String> macs;
    @Override
    public void drawMenu() {
        // List all devices
        macs = new ArrayList<>();
        Map<String, AbsDevice> c = new DevicesCache().getCache();
        int i = 1;
        for (String mac : c.keySet()) {
            AbsDevice device = c.get(mac);
            System.out.println(i + ". " + super.convertStatus(device.getStatus()) + " " + device.getMac() + " -> " + device.getIP());
            macs.add(mac);
            i++;
        }
        System.out.println("0. Voltar");
    }

    public SelectDeviceScreen() {
        this.screen(true);
    }

    @Override
    public void handleOption(int option) throws InvalidOptionException {
        if (option > 0 && option <= new DevicesCache().getCache().size()) {
            String mac = macs.get(option - 1);
            AbsDevice device = new DevicesCache().getCache().get(mac);
            new SelectedDeviceScreen(device);
        } else if (option == 0) {
        } else {
            throw new InvalidOptionException();
        }
    }
}
