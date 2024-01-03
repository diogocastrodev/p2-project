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

    public DHCPType getType() {
        return type;
    }

    public void setType(DHCPType type) {
        this.type = type;
    }

    public IP getIpDHCP() {
        return ipDHCP;
    }

    public void setIpDHCP(IP ipDHCP) {
        this.ipDHCP = ipDHCP;
    }

    public IP getInitialIP() {
        return initialIP;
    }

    public void setInitialIP(IP initialIP) {
        this.initialIP = initialIP;
    }

    public IP getFinalIP() {
        return finalIP;
    }

    public void setFinalIP(IP finalIP) {
        this.finalIP = finalIP;
    }

    public IP getSubnetMask() {
        return subnetMask;
    }

    public void setSubnetMask(IP subnetMask) {
        this.subnetMask = subnetMask;
    }

    public IP getGateway() {
        return gateway;
    }

    public void setGateway(IP gateway) {
        this.gateway = gateway;
    }

    public IP getDns() {
        return dns;
    }

    public void setDns(IP dns) {
        this.dns = dns;
    }

}
