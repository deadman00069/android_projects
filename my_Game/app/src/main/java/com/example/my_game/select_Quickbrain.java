package com.example.my_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;

public class select_Quickbrain extends AppCompatActivity {
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__quickbrain);
    }

    public void quick_match(View view) {
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this,quick_Brain.class);
        startActivity(intent);
    }

    public void true_false(View view) {
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }
}
