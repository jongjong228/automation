package pojo;

import keys.SQLTestKeys;
import utils.JsonUtils;
import utils.SQLScriptsUtils;

import java.util.ArrayList;

public class Author {
    private final String NAME = JsonUtils.getString(SQLTestKeys.NAME,  JsonUtils.SQL_DATA);
    private final String LOGIN = JsonUtils.getString(SQLTestKeys.NAME, JsonUtils.SQL_DATA);
    private final String EMAIL = JsonUtils.getString(SQLTestKeys.NAME, JsonUtils.SQL_DATA);
    private int id;
    private String name;
    private String login;
    private String email;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Author() {
        setName(NAME);
        setLogin(LOGIN);
        setEmail(EMAIL);
    }

    public ArrayList<String> getParams() {
        ArrayList<String> params = new ArrayList<>();
        params.add(SQLScriptsUtils.formatString(this.name));
        params.add(SQLScriptsUtils.formatString(this.login));
        params.add(SQLScriptsUtils.formatString(this.email));
        return params;
    }
}
