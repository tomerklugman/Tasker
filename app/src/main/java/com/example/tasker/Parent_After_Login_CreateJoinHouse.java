package com.example.tasker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Parent_After_Login_CreateJoinHouse extends AppCompatActivity {

    EditText HouseNumber, HousePassword;
    Button CreateButton,JoinButton;

    FirebaseDatabase database;
    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_after_login_createjoinhouse);



        CreateButton = findViewById(R.id.create_house_button);
        JoinButton = findViewById(R.id.join_house_button);

        CreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HouseNumber = findViewById(R.id.create_house_number);
                HousePassword = findViewById(R.id.create_house_password);

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("houses");

                String HouseNum =  HouseNumber.getText().toString().trim();
                String HousePass = HousePassword.getText().toString().trim();

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("houses");
                Query checkHouseDatabase = reference.orderByKey();

                checkHouseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            HouseNumber.setError(null);
                            boolean HouseFromDB = snapshot.hasChild(HouseNum);



                            userGettersSetters.house=HouseNum;



                            if (!HouseFromDB){

                                houseGettersSetters houseGettersSetters = new houseGettersSetters(HousePass);

                                reference.child(HouseNum).child("password").setValue(HousePass);


                                Toast.makeText(Parent_After_Login_CreateJoinHouse.this, "you have created house " + HouseNum, Toast.LENGTH_SHORT).show();



                                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("users");
                                Query checkstatusDatabase = reference1.orderByChild("username").equalTo(userGettersSetters.username);

                                checkstatusDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        reference1.child(userGettersSetters.username).child("house").setValue(HouseNum);

                                        String statusFromDB = snapshot.child(userGettersSetters.username).child("status").getValue(String.class);

                                        userGettersSetters.status=statusFromDB;

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                                Intent intent = new Intent(Parent_After_Login_CreateJoinHouse.this, home_bottom_toolbar_parent.class); // change to parent home panel
                                startActivity(intent);

                            } else {

                                HouseNumber.setError(null);
                                HouseNumber.setError("house in use choose other number");
                                HouseNumber.requestFocus();

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        JoinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HouseNumber = findViewById(R.id.join_house_number);
                HousePassword = findViewById(R.id.join_house_password);

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("houses");

                String HouseNum =  HouseNumber.getText().toString().trim();
                String HousePass = HousePassword.getText().toString().trim();

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("houses");
                Query checkHouseDatabase = reference.orderByKey();

                checkHouseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            HouseNumber.setError(null);
                            boolean HouseFromDB = snapshot.hasChild(HouseNum);
                            String PassFromDB = snapshot.child(HouseNum).child("password").getValue(String.class);

                            userGettersSetters.house=HouseNum;


                            if (HouseFromDB && (Objects.equals(PassFromDB, HousePass))){

                                Toast.makeText(Parent_After_Login_CreateJoinHouse.this, "you have joined house " + HouseNum, Toast.LENGTH_SHORT).show();


                                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("users");
                                Query checkstatusDatabase = reference1.orderByChild("username").equalTo(userGettersSetters.username);

                                checkstatusDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        reference1.child(userGettersSetters.username).child("house").setValue(HouseNum);

                                        String statusFromDB = snapshot.child(userGettersSetters.username).child("status").getValue(String.class);

                                        userGettersSetters.status=statusFromDB;
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                                Intent intent = new Intent(Parent_After_Login_CreateJoinHouse.this, home_bottom_toolbar_parent.class); // change to parent home panel
                                startActivity(intent);

                            } else {

                                if(!HouseFromDB){
                                    HouseNumber.setError(null);
                                    HouseNumber.setError("no such house");
                                    HouseNumber.requestFocus();
                                }else {

                                    HousePassword.setError(null);
                                    HousePassword.setError("incorrect password");
                                    HousePassword.requestFocus();
                                }

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

    }
}