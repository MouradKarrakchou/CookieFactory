package fr.unice.polytech.cod;

import java.util.logging.Logger;

public class Display {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    public final static Logger LOGGER = Logger.getLogger("");

    public Display() {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");
    }

    public static void itemInfo(Item item) {
        LOGGER.info(ANSI_BLUE + "[" + item.getQuantity() + "] " + item.getCookie().getCookieName() + ANSI_RESET);
    }

}
