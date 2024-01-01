package classes.devices;

import classes.addresses.IP;
import classes.addresses.Mac;
import classes.exceptions.InvalidArgumentException;
import classes.protocols.DHCP;

// Router'll be considered a switch but with different way to process
public class Router extends Switch {

    private String ssid;
    private String password;

    private IP gateway;
    private IP subnetMask;
    private IP dns1;
    private IP dns2;

    private String username;
    private String passwordAdmin;

    private DHCP dhcp;

    public Router(Mac mac, int portCount) throws InvalidArgumentException  {
        super(mac, portCount);
    }
}
