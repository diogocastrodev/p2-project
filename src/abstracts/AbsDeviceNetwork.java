package abstracts;

import classes.addresses.IP;
import classes.addresses.Mac;
import classes.exceptions.InvalidArgumentException;
import classes.dhcp.DHCPDist;

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
    public AbsDeviceNetwork(Mac mac, IP ip, int portsAmount) throws InvalidArgumentException {
        super(mac, ip);
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
     * Connection to an END device
     * @param port
     * @param device
     * @throws InvalidArgumentException
     */
    public void setPort(int port, AbsDevice device) throws InvalidArgumentException {
        if (device == null) { // Verificar se o device é válido
            throw new InvalidArgumentException("Device is not valid"); // Mensagem de erro
        }
        if (port <= 0 || port > this.getPortsAmount()) { // Verificar se o número da porta é válido
            throw new InvalidArgumentException("Port is not valid"); // Mensagem de erro
        }
        if (this.ports.containsKey(port)) { // Verificar se o port já está em uso
            throw new InvalidArgumentException("Port is already in use"); // Mensagem de erro
        }
        AbsDeviceEnd t = ((AbsDeviceEnd) device);
        if (t.getConnectedDevice() != null) { // Verificar se o AbsDeviceEnd já está conectado a outro device
            throw new InvalidArgumentException("Device is already connected to another device."); // Mensagem de erro
        } else {
            t.forceSetConnectedDevice(this); // Chamar o método forceSetConnectedDevice do AbsDeviceEnd para atribuir este device
        }
        this.ports.put(port, device); // Adicionar o device ao port
    }

    public void connectDevice(int port, AbsDevice device) throws InvalidArgumentException {
        this.setPort(port, device);
    }

    public void forceSetPort(int port, AbsDevice device) {
        this.ports.put(port, device);
    }

    /**
     * Connection to a NETWORK device
     * @param port Port on THIS device to connect
     * @param device Device to connect
     * @param portDevice That'll be the port IN the device is connected to this device
     */
    public void setPort(int port, AbsDevice device, int portDevice) throws InvalidArgumentException {
        if (device == null) { // Verificar se o device é válido
            throw new InvalidArgumentException("Device is not valid"); // Mensagem de erro
        }
        if (port <= 0 || port > this.getPortsAmount()) { // Verificar se o número da porta é válido
            throw new InvalidArgumentException("Port is not valid"); // Mensagem de erro
        }
        if (this.ports.containsKey(port)) { // Verificar se o port já está em uso
            throw new InvalidArgumentException("Port is already in use"); // Mensagem de erro
        }
        AbsDeviceNetwork t = ((AbsDeviceNetwork) device);
        if (portDevice <= 0 || portDevice > t.getPortsAmount()) { // Verificar se o portDevice é válido
            throw new InvalidArgumentException("PortDevice is not valid"); // Mensagem de erro
        }
        AbsDevice b = t.getPort(portDevice); // Verificar se o portDevice é válido
        if (b != null) { // Verificar se o portDevice é válido
            throw new InvalidArgumentException("PortDevice is already in use"); // Mensagem de erro
        } else {

            t.forceSetPort(portDevice, this); // Chamar o método forceSetConnectedDevice do AbsDeviceEnd para atribuir este device
        }
        this.forceSetPort(port, device); // Adicionar o device ao port
    }

    public void connectDevice(int port, AbsDevice device, int portDevice) throws InvalidArgumentException {
        this.setPort(port, device, portDevice);
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
    @Override
    public String toString() {

        String ports = "";

        for (int i = 1; i <= this.getPortsAmount(); i++) {
            AbsDevice device = this.ports.get(i);
            if (device != null) {
                ports += "{" + i + "='" + device.getMac() + "'}";
            } else {
                ports += "{" + i + "='null'}";
            }
        }

        return "AbsDeviceNetwork{" +
                "dhcpDist=" + dhcpDist +
                ", mac='" + super.getMac() + '\'' +
                ", ip='" + super.getIP() + '\'' +
                ", portsAmount='" + portsAmount + '\'' +
                ", ports=" + ports +
                '}';
    }
}
