package com.example.xddd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class welcome extends AppCompatActivity {

    Button start;
    Boolean log_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        SharedPreferences preps = getSharedPreferences("checkbox", MODE_PRIVATE);
        log_detail=preps.getBoolean("first",false);
        if (log_detail) {
            Intent intent = new Intent(welcome.this, loginActivity.class);
            startActivity(intent);
            finish();
        }
        start = findViewById(R.id.start_btn);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(welcome.this, registerActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

}
