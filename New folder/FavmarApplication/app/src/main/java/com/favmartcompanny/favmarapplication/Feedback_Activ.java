package com.favmartcompanny.favmarapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Feedback_Activ extends AppCompatActivity {

    EditText sendmessages;
    Button btnsave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_2);
        sendmessages=findViewById(R.id.editTextMessage);
        btnsave= findViewById(R.id.btnsends);

    }
}