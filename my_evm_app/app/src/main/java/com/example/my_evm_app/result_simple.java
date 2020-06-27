package com.example.my_evm_app;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class result_simple extends Fragment {
    DatabaseReference ref;
    TableLayout tabLayout;

    public result_simple() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result_simple, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout = view.findViewById(R.id.tableLayout);
        TableRow tableRow = new TableRow(getContext());
        TextView textView = new TextView(getContext());
        TextView textView1 = new TextView(getContext());
        textView.setText("Party");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(20);
        textView1.setText("Count");
        textView1.setGravity(Gravity.CENTER);
        textView1.setTextSize(20);
        tableRow.setBackgroundColor(Color.rgb(132, 232, 159));
        tableRow.addView(textView);
        tableRow.addView(textView1);
        tabLayout.addView(tableRow);
        ref = FirebaseDatabase.getInstance().getReference();
        ref.child("vote").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    TableRow tableRow = new TableRow(getContext());
                    TextView textView = new TextView(getContext());
                    TextView textView1 = new TextView(getContext());
                    textView.setText("" + ds.getKey());
                    textView.setGravity(Gravity.CENTER);
                    textView.setTextSize(20);
                    textView1.setText("" + ds.child("count").getValue());
                    textView1.setGravity(Gravity.CENTER);
                    textView1.setTextSize(20);
                    TableLayout.LayoutParams lp =
                            new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                                    TableLayout.LayoutParams.WRAP_CONTENT);

                    lp.setMargins(10,30,10,10);
                    tableRow.setLayoutParams(lp);
                    tableRow.addView(textView);
                    tableRow.addView(textView1);
                    tabLayout.addView(tableRow);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
