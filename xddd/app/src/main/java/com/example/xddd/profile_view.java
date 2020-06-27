package com.example.xddd;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class profile_view extends Fragment {
    DatabaseReference ref;
    FirebaseAuth auth;
    FirebaseUser user;
    String uid;
    ProgressBar progressBar;
    ImageView back;
    NavController navController;
    public profile_view() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_view, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController= Navigation.findNavController(view);
        back=view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_profile_view_to_profile_frag2);}
        });
        ref = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        uid = user.getUid();
        progressBar = view.findViewById(R.id.pro);

        final EditText et1 = view.findViewById(R.id.nameText);
        final EditText et2 = view.findViewById(R.id.birthdayText);
        final EditText e3 = view.findViewById(R.id.heightText);
        final EditText e4 = view.findViewById(R.id.weightText);
        ref.child(uid).child("profile").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists())
                {
                    Toast.makeText(getContext(), "no data found", Toast.LENGTH_SHORT).show();
                }
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    if (dataSnapshot1.getKey().equals("bDay")) {
                        et2.setText("" + dataSnapshot1.getValue());
                    }
                    if (dataSnapshot1.getKey().equals("name")) {
                        et1.setText("" + dataSnapshot1.getValue());
                    }
                    if (dataSnapshot1.getKey().equals("height")) {
                        e3.setText("" + dataSnapshot1.getValue());
                    }
                    if (dataSnapshot1.getKey().equals("weight")) {
                        e4.setText("" + dataSnapshot1.getValue());
                    }

                    Log.i("lol", "" + dataSnapshot1.getKey());
                }
                progressBar.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
