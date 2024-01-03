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
    private int dataOffset;
    /**
     * Reserved
     */
    private int reserved;
    /**
     * Flags
     */
    private TCPHeadersFlags flags;
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
    private int urgentPointer;
    /**
     * Options
     */
    private int options;
    /**
     * Data
     */
    private Object data;

    /**
     * Constructor
     * @param sourcePort
     * @param destinationPort
     * @param sequenceNumber
     * @param acknowledgementNumber
     * @param dataOffset
     * @param reserved
     * @param flags
     * @param window
     * @param checksum
     * @param urgentPointer
     * @param options
     * @param data
     * @throws InvalidArgumentException
     */
    public TCP(IP srcIP, IP distIP, int sourcePort, int destinationPort, int sequenceNumber, int acknowledgementNumber, int dataOffset, int reserved, TCPHeadersFlags flags, int window, ChecksumTCP checksum, int urgentPointer, int options, Object data) throws InvalidArgumentException {
        super(6, srcIP, distIP, data);
        this.sourcePort = checkForPositives(sourcePort);
        this.destinationPort = checkForPositives(destinationPort);
        this.sequenceNumber = sequenceNumber;
        this.acknowledgementNumber = acknowledgementNumber;
        this.dataOffset = dataOffset;
        this.reserved = reserved;
        this.flags = flags;
        this.window = window;
        this.checksum = checksum;
        this.urgentPointer = urgentPointer;
        this.options = options;
        this.data = data;
    }

    /**
     * Constructor
     * @param sourcePort
     * @param destinationPort
     * @param sequenceNumber
     * @param acknowledgementNumber
     * @param dataOffset
     * @param reserved
     * @param flags
     * @param window
     * @param checksum
     * @param urgentPointer
     * @param data
     * @throws InvalidArgumentException
     */
    public TCP(IP srcIP, IP distIP,int sourcePort, int destinationPort, int sequenceNumber, int acknowledgementNumber, int dataOffset, int reserved, TCPHeadersFlags flags, int window, ChecksumTCP checksum, int urgentPointer, Object data) throws InvalidArgumentException {
        this(srcIP, distIP, sourcePort, destinationPort, sequenceNumber, acknowledgementNumber, dataOffset, reserved, flags, window, checksum, urgentPointer, 0, data);
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
