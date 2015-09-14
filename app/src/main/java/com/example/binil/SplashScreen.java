package com.example.binil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


/**
 * Created by binil1931 on 11-Sep-15.
 */
public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         int SPLASH_TIME_OUT = 3000;
        setContentView(R.layout.splash_screen);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub

                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                finish();
                startActivity(i);

            }
        }, SPLASH_TIME_OUT);
    }
}
