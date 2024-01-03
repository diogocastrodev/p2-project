import classes.addresses.IP;
import classes.addresses.SubnetMask;
import classes.exceptions.InvalidArgumentException;
import screens.MainScreen;

public class Main {
    public static void main(String[] args) throws InvalidArgumentException {


        SubnetMask subnetMask = new SubnetMask(new IP("255.255.255.0"));
        IP ip = new IP("192.168.4.27");
        IP ip2 = new IP("192.168.2.59");
        System.out.println(subnetMask.generateRandomIPInsideMask(ip, ip2));
        System.out.println(subnetMask.generateRandomIPInsideMask(ip, ip2));

        System.out.println(subnetMask.generateRandomIPInsideMask(ip, ip2));

        System.out.println(subnetMask.generateRandomIPInsideMask(ip, ip2));

        System.out.println(subnetMask.generateRandomIPInsideMask(ip, ip2));

        System.out.println(subnetMask.generateRandomIPInsideMask(ip, ip2));



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