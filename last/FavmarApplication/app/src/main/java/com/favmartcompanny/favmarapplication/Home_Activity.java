package com.favmartcompanny.favmarapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.favmartcompanny.favmarapplication.fragment.MyAccountFragment;
import com.favmartcompanny.favmarapplication.fragment.MycartFragment;
import com.favmartcompanny.favmarapplication.fragment.MyfavFragment;
import com.favmartcompanny.favmarapplication.fragment.ShopFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home_Activity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FragmentManager manager;
    LottieAnimationView img ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);
        manager = getSupportFragmentManager();
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        loadFragment(new ShopFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(listener);
    }

    BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment = null;
            switch (menuItem.getItemId()){
                case R.id.action_shop:
                     fragment = new ShopFragment();
                    break;
                case R.id.action_mycart:
                    fragment =new MycartFragment();
                    Log.v("eror1","sssssssss");
                    break;
                case R.id.action_favourite:
                    fragment=new MyfavFragment();
                    break;
                case R.id.action_profile:
                    fragment=new MyAccountFragment();
                    break;
            }
            return loadFragment(fragment);
        }
    };
    private boolean loadFragment(Fragment fragment){
        if (fragment != null){
            manager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
            return true;
        }
        return false;
    }
}