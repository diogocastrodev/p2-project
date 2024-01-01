package classes.dhcp;

import classes.addresses.IP;
import enums.DHCPType;

public class DHCPDist {

    /**
     * DHCP Type
     */
    private DHCPType type;
    /**
     * RELAY: IP of DHCP Server
     */
    private IP ipDHCP;

    /**
     * SERVER: Initial IP
     */
    private IP initialIP;
    /**
     * SERVER: Final IP
     */
    private IP finalIP;
    /**
     * SERVER: Subnet Mask
     */
    private IP subnetMask;
    /**
     * SERVER: Gateway
     */
    private IP gateway;
    /**
     * SERVER: DNS (Optional)
     */
    private IP dns;

    /**
     * DHCP Server with DNS
     * @param initialIP Initial IP
     * @param finalIP Final IP
     * @param subnetMask Subnet Mask
     * @param gateway Gateway
     * @param dns DNS
     */
    public DHCPDist(IP initialIP, IP finalIP, IP subnetMask, IP gateway, IP dns) {
        this.type = DHCPType.Server;
        this.initialIP = initialIP;
        this.finalIP = finalIP;
        this.subnetMask = subnetMask;
        this.gateway = gateway;
        this.dns = dns;
    }

    /**
     * DHCP Server without DNS
     * @param initialIP Initial IP
     * @param finalIP Final IP
     * @param subnetMask Subnet Mask
     * @param gateway Gateway
     */
    public DHCPDist(IP initialIP, IP finalIP, IP subnetMask, IP gateway) {
        this.type = DHCPType.Server;
        this.initialIP = initialIP;
        this.finalIP = finalIP;
        this.subnetMask = subnetMask;
        this.gateway = gateway;
    }

    /**
     * DHCP Relay
     * @param ipDHCP IP of DHCP Server
     */
    public DHCPDist(IP ipDHCP) {
        this.type = DHCPType.Relay;
        this.ipDHCP = ipDHCP;
    }

}
