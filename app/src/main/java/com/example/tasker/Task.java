package com.example.tasker;

public class Task {
    final private String accomplisher;
    final private String content;
    final private String price;
    final private String status;
    final private String title;
    final private String publisher;

    public Task(String accomplisher , String content , String price , String status , String title , String publisher){
        this.accomplisher = accomplisher;
        this.content = content;
        this.price = price;
        this.status = status;
        this.title = title;
        this.publisher=publisher;
    }

    public String getAccomplisher() {
        return accomplisher;
    }
    public String getContent() {
        return content;
    }
    public String getPrice() {
        return price;
    }
    public String getStatus() {
        return status;
    }
    public String getTitle() {
        return title;
    }
    public String getPublisher(){return publisher;}

}
