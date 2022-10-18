package fr.unice.polytech.cod;

import java.util.List;
import java.util.logging.Logger;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;


public class Display {
    private final static Logger LOGGER = Logger.getLogger(Display.class.getName());

    public static void start(){
        System.setProperty("java.util.logging.SimpleFormatter.format", colorize("%5$s %n", BRIGHT_WHITE_TEXT()));
        LOGGER.info(colorize("Welcome to cookie on demand!", YELLOW_TEXT(), MAGENTA_BACK()));
    }

    public static void itemInfo(Item item) {
        String quantity = item.getQuantity() + "x ";
        String cookieName = item.getCookie().getName();
        LOGGER.info(" - " + colorize(quantity, BLUE_TEXT()) + colorize(cookieName, BRIGHT_WHITE_TEXT()));
    }

    public static void displayCookies(List<Cookie> availableCookie) {
        LOGGER.info(colorize("Here is the catalog:", BRIGHT_BLUE_TEXT()));
        StringBuilder cookieNames = new StringBuilder();
        for(Cookie cookie : availableCookie){
            cookieNames.append("    - ").append(cookie.getName()).append("\n");
        }
        LOGGER.info(cookieNames.toString());
    }
}
