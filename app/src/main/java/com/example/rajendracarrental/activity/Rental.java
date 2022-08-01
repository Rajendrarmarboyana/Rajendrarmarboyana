package com.example.rajendracarrental.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.rajendracarrental.R;
import com.example.rajendracarrental.adapter.Carrentaladapter;
import com.example.rajendracarrental.adapter.Newsadapter;
import com.example.rajendracarrental.container.Carscontainer;
import com.example.rajendracarrental.container.Newscontainer;
import com.example.rajendracarrental.session;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Rental extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    private List<Carscontainer> carsList = new ArrayList<>();
    RecyclerView cars_rec_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.getMenu().findItem(R.id.rental).setChecked(true);
        cars_rec_view= (RecyclerView) findViewById(R.id.cars_rec_view);
        LinearLayoutManager llm_cars = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        cars_rec_view.setLayoutManager(llm_cars);

        getCarsList();

    }

    private void getCarsList() {
        DatabaseReference ListReference = FirebaseDatabase.getInstance().getReference().child("vehicle");

        ListReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String key = (String) ds.getKey();
                    DatabaseReference keyReference = FirebaseDatabase.getInstance().getReference().child("vehicle").child(key);
                    keyReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String category = dataSnapshot.child("category").getValue(String.class);
                            String manufacturer = dataSnapshot.child("manufacturer").getValue(String.class);
                            String model = dataSnapshot.child("model").getValue(String.class);
                            String vehicleimageurl = dataSnapshot.child("vehicleimageurl").getValue(String.class);
                            //double lat = (Double) dataSnapshot.child("lat").getValue(Double.class);
                            //double lng = (Double) dataSnapshot.child("lng").getValue(Double.class);
                            String mileage = dataSnapshot.child("mileage").getValue(String.class);
                            String price = dataSnapshot.child("price").getValue(String.class);
                            String seats = dataSnapshot.child("seats").getValue(String.class);
                            String vehicleid = dataSnapshot.child("vehicleid").getValue(String.class);
                            String year = dataSnapshot.child("year").getValue(String.class);
                            //Log.d("Lat Long", "Lat " + lat + " Long " + lng);
                            Carscontainer carscontainer = new Carscontainer(true, category, manufacturer, model, vehicleimageurl, 0.0, 0.0, mileage, price, seats, vehicleid, year);
                            carsList.add(carscontainer);
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.d("ERROR DATABASE", "Read failed");
                        }
                    });
                }
                Carrentaladapter carrentaladapter = new Carrentaladapter(carsList);
                cars_rec_view.setAdapter(carrentaladapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("TAG", "Read failed");
            }

        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                startActivity(new Intent(Rental.this, Home.class));
                return true;
            case R.id.booking:
                startActivity(new Intent(Rental.this, Booking.class));
                return true;
            case R.id.rental:
                startActivity(new Intent(Rental.this,Rental.class));
                return true;
            case R.id.contact:
                startActivity(new Intent(Rental.this,Contact.class));
                return true;
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                session.clearData(Rental.this);
                startActivity(new Intent(Rental.this, Login.class));
                return true;
        }
        return false;
    }
}