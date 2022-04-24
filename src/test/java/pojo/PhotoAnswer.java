package pojo;

import utils.LoggerUtils;

public class PhotoAnswer {
    private int album_id;
    private int id;
    private int date;
    private int owner_id;
    private String size;
    private String text;
    private boolean has_tags;

    public int getAlbum_id() {
        return album_id;
    }

    public int getId() {
        return id;
    }

    public int getDate() {
        return date;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public String getSize() {
        return size;
    }

    public String getText() {
        return text;
    }

    public boolean isHas_tags() {
        return has_tags;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setHas_tags(boolean has_tags) {
        this.has_tags = has_tags;
    }

    public String getPhotoHref() {
        LoggerUtils.getLogger().info("get Photo Id");
        return owner_id + "_" + id;
    }
}
