package com.example.my_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;

public class select_page extends AppCompatActivity {
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_page);
    }

    public void quick_Brain(View view) {
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this,select_Quickbrain.class);
        startActivity(intent);
        finish();
    }

    public void connect_3(View view) {
        view.startAnimation(buttonClick);
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this,connect_3.class);
        startActivity(intent);
        finish();
        //Intent intent = new Intent(this,)
    }
}
