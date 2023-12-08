package classes.devices;

import abstracts.AbsDevice;
import abstracts.AbsDeviceConnect;
import classes.devices.Device;

import java.util.List;

public class Router extends AbsDeviceConnect {


    public Router(String ip, String mac, int portCount) {
        super(ip, mac, portCount);
    }

    public Router(String ip, String mac, AbsDevice connectedDevice, int portCount) {
        super(ip, mac, connectedDevice, portCount);
    }
}
