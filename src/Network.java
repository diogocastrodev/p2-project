import abstracts.AbsDevice;

/**
 * This class will be used to create a network of nodes.
 * The network will be used to simulate the network of nodes
 */
public class Network {
    /**
     * List of nodes in the network
     */
    private AbsDevice[] devices;

    /**
     * Constructor for Network
     * @param devices List of nodes in the network
     */
    public Network(AbsDevice[] devices) {
        this.devices = devices;
    }

    /**
     * Get the list of nodes in the network
     * @return List of nodes in the network
     */
    public AbsDevice[] getDevices() {
        return devices;
    }

    /**
     * Set the list of nodes in the network
     * @param devices List of nodes in the network
     */
    public void addDevice(AbsDevice[] devices) {
        this.devices = devices;
    }
}
