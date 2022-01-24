package com.favmartcompanny.favmarapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;

public class Fav_detail_Activity extends AppCompatActivity {
    TextView tvFName, tvFPrice, tvDescription, tvamount,tvquantity , tvunit;
    ImageView FruitImage;
    Button btnmycart;
    FirebaseFirestore db;
    FirebaseAuth auth;
    int quantity = 1;
    int tprice;
    int amount;
    String userId,uname,uphone,uemail;
    String fname,image,price,description,unit;
    Button btnincrement , btndecrement;
    LottieAnimationView imgfav,success;
    Button btnok;
    Dialog dialog;
    String from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_detail_);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        userId = auth.getCurrentUser().getUid();
        getUserData();
        tvunit=findViewById(R.id.favtextviewUnit);
        tvamount = findViewById(R.id.favtextviewFAmount);
        tvquantity=findViewById(R.id.favtextviewFQuantity);
        btnincrement = findViewById(R.id.favbtnFIncrement);
        btndecrement = findViewById(R.id.favbtnFDecrement);
        FruitImage = findViewById(R.id.fav_img_in_details);
        tvFName = findViewById(R.id.textviewFavName);
        tvFPrice = findViewById(R.id.favtextviewFFruitPrice);
        tvDescription = findViewById(R.id.favtextviewFruitDescription);
        btnmycart = findViewById(R.id.btnmycart);
        dialog=new Dialog(this);
        imgfav=findViewById(R.id.imgfav);
        Intent intent = getIntent();
        if (intent != null) {
            fname = intent.getStringExtra("FName");
            image = intent.getStringExtra("Image");
            price = intent.getStringExtra("Price");
            description = intent.getStringExtra("Description");
            unit = intent.getStringExtra("Unit");
            from=intent.getStringExtra("from");
            //if(from.equals("fruits"))
            tvFName.setText(fname);
            tvFPrice.setText(price);
            tvunit.setText(unit);
            tvDescription.setText(description);
            tprice=Integer.parseInt(price);
            tvamount.setText(String.valueOf(price));
            Log.v("amaount",String.valueOf(amount));
            Glide.with(this).load(image).into(FruitImage);
            dialog=new Dialog(this);
            btnmycart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addmycart();
                    dialog.setContentView(R.layout.addedcartdialog);
                    // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                    success=dialog.findViewById(R.id.success);
                    success.playAnimation();
                    btnok=dialog.findViewById(R.id.btnOk);
                    btnok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            Intent intent=new Intent(Fav_detail_Activity.this,Home_Activity.class);
                            startActivity(intent);
                        }
                    });
                }
            });
            imgfav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgfav.playAnimation();
                    addmyfav();
                }
            });
        }

    }
    public void addmycart(){
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("FName", fname);
        hashMap.put("Image", image);
        hashMap.put("Price",price);
        hashMap.put("Quantity",quantity);

        // amount=String.valueOf(amount).toString();
        hashMap.put("TotalPrice",amount);
        // hashMap.put("Description", description);
        hashMap.put("userId", userId);
        hashMap.put("Name", uname);
        hashMap.put("email", uemail);
        hashMap.put("Unit", unit);
        hashMap.put("date", new Timestamp(new Date()));
        db.collection("mycart").add(hashMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()){
                    //   Toast.makeText(Fruits_detail_Activity.this, "Added to your cart", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Fav_detail_Activity.this, "cart not Completed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void addmyfav(){
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("FName", fname);
        hashMap.put("Image", image);
        hashMap.put("Price",price);
        hashMap.put("userId", userId);
        hashMap.put("Name", uname);
        hashMap.put("Phone", uphone);
        hashMap.put("Unit", unit);
        hashMap.put("Description", description);
        hashMap.put("date", new Timestamp(new Date()));
        db.collection("myfavorite").add(hashMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Fav_detail_Activity.this, "Added to your favorite", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Fav_detail_Activity.this, "favorite not Completed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void FavOnbtnIncrement(View view) {
        if (quantity == 10) {
            Toast.makeText(Fav_detail_Activity.this, "lama gadan karo wax ka badan 10 ",Toast.LENGTH_LONG).show();
            return;
        } else {
            quantity = quantity + 1;
                amount = tprice * quantity;
            }
            displayQuantity(quantity, amount);
    }
    public void FavOnbtnDecrement(View view) {
        if (quantity == 1) {
            return;
        }
        else {
            quantity = quantity -1;
            amount = tprice* quantity;
            displayQuantity(quantity, amount);
        }
    }
    private void displayQuantity(int numberOfPackages,int am) {
        tvamount.setText(String.valueOf(am));
        tvquantity.setText(String.valueOf(numberOfPackages));
    }
    public void getUserData()
    {
        db.collection("users").document(userId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot snapshot = task.getResult();
                    uname = snapshot.getString("name");
                    uphone = snapshot.getString("phone");
                    uemail = snapshot.getString("email");
                } else {
                    Log.v("users","Could not get User data");
                }
            }
        });
    }
}