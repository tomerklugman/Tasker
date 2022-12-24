package com.example.tasker;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Calendar;
import java.util.Date;


public class HomeFragment extends Fragment {


    DatabaseReference databaseReference = FirebaseDatabase.getInstance(login_regular.f_url).getReference();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        initDateAndTime(view);

        return view;
    }

    private void initDateAndTime(View view){
        TextView welcome = view.findViewById(R.id.welcome);
        welcome.setText("Hello there, "+ login_regular.val);
        final Calendar calendar = Calendar.getInstance();
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                calendar.setTimeInMillis(System.currentTimeMillis());
                Date date = calendar.getTime();
                TextView dataTimeTextView = view.findViewById(R.id.date_and_time_view);
                dataTimeTextView.setText(String.format("%tF %tT",date,date));

                // reschedule the Runnable to run again after 1 second
                handler.postDelayed(this, 1000);
            }
        };

// start the loop by scheduling the Runnable to run after 1 second
        handler.postDelayed(runnable, 1000);
    }


}