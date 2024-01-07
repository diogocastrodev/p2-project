package classes.devices;

import classes.addresses.IP;
import classes.addresses.Mac;
import classes.exceptions.InvalidArgumentException;
import classes.dhcp.DHCPDist;

// Router'll be considered a switch but with different way to process
public class Router extends Switch {
    public Router(Mac mac, IP ip, int portCount) throws InvalidArgumentException  {
        super(mac, ip, portCount);
    }
}
