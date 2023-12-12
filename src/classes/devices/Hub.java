package classes.devices;

import abstracts.AbsDevice;
import abstracts.AbsDeviceConnect;
import classes.exceptions.InvalidArgumentException;

import java.util.List;
import java.util.Map;

public class Hub extends AbsDeviceConnect {


    public Hub(String ip, String mac, int portCount) throws InvalidArgumentException {
        super(ip, mac, portCount);
    }

    public Hub(String ip, String mac, AbsDevice connectedDevice, int portCount) throws InvalidArgumentException  {
        super(ip, mac, connectedDevice, portCount);
    }
}
