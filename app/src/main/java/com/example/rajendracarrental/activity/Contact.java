package com.example.rajendracarrental.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rajendracarrental.R;
import com.example.rajendracarrental.container.Contacts;
import com.example.rajendracarrental.container.Customers;
import com.example.rajendracarrental.session;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Contact extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    EditText name,email,phone,msg;
    Button submit;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.getMenu().findItem(R.id.contact).setChecked(true);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Contacts");
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        msg = (EditText) findViewById(R.id.msg);
        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_name = name.getText().toString().trim();
                String str_email = email.getText().toString().trim();
                String str_phone = phone.getText().toString().trim();
                String str_msg = msg.getText().toString().trim();
                submitContactQuery(str_name,str_email,str_phone,str_msg);
            }
        });
    }

    private void submitContactQuery(String str_name, String str_email, String str_phone, String str_msg) {
        if(str_name.isEmpty()){
            name.setError("Please Enter Name");
            name.requestFocus();
            return;
        }else if(str_email.isEmpty()){
            email.setError("Please Enter Email");
            email.requestFocus();
            return;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(str_email).matches()){
            email.setError("Please Enter Currect Email");
            email.requestFocus();
            return;
        }else if(str_phone.isEmpty()){
            phone.setError("Please Enter Phone Number");
            phone.requestFocus();
            return;
        }else if(str_msg.isEmpty()){
            msg.setError("Message should not Empty");
            msg.requestFocus();
            return;
        }else{
            Contacts contacts = new Contacts(str_name,str_email,str_phone,str_msg);
            databaseReference.push().setValue(contacts);
            Toast.makeText(getApplicationContext(), "Thankyou For Contacting with Us will contact you soon..", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                startActivity(new Intent(Contact.this, Home.class));
                return true;
            case R.id.booking:
                startActivity(new Intent(Contact.this, Booking.class));
                return true;
            case R.id.rental:
                startActivity(new Intent(Contact.this,Rental.class));
                return true;
            case R.id.contact:
                startActivity(new Intent(Contact.this,Contact.class));
                return true;
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                session.clearData(Contact.this);
                startActivity(new Intent(Contact.this, Login.class));
                return true;
        }
        return false;
    }
}