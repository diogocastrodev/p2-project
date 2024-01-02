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
