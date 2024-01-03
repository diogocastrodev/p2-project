package classes.protocols;

import classes.addresses.IP;
import classes.addresses.Mac;
import classes.exceptions.InvalidArgumentException;
import enums.Operation;

public class DHCP extends IPProtocol implements Protocol {
    /**
     * Operation
     * 1 for request, 2 for reply
     */
    private Operation op;
    /**
     * Hardware type
     */
    private int htype = 1;
    /**
     * Hardware address length
     */
    private int hlen = 6;
    /**
     * Hops
     */
    private int hops = 0;
    /**
     * Transaction ID
     */
    private int transactionID = 0;
    /**
     * Seconds
     */
    private int seconds = 0;
    /**
     * Flags
     */
    private int flags = 0x8000;
    /**
     * Client IP
     */
    private IP clientIP;
    /**
     * Your Client IP (What the server is offering)
     */
    private IP yourIP;
    /**
     * Server IP
     */
    private IP serverIP;
    /**
     * Relay IP
     */
    private IP relayIP = new IP();
    /**
     * Client Mac
     */
    private Mac clientMac;
    /**
     * Server Name
     */
    private String serverName;
    /**
     * Boot File Name
     */
    private String bootFileName = "bootfile";
    /**
     * Options
     */
    private Object options = new Object();

    /**
     * Constructor
     * @param op Operation
     * @param clientMac Client Mac
     * @param serverIP Server IP
     * @throws InvalidArgumentException
     */
    public DHCP(Operation op, Mac clientMac, IP serverIP) throws InvalidArgumentException {
        this.setOp(op);
        this.setServerIP(serverIP);
        this.setClientMac(clientMac);
    }

    /**
     * Constructor
     * @param op Operation
     * @param clientMac Client Mac
     * @throws InvalidArgumentException
     */
    public DHCP(Operation op, Mac clientMac) throws InvalidArgumentException {
        this.setOp(op);
        this.setClientMac(clientMac);
    }

    /**
     * Set Operation
     * @param op Operation
     */
    private void setOp(Operation op) {
        this.op = op;
    }

    /**
     * Get Operation
     * @return Operation
     */
    public Operation getOp() {
        return op;
    }

    /**
     * Get Hardware Type
     * @return Hardware Type
     */
    public int getHtype() {
        return htype;
    }

    /**
     * Set Hardware Type
     * @param htype Hardware Type
     */
    public void setHtype(int htype) {
        this.htype = htype;
    }

    /**
     * Get Hardware Address Length
     * @return Hardware Address Length
     */
    public int getHlen() {
        return hlen;
    }

    /**
     * Set Hardware Address Length
     * @param hlen Hardware Address Length
     */
    public void setHlen(int hlen) {
        this.hlen = hlen;
    }

    /**
     * Get Hops
     * @return Hops
     */
    public int getHops() {
        return hops;
    }

    /**
     * Set Hops
     * @param hops Hops
     */
    public void setHops(int hops) {
        this.hops = hops;
    }

    /**
     * Get Transaction ID
     * @return Transaction ID
     */
    public int getTransactionID() {
        return transactionID;
    }

    /**
     * Set Transaction ID
     * @param transactionID Transaction ID
     */
    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    /**
     * Get Seconds
     * @return Seconds
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * Set Seconds
     * @param seconds Seconds
     */
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    /**
     * Get Flags
     * @return Flags
     */
    public int getDHCPFlags() {
        return flags;
    }

    /**
     * Set Flags
     * @param flags Flags
     */
    public void setDHCPFlags(int flags) {
        this.flags = flags;
    }

    /**
     * Get Client IP
     * @return Client IP
     */
    public IP getClientIP() {
        return clientIP;
    }

    /**
     * Set Client IP
     * @param clientIP Client IP
     */
    public void setClientIP(IP clientIP) {
        this.clientIP = clientIP;
    }

    /**
     * Get Your IP
     * @return Your IP
     */
    public IP getYourIP() {
        return yourIP;
    }

    /**
     * Set Your IP
     * @param yourIP Your IP
     */
    public void setYourIP(IP yourIP) {
        this.yourIP = yourIP;
    }

    /**
     * Get Server IP
     * @return Server IP
     */
    public IP getServerIP() {
        return serverIP;
    }

    /**
     * Set Server IP
     * @param serverIP Server IP
     */
    public void setServerIP(IP serverIP) {
        this.serverIP = serverIP;
    }

    /**
     * Get Relay IP
     * @return Relay IP
     */
    public IP getRelayIP() {
        return relayIP;
    }

    /**
     * Set Relay IP
     * @param relayIP Relay IP
     */
    public void setRelayIP(IP relayIP) {
        this.relayIP = relayIP;
    }

    /**
     * Get Client Mac
     * @return Client Mac
     */
    public Mac getClientMac() {
        return clientMac;
    }

    /**
     * Set Client Mac
     * @param clientMac Client Mac
     */
    public void setClientMac(Mac clientMac) {
        this.clientMac = clientMac;
    }

    /**
     * Get Server Name
     * @return Server Name
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * Set Server Name
     * @param serverName Server Name
     */
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    /**
     * Get Boot File Name
     * @return Boot File Name
     */
    public String getBootFileName() {
        return bootFileName;
    }

    /**
     * Set Boot File Name
     * @param bootFileName Boot File Name
     */
    public void setBootFileName(String bootFileName) {
        this.bootFileName = bootFileName;
    }

    /**
     * Get Options
     * @return Options
     */
    public Object getOptions() {
        return options;
    }

    /**
     * Set Options
     * @param options Options
     */
    public void setOptions(Object options) {
        this.options = options;
    }
}
