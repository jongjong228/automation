package utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.apache.commons.lang.RandomStringUtils;

import java.util.Random;

public class Randoms {
    private static final Random sRandom = new Random();
    private static final ISettingsFile  testDate = new JsonSettingsFile("test_date.json");
    private static String email;
    private static final String ruChar = "ячсмитьбюфывапролджэйцукенгшщзхъ";
    private static boolean arrayInterests[] = new boolean[getValue("/numOfInterests")];
    public static String getRandomPassword(){
        String password = RandomStringUtils.randomAlphabetic((int) (Math.random() * getValue("/numOfDopCharacters") + getValue("/minNumOfCharacters"))).toLowerCase();
        StringBuilder str = new StringBuilder(password);
        str.append(RandomStringUtils.randomAlphabetic(getValue("/numOfUppersEmail")).toUpperCase());
        str.append(getRandomRuString());
        str.append(email.charAt((int)(Math.random() * email.length())));
        str.append((int)(Math.random() * getValue("/numOfNumbers")));
        return str.toString();
    }
    public static String getRandomEmail(){
        email = RandomStringUtils.randomAlphabetic((int)(Math.random() * getValue("/lengthOfEmail")  + 1)).toLowerCase();
        return email;
    }
    public static String getRandomDomain(){
        return RandomStringUtils.randomAlphabetic((int)(Math.random() * getValue("/lengthOfDomain") + 2)).toLowerCase();
    }
    public static int getRandomTLDomainIndex(){
        return ((int)(Math.random() * getValue("/numOfTLDomains") + 1));
    }
    public static int getRandomInterest(){
        int index;
        while (true) {
            index = (int) (Math.random() * (getValue("/numOfInterests") - 1));
            if (!arrayInterests[index] && index != getValue("/selectAllIndex")) {
                arrayInterests[index] = true;
                return index;
            }
        }
    }
    private static int getValue(String name){
        return Integer.parseInt(testDate.getValue(name).toString());
    }
    private static String getRandomRuString(){
        int num = (int)(Math.random() * getValue("/numOfRuCharacters") + 1);
        String str = "";
        StringBuilder strBuild = new StringBuilder(str);
        for(int i = 0;i < num; i++)
            strBuild.append(sRandom.nextInt(ruChar.length()));
        return strBuild.toString();
    }
}
