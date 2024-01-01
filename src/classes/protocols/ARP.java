package classes.protocols;

import classes.addresses.IP;
import classes.addresses.Mac;

public class ARP {
    private String hardwareType = "0x0001";
    private String protocolType = "0x0800";
    private String HLEN = "0x06";
    private String PLEN = "0x04";
    private int operation; // 1 for request, 2 for reply
    private Mac sourceMac;
    private IP sourceIP;
    private Mac targetMac;
    private IP targetIP;
}
