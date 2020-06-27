package com.example.xddd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;

public class myAdapter extends RecyclerView.Adapter<myAdapter.MyView> {
    public ArrayList<String> ex_name;
    public ArrayList<Integer> ex_img;
    static String nameofex;
    public static HashMap<Integer, Pair<Integer, String>> selected = new HashMap<>();
    public static Context context;
    static ArrayList<String> result1;


    public myAdapter(ArrayList<String> ex_name, ArrayList<Integer> ex_img, String nameofex,Context context) {
        this.ex_name = ex_name;
        this.ex_img = ex_img;
        this.context = context;
        this.nameofex=nameofex;

    }


    @NonNull
    @Override
    public myAdapter.MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        result1=new ArrayList<>();
        return new MyView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myAdapter.MyView holder, int position) {
        holder.setIndex(position);
        holder.ex_name.setText(ex_name.get(position));
        holder.ex_img.setImageResource(ex_img.get(position));
    }

    @Override
    public int getItemCount() {
        return ex_name.size();
    }


    public class MyView extends RecyclerView.ViewHolder {
        public TextView ex_name;
        public ImageView ex_img;
        public Button add, remove;
        private int index;

        public MyView(@NonNull View itemView) {
            super(itemView);
            ex_name = itemView.findViewById(R.id.card_exeName);
            ex_img = itemView.findViewById(R.id.card_exeImg);
            add = itemView.findViewById(R.id.add_btn);
            remove = itemView.findViewById(R.id.remove_btn);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    add.setText("Added");
                    remove.setVisibility(View.VISIBLE);
                    String name = myAdapter.this.ex_name.get(index);
                    Integer image = myAdapter.this.ex_img.get(index);
                    result1.add(name);

                }
            });
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = myAdapter.this.ex_name.get(index);
                    result1.remove(name);
                    remove.setVisibility(View.INVISIBLE);
                    add.setText("Add");
                    Log.i("loda", "" + selected);
                }
            });

        }

        public void setIndex(int position) {
            this.index = position;
        }
    }

//    public ArrayList<String> getSelectedNames() {
//        result1 = new ArrayList<>();
//        for (Pair<Integer, String> pair : selected.values()) {
//            result1.add(pair.second);
//        }
//        return result1;
//    }

//    public ArrayList<Integer> getSelectedImages() {
//        ArrayList<Integer> result = new ArrayList<>();
//        for (Pair<Integer, String> pair : selected.values()) {
//            result.add(pair.first);
//        }
//        return result;
//    }

    public static View.OnClickListener myclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (result1.isEmpty())
            {
                Toast.makeText(context, "add exercise", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(context, Main4Activity.class);
            intent.putExtra("yyy", result1);
            intent.putExtra("which_ex",nameofex);
            context.startActivity(intent);
            ((Activity)context).finish();
        }
    };


}