package com.example.xddd;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.BatchUpdateException;

public class stats_Fragment extends Fragment {
    DatabaseReference reference;
    FirebaseUser user;
    FirebaseAuth auth;
    String uid;
    LinearLayout l2;
    ProgressBar progressBar;
    String ex_name;
    NavController navController;


    public stats_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController= Navigation.findNavController(view);
        final LinearLayout l1 = view.findViewById(R.id.linear1);
        progressBar = view.findViewById(R.id.progress_bar);
        l2 = view.findViewById(R.id.linear2);
        final Button chest = view.findViewById(R.id.chest_btn);

        chest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l1.setVisibility(View.INVISIBLE);
                l2.setVisibility(View.VISIBLE);
                ex_name="chest";
                date();

            }
        });

    }

    public void date() {
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        uid = user.getUid();
        reference = FirebaseDatabase.getInstance().getReference();
        progressBar.setVisibility(View.VISIBLE);
        reference.child(uid).child("chest").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (final DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.i("data", "" + dataSnapshot1.getKey());
                    final Button button = new Button(getContext());
                    int id = button.getId();
                    final String tag = dataSnapshot1.getKey();
                    button.setTag(tag);
                    Log.i("id", "" + id);
                    button.setText("" + dataSnapshot1.getKey());
                    button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    l2.addView(button);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (tag.equals(dataSnapshot1.getKey())) {
//                                training_stat_selectDirections.ActionTrainingStatSelectToViewStats action=training_stat_selectDirections.actionTrainingStatSelectToViewStats();
//                                action.setExName(ex_name);
//                                action.setDate(tag);
//                                navController.navigate(action);
                            }
                        }
                    });
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


}
