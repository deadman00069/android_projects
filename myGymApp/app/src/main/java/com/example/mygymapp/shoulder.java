package com.example.mygymapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class shoulder extends AppCompatActivity {
    DatabaseReference myRef;
    FirebaseAuth auth;
    FirebaseUser user;
    String uid;
    String list;
    long count;
    TableLayout stk;
    Button addButton, add, back, submit, del, view;
    EditText editText;
    RelativeLayout relativeLayout;
    ArrayList<EditText> editTexts;
    ArrayList<String> ex_name;
    Date c = Calendar.getInstance().getTime();
    private SimpleDateFormat dateFormat;
    private String date;
    TextView tv;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoulder);
        final Intent intent = getIntent();
        name = intent.getStringExtra("Excercise_name");
        tv = findViewById(R.id.txt);
        tv.setText(name);
        addButton = findViewById(R.id.add_button);
        add = findViewById(R.id.add);
        back = findViewById(R.id.back);
        submit = findViewById(R.id.submit);
        del = findViewById(R.id.delete);
        view = findViewById(R.id.viewmax);
        editText = findViewById(R.id.addExercise);
        relativeLayout = findViewById(R.id.r2);

        dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        date = dateFormat.format(c);
        myRef = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        uid = user.getUid();
        stk = findViewById(R.id.t1);
        TableRow tr0 = new TableRow(shoulder.this);
        final TextView t0 = new TextView(shoulder.this);
        t0.setText(" Exercise");
        t0.setGravity(Gravity.CENTER);
        t0.setTextSize(20);
        tr0.addView(t0);
        final TextView t1 = new TextView(shoulder.this);
        t1.setText(" done");
        tr0.addView(t1);
        t1.setGravity(Gravity.CENTER);
        t1.setTextSize(20);
        TextView t2 = new TextView(shoulder.this);
        t2.setText(" max");
        tr0.addView(t2);
        t2.setGravity(Gravity.CENTER);
        t2.setTextSize(20);
        stk.addView(tr0);
        tablecall();
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setVisibility(View.INVISIBLE);
                editText.setVisibility(View.VISIBLE);
                add.setText("Add");
                add.setVisibility(View.VISIBLE);
                back.setVisibility(View.VISIBLE);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String getname = editText.getText().toString().trim();
                if (getname.isEmpty()) {
                    Toast.makeText(shoulder.this, "enter name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (add.getText().equals("Add")) {
                    myRef.child(uid).child("exercise_name").child(name).child("name").push().child("ex_name").setValue(getname);
                    relativeLayout.setVisibility(View.VISIBLE);
                    editText.setVisibility(View.INVISIBLE);
                    add.setVisibility(View.INVISIBLE);
                    back.setVisibility(View.INVISIBLE);
                    String l = "" + count;
                    int i = Integer.parseInt(l);
                    stk.removeViews(1, i);
                } else {
                    myRef.child(uid).child("exercise_name").child(name).child("name").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                if (ds.child("ex_name").getValue(String.class).equals(getname)) {
                                    ds.child("ex_name").getRef().removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(shoulder.this, "delete success", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(shoulder.this, "delete fail", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                    relativeLayout.setVisibility(View.VISIBLE);
                                    editText.setVisibility(View.INVISIBLE);
                                    add.setVisibility(View.INVISIBLE);
                                    back.setVisibility(View.INVISIBLE);
                                    String l = "" + count;
                                    int i = Integer.parseInt(l);
                                    stk.removeViews(1, i);
                                }
                            }
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    myRef.child(uid)
                            .child("exercise")
                            .child(name).child(getname).child("weight").setValue(null);
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setVisibility(View.VISIBLE);
                editText.setVisibility(View.INVISIBLE);
                add.setVisibility(View.INVISIBLE);
                back.setVisibility(View.INVISIBLE);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i <= count - 1; i++) {
                    String s = ex_name.get(i);
                    EditText et = editTexts.get(i);
                    String maxweight = et.getText().toString();
                    Log.i("tt", "" + s);
                    if (maxweight.isEmpty() || maxweight.equals("")) {
                        Toast.makeText(shoulder.this, "enter weight", Toast.LENGTH_SHORT).show();
                    } else {


                        myRef.child(uid).child("exercise").child(name).child(s).child("weight").setValue(maxweight).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(shoulder.this, "submit success", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                relativeLayout.setVisibility(View.INVISIBLE);
                editText.setVisibility(View.VISIBLE);
                add.setText("DELETE");
                add.setVisibility(View.VISIBLE);
                back.setVisibility(View.VISIBLE);

            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(shoulder.this, view.class);
                intent.putExtra("Excercise_name", name);
                startActivity(intent);
                finish();
            }
        });

    }

    public void tablecall() {
        myRef.child(uid).child("exercise_name").child(name).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    editTexts = new ArrayList<>();
                    ex_name = new ArrayList<>();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        count = ds.getChildrenCount();
                        for (DataSnapshot ds1 : ds.getChildren()) {
                            //Log.i("hh",""+ds1.child("ex_name").getValue());
                            ex_name.add("" + ds1.child("ex_name").getValue());
                            list = "" + ds1.child("ex_name").getValue();
                            TableRow tableRow = new TableRow(shoulder.this);
                            TextView textView = new TextView(shoulder.this);
                            EditText editText = new EditText(shoulder.this);
                            Switch aSwitch = new Switch(shoulder.this);
                            editTexts.add(editText);
                            textView.setText("" + list);
                            textView.setGravity(Gravity.CENTER);
                            textView.setTextSize(20);
                            tableRow.addView(textView);
                            aSwitch.setGravity(Gravity.LEFT);
                            tableRow.addView(aSwitch);
                            editText.setGravity(Gravity.CENTER);
                            editText.setBackground(null);
                            tableRow.addView(editText);
                            stk.addView(tableRow);

                        }
                    }
                } else {
                    Toast.makeText(shoulder.this, "please add exercise", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
