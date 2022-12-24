package com.example.tasker;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ActivityNavigator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class addTaskFragment extends Fragment {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance(login_regular.f_url).getReference();









    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_addtask, container, false);
        initsubmitButton(view);

        return view;
    }



    private void initsubmitButton(View view){

        TextInputLayout _title = view.findViewById(R.id.title);
        TextInputLayout _price = view.findViewById(R.id.price);
        TextInputLayout _content = view.findViewById(R.id.content);
        EditText title = _title.getEditText();
        EditText price = _price.getEditText();
        EditText content = _content.getEditText();
        Button submitBtn = view.findViewById(R.id.submit);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(title != null && price != null && content !=null) {
                    if(title.getText().toString().length()>0 && price.getText().toString().length() > 0 && content.getText().toString().length() > 0){
                        DatabaseReference newTask = databaseReference.child("houses").child("2").child("tasks").push();
                        Task newtask = new Task("",content.getText().toString(),price.getText().toString(),"undone",title.getText().toString(),login_regular.val);
                        newTask.setValue(newtask);
                        Toast.makeText(getContext(), "the task was sent succesfully", Toast.LENGTH_SHORT).show();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.addtaskcontainer , new HomeFragment()).commit();

                    }
                    if(title.getText().toString().length() < 1){
                        System.out.println("???????");
                        Toast.makeText(getContext() , "invalid title" , Toast.LENGTH_SHORT).show();
                    }
                    else if(price.getText().toString().length() < 1 ){
                        Toast.makeText(getContext() , "invalid price" , Toast.LENGTH_SHORT).show();
                    }
                    else if(content.getText().toString().length() < 1) {
                        Toast.makeText(getContext() , "invalid content" , Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        );
    }
    }
