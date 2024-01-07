package classes.devices;

import abstracts.AbsDevice;
import abstracts.AbsDeviceNetwork;
import classes.addresses.IP;
import classes.addresses.Mac;
import classes.exceptions.InvalidArgumentException;
import classes.packages.Packet;

import java.util.HashMap;
import java.util.Map;

public class Switch extends AbsDeviceNetwork {

    private Map<Mac, Integer> table;

    public Switch(Mac mac, IP ip, int portCount) throws InvalidArgumentException {
        super(mac, ip, portCount);
        table = new HashMap<>();
    }


    @Override
    public Packet sendPacket(Packet packet, AbsDevice sender) {
        return null;
    }

    @Override
    public Packet processPacket(Packet packet, AbsDevice sender) {
        return null;
    }

    @Override
    public String toString() {
        return super.toString().replace("AbsDeviceNetwork", "Switch");
    }
}
