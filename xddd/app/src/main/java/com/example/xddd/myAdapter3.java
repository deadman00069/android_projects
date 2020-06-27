package com.example.xddd;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class myAdapter3 extends RecyclerView.Adapter<myAdapter3.MyView> {
    ArrayList<String> max_Weight;
    ArrayList<String> ex_Name;
    public myAdapter3(ArrayList<String> max_Weight, ArrayList<String> ex_Name) {
        this.max_Weight = max_Weight;
        this.ex_Name = ex_Name;
    }
    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mycard3, parent, false);
        return new myAdapter3.MyView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        holder.weight.setText(ex_Name.get(position)+"kg");
        holder.names.setText(max_Weight.get(position));
    }

    @Override
    public int getItemCount() {
        return ex_Name.size();
    }

    public class MyView extends RecyclerView.ViewHolder {
        TextView names;
        TextView weight;

        public MyView(@NonNull View itemView) {
            super(itemView);
            names = itemView.findViewById(R.id.card_exeName);
            weight = itemView.findViewById(R.id.max_weight_text);
        }
    }
}
