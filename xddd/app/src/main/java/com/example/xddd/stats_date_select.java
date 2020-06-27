package com.example.xddd;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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
import java.util.HashMap;


public class stats_date_select extends Fragment {

    DatabaseReference reference;
    FirebaseUser user;
    FirebaseAuth auth;
    String uid;
    String ex_name;
    ProgressBar progressBar;
    LinearLayout linearLayout;
    ArrayList<String> max_Weight;
    ArrayList<String> ex_Name;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RelativeLayout r1, r2;


    public stats_date_select() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats_date_select, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        r1 = view.findViewById(R.id.r1);
        r2 = view.findViewById(R.id.r2);
        linearLayout = view.findViewById(R.id.linear1);
        progressBar = view.findViewById(R.id.progress_bar);
        reference = FirebaseDatabase.getInstance().getReference();
        recyclerView = view.findViewById(R.id.recyclerView3);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Fragment parentFragment = getParentFragment();
        Bundle parentArgs = parentFragment.getArguments();
        if (parentArgs != null) {
            training_stat_selectArgs args = training_stat_selectArgs.fromBundle(getArguments());
            ex_name = args.getEName();
            date();

        } else {
            Toast.makeText(getContext(), "null", Toast.LENGTH_SHORT).show();
        }

    }

    public void date() {
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        uid = user.getUid();
        progressBar.setVisibility(View.VISIBLE);
        reference.child(uid).child(ex_name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (final DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        Log.i("data", "" + dataSnapshot1.getKey());
                        final Button button = new Button(getContext());
                        int id = button.getId();
                        final String date__ = dataSnapshot1.getKey();
                        button.setTag(date__);
                        Log.i("id", "" + id);
                        button.setText("" + dataSnapshot1.getKey());
                        button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                        linearLayout.addView(button);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (date__.equals(dataSnapshot1.getKey())) {
                                    Toast.makeText(getContext(), "tooooooo", Toast.LENGTH_SHORT).show();
                                    r1.setVisibility(View.INVISIBLE);
                                    r2.setVisibility(View.VISIBLE);
                                    data1(date__);
                                }
                            }
                        });
                        progressBar.setVisibility(View.INVISIBLE);
                    }

                } else {
                    Toast.makeText(getContext(), "no data available", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    return;


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void data1(String date) {

        HashMap<Integer, String> e = new HashMap<>();
        if (getArguments() != null) {
            ex_Name = new ArrayList<>();
            max_Weight = new ArrayList<>();
            user = auth.getCurrentUser();
            uid = user.getUid();
            reference.child(uid).child(ex_name).child(date).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        ex_Name.add(ds.getKey());
                        max_Weight.add(ds.getValue().toString());
                    }
                    adapter = new myAdapter3(ex_Name, max_Weight);
                    recyclerView.setAdapter(adapter);
                    Log.i("daata", "" + ex_Name);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }
    }
}
