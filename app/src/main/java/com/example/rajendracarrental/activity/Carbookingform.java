package com.example.rajendracarrental.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rajendracarrental.R;
import com.example.rajendracarrental.container.Bookingformcontainer;
import com.example.rajendracarrental.container.Contacts;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Carbookingform extends AppCompatActivity {

    String category,model,price;
    EditText etcategory,etmodel,etprice,startfrom,destination,email,phone,msg;
    Button submit;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carbookingform);
        Intent intent = getIntent();
        category = intent.getStringExtra("category");
        model = intent.getStringExtra("model");
        price = intent.getStringExtra("price");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Booking");
        etcategory = (EditText) findViewById(R.id.category);
        etmodel = (EditText) findViewById(R.id.model);
        etprice = (EditText) findViewById(R.id.price);
        startfrom = (EditText) findViewById(R.id.startfrom);
        destination = (EditText) findViewById(R.id.destination);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        msg = (EditText) findViewById(R.id.msg);
        submit = (Button) findViewById(R.id.submit);

        // set data coming from another activity
        etcategory.setText(category);
        etmodel.setText(model);
        etprice.setText(price);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_category = etcategory.getText().toString().trim();
                String str_model = etmodel.getText().toString().trim();
                String str_price = etprice.getText().toString().trim();
                String str_startfrom = startfrom.getText().toString().trim();
                String str_destination = destination.getText().toString().trim();
                String str_email = email.getText().toString().trim();
                String str_phone = phone.getText().toString().trim();
                String str_msg = msg.getText().toString().trim();
                submitBooking(str_category,str_model,str_price,str_startfrom,str_destination,str_email,str_phone,str_msg);
            }
        });
    }

    private void submitBooking(String str_category, String str_model, String str_price, String str_startfrom, String str_destination, String str_email, String str_phone, String str_msg) {
        if(str_category.isEmpty()){
            etcategory.setError("Please Enter Category");
            etcategory.requestFocus();
            return;
        }else if(str_model.isEmpty()){
            etmodel.setError("Please Enter Model");
            etmodel.requestFocus();
            return;
        }else if(str_price.isEmpty()){
            etprice.setError("Please Enter Price");
            etprice.requestFocus();
            return;
        }else if(str_startfrom.isEmpty()){
            startfrom.setError("Please Enter Start from Location");
            startfrom.requestFocus();
            return;
        }else if(str_destination.isEmpty()){
            destination.setError("Please Enter Destination of Location");
            destination.requestFocus();
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
            Bookingformcontainer booking = new Bookingformcontainer(str_category,str_model,str_price,str_startfrom,str_destination,str_email,str_phone,str_msg);
            databaseReference.push().setValue(booking);
            Toast.makeText(getApplicationContext(), "Thankyou For booking will contact you soon..", Toast.LENGTH_SHORT).show();
        }


    }
}