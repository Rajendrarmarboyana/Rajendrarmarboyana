package com.example.rajendracarrental.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.rajendracarrental.R;
import com.example.rajendracarrental.session;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;
    CardView news,aboutus,booking,map;
    TextView name,username;
    Button logout;
    Animation image_anim, text_anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        news = (CardView) findViewById(R.id.news);
        aboutus = (CardView) findViewById(R.id.aboutus);
        booking = (CardView) findViewById(R.id.booking);
        map = (CardView) findViewById(R.id.map);
        name = (TextView) findViewById(R.id.name);
        username = (TextView) findViewById(R.id.username);

        /* Animation code */
        image_anim = AnimationUtils.loadAnimation(this, R.anim.imageanimation);
        text_anim = AnimationUtils.loadAnimation(this, R.anim.animatetext);
        news.setAnimation(image_anim);
        aboutus.setAnimation(text_anim);
        booking.setAnimation(image_anim);
        map.setAnimation(text_anim);
        /* End of Animation */

        if (session.getDataLogin(this)) {
            if (session.getDataLogin(this)) {
                name.setText(session.getDataName(getApplicationContext()));
                username.setText(session.getDataUsername(getApplicationContext()));
            }
        }

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, News.class));
            }
        });
        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, Aboutus.class));
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, Onmap.class));
            }
        });
        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, Booking.class));
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                startActivity(new Intent(Home.this, Home.class));
                return true;
            case R.id.booking:
                startActivity(new Intent(Home.this, Booking.class));
                return true;
            case R.id.rental:
                startActivity(new Intent(Home.this,Rental.class));
                return true;
            case R.id.contact:
                startActivity(new Intent(Home.this,Contact.class));
                return true;
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                session.clearData(Home.this);
                startActivity(new Intent(Home.this, Login.class));
                return true;
        }
        return false;
    }
}