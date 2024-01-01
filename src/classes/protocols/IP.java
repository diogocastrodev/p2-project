package classes.protocols;

public class IP {
    private int version = 4;
    private int IHL = 5;
    private int DSCP = 0;
    private int TL;
    private int identification;
    private int flags;
    private int fragmentOffset;
    private int TTL;
    private int protocol;
    private int headerChecksum;
    private IP sourceAddress;
    private IP destinationAddress;
    private Object data;
}
