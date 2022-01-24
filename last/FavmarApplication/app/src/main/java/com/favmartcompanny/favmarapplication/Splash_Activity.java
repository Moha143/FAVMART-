package com.favmartcompanny.favmarapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Splash_Activity extends AppCompatActivity {

    FirebaseAuth auth;
    Button btnget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_);
        auth = FirebaseAuth.getInstance();
        btnget=findViewById(R.id.btn_get_start);
        btnget.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (Splash_Activity.this, Login_Activity.class);
                startActivity (intent);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();

        if (auth.getCurrentUser() != null){
            Intent intent = new Intent(Splash_Activity.this, Home_Activity.class);
            startActivity(intent);
        }
    }

}