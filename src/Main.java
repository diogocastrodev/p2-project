import abstracts.AbsDeviceEnd;
import abstracts.AbsDeviceNetwork;
import cache.MacCache;
import classes.addresses.IP;
import classes.addresses.Mac;
import classes.devices.Device;
import classes.devices.Switch;
import classes.exceptions.InvalidArgumentException;
import screens.MainScreen;
import test_tools.TestTools;

public class Main {
    public static void main(String[] args) throws InvalidArgumentException {
        try
        {
            new MainScreen();
        }
        catch (Exception e)
        {
            // System.out.println(e.getMessage());
        }
    }
}