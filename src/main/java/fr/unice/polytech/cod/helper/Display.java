package fr.unice.polytech.cod.helper;

import fr.unice.polytech.cod.food.Item;
import fr.unice.polytech.cod.food.Cookie;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;


public class Display {
    private static final Logger LOGGER = Logger.getLogger(Display.class.getName());

    public static void start(){
        System.setProperty("java.util.logging.SimpleFormatter.format", colorize("%5$s %n", BRIGHT_WHITE_TEXT()));
        LOGGER.info(colorize("Welcome to cookie on demand!", YELLOW_TEXT(), MAGENTA_BACK()));
        endLine();
    }

    public static void displayCookies(List<Cookie> availableCookie) {
        title("Here is the catalog:");
        StringBuilder cookieNames = new StringBuilder();
        for(Cookie cookie : availableCookie){
            cookieNames.append("    - ").append(cookie.getName()).append("\n");
        }
        LOGGER.info(cookieNames.toString());
    }


    public static void title(String title){
        LOGGER.info(colorize(title, BRIGHT_BLUE_TEXT()));
    }

    public static void endLine(){
        LOGGER.info("");
    }

    public static void info(String string){
        LOGGER.info(string);
    }

    public static void showItems(Set<Item> items) {
        StringBuilder output = new StringBuilder();
        for(Item item : items){
            output.append("    - ")
                    .append(colorize("" + item.getQuantity() + "x ", BRIGHT_BLUE_TEXT()))
                    .append(colorize(item.getCookie().getName(), BRIGHT_WHITE_TEXT()));
        }
        LOGGER.info(output.toString());
    }

    public static void smsOk(String name, String content){
        LOGGER.info("SMS has been sent to " + name + ".\n Content: " + content);
    }

    public static void smsNok(String reason){
        LOGGER.info("Can not send sms to the user. Reason: " + reason);
    }

    public static void mailNotifier(String message) {
        LOGGER.info(message);
    }
}
