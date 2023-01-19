package com.example.tasker.ui;

import static androidx.databinding.DataBindingUtil.setContentView;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tasker.R;
import com.example.tasker.loginSignUp.Child_After_login_JoinHouse;
import com.example.tasker.loginSignUp.Parent_After_Login_CreateJoinHouse;
import com.example.tasker.loginSignUp.login_regular;
import com.example.tasker.models.userGettersSetters;
import com.github.mikephil.charting.data.BarEntry;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.Objects;


public class ProfileFragment extends Fragment {

    TextView name;
    TextView mail;
    TextView status;
    TextView house;
    TextView sum;
    Button logout;

    String statusFromDB;
    String houseFromDB;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    FirebaseDatabase database;
    DatabaseReference reference;

    View view;

     int id4=0;
     int sum1=0;

    public static int[] DayGraph = new int[31];


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_profile, container, false);

        name=view.findViewById(R.id.name);
        mail=view.findViewById(R.id.mail);
        status=view.findViewById(R.id.status);
        house=view.findViewById(R.id.house);
        sum=view.findViewById(R.id.sum);

        name.setText(userGettersSetters.username);
        mail.setText(userGettersSetters.password);

        status.setText("Account Type: "+userGettersSetters.status);

        house.setText("House: "+userGettersSetters.house);

        for (int i = 0; i < DayGraph.length; i++) {
            DayGraph[i]=0;
        }


        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference();
        Query checkHouseDatabase1 = reference1.child("houses").child(userGettersSetters.house).child("tasks");
        checkHouseDatabase1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    if (Objects.equals(ds.child("status").getValue(String.class),"finished")) {
                        String check = ds.child("price").getValue(String.class);
                        id4 = Integer.parseInt(check);
                        sum1 = sum1 + id4;

                        String check1 = ds.child("finishDay").getValue(String.class);
                        int id5 = Integer.parseInt(check1);
                        DayGraph[id5] = DayGraph[id5] + 1;

                    }
                }
                sum.setText("Monthly Sum: " + sum1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        TextView myButton = (TextView) view.findViewById(R.id.logout);



        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                startActivity(new Intent(getActivity(), login_regular.class));
            }
        });


        TextView myButton1 = (TextView) view.findViewById(R.id.joinotherhouse);

        myButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Objects.equals(userGettersSetters.status,"parent")) {
                    getActivity().finish();
                    startActivity(new Intent(getActivity(), Parent_After_Login_CreateJoinHouse.class));
                }else{
                    getActivity().finish();
                    startActivity(new Intent(getActivity(), Child_After_login_JoinHouse.class));
                }
            }
        });
        TextView myButton2 = (TextView) view.findViewById(R.id.tasksgraph);
        myButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                startActivity(new Intent(getActivity(), MonthlyGraph.class));
            }
        });



        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }




}