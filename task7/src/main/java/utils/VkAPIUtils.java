package utils;

import keys.ApiKeys;
import keys.JsonKeys;
import keys.TestDataKeys;
import pojo.*;

public class VkAPIUtils {
    private static Example example;
    private static final String ADDRESS = JsonUtils.getString(ApiKeys.BASE_ADDRESS, JsonUtils.API);
    private static final String POST = JsonUtils.getString(ApiKeys.POST_METHOD, JsonUtils.API);
    private static final String WALL = JsonUtils.getString(JsonKeys.WALL, JsonUtils.API);
    private static final String EDIT = JsonUtils.getString(ApiKeys.EDIT, JsonUtils.API);
    private static final String COMMENT = JsonUtils.getString(ApiKeys.CREATE_COMMENT, JsonUtils.API);
    private static final String LIKED = JsonUtils.getString(ApiKeys.IS_LIKED, JsonUtils.API);
    private static final String LIKES = JsonUtils.getString(JsonKeys.LIKES, JsonUtils.API);
    private static final String DELETE = JsonUtils.getString(ApiKeys.DELETE, JsonUtils.API);
    private static final String GET_SERVER = JsonUtils.getString(ApiKeys.GET_SERVER, JsonUtils.API);
    private static final String PHOTOS = JsonUtils.getString(JsonKeys.PHOTOS, JsonUtils.API);
    private static final String PATH = JsonUtils.getString(TestDataKeys.PATH_TO_PHOTO, JsonUtils.PHOTO_JSON);
    private static final String SAVE_PHOTO = JsonUtils.getString(ApiKeys.SAVE_PHOTO, JsonUtils.API);

    private static String createMethod(String method, String methodTeg) {
        LoggerUtils.getLogger().info("Create vk api Method");
        String urr = ADDRESS + methodTeg + "." + method + "?" + ParamsUtils.getParams();
        return ADDRESS + methodTeg + "." + method + "?" + ParamsUtils.getParams();
    }

    public static String createPost(String random) {
        ParamsUtils.createPostParams(random);
        return createMethod(POST, WALL);
    }

    public static String editPost(String random, Response response, PhotoAnswer photo) {
        ParamsUtils.createEditParams(random, Integer.toString(response.getPostId()), photo);
        return createMethod(EDIT, WALL);
    }

    public static String createComment(String random, Response response, PhotoAnswer photo) {
        ParamsUtils.createCommentParams(photo, response, random);
        return createMethod(COMMENT, WALL);
    }

    public static String isLiked(Response response) {
        ParamsUtils.isLikedParams(response);
        return createMethod(LIKED, LIKES);
    }

    public static String deletePost(Response response) {
        ParamsUtils.deleteParams(response);
        return createMethod(DELETE, WALL);
    }

    public static PhotoAnswer downloadPhoto() {
        sharePhoto(getDownloadURL());
        savePhoto();
        return ((Photo) APIUtils.getResponse(Photo.class)).getResponse().get(0);
    }

    private static String getDownloadURL() {
        LoggerUtils.getLogger().info("get url to download photo");
        ParamsUtils.createGetServerParams();
        APIUtils.getRequest(createMethod(GET_SERVER, PHOTOS));
        return ((Post) APIUtils.getResponse(Post.class)).getResponse().getUploadUrl();
    }

    private static void sharePhoto(String url) {
        LoggerUtils.getLogger().info("share photo");
        APIUtils.postRequest(url, PATH);
        example = ((Example) APIUtils.getResponse(Example.class));
    }

    private static void savePhoto() {
        LoggerUtils.getLogger().info("save photo in album");
        ParamsUtils.createSavePhotoParams(example);
        APIUtils.getRequest(createMethod(SAVE_PHOTO, PHOTOS));
    }
}

