package com.example.tasker;

public class houseGettersSetters {

    public static String housenumber,housepassword;

    public houseGettersSetters() {
    }

    public String getUsername() {
        return housenumber;
    }

    public void setUsername(String username) {
        this.housenumber = housenumber;
    }

    public String getPassword() {
        return housepassword;
    }

    public void setPassword(String password) {
        this.housepassword = password;
    }

    public houseGettersSetters(String password) {

        this.housepassword = password;

    }


    public houseGettersSetters(String housenumber, String password) {
        this.housenumber = housenumber;
        this.housepassword = password;

    }


}
