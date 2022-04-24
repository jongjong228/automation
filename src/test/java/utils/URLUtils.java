package utils;

public class URLUtils {
    public static final String posts = "posts";
    public static final String post99 = "posts/99";
    public static final String users = "users";
    public static final String user5 = "users/5";
    public static final String post150 = "posts/150";

    public static String createURL(String str) {
        return JsonUtils.getValue(JsonKeys.baseUrl) + "/" + str;
    }

}
