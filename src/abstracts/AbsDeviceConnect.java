package abstracts;

import classes.exceptions.InvalidArgumentException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Abstract class for devices that can connect multiple devices to it
 */
public abstract class AbsDeviceConnect extends AbsDevice {

    /**
     * Amount of ports available on the hub
     */
    private int portCount;
    /**
     * Devices connected to the hub
     * Key: Port number
     * Value: Device connected to the port
     */
    private Map<Integer, AbsDevice> devices;

    public AbsDeviceConnect(String ip, String mac, int portCount) throws InvalidArgumentException {
        super(ip, mac);
        this.setPortCount(portCount);
        devices = new HashMap<>();
    }

    public AbsDeviceConnect(String ip, String mac, AbsDevice connectedDevice, int portCount) throws InvalidArgumentException {
        super(ip, mac, connectedDevice);
        this.setPortCount(portCount);
        devices = new HashMap<>();
    }
    /**
     * Get the amount of ports available on the hub
     * @return Amount of ports available on the hub
     */
    public int getPortCount() {
        return portCount;
    }

    /**
     * Set the amount of ports available on the hub
     * @param portCount Amount of ports available on the hub (Must be at least 2)
     */
    public void setPortCount(int portCount) throws IllegalArgumentException {
        if (portCount < 2) { // 1 port for input, At least 1 port for output
            throw new IllegalArgumentException("Port count must be at least 2");
        }
        this.portCount = portCount;
    }

    /**
     * Get devices connected to the hub
     * @return Devices connected to the hub [Port, Device]
     */
    public Map<Integer, AbsDevice> getDevices() {
        return this.devices;
    }

    /**
     * Add devices to the hub
     * @param devices Devices to add
     * @return List of devices successfully added
     */
    public List<AbsDevice> addDevices(AbsDevice ...devices) throws IllegalArgumentException {
        if (this.devices.size() >= portCount) {
            throw new IllegalArgumentException("Maximum number of devices reached");
        }
        List<AbsDevice> addedDevices = List.of(devices);
        for (AbsDevice device : devices) {
            this.devices.put(this.devices.size() + 1, device);
            addedDevices.add(device);
        }
        return addedDevices;
    }

    /**
     * Add devices to the hub
     * @param devices Devices to add
     * @return List of devices successfully added
     */
    public List<AbsDevice> addDevices(AbsDeviceConnect ...devices) throws IllegalArgumentException {
        if (this.devices.size() >= portCount) {
            throw new IllegalArgumentException("Maximum number of devices reached");
        }
        List<AbsDevice> addedDevices = List.of(devices);
        for (AbsDevice device : devices) {
            this.devices.put(this.devices.size() + 1, device);
            addedDevices.add(device);
        }
        return addedDevices;
    }

    /**
     * Remove devices from the hub
     * @param port Port number of the device to remove
     * @return boolean indicating if the device was removed
     */
    public boolean removeDevice(int port) throws IllegalArgumentException {
        if (port < 1 || port > this.portCount) {
            throw new IllegalArgumentException("Invalid port number");
        }
        if (this.devices.containsKey(port)) {
            this.devices.remove(port);
            return true;
        }
        return false;
    }
}
