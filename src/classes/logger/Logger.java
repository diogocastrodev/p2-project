package classes.logger;

import classes.addresses.IP;
import classes.addresses.Mac;
import classes.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
    public static final List<String> logs = new ArrayList<>();

    /**
     * Constructor
     */
    public Logger() {}

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






}
