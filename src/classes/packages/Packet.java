package classes.packages;

import classes.protocols.*;
import enums.Protocols;

public class Packet {
    private Protocol protocol;
    private Protocols protocolType;

    public Packet(Protocol protocol, Protocols protocolType) {
        this.setProtocol(protocol);
        this.setProtocolType(protocolType);
    }

    public Protocol getProtocol() {
        return protocol;
    }

    private void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public Protocols getProtocolType() {
        return protocolType;
    }

    private void setProtocolType(Protocols protocolType) {
        this.protocolType = protocolType;
    }

    public ARP getARP() {
        if (this.getProtocolType() != Protocols.ARP) {
            return null;
        }
        return (ARP) this.getProtocol();
    }

    public DHCP getDHCP() {
        if (this.getProtocolType() != Protocols.DHCP) {
            return null;
        }
        return (DHCP) this.getProtocol();
    }

    public ICMP getICMP() {
        if (this.getProtocolType() != Protocols.ICMP) {
            return null;
        }
        return (ICMP) this.getProtocol();
    }

    public TCP getTCP() {
        if (this.getProtocolType() != Protocols.TCP) {
            return null;
        }
        return (TCP) this.getProtocol();
    }
}
