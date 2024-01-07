import cache.DevicesCache;
import cache.MacCache;
import classes.addresses.IP;
import classes.addresses.SubnetMask;
import classes.exceptions.InvalidArgumentException;
import screens.MainScreen;

public class Main {
    public static void main(String[] args) throws InvalidArgumentException {
        final boolean testMode = true;

        new DevicesCache();
        new MacCache();
        try
        {
            if (testMode)
                new Tests();
            else
                new MainScreen();
        }
        catch (Exception e)
        {
            // System.out.println(e.getMessage());
        }
    }
}