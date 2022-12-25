package com.example.tasker;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class descfragmet extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    String name, desc, price, status;

    public descfragmet() {

    }

    public descfragmet(String name,String desc,String price,String status) {
        this.name=name;
        this.desc=desc;
        this.price=price;
        this.status=status;


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




        return view;
    }

    public void onBackPressed(){
        AppCompatActivity activity= (AppCompatActivity) getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new RequestsFragment()).addToBackStack(null).commit();

    }
}