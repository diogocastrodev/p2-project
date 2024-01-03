package classes.protocols;

import classes.exceptions.InvalidArgumentException;
import classes.protocols.tcp.ChecksumTCP;
import classes.protocols.tcp.TCPHeadersFlags;

/**
 *
 */
public class TCP extends IPProtocol implements Protocol {
    private int sourcePort;
    private int destinationPort;
    private int sequenceNumber;
    private int acknowledgementNumber;
    private int dataOffset;
    private int reserved;
    private TCPHeadersFlags flags;
    private int window; // Check for positives only
    private ChecksumTCP checksum;
    private int urgentPointer;
    private int options;

    private Object data;

    public TCP(int sourcePort, int destinationPort, int sequenceNumber, int acknowledgementNumber, int dataOffset, int reserved, TCPHeadersFlags flags, int window, ChecksumTCP checksum, int urgentPointer, int options, Object data) throws InvalidArgumentException {
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

    public TCP(int sourcePort, int destinationPort, int sequenceNumber, int acknowledgementNumber, int dataOffset, int reserved, TCPHeadersFlags flags, int window, ChecksumTCP checksum, int urgentPointer, Object data) throws InvalidArgumentException {
        this(sourcePort, destinationPort, sequenceNumber, acknowledgementNumber, dataOffset, reserved, flags, window, checksum, urgentPointer, 0, data);
    }

    private int checkForPositives (int value) throws InvalidArgumentException {
        if (value < 0) {
            throw new InvalidArgumentException("Valor negativo não permitido", String.valueOf(value));
        }
        return value;
    }

    public int getSourcePort() {
        return sourcePort;
    }

    public void setSourcePort(int sourcePort) {
        this.sourcePort = sourcePort;
    }

    public int getDestinationPort() {
        return destinationPort;
    }

    public void setDestinationPort(int destinationPort) {
        this.destinationPort = destinationPort;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public int getAcknowledgementNumber() {
        return acknowledgementNumber;
    }

    public void setAcknowledgementNumber(int acknowledgementNumber) {
        this.acknowledgementNumber = acknowledgementNumber;
    }

    public int getDataOffset() {
        return dataOffset;
    }

    public void setDataOffset(int dataOffset) {
        this.dataOffset = dataOffset;
    }

    public int getReserved() {
        return reserved;
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
    }

    public void setFlags(TCPHeadersFlags flags) {
        this.flags = flags;
    }

    public int getWindow() {
        return window;
    }

    public void setWindow(int window) {
        this.window = window;
    }

    public ChecksumTCP getChecksum() {
        return checksum;
    }

    public void setChecksum(ChecksumTCP checksum) {
        this.checksum = checksum;
    }

    public int getUrgentPointer() {
        return urgentPointer;
    }

    public void setUrgentPointer(int urgentPointer) {
        this.urgentPointer = urgentPointer;
    }

    public int getOptions() {
        return options;
    }

    public void setOptions(int options) {
        this.options = options;
    }

    @Override
    public Object getData() {
        return data;
    }

    @Override
    public void setData(Object data) {
        this.data = data;
    }
}
