package abstracts;

import classes.addresses.IP;
import classes.addresses.Mac;
import classes.exceptions.InvalidArgumentException;

/**
 * Abstract class for a common device
 */
public abstract class AbsDevice {
    /**
     * IP address of the device
     */
    private IP ip;
    /**
     * MAC address of the device
     */
    private Mac mac;
    /**
     * Device connected to this device
     * This is used to create a network
     * Can be null
     */
    private AbsDevice connectedDevice;

    /**
     * Constructor
     * @param ip IP address
     * @param mac MAC address
     */
    public AbsDevice(String ip, String mac) throws InvalidArgumentException {
        this.setIp(ip);
        this.setMac(mac);
    }

    /**
     * Constructor
     * @param ip IP address
     * @param mac MAC address
     * @param connectedDevice Device connected to this device
     */
    public AbsDevice(String ip, String mac, AbsDevice connectedDevice) throws InvalidArgumentException {
        this.setIp(ip);
        this.setMac(mac);
        this.setConnectedDevice(connectedDevice);
    }

    /**
     * Get the device connected to this device
     * @return Device connected to this device
     */
    public AbsDevice getConnectedDevice() {
        return connectedDevice;
    }

    /**
     * Set the device connected to this device
     * @param connectedDevice Device connected to this device
     */
    public void setConnectedDevice(AbsDevice connectedDevice) {
        this.connectedDevice = connectedDevice;
    }

    /**
     * Get the IP address of the device
     * @return IP address of the device
     */
    public String getIp() {
        return this.ip.getIP();
    }

    /**
     * Set the IP address of the device
     * @param ip IP address of the device
     */
    public void setIp(String ip) throws InvalidArgumentException {
        this.ip = new IP(ip);
    }

    /**
     * Get the MAC address of the device
     * @return MAC address of the device
     */
    public String getMac() {
        return this.mac.getMac();
    }

    /**
     * Set the MAC address of the device (00:00:00:00:00:00 | 00-00-00-00-00-00 | 000000000000)
     * @param mac MAC address of the device
     */
    public void setMac(String mac) throws InvalidArgumentException {
        this.mac = new Mac(mac);
    }

    /**
     * Get the device type
     * @return Device type
     */
    @Override
    public String toString() {
        return "IP: " + this.getIp() + "\nMAC: " + this.getMac();
    }
}
