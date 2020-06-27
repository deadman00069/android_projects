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

public class MainActivity extends AppCompatActivity {

    EditText email,pass;
    Button login;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
         email=findViewById(R.id.email);
         pass=findViewById(R.id.pass);
         login=findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                on_Login();
            }
        });
}
    public void on_Login()
    {
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
        mAuth.signInWithEmailAndPassword(e_mail,passs).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful())
              {
                  Toast.makeText(MainActivity.this, "sign-in success", Toast.LENGTH_SHORT).show();
                  Intent intent = new Intent(MainActivity.this,select_exercise.class);
                  startActivity(intent);
              }
              else
              {
                  Toast.makeText(MainActivity.this, "sign-in fail ", Toast.LENGTH_SHORT).show();
              }
            }
        });
}

    public void sign_up(View view) {
        Intent intent=new Intent(this,sign_up.class);
        startActivity(intent);
        finish();
    }
    public void onforgot()
    {
        String e_mail=email.getText().toString().trim();
        if (e_mail.isEmpty())
        {
            Toast.makeText(this, "enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
                mAuth.sendPasswordResetEmail(e_mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(MainActivity.this, "password reset send success", Toast.LENGTH_SHORT).show();
                    }
                 }
            });
        }
    }

    public void forgot(View view) {
        onforgot();
    }
}
