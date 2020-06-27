package com.example.ui_test4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentManager.OnBackStackChangedListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class customer extends AppCompatActivity {

    RelativeLayout ui, recycler;
    Spinner state, city, area;

    private RecyclerView recycler_view;
    private RecyclerView.Adapter aadapter;
    private ArrayList<fetch_detail> list;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        ui = findViewById(R.id.ui_Relativelayout);
        recycler = findViewById(R.id.recyclerview_Layout);
        state = findViewById(R.id.state_Spinner);
        city = findViewById(R.id.city_Spinner);
        area = findViewById(R.id.area_Spinner);


        recycler_view = findViewById(R.id.recycler_vieew);
        recycler_view.setHasFixedSize(true);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<fetch_detail>();

        ArrayList<String> state_list = new ArrayList<>();
        state_list.add("Select State");
        state_list.add("Haryana");
        state_list.add("soon");
        state_list.add("soon");
        state_list.add("soon");


        ArrayAdapter<String> state_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, state_list);
        state.setAdapter(state_Adapter);


        state.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Toast.makeText(customer.this, "Select State", Toast.LENGTH_SHORT).show();
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
                        ArrayAdapter<String> city_Adapter = new ArrayAdapter<String>(customer.this, android.R.layout.simple_spinner_dropdown_item, city_list);
                        city.setAdapter(city_Adapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        city.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Toast.makeText(customer.this, "Select City", Toast.LENGTH_SHORT).show();
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
                        ArrayAdapter<String> area_Adapter = new ArrayAdapter<String>(customer.this, android.R.layout.simple_spinner_dropdown_item, gurgaon_area_list);
                        area.setAdapter(area_Adapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        area.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Toast.makeText(customer.this, "Select Area", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        ui.setVisibility(View.INVISIBLE);
                        recycler.setVisibility(View.VISIBLE);
                        reference = FirebaseDatabase.getInstance().getReference().child("maid").child("area").child("gurgaon").child("sector_10");
                        reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                    fetch_detail p = dataSnapshot1.getValue(fetch_detail.class);
                                    list.add(p);
                                }
                                aadapter = new MyAdapter(customer.this, list);
                                recycler_view.setAdapter(aadapter);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(customer.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case 2:
                        ui.setVisibility(View.INVISIBLE);
                        recycler.setVisibility(View.VISIBLE);
                        reference = FirebaseDatabase.getInstance().getReference().child("maid").child("area").child("gurgaon").child("sector_10A");
                        reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                    fetch_detail p = dataSnapshot1.getValue(fetch_detail.class);
                                    list.add(p);
                                }
                                aadapter = new MyAdapter(customer.this, list);
                                recycler_view.setAdapter(aadapter);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(customer.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;   //sector_9
                    case 3:
                        ui.setVisibility(View.INVISIBLE);
                        recycler.setVisibility(View.VISIBLE);
                        reference = FirebaseDatabase.getInstance().getReference().child("maid").child("area").child("gurgaon").child("sector_9");
                        reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                    fetch_detail p = dataSnapshot1.getValue(fetch_detail.class);
                                    list.add(p);
                                }
                                aadapter = new MyAdapter(customer.this, list);
                                recycler_view.setAdapter(aadapter);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(customer.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
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

}
