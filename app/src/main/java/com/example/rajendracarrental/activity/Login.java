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
import com.example.rajendracarrental.session;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    // created view input fields object ref
    EditText username,password;
    Button login,register;
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        // Initialize Firebase Auth
        fAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_username = username.getText().toString().trim();
                String str_password = password.getText().toString().trim();
                loginAction(str_username,str_password);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Register.class));
            }
        });

    }

    private void loginAction(String str_username, String str_password) {
        if(str_username.isEmpty()){
            username.setError("Please Enter Username");
            username.requestFocus();
            return;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(str_username).matches()){
            username.setError("Please Enter Currect Username");
            username.requestFocus();
            return;
        }else if(str_password.isEmpty()){
            password.setError("Password should not Empty");
            password.requestFocus();
            return;
        }else{
            fAuth.signInWithEmailAndPassword(str_username,str_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                        reference.orderByChild("username").equalTo(str_username).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for(DataSnapshot datas: dataSnapshot.getChildren()){
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    String uid = user.getUid();
                                    String strusername=datas.child("username").getValue().toString();
                                    String name=datas.child("name").getValue().toString();
                                    String strphone=datas.child("phone").getValue().toString();
                                    String straddress=datas.child("address").getValue().toString();
                                    Toast.makeText(getApplicationContext(),name,Toast.LENGTH_LONG).show();
                                    session.setDataLogin(Login.this, true);
                                    session.setDataName(Login.this, name);
                                    session.setDataUserid(Login.this,uid);
                                    session.setDataUsername(Login.this,strusername);
                                    session.setDataPhone(Login.this,strphone);
                                    session.setDataAddress(Login.this,straddress);
                                    startActivity(new Intent(Login.this, Home.class));
                                }
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }else{
                        Toast.makeText(Login.this,"Failed to login try gain", Toast.LENGTH_LONG).show();
                        //progressBar.setVisibility(View.GONE);
                    }
                }
            });
        }

        //if(password.length() < 6){
           // et_password.setError("Min Password lenght should be 6 characters");
           // et_password.requestFocus();
           // return;
       // }
        
    }
}