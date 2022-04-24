package pojo;

import java.util.Objects;

public class Post {
    private int userId;
    private int id;
    private String body;
    private String title;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return getUserId() == post.getUserId() && getId() == post.getId() && Objects.equals(getBody(), post.getBody()) && Objects.equals(getTitle(), post.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getId(), getBody(), getTitle());
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

