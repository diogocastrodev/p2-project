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
        Device d1 = new Device(testTools.generateIP(192,168,1), testTools.generateMac());
        Device d2 = new Device(testTools.generateIP(192,168,1), testTools.generateMac());

        Device d3 = new Device(testTools.generateIP(192,168,1), testTools.generateMac());
        Device d4 = new Device(testTools.generateIP(192,168,1), testTools.generateMac());
        Device d5 = new Device(testTools.generateIP(192,168,1), testTools.generateMac());

        Switch s1 = new Switch(testTools.generateMac(), testTools.generateIP(192,168,1), 5);


        d1.setConnectedDevice(d2);

        s1.setPort(1, d3);
        s1.setPort(2, d4);
        s1.setPort(3, d5);


        new MainScreen();

        new DataManager().saveDevices();
    }
}
