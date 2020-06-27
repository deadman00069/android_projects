package com.example.xddd;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.datepicker.MaterialTextInputPicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

public class registerActivity extends AppCompatActivity {

    TextInputEditText emailTextView;
    TextInputEditText passTextView;
    TextInputEditText confirm_passTextView;
    TextView already;
    FirebaseAuth auth;
    Button registerButton;
    int token = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth = FirebaseAuth.getInstance();
        emailTextView = findViewById(R.id.emailInput);
        passTextView = findViewById(R.id.passwordInput);
        confirm_passTextView = findViewById(R.id.confirm_passwordInput);
        registerButton = findViewById(R.id.registerBtn);
        already=findViewById(R.id.already);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email;
                String pass;
                email = emailTextView.getText().toString().trim();
                pass = passTextView.getText().toString().trim();
                String confPass = confirm_passTextView.getText().toString().trim();
                if (email.isEmpty()) {
                    Toast.makeText(registerActivity.this, "Fill Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pass.isEmpty()) {
                    Toast.makeText(registerActivity.this, "Fill Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!pass.equals(confPass)) {
                    Toast.makeText(registerActivity.this, "password not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.fetchSignInMethodsForEmail(email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        // Log.d(TAG,""+task.getResult().getSignInMethods().size());
                        if (task.getResult().getSignInMethods().size() == 0) {
                            token = 1;

                        } else {
                            Toast.makeText(registerActivity.this, "email exist", Toast.LENGTH_SHORT).show();
                            token = 0;
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.printStackTrace();
                    }
                });

                if (token == 1) {
                    auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(registerActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(registerActivity.this, personal_detainActivity.class);
                                startActivity(intent);
                                SharedPreferences preps=getSharedPreferences("checkbox",MODE_PRIVATE);
                                SharedPreferences.Editor editor=preps.edit();
                                editor.putBoolean("first",true);
                                editor.apply();
                                finish();
                            } else {
                                task.getException();
                                Toast.makeText(registerActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(registerActivity.this, loginActivity.class);
                startActivity(intent);
            }
        });
    }


}
