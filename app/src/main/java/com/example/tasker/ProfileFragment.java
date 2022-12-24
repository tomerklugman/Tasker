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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class ProfileFragment extends Fragment {







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        initDetails(view);

        return view;
    }

    private void initDetails(View view){
        TextInputLayout _firstname = view.findViewById(R.id.TEfirstname);
//        TextInputLayout _lastname = view.findViewById(R.id.TElastname);
//        TextInputLayout _email = view.findViewById(R.id.TEemail);
//        TextInputLayout _gender = view.findViewById(R.id.TEgender);
//        TextInputLayout _password = view.findViewById(R.id.TEpassword);
        EditText firstname = _firstname.getEditText();
//        EditText lastname = _lastname.getEditText();
//        EditText email = _email.getEditText();
//        EditText password = _gender.getEditText();
//        EditText passwordEditText = _password.getEditText();



    }




}