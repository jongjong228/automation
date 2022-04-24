package utils;

import keys.ApiKeys;
import keys.TestDataKeys;
import pojo.*;

public class VkAPIUtils {
    private static Example example;

    private static String createMethod(String method) {
        LoggerUtils.getLogger().info("Create vk api Method");
        return JsonUtils.getString(ApiKeys.BASE_ADDRESS) + method + "?" + ParamsUtils.getParams();
    }

    public static String createPost(String random) {
        ParamsUtils.createPostParams(random);
        return createMethod(JsonUtils.getString(ApiKeys.POST_METHOD));
    }

    public static String editPost(String random, Response response, PhotoAnswer photo) {
        ParamsUtils.createEditParams(random, Integer.toString(response.getPost_id()), photo);
        return createMethod(JsonUtils.getString(ApiKeys.EDIT_METHOD));
    }

    public static String createComment(String random, Response response, PhotoAnswer photo) {
        ParamsUtils.createCommentParams(photo, response, random);
        return createMethod(JsonUtils.getString(ApiKeys.CREATE_COMMENT_METHOD));
    }

    public static String isLiked(Response response) {
        ParamsUtils.isLikedParams(response);
        return createMethod(JsonUtils.getString(ApiKeys.IS_LIKED_METHOD));
    }

    public static String deletePost(Response response) {
        ParamsUtils.deleteParams(response);
        return createMethod(JsonUtils.getString(ApiKeys.DELETE_METHOD));
    }

    public static PhotoAnswer downloadPhoto() {
        sharePhoto(getDownloadURL());
        savePhoto();
        return ((Photo) APIUtils.getResponse(Photo.class)).getResponse().get(0);
    }

    private static String getDownloadURL() {
        LoggerUtils.getLogger().info("get url to download photo");
        ParamsUtils.createGetServerParams();
        APIUtils.getRequest(createMethod(JsonUtils.getString(ApiKeys.GET_SERVER_METHOD)));
        return ((Post) APIUtils.getResponse(Post.class)).getResponse().getUpload_url();
    }

    private static void sharePhoto(String url) {
        LoggerUtils.getLogger().info("share photo");
        APIUtils.postRequest(url, JsonUtils.getPhotoString(TestDataKeys.PATH_TO_PHOTO));
        example = ((Example) APIUtils.getResponse(Example.class));
    }

    private static void savePhoto() {
        LoggerUtils.getLogger().info("save photo in album");
        ParamsUtils.createSavePhotoParams(example);
        APIUtils.getRequest(createMethod(JsonUtils.getString(ApiKeys.SAVE_PHOTOS_METHOD)));
    }
}

