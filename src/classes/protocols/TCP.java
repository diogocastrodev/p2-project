package classes.protocols;

import classes.addresses.IP;
import classes.exceptions.InvalidArgumentException;
import classes.protocols.tcp.ChecksumTCP;
import classes.protocols.tcp.TCPHeadersFlags;

/**
 *
 */
public class TCP extends IPProtocol implements Protocol {
    /**
     * Source port
     */
    private int sourcePort;
    /**
     * Destination port
     */
    private int destinationPort;
    /**
     * Sequence number
     */
    private int sequenceNumber;
    /**
     * Acknowledgement number
     */
    private int acknowledgementNumber;
    /**
     * Data offset
     */
    private int dataOffset = 5;
    /**
     * Reserved
     */
    private int reserved = 0;
    /**
     * Flags
     */
    private TCPHeadersFlags flags = new TCPHeadersFlags(0,0,0,0,0,0,0,0);
    /**
     * Window
     */
    private int window; // Check for positives only
    /**
     * Checksum
     */
    private ChecksumTCP checksum;
    /**
     * Urgent pointer
     */
    private int urgentPointer = 0;
    /**
     * Options
     */
    private int options = 0;
    /**
     * Data
     */
    private Object data;

    public TCP (IP sourceAddress, IP destinantionAddress, int sourcePort, int destinationPort, int sequenceNumber, int window, Object data) throws InvalidArgumentException {
        super(6, sourceAddress, destinantionAddress, data);
        this.setSourcePort(sourcePort);
        this.setDestinationPort(destinationPort);
        // SYN
        this.setSequenceNumber(sequenceNumber);
        // ACK = SYN + 1
        this.setAcknowledgementNumber(sequenceNumber + 1);
        this.setWindow(window);
        this.setTCPData(data);
        this.setChecksum(new ChecksumTCP(super.getSourceAddress(), super.getDestinationAddress(), 0, 0, 0));
    }

    /**
     * Only positive values are allowed
     * @param value Value to be checked
     * @return Value if it is positive
     * @throws InvalidArgumentException
     */
    private int checkForPositives (int value) throws InvalidArgumentException {
        if (value < 0) {
            throw new InvalidArgumentException("Valor negativo não permitido", String.valueOf(value));
        }
        return value;
    }

    /**
     * Get Source port
     * @return
     */
    public int getSourcePort() {
        return sourcePort;
    }

    /**
     * Set Source port
     * @param sourcePort
     */
    public void setSourcePort(int sourcePort) {
        this.sourcePort = sourcePort;
    }

    /**
     * Get Destination port
     * @return
     */
    public int getDestinationPort() {
        return destinationPort;
    }

    /**
     * Set Destination port
     * @param destinationPort
     */
    public void setDestinationPort(int destinationPort) {
        this.destinationPort = destinationPort;
    }

    /**
     * Get Sequence number
     * @return
     */
    public int getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Set Sequence number
     * @param sequenceNumber
     */
    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * Get Acknowledgement number
     * @return
     */
    public int getAcknowledgementNumber() {
        return acknowledgementNumber;
    }

    /**
     * Set Acknowledgement number
     * @param acknowledgementNumber
     */
    public void setAcknowledgementNumber(int acknowledgementNumber) {
        this.acknowledgementNumber = acknowledgementNumber;
    }

    /**
     * Get Data offset
     * @return
     */
    public int getDataOffset() {
        return dataOffset;
    }

    /**
     * Set Data offset
     * @param dataOffset
     */
    public void setDataOffset(int dataOffset) {
        this.dataOffset = dataOffset;
    }

    /**
     * Get Reserved
     * @return
     */
    public int getReserved() {
        return reserved;
    }

    /**
     * Set Reserved
     * @return
     */
    public void setReserved(int reserved) {
        this.reserved = reserved;
    }

    /**
     * Set Flags
     * @return
     */
    public void setFlags(TCPHeadersFlags flags) {
        this.flags = flags;
    }

    /**
     * Get Flags
     * @return
     */
    public TCPHeadersFlags getTCPFlags() {
        return flags;
    }

    /**
     * Get Window
     * @return
     */
    public int getWindow() {
        return window;
    }

    /**
     * Set Window
     * @param window
     */
    public void setWindow(int window) {
        this.window = window;
    }

    /**
     * Get Checksum
     * @return ChecksumTCP
     */
    public ChecksumTCP getChecksum() {
        return checksum;
    }

    /**
     * Set Checksum
     * @param checksum
     */
    public void setChecksum(ChecksumTCP checksum) {
        this.checksum = checksum;
    }

    /**
     * Get Urgent pointer
     * @return
     */
    public int getUrgentPointer() {
        return urgentPointer;
    }

    /**
     * Set Urgent pointer
     * @param urgentPointer
     */
    public void setUrgentPointer(int urgentPointer) {
        this.urgentPointer = urgentPointer;
    }

    /**
     * Get Options
     * @return
     */
    public int getOptions() {
        return options;
    }

    /**
     * Set Options
     * @param options
     */
    public void setOptions(int options) {
        this.options = options;
    }

    /**
     * Get Data
     * @return
     */
    public Object getTCPData() {
        return data;
    }

    /**
     * Set Data
     * @param data
     */
    public void setTCPData(Object data) {
        this.data = data;
    }

    /**
     * String representation of the object
     * @return String representation of the object
     */
    @Override
    public String toString() {
        return "TCP{" +
                "sourcePort=" + sourcePort + ", " +
                "destinationPort=" + destinationPort + ", " +
                "sequenceNumber=" + sequenceNumber + ", " +
                "acknowledgementNumber=" + acknowledgementNumber + ", " +
                "dataOffset=" + dataOffset + ", " +
                "reserved=" + reserved + ", " +
                "flags=" + flags + ", " +
                "window=" + window + ", " +
                "checksum=" + checksum + ", " +
                "urgentPointer=" + urgentPointer + ", " +
                "options=" + options + ", " +
                "data=" + data +
                "}";
    }
}
