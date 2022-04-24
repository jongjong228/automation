package pojo;

import com.google.gson.annotations.SerializedName;

public class Response {
    private int liked;
    private int copied;
    private int id;
    @SerializedName("post_id")
    private int postId;
    @SerializedName("album_id")
    private int albumId;
    @SerializedName("user_id")
    private int userId;
    @SerializedName("upload_url")
    private String uploadUrl;

    public int getLiked() {
        return liked;
    }

    public int getCopied() {
        return copied;
    }

    public int getId() {
        return id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }

    public void setCopied(int copied) {
        this.copied = copied;
    }

    public boolean isLiked() {
        return liked == 1;
    }
}
