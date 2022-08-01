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
import com.example.rajendracarrental.activity.Booking;
import com.example.rajendracarrental.activity.Carbookingform;
import com.example.rajendracarrental.activity.Home;
import com.example.rajendracarrental.container.Carscontainer;
import com.example.rajendracarrental.container.Newscontainer;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Carrentaladapter extends RecyclerView.Adapter<Carrentaladapter.MyViewHolder>  {

    private List<Carscontainer> carList;
    private Context mContext;
    private View parent_view;
    class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout parentLayout;
        TextView description;
        ImageView carimg;
        Button bookcar;
        MyViewHolder(View view) {
            super(view);
            mContext = view.getContext();
            parent_view = view.findViewById(android.R.id.content);
            description = (TextView) view.findViewById(R.id.description);
            carimg = (ImageView) view.findViewById(R.id.carimg);
            bookcar = (Button) view.findViewById(R.id.bookcar);

        }
    }

    public Carrentaladapter(List<Carscontainer> carList) {
        this.carList = carList;
    }

    @NonNull
    @Override
    public Carrentaladapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cars_list, parent, false);
        return new Carrentaladapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Carrentaladapter.MyViewHolder holder, int position) {
        final Carscontainer cars = carList.get(position);
        final String category = cars.getCategory();
        final String manufacturer = cars.getManufacturer();
        final String mileage = cars.getMileage();
        final String model = cars.getModel();
        final String price = cars.getPrice();
        final String seats = cars.getSeats();
        holder.description.setText("Category : "+category+" Manufacurer : "+manufacturer+" Mileage : "+mileage+" Model : "+model+" Price : "+price+" Seats : "+seats);
        String url = cars.getVehicleimageurl();
        Picasso.get().load(url).into(holder.carimg);
        holder.bookcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Carbookingform.class);
                intent.putExtra("category",category);
                intent.putExtra("model",model);
                intent.putExtra("price",price);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

}
