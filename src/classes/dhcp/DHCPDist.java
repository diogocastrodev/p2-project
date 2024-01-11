package classes.dhcp;

import abstracts.AbsDeviceNetwork;
import classes.addresses.IP;
import classes.addresses.SubnetMask;
import classes.exceptions.InvalidArgumentException;
import classes.logger.Logger;
import classes.packages.Packet;
import classes.protocols.DHCP;
import classes.protocols.ICMP;
import enums.DHCPType;
import enums.Operation;
import enums.Protocols;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class DHCPDist implements Serializable {

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
    private SubnetMask subnetMask;
    /**
     * SERVER: Gateway
     */
    private IP gateway;
    /**
     * SERVER: DNS (Optional)
     */
    private IP dns;

    private Set<IP> ipsGiven;

    /**
     * DHCP Server with DNS
     * @param initialIP Initial IP
     * @param finalIP Final IP
     * @param subnetMask Subnet Mask
     * @param gateway Gateway
     * @param dns DNS
     */
    public DHCPDist(IP initialIP, IP finalIP, SubnetMask subnetMask, IP gateway, IP dns) throws InvalidArgumentException {
        this.setType(DHCPType.Server);
        this.setInitialIP(initialIP);
        this.setFinalIP(finalIP);
        this.setSubnetMask(subnetMask);
        this.setGateway(gateway);
        this.setDns(dns);
        this.ipsGiven = new HashSet<>();
    }

    /**
     * DHCP Server without DNS
     * @param initialIP Initial IP
     * @param finalIP Final IP
     * @param subnetMask Subnet Mask
     * @param gateway Gateway
     */
    public DHCPDist(IP initialIP, IP finalIP, SubnetMask subnetMask, IP gateway) throws InvalidArgumentException {
        this.setType(DHCPType.Server);
        this.setInitialIP(initialIP);
        this.setFinalIP(finalIP);
        this.setSubnetMask(subnetMask);
        this.setGateway(gateway);
        this.ipsGiven = new HashSet<>();
    }

    /**
     * DHCP Relay
     * @param ipDHCP IP of DHCP Server
     */
    public DHCPDist(IP ipDHCP) {
        this.setType(DHCPType.Relay);
        this.setIpDHCP(ipDHCP);
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

    public void setFinalIP(IP finalIP) throws InvalidArgumentException {
        // Verficar se finalIP é maior que initialIP
        int[] ipInitialParts = this.getInitialIP().parseIPInteger();
        int[] ipFinalParts = finalIP.parseIPInteger();
        if (finalIP.equals(this.getInitialIP())) {
            throw new InvalidArgumentException("IP final deve ser maior que IP inicial");
        }
        for (int i = 0; i < ipInitialParts.length; i++) {
            if (ipFinalParts[i] < ipInitialParts[i]) {
                throw new InvalidArgumentException("IP final deve ser maior que IP inicial");
            }
        }
        this.finalIP = finalIP;
    }

    public SubnetMask getSubnetMask() {
        return subnetMask;
    }

    public void setSubnetMask(SubnetMask subnetMask) {
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

    public Packet processServer(AbsDeviceNetwork device, Packet packet) throws InvalidArgumentException {
        if (device == null || packet == null) {
            throw new InvalidArgumentException("Argumentos inválidos");
        }
        if (!packet.getProtocolType().equals(Protocols.DHCP)) {
            throw new InvalidArgumentException("Protocolo inválido");
        }
        if (this.getType().equals(DHCPType.Relay)) {
            throw new InvalidArgumentException("Este dispositivo não é um servidor DHCP");
        }
        DHCP request = packet.getDHCP();
        DHCP answer = new DHCP(Operation.Reply, request.getClientMac(), device.getIP());
        answer.setServerName("Não sei, mas tem isto: " + device.getMac());


        IP ipGiven = null;
        while (true) {
            ipGiven = this.getSubnetMask().generateRandomIPInsideMask(this.getInitialIP(), this.getFinalIP());
            new Logger().addLog(Protocols.DHCP, "Generated IP: " + ipGiven.toString());
            if (!this.ipsGiven.contains(ipGiven)) {
                int seqNumber = Math.abs((int) (Math.random() * 1000));
                ICMP icmp = new ICMP(device.getIP(), ipGiven, "Check IP Existence", 1, seqNumber);
                Packet p = new Packet(icmp, Protocols.ICMP);
                Packet ans = device.sendPacket(p, device);
                if (ans == null) {
                    // IP não existe
                    this.ipsGiven.add(ipGiven);
                    break;
                }
            }
        }

        answer.setClientIP(ipGiven);

        return new Packet(answer, Protocols.DHCP);
    }

}
