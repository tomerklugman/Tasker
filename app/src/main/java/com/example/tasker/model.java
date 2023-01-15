package com.example.tasker;

public class model {
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    String name,desc,price,status,id,task,user;

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public model() {
    }

    public model(String name, String desc, String price, String status) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.status = status;
    }

    public model(String name, String desc, String price, String status, String id) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.status = status;
        this.id = id;
    }
    public model(String name, String desc, String price, String status, String id,String task) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.status = status;
        this.id = id;
        this.task = task;

    }

    public model(String name, String desc, String price, String status, String id,String task,String user) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.status = status;
        this.id = id;
        this.task = task;
        this.user = user;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}