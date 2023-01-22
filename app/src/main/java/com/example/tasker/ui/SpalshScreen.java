package com.example.tasker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.tasker.R;
import com.example.tasker.ui.loginSignUp.MainLoginPage;

public class SpalshScreen extends AppCompatActivity {

    public static int Splash_Timer = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SpalshScreen.this, MainLoginPage.class);
                startActivity(intent);
                finish();
            }
        },Splash_Timer );
    }
}