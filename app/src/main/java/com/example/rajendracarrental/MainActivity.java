package com.example.rajendracarrental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rajendracarrental.activity.Home;
import com.example.rajendracarrental.activity.Login;

public class MainActivity extends AppCompatActivity {

    Animation image_anim, text_anim;
    ImageView image;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.splashscreen);
        text = findViewById(R.id.text);
        /* Animation code */
        image_anim = AnimationUtils.loadAnimation(this, R.anim.imageanimation);
        text_anim = AnimationUtils.loadAnimation(this, R.anim.animatetext);
        image.setAnimation(image_anim);
        text.setAnimation(text_anim);
        /* End of Animation */

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (session.getDataLogin(getApplicationContext())) {
                    if (session.getDataLogin(getApplicationContext())) {
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        finish();
                    }
                }else {
                    // change when use session Login to main
                    Intent i = new Intent(MainActivity.this, Login.class);
                    startActivity(i);
                    finish();
                }
            }
        }, 3000);

    }
}