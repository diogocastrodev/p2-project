package classes.devices;

import abstracts.AbsDevice;
import abstracts.AbsDeviceConnect;

import java.util.List;
import java.util.Map;

public class Hub extends AbsDeviceConnect {


    public Hub(String ip, String mac, int portCount) {
        super(ip, mac, portCount);
    }

    public Hub(String ip, String mac, AbsDevice connectedDevice, int portCount) {
        super(ip, mac, connectedDevice, portCount);
    }
}
