package classes.protocols;

import classes.addresses.IP;
import classes.addresses.Mac;
import enums.ARPOperation;

public class ARP implements Protocol {
    /**
     * Hardware type (HTYPE)
     * 1 for Ethernet
     */
    private int hardwareType = 0x0001;
    /**
     * Protocol type (PTYPE)
     * 0x0800 for IPv4
     */
    private int protocolType = 0x0800;
    /**
     * Hardware address length (HLEN)
     * 6 for Ethernet
     */
    private int HLEN = 0x06;
    /**
     * Protocol address length (PLEN)
     * 4 for IPv4
     */
    private int PLEN = 0x04;
    /**
     * Operation (OPER)
     * 1 for request, 2 for reply
     */
    private ARPOperation operation;
    /**
     * Source Mac Address
     */
    private Mac sourceMac;
    /**
     * Source IP Address
     */
    private IP sourceIP;
    /**
     * Target Mac Address
     */
    private Mac targetMac;
    /**
     * Target IP Address
     */
    private IP targetIP;

    public ARP(ARPOperation operation, Mac sourceMac, IP sourceIP, Mac targetMac, IP targetIP) {
        this.operation = operation;
        this.sourceMac = sourceMac;
        this.sourceIP = sourceIP;
        this.targetMac = targetMac;
        this.targetIP = targetIP;
    }
}
