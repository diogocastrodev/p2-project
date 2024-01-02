package classes.devices;

import abstracts.AbsDeviceEnd;
import classes.addresses.IP;
import classes.addresses.Mac;
import classes.exceptions.InvalidArgumentException;
import classes.packages.Packet;
import enums.Connection;

public class Device extends AbsDeviceEnd {

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
    public String toString() {
        String name = this.getName() != null ? this.getName() : "unknown"; // Se o nome for null, então é "unknown"
        String connection = this.getConnection() != null ? this.getConnection().name() : "unknown"; // Se a conexão for null, então é "unknown"
        return "{" +
                "name='" + name + '\'' +
                ", connection='" + connection + '\'' +
                ", ip='" + this.getIP() + '\'' +
                ", mac='" + super.getMac() + '\'' +
                '}';
    }

    @Override
    public Packet sendPacket(Packet packet) {
        return null;
    }

    @Override
    public Packet processPacket(Packet packet) {
        return null;
    }
}
