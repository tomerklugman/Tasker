package com.example.tasker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Tadapter extends ArrayAdapter<Task> {
    public Tadapter(Context context, int resource , ArrayList<Task> tasks) {
        super(context, resource , tasks);
    }

    @SuppressLint("SuspiciousIndentation")
    @Override
    public View getView(int position , View convret , ViewGroup parent){
        Task task = getItem(position);
            if(convret==null)
                convret = LayoutInflater.from(getContext()).inflate(R.layout.taskactive,parent,false);
                taskcontent(convret , task);

        return convret;
    }

    private void taskcontent(View convert ,Task task ){
        TextView publisher = convert.findViewById(R.id.parentName);
        String textname = "Publisher: "+task.getPublisher();
        publisher.setText(textname);

        TextView price = convert.findViewById(R.id.textprice);
        String strprice = "Price: "+task.getPrice();
        price.setText(strprice);

        TextView content = convert.findViewById(R.id.textcontent);
        String textcontent = "Content: "+task.getContent();
        content.setText(textcontent);

        TextView status = convert.findViewById(R.id.textstatus);
        String textprice = "Status: "+task.getStatus();
        status.setText(textprice);

        TextView accomplisher = convert.findViewById(R.id.textaccomplisher);
        String textaccomplisher = "Accomplisher: "+"None" ;
        accomplisher.setText(textaccomplisher);





    }




}
