package com.example.xddd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class selectexerciseActivity extends AppCompatActivity {

    Button button_chest, button_biceps,button_back,button_triceps,button_leg,button_shoulder;
    ImageView back_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectexercise);

        button_chest = findViewById(R.id.btn1);
        button_biceps = findViewById(R.id.btn2);
        button_back=findViewById(R.id.btn3);
        button_triceps=findViewById(R.id.btn4);
        button_leg=findViewById(R.id.btn5);
        button_shoulder=findViewById(R.id.btn6);
        back_click=findViewById(R.id.back);
        back_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(selectexerciseActivity.this,startfrag.class);
//                startActivity(intent);
//                finish();
               onBackPressed();
            }
        });
        button_chest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(selectexerciseActivity.this, exerciseActivity.class);
                Toast.makeText(selectexerciseActivity.this, "chest", Toast.LENGTH_SHORT).show();
                intent.putExtra("Excercise_name", "chest");
                startActivity(intent);
                finish();

            }
        });
        button_biceps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(selectexerciseActivity.this, exerciseActivity.class);
                intent.putExtra("Excercise_name", "biceps");
                startActivity(intent);
            }
        });
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(selectexerciseActivity.this,exerciseActivity.class);
                intent.putExtra("Excercise_name","back");
                startActivity(intent);

            }
        });
        button_triceps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(selectexerciseActivity.this,exerciseActivity.class);
                intent.putExtra("Excercise_name","triceps");
                startActivity(intent);

            }
        });
        button_leg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(selectexerciseActivity.this,exerciseActivity.class);
                intent.putExtra("Excercise_name","legs");
                startActivity(intent);
            }
        });
        button_shoulder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(selectexerciseActivity.this,exerciseActivity.class);
                intent.putExtra("Excercise_name","shoulder");
                startActivity(intent);

            }
        });

    }
}
