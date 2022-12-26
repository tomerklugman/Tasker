package com.example.tasker;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


public class descfragmetAcceptReject extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    String name, desc, price, status, id;

    public descfragmetAcceptReject() {

    }

    public descfragmetAcceptReject(String name, String desc, String price, String status, String id) {
        this.name=name;
        this.desc=desc;
        this.price=price;
        this.status=status;
        this.id=id;

    }

    public descfragmetAcceptReject(String name, String desc, String price, String status) {
        this.name=name;
        this.desc=desc;
        this.price=price;
        this.status=status;

    }


    public static descfragmetAcceptReject newInstance(String param1, String param2) {
        descfragmetAcceptReject fragment = new descfragmetAcceptReject();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_descfragmet_acceot_reject, container, false);

        TextView nameholder = view.findViewById(R.id.nameholder);
        TextView descholder = view.findViewById(R.id.descholder);
        TextView priceholder = view.findViewById(R.id.priceholder);
        TextView statusholder = view.findViewById(R.id.statusholder);

        nameholder.setText(name);
        descholder.setText(desc);
        priceholder.setText(price);
        statusholder.setText(status);

        Button accepted = view.findViewById(R.id.accept_button);
        Button rejected = view.findViewById(R.id.reject_button);

        accepted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Do something when the button is clicked.
                status = "accepted";
                model mod = new model(name, desc, price,status, id);
                System.out.println(mod.id);
                if(Objects.equals(userGettersSetters.status,"parent")){
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("houses").child(userGettersSetters.house).child("tasks").child(mod.getId());
                    ref.child("status").setValue(status);

                    DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("houses").child(userGettersSetters.house).child("requests").child(mod.getId());
                    ref1.child("status").setValue(status);

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("houses");
                    Query checkHouseDatabase = reference.orderByKey();
                    checkHouseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){

                                String strid=Integer.toString(++addRequestFragment.id);

                                model model = new model(mod.getName(), mod.getDesc(), mod.getPrice(), mod.getStatus(),strid,"1");


                                reference.child(userGettersSetters.house).child("tasks").child(strid).setValue(model);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    replaceFragment(new TasksFragment());

                }else {


                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("houses1").child(userGettersSetters.house).child("tasks").child(mod.getId());
                    ref.child("status").setValue(status);
                    replaceFragment(new RequestsFragment());
                }
            }

        });

        rejected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Do something when the button is clicked.
                status = "rejected";
                model mod = new model(name, desc, price,status, id);
                if(Objects.equals(userGettersSetters.status,"parent")){
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("houses").child(userGettersSetters.house).child("requests").child(mod.getId());
                    ref.child("status").setValue(status);
                    replaceFragment(new TasksFragment());

                }else {


                    DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("houses").child(userGettersSetters.house).child("requests").child(mod.getId());
                    ref1.child("status").setValue(status);
                    replaceFragment(new RequestsFragment());
                }
            }

        });

        return view;
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onBackPressed(){
        AppCompatActivity activity= (AppCompatActivity) getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new RequestsFragment()).addToBackStack(null).commit();

    }
}