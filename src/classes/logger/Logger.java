package classes.logger;

import classes.addresses.IP;
import classes.addresses.Mac;
import classes.exceptions.InvalidArgumentException;
import enums.Protocols;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * This is a class for logging
 * all the routes the Packet
 * has taken.
 */
public class Logger {
    /**
     * Folder path
     */
    private final String folderPath = "logs/";
    /**
     * Logs array
     */
    private static final List<String> logs = new ArrayList<>();

    /**
     * Last log created
     */
    private static String lastLog = "";

    /**
     * Constructor
     */
    public Logger() {
        if (lastLog.equals("") || lastLog.equals(null)) {
            // Search for the last log file in the folder
            File folder = new File(folderPath);
            if (!folder.exists()) {
                // Create folder if it doesn't exist
                folder.mkdirs();
            }
            File[] listOfFiles = folder.listFiles();
            if (listOfFiles.length == 0 || listOfFiles == null) {
                // No log files
                return;
            }
            File lastModifiedFile = listOfFiles[0];
            for (int i = 1; i < listOfFiles.length; i++) {
                if (lastModifiedFile.lastModified() < listOfFiles[i].lastModified()) {
                    lastModifiedFile = listOfFiles[i];
                }
            }
            this.setLastLog(lastModifiedFile.getAbsolutePath());
        }
    }

    /**
     * Get the last log file
     * @return
     */
    public String getLastLog() {
        return lastLog;
    }

    /**
     * Set the last log file
     * @param lastLog Last log file
     */
    private void setLastLog(String lastLog) {
        Logger.lastLog = lastLog;
    }

    /**
     * Add a log to the logs array
     * @param protocol Protocol name
     * @param message Message
     */
    public void addLog(Protocols protocol, String message)  {
        // [YYYY-MM-DD HH:MM:SS.MMM] [Protocol] [Message]
        Date date = new Date(); // This object contains the current date value
        Calendar calendar = Calendar.getInstance(); // Create a calendar object with the current time
        calendar.setTime(date); // Set calendar to date
        // Create log
        String log = "["
                + calendar.get(Calendar.YEAR) + "-"
                + calendar.get(Calendar.MONTH) + 1 + "-"
                + calendar.get(Calendar.DAY_OF_MONTH) + " "
                + calendar.get(Calendar.HOUR_OF_DAY) + ":"
                + calendar.get(Calendar.MINUTE) + ":"
                + calendar.get(Calendar.SECOND) + "."
                + calendar.get(Calendar.MILLISECOND) + "] ";

        log += "[" + protocol.toString() + "] ";

        log += message;

        logs.add(log); // Add log to logs array
    }
    /**
     * Add a log to the logs array
     * @param ip IP address
     *        (null if the IP address is unknown)
     * @param mac MAC address (Not null)
     * @param protocol Protocol name
     * @param message Message
     */
    public void addLog(IP ip, Mac mac, String protocol, String message)  {
        if (mac == null) {
            try {
                mac = new Mac("00:00:00:00:00:00");
            } catch (InvalidArgumentException e) {
                // This will never happen
            }

        }
        // [YYYY-MM-DD HH:MM:SS.MMM] [IP] [MAC] [Protocol] [Message]
        Date date = new Date(); // This object contains the current date value
        Calendar calendar = Calendar.getInstance(); // Create a calendar object with the current time
        calendar.setTime(date); // Set calendar to date
        // Create log
        String log = "["
                + calendar.get(Calendar.YEAR) + "-"
                + calendar.get(Calendar.MONTH) + 1 + "-"
                + calendar.get(Calendar.DAY_OF_MONTH) + " "
                + calendar.get(Calendar.HOUR_OF_DAY) + ":"
                + calendar.get(Calendar.MINUTE) + ":"
                + calendar.get(Calendar.SECOND) + "."
                + calendar.get(Calendar.MILLISECOND) + "] ";

        if (ip != null) {
            log += "[" + ip + "] ";
        } else {
            log += "[ ---.---.---.--- ] ";
        }

        log += "[" + mac + "] ";

        log += "[" + protocol.toUpperCase() + "] ";

        log += message;

        logs.add(log); // Add log to logs array
    }

    /**
     * Reset the logs array
     */
    private void reset () {
        // Clear the Array
        logs.clear();
    }

    /**
     * Save the logs to a file
     */
    public void save () {
        new File(folderPath).mkdirs(); // Create folder if it doesn't exist
        try {
            Date date = new Date(); // This object contains the current date value
            Calendar calendar = Calendar.getInstance(); // Create a calendar object with the current time
            calendar.setTime(date); // Set calendar to date
            // Create file name (log_YYYY-MM-DD_HH_MM_SS_MMM.txt)
            String fileName = "log_"
                    + calendar.get(Calendar.YEAR) + "-"
                    + calendar.get(Calendar.MONTH) + 1 + "-"
                    + calendar.get(Calendar.DAY_OF_MONTH) + "_"
                    + calendar.get(Calendar.HOUR_OF_DAY) + "_"
                    + calendar.get(Calendar.MINUTE) + "_"
                    + calendar.get(Calendar.SECOND) + "_"
                    + calendar.get(Calendar.MILLISECOND);
            File file = new File(folderPath + fileName + ".txt"); // Create file
            this.setLastLog(file.getAbsolutePath());
            PrintWriter output = new PrintWriter(file); // Create file writer
            // Write logs to file
            for (String log : logs) { // For each log
                output.println(log);
            }
            output.close(); // Close file writer
        } catch (FileNotFoundException e) {
            e.printStackTrace(); // Print error
        }
        this.reset(); // Reset logs array
    }

    public void openLastLogFile() throws Exception {
        /**
         * Windows: start %s
         * Linux: xdg-open %s
         * Mac: open %s
         */
        String os = System.getProperty("os.name").toLowerCase();
        String command = "";
        if (os.contains("win")) {
            command = "start";
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            command = "xdg-open";
        } else if (os.contains("mac")) {
            command = "open";
        }

        if (command.equals("")) {
            throw new Exception("OS not supported");
        }

        Runtime p = Runtime.getRuntime();
        try {
            p.exec(command + " " + this.getLastLog());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void openLogFolder() {
        /**
         * Windows: start %s
         * Linux: xdg-open %s
         * Mac: open %s
         */
        String os = System.getProperty("os.name").toLowerCase();
        String command = "";
        if (os.contains("win")) {
            command = "start";
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            command = "xdg-open";
        } else if (os.contains("mac")) {
            command = "open";
        }

        if (command.equals("")) {
            return;
        }

        Runtime p = Runtime.getRuntime();
        try {
            p.exec(command + " " + this.folderPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getLastLogLines() {
        if (this.getLastLog() == null) {
            return null;
        }
        File file = new File(this.getLastLog());
        if (file.exists()) {
            // Read from file
            try {
                Scanner reader = new Scanner(file);
                List<String> lines = new ArrayList<>();
                while (reader.hasNextLine()) {
                    String data = reader.nextLine();
                    lines.add(data);
                }
                reader.close();
                return lines;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }




}
