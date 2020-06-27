package com.example.ui_test4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class login_select extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_select);


    }

    public void admin_login(View view) {
        Intent intent = new Intent(this,login_page.class);
        intent.putExtra("button","1");
        startActivity(intent);
    }

    public void customer_login(View view) {
        Intent intent = new Intent(this,login_page.class);
        intent.putExtra("button","2");
        startActivity(intent);
    }

    public void maid_login(View view) {
        Intent intent = new Intent(this,login_page.class);
        intent.putExtra("button","3");
        startActivity(intent);
    }
}
