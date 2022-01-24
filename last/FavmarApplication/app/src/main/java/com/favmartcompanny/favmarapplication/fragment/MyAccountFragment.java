package com.favmartcompanny.favmarapplication.fragment;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.favmartcompanny.favmarapplication.Edit_Profile_Activity;
import com.favmartcompanny.favmarapplication.Feedback_Activ;
import com.favmartcompanny.favmarapplication.Feedback_Activity;
import com.favmartcompanny.favmarapplication.Fruits_detail_Activity;
import com.favmartcompanny.favmarapplication.Login_Activity;
import com.favmartcompanny.favmarapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
public class MyAccountFragment extends Fragment {
    FirebaseFirestore db;
    FirebaseAuth auth;
    String userId,uname,uemail;
    RelativeLayout Relativefeedback;
    TextView txtProfileName,txtProfileEmail,txtphone,txtfeedback;
    ImageView imgProfile, img;
    Button btnlogout,btnEditprofile;
    String name,phone,email;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.myaccount,container,false);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        userId = auth.getCurrentUser().getUid();
        btnEditprofile=view.findViewById(R.id.btnmyAEdit);
        img=view.findViewById(R.id.img_feedback);
        getUserData();
        //TextView
        txtProfileName=view.findViewById(R.id.userprofile_name);
        txtProfileEmail=view.findViewById(R.id.userprofile_email);
        txtphone=view.findViewById(R.id.userprofile_phone);
        imgProfile=view.findViewById(R.id.userprofile_img);
        //Relatives
        Relativefeedback=view.findViewById(R.id.RMYA_feedback);
        btnlogout=view.findViewById(R.id.btnmyaccLogout);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
               Intent intent =new Intent(getActivity(), Login_Activity.class);
               startActivity(intent);
            }
        });
        Relativefeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=txtProfileName.getText().toString();
                phone=txtphone.getText().toString();
                email=txtProfileEmail.getText().toString();
                Log.v("total", String.valueOf(phone));
                Log.v("total", String.valueOf(name));
                Log.v("total", String.valueOf(email));
                Intent intent =new Intent(getActivity(), Feedback_Activity.class);
                intent.putExtra("FName",name);
                intent.putExtra("FPhone",phone);
                intent.putExtra("FEmail",email);
                startActivity(intent);
            }
        });

        //edit my profile
        btnEditprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=txtProfileName.getText().toString();
                phone=txtphone.getText().toString();
                email=txtProfileEmail.getText().toString();
                Log.v("total", String.valueOf(phone));
                Log.v("total", String.valueOf(name));
                Log.v("total", String.valueOf(email));
                Intent intent=new Intent(getActivity(), Edit_Profile_Activity.class);
                intent.putExtra("PName",name);
                intent.putExtra("PPhone",phone);
                intent.putExtra("PEmail",email);
                startActivity(intent);
            }
        });

        return view;
    }
    public void getUserData()
    {
        db.collection("users").document(userId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.getResult().exists()){
                    txtProfileName.setText(task.getResult().getString("name"));
                    txtProfileEmail.setText(task.getResult().getString("email"));
                    txtphone.setText(task.getResult().getString("phone"));

                }
                /*if (task.isSuccessful()) {
                    Log.v("mmm","Could get User data");
                    DocumentSnapshot snapshot = task.getResult();
                    uname = snapshot.getString("name");
                    uemail = snapshot.getString("email");
                }

                */
                else {
                    Log.v("users","Could not get User data");
                }
            }
        });
    }
}
