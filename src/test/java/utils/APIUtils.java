package utils;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.Post;
import pojo.User;


import java.util.Arrays;
import java.util.List;

public class APIUtils {
    private static RequestSpecification httpRequest;
    private static Response response;

    public static void initGetRequest(String urlPath) {
        setURL(urlPath);
        setRequestSpecification();
    }

    private static void setURL(String url) {
        LoggerUtils.getLogger().info("set URL");
        RestAssured.baseURI = url;
    }

    private static void setRequestSpecification() {
        httpRequest = RestAssured.given();
    }


    public static void makeGetRequestCall(String param) {
        LoggerUtils.getLogger().info("init request");
        response = httpRequest.get(param);
    }

    public static int getStatusCode() {
        LoggerUtils.getLogger().info("get response status code");
        return response.getStatusCode();
    }

    public static String getResponseHeader(String param) {
        LoggerUtils.getLogger().info("get response header");
        return response.getHeader(param);
    }

    public static String getJsonString(String jsonPath) {
        try {
            LoggerUtils.getLogger().info("get string from json, path:" + jsonPath);
            JsonPath jsPath = response.jsonPath();
            return jsPath.get(jsonPath);
        } catch (Exception e) {
            LoggerUtils.getLogger().warn("getting values from json fail");
            return JsonUtils.getValue(JsonKeys.clearString);

        }
    }


    public static void postRequest(Object obj) {
        LoggerUtils.getLogger().info("post Request");
        httpRequest.header(JsonUtils.getValue(JsonKeys.jsonPath), JsonUtils.getValue(JsonKeys.contentTypeValue));
        response = httpRequest.body(obj).post("");
    }

    public static Object getResponseBody(Class name) {
        LoggerUtils.getLogger().info("convert json to pojo");
        return response.body().as(name);
    }

    public static List<Post> getResponseBodyAsPostList() {
        LoggerUtils.getLogger().info("convert json to pojo Post[]");
        return Arrays.asList(response.body().as(Post[].class));
    }

    public static List<User> getResponseBodyAsUserList() {
        LoggerUtils.getLogger().info("convert json to pojo User[]");
        return Arrays.asList(response.body().as(User[].class));
    }
}
