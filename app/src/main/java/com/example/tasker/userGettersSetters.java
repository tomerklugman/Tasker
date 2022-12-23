package com.example.tasker;

public class userGettersSetters {

    public static String username,password,status,house;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public userGettersSetters(String username, String password,String status,String house) {
        this.username = username;
        this.password = password;
        this.status = status;
        this.house = house;
    }

    public userGettersSetters(String username, String password,String status) {
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public userGettersSetters(String username, String password) {
        this.username = username;
        this.password = password;

    }
    public userGettersSetters(String house) {
        this.house = house;


    }


}
