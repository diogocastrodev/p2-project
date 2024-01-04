import classes.addresses.IP;
import classes.addresses.SubnetMask;
import classes.exceptions.InvalidArgumentException;
import screens.MainScreen;

public class Main {
    public static void main(String[] args) throws InvalidArgumentException {
        final boolean testMode = true;
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