package utils;

import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class MyLogger {
    private static Logger logger;
    static {
        try(FileInputStream ins = new FileInputStream("src/main/framework/property/log.properties")){
            LogManager.getLogManager().readConfiguration(ins);
            logger = Logger.getGlobal();
        }catch (Exception ignore){
            error("Logger init error");
            ignore.printStackTrace();
        }
    }
    public static void info(String str){
        logger.info(str);
    }
    public static void error(String str){
        logger.log(Level.SEVERE,str);
    }

}
