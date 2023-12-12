package classes.devices;

import abstracts.AbsDevice;
import classes.exceptions.InvalidArgumentException;
import enums.Connection;

public class Device extends AbsDevice {

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
    public Device(String ip, String mac) throws InvalidArgumentException {
        super(ip, mac);
    }

    /**
     * The constructor for the Device class ( Main things + name)
     * @param ip The IP address of the device
     * @param mac The MAC address of the device
     * @param name The name of the device
     */
    public Device(String ip, String mac, String name) throws InvalidArgumentException {
        super(ip, mac);
        this.setName(name);
    }

    public Device(String ip, String mac, String name, Connection connection) throws InvalidArgumentException {
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
        this.connection = Connection.valueOf(connection.toUpperCase());
    }

    @Override
    public String toString() {
        String name = this.getName() != null ? this.getName() : "unknown";
        String connection = this.getConnection() != null ? this.getConnection().name() : "unknown";
        return "{" +
                "name='" + name + '\'' +
                ", connection='" + connection + '\'' +
                ", ip='" + super.getIp() + '\'' +
                ", mac='" + super.getMac() + '\'' +
                '}';
    }
}
