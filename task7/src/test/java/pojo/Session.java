package pojo;

import utils.SQLScriptsUtils;

import java.util.ArrayList;

public class Session {
    private int id;
    private String key;
    private String createTime;
    private int buildNumber;

    public int getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getCreateTime() {
        return createTime;
    }

    public int getBuildNumber() {
        return buildNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setBuildNumber(int buildNumber) {
        this.buildNumber = buildNumber;
    }

    public Session(String time, int number, String key) {
        setCreateTime(time);
        setKey(key);
        setBuildNumber(number);
    }

    public ArrayList<String> getParams() {
        ArrayList<String> params = new ArrayList<>();
        params.add(SQLScriptsUtils.formatString(this.key));
        params.add(SQLScriptsUtils.formatString(this.createTime));
        params.add(Integer.toString(this.buildNumber));
        return params;
    }
}
