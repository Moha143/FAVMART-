package com.favmartcompanny.favmarapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class Splash_start_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_start_);
        EasySplashScreen conf =new EasySplashScreen(Splash_start_Activity.this)
                .withFullScreen().withTargetActivity(Splash_Activity.class)
                .withSplashTimeOut(3000)
                .withLogo(R.drawable.logo);
                 View eays=conf.create();
                 setContentView(eays);
    }
}