package com.favmartcompanny.favmarapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.favmartcompanny.favmarapplication.R;
import com.favmartcompanny.favmarapplication.model.Fruits;

import java.util.ArrayList;
public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.FruitsViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<Fruits> fruitsArrayList = new ArrayList<>();
    OnFruitClicked clicked;
    public FruitAdapter(Context context, ArrayList<Fruits> fruits){
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.fruitsArrayList = fruits;
    }
    public interface OnFruitClicked{
        void onFruitClicked(String name, String image, String price , String description, String unity);
    }
    public void setOnclick(OnFruitClicked clicked) {
        this.clicked = clicked;
    }
    @NonNull
    @Override
    public FruitsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view =inflater.inflate(R.layout.fruits_tamplate,parent,false);
       return new FruitsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FruitsViewHolder holder, int position) {
         final Fruits currentFruits = fruitsArrayList.get(position);
        holder.tvFruitName.setText(currentFruits.getName());
        holder.tvunity.setText(currentFruits.getunity());
        holder.tvPrice.setText(currentFruits.getPrice());
        Glide.with(context).load(currentFruits.getImage()).into(holder.fruitIcon);
        holder.imgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked.onFruitClicked(currentFruits.getName(),currentFruits.getImage(),currentFruits.getPrice(),currentFruits.getDescription(), currentFruits.getunity());
            }
        });

    }
    @Override
    public int getItemCount() {
        return fruitsArrayList.size();
    }

    public class  FruitsViewHolder extends RecyclerView.ViewHolder {
        TextView tvFruitName,tvPrice,tvunity;
        ImageView fruitIcon,imgv;
        public FruitsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFruitName=itemView.findViewById(R.id.fruits_tamplate_name);
            tvPrice=itemView.findViewById(R.id.fruits_tamplate_price);
            fruitIcon=itemView.findViewById(R.id.fruits_tamplate_image);
            imgv=itemView.findViewById(R.id.fruits_tamplate_plusicons);
            tvunity=itemView.findViewById(R.id.fruits_tamplate_unity_name);
        }
    }

}
