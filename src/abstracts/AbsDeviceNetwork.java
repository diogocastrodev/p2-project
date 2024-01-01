package abstracts;

import classes.addresses.Mac;
import classes.exceptions.InvalidArgumentException;
import classes.protocols.DHCPDist;

import java.util.HashMap;
import java.util.Map;

/**
 * This is a class for a device
 * with multiple ports
 * and a DHCP configuration.
 */
public abstract class AbsDeviceNetwork extends AbsDevice {
    /**************************************************************************
     * Variables
     **************************************************************************/
    /**
     * DHCP Configuration
     */
    private DHCPDist dhcpDist;
    /**
     * Amount of ports
     */
    private int portsAmount;
    /**
     * Ports of the device 1-PortAmount
     */
    private Map<Integer, AbsDevice> ports;



    /**************************************************************************
     * Constructors
     **************************************************************************/

    /**
     * Constructor
     * @param mac MAC address
     */
    public AbsDeviceNetwork(Mac mac, int portsAmount) throws InvalidArgumentException {
        super(mac);
        this.setPortsAmount(portsAmount);
        this.ports = new HashMap<>(portsAmount);
    }

    /**************************************************************************
     * Getters and Setters
     **************************************************************************/
    /**
     * Get the DHCP configuration
     * @return DHCP configuration
     */
    public DHCPDist getDhcp() {
        return dhcpDist;
    }

    /**
     * Set the DHCP configuration
     * @param dhcpDist
     */
    public void setDhcp(DHCPDist dhcpDist) {
        this.dhcpDist = dhcpDist;
    }

    /**
     * Get the amount of ports
     * @return int Amount of ports
     */
    public int getPortsAmount() {
        return portsAmount;
    }

    /**
     * Set the amount of ports
     * @param portsAmount Amount of ports
     */
    private void setPortsAmount(int portsAmount) throws InvalidArgumentException {
        if (portsAmount < 2) {
            throw new InvalidArgumentException("Ports amount is not valid, must be at least 2");
        }
        this.portsAmount = portsAmount;
    }

    /**
     * Get the ports of the device
     */
    public Map<Integer, AbsDevice> getPorts() {
        return this.ports;
    }

    /**
     * Set the ports of the device
     */
    public void setPort(int port, AbsDevice device) throws InvalidArgumentException {
        if (port <= 0 || port > this.getPortsAmount()) {
            throw new InvalidArgumentException("Port is not valid");
        }
        // TODO: Check if port is valid and if can connect to device (the other device has a free port)
        this.ports.put(port, device);
    }

    /**
     * Remove a port of the device
     */
    public AbsDevice removePort(int port) throws InvalidArgumentException {
        if (port <= 0 || port > this.getPortsAmount()) {
            throw new InvalidArgumentException("Port is not valid");
        }
        return this.ports.remove(port);
    }

    /**
     * Get the device connected to a port
     */
    public AbsDevice getPort(int port) throws InvalidArgumentException {
        if (port <= 0 || port > this.getPortsAmount()) {
            throw new InvalidArgumentException("Port is not valid");
        }
        return this.ports.get(port);
    }

    /**************************************************************************
     * Methods
     **************************************************************************/
}
