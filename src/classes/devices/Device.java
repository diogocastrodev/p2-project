package classes.devices;

import abstracts.AbsDevice;
import abstracts.AbsDeviceEnd;
import classes.addresses.IP;
import classes.addresses.Mac;
import classes.exceptions.InvalidArgumentException;
import classes.packages.Packet;
import classes.protocols.ARP;
import classes.protocols.DHCP;
import classes.protocols.ICMP;
import classes.protocols.TCP;
import enums.Connection;
import enums.Operation;
import enums.Protocols;

import java.io.Serializable;

public class Device extends AbsDeviceEnd implements Serializable {

    /**
     * The name of the device
     */
    private String name;

    /**
     * The Type of connection the device has
     */
    private Connection connection;

    /**
     * The constructor for the Device class ( Main things )
     * @param ip The IP address of the device
     * @param mac The MAC address of the device
     */
    public Device(IP ip, Mac mac) throws InvalidArgumentException {
        super(ip, mac);
    }

    /**
     * The constructor for the Device class ( Main things + name)
     * @param ip The IP address of the device
     * @param mac The MAC address of the device
     * @param name The name of the device
     */
    public Device(IP ip, Mac mac, String name) throws InvalidArgumentException {
        super(ip, mac);
        this.setName(name);
    }

    public Device(IP ip, Mac mac, String name, Connection connection) throws InvalidArgumentException {
        super(ip, mac);
        this.setName(name);
        this.setConnection(connection);
    }

    /**
     * Get the Device's name
     * @return The Device's name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the Device's name
     * @param name The Device's name
     */
    public void setName(String name) {
        this.name = name.trim();
    }

    /**
     * Get the Device's connection type
     * @return The Device's connection type
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Set the Device's connection type
     * @param connection The Device's connection type
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * Set the Device's connection type
     * @param connection The Device's connection type
     */
    public void setConnection(String connection) {
        this.connection = Connection.valueOf(connection.toUpperCase()); // Pegar numa String e converter para um ENUM
    }

    @Override
    public Packet sendPacket(Packet packet, AbsDevice sender) {
        if (this.getConnectedDevice() != null) {
            // Send the packet to the connected device
            if (!this.getConnectedDevice().getMac().toString().equals(sender.getMac().toString())) {
                // The packet didn't come from the connected device
                // Send the packet to the connected device
                return this.getConnectedDevice().processPacket(packet, this);
            }
            // The packet came from the connected device
        }
        // Can't send the packet to the connected device (no connected device)
        return null;
    }

    @Override
    public Packet processPacket(Packet packet, AbsDevice sender) {
        // TODO: TCP & DHCP
        if (!sender.equals(this)) {
            if (packet.getProtocolType().equals(Protocols.DHCP)) {
                // Process packet
                DHCP p = packet.getDHCP();
            } else if (packet.getProtocolType().equals(Protocols.ARP)) {
                // Process packet
                ARP p = packet.getARP();
                if (p.getTargetMac().toString().equals(super.getMac().toString())
                        || p.getTargetIP().toString().equals(super.getIP().toString())) {
                    // Packet is for this device
                    if (p.getOperation().equals(Operation.Request)) {
                        // Packet is a request
                        ARP arp = new ARP(Operation.Reply, super.getMac(), super.getIP(), p.getSourceMac(), p.getSourceIP());
                        return new Packet(arp, Protocols.ARP);
                    }
                }
            } else if (packet.getProtocolType().equals(Protocols.TCP)) {
                // Process packet
                TCP p = packet.getTCP();
                if (p.getDestinationAddress().toString().equals(super.getIP().toString())) {
                    // Packet is for this device
                }
            } else if (packet.getProtocolType().equals(Protocols.ICMP)) {
                // Process packet
                ICMP p = packet.getICMP();
                if (p.getDestinationAddress().toString().equals(super.getIP().toString())) {
                    // Packet is for this device
                    ICMP icmp = new ICMP(super.getIP(), p.getSourceAddress(), super.getMac(), 2, 0);
                    return new Packet(icmp, Protocols.ICMP);
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString().replace("AbsDeviceEnd", "Device");
    }


}
