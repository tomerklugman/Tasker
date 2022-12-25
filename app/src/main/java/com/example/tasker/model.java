package com.example.tasker;

public class model {

    String name,desc,price,status,id;

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
