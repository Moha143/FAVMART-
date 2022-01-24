package com.favmartcompanny.favmarapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
public class Confirm_Activity extends AppCompatActivity {
    Button btnfinish;
    EditText edcode;
    FirebaseAuth auth;
    FirebaseFirestore db;
    String name , email,password , phone,address;
    String VerificationID;
    Boolean iscodeverify = false;
    ProgressBar progressBar;
    private static final String KEY_VERIFICATION_ID = "key_verification_id";
    private String mVerificationId;
    TinyDB tinyDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_);
        btnfinish= findViewById (R.id.btnFFinish);
        edcode= findViewById (R.id.editTextVCode);
        auth= FirebaseAuth.getInstance ();
        db= FirebaseFirestore.getInstance ();
        tinyDB = new TinyDB(this);
        progressBar = (ProgressBar) findViewById(R.id.progressbars);
        Intent intent = getIntent ();
        if(intent != null)
        {
            name = intent.getStringExtra ("name");
            email = intent.getStringExtra ("email");
            phone = intent.getStringExtra ("phone");
            password = intent.getStringExtra ("password");
            address = intent.getStringExtra ("address");
            Log.e("Success", "Intent contains data");
            sendVerificationCode (phone);
        }

        if (mVerificationId == null && savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        }
        btnfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( ! iscodeverify   )
                {
                    String code = edcode .getText ().toString ();
                    verifyphone (code);

                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_VERIFICATION_ID,mVerificationId);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mVerificationId = savedInstanceState.getString(KEY_VERIFICATION_ID);
    }

//    public void sendVerficationCode(String phone){
//        PhoneAuthProvider.getInstance ().verifyPhoneNumber ( "+252" + phone, 60 , TimeUnit.SECONDS, TaskExecutors.MAIN_THREAD,callbacks );
//    }
    private void sendVerificationCode(String mobile) {
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber("+252"+mobile)
                .setTimeout(60L,TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(callbacks)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
    private  PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            Log.e("verifyCompleted", phoneAuthCredential.getSmsCode());
            verifyphone (phoneAuthCredential.getSmsCode ());
        }
        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Log.e("verify Error", e.getMessage());
        }
        @Override
        public void onCodeSent(@NonNull String id, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(id, forceResendingToken);
            VerificationID=id;
        }
    };

    private void verifyphone(String smsCode){
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(VerificationID,smsCode);
        signwithcredential(credential);
    }
    private  void  signwithcredential (PhoneAuthCredential credential){
        auth.signInWithCredential (credential).addOnCompleteListener (new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                iscodeverify = true;
                createEmailwithpassword();
            }
        }) .addOnFailureListener (new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText (Confirm_Activity.this, "Something wrong Please try again letter" , Toast.LENGTH_LONG).show ();
            }
        });
    }
    private  void  createEmailwithpassword(){
        auth.createUserWithEmailAndPassword (email, password).addOnSuccessListener (new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Log.e ("successful", "Successfully created email and password");
                adduser ();
            }
        }).addOnFailureListener (new OnFailureListener () {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e ("Failure errors",e.getMessage ());
            }
        });
    }
    private  void  adduser() {
        progressBar.setVisibility(View.VISIBLE);
        String userid = auth.getCurrentUser().getUid();
        HashMap<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("phone", phone);
        user.put("email", email);
        user.put("token",Utilty.getToken(this));
        user.put("address", address);
        user.put("date", new Timestamp(new Date()));
        //  user.put ("date" , new Timestamp ( new Date()));
        db.collection("users").document(userid).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    tinyDB.putString("name",name);
                    tinyDB.putString("email",email);
                    tinyDB.putString("phone",phone);
                    tinyDB.putString("address",address);

                    Log.e("successful", "data have been saved ");
                    Intent intent = new Intent(Confirm_Activity.this, Home_Activity.class);
                    startActivity(intent);
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("database error", e.getMessage());
            }
        });
    }
}