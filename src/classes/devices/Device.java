package classes.devices;

import abstracts.AbsDevice;
import abstracts.AbsDeviceEnd;
import classes.addresses.IP;
import classes.addresses.Mac;
import classes.exceptions.InvalidArgumentException;
import classes.logger.Logger;
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
                new Logger().addLog(super.getIP(), super.getMac(), packet.getProtocolType().toString(), "Sending to " + this.getConnectedDevice().getIP() + " (" + this.getConnectedDevice().getMac() + ")");
                Packet p = this.getConnectedDevice().processPacket(packet, this);
                if (p != null) {
                    new Logger().addLog(super.getIP(), super.getMac(), packet.getProtocolType().toString(), "Received a packet from " + this.getConnectedDevice().getIP() + " (" + this.getConnectedDevice().getMac() + ")");
                }
                return p;
            }
            // The packet came from the connected device
        }
        // Can't send the packet to the connected device (no connected device)
        return null;
    }

    @Override
    public Packet processPacket(Packet packet, AbsDevice sender) {
        if (!sender.equals(this)) {
            new Logger().addLog(super.getIP(), super.getMac(), packet.getProtocolType().toString(), "Received a packet"); // Log the packet
            if (packet.getProtocolType().equals(Protocols.DHCP)) {
                // Process packet
                DHCP p = packet.getDHCP();
                return null; // End devices won't process DHCP packets
            } else if (packet.getProtocolType().equals(Protocols.ARP)) {
                // Process packet
                ARP p = packet.getARP();
                if (p.getTargetIP().toString().equals(super.getIP().toString())) {
                    // Packet is for this device
                    if (p.getOperation().equals(Operation.Request)) {
                        new Logger().addLog(super.getIP(), super.getMac(), packet.getProtocolType().toString(), "Processing ARP packet"); // Log the packet
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
                    new Logger().addLog(super.getIP(), super.getMac(), packet.getProtocolType().toString(), "Processing TCP packet"); // Log the packet
                    try {
                        TCP tcp = new TCP(super.getIP(), p.getSourceAddress(), p.getDestinationPort(), p.getDestinationPort(), p.getAcknowledgementNumber(), p.getWindow(), "Resposta");
                        return new Packet(tcp, Protocols.TCP);
                    } catch (InvalidArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } else if (packet.getProtocolType().equals(Protocols.ICMP)) {
                // Process packet
                ICMP p = packet.getICMP();
                if (p.getDestinationAddress().toString().equals(super.getIP().toString())) {
                    // Packet is for this device
                    new Logger().addLog(super.getIP(), super.getMac(), packet.getProtocolType().toString(), "Processing ICMP packet"); // Log the packet
                    ICMP icmp = new ICMP(super.getIP(), p.getSourceAddress(), super.getMac(), 2, 0);
                    return new Packet(icmp, Protocols.ICMP);
                }
            }
        }
        new Logger().addLog(super.getIP(), super.getMac(), packet.getProtocolType().toString(), "Ignored packet"); // Log the packet
        return null;
    }

    @Override
    public String toString() {
        return super.toString().replace("AbsDeviceEnd", "Device");
    }


}
