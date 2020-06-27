package com.example.mygymapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class view extends AppCompatActivity {

    DatabaseReference myRef;
    FirebaseAuth auth;
    FirebaseUser user;
    String uid;
    String  list;
    TableLayout stk;
    ArrayList<String> max_weight;
    ArrayList<String> ex_name;
    String name;
    String mm;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Intent intent=getIntent();
        name=intent.getStringExtra("Excercise_name");
        Log.i("ff",""+name);
        back=findViewById(R.id.back);
        myRef = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        uid=user.getUid();
        stk =findViewById(R.id.t1);
        TableRow tr0=new TableRow(view.this);
        final TextView t0=new TextView(view.this);
        t0.setText(" Exercise");
        t0.setGravity(Gravity.CENTER);
        t0.setTextSize(20);
        tr0.addView(t0);
        final TextView t1=new TextView(view.this);
        t1.setText(" done");
        tr0.addView(t1);
        t1.setGravity(Gravity.CENTER);
        t1.setTextSize(20);
        TextView t2=new TextView(view.this);
        t2.setText(" max");
        tr0.addView(t2);
        t2.setGravity(Gravity.CENTER);
        t2.setTextSize(20);
        stk.addView(tr0);
        tablecall();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(view.this,select_exercise.class);
                startActivity(intent);
                finish();
            }
        });
    }


    public void tablecall()
    {
        myRef.child(uid).child("exercise").child(name).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {

                   max_weight = new ArrayList<>();
                    ex_name = new ArrayList<>();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Log.i("fd",""+ds.getChildrenCount());
                        Log.i("fdd",""+ds.getKey());

                       for (DataSnapshot dataSnapshot1:ds.getChildren())
                       {
                           mm=""+dataSnapshot1.getValue();
                           Log.i("fd1",""+dataSnapshot1.getValue());
                       }
                            ex_name.add("" + ds.getValue());
                            list = "" + ds.getKey();
                            TableRow tableRow = new TableRow(view.this);
                            TextView textView = new TextView(view.this);
                            TextView maxweight = new TextView(view.this);
                            Switch aSwitch = new Switch(view.this);
                            textView.setText("" + list);
                            textView.setGravity(Gravity.CENTER);
                            textView.setTextSize(20);
                            tableRow.addView(textView);
                            aSwitch.setGravity(Gravity.LEFT);
                            tableRow.addView(aSwitch);
                            maxweight.setText(""+mm);
                            maxweight.setGravity(Gravity.CENTER);
                           tableRow.addView(maxweight);
                           stk.addView(tableRow);
                           Log.i("arrlist",""+max_weight);
                        Log.i("arrlist",""+list);

                    }
                }
                else {
                    Toast.makeText(view.this, "please add exercise", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
