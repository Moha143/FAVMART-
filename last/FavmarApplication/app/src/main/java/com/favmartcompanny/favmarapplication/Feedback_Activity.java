package com.favmartcompanny.favmarapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
public class Feedback_Activity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseFirestore db;

    EditText edsend;
    Button btnsends;
    String Name,Phone,Email,userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        btnsends= findViewById(R.id.btnsend);
        edsend=findViewById(R.id.editTextAA);
       // edsend= (EditText) findViewById(R.id.editTextAA);
       userId = auth.getCurrentUser().getUid();
        Intent intent = getIntent();
        if (intent != null) {
            Name = intent.getStringExtra("FName");
            Phone = intent.getStringExtra("FPhone");
            Email = intent.getStringExtra("FEmail");
            Log.v("total", String.valueOf(Phone));
            Log.v("total", String.valueOf(Name));
            Log.v("total", String.valueOf(Email));
        }
        btnsends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addmessage();
                Log.v("cliked","Could data");

            }
        });


    }
    public void addmessage(){
        HashMap<String,Object> hashMap = new HashMap<>();
        String userid = auth.getCurrentUser().getUid();
      String ed=  edsend.getText().toString();
        hashMap.put("name", Name);
        hashMap.put("phone", Phone);
        hashMap.put("email",Email);
        hashMap.put("message",ed );
        hashMap.put("UserId",userid );
        hashMap.put("date", new Timestamp(new Date()));
        db.collection("Feedback").add(hashMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Feedback_Activity.this, "THANKS YOU FOR YOUR FEEDBACK", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Feedback_Activity.this, "Something wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}