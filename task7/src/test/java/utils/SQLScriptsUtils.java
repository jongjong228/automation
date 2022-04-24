package utils;

import keys.SQLKeys;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SQLScriptsUtils {
    private static final String DATE_FORMAT = JsonUtils.getString(SQLKeys.DATE_FORMAT, JsonUtils.CONFIG_JSON);

    public static String formatString(String str) {
        return "'" + str + "'";
    }

    public static String getValues(ArrayList<String> params) {
        StringBuilder stringBuilder = new StringBuilder("(");
        for (String param : params) {
            stringBuilder.append(param).append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public static String formatDate(Date date) {
        SimpleDateFormat formatForDateNow = new SimpleDateFormat(DATE_FORMAT);
        return formatForDateNow.format(date);
    }
}
