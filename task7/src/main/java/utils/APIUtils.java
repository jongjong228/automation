package utils;

import com.google.gson.Gson;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.FileBody;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import pojo.Post;

import java.io.File;
import java.io.IOException;

public class APIUtils {
    private static final CloseableHttpClient httpclient = HttpClients.createDefault();
    private static CloseableHttpResponse response;
    private static HttpEntity entity;

    public static void getRequest(String url) {
        try {
            LoggerUtils.getLogger().info("Create get request");
            response = httpclient.execute(new HttpGet(url));
            entity = response.getEntity();
        } catch (IOException e) {
            LoggerUtils.getLogger().warn("Get request fail");
            e.printStackTrace();
        }
    }

    public static Object getResponse(Class name) {
        if (entity != null) {
            try {
                LoggerUtils.getLogger().info("get response");
                String str = EntityUtils.toString(entity);
                return new Gson().fromJson(str, name);
            } catch (IOException | ParseException e) {
                LoggerUtils.getLogger().warn("entity to string fail");
                e.printStackTrace();
            }
        }
        return new Post();
    }

    public static int getStatusCode() {
        LoggerUtils.getLogger().info("get response status code");
        return response.getCode();
    }

    public static void postRequest(String url, String path) {
        LoggerUtils.getLogger().info("Create post request");
        HttpPost httpPost = new HttpPost(url);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addPart("file", new FileBody(new File(path)));
        HttpEntity multipart = builder.build();
        httpPost.setEntity(multipart);
        try {
            response = httpclient.execute(httpPost);
        } catch (IOException e) {
            LoggerUtils.getLogger().warn("entity to string fail");
            e.printStackTrace();
        }
        entity = response.getEntity();
    }
}
