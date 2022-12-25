package com.example.tasker;

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

        holder.nametext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity= (AppCompatActivity) view.getContext();

                if(Objects.equals(userGettersSetters.status,"parent" )){ // if parent

                if(Objects.equals(model.task,"1") ){

                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new descfragmentNoAcceptNoreject(model.getName(), model.getDesc(), model.getPrice(), model.getStatus(), model.getId())).addToBackStack(null).commit();



                } else { // its a parent request
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new descfragmetAcceptReject(model.getName(), model.getDesc(), model.getPrice(), model.getStatus(), model.getId())).addToBackStack(null).commit();

                }

                }else { if(Objects.equals(model.task,"1") ){// if child

                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new descfragmentAcceptRejectFinish(model.getName(), model.getDesc(), model.getPrice(), model.getStatus(), model.getId())).addToBackStack(null).commit();
                }else {
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
