package classes.data;

import abstracts.AbsDevice;
import cache.DevicesCache;

import java.io.*;
import java.util.Map;

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
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));

            oos.writeObject(devicesCache.getCache());

            oos.close();
        } catch (IOException e) {
            System.out.println(e);
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
                devicesCache.clear();
                return;
            }
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            Map<String, AbsDevice> devices = (Map<String, AbsDevice>) ois.readObject();
            devicesCache.load(devices);
            ois.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
