package com.example.xddd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login_emailActivity extends AppCompatActivity {

    Button loginbtn;
    TextInputEditText emailTextView;
    TextInputEditText passTextView;
    CheckBox checkBox;
    FirebaseAuth auth;

   //Boolean log_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_email);
        auth = FirebaseAuth.getInstance();
        emailTextView = findViewById(R.id.emailInput);
        passTextView = findViewById(R.id.passwordInput);
        checkBox = findViewById(R.id.chechbox);
        loginbtn = findViewById(R.id.loginBtn);
        // SharedPreferences preps=getSharedPreferences("checkbox",MODE_PRIVATE);
        //log_detail= preps.getBoolean("remember",false);
//        if (log_detail) {
//            Intent intent = new Intent(login_emailActivity.this, homeActivity.class);
//            startActivity(intent);
//            finish();
//        }
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email;
                String pass;
                email = emailTextView.getText().toString().trim();
                pass = passTextView.getText().toString().trim();
                if (email.isEmpty()) {
                    Toast.makeText(login_emailActivity.this, "Fill Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pass.isEmpty()) {
                    Toast.makeText(login_emailActivity.this, "Fill Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(login_emailActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(login_emailActivity.this, homeActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(login_emailActivity.this, "Login fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {

                } else if (!buttonView.isChecked()) {
//                    SharedPreferences preps=getSharedPreferences("checkbox",MODE_PRIVATE);
//                    SharedPreferences.Editor editor=preps.edit();
//                    editor.putBoolean("remember",false);
//                    editor.apply();

                }
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser!=null)
        {
            Intent intent = new Intent(login_emailActivity.this, homeActivity.class);
            startActivity(intent);

        }
    }
}