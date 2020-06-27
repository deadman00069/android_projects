package com.example.ui_test4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup_page extends AppCompatActivity {
    ImageView iv_signup;
    EditText et_email, et_pass;
    TextView tv_login;
    FirebaseAuth mauth;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mauth=FirebaseAuth.getInstance();
        tv_login=findViewById(R.id.login_textView);
        setContentView(R.layout.activity_signup_page);
            et_email = findViewById(R.id.email_editText);
            et_pass = findViewById(R.id.password_edittext);
            iv_signup = findViewById(R.id.signup_button);
    }

    private void on_Signup() {
        String em=et_email.getText().toString().trim();
        String ps=et_pass.getText().toString().trim();
        Log.i("email ",""+em);
        Log.i("password",""+ps);
        if(em.isEmpty())
        {
            Toast.makeText(this, "enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(ps.isEmpty())
        {
            Toast.makeText(this, "enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        mauth.createUserWithEmailAndPassword(em,ps).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(signup_page.this, "signup success", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(signup_page.this,login_page.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(signup_page.this, "fail", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void login(View view) {
        Intent intent=new Intent(signup_page.this,login_page.class);
        startActivity(intent);

    }

    public void sign__up(View view) {

        view.startAnimation(buttonClick);
        on_Signup();
    }
}
