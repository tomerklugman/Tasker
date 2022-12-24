package com.example.tasker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.tasker.databinding.ActivityHomeBottomToolbarBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class home_bottom_toolbar extends AppCompatActivity {




    ActivityHomeBottomToolbarBinding binding;
    FloatingActionButton actionButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBottomToolbarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new HomeFragment());

        actionButton = findViewById(R.id.plusbutton);


        binding.bottomNavigationView.setBackground(null);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    item.setChecked(true);
                    replaceFragment(new HomeFragment());
                    break;

                case R.id.tasks:
                    item.setChecked(true);
                    replaceFragment(new TasksFragment());
                    break;

                case R.id.change:
                    item.setChecked(true);
                    replaceFragment(new ChangeFragment());
                    break;

                case R.id.profile:
                    item.setChecked(true);
                    replaceFragment(new ProfileFragment());
                    break;

            }

            return false;
        });
        actionButton.setOnClickListener(view ->replaceFragment(new addTaskFragment()));
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }


}