package utils;

import aquality.selenium.browser.Browser;

public class BrowserUtils {
    public static void scrollToTop(Browser browser) {
        browser.executeScript("window.scrollTo(0, 0)");
    }
}
