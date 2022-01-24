package com.favmartcompanny.favmarapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Activity extends AppCompatActivity {
    TextView tvSignUp;
    EditText edEmail,edPassword;
    FirebaseAuth auth;
    Button btnlogin;

    TextView NewUser, forgotpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        btnlogin=findViewById(R.id.btnLogin);
        NewUser=findViewById(R.id.newUser);
        edEmail=findViewById(R.id.editTextLEmail);
        edPassword=findViewById(R.id.editTextLPassword);
        forgotpassword=findViewById(R.id.forgotpassword);

        auth= FirebaseAuth.getInstance ();

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent (Login_Activity.this, Forgot_Password.class);
                startActivity(intent);

            }
        });

        NewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent (Login_Activity.this, Create_Account_Activity.class);
                startActivity(intent);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Intent intent = new Intent(Login_Activity.this , Home_Activity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(Login_Activity.this, "Please Check Your Email or Password", Toast.LENGTH_LONG).show();
                        }

                    }
                });

            }
        });
    }


}