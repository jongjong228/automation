package myutils;

public class RefactorStrings {
    public static String getFormattedRecord(String ftName,String sdName,String email,String age,String salary, String department){
        return (ftName + "\n" + sdName + "\n" + age + "\n" + email + "\n" + salary + "\n" + department);
    }
    public static String createXpath(String sample,String position){
        return (sample + position + "']");
    }
}
