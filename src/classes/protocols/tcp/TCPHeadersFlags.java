package classes.protocols.tcp;

public class TCPHeadersFlags {
    /**
     * CWR -> Congestion Window Reduced
     */
    private int cwr;
    /**
     * ECE -> ECN-Echo
     */
    private int ece;
    /**
     * URG -> Urgent
     */
    private int urg;
    /**
     * ACK -> Acknowledgement
     */
    private int ack;
    /**
     * PSH -> Push
     */
    private int psh;
    /**
     * RST -> Reset
     */
    private int rst;
    /**
     * SYN -> Synchronize
     */
    private int syn;
    /**
     * FIN -> Finish (Last Packet)
     */
    private int fin;

    public TCPHeadersFlags(int cwr, int ece, int urg, int ack, int psh, int rst, int syn, int fin) {
        this.cwr = cwr;
        this.ece = ece;
        this.urg = urg;
        this.ack = ack;
        this.psh = psh;
        this.rst = rst;
        this.syn = syn;
        this.fin = fin;
    }
}
