package com.favmartcompanny.favmarapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.favmartcompanny.favmarapplication.Fav_detail_Activity;
import com.favmartcompanny.favmarapplication.Fruits_detail_Activity;
import com.favmartcompanny.favmarapplication.R;
import com.favmartcompanny.favmarapplication.adapter.MycartAdapter;
import com.favmartcompanny.favmarapplication.adapter.MyfavAdapter;
import com.favmartcompanny.favmarapplication.model.Mycart;
import com.favmartcompanny.favmarapplication.model.Myfav;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MyfavFragment  extends Fragment implements MyfavAdapter.OnMyfavClicked {
    FirebaseFirestore db;
    FirebaseAuth auth;
    String userId;
    RecyclerView myfavtRecyclerView;
    ArrayList<Myfav> myfavArrayList = new ArrayList<>();
    MyfavAdapter myfavAdapter ;
    RelativeLayout emptyContainer,cart;
    LinearLayout cardView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.myfav,container,false);
        db = FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        cart=view.findViewById(R.id.Relative_cart);
        emptyContainer = view.findViewById(R.id.cart_empty);
        cardView = view.findViewById(R.id.card_Recycer);
        userId = auth.getCurrentUser().getUid();
        myfavtRecyclerView=view.findViewById(R.id.recycler_myfav);
        myfavAdapter=new MyfavAdapter(getContext(),myfavArrayList);
        myfavAdapter.setOnclick(this);
        myfavtRecyclerView.setAdapter(myfavAdapter);
        myfavtRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        getmyfav();
        return view;
    }
    //getting mycart from firestore
    private void getmyfav() {
        db.collection("myfavorite").whereEqualTo("userId", userId).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            Log.e("isSucessfulmyfavorite", "Yes");
                            Myfav myfav;
                            for (DocumentSnapshot snapshot : task.getResult()) {
                                String FName = snapshot.getString("FName");
                                String Image = snapshot.getString("Image");
                                String name = snapshot.getString("Name");
                                String Phone = snapshot.getString("Phone");
                                String Price = snapshot.getString("Price");
                                String unit = snapshot.getString("Unit");
                                String date = "";
                                String userid = snapshot.getString("userId");
                                String description = snapshot.getString("Description");
                                myfav=new Myfav(FName,Image,name,Phone,Price,date,userid,unit,description);
                                myfavArrayList.add(myfav);
                            }
                            Log.v("displays","all data are displayed");
                            myfavAdapter.notifyDataSetChanged();
                            if (myfavArrayList.size() > 0){
                                cardView.setVisibility(View.VISIBLE);
                                cart.setVisibility(View.VISIBLE);
                                emptyContainer.setVisibility(View.GONE);
                            }else{
                                cardView.setVisibility(View.GONE);
                                cart.setVisibility(View.GONE);
                                emptyContainer.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
    }
    @Override
    public void OnMyfavClicked(String fname, String image, String price, String description, String unity, String userid) {
        Intent intent = new Intent(getActivity(), Fav_detail_Activity.class);
        intent.putExtra("FName",fname);
        intent.putExtra("Image",image);
        intent.putExtra("Price",price);
        intent.putExtra("Description",description);
        intent.putExtra("Unit",unity);
        startActivity(intent);

    }
}
