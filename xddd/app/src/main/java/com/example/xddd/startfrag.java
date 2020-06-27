package com.example.xddd;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class startfrag extends Fragment {
    public NavController navController;
    FirebaseAuth auth;

    public startfrag() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.startfrag, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        auth = FirebaseAuth.getInstance();
        Button home = view.findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_home_frag_to_profile_frag2);
            }
        });
        Button b = view.findViewById(R.id.logout);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent = new Intent(getContext(), loginActivity.class);
                startActivity(intent);
                Toast.makeText(getContext(), "Logout Success", Toast.LENGTH_SHORT).show();
            }
        });
        Button training = view.findViewById(R.id.training);
        training.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), selectexerciseActivity.class);
                startActivity(intent);
            }
        });
        Button stats = view.findViewById(R.id.training_statsBtn);
        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_home_frag_to_select_exercise_stats);
                //navController.navigate(R.id.action_home_frag_to_stats_Fragment);
            }
        });

    }
}
