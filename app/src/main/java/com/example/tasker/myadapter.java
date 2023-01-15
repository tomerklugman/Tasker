package com.example.tasker;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.Objects;

public class myadapter extends FirebaseRecyclerAdapter<model, myadapter.myviewholder> {


    public myadapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model model) {
        holder.nametext.setText(model.getName());
        holder.desctext.setText(model.getDesc());
        holder.pricetext.setText(model.getPrice());
        holder.statustext.setText(model.getStatus());

        if (Objects.equals(model.getStatus(),"pending")){
            holder.statustext.setTextColor(Color.BLUE);
        }

        if (Objects.equals(model.getStatus(),"accepted")){
            holder.statustext.setTextColor(Color.GREEN);
        }

        if (Objects.equals(model.getStatus(),"rejected")){
            holder.statustext.setTextColor(Color.RED);
        }

        if (Objects.equals(model.getStatus(),"finished")){
            holder.statustext.setTextColor(Color.GRAY);
        }



        holder.nametext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity= (AppCompatActivity) view.getContext();

                if(Objects.equals(userGettersSetters.status,"parent" )){ // if parent


                if(Objects.equals(model.task,"1") ){

                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new descfragmentNoAcceptNoreject(model.getName(), model.getDesc(), model.getPrice(), model.getStatus(), model.getId())).addToBackStack(null).commit();



                } else { // its a parent request

                    if (Objects.equals(model.task,"0") && (Objects.equals(model.status,"accepted") || Objects.equals(model.status,"rejected"))){ // parent accepted request or rejected -- saved as history
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new descfragmentNoAcceptNoreject(model.getName(), model.getDesc(), model.getPrice(), model.getStatus(), model.getId())).addToBackStack(null).commit();
                    } else { // parent pending for accept or reject
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new descfragmetAcceptReject(model.getName(), model.getDesc(), model.getPrice(), model.getStatus(), model.getId())).addToBackStack(null).commit();
                    }
                }

                }else { if(Objects.equals(model.task,"1") ){ // if child
                    //child tasks
                    if (Objects.equals(model.task,"1") && (Objects.equals(model.status,"finished") || Objects.equals(model.status,"rejected"))){ // child finished or rejected task
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new descfragmentNoAcceptNoreject(model.getName(), model.getDesc(), model.getPrice(), model.getStatus(), model.getId())).addToBackStack(null).commit();
                    } else { // child accepted task
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new descfragmentAcceptRejectFinish(model.getName(), model.getDesc(), model.getPrice(), model.getStatus(), model.getId())).addToBackStack(null).commit();
                    }
                }else { // child requests - can only look as history
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new descfragmentNoAcceptNoreject(model.getName(), model.getDesc(), model.getPrice(), model.getStatus(), model.getId())).addToBackStack(null).commit();
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
