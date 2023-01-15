package com.example.tasker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class MonthlyGraph extends AppCompatActivity {

    BarChart barChart;
    Button returnButton;
    int id3;
    int value;
    //int i=1;
    public static int d1,d2,d3,d4,d5=0,d6,d7,d8,d9,d10,d11,d12,d13,d14,d15,d16,d17,d18;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_graph);

        int[] save = new int[31];
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<Integer> barEntries1 = new ArrayList<>();


        barChart = findViewById(R.id.bar_chart);


       // System.out.println(Arrays.toString(save));
      // for (int i=0; i <save.length ; i++){
      //     BarEntry barEntry = new BarEntry(i, 2*i);
      //      barEntries.add(barEntry);
      //  }


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        Query ref = firebaseDatabase.getReference("houses").child(userGettersSetters.house).child("tasks");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

     for(DataSnapshot ds : snapshot.getChildren()) {
         String check = ds.child("finishDay").getValue(String.class);
         id3 = Integer.parseInt(check);


         save[id3]=save[id3]+1;
         //System.out.println(Arrays.toString(save));
         d5=save[id3];

         //barEntries1.addAll((ArrayList<Integer>)snapshot.getValue());
         //System.out.println(d5);
        // System.out.println(barEntries1);

        // BarEntry barEntry = new BarEntry(id3, save[id3]);
         //barEntries.add(barEntry);






     }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }



        });

        //System.out.println(d5);
       // System.out.println(Arrays.toString(save));
      //  System.out.println(barEntries1);
      // for (int i=1; i <32 ; i++){
            //BarEntry barEntry = new BarEntry(5, d5);

      //  barEntries.add(new BarEntry(i, i));

    //  }

        barEntries.add(new BarEntry(1, 5));
        barEntries.add(new BarEntry(2, 2));
        barEntries.add(new BarEntry(3, 11));
        barEntries.add(new BarEntry(4, 6));
        barEntries.add(new BarEntry(5, 0));
        barEntries.add(new BarEntry(6, 0));
        barEntries.add(new BarEntry(7, 1));
        barEntries.add(new BarEntry(8, 0));
        barEntries.add(new BarEntry(9, 0));
        barEntries.add(new BarEntry(10, 0));
        barEntries.add(new BarEntry(11, 20));
        barEntries.add(new BarEntry(12, 1));
        barEntries.add(new BarEntry(13, 3));
        barEntries.add(new BarEntry(14, 2));
        barEntries.add(new BarEntry(15, 1));
        barEntries.add(new BarEntry(16, 5));
        barEntries.add(new BarEntry(17, 0));
        barEntries.add(new BarEntry(18, 0));
        barEntries.add(new BarEntry(19, 0));
        barEntries.add(new BarEntry(20, 5));
        barEntries.add(new BarEntry(21, 0));
        barEntries.add(new BarEntry(22, 0));
        barEntries.add(new BarEntry(23, 0));
        barEntries.add(new BarEntry(24, 8));
        barEntries.add(new BarEntry(25, 0));
        barEntries.add(new BarEntry(26, 0));
        barEntries.add(new BarEntry(27, 0));
        barEntries.add(new BarEntry(28, 2));
        barEntries.add(new BarEntry(29, 0));
        barEntries.add(new BarEntry(30, 1));
        barEntries.add(new BarEntry(31, 0));






        BarDataSet barDataSet = new BarDataSet(barEntries, "Days");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setDrawValues(true);
        barChart.setData(new BarData(barDataSet));
        barChart.animateY(5000);
        barChart.getDescription().setText("Days");
        barChart.getDescription().setTextColor(Color.BLUE);
        XAxis XAxis =barChart.getXAxis();
        XAxis.setPosition(com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM);
        XAxis.setLabelCount(31);






        returnButton = findViewById(R.id.returnbutton);

        returnButton.setOnClickListener(new View.OnClickListener() { // button action
            @Override
            public void onClick(View view) {

                if (Objects.equals(userGettersSetters.status,"parent")) {

                    startActivity(new Intent(MonthlyGraph.this, home_bottom_toolbar_parent.class));
                }else{
                    startActivity(new Intent(MonthlyGraph.this, home_bottom_toolbar_child.class));
                }
            }
        });

    }
}