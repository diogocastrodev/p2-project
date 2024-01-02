package classes.protocols;

import classes.addresses.IP;
import classes.addresses.Mac;

public class DHCP extends IPProtocol implements Protocol {
    private int op;
    private int htype;
    private int hlen;
    private int hops;
    private int transactionID;
    private int seconds;
    private String flags;
    private IP clientIP;
    private IP yourIP;
    private IP serverIP;
    private IP relayIP;
    private Mac clientMac;
    private String serverName;
    private String bootFileName;
    private Object options;

    private void setOp(int op) {

        this.op = op;
    }
}
