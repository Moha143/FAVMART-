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
import com.favmartcompanny.favmarapplication.model.Mycart;
import com.favmartcompanny.favmarapplication.model.Myfav;

import java.util.ArrayList;
public class MyfavAdapter extends RecyclerView.Adapter<MyfavAdapter.MyfavViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<Myfav> myfavArrayList  = new ArrayList<>();
    OnMyfavClicked clicked;
    public MyfavAdapter(Context context, ArrayList<Myfav> mycarts){
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.myfavArrayList = mycarts;
    }
    public interface OnMyfavClicked{
        void OnMyfavClicked(String fname, String image, String price , String description, String unity, String userid);
    }
    public void setOnclick(OnMyfavClicked clicked) {
      this.clicked = clicked;
    }
    @NonNull
    @Override
    public MyfavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.myfav_tamplate,parent,false);
        return new MyfavAdapter.MyfavViewHolder(view) ;
    }
    @Override
    public void onBindViewHolder(@NonNull MyfavViewHolder holder, int position) {
        final Myfav currentMyfav = myfavArrayList.get(position);
        holder.tvFruitName.setText(currentMyfav.getFName());
        holder.tvunit.setText(currentMyfav.getUnity());
        holder.tvPrice.setText(currentMyfav.getPrice());
        Glide.with(context).load(currentMyfav.getImage()).into(holder.fruitIcon);
        holder.imgclicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked.OnMyfavClicked(currentMyfav.getFName(),currentMyfav.getImage(),currentMyfav.getPrice(),
                        currentMyfav.getDescription(),currentMyfav.getUnity(),currentMyfav.getUserId()
                        );
            }
        });
    }
    @Override
    public int getItemCount() {
        return myfavArrayList.size();
    }

    public class MyfavViewHolder extends RecyclerView.ViewHolder {
        TextView tvFruitName, tvPrice, tvunit;
        ImageView fruitIcon,imgclicked;
        public MyfavViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFruitName = itemView.findViewById(R.id.fruit_name_in_myfav);
            tvPrice = itemView.findViewById(R.id.fruit_price_in_myfav);
            fruitIcon = itemView.findViewById(R.id.fruit_img_in_myfav);
            tvunit = itemView.findViewById(R.id.fruit_unit_in_myfav);
            imgclicked = itemView.findViewById(R.id.fruit_imgicon_in_myfav);
        }
    }

}
