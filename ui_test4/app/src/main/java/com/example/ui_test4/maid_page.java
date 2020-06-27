package com.example.ui_test4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class maid_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maid_page);
    }

    public void view_acc(View view) {
       Intent intent = new Intent(this,view_maid_account.class);
        startActivity(intent);

    }

    public void update(View view) {

        Intent intent = new Intent(this,update_Buttoon.class);
        startActivity(intent);
    }

}
