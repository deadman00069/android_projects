package com.example.my_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class quick_Brain extends AppCompatActivity {
    ImageView easy,med,hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick__brain);
        easy=findViewById(R.id.imageView2);
        med=findViewById(R.id.imageView3);
        hard=findViewById(R.id.imageView13);
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(quick_Brain.this,quick_Match.class);
                startActivity(in);
                finish();
            }
        });
        med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(quick_Brain.this,medium__.class);
                startActivity(in);
                finish();
            }
        });
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(quick_Brain.this,hard__.class);
                startActivity(in);
            }
        });
    }
}
