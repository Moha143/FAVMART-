package com.favmartcompanny.favmarapplication.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.favmartcompanny.favmarapplication.Fruits_detail_Activity;
import com.favmartcompanny.favmarapplication.R;
import com.favmartcompanny.favmarapplication.TinyDB;
import com.favmartcompanny.favmarapplication.Utilty;
import com.favmartcompanny.favmarapplication.adapter.FruitAdapter;
import com.favmartcompanny.favmarapplication.adapter.VegetableAdapter;
import com.favmartcompanny.favmarapplication.model.Fruits;
import com.favmartcompanny.favmarapplication.model.Vegetable;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

public class ShopFragment extends Fragment implements FruitAdapter.OnFruitClicked, View.OnClickListener,VegetableAdapter.OnVegetableClicked {
    FirebaseFirestore db;
    FirebaseAuth auth;
    Context context;
    TinyDB tinyDB;

    RecyclerView fruitsRecyclerView;
    ArrayList<Fruits> fruitsArrayList = new ArrayList<>();
    FruitAdapter fruitAdapter;
    TextView fruitsee,vegetablesee;
    RecyclerView vegetablesRecyclerView;
    ArrayList<Vegetable> vegetableArrayList = new ArrayList<>();
    VegetableAdapter vegetableAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.home_screen, container, false);
        db = FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();

        context=getContext();
        tinyDB=new TinyDB(context);

        //Fruits
        fruitsRecyclerView=view.findViewById(R.id.recycler_fruits_tamplate);
        fruitAdapter=new FruitAdapter(getContext(),fruitsArrayList);
        fruitAdapter.setOnclick(this);
        fruitsee=view.findViewById(R.id.fruitsee);
        fruitsee.setOnClickListener(this);
        fruitsRecyclerView.setAdapter(fruitAdapter);
       fruitsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        getFruits();

        //Vegetable
        vegetablesee=view.findViewById(R.id.vegetablesee);
        vegetablesee.setOnClickListener(this);
        vegetablesRecyclerView=view.findViewById(R.id.recycler_vegetable_tamplate);
        vegetableAdapter=new VegetableAdapter(getContext(),vegetableArrayList);
        vegetableAdapter.setOnVclick(this);
        vegetablesRecyclerView.setAdapter(vegetableAdapter);
       vegetablesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        getVegetable();
        seteUser();
        registerFCMTopic();

        return view;
    }
   public void onClick(View v) {
        if (v==fruitsee){
            fruitsee.setText("minimize");
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
            fruitsRecyclerView.setLayoutManager(gridLayoutManager);

        }
        if (v==vegetablesee){
            vegetablesee.setText("minimize");
            GridLayoutManager gridLayoutManage = new GridLayoutManager(getContext(), 2);
            vegetablesRecyclerView.setLayoutManager(gridLayoutManage);
        }
   }


    //getting fruits from firestore
    private void getFruits(){
        CollectionReference collectionReference = db.collection("fruits");
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    Log.e("isSucessfulFruits", "Yes");
                    Fruits fruits;
                    for (DocumentSnapshot snapshot : task.getResult()){
                        String name = snapshot.getString("FName");
                        String image = snapshot.getString("Image");
                        String price = snapshot.getString("Price");
                        String description = snapshot.getString("Description");
                        String unit = snapshot.getString("Unit");
                        String date = "";
                        fruits = new Fruits(name,image, date,price,description,unit);
                        fruitsArrayList.add(fruits);
                    }
                    fruitAdapter.notifyDataSetChanged();
                }
            }
        });
    }
    //getting vegetable from firestore
    private void getVegetable(){
        CollectionReference collectionReference = db.collection("vegetables");
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    Log.e("isSucessfulVegetable", "Yes");
                    Vegetable vegetable;
                    for (DocumentSnapshot snapshot : task.getResult()){
                        String name = snapshot.getString("VName");
                        String image = snapshot.getString("VImage");
                        String price = snapshot.getString("VPrice");
                        String unit = snapshot.getString("VUnit");
                        String description = snapshot.getString("VDescription");
                        String date = "";
                        vegetable = new Vegetable(name,image, date,price,unit,description);
                        vegetableArrayList.add(vegetable);
                    }
                    fruitAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onFruitClicked(String name, String image, String price, String description ,String unity) {

        Intent intent = new Intent(getActivity(), Fruits_detail_Activity.class);
        intent.putExtra("FName",name);
        intent.putExtra("Image",image);
        intent.putExtra("Price",price);
        intent.putExtra("Description",description);
        intent.putExtra("Unit",unity);
        intent.putExtra("from","fruits");
        startActivity(intent);
    }
    @Override
    public void OnVegetableClicked(String name, String image, String price, String description, String unity) {
        Intent intent = new Intent(getActivity(), Fruits_detail_Activity.class);
        intent.putExtra("FName",name);
        intent.putExtra("Image",image);
        intent.putExtra("Price",price);
        intent.putExtra("Description",description);
        intent.putExtra("Unit",unity);
        intent.putExtra("from","vegetables");
        startActivity(intent);
    }

    private void seteUser(){
        final ProgressDialog pd = new ProgressDialog(context);
        pd.setMessage("Fadlan Wax Yar Usug");
        pd.show();
        final String userID = auth.getCurrentUser().getUid();
        DocumentReference docRef = db.collection("users").document(userID);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                pd.dismiss();
                if (task.isSuccessful()){
                    DocumentSnapshot snapshot = task.getResult();
                    if (snapshot.exists()){
                        Log.e("userDataOnB", String.valueOf(snapshot.getData()));
                        String localName = snapshot.getString("name");
                        String LocalPhone = snapshot.getString("phone");
                        String localEmail = snapshot.getString("email");
                        String LocalAddress = snapshot.getString("address");
                        String token = snapshot.getString("token");
                        if (tinyDB.getString("name").isEmpty() || tinyDB.getString("phone").isEmpty()||

                                tinyDB.getString("email").isEmpty()|| tinyDB.getString("address").isEmpty() ){
                            tinyDB.putString("name",localName);
                            tinyDB.putString("email",localEmail);
                            tinyDB.putString("phone",LocalPhone);
                            tinyDB.putString("address",LocalAddress);
                        }

//                        Utilty.setName(context,name);
//                        Utilty.setPhone(context,phone);
//                        Utility.setGender(context,gender);
                        if (!Utilty.getToken(context).equals(token)){
                            Log.e("tokens","not Equal");
                            db.collection("users").document(userID).update("token",Utilty.getToken(context));
                        }
                    }
                    else {
                        Log.e("userData","null");
                    }
                }
                else {
                    Log.e("failedToGetUser",  task.getException().getMessage());
                }
            }
        });
    }

    private void registerFCMTopic(){
        if (!tinyDB.getBoolean("isSubscribedToFCM")){
            FirebaseMessaging.getInstance().subscribeToTopic("users")
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (!task.isSuccessful()) {
//                                Toast.makeText(context, "Failed To Subscribed", Toast.LENGTH_SHORT).show();
                                tinyDB.putBoolean("isSubscribedToFCM", false);
                            }
//                            Toast.makeText(context, "Subscribed To Drivers", Toast.LENGTH_SHORT).show();
                            tinyDB.putBoolean("isSubscribedToFCM", true);
                        }
                    });
        }

    }
}
