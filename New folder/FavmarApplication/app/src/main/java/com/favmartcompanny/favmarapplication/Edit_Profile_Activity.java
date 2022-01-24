package com.favmartcompanny.favmarapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.favmartcompanny.favmarapplication.model.Myprofile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Date;
import java.util.HashMap;

public class Edit_Profile_Activity extends AppCompatActivity {

    private  static  final  int Pick_image=1;
    Button btnupdate ;
    EditText edname,edphone;
    ProgressBar progressBar;
    FirebaseAuth auth;
    private Uri imageUri;
    UploadTask uploadTask;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    FirebaseFirestore db ;
    Myprofile member;
    DocumentReference documentReference;
    ImageView imageprofile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__profile_);
        db=FirebaseFirestore.getInstance();
        progressBar=findViewById(R.id.progressbar_cp);
        auth= FirebaseAuth.getInstance ();
        String Name,Phone,Email;
        btnupdate=findViewById(R.id.btnPUpdate);
        edname=findViewById(R.id.editTextEPName);
        edphone=findViewById(R.id.editTextEPPhone);
        imageprofile=findViewById(R.id.profile_img);
        String userid = auth.getCurrentUser().getUid();
        documentReference = db.collection("users").document();
        storageReference = firebaseStorage.getInstance().getReference("fruits");
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //UploadData();
                adduser();
                Log.v("upload","eror aya jiro");
            }
        });
        Intent intent = getIntent();
        if (intent != null) {
            Name = intent.getStringExtra("PName");
            Phone = intent.getStringExtra("PPhone");
            Email = intent.getStringExtra("PEmail");
            Log.v("total", String.valueOf(Phone));
            Log.v("total", String.valueOf(Name));
            Log.v("total", String.valueOf(Email));
            edname.setText(Name);
            edphone.setText(Phone);
           // tvunit.setText(unit);
        }
    }
    public String getFileExt(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return  mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    public void chooseImageProfile(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,Pick_image);
    }
    private  void  adduser() {
        String userid = auth.getCurrentUser().getUid();
        HashMap<String, Object> user = new HashMap<>();
        user.put("name", edname);
        user.put("phone", edphone);
        user.put("date", new Timestamp(new Date()));
        //  user.put ("date" , new Timestamp ( new Date()));
        db.collection("users").document(userid).update(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.e("updated", "data have been updated ");
                Intent intent = new Intent(Edit_Profile_Activity.this, Edit_Profile_Activity.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("database error", e.getMessage());
            }
        });
    }
}