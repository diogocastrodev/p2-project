package classes.devices;

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
    public void sendPacket(Packet packet) {

    }

    @Override
    public Packet processPacket(Packet packet) {
        return null;
    }
}
