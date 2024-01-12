package classes.devices;

import abstracts.AbsDevice;
import abstracts.AbsDeviceNetwork;
import classes.addresses.IP;
import classes.addresses.Mac;
import classes.exceptions.InvalidArgumentException;
import classes.logger.Logger;
import classes.packages.Packet;
import classes.protocols.ARP;
import classes.protocols.DHCP;
import classes.protocols.ICMP;
import classes.protocols.TCP;
import enums.DHCPType;
import enums.Operation;
import enums.Protocols;
import others.Consts;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Switch extends AbsDeviceNetwork implements Serializable {

    private Map<String, AbsDevice> table;

    public Switch(Mac mac, IP ip, int portCount) throws InvalidArgumentException {
        super(mac, ip, portCount);
        table = new HashMap<>();
    }




    /**
     * Send a packet all the ports
     * @param packet Packet to be sent
     * @param sender Sender of the packet
     * @return Packet to be returned (last packet)
     */
    public Packet sendPacket(Packet packet, AbsDevice sender) {
        for (AbsDevice device : this.getPorts().values()) {
            this.sendPacket(packet, sender, device);
        }
        return null;
    }

    /**
     * Send a packet to a specific destination
     * @param packet
     * @param sender
     * @param destination
     * @return
     */
    public Packet sendPacket(Packet packet, AbsDevice sender, AbsDevice destination) {
        if (destination == null || destination.equals(this) || destination.equals(sender)) {
            return null;
        }
        new Logger().addLog(super.getIP(), super.getMac(), packet.getProtocolType().toString(), "Sending to " + destination.getIP() + " (" + destination.getMac() + ")");
        Packet p = destination.processPacket(packet, this);
        if (p != null) {
            new Logger().addLog(super.getIP(), super.getMac(), packet.getProtocolType().toString(), "Received a packet from " + destination.getIP() + " (" + destination.getMac() + ")");
        }
        return p;
    }

    /**
     * Process a packet
     * @param packet Packet to process (send to all ports)
     * @param sender Sender of the packet
     * @return Packet to send (null if no packet to send)
     */
    @Override
    public Packet processPacket(Packet packet, AbsDevice sender) {
        new Logger().addLog(super.getIP(), super.getMac(), packet.getProtocolType().toString(), "Received a packet"); // Log the packet
        if (packet.getProtocolType().equals(Protocols.DHCP) && Consts.allowDHCP) {
            DHCP p = packet.getDHCP();
            if(super.getDhcp().getType().equals(DHCPType.Server)) {
                try {
                    if (p.getDestinationAddress().toString().contains("255") ||
                            p.getDestinationAddress().toString().equals(super.getIP().toString()))
                    {
                        new Logger().addLog(super.getIP(), super.getMac(), packet.getProtocolType().toString(), "Processing DHCP packet"); // Log the packet
                        return super.getDhcp().processServer(this, packet);
                    }
                    // Ignore the packet
                } catch (InvalidArgumentException e) {
                    new Logger().addLog(super.getIP(), super.getMac(), packet.getProtocolType().toString(), "Error processing DHCP Server");
                }
            }
        } else if (packet.getProtocolType().equals(Protocols.ARP)) {
            ARP p = packet.getARP();
            if (p.getTargetIP().equals(this.getIP())) {
                // For this device ignore
            } else if (p.getTargetMac() != null && this.table.containsKey(p.getTargetMac().toString())) {
                new Logger().addLog(super.getIP(), super.getMac(), packet.getProtocolType().toString(), "Redirect to " + this.table.get(p.getTargetMac().toString()).getIP() + " (" + this.table.get(p.getTargetMac().toString()).getMac() + ")"); // Log the packet
                return this.sendPacket(packet, sender, this.getPorts().get(this.table.get(p.getTargetMac().toString())));
            }
        }
        if (packet.getProtocolType().equals(Protocols.TCP)) {
            TCP p = packet.getTCP();
            if (p.getDestinationAddress().toString().equals(super.getIP().toString())) {
                // Process the packet
                new Logger().addLog(super.getIP(), super.getMac(), packet.getProtocolType().toString(), "Processing TCP packet"); // Log the packet
            }
        } else if (packet.getProtocolType().equals(Protocols.ICMP)) {
            ICMP p = packet.getICMP();
            if (p.getDestinationAddress().toString().equals(super.getIP().toString())) {
                // Process the packet
                new Logger().addLog(super.getIP(), super.getMac(), packet.getProtocolType().toString(), "Processing ICMP packet"); // Log the packet
                ICMP icmp = new ICMP(super.getIP(), p.getSourceAddress(), super.getMac(), 2, 0);
                return new Packet(icmp, Protocols.ICMP);
            }
        } else if (packet.getProtocolType().equals(Protocols.ARP)) {
            ARP p = packet.getARP();
            if (p.getTargetIP().toString().equals(super.getIP().toString())) {
                // Process the packet
                new Logger().addLog(super.getIP(), super.getMac(), packet.getProtocolType().toString(), "Processing ARP packet"); // Log the packet
                ARP arp = new ARP(Operation.Reply, super.getMac(), super.getIP(), p.getSourceMac(), p.getSourceIP());
                return new Packet(arp, Protocols.ARP);
            }
        }
        new Logger().addLog(super.getIP(), super.getMac(), packet.getProtocolType().toString(), "Redirect to all ports"); // Log the packet
        return this.sendPacket(packet, sender); // Send the packet to all ports
    }

    @Override
    public String toString() {
        return super.toString().replace("AbsDeviceNetwork", "Switch");
    }
}
