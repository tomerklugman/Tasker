package com.example.tasker.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.tasker.R;
import com.example.tasker.models.taskRegGettersSetters;
import com.example.tasker.models.userGettersSetters;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class addRequestFragment extends Fragment {


    public static int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_add_request_button, container, false);


        EditText name =(EditText) view.findViewById(R.id.name);
        EditText desc = (EditText) view.findViewById(R.id.textdesc);
        EditText price = (EditText) view.findViewById(R.id.price);

        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference();
        Query checkHouseDatabase1 = reference1.child("houses").child(userGettersSetters.house).child("requests").orderByKey().limitToLast(1);
        checkHouseDatabase1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    String check = ds.child("id").getValue(String.class);
                    id = Integer.parseInt(check);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        Button b = (Button) view.findViewById(R.id.addbutton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name1 = name.getText().toString().trim();
                String desc1 = desc.getText().toString().trim();
                String price1 = price.getText().toString().trim();

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("houses");
                Query checkHouseDatabase = reference.orderByKey();
                checkHouseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){

                            String strid=Integer.toString(++id);

                            taskRegGettersSetters taskRegGettersSetters = new taskRegGettersSetters(name1, desc1, price1,"pending",strid,"0");


                            reference.child(userGettersSetters.house).child("requests").child(strid).setValue(taskRegGettersSetters);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                replaceFragment(new RequestsFragment());
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


}