package classes.devices;

import abstracts.AbsDeviceNetwork;
import classes.addresses.Mac;
import classes.exceptions.InvalidArgumentException;
import classes.packages.Packet;

public class Hub extends AbsDeviceNetwork {

    public Hub(Mac mac, int portCount ) throws InvalidArgumentException  {
        super(mac, portCount);
    }

    @Override
    public void sendPacket(Packet packet) {

    }

    @Override
    public Packet processPacket(Packet packet) {
        return null;
    }
}
