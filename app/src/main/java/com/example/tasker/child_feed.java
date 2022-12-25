package com.example.tasker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class child_feed extends AppCompatActivity {
    private ArrayList<Task>_tasklist;
    private RecyclerView _recyclerview;
    private String _houseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_feed);
        _recyclerview = findViewById(R.id.parent_tasks_feed);
        _tasklist = new ArrayList<>();

        setUserInfo();
        setAdapter();
    }

    private void setAdapter() {
        childAdapter adapter = new childAdapter(_tasklist);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        _recyclerview.setLayoutManager(layoutManager);
        _recyclerview.setItemAnimator(new DefaultItemAnimator());
        _recyclerview.setAdapter(adapter);
    }

    private void setUserInfo(){
        _tasklist.add(new Task("Take the garbage out"));
        _tasklist.add(new Task("Finish your report on the battle of Gettysburg"));
        _tasklist.add(new Task("Wash the kitchen floor"));

    }
}