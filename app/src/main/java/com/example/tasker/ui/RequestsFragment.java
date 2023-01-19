package com.example.tasker.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tasker.R;
import com.example.tasker.models.taskRegGettersSetters;
import com.example.tasker.models.userGettersSetters;
import com.example.tasker.utils.fragmentsAdapater;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class RequestsFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    RecyclerView recview1;
    fragmentsAdapater adapter;

    public RequestsFragment() {

    }

    public static TasksFragment newInstance(String param1, String param2) {
        TasksFragment fragment = new TasksFragment();
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
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_requests, container, false);

        recview1=(RecyclerView) view.findViewById(R.id.recview1);
        recview1.setLayoutManager(new LinearLayoutManager(getContext()));


        FirebaseRecyclerOptions<taskRegGettersSetters> options =
                new FirebaseRecyclerOptions.Builder<taskRegGettersSetters>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("houses").child(userGettersSetters.house).child("requests"), taskRegGettersSetters.class)
                        .build();

        adapter=new fragmentsAdapater(options);
        recview1.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}