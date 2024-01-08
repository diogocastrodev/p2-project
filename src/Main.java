import cache.DevicesCache;
import cache.MacCache;
import classes.addresses.IP;
import classes.addresses.SubnetMask;
import classes.data.DataManager;
import classes.exceptions.InvalidArgumentException;
import screens.MainScreen;

public class Main {
    public static void main(String[] args) throws InvalidArgumentException {
        // Debug
        final boolean testMode = true;

        // Iniciar o cache
        new DevicesCache();
        new MacCache();
        // Carregar ao iniciar o programa
        new DataManager().loadDevices();
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
        // Guardar ao fechar o programa
        new DataManager().saveDevices();
    }
}