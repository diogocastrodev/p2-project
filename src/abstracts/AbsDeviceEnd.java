package abstracts;

import abstracts.AbsDevice;
import classes.addresses.IP;
import classes.addresses.Mac;
import classes.exceptions.InvalidArgumentException;
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

    /**************************************************************************
     * Constructors
     **************************************************************************/
    /**
     * Constructor
     * @param ip IP address
     * @param mac MAC address
     */
    public AbsDeviceEnd(IP ip, Mac mac) {
        super(mac, ip);
        this.setConnection(Connection.ETHERNET);
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

    public void forceSetConnectedDevice(AbsDevice connectedDevice) {
        this.connectedDevice = connectedDevice;
    }

    /**
     * Connection to an END device.
     * @param connectedDevice Device connected to this device.
     */
    public void setConnectedDevice(AbsDevice connectedDevice) throws InvalidArgumentException {
        if (connectedDevice == null) {
            throw new InvalidArgumentException("Device is not valid.");
        }
        AbsDeviceEnd t = ((AbsDeviceEnd) connectedDevice);
        if (t.getConnectedDevice() != null) {
            throw new InvalidArgumentException("Device is already connected to another device.");
        } else {
            t.forceSetConnectedDevice(this);
        }
        this.forceSetConnectedDevice(connectedDevice);

    }
    /**
     * Connection to a Network device.
     */
    public void setConnectedDevice(AbsDevice connectedDevice, int port) throws InvalidArgumentException {
        if (connectedDevice == null) {
            throw new InvalidArgumentException("Device is not valid.");
        }
        AbsDeviceNetwork t = ((AbsDeviceNetwork) connectedDevice);
        t.setPort(port, this);
        this.forceSetConnectedDevice(connectedDevice);
    }

    /**************************************************************************
     * Methods
     **************************************************************************/

    @Override
    public String toString() {
        return "AbsDeviceEnd{" +
                "ip=" + this.getIP() + ", " +
                "mac=" + this.getMac() + ", " +
                "status=" + this.getStatus() + ", " +
                "connection=" + this.getConnection() + ", " +
                "connectedDevice=" + this.getConnectedDevice() + '}';
    }
}
