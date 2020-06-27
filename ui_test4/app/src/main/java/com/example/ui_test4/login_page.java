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
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

public class login_page extends AppCompatActivity {
    EditText et_Email, et_pass;
    ImageView im_login;
    TextView tv_signup;
    FirebaseAuth mauth;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    String button,button1,button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mauth=FirebaseAuth.getInstance();

        AuthCredential credential = EmailAuthProvider.getCredential("user@example.com", "password1234");

        tv_signup=findViewById(R.id.register_textView);
        setContentView(R.layout.activity_login_page);
        et_Email = findViewById(R.id.email_editText);
        et_pass = findViewById(R.id.password_editText);
        im_login = findViewById(R.id.login_Button);
        Intent intent = getIntent();
         button= intent.getStringExtra("button");
         Log.i("taag",""+button);

        im_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(buttonClick);
                on_Login();
            }
        });

    }

    private void on_Login() {
        String em=et_Email.getText().toString().trim();
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
        mauth.signInWithEmailAndPassword(em,ps).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful()) {
                  if (button.equals("1")) {
                      Toast.makeText(login_page.this, "sign in success", Toast.LENGTH_SHORT).show();
                      Intent intent = new Intent(login_page.this, add_details_page.class);
                      startActivity(intent);
                  } else if (button.equals("2")) {
                      Toast.makeText(login_page.this, "sign in success", Toast.LENGTH_SHORT).show();
                      Intent intent = new Intent(login_page.this, customer.class);
                      startActivity(intent);

                  } else if (button.equals("3")) {
                      Toast.makeText(login_page.this, "sign in success", Toast.LENGTH_SHORT).show();
                      Intent intent = new Intent(login_page.this, maid_page.class);
                      startActivity(intent);

                  }
              }
              else {
                  Toast.makeText(login_page.this, "fail", Toast.LENGTH_SHORT).show();
              }
            }
        });

    }

    public void register(View view) {

                Intent intent=new Intent(login_page.this,signup_page.class);
                startActivity(intent);
            }

}
