package classes.devices;

import classes.addresses.IP;
import classes.addresses.Mac;
import classes.exceptions.InvalidArgumentException;

import java.io.Serializable;

// Router'll be considered a switch but with different way to process
public class Router extends Switch implements Serializable {
    public Router(Mac mac, IP ip, int portCount) throws InvalidArgumentException  {
        super(mac, ip, portCount);
    }

    @Override
    public String toString() {
        if (super.toString().contains("Switch")) {
            return super.toString().replace("Switch", "Router");
        }
        return super.toString().replace("AbsDeviceNetwork", "Router");
    }
}
