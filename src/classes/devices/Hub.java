package classes.devices;

import abstracts.AbsDeviceNetwork;
import classes.addresses.IP;
import classes.addresses.Mac;
import classes.exceptions.InvalidArgumentException;
import classes.packages.Packet;

public class Hub extends AbsDeviceNetwork {

    public Hub(Mac mac, IP ip, int portCount ) throws InvalidArgumentException  {
        super(mac, ip, portCount);
    }

    @Override
    public void sendPacket(Packet packet) {

    }

    @Override
    public Packet processPacket(Packet packet) {
        return null;
    }
}
