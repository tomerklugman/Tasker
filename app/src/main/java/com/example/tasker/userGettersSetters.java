package com.example.tasker;

public class userGettersSetters {

    public static String username,password;

    public userGettersSetters() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public userGettersSetters(String username, String password) {
        this.username = username;
        this.password = password;
    }


}
