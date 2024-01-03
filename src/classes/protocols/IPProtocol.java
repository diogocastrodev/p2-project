package classes.protocols;

import classes.addresses.IP;

public class IPProtocol {
    /**
     * Version
     * 4 for IPv4
     */
    private int version = 4;
    /**
     * Internet Header Length
     * 5 for 20 bytes
     */
    private int IHL = 5;
    /**
     * Differentiated Services Code Point
     * 0 for default
     */
    private int DSCP = 0;
    /**
     * TL
     */
    private int TL;
    /**
     * Identification
     */
    private int identification;
    /**
     * Flags
     */
    private int flags = 0;
    /**
     * Fragment Offset
     */
    private int fragmentOffset = 0;
    /**
     * Time to Live
     */
    private int TTL = 128;
    /**
     * Protocol
     * 1 for ICMP
     * 6 for TCP
     *
     */
    private int protocol;
    /**
     * Header Checksum
     */
    private int headerChecksum;
    /**
     * Source Address
     */
    private IP sourceAddress;
    /**
     * Destination Address
     */
    private IP destinationAddress;
    /**
     * Data
     */
    private Object data;


    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getIHL() {
        return IHL;
    }

    public void setIHL(int IHL) {
        this.IHL = IHL;
    }

    public int getDSCP() {
        return DSCP;
    }

    public void setDSCP(int DSCP) {
        this.DSCP = DSCP;
    }

    public int getTL() {
        return TL;
    }

    public void setTL(int TL) {
        this.TL = TL;
    }

    public int getIdentification() {
        return identification;
    }

    public void setIdentification(int identification) {
        this.identification = identification;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public int getFragmentOffset() {
        return fragmentOffset;
    }

    public void setFragmentOffset(int fragmentOffset) {
        this.fragmentOffset = fragmentOffset;
    }

    public int getTTL() {
        return TTL;
    }

    public void setTTL(int TTL) {
        this.TTL = TTL;
    }

    public int getProtocol() {
        return protocol;
    }

    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }

    public int getHeaderChecksum() {
        return headerChecksum;
    }

    public void setHeaderChecksum(int headerChecksum) {
        this.headerChecksum = headerChecksum;
    }

    public IP getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(IP sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public IP getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(IP destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "IPProtocol{" +
                "version=" + version + ", " +
                "IHL=" + IHL + ", " +
                "DSCP=" + DSCP + ", " +
                "TL=" + TL + ", " +
                "identification=" + identification + ", " +
                "flags=" + flags + ", " +
                "fragmentOffset=" + fragmentOffset + ", " +
                "TTL=" + TTL + ", " +
                "protocol=" + protocol + ", " +
                "headerChecksum=" + headerChecksum + ", " +
                "sourceAddress=" + sourceAddress + ", " +
                "destinationAddress=" + destinationAddress + ", " +
                "data=" + data + '}';
    }
}
