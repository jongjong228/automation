package pojo;

import com.google.gson.annotations.SerializedName;

public class Example {
    private int server;
    private String hash;
    private int aid;
    @SerializedName("photos_list")
    private String photosList;

    public int getAid() {
        return aid;
    }

    public int getServer() {
        return server;
    }

    public String getHash() {
        return hash;
    }

    public String getPhotosList() {
        return photosList;
    }

    public void setServer(int server) {
        this.server = server;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setPhotosList(String photosList) {
        this.photosList = photosList;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }
}
