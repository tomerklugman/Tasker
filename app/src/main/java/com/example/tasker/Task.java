package com.example.tasker;

public class Task {
    private String _name, _description;
    Boolean _completed, _parentTask;
    int _price;

    public Task(String name){
        _name = name;
    }


    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public Boolean get_completed() {
        return _completed;
    }

    public void set_completed(Boolean _completed) {
        this._completed = _completed;
    }

    public int get_price() {
        return _price;
    }

    public void set_price(int _price) {
        this._price = _price;
    }

    public Boolean get_parentTask() {
        return _parentTask;
    }

    public void set_parentTask(Boolean _parentTask) {
        this._parentTask = _parentTask;
    }
}

