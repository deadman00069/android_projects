package com.example.xddd;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class stats_graphView extends Fragment {
    String ex_name;
    DatabaseReference reference;
    FirebaseAuth auth;
    FirebaseUser user;
    String uid;
    LinearLayout l;
    ArrayList<String> exercises;
    LineChart chart;
    TextView t;


    public stats_graphView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats_graph_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        l = view.findViewById(R.id.linear);
        t=view.findViewById(R.id.txttt);
        reference = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        uid = user.getUid();
        exercises = new ArrayList<>();
        final ArrayList<Entry> entries = new ArrayList<>();
        Fragment parentFrag = getParentFragment();
        Bundle parentArgs = parentFrag.getArguments();
        chart = view.findViewById(R.id.lineChart);
        final ArrayList<Integer> x_axis_data = new ArrayList<>();
        final ArrayList<String> y_asix_label=new ArrayList<>();
        if (parentArgs != null) {
            training_stat_selectArgs args = training_stat_selectArgs.fromBundle(getArguments());
            ex_name = args.getEName();
            if (ex_name.equals("chest")) {
                exercises.add("Barbell Bench Press");
                exercises.add("Dumbbell Bench Press");
                exercises.add("Smith Machine Incline Press");
                exercises.add("Incline Dumbbell Flye");
                exercises.add("Cable Crossover");
                exercises.add("Incline Dumbbell Press");
                exercises.add("Chest Press Machine");
                exercises.add("Dumbbell Flye");
                exercises.add("Low-Cable Crossover");

            } else if (ex_name.equals("biceps")) {
                exercises.add("Fat-Grip Hammer Curl");
                exercises.add("EZ-Bar Preacher Curl");
                exercises.add("Reverse Curl");
                exercises.add("Wide-Grip Curl");
                exercises.add("Close-Grip Curl");
                exercises.add("Hammer Curl");
                exercises.add("Dip");
                exercises.add("Side Curl with Band");
            }
            else if (ex_name.equals("back"))
            {
                exercises.add("Deadlift");
                exercises.add(" Incline Dumbbell Row");
                exercises.add("Trap-Bar Deadlift");
                exercises.add("Cable Row");
                exercises.add("Yates Row");
                exercises.add(" Bentover Row to Neck");
                exercises.add("Close-Grip Pull-Down");
                exercises.add(" Single-Arm Dumbbell Row");
            }
            else if (ex_name.equals("triceps"))
            {
                exercises.add("Diamond Pushup");
                exercises.add("Neutral-Grip Triceps Extension");
                exercises.add("Reverse Curl");
                exercises.add("Pullover/Triceps Extension");
                exercises.add("Decline Triceps Extension");
                exercises.add("Close-Grip Bench Press");
                exercises.add(" Lying Triceps Extension");
                exercises.add("Underhand Kickback");
            }
            else if (ex_name.equals("legs"))
            {
                exercises.add("Leg Press");
                exercises.add("Pause Squat");
                exercises.add("Reverse Lunge");
                exercises.add("Dumbbell Squat");
                exercises.add("Barbell Hip Thrust");
                exercises.add("Seated Calf Raise");
                exercises.add("Front Squat");
                exercises.add("Romanian Deadlift");
                exercises.add("Squart");
            }
            else if (ex_name.equals("shoulder"))
            {
                exercises.add("Barbell Overhead Press");
                exercises.add("Standing Dumbbell Fly");
                exercises.add(" Face Pull");
                exercises.add("High Pull");
                exercises.add("Seated Dumbbell Clean");
                exercises.add(" Clean and Press");
                exercises.add("Snatch-Grip High Pull");
                exercises.add("Machine Shoulder Press");
            }
            for (int i = 0; i < exercises.size(); i++) {
                final Button b = new Button(getContext());
                b.setText(exercises.get(i));
                b.setBackgroundResource(R.drawable.custom_button);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(800, ViewGroup.LayoutParams.WRAP_CONTENT);
                b.setLayoutParams(params);
                params.setMargins(0, 40, 0, 0);
                b.setTextColor(Color.parseColor("#FFFFFF"));
                l.addView(b);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        reference.child(uid).child(ex_name).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                    int i=0;
                                    for (DataSnapshot ds2 : ds.getChildren()) {
                                        if (ds2.getKey().equals(b.getText().toString())) {
                                            x_axis_data.add(Integer.parseInt(ds2.getValue().toString()));
                                            y_asix_label.add(ds.getKey());
                                        }
                                    }
                                }
                                for (int k=0;k<x_axis_data.size();k++)
                                {
                                    entries.add(new Entry(k,x_axis_data.get(k)));
//                                    y_asix_label.add(""+k+3);
                                }
                                if (entries.size()<1)
                                {
                                    Toast.makeText(getContext(), "Add more data", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                Log.i("llllol",""+y_asix_label);
                                t.setVisibility(View.INVISIBLE);
                                l.setVisibility(View.INVISIBLE);
                                chart.setVisibility(View.VISIBLE);
                                LineDataSet dataSet = new LineDataSet(entries, "Maximum Weight");
                                dataSet.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                                dataSet.setValueTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
                                chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(y_asix_label));
                                XAxis xAxis = chart.getXAxis();
                                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                                xAxis.setGranularity(1f);
                                chart.setTouchEnabled(true);
                                chart.setDragEnabled(true);
                                chart.setScaleEnabled(true);
                                LineData data11 = new LineData(dataSet);
                                chart.setData(data11);
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });





                    }
                });

            }


        }
    }
}
