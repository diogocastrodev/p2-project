package classes.devices;

import abstracts.AbsDevice;
import abstracts.AbsDeviceConnect;

import java.util.HashMap;
import java.util.Map;

public class Switch extends AbsDeviceConnect {

    private Map<String, Integer> table;

    public Switch(String ip, String mac, int portCount) {
        super(ip, mac, portCount);
        table = new HashMap<>();
    }

    public Switch(String ip, String mac, AbsDevice connectedDevice, int portCount) {
        super(ip, mac, connectedDevice, portCount);
        table = new HashMap<>();
    }
}
