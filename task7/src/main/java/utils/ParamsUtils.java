package utils;

import keys.ApiKeys;
import keys.JsonKeys;
import keys.TestDataKeys;
import pojo.Example;
import pojo.PhotoAnswer;
import pojo.Response;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class ParamsUtils {
    private static final Map<String, String> params = new HashMap<>();
    private static final String ACCESS_TOKEN = JsonUtils.getString(ApiKeys.ACCESS_TOKEN, JsonUtils.API);
    private static final String TOKEN = JsonUtils.getString(TestDataKeys.TOKEN, JsonUtils.TEST_DATA);
    private static final String VERSION = JsonUtils.getString(ApiKeys.VERSION, JsonUtils.API);
    private static final String API_VERSION = JsonUtils.getString(JsonKeys.API_VERSION, JsonUtils.API);
    private static final String OWNER = JsonUtils.getString(ApiKeys.OWNER, JsonUtils.API);
    private static final String USER = JsonUtils.getString(ApiKeys.USER, JsonUtils.API);
    private static final String MESSAGE = JsonUtils.getString(ApiKeys.MESSAGE, JsonUtils.API);
    private static final String PHOTO = JsonUtils.getString(JsonKeys.PHOTO, JsonUtils.API);
    private static final String POST = JsonUtils.getString(ApiKeys.POST, JsonUtils.API);
    private static final String ATTACHMENTS = JsonUtils.getString(ApiKeys.ATTACHMENTS, JsonUtils.API);
    private static final String ALBUM = JsonUtils.getString(ApiKeys.ALBUM, JsonUtils.API);
    private static final String ALBUM_ID = JsonUtils.getString(TestDataKeys.ALBUM_ID, JsonUtils.PHOTO_JSON);
    private static final String HASH = JsonUtils.getString(ApiKeys.HASH, JsonUtils.API);
    private static final String PHOTO_LIST = JsonUtils.getString(ApiKeys.PHOTO_LIST, JsonUtils.API);
    private static final String AID = JsonUtils.getString(ApiKeys.AID, JsonUtils.API);
    private static final String SERVER = JsonUtils.getString(ApiKeys.SERVER, JsonUtils.API);
    private static final String NAME = JsonUtils.getString(TestDataKeys.USER, JsonUtils.TEST_DATA);
    private static final String TYPE = JsonUtils.getString(ApiKeys.TYPE, JsonUtils.API);
    private static final String POST_LABEL = JsonUtils.getString(JsonKeys.POST, JsonUtils.API);
    private static final String ITEM = JsonUtils.getString(ApiKeys.ITEM, JsonUtils.API);

    private static void clearParams() {
        LoggerUtils.getLogger().info("create parameters");
        params.clear();
    }

    private static void addGeneralParams() {
        params.put(ACCESS_TOKEN, TOKEN);
        params.put(VERSION, API_VERSION);
    }

    public static String getParams() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> item : params.entrySet()) {
            builder.append(item.getKey()).append("=").append(item.getValue()).append("&");
        }
        return builder.toString();
    }

    public static void createPostParams(String random) {
        clearParams();
        params.put(OWNER, NAME);
        params.put(MESSAGE, random);
        addGeneralParams();
    }

    public static void createEditParams(String random, String id, PhotoAnswer photo) {
        createPostParams(random);
        String stringBuilder = PHOTO + photo.getOwnerId() + "_" +
                photo.getId();
        params.put(POST, id);
        params.put(ATTACHMENTS, stringBuilder);
        addGeneralParams();
    }

    public static void createGetServerParams() {
        clearParams();
        params.put(ALBUM, ALBUM_ID);
        addGeneralParams();
    }

    public static void createSavePhotoParams(Example example) {
        clearParams();
        params.put(ALBUM, ALBUM_ID);
        params.put(HASH, example.getHash());

        try {
            params.put(PHOTO_LIST, URLEncoder.encode(example.getPhotosList(), java.nio.charset.StandardCharsets.UTF_8.toString()));
        } catch (UnsupportedEncodingException e) {
            LoggerUtils.getLogger().warn("Photo encoding fail");
            e.printStackTrace();
        }
        params.put(AID, Integer.toString(example.getAid()));
        params.put(SERVER, Integer.toString(example.getServer()));
        addGeneralParams();
    }

    public static void createCommentParams(PhotoAnswer post, Response response, String mes) {
        clearParams();
        params.put(OWNER, Integer.toString(post.getOwnerId()));
        params.put(MESSAGE, mes);
        params.put(POST, Integer.toString(response.getPostId()));
        addGeneralParams();
    }

    public static void isLikedParams(Response response) {
        clearParams();
        params.put(USER, NAME);
        params.put(TYPE, POST_LABEL);
        params.put(ITEM, Integer.toString(response.getPostId()));
        addGeneralParams();
    }

    public static void deleteParams(Response response) {
        clearParams();
        params.put(OWNER, NAME);
        params.put(POST, Integer.toString(response.getPostId()));
        addGeneralParams();
    }
}