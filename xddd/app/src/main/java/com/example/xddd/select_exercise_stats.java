package com.example.xddd;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class select_exercise_stats extends Fragment {
    NavController navController;
    Button chest;
    Button biceps;
    Button back;
    Button triceps;
    Button legs;
    Button shoulder;

    public select_exercise_stats() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_exercise_stats, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chest = view.findViewById(R.id.chest_btn);
        biceps=view.findViewById(R.id.biceps_btn);
        back=view.findViewById(R.id.back_btn);
        triceps=view.findViewById(R.id.triceps_btn);
        legs=view.findViewById(R.id.legs_btn);
        shoulder=view.findViewById(R.id.shoulde_btn);
        navController= Navigation.findNavController(view);
        chest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_exercise_statsDirections.ActionSelectExerciseStatsToTrainingStatSelect action=select_exercise_statsDirections.actionSelectExerciseStatsToTrainingStatSelect();
                action.setEName("chest");
                navController.navigate(action);
            }
        });
        biceps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_exercise_statsDirections.ActionSelectExerciseStatsToTrainingStatSelect action=select_exercise_statsDirections.actionSelectExerciseStatsToTrainingStatSelect();
                action.setEName("biceps");
                navController.navigate(action);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_exercise_statsDirections.ActionSelectExerciseStatsToTrainingStatSelect action=select_exercise_statsDirections.actionSelectExerciseStatsToTrainingStatSelect();
                action.setEName("back");
                navController.navigate(action);
            }
        });

        triceps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_exercise_statsDirections.ActionSelectExerciseStatsToTrainingStatSelect action=select_exercise_statsDirections.actionSelectExerciseStatsToTrainingStatSelect();
                action.setEName("triceps");
                navController.navigate(action);
            }
        });
        legs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_exercise_statsDirections.ActionSelectExerciseStatsToTrainingStatSelect action=select_exercise_statsDirections.actionSelectExerciseStatsToTrainingStatSelect();
                action.setEName("legs");
                navController.navigate(action);
            }
        });
        shoulder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_exercise_statsDirections.ActionSelectExerciseStatsToTrainingStatSelect action=select_exercise_statsDirections.actionSelectExerciseStatsToTrainingStatSelect();
                action.setEName("shoulder");
                navController.navigate(action);
            }
        });

    }
}
