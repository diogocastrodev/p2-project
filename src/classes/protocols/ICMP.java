package classes.protocols;

public class ICMP extends IPProtocol implements Protocol {
    /**
     * Type
     * 0 for Echo Reply
     * 3 for Destination Unreachable
     * 8 for Echo Request
     */
    private int type;
    /**
     * Code
     */
    private int code = 0;
    /**
     * Checksum
     */
    private String checksum;
    /**
     * Identifier
     */
    private int identifier;
    /**
     * Sequence Number
     */
    private int sequenceNumber;

    public ICMP(int type, String checksum, int identifier, int sequenceNumber) {
        this.type = type;
        this.checksum = checksum;
        this.identifier = identifier;
        this.sequenceNumber = sequenceNumber;
    }
}
