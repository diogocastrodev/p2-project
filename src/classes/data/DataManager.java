package classes.data;

import abstracts.AbsDevice;
import cache.DevicesCache;
import classes.addresses.IP;
import classes.addresses.Mac;
import classes.devices.Device;
import enums.Connection;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DataManager {
    private final String folderPath = "data/";
    private final String fileName = "data.dat";

    public DataManager() {
        this.createFolder();
    }

    private void createFolder() {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    public void saveDevices() {
        this.createFolder();
        DevicesCache devicesCache = new DevicesCache();
        try {
            File file = new File(folderPath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            PrintWriter printWriter = new PrintWriter(file);
            for (AbsDevice device : devicesCache.getCache().values()) {
                printWriter.println(device.toString());
            }
            printWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadDevices() {
        this.createFolder();
        DevicesCache devicesCache = new DevicesCache();
        try {
            File file = new File(folderPath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            devicesCache.clear();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String type = line.split("\\{")[0];
                String ip = this.extract(line, "ip");
                String mac = this.extract(line, "mac");
                switch (type) {
                    case "Device":
                        String name = this.extract(line, "name");
                        String connection = this.extract(line, "connection").toUpperCase();
                        new Device(new IP(ip), new Mac(mac), name, Connection.valueOf(connection));
                        break;
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String extract(String line, String key) {
        return line.split(key + "=")[1].split("'")[1].split("'")[0];
    }
}
