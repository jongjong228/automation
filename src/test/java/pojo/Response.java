package pojo;

public class Response {
    private int liked;
    private int copied;
    private int id;
    private int post_id;
    private int album_id;
    private int user_id;
    private String upload_url;

    public int getLiked() {
        return liked;
    }

    public int getCopied() {
        return copied;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUpload_url() {
        return upload_url;
    }


    public int getAlbum_id() {
        return album_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUpload_url(String upload_url) {
        this.upload_url = upload_url;
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
