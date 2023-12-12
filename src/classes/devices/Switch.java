package classes.devices;

import abstracts.AbsDevice;
import abstracts.AbsDeviceConnect;
import classes.exceptions.InvalidArgumentException;

import java.util.HashMap;
import java.util.Map;

public class Switch extends AbsDeviceConnect {

    private Map<String, Integer> table;

    public Switch(String ip, String mac, int portCount) throws InvalidArgumentException {
        super(ip, mac, portCount);
        table = new HashMap<>();
    }

    public Switch(String ip, String mac, AbsDevice connectedDevice, int portCount) throws InvalidArgumentException  {
        super(ip, mac, connectedDevice, portCount);
        table = new HashMap<>();
    }
}
