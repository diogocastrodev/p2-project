package abstracts;

import abstracts.AbsDevice;
import classes.addresses.IP;
import classes.addresses.Mac;
import enums.Connection;

/**
 * This is a class for a device
 * with a single port.
 */
public abstract class AbsDeviceEnd extends AbsDevice {

    /**************************************************************************
     * Variables
     **************************************************************************/
    /**
     * The connection type of the device.
     */
    private Connection connection;
    /**
     * The device connected to this device.
     */
    private AbsDevice connectedDevice;
    /**
     * IP address of the device
     */
    private IP ip;

    /**************************************************************************
     * Constructors
     **************************************************************************/
    /**
     * Constructor
     * @param ip IP address
     * @param mac MAC address
     */
    public AbsDeviceEnd(IP ip, Mac mac) {
        super(mac);
        this.connection = connection;
    }
    /**************************************************************************
     * Getters and Setters
     **************************************************************************/
    /**
     * Get the connection type of the device.
     * @return Connection type of the device.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Set the connection type of the device.
     * @param connection Connection type of the device.
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * Get the device connected to this device.
     * @return Device connected to this device.
     */
    public AbsDevice getConnectedDevice() {
        return connectedDevice;
    }

    /**
     * Set the device connected to this device.
     * @param connectedDevice Device connected to this device.
     */
    public void setConnectedDevice(AbsDevice connectedDevice) {
        // TODO: Check if the device is already connected to another device.
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

    /**************************************************************************
     * Methods
     **************************************************************************/

}
