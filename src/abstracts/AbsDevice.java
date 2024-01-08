package abstracts;

import cache.DevicesCache;
import cache.MacCache;
import classes.addresses.IP;
import classes.addresses.Mac;
import classes.packages.Packet;
import enums.IPType;
import enums.Status;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class AbsDevice implements Serializable {
    /**************************************************************************
     * Variables
     **************************************************************************/
    /**
     * Status of the device
     */
    private Status status;
    /**
     * IP address of the device
     */
    private IP ip;
    /**
     * IP type of the device
     */
    private IPType ipType;
    /**
     * MAC address of the device
     */
    private Mac mac;
    /**
     * ARP Table of the device
     */
    private Map<Mac, IP> arpTable;

    /**************************************************************************
     * Constructors
     **************************************************************************/
    /**
     * Constructor
     * @param mac MAC address
     */
    public AbsDevice(Mac mac, IP ip) {
        this.setMac(mac); // Set the MAC address
        this.setIP(ip); // Set the IP address
        if (ip == null || ip.toString().equals("0.0.0.0")) {
            this.setIpType(IPType.DYNAMIC);
        } else {
            this.setIpType(IPType.STATIC);
        }
        this.setStatus(Status.UP); // Set the status to DOWN
        this.arpTable = new HashMap<>(); // Initialize the ARP Table

        new DevicesCache().addDevice(this);
        new MacCache().add(this.getMac().toString());
    }

    /**************************************************************************
     * Getters and Setters
     **************************************************************************/
    /**
     * Get the status of the device
     * @return Status of the device
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Set the status of the device
     * @param status Status of the device
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Get the MAC address of the device
     * @return MAC address of the device
     */
    public Mac getMac() {
        return mac;
    }

    /**
     * Set the MAC address of the device
     * @param mac MAC address of the device
     */
    public void setMac(Mac mac) {
        this.mac = mac;
    }

    /**
     * Get the IP address of the device
     * @return IP address of the device
     */
    public IP getIP() {
        return ip;
    }

    /**
     * Set the IP address of the device
     * @param ip IP address of the device
     */
    public void setIP(IP ip) {
        this.ip = ip;
    }

    /**
     * Get Type of the IP address
     * @return Type of the IP address
     */
    public IPType getIpType() {
        return ipType;
    }

    /**
     * Set Type of the IP address
     * @param ipType
     */
    public void setIpType(IPType ipType) {
        this.ipType = ipType;
    }

    /**************************************************************************
     * Methods
     **************************************************************************/

    /**
     * Logistic behind this:
     * 1. Device A - sendPacket() is called
     * 2. Device A - sendPacket() calls processPacket() - Device B
     * 3. Device B - processPacket() Will send the packet to the correct device - Device C
     * 4. Device C - the correct device will return a packet with information - Device B
     * 5. Device B - return the packet received from Device C - Device A
     * 6. Device A - got the packet it wanted
     */

    /**
     * Send a packet to the device
     * @param packet Packet to be sent
     * @return Packet to be returned
     */
    public abstract Packet sendPacket(Packet packet, AbsDevice sender);

    /**
     * Process a packet (send to correct device or process the packet)
     * @param packet Packet to process
     * @return Packet to send
     */
    public abstract Packet processPacket(Packet packet, AbsDevice sender);


    @Override
    public String toString() {
        return "AbsDevice{" +
                "status=" + status +
                ", ip=" + ip +
                ", mac=" + mac +
                '}';
    }
}
