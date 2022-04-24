package functions;

public class Functions {
    public static String getPriseFromField(String str){
        return (str.substring(str.indexOf('\n') +1) + " USD");
    }
    public static String replaceSymbols(String str){
        return str.replaceAll("\\D+","");
    }
    private static String getValueFromField(String str){
        return str.substring(str.indexOf('\n') + 1);
    }
    public static  Boolean compareValue(String first, String second){
        first = getValueFromField(first);
        second = getValueFromField(second);
        Boolean isLonger = true;
        int i = 0;
        if (second.length() <= first.length())
            while (i <= first.length()){
                if (second.charAt(i) < first.charAt(i))
                {
                    isLonger = false;
                    break;
                }
                else if (second.charAt(i) > first.charAt(i))
                    break;
                i++;

            }

        return isLonger;
    }
}
