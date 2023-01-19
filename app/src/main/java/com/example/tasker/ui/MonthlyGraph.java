package com.example.tasker.ui;

import static com.example.tasker.ui.ProfileFragment.DayGraph;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tasker.R;
import com.example.tasker.models.userGettersSetters;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class MonthlyGraph extends AppCompatActivity {

    BarChart barChart;
    Button returnButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_graph);

        ArrayList<BarEntry> barEntries = new ArrayList<>();

        barChart = findViewById(R.id.bar_chart);



        for (int i = 0; i < DayGraph.length; i++) {
            BarEntry barEntry = new BarEntry(i, DayGraph[i]);
            barEntries.add(barEntry);
        }


        BarDataSet barDataSet = new BarDataSet(barEntries, "Days");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setDrawValues(true);
        barChart.setData(new BarData(barDataSet));
        barChart.animateY(5000);
        barChart.getDescription().setText("Days");
        barChart.getDescription().setTextColor(Color.BLUE);
        XAxis XAxis = barChart.getXAxis();
        XAxis.setPosition(com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM);
        XAxis.setLabelCount(31);


        returnButton = findViewById(R.id.returnbutton);

        returnButton.setOnClickListener(new View.OnClickListener() { // button action
            @Override
            public void onClick(View view) {

                if (Objects.equals(userGettersSetters.status, "parent")) {

                    startActivity(new Intent(MonthlyGraph.this, home_bottom_toolbar_parent.class));
                } else {
                    startActivity(new Intent(MonthlyGraph.this, home_bottom_toolbar_child.class));
                }
            }
        });

    }

}