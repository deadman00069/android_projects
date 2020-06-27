package com.example.mygymapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class sign_up extends AppCompatActivity {

    EditText email,pass;
    Button sign_up;
    private FirebaseAuth mAuth;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        sign_up=findViewById(R.id.signup);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                on_signup();
            }
        });
    }

    private void on_signup() {
        String e_mail=email.getText().toString().trim();
        String passs=pass.getText().toString().trim();
        if(e_mail.equals(""))
        {
            Toast.makeText(this, "enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (passs.equals(""))
        {
            Toast.makeText(this, "enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(e_mail,passs).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(sign_up.this, "signup success", Toast.LENGTH_SHORT).show();
                    intent=new Intent(sign_up.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                    {
                        Toast.makeText(sign_up.this, "signup fail", Toast.LENGTH_SHORT).show();
                    }
            }
        });

    }

    public void log_in(View view) {
        intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
