package com.favmartcompanny.favmarapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.favmartcompanny.favmarapplication.R;
import com.favmartcompanny.favmarapplication.model.Fruits;
import com.favmartcompanny.favmarapplication.model.Vegetable;

import java.util.ArrayList;
public class VegetableAdapter  extends RecyclerView.Adapter<VegetableAdapter.vegetableViewHolder>{
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<Vegetable> vegetableArrayList = new ArrayList<>();
    VegetableAdapter.OnVegetableClicked clicked;
    public VegetableAdapter(Context context, ArrayList<Vegetable> vegetables){
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.vegetableArrayList = vegetables;
    }
    public interface OnVegetableClicked{
        void OnVegetableClicked(String name, String image, String price , String description, String unity);
    }
    public void setOnVclick(VegetableAdapter.OnVegetableClicked clicked) {
        this.clicked = clicked;
    }
    @NonNull
    @Override
    public vegetableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =inflater.inflate(R.layout.vegetable_tamplate,parent,false);
        return new VegetableAdapter.vegetableViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull vegetableViewHolder holder, int position) {
        final Vegetable currentVegetable = vegetableArrayList.get(position);
        holder.tvVegetableName.setText(currentVegetable.getVname());
        holder.tvVegetablePrice.setText(currentVegetable.getVprice());
        holder.tvunity.setText(currentVegetable.getVunity());
        Glide.with(context).load(currentVegetable.getVimage()).into(holder.VegetableIcon);
        holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked.OnVegetableClicked(currentVegetable.getVname(),currentVegetable.getVimage(),currentVegetable.getVprice(),currentVegetable.getVdescription(),currentVegetable.getVunity());
                 }
        });
    }
    @Override
    public int getItemCount() {
        return vegetableArrayList.size();
    }
    public class vegetableViewHolder extends RecyclerView.ViewHolder {
        TextView tvVegetableName,tvVegetablePrice,tvdescription,tvunity;
        ImageView VegetableIcon,icon;
        public vegetableViewHolder(@NonNull View itemView) {
            super(itemView);
            tvVegetableName=itemView.findViewById(R.id.vegetable_tamplate_name);
            tvVegetablePrice=itemView.findViewById(R.id.vegetable_tamplate_price);
            VegetableIcon=itemView.findViewById(R.id.vegetable_tamplate_image);
            tvunity=itemView.findViewById(R.id.vegetable_tamplate_unity);
            icon=itemView.findViewById(R.id.vegetable_tamplate_plusicons);
        }
    }
}