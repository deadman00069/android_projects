package com.example.ui_test4;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
{
    Context context;
    ArrayList<fetch_detail> profiles;

    public MyAdapter(Context c , ArrayList<fetch_detail> p)
    {
        context = c;
        profiles = p;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.my_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name1.setText("Name: "+profiles.get(position).getName());
        holder.address1.setText("Address: "+profiles.get(position).getAddress());
        holder.phone_no1.setText("Phone No: "+profiles.get(position).getPhone_no());
        String url=profiles.get(position).getImage();
        Picasso.get().load(url).fit().into(holder.img);
        holder.status1.setText("status: "+profiles.get(position).getStatus());

    }
    @Override
    public int getItemCount()
    {
        return profiles.size();
    }
    public  class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView name1,address1,phone_no1,status1;
         ImageView img;

        public MyViewHolder(View itemView) {
            super(itemView);
            name1 =  itemView.findViewById(R.id.name);
            address1 =  itemView.findViewById(R.id.address);
            phone_no1 =  itemView.findViewById(R.id.phone_no);
            img = itemView.findViewById(R.id.image_dislay);
            status1=itemView.findViewById(R.id.status);

        }
    }
}


