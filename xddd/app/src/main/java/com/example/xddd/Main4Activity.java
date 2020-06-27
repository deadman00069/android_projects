package com.example.xddd;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main4Activity extends AppCompatActivity {

    Intent intent;
    ArrayList<String> names;
    public RecyclerView recycler_view;
    public RecyclerView.Adapter adapter2;
    Button submit;
    String which_ex;
    ImageView back_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        back_btn = findViewById(R.id.backBtn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        intent = getIntent();
        names = intent.getStringArrayListExtra("yyy");
        which_ex = intent.getStringExtra("which_ex");
        Log.i("fff", "" + which_ex);
        submit = findViewById(R.id.submit_btn);
        submit.setOnClickListener(myAdapter2.myclick);
        Log.i("ff", "" + intent.getStringArrayListExtra("yyy"));
        recycler_view = findViewById(R.id.recyclerView2);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        adapter2 = new myAdapter2(names, which_ex, Main4Activity.this);
        recycler_view.setAdapter(adapter2);
        adapter2.notifyDataSetChanged();
    }
}
