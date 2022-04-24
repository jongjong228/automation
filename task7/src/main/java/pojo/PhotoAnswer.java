package pojo;

import com.google.gson.annotations.SerializedName;
import keys.JsonKeys;
import utils.JsonUtils;
import utils.LoggerUtils;

public class PhotoAnswer {
    private final String PHOTO = JsonUtils.getString(JsonKeys.PHOTO, JsonUtils.API);
    private final String URL = JsonUtils.getString(JsonKeys.BASE_URL, JsonUtils.PROPERTIES);
    @SerializedName("album_id")
    private int albumId;
    private int id;
    private int date;
    @SerializedName("owner_id")
    private int ownerId;
    private String size;
    private String text;
    private boolean hasTags;

    public int getId() {
        return id;
    }

    public int getDate() {
        return date;
    }

    public int getAlbumId() {
        return albumId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public String getSize() {
        return size;
    }

    public String getText() {
        return text;
    }

    public boolean isHasTags() {
        return hasTags;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public void setHasTags(boolean hasTags) {
        this.hasTags = hasTags;
    }

    public String getPhotoHref() {
        LoggerUtils.getLogger().info("get Photo Id");
        return URL + PHOTO + ownerId + "_" + id;
    }
}
