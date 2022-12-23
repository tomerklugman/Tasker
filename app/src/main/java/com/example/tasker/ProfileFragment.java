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

        //logout=getView().findViewById(R.id.name);

        //logout.setOnClickListener(view -> startActivity(new Intent(getActivity(),MainLoginPage.class)));

        view = inflater.inflate(R.layout.fragment_profile, container, false);

        Button myButton = view.findViewById(R.id.logout);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                startActivity(new Intent(getActivity(), login_regular.class));
            }
        });


/*
        name= getView().findViewById(R.id.name);
        mail=getView().findViewById(R.id.name);
        //user=findViewById(R.id.password);
        status=getView().findViewById(R.id.name);
        house=getView().findViewById(R.id.name);
        logout=getView().findViewById(R.id.name);






         // regular sign in

            name.setText(userGettersSetters.username);
            mail.setText(userGettersSetters.password);

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
            Query checkstatusDatabase = reference.orderByChild("username").equalTo(userGettersSetters.username);

            checkstatusDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    statusFromDB = snapshot.child(userGettersSetters.username).child("status").getValue(String.class);
                    houseFromDB = snapshot.child(userGettersSetters.username).child("house").getValue(String.class);


                    status.setText(statusFromDB);
                    house.setText(houseFromDB);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

*/

/*

            View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
            // Inflate the layout for this fragment
            Button btn = (Button) rootView.findViewById(R.id.logout);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                startActivity(new Intent(getActivity(), MainLoginPage.class));
            }
        });

            return rootView;
*/

/*
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        Button btn = (Button) v.findViewById(R.id.logout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                startActivity(new Intent(getActivity(), MainLoginPage.class));
            }
        });
        return v;

*/
/*
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        getActivity().finish();
                        startActivity(new Intent(getActivity(), MainLoginPage.class));
                    }
                });
            }
        });


*/

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }




}