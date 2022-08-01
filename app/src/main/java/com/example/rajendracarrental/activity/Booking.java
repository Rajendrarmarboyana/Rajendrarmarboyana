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
import com.example.rajendracarrental.adapter.Bookingadapter;
import com.example.rajendracarrental.adapter.Carrentaladapter;
import com.example.rajendracarrental.container.Bookingformcontainer;
import com.example.rajendracarrental.container.Carscontainer;
import com.example.rajendracarrental.session;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Booking extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    private List<Bookingformcontainer> bookingList = new ArrayList<>();
    RecyclerView booking_rec_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.getMenu().findItem(R.id.booking).setChecked(true);
        booking_rec_view = (RecyclerView) findViewById(R.id.booking_rec_view);

        LinearLayoutManager llm_booking = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        booking_rec_view.setLayoutManager(llm_booking);

        getCarsBookingList();
    }

    private void getCarsBookingList() {
        DatabaseReference ListReference = FirebaseDatabase.getInstance().getReference().child("Booking");
        Log.d("firstcheck","yes");
        ListReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                Log.d("secondcheck","yes");
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String key = (String) ds.getKey();
                    Log.d("thirdcheck","yes"+key);
                    DatabaseReference keyReference = FirebaseDatabase.getInstance().getReference().child("Booking").child(key);
                    keyReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String category = dataSnapshot.child("category").getValue(String.class);
                            String destination = dataSnapshot.child("destination").getValue(String.class);
                            String price = dataSnapshot.child("price").getValue(String.class);
                            String startfrom = dataSnapshot.child("startfrom").getValue(String.class);
                            String msg = dataSnapshot.child("msg").getValue(String.class);
                            Log.d("categorycheck",category);
                            Bookingformcontainer bookingcontainer = new Bookingformcontainer( category, "", price, startfrom, destination, "", "", msg);
                            bookingList.add(bookingcontainer);
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.d("ERROR DATABASE", "Read failed");
                        }
                    });
                }
                Bookingadapter bookingadapter = new Bookingadapter(bookingList);
                booking_rec_view.setAdapter(bookingadapter);
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
                startActivity(new Intent(Booking.this, Home.class));
                return true;
            case R.id.booking:
                startActivity(new Intent(Booking.this, Booking.class));
                return true;
            case R.id.rental:
                startActivity(new Intent(Booking.this,Rental.class));
                return true;
            case R.id.contact:
                startActivity(new Intent(Booking.this,Contact.class));
                return true;
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                session.clearData(Booking.this);
                startActivity(new Intent(Booking.this, Login.class));
                return true;
        }
        return false;
    }
}