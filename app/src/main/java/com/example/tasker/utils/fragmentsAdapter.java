package com.example.tasker.utils;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tasker.R;
import com.example.tasker.models.taskRegGettersSetters;
import com.example.tasker.models.userGettersSetters;
import com.example.tasker.ui.descfragmentAcceptRejectFinish;
import com.example.tasker.ui.descfragmentNoAcceptNoreject;
import com.example.tasker.ui.descfragmetAcceptReject;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.Objects;

public class fragmentsAdapter extends FirebaseRecyclerAdapter<taskRegGettersSetters, fragmentsAdapter.myviewholder> {


    public fragmentsAdapter(@NonNull FirebaseRecyclerOptions<taskRegGettersSetters> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull taskRegGettersSetters taskRegGettersSetters) {
        holder.nametext.setText(taskRegGettersSetters.getName());
        holder.desctext.setText(taskRegGettersSetters.getDesc());
        holder.pricetext.setText(taskRegGettersSetters.getPrice());
        holder.statustext.setText(taskRegGettersSetters.getStatus());

        if (Objects.equals(taskRegGettersSetters.getStatus(),"pending")){
            holder.statustext.setTextColor(Color.BLUE);
        }

        if (Objects.equals(taskRegGettersSetters.getStatus(),"accepted")){
            holder.statustext.setTextColor(Color.GREEN);
        }

        if (Objects.equals(taskRegGettersSetters.getStatus(),"rejected")){
            holder.statustext.setTextColor(Color.RED);
        }

        if (Objects.equals(taskRegGettersSetters.getStatus(),"finished")){
            holder.statustext.setTextColor(Color.GRAY);
        }



        holder.nametext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity= (AppCompatActivity) view.getContext();

                if(Objects.equals(userGettersSetters.status,"parent" )){ // if parent


                if(Objects.equals(taskRegGettersSetters.task,"1") ){

                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new descfragmentNoAcceptNoreject(taskRegGettersSetters.getName(), taskRegGettersSetters.getDesc(), taskRegGettersSetters.getPrice(), taskRegGettersSetters.getStatus(), taskRegGettersSetters.getId())).addToBackStack(null).commit();



                } else { // its a parent request

                    if (Objects.equals(taskRegGettersSetters.task,"0") && (Objects.equals(taskRegGettersSetters.status,"accepted") || Objects.equals(taskRegGettersSetters.status,"rejected"))){ // parent accepted request or rejected -- saved as history
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new descfragmentNoAcceptNoreject(taskRegGettersSetters.getName(), taskRegGettersSetters.getDesc(), taskRegGettersSetters.getPrice(), taskRegGettersSetters.getStatus(), taskRegGettersSetters.getId())).addToBackStack(null).commit();
                    } else { // parent pending for accept or reject
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new descfragmetAcceptReject(taskRegGettersSetters.getName(), taskRegGettersSetters.getDesc(), taskRegGettersSetters.getPrice(), taskRegGettersSetters.getStatus(), taskRegGettersSetters.getId())).addToBackStack(null).commit();
                    }
                }

                }else { if(Objects.equals(taskRegGettersSetters.task,"1") ){ // if child
                    //child tasks
                    if (Objects.equals(taskRegGettersSetters.task,"1") && (Objects.equals(taskRegGettersSetters.status,"finished") || Objects.equals(taskRegGettersSetters.status,"rejected"))){ // child finished or rejected task
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new descfragmentNoAcceptNoreject(taskRegGettersSetters.getName(), taskRegGettersSetters.getDesc(), taskRegGettersSetters.getPrice(), taskRegGettersSetters.getStatus(), taskRegGettersSetters.getId())).addToBackStack(null).commit();
                    } else { // child accepted task
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new descfragmentAcceptRejectFinish(taskRegGettersSetters.getName(), taskRegGettersSetters.getDesc(), taskRegGettersSetters.getPrice(), taskRegGettersSetters.getStatus(), taskRegGettersSetters.getId())).addToBackStack(null).commit();
                    }
                }else { // child requests - can only look as history
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new descfragmentNoAcceptNoreject(taskRegGettersSetters.getName(), taskRegGettersSetters.getDesc(), taskRegGettersSetters.getPrice(), taskRegGettersSetters.getStatus(), taskRegGettersSetters.getId())).addToBackStack(null).commit();
                }
                }
            }
        });


    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign,parent,false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder{

        TextView nametext,desctext,pricetext,statustext;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            nametext=itemView.findViewById(R.id.nametext);
            desctext=itemView.findViewById(R.id.desctext);
            pricetext=itemView.findViewById(R.id.pricetext);
            statustext=itemView.findViewById(R.id.statustext);





        }
    }
}
