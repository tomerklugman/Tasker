package com.example.tasker;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class AddtaskFragment extends Fragment {


    public static int id1 = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_add_task_button, container, false);


        EditText name =(EditText) view.findViewById(R.id.name);
        EditText desc = (EditText) view.findViewById(R.id.textdesc);
        EditText price = (EditText) view.findViewById(R.id.price);


        Button b = (Button) view.findViewById(R.id.addbutton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name1 = name.getText().toString().trim();
                String desc1 = desc.getText().toString().trim();
                String price1 = price.getText().toString().trim();

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("houses1");
                Query checkHouseDatabase = reference.orderByKey();
                checkHouseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){


                            String strid1=Integer.toString(++id1);

                            model model = new model(name1, desc1, price1,"pending",strid1,"1");

                            reference.child(userGettersSetters.house).child("tasks").child(strid1).setValue(model);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                replaceFragment(new TasksFragment());
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