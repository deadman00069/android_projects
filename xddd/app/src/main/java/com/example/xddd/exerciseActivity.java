package com.example.xddd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class exerciseActivity extends AppCompatActivity {


    public RecyclerView recycler_view;
    public RecyclerView.Adapter adapter;
    public ArrayList<String> ex_name;
    public ArrayList<Integer> ex_img;
    public String select_ex_name;
    RecyclerView.LayoutManager layoutManager;

    Intent intent;
    TextView exName;
    Button next;
    ImageView back_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        back_btn=findViewById(R.id.back);
        exName = findViewById(R.id.exe_name);
        next = findViewById(R.id.next_btn);
        intent = getIntent();
        ex_name = new ArrayList<>();
        ex_img = new ArrayList<Integer>();
        select_ex_name = intent.getStringExtra("Excercise_name");
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(exerciseActivity.this,selectexerciseActivity.class);
                startActivity(intent);
                finish();
            }
        });

        if (select_ex_name.equals("biceps")) {
            exName.setText("Biceps");
            ex_name.add("Fat-Grip Hammer Curl");
            ex_img.add(R.drawable.bicpes_1);
            ex_name.add("EZ-Bar Preacher Curl");
            ex_img.add(R.drawable.biceps_2);
            ex_name.add("Reverse Curl");
            ex_img.add(R.drawable.biceps_3);
            ex_name.add("Wide-Grip Curl");
            ex_img.add(R.drawable.biceps_4);
            ex_name.add("Close-Grip Curl");
            ex_img.add(R.drawable.biceps_5);
            ex_name.add("Hammer Curl");
            ex_img.add(R.drawable.biceps_6);
            ex_name.add("Dip");
            ex_img.add(R.drawable.biceps_7);
            ex_name.add("Behind-the-Back Cable Curl");
            ex_img.add(R.drawable.biceps_8);
            ex_name.add("Side Curl with Band");
            ex_img.add(R.drawable.biceps_9);

        } else if (select_ex_name.equals("chest")) {
            exName.setText("Chest");
            ex_name.add("Barbell Bench Press");
            ex_img.add(R.drawable.chest_1);
            ex_name.add("Dumbbell Bench Press");
            ex_img.add(R.drawable.chest_2);
            ex_name.add("Smith Machine Incline Press");
            ex_img.add(R.drawable.chest_3);
            ex_name.add("Incline Dumbbell Flye");
            ex_img.add(R.drawable.chest_4);
            ex_name.add("Cable Crossover");
            ex_img.add(R.drawable.chest_5);
            ex_name.add("Incline Dumbbell Press");
            ex_img.add(R.drawable.chest_6);
            ex_name.add("Chest Press Machine");
            ex_img.add(R.drawable.chest_7);
            ex_name.add("Dumbbell Flye");
            ex_img.add(R.drawable.chest_8);
            ex_name.add("Low-Cable Crossover");
            ex_img.add(R.drawable.chest_9);
        } else if (select_ex_name.equals("back")) {
            exName.setText("Back");
            ex_name.add("Deadlift");
            ex_img.add(R.drawable.back_1);
            ex_name.add(" Incline Dumbbell Row");
            ex_img.add(R.drawable.back_2);
            ex_name.add("Trap-Bar Deadlift");
            ex_img.add(R.drawable.back_3);
            ex_name.add("Cable Row");
            ex_img.add(R.drawable.back_4);
            ex_name.add("Yates Row");
            ex_img.add(R.drawable.back_5);
            ex_name.add(" Bentover Row to Neck");
            ex_img.add(R.drawable.back_6);
            ex_name.add("Close-Grip Pull-Down");
            ex_img.add(R.drawable.back_7);
            ex_name.add(" Single-Arm Dumbbell Row");
            ex_img.add(R.drawable.back_8);


        } else if (select_ex_name.equals("triceps")) {
            exName.setText("Triceps");
            ex_name.add("Diamond Pushup");
            ex_img.add(R.drawable.tri_1);
            ex_name.add("Neutral-Grip Triceps Extension");
            ex_img.add(R.drawable.tri_2);
            ex_name.add("Reverse Curl");
            ex_img.add(R.drawable.tri_3);
            ex_name.add("Pullover/Triceps Extension");
            ex_img.add(R.drawable.tri_4);
            ex_name.add("Decline Triceps Extension");
            ex_img.add(R.drawable.tri_5);
            ex_name.add("Close-Grip Bench Press");
            ex_img.add(R.drawable.tri_6);
            ex_name.add(" Lying Triceps Extension");
            ex_img.add(R.drawable.tri_7);
            ex_name.add("Underhand Kickback");
            ex_img.add(R.drawable.tri_8);

        } else if (select_ex_name.equals("legs")) {
            exName.setText("Legs");
            ex_name.add("Leg Press");
            ex_img.add(R.drawable.legs1);
            ex_name.add("Pause Squat");
            ex_img.add(R.drawable.leg_2);
            ex_name.add("Reverse Lunge");
            ex_img.add(R.drawable.leg_3);
            ex_name.add("Dumbbell Squat");
            ex_img.add(R.drawable.leg_4);
            ex_name.add("Barbell Hip Thrust");
            ex_img.add(R.drawable.leg_5);
            ex_name.add("Seated Calf Raise");
            ex_img.add(R.drawable.leg_6);
            ex_name.add("Front Squat");
            ex_img.add(R.drawable.leg_7);
            ex_name.add("Romanian Deadlift");
            ex_img.add(R.drawable.leg_8);
            ex_name.add("Squart");
            ex_img.add(R.drawable.leg_9);
        } else if (select_ex_name.equals("shoulder")) {
            exName.setText("Shoulder");
            ex_name.add("Barbell Overhead Press");
            ex_img.add(R.drawable.shoulder_1);
            ex_name.add("Standing Dumbbell Fly");
            ex_img.add(R.drawable.shoulder_2);
            ex_name.add(" Face Pull");
            ex_img.add(R.drawable.shoulder_3);
            ex_name.add("High Pull");
            ex_img.add(R.drawable.shoulder_4);
            ex_name.add("Seated Dumbbell Clean");
            ex_img.add(R.drawable.shoulder_5);
            ex_name.add(" Clean and Press");
            ex_img.add(R.drawable.shoulder_6);
            ex_name.add("Snatch-Grip High Pull");
            ex_img.add(R.drawable.shoulder_7);
            ex_name.add("Machine Shoulder Press");
            ex_img.add(R.drawable.shoulder_8);
        }
        recycler_view = findViewById(R.id.recyclerView);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        adapter = new myAdapter(ex_name, ex_img,select_ex_name, exerciseActivity.this);
        recycler_view.setAdapter(adapter);
        next.setOnClickListener(myAdapter.myclick);
    }
}
