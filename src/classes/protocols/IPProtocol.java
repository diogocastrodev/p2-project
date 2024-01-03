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


    /**
     * Version
     * @return Version
     */
    public int getVersion() {
        return version;
    }

    /**
     * Version
     * @param version Version
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Internet Header Length
     * @return Internet Header Length
     */
    public int getIHL() {
        return IHL;
    }

    /**
     * Internet Header Length
     * @param IHL Internet Header Length
     */
    public void setIHL(int IHL) {
        this.IHL = IHL;
    }

    /**
     * Differentiated Services Code Point
     * @return Differentiated Services Code Point
     */
    public int getDSCP() {
        return DSCP;
    }

    /**
     * Differentiated Services Code Point
     * @param DSCP Differentiated Services Code Point
     */
    public void setDSCP(int DSCP) {
        this.DSCP = DSCP;
    }

    /**
     * TL
     * @return TL
     */
    public int getTL() {
        return TL;
    }

    /**
     * TL
     * @param TL TL
     */
    public void setTL(int TL) {
        this.TL = TL;
    }

    /**
     * Identification
     * @return Identification
     */
    public int getIdentification() {
        return identification;
    }

    /**
     * Identification
     * @param identification Identification
     */
    public void setIdentification(int identification) {
        this.identification = identification;
    }

    /**
     * Flags
     * @return Flags
     */
    public int getFlags() {
        return flags;
    }

    /**
     * Flags
     * @param flags Flags
     */
    public void setFlags(int flags) {
        this.flags = flags;
    }

    /**
     * Fragment Offset
     * @return Fragment Offset
     */
    public int getFragmentOffset() {
        return fragmentOffset;
    }

    /**
     * Fragment Offset
     * @param fragmentOffset Fragment Offset
     */
    public void setFragmentOffset(int fragmentOffset) {
        this.fragmentOffset = fragmentOffset;
    }

    /**
     * Time to Live
     * @return Time to Live
     */
    public int getTTL() {
        return TTL;
    }

    /**
     * Time to Live
     * @param TTL Time to Live
     */
    public void setTTL(int TTL) {
        this.TTL = TTL;
    }

    /**
     * Protocol
     * @return Protocol
     */
    public int getProtocol() {
        return protocol;
    }

    /**
     * Protocol
     * @param protocol Protocol
     */
    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }

    /**
     * Header Checksum
     * @return Header Checksum
     */
    public int getHeaderChecksum() {
        return headerChecksum;
    }

    /**
     * Header Checksum
     * @param headerChecksum Header Checksum
     */
    public void setHeaderChecksum(int headerChecksum) {
        this.headerChecksum = headerChecksum;
    }

    /**
     * Source Address
     * @return Source Address
     */
    public IP getSourceAddress() {
        return sourceAddress;
    }

    /**
     * Source Address
     * @param sourceAddress Source Address
     */
    public void setSourceAddress(IP sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    /**
     * Destination Address
     * @return Destination Address
     */
    public IP getDestinationAddress() {
        return destinationAddress;
    }

    /**
     * Destination Address
     * @param destinationAddress Destination Address
     */
    public void setDestinationAddress(IP destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    /**
     * Data
     * @return Data
     */
    public Object getData() {
        return data;
    }

    /**
     * Data
     * @param data Data
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * String representation of the object
     * @return String representation of the object
     */
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
