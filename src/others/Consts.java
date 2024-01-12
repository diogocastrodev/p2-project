package others;

import classes.addresses.Mac;
import classes.exceptions.InvalidArgumentException;

public class Consts {
    public static final String LOGGER_FOLDER = "logs/";
    public static final Mac BROADCAST_MAC;

    public static final boolean allowDHCP = false;

    static {
        try {
            BROADCAST_MAC = new Mac("FF:FF:FF:FF:FF:FF");
        } catch (InvalidArgumentException e) {
            throw new RuntimeException(e);
        }
    }
}
