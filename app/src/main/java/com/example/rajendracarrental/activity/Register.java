package com.example.rajendracarrental.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rajendracarrental.R;
import com.example.rajendracarrental.container.Customers;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    EditText name,username,phone,password;
    Button register,login;
    private FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.name);
        username = (EditText) findViewById(R.id.username);
        phone = (EditText) findViewById(R.id.phone);
        password = (EditText) findViewById(R.id.password);
        register = (Button) findViewById(R.id.register);
        login = (Button) findViewById(R.id.login);
        // Initialize Firebase Auth
        fAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_name = name.getText().toString().trim();
                String str_username = username.getText().toString().trim();
                String str_phone = phone.getText().toString().trim();
                String str_password = password.getText().toString().trim();
                registerAction(str_name,str_username,str_phone,str_password);
            }
        });

    }

    private void registerAction(String str_name, String str_username, String str_phone, String str_password) {
        if(str_name.isEmpty()){
            name.setError("Please Enter Name");
            name.requestFocus();
            return;
        }else if(str_username.isEmpty()){
            username.setError("Please Enter Username");
            username.requestFocus();
            return;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(str_username).matches()){
            username.setError("Please Enter Currect Username");
            username.requestFocus();
            return;
        }else if(str_phone.isEmpty()){
            phone.setError("Please Enter Phone Number");
            phone.requestFocus();
            return;
        }else if(str_password.isEmpty()){
            password.setError("Password should not Empty");
            password.requestFocus();
            return;
        }else{
            fAuth.createUserWithEmailAndPassword(str_username,str_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Customers users = new Customers(str_name,str_username,str_phone,"");
                        FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(Register.this,"User has been registered successfully !",Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(Register.this,Login.class));
                                }else{
                                    Toast.makeText(Register.this,"Failed to registered try gain", Toast.LENGTH_LONG).show();
                                   // progressBar.setVisibility(View.GONE);
                                }
                            }
                        });
                    }
                }
            });
        }

    }
}