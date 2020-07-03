package com.example.my_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class score extends AppCompatActivity {
    Button score_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        score_button=findViewById(R.id.score_display);
        String score=getIntent().getStringExtra("Score is");
        score_button.setText(score);
        Log.i("score ",""+score);

    }
    
    public void close(View view) {
        Intent intent=new Intent(this,select_page.class);
        startActivity(intent);
        finish();
    }

    public void again(View view) {
        Intent intent=new Intent(this,select_Quickbrain.class);
        startActivity(intent);
        finish();
    }
}
