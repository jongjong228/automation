package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtils {
    protected static FileInputStream fileInputStreamConfig,fileInputStreamTest;
    protected static Properties ConfProperties,TestProperties;
    static {
        try {
            fileInputStreamConfig = new FileInputStream("src/main/framework/property/conf.properties");
            ConfProperties = new Properties();
            ConfProperties.load(fileInputStreamConfig);
        } catch (IOException e) {
            MyLogger.error("Config File is not opened");
            e.printStackTrace();
        } finally {
            if (fileInputStreamConfig != null)
                try {
                    fileInputStreamConfig.close();
                } catch (IOException e) {
                    MyLogger.error("Config File is not closed");
                    e.printStackTrace(); } }
        try {
            fileInputStreamTest = new FileInputStream("src/main/framework/property/test.properties");
            TestProperties = new Properties();
            TestProperties.load(fileInputStreamTest);
        } catch (IOException e) {
            MyLogger.error("Test File is not opened");
            e.printStackTrace();
        } finally {
            if (fileInputStreamTest != null)
                try {
                    fileInputStreamTest.close();
                } catch (IOException e) {
                    MyLogger.error("Test File is not closed");
                    e.printStackTrace(); } }
    }

    public static String getConfProperty(String key) {
        return ConfProperties.getProperty(key); }

    public static String getTestProperty(String key) {
        return TestProperties.getProperty(key); }

    public static String getChromeSizes(){
        return ("window-size=" + ConfigUtils.getConfProperty("Width") + "," + ConfigUtils.getConfProperty("Height"));
    }
    public static String getFirefoxWidth(){
        return (("-width") + "=" + ConfigUtils.getConfProperty("Width"));
    }
    public static String getFirefoxHeight(){
        return (("-height") + "=" + ConfigUtils.getConfProperty("Height"));
    }
}
