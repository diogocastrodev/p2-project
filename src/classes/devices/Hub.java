package classes.devices;

import abstracts.AbsDevice;
import abstracts.AbsDeviceNetwork;
import classes.addresses.IP;
import classes.addresses.Mac;
import classes.exceptions.InvalidArgumentException;
import classes.logger.Logger;
import classes.packages.Packet;
import enums.DHCPType;
import enums.Protocols;

public class Hub extends AbsDeviceNetwork {

    public Hub(Mac mac, IP ip, int portCount ) throws InvalidArgumentException  {
        super(mac, ip, portCount);
    }

    /**
     * Send a packet all the ports
     * @param packet Packet to be sent
     * @return Packet to be returned (last packet)
     */
    @Override
    public Packet sendPacket(Packet packet) {
        Packet a = new Packet(null, null); // Packet to be returned
        for (AbsDevice device : super.getPorts().values()) { // Send the packet to all ports
            new Logger().addLog(super.getIP(), super.getMac(), packet.toString(), "Redirect to " + device.getIP()); // Log the packet
            Packet b = device.processPacket(packet); // Process the packet
            if (b != null) { // If the packet is not null
                a = b; // Set the packet to be returned

            }
        }
        return a; // Return the packet
    }

    /**
     * Process a packet
     * @param packet Packet to process (send to all ports)
     * @return Packet to send (null if no packet to send)
     */
    @Override
    public Packet processPacket(Packet packet) {
        new Logger().addLog(super.getIP(), super.getMac(), packet.toString(), "Received a packet"); // Log the packet
        if (packet.getProtocolType().equals(Protocols.DHCP)) {
            if(super.getDhcp().getType().equals(DHCPType.Server)) {
                // TODO: DHCP Server, answer to the packet
            }
        }
        return this.sendPacket(packet); // Send the packet to all ports
    }
}
