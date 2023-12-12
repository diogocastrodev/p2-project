package screens;

import cache.Cache;
import classes.devices.Device;
import enums.Connection;
import test_tools.TestTools;

public class MainScreen {
    public MainScreen() {
        System.out.println("MainScreen");
        Utils.clearScreen();
        try {
            System.out.println(new Device(new TestTools().generateIP(), new TestTools().generateMac(), "diogo-pc", Connection.ETHERNET));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
