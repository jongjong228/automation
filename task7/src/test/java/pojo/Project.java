package pojo;

import keys.SQLTestKeys;
import utils.JsonUtils;
import utils.SQLScriptsUtils;

import java.util.ArrayList;

public class Project {
    private final String PROJECT = JsonUtils.getString(SQLTestKeys.PROJECT, JsonUtils.SQL_DATA);
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project() {
        setName(PROJECT);
    }

    public ArrayList<String> getParams() {
        ArrayList<String> params = new ArrayList<>();
        params.add(SQLScriptsUtils.formatString(this.name));
        return params;
    }
}
