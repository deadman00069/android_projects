package com.example.my_evm_app;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
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
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
public class result_graph extends Fragment {

    DatabaseReference ref;
    BarChart barChart;
    ArrayList barEntries;
    BarDataSet barDataSet;
    BarData barData;
    XAxis xAxis;
    int x=1;
    ArrayList <String> labe;

    public result_graph() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result_graph, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final String[] weekdays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        barChart=view.findViewById(R.id.barChart);
        barEntries = new ArrayList<>();
        labe=new ArrayList<>();
        labe.add("ttr");
        ref= FirebaseDatabase.getInstance().getReference();
        ref.child("vote").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren())
                {

                    BarEntry e=new BarEntry(Float.parseFloat(x+"f"),Integer.parseInt(ds.child("count").getValue().toString()));
                    barEntries.add(e);
                    labe.add(ds.getKey());
                    x++;

                }
                barDataSet = new BarDataSet(barEntries, "Parties");
                barData = new BarData(barDataSet);
                //barData.setBarWidth(0.9f);
                barChart.setData(barData);
                barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
                //barDataSet.setValueTextColor(Color.BLACK);
                //barDataSet.setValueTextSize(18f);
                xAxis=barChart.getXAxis();
                xAxis.setValueFormatter(new IndexAxisValueFormatter(labe));
                barChart.setFitBars(true);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
