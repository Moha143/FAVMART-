package com.favmartcompanny.favmarapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class complete_profile_activity extends AppCompatActivity {

    Button btnfinish ;
    EditText tvaddress;
    ProgressBar progressBar;
    FirebaseAuth auth;
    private Uri imageUri;
    private  static  final  int Pick_image=1;
    UploadTask uploadTask;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    FirebaseFirestore db ;
    DocumentReference documentReference;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db=FirebaseFirestore.getInstance();
        auth= FirebaseAuth.getInstance ();
        setContentView(R.layout.activity_complete_profile_activity);
        btnfinish=findViewById(R.id.btnCFinish);
        tvaddress=findViewById(R.id.editTextAddress);
        progressBar=findViewById(R.id.progressbar_cp);
        imageView=findViewById(R.id.complete_image_icon);
        String userid = auth.getCurrentUser().getUid();

        documentReference=db.collection("users").document(userid);
        storageReference = firebaseStorage.getInstance().getReference("profiles");
        btnfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UploadData();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Pick_image || resultCode == RESULT_OK ||
                data != null || data.getData() != null){
            imageUri = data.getData();

            Picasso.get().load(imageUri).into(imageView);
        }
    }

    private void UploadData() {
        String address = tvaddress.getText().toString();
        progressBar.setVisibility(View.VISIBLE);
        final  StorageReference reference = storageReference.child(System.currentTimeMillis() + "." + getFileExt(imageUri));
        uploadTask = reference.putFile(imageUri);
    }
    public String getFileExt(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return  mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    public void chooseImage(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,Pick_image);
    }
}