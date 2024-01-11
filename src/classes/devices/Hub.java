package classes.devices;

import abstracts.AbsDevice;
import abstracts.AbsDeviceNetwork;
import classes.addresses.IP;
import classes.addresses.Mac;
import classes.exceptions.InvalidArgumentException;
import classes.logger.Logger;
import classes.packages.Packet;
import classes.protocols.ARP;
import classes.protocols.ICMP;
import classes.protocols.TCP;
import enums.DHCPType;
import enums.Operation;
import enums.Protocols;

import java.io.Serializable;

public class Hub extends AbsDeviceNetwork implements Serializable {

    public Hub(Mac mac, IP ip, int portCount ) throws InvalidArgumentException  {
        super(mac, ip, portCount);
    }

    /**
     * Send a packet all the ports
     * @param packet Packet to be sent
     * @return Packet to be returned (last packet)
     */
    @Override
    public Packet sendPacket(Packet packet, AbsDevice sender) {
        Packet a = new Packet(null, null); // Packet to be returned
        for (AbsDevice device : super.getPorts().values()) { // Send the packet to all ports
            new Logger().addLog(super.getIP(), super.getMac(), packet.toString(), "Redirect to " + device.getIP()); // Log the packet
            Packet b = device.processPacket(packet, this); // Process the packet
            if (b != null) { // If the packet is not null
                a = b; // Set the packet to be returned

            }
        }
        if (a.getProtocol() != null) {
            return a;
        } else {
            return null;
        }
    }

    /**
     * Process a packet
     * @param packet Packet to process (send to all ports)
     * @return Packet to send (null if no packet to send)
     */
    @Override
    public Packet processPacket(Packet packet, AbsDevice sender) {
        new Logger().addLog(super.getIP(), super.getMac(), packet.toString(), "Received a packet"); // Log the packet
        if (packet.getProtocolType().equals(Protocols.DHCP)) {
            if(super.getDhcp().getType().equals(DHCPType.Server)) {
                try {
                    return super.getDhcp().processServer(this, packet);
                } catch (InvalidArgumentException e) {
                    new Logger().addLog(super.getIP(), super.getMac(), packet.toString(), "Error processing DHCP Server");
                }
            }
        }
        if (packet.getProtocolType().equals(Protocols.TCP)) {
            TCP p = packet.getTCP();
            if (p.getDestinationAddress().toString().equals(super.getIP().toString())) {
                // Process the packet
                new Logger().addLog(super.getIP(), super.getMac(), packet.getProtocolType().toString(), "Received a TCP packet " + super.getIP() + " [" +  super.getMac() + "]"); // Log the packet
            }
        } else if (packet.getProtocolType().equals(Protocols.ICMP)) {
            ICMP p = packet.getICMP();
            if (p.getDestinationAddress().toString().equals(super.getIP().toString())) {
                // Process the packet
                new Logger().addLog(super.getIP(), super.getMac(), packet.getProtocolType().toString(), "Received an ICMP packet " + super.getIP() + " [" +  super.getMac() + "]"); // Log the packet
                ICMP icmp = new ICMP(super.getIP(), p.getSourceAddress(), super.getMac(), 2, 0);
                return new Packet(icmp, Protocols.ICMP);
            }
        } else if (packet.getProtocolType().equals(Protocols.ARP)) {
            ARP p = packet.getARP();
            if (p.getTargetIP().toString().equals(super.getIP().toString())) {
                // Process the packet
                new Logger().addLog(super.getIP(), super.getMac(), packet.getProtocolType().toString(), "Received an ARP packet " + super.getIP() + " [" +  super.getMac() + "]"); // Log the packet
                ARP arp = new ARP(Operation.Reply, super.getMac(), super.getIP(), p.getSourceMac(), p.getSourceIP());
                return new Packet(arp, Protocols.ARP);
            }
        }
        return this.sendPacket(packet, this); // Send the packet to all ports
    }

    @Override
    public String toString() {
        return super.toString().replace("AbsDeviceNetwork", "Hub");
    }
}
