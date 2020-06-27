package com.example.ui_test4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class view_maid_account extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference reference;
    String uid;
    int j;
    private ArrayList<fetch_detail> list;

    TextView name,adress,ph_no;
    String f_n ,ad ,ph;
    Switch aSwitch;
    String image;

    LinearLayout linearLayout;
    ConstraintLayout c;


    Spinner state, city, area;
    ImageView photo;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_maid_account);


        linearLayout=findViewById(R.id.linear_layout);
        c=findViewById(R.id.main_layout);
        reference = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        uid=user.getUid();
        photo=findViewById(R.id.photo);
        name = findViewById(R.id.f_name);
        adress = findViewById(R.id.address);
        ph_no = findViewById(R.id.phone_no);
        aSwitch=findViewById(R.id.switch1);
        state = findViewById(R.id.state_Spinner);
        city = findViewById(R.id.city_Spinner);
        area = findViewById(R.id.area_Spinner);

        ArrayList<String> state_list = new ArrayList<>();
        state_list.add("Select State");
        state_list.add("Haryana");
        state_list.add("soon");
        state_list.add("soon");
        state_list.add("soon");


        ArrayAdapter<String> state_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, state_list);
        state.setAdapter(state_Adapter);


        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Toast.makeText(view_maid_account.this, "Select State", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        ArrayList<String> city_list = new ArrayList<>();
                        city_list.add("select city");
                        city_list.add("Gurgaon");
                        city_list.add("Delhi");
                        city_list.add("Sonipat");
                        city_list.add("soon");
                        city_list.add("soon");
                        city_list.add("soon");
                        ArrayAdapter<String> city_Adapter = new ArrayAdapter<String>(view_maid_account.this, android.R.layout.simple_spinner_dropdown_item, city_list);
                        city.setAdapter(city_Adapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Toast.makeText(view_maid_account.this, "Select City", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        ArrayList<String> gurgaon_area_list = new ArrayList<>();
                        gurgaon_area_list.add("Select Area");
                        gurgaon_area_list.add("Sector_10");
                        gurgaon_area_list.add("Sector_10A");
                        gurgaon_area_list.add("Sector_9");
                        gurgaon_area_list.add("Sector_4");
                        gurgaon_area_list.add("Sector_5");
                        gurgaon_area_list.add("Basai");
                        ArrayAdapter<String> area_Adapter = new ArrayAdapter<String>(view_maid_account.this, android.R.layout.simple_spinner_dropdown_item, gurgaon_area_list);
                        area.setAdapter(area_Adapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                j=i;
                switch (i)
                {
                    case 1:
                        reference.child("maid").child("area").child("gurgaon").child("sector_10").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists())
                                {
                                    linearLayout.setVisibility(View.INVISIBLE);
                                    c.setVisibility(View.VISIBLE);
                                    reference.child("maid").child("area").child("gurgaon").child("sector_10").child(uid).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            String name1=dataSnapshot.child("name").getValue().toString();
                                            String address1=dataSnapshot.child("address").getValue().toString();
                                            String ph_no1=dataSnapshot.child("phone_no").getValue().toString();
                                            String status1=dataSnapshot.child("status").getValue().toString();
                                            String imgurl1=dataSnapshot.child("image").getValue().toString();
                                            image=imgurl1;

                                            Log.i("tag",""+name1);
                                            Log.i("tag",""+address1);
                                            Log.i("tag",""+ph_no1);
                                            Log.i("tag",""+status1);
                                            Picasso.get().load(imgurl1).fit().into(photo);
                                            name.setText(name1);
                                            adress.setText(address1);
                                            ph_no.setText(ph_no1);
                                            if(status1.equals("active"))
                                            {
                                                aSwitch.setChecked(true);
                                                aSwitch.setClickable(false);
                                            }
                                            else {
                                                aSwitch.setChecked(false);
                                                aSwitch.setClickable(false);
                                            }
                                        }
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                }
                                else {
                                    Toast.makeText(view_maid_account.this, "no record found", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(view_maid_account.this,maid_page.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        break;
                    case 2:
                        reference.child("maid").child("area").child("gurgaon").child("sector_10A").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                Log.i("key",""+dataSnapshot.getKey());
                                if (dataSnapshot.exists())
                                {
                                    Toast.makeText(view_maid_account.this, "record exist", Toast.LENGTH_SHORT).show();
                                    reference.child("maid").child("area").child("gurgaon").child("sector_10A").child(uid).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                            String name1=dataSnapshot.child("name").getValue().toString();
                                            String address1=dataSnapshot.child("address").getValue().toString();
                                            String ph_no1=dataSnapshot.child("phone_no").getValue().toString();
                                            String status1=dataSnapshot.child("status").getValue().toString();
                                            String imgurl1=dataSnapshot.child("image").getValue().toString();
                                            image=imgurl1;

                                            Log.i("tag",""+name1);
                                            Log.i("tag",""+address1);
                                            Log.i("tag",""+ph_no1);
                                            Log.i("tag",""+status1);
                                            name.setText(name1);
                                            adress.setText(address1);
                                            ph_no.setText(ph_no1);
                                            if(status1.equals("active"))
                                            {
                                                aSwitch.setChecked(true);
                                            }
                                            else {
                                                aSwitch.setChecked(false);
                                            }
                                        }
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                        }
                                    });
                                }
                                else {
                                    Toast.makeText(view_maid_account.this, "no record found", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(view_maid_account.this,maid_page.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void back(View view) {
        Intent intent = new Intent(this,maid_page.class);
        startActivity(intent);
        finish();
    }
}
