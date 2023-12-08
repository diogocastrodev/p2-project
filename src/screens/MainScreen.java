package screens;

import cache.Cache;
import classes.devices.Device;
import enums.Connection;

public class MainScreen {
    public MainScreen() {
        System.out.println("MainScreen");
        Utils.clearScreen();
        System.out.println(new Device("192.132.123.212", "00-00-00-00-00-00", "diogo-pc", Connection.ETHERNET));
    }
}
