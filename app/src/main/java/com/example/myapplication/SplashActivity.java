package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Use a Handler to delay the start of the main activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the main activity
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);

                // Close the splash activity to prevent the user from coming back to it
                finish();
            }
        }, 2000); // 2000 milliseconds (2 seconds) delay
    }
}
