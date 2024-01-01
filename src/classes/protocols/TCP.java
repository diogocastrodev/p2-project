package classes.protocols;

import classes.exceptions.InvalidArgumentException;
import classes.protocols.tcp.ChecksumTCP;
import classes.protocols.tcp.TCPHeadersFlags;

/**
 *
 */
public class TCP {
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
}
