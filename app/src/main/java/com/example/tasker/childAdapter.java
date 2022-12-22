package com.example.tasker;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;


import java.util.ArrayList;

public class childAdapter extends RecyclerView.Adapter<childAdapter.MyViewHolder> {

    private ArrayList<Task>_taskList;


    public childAdapter(ArrayList<Task>tasksList){
        this._taskList = tasksList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView _nameTxt, _descTxt, _childTxt;

        public MyViewHolder(final View view){
            super(view);
            _nameTxt = view.findViewById(R.id.taskName);
            _childTxt = view.findViewById(R.id.childName);
        }
    }


    @NonNull
    @Override
    public childAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_task, parent, false);
        return new MyViewHolder(itemview);
    }



    @Override
    public void onBindViewHolder(@NonNull childAdapter.MyViewHolder holder, int position) {
        String name = _taskList.get(position).get_name();
        holder._nameTxt.setText(name);
    }

    @Override
    public int getItemCount() {
        return _taskList.size();
    }
}

