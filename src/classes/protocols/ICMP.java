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
    private int code = 0x0;
    /**
     * Checksum
     */
    private String checksum = "";
    /**
     * Identifier
     */
    private int identifier = 0x0002;
    /**
     * Sequence Number
     */
    private int sequenceNumber;

    /**
     * Constructor
     * @param type Type of ICMP
     * @param sequenceNumber Sequence Number
     */
    public ICMP(int type, int sequenceNumber) {
        this.type = type;
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * Type
     * @return Type
     */
    public int getType() {
        return type;
    }

    /**
     * Type
     * @param type Type
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Code
     * @return Code
     */
    public int getCode() {
        return code;
    }

    /**
     * Code
     * @param code Code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Checksum
     * @return Checksum
     */
    public String getChecksum() {
        return checksum;
    }

    /**
     * Checksum
     * @param checksum Checksum
     */
    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    /**
     * Identifier
     * @return Identifier
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * Identifier
     * @param identifier Identifier
     */
    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    /**
     * Sequence Number
     * @return Sequence Number
     */
    public int getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Sequence Number
     * @param sequenceNumber Sequence Number
     */
    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * Verify Checksum
     * @param checksum Checksum
     * @return Whether the checksum is valid
     */
    public boolean verifyChecksum(String checksum) {
        return this.checksum.equals(checksum);
    }

    /**
     * String representation of the object
     * @return String representation of the object
     */
    @Override
    public String toString() {
        return "ICMP{" +
                "type=" + type +
                ", code=" + code +
                ", checksum='" + checksum + '\'' +
                ", identifier=" + identifier +
                ", sequenceNumber=" + sequenceNumber +
                '}';
    }
}
