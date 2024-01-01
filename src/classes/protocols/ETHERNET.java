package classes.protocols;

import classes.addresses.Mac;

public class ETHERNET {
    private String preamble = "10101010";
    private String sfd = "10101011";
    private Mac destinationMac;
    private Mac sourceMac;
    private String type;
    private Object data;
    private String fcs = "0x00000000";
}
