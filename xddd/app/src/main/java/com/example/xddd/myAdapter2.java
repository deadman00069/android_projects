package com.example.xddd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class myAdapter2 extends RecyclerView.Adapter<myAdapter2.MyView> {
    static DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    static FirebaseAuth auth = FirebaseAuth.getInstance();
    static FirebaseUser user = auth.getCurrentUser();
    static String uid = user.getUid();
    static Date c = Calendar.getInstance().getTime();
    static SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
    static String formattedDate = df.format(c);
    public static ArrayList<String> names;
    public static Context context2;
    static ArrayList<String> t;
    static HashMap<Integer, String> max_weight;
    static HashMap<Integer, String> exercise_name = new HashMap<>();
    static HashMap<String, String> sent;
    static String which_ex;


    public myAdapter2(ArrayList<String> names, String which_ex, Context context2) {
        this.names = names;
        this.context2 = context2;
        this.which_ex = which_ex;
    }

    @NonNull
    @Override
    public myAdapter2.MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mycard2, parent, false);
        return new MyView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        holder.names.setText(names.get(position));
        Log.i("name", "" + names.get(position));
        Log.i("name", "" + position);
        exercise_name.put(position, names.get(position));
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class MyView extends RecyclerView.ViewHolder {
        TextView names;
        EditText maxweight;

        public MyView(View v) {
            super(v);
            max_weight = new HashMap<>();
            names = v.findViewById(R.id.card_exeName);
            maxweight = v.findViewById(R.id.max_weight_text);
            maxweight.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.i("dd", "" + getAdapterPosition());
                    max_weight.put(getAdapterPosition(), s.toString());
                }
            });
        }
    }

    public static View.OnClickListener myclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sent = new HashMap<>();
            if (max_weight.size()!=names.size())
            {
                Toast.makeText(context2, "Enter Weight", Toast.LENGTH_SHORT).show();
                return;
            }
            for (int i = 0; i < max_weight.size(); i++) {
                for (Map.Entry<Integer, String> entry : max_weight.entrySet()) {
                    if (entry.getKey().equals(i)) {
                        System.out.println("jhjjj");
                        for (Map.Entry<Integer, String> entry1 : exercise_name.entrySet()) {
                            if (entry1.getKey().equals(i)) {
                                sent.put(entry.getValue(), "" + entry1.getValue());
                            }
                        }
                    }
                }
            }
            for (Map.Entry<String, String> entry : sent.entrySet()) {
                ref.child(uid).child(which_ex).child(formattedDate).child(entry.getValue()).setValue(entry.getKey()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(context2, "submit success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context2, loginActivity.class);
                            context2.startActivity(intent);
                            ((Activity) context2).finish();
                        }
                    }
                });

            }
        }
    };
}
