package com.example.my_evm_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class home extends Fragment {
    ImageView form, close_btn, result_btn;
    FrameLayout form_click;
    CardView cardView;
    NavController navController;

    public home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        form = view.findViewById(R.id.imageView2);
        result_btn = view.findViewById(R.id.imageView4);
        form_click = view.findViewById(R.id.frameLayout2);
        close_btn = view.findViewById(R.id.close_btn);
        cardView = view.findViewById(R.id.cd1);
        form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                form_click.setVisibility(View.VISIBLE);
            }
        });
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_home2_to_apply_form);
            }
        });
        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                form_click.setVisibility(View.INVISIBLE);
            }
        });
        result_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_home2_to_result_page);
            }
        });


    }
}
