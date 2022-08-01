package com.example.rajendracarrental.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.rajendracarrental.R;
import com.example.rajendracarrental.container.Bookingformcontainer;
import java.util.List;

public class Bookingadapter extends RecyclerView.Adapter<Bookingadapter.MyViewHolder>  {

    private List<Bookingformcontainer> bookingList;
    private Context mContext;
    private View parent_view;
    class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout parentLayout;
        TextView description;
        MyViewHolder(View view) {
            super(view);
            mContext = view.getContext();
            parent_view = view.findViewById(android.R.id.content);
            description = (TextView) view.findViewById(R.id.description);
        }
    }

    public Bookingadapter(List<Bookingformcontainer> bookingList) {
        this.bookingList = bookingList;
    }

    @NonNull
    @Override
    public Bookingadapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_list, parent, false);
        return new Bookingadapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Bookingadapter.MyViewHolder holder, int position) {
        final Bookingformcontainer booking = bookingList.get(position);
        final String category = booking.getCategory();
        final String destination = booking.getDestination();
        final String msg = booking.getMsg();
        final String price = booking.getPrice();
        final String startfrom = booking.getStartfrom();
        holder.description.setText("Category : "+category+" Start From : "+startfrom+" Destination : "+destination+" Message : "+msg+" Price : "+price);
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }
}
