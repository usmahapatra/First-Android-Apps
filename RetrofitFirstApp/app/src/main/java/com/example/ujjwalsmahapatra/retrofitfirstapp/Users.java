package com.example.ujjwalsmahapatra.retrofitfirstapp;

public class Users {
    private String login,id;

    public Users(String login, String id) {
        this.login = login;
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
