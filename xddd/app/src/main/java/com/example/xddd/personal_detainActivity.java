package com.example.xddd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class personal_detainActivity extends AppCompatActivity {

    DatabaseReference myRef;
    FirebaseAuth auth;
    FirebaseUser user;
    String uid;
    Button submit;
    EditText nameET, bDayET, heightET, weightET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_detain);
        myRef = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        uid = user.getUid();
        Log.i("f",""+uid);
        nameET = findViewById(R.id.nameText);
        bDayET = findViewById(R.id.birthdayText);
        heightET = findViewById(R.id.heightText);
        weightET = findViewById(R.id.weightText);
        submit = findViewById(R.id.finishBtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=nameET.getText().toString().trim();
                String bDay=bDayET.getText().toString().trim();
                String height=heightET.getText().toString().trim();
                String weight=weightET.getText().toString().trim();
                if (name.isEmpty() || bDay.isEmpty() || height.isEmpty() || weight.isEmpty())
                {
                    Toast.makeText(personal_detainActivity.this, "fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                info in=new info(name,bDay,height,weight);
                myRef.child(uid).child("profile").setValue(in).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(personal_detainActivity.this, "Submit success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(personal_detainActivity.this,homeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });

    }
}
