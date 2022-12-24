package com.example.tasker;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;


public class TasksFragment extends Fragment {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance(login_regular.f_url).getReference();
    Tadapter tAdapter;
   static  ArrayList<Task> taskl = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tasks, container, false);
        initLV(view);

        databaseReference.child("houses").child(login_regular.houseFromDB).child("tasks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                taskl = new ArrayList<>();
                for(DataSnapshot snapshot : datasnapshot.getChildren()){

                    Task newtask = new Task(snapshot.child("accomplisher").getValue(String.class) , snapshot.child("content").getValue(String.class) ,snapshot.child("price").getValue(String.class) ,snapshot.child("status").getValue(String.class) ,snapshot.child("title").getValue(String.class) ,login_regular.val);
                    taskl.add(newtask);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        return view;
    }
    private void initLV(View view){
        ListView listView = view.findViewById(R.id.tasksLV);
        tAdapter =new Tadapter(getContext(), 0 , taskl);
        listView.setAdapter(tAdapter);
    }

}