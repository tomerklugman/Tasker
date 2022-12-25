package com.example.tasker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class PlusbuttonFragment extends Fragment {

    EditText nameEditText, descriptionEditText, amountEditText;
    Button addButton;
    String lastTask;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_plusbutton, container, false);

        // Find the EditText and Button views by their ID
        EditText editTextName = view.findViewById(R.id.name);
        EditText editTextDescription = view.findViewById(R.id.textdesc);
        EditText editTextAmount = view.findViewById(R.id.price);
        Button buttonCreate = view.findViewById(R.id.addbutton);


        // Set an OnClickListener for the button
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("houses2").child(userGettersSetters.house).child("requests");
                ref.orderByKey().limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot requestSnapshot : dataSnapshot.getChildren()) {
                            String requestNumber = requestSnapshot.getKey();
                            lastTask=requestNumber;
                            // Use the requestNumber variable here
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle the error
                    }
                });

                int taskNumber = Integer.parseInt(lastTask);
                taskNumber++;
                lastTask = Integer.toString(taskNumber);

                // Get the values from the EditText views
                String name = editTextName.getText().toString();
                String description = editTextDescription.getText().toString();
                String amount = editTextAmount.getText().toString();
                String id = lastTask;

                model mod = new model(name, description, amount, "pending", id);

                // Push the new request to the database
                ref = FirebaseDatabase.getInstance().getReference("houses2").child(userGettersSetters.house).child("requests").child(id).push();
                ref.setValue(mod);


            }
            // ... other code
        });

        return inflater.inflate(R.layout.fragment_plusbutton, container, false);
    }
}