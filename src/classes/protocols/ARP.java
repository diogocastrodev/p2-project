package classes.protocols;

import classes.addresses.IP;
import classes.addresses.Mac;
import enums.Operation;

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
    private Operation operation;
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

    /**
     * Constructor
     * @param operation Operation
     * @param sourceMac Source Mac Address
     * @param sourceIP Source IP Address
     * @param targetMac Target Mac Address
     * @param targetIP Target IP Address
     */
    public ARP(Operation operation, Mac sourceMac, IP sourceIP, Mac targetMac, IP targetIP) {
        this.operation = operation;
        this.sourceMac = sourceMac;
        this.sourceIP = sourceIP;
        this.targetMac = targetMac;
        this.targetIP = targetIP;
    }

    /**
     * Hardware type (HTYPE)
     * @return Hardware type
     */
    public int getHardwareType() {
        return hardwareType;
    }

    /**
     * Hardware type (HTYPE)
     * @param hardwareType Hardware type
     */
    public void setHardwareType(int hardwareType) {
        this.hardwareType = hardwareType;
    }

    /**
     * Protocol type (PTYPE)
     * @return Protocol type
     */
    public int getProtocolType() {
        return protocolType;
    }

    /**
     * Protocol type (PTYPE)
     * @param protocolType Protocol type
     */
    public void setProtocolType(int protocolType) {
        this.protocolType = protocolType;
    }

    /**
     * Hardware address length (HLEN)
     * @return Hardware address length
     */
    public int getHLEN() {
        return HLEN;
    }

    /**
     * Hardware address length (HLEN)
     * @param HLEN Hardware address length
     */
    public void setHLEN(int HLEN) {
        this.HLEN = HLEN;
    }

    /**
     * Protocol address length (PLEN)
     * @return Protocol address length
     */
    public int getPLEN() {
        return PLEN;
    }

    /**
     * Protocol address length (PLEN)
     * @param PLEN Protocol address length
     */
    public void setPLEN(int PLEN) {
        this.PLEN = PLEN;
    }

    /**
     * Operation (OPER)
     * @return Operation
     */
    public Operation getOperation() {
        return operation;
    }

    /**
     * Operation (OPER)
     * @param operation Operation
     */
    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    /**
     * Source Mac Address
     * @return Source Mac Address
     */
    public Mac getSourceMac() {
        return sourceMac;
    }

    /**
     * Source Mac Address
     * @param sourceMac Source Mac Address
     */
    public void setSourceMac(Mac sourceMac) {
        this.sourceMac = sourceMac;
    }

    /**
     * Source IP Address
     * @return Source IP Address
     */
    public IP getSourceIP() {
        return sourceIP;
    }

    /**
     * Source IP Address
     * @param sourceIP Source IP Address
     */
    public void setSourceIP(IP sourceIP) {
        this.sourceIP = sourceIP;
    }

    /**
     * Target Mac Address
     * @return Target Mac Address
     */
    public Mac getTargetMac() {
        return targetMac;
    }

    /**
     * Target Mac Address
     * @param targetMac Target Mac Address
     */
    public void setTargetMac(Mac targetMac) {
        this.targetMac = targetMac;
    }

    /**
     * Target IP Address
     * @return Target IP Address
     */
    public IP getTargetIP() {
        return targetIP;
    }

    /**
     * Target IP Address
     * @param targetIP Target IP Address
     */
    public void setTargetIP(IP targetIP) {
        this.targetIP = targetIP;
    }

    /**
     * String representation of the object
     * @return String representation of the object
     */
    @Override
    public String toString() {
        return "ARP{" +
                "hardwareType=" + hardwareType + ", " +
                "protocolType=" + protocolType + ", " +
                "HLEN=" + HLEN + ", " +
                "PLEN=" + PLEN + ", " +
                "operation=" + operation + ", " +
                "sourceMac=" + sourceMac + ", " +
                "sourceIP=" + sourceIP + ", " +
                "targetMac=" + targetMac + ", " +
                "targetIP=" + targetIP + '}';
    }
}
