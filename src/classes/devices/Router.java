package classes.devices;

import abstracts.AbsDevice;
import abstracts.AbsDeviceConnect;
import classes.devices.Device;
import classes.exceptions.InvalidArgumentException;

import java.util.List;

public class Router extends AbsDeviceConnect {


    public Router(String ip, String mac, int portCount) throws InvalidArgumentException {
        super(ip, mac, portCount);
    }

    public Router(String ip, String mac, AbsDevice connectedDevice, int portCount) throws InvalidArgumentException  {
        super(ip, mac, connectedDevice, portCount);
    }
}
