import classes.data.DataManager;
import classes.devices.Device;
import classes.devices.Switch;
import classes.exceptions.InvalidArgumentException;
import classes.logger.Logger;
import enums.Protocols;
import screens.MainScreen;
import screens.logs.LogScreen;
import screens.select.SelectDeviceScreen;
import test_tools.TestTools;

public class Tests {
    public Tests() throws InvalidArgumentException {
        TestTools testTools = new TestTools();

        new MainScreen();
    }
}
