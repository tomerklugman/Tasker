package com.example.tasker;

import static androidx.databinding.DataBindingUtil.setContentView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class ProfileFragment extends Fragment {

    TextView name;
    TextView mail;
    TextView status;
    TextView house;
    Button logout;

    String statusFromDB;
    String houseFromDB;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    FirebaseDatabase database;
    DatabaseReference reference;

    View view;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_profile, container, false);

        name=view.findViewById(R.id.name);
        mail=view.findViewById(R.id.mail);
        status=view.findViewById(R.id.status);
        house=view.findViewById(R.id.house);

        name.setText(userGettersSetters.username);
        mail.setText(userGettersSetters.password);

        status.setText(userGettersSetters.status);
        house.setText("House "+userGettersSetters.house);

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

                if (userGettersSetters.status=="parent") {
                    getActivity().finish();
                    startActivity(new Intent(getActivity(), Parent_After_Login_CreateJoinHouse.class));
                }else{
                    getActivity().finish();
                    startActivity(new Intent(getActivity(), Child_After_login_JoinHouse.class));
                }
            }
        });



        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }




}