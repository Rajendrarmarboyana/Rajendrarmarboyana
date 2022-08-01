package com.example.rajendracarrental.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rajendracarrental.R;
import com.example.rajendracarrental.container.Newscontainer;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Newsadapter extends RecyclerView.Adapter<Newsadapter.MyViewHolder>  {
    private List<Newscontainer> newsList;
    private Context mContext;
    private View parent_view;
    class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout parentLayout;
        TextView title;
        ImageView img;
        MyViewHolder(View view) {
            super(view);
            mContext = view.getContext();
            parent_view = view.findViewById(android.R.id.content);
            title = (TextView) view.findViewById(R.id.title);
            img = (ImageView) view.findViewById(R.id.img);
        }
    }

    public Newsadapter(List<Newscontainer> newsList) {
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public Newsadapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list, parent, false);
        return new Newsadapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Newsadapter.MyViewHolder holder, int position) {
        final Newscontainer news = newsList.get(position);
        final String newstitle = news.getTitle();
        holder.title.setText(newstitle);
        String url = news.getImg_url();
        Picasso.get().load(url).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}
