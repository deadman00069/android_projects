package com.example.mygymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class select_exercise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_exercise); }
    public void buttonClicked(View view) {

        if (view.getId() == R.id.shouder) {
            Intent intent = new Intent(this,shoulder.class);
            intent.putExtra("Excercise_name","SHOULDER");
            startActivity(intent);
        } else if (view.getId() == R.id.bi_tri) {
            Intent intent = new Intent(this,shoulder.class);
            intent.putExtra("Excercise_name","BI/TRI");
            startActivity(intent);
        } else if (view.getId() == R.id.chest) {
            Intent intent = new Intent(this,shoulder.class);
            intent.putExtra("Excercise_name","CHEST");
            startActivity(intent);
        }
        else if (view.getId() == R.id.legs) {
            Intent intent = new Intent(this,shoulder.class);
            intent.putExtra("Excercise_name","LEGS");
            startActivity(intent);
        }
        else if (view.getId() == R.id.back) {
            Intent intent = new Intent(this,shoulder.class);
            intent.putExtra("Excercise_name","BACK");
            startActivity(intent);
        }

    }
}
