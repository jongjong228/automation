package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;

public class LoggerUtils {
    private final static Logger logger = AqualityServices.getLogger();

    public static Logger getLogger() {
        return logger;
    }
}
