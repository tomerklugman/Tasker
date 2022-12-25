package com.example.tasker;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class descfragmet extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    String name, desc, price, status, id;

    public descfragmet() {

    }

    public descfragmet(String name,String desc,String price,String status, String id) {
        this.name=name;
        this.desc=desc;
        this.price=price;
        this.status=status;
        this.id=id;

    }


    public static descfragmet newInstance(String param1, String param2) {
        descfragmet fragment = new descfragmet();
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

        View view= inflater.inflate(R.layout.fragment_descfragmet, container, false);

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
                DatabaseReference  ref = FirebaseDatabase.getInstance().getReference("houses1").child(userGettersSetters.house).child("requests").child(mod.getId());
                ref.child("status").setValue(status);
            }
        });

        rejected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Do something when the button is clicked.
                status = "rejected";
                model mod = new model(name, desc, price,status, id);
                DatabaseReference  ref = FirebaseDatabase.getInstance().getReference("houses1").child(userGettersSetters.house).child("requests").child(mod.getId());
                ref.child("status").setValue(status);
            }
        });

        return view;
    }



    public void onBackPressed(){
        AppCompatActivity activity= (AppCompatActivity) getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new RequestsFragment()).addToBackStack(null).commit();

    }
}