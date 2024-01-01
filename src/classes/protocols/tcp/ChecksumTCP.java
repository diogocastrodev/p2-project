package classes.protocols.tcp;

import classes.addresses.IP;

public class ChecksumTCP {
    /**
     * Source IP Address
     */
    private IP sourceAddress;
    /**
     * Destination IP Address
     */
    private IP destinationAddress;
    /**
     * Zero -> bits set to zero
     */
    private int zero;
    /**
     * PTCL -> Nº Protocolo do IP Header
     */
    private int PTCL;
    /**
     * TCP Header Length -> TCP header length + data length in octets | it does not count the 12 octets of the pseudo-header.
     */
    private int TCPHeaderLength;

    /**
     * ChecksumTCP Constructor
     * @param sourceAddress Source IP Address
     * @param destinationAddress Destination IP Address
     * @param zero Zero
     * @param PTCL PTCL
     * @param TCPHeaderLength TCP Header Length
     */
    public ChecksumTCP(IP sourceAddress, IP destinationAddress, int zero, int PTCL, int TCPHeaderLength) {
        this.sourceAddress = sourceAddress;
        this.destinationAddress = destinationAddress;
        this.zero = zero;
        this.PTCL = PTCL;
        this.TCPHeaderLength = TCPHeaderLength;
    }


}
