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

    private static void clearParams() {
        LoggerUtils.getLogger().info("create parameters");
        params.clear();
    }

    private static void addGeneralParams() {
        params.put(JsonUtils.getString(ApiKeys.ACCESS_TOKEN), JsonUtils.getUserString(TestDataKeys.TOKEN));
        params.put(JsonUtils.getString(JsonKeys.VERSION), JsonUtils.getString(JsonKeys.API_VERSION));
    }

    public static String getParams() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> item : params.entrySet()) {
            builder.append(item.getKey()).append(item.getValue()).append("&");
        }
        return builder.toString();
    }

    public static void createPostParams(String random) {
        clearParams();
        params.put(JsonUtils.getString(ApiKeys.OWNER_FIELD), JsonUtils.getUserString(TestDataKeys.USER_ID));
        params.put(JsonUtils.getString(ApiKeys.MESSAGE_FIELD), random);
        addGeneralParams();
    }

    public static void createEditParams(String random, String id, PhotoAnswer photo) {
        createPostParams(random);
        params.put(JsonUtils.getString(ApiKeys.POST_ID_FIELD), id);
        params.put(JsonUtils.getString(ApiKeys.ATTACHMENTS_FIELD), photo.getOwner_id() + "_" + photo.getId());
        addGeneralParams();
    }

    public static void createGetServerParams() {
        clearParams();
        params.put(JsonUtils.getString(ApiKeys.ALBUM_FIELD), JsonUtils.getPhotoString(TestDataKeys.ALBUM_ID));
        addGeneralParams();
    }

    public static void createSavePhotoParams(Example example) {
        clearParams();
        params.put(JsonUtils.getString(ApiKeys.ALBUM_FIELD), JsonUtils.getPhotoString(TestDataKeys.ALBUM_ID));
        params.put(JsonUtils.getString(ApiKeys.HASH), example.getHash());

        try {
            params.put(JsonUtils.getString(ApiKeys.PHOTO_LIST), URLEncoder.encode(example.getPhotos_list(), java.nio.charset.StandardCharsets.UTF_8.toString()));
        } catch (UnsupportedEncodingException e) {
            LoggerUtils.getLogger().warn("Photo encoding fail");
            e.printStackTrace();
        }
        params.put(JsonUtils.getString(ApiKeys.AID), Integer.toString(example.getAid()));
        params.put(JsonUtils.getString(ApiKeys.SERVER), Integer.toString(example.getServer()));
        addGeneralParams();
    }

    public static void createCommentParams(PhotoAnswer post, Response response, String mes) {
        clearParams();
        params.put(JsonUtils.getString(ApiKeys.OWNER_FIELD), Integer.toString(post.getOwner_id()));
        params.put(JsonUtils.getString(ApiKeys.MESSAGE_FIELD), mes);
        params.put(JsonUtils.getString(ApiKeys.POST_ID_FIELD), Integer.toString(response.getPost_id()));
        addGeneralParams();
    }

    public static void isLikedParams(Response response) {
        clearParams();
        params.put(JsonUtils.getString(ApiKeys.USER_ID_FIELD), JsonUtils.getUserString(TestDataKeys.USER_ID));
        params.put(JsonUtils.getString(ApiKeys.TYPE_FIELD), JsonUtils.getString(JsonKeys.POST_LABEL));
        params.put(JsonUtils.getString(ApiKeys.ITEM_ID_FIELD), Integer.toString(response.getPost_id()));
        addGeneralParams();
    }

    public static void deleteParams(Response response) {
        clearParams();
        params.put(JsonUtils.getString(ApiKeys.OWNER_FIELD), JsonUtils.getUserString(TestDataKeys.USER_ID));
        params.put(JsonUtils.getString(ApiKeys.POST_ID_FIELD), Integer.toString(response.getPost_id()));
        addGeneralParams();
    }
}
