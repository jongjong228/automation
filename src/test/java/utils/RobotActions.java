package utils;

import aquality.selenium.browser.AqualityServices;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class RobotActions {
    public static void uploadFile(String pathToFile) {
        try {
            Robot rb = new Robot();
            // copying File path to Clipboard
            StringSelection str = new StringSelection(pathToFile);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
            // press Contol+V for pasting
            rb.keyPress(KeyEvent.VK_CONTROL);
            rb.keyPress(KeyEvent.VK_V);
            // release Contol+V for pasting
            rb.keyRelease(KeyEvent.VK_CONTROL);
            rb.keyRelease(KeyEvent.VK_V);
            // for pressing and releasing Enter
            rb.keyPress(KeyEvent.VK_ENTER);
            rb.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            AqualityServices.getLogger().warn("AWTException");
        }
    }
}
