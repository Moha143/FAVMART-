package com.favmartcompanny.favmarapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_Password extends AppCompatActivity {
    EditText iemail;
    private Button btnsend;

    private FirebaseAuth auth;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__password);
        iemail=findViewById(R.id.editTextFemail);
        btnsend=findViewById(R.id.btnForgot);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        auth = FirebaseAuth.getInstance();
        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = iemail.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplication(), "Enter your registered email ", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Forgot_Password.this, "We have sent you instructions to reset your password!. check your email", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Forgot_Password.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                        }
                        progressBar.setVisibility(View.GONE);

                    }
                });



            }
        });

    }
}