package com.example.greenifylogin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ViewHolder extends RecyclerView.Adapter<ViewHolder.myViewHolder> {

    ArrayList<Member> list;
    Context context;
    public ViewHolder(Context context,ArrayList<Member> list) {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder.myViewHolder holder, int position) {
        Member member = list.get(position);
        holder.title.setText(member.getDescription());
        Glide.with(context).load(member.getImage()).into(holder.imagev);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView imagev;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.rTitleView);
            imagev = itemView.findViewById(R.id.rImageview);
        }
    }
}
