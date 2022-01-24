package com.favmartcompanny.favmarapplication.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.favmartcompanny.favmarapplication.R;
import com.favmartcompanny.favmarapplication.TinyDB;
import com.favmartcompanny.favmarapplication.adapter.MycartAdapter;
import com.favmartcompanny.favmarapplication.model.Mycart;
import com.favmartcompanny.favmarapplication.model.Orders;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MycartFragment extends Fragment implements MycartAdapter.OnDeleteClicked,MycartAdapter.OnTotalrequest {
    FirebaseFirestore db;
    FirebaseAuth auth;
    String userIds;
    String myname;
    RecyclerView mycartRecyclerView;
    CollectionReference orderRef;
    ArrayList<Mycart> mycartArrayList = new ArrayList<>();
    MycartAdapter mycartAdapter;
    RelativeLayout emptyContainer, checkout;
    LinearLayout cardView, totalcost;
    LottieAnimationView imgan;
    TextView totalofall, totalcostdialog;
    int dialogtotal = 0;
    Dialog dialog, dialogpayment;
    Button btndeleted, btncheckout, btnpay;
    ImageView dialogcancel;

    TinyDB tinyDB;
    LottieAnimationView deletedicon;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fruit_mycart, container, false);

        db = FirebaseFirestore.getInstance();

        tinyDB = new TinyDB(getContext());
        auth = FirebaseAuth.getInstance();
        orderRef = db.collection("Orders");
        btncheckout = view.findViewById(R.id.btnmycartcheckout);

        imgan = view.findViewById(R.id.cart_image);
        userIds = auth.getCurrentUser().getUid();
        totalcost = view.findViewById(R.id.Ltotalcost);
        checkout = view.findViewById(R.id.Relative_check);
        emptyContainer = view.findViewById(R.id.cart_id);
        cardView = view.findViewById(R.id.card_view);
        totalofall = view.findViewById(R.id.totalofallprice);
        mycartRecyclerView = view.findViewById(R.id.recycler_mycart);
        mycartAdapter = new MycartAdapter(getContext(), mycartArrayList);
        mycartAdapter.setOnclick(this);
        mycartAdapter.setTotalrequest(this);
        dialogpayment = new Dialog(getContext());
        dialog = new Dialog(getContext());
        mycartRecyclerView.setAdapter(mycartAdapter);
        mycartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        getmycart();
        imgan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgan.playAnimation();
            }
        });
        btncheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogpayment.setContentView(R.layout.paymentdialog);
               // myaa();
                addmyorder();
                // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                totalcostdialog = dialogpayment.findViewById(R.id.txttotalcost);
                dialogcancel = dialogpayment.findViewById(R.id.dialogcancel);
                totalcostdialog.setText(String.valueOf(dialogtotal));
                btnpay = dialogpayment.findViewById(R.id.btnpay);
                btnpay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PayMoney();
                    }
                });
                dialogcancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogpayment.dismiss();
                    }
                });
                dialogpayment.show();
            }
        });
        return view;
    }

    //getting mycart from firestore
    private void getmycart() {
        db.collection("mycart").whereEqualTo("userId", userIds).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Log.e("isSucessfulmycart", "Yes");
                            Mycart mycart;
                            for (DocumentSnapshot snapshot : task.getResult()) {
                                String id = snapshot.getId();
                                String FName = snapshot.getString("FName");
                                String Image = snapshot.getString("Image");
                                String name = snapshot.getString("Name");
                                String Phone = snapshot.getString("Phone");
                                String Price = snapshot.getString("Price");
                                String unit = snapshot.getString("Unit");
                                String date = "";
                                String docid = "";
                                String userid = snapshot.getString("userId");
                                String totalPrice;
//                                totalPrice= snapshot.getString("TotalPrice");
                                totalPrice = String.valueOf(snapshot.getDouble("TotalPrice"));
                                //String TotalPrice = snapshot.getString("TotalPrice");
                                String quantity = String.valueOf(snapshot.getDouble("Quantity"));
                                mycart = new Mycart(id, FName, Image, name, totalPrice, Phone, Price, date, userid, unit, quantity);
                                mycartArrayList.add(mycart);
                            }
                            Log.v("displays", "all data are displayed");
                            mycartAdapter.notifyDataSetChanged();
                            if (mycartArrayList.size() > 0) {
                                cardView.setVisibility(View.VISIBLE);
                                totalcost.setVisibility(View.VISIBLE);
                                checkout.setVisibility(View.VISIBLE);
                                emptyContainer.setVisibility(View.GONE);
                                imgan.playAnimation();
                            } else {
                                cardView.setVisibility(View.GONE);
                                totalcost.setVisibility(View.GONE);
                                checkout.setVisibility(View.GONE);
                                emptyContainer.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
    }

    @Override
    public void OnDeleteClicked(String id, String fname, String name, String phone, String price, final String date, String userId, String unity, String TotalPrice, String image, String quantity) {

        db.collection("mycart").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dialog.setContentView(R.layout.deleteddialog);
                // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                deletedicon = dialog.findViewById(R.id.deleteddialogs);
                deletedicon.playAnimation();
                btndeleted = dialog.findViewById(R.id.btnDialogDeleted);
                btndeleted.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });


                Toast.makeText(getActivity(), "Deleted", Toast.LENGTH_LONG).show();
                Log.v("deleted", "Succcessfully deleed");

            }
        });
    }

    @Override
    public void OnTotal(double totalofallprices) {
        totalofall.setText(String.valueOf(totalofallprices));
        Log.v("total", String.valueOf(totalofallprices));
        dialogtotal = (int) totalofallprices;
    }

    public void PayMoney() {
        String number = "615314730";
        String usdc;
        int total = dialogtotal;
        usdc = "*712*" + number + "*" + total + "#";
        String uri = "tel:" + usdc + "#";
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }

    public void addmyorder() {
        String userID = auth.getCurrentUser().getUid();
        String name = tinyDB.getString("name");
        String email = tinyDB.getString("email");
        String phone = tinyDB.getString("phone");
        Orders order = new Orders(name, phone, email, new Timestamp(new Date()), mycartArrayList);
        orderRef.document(userID).collection("myorder").add(order).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {

                Log.v("success", "successfuly");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Log.e("errorr", "crash");
            }
        });

//        for (int i=0; i < mycartArrayList.size(); i++){
//            orders = new Orders("ali","617898989","hhshd",new Timestamp(new Date()),mycartArrayList.get(i))
//        }


    }

    public void myaa() {
        String userID = auth.getCurrentUser().getUid();
        String name = tinyDB.getString("name");
        String email = tinyDB.getString("email");
        String phone = tinyDB.getString("phone");
        Map<Mycart, Boolean> tags = new HashMap<>();
        for (Mycart tag : mycartArrayList) {
            tags.put(tag, true);
        }
//        Note note = new Note(title, description, priority, tags);
//        Orders order = new Orders(name,phone,email,new Timestamp(new Date()),tags);
//        notebookRef.document("14g7Y5YjuaRcmGAiikUi")
////                .collection("Child Notes").add(note);
//
//         //   orderRef.document(userID).collection("myorder").add(order).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
//                @Override
//                public void onComplete(@NonNull Task<DocumentReference> task) {
//
//                }
//            });
//        }
//      //  Orders order = new Orders(name,phone,email,new Timestamp(new Date()),mycartArrayList);
//
//
//    }

    }
}
