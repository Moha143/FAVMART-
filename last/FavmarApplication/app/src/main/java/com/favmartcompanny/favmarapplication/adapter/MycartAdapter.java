package com.favmartcompanny.favmarapplication.adapter;

import android.content.Context;
import android.util.Log;
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
import com.favmartcompanny.favmarapplication.model.Mycart;

import java.util.ArrayList;
public class MycartAdapter extends RecyclerView.Adapter<MycartAdapter.MycartViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    MycartAdapter.OnDeleteClicked clicked;
    OnTotalrequest Totalrequest;
    double totalofeach=0;
    private ArrayList<Mycart> mycartArrayList  = new ArrayList<>();
  //  FruitAdapter.OnFruitClicked clicked;
    public MycartAdapter(Context context, ArrayList<Mycart> mycarts){
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.mycartArrayList = mycarts;
    }
    public interface OnDeleteClicked{
        void OnDeleteClicked( String id,String fname,String name,
                             String phone,String price,String
                                     date,String userId,String unity,
                             String TotalPrice,String image , String quantity);
    }
    public interface OnTotalrequest{
        void OnTotal(double totalofallprices);
    }
    public void setOnclick(OnDeleteClicked clicked) {
        this.clicked = clicked;
    }
    @NonNull
    @Override
    public MycartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =inflater.inflate(R.layout.mycart_tamplate,parent,false);
        return new  MycartAdapter.MycartViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MycartViewHolder holder, int position) {
        final Mycart currentMycart = mycartArrayList.get(position);
        holder.tvFruitName.setText(currentMycart.getFName());
        holder.tvunit_mycart.setText(currentMycart.getUnity());
        holder.tvPrice.setText(currentMycart.getPrice());
       holder.tvTotalPrice.setText(currentMycart.getTotalPrice());
       totalofeach= totalofeach +Double.parseDouble(currentMycart.getTotalPrice());
        Log.e("totalapdaters", String.valueOf(totalofeach));
       Totalrequest.OnTotal(totalofeach);
       Glide.with(context).load(currentMycart.getImage()).into(holder.fruitIcon);
       holder.daleteicon.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               clicked.OnDeleteClicked(currentMycart.getId(),currentMycart.getFName(),currentMycart.getName()
                       ,currentMycart.getPhone(),currentMycart.getPrice(),currentMycart.getDate(),
                       currentMycart.getUserId(),currentMycart.getUnity(),
                       currentMycart.getTotalPrice(),currentMycart.getImage(),currentMycart.getUserId());
           }
       });
    }
    @Override
    public int getItemCount() {
        return  mycartArrayList.size();
    }
    public class MycartViewHolder extends RecyclerView.ViewHolder {
        TextView tvFruitName, tvPrice, tvunit_mycart,tvTotalPrice;
        ImageView fruitIcon,daleteicon;
        LinearLayout linearLayout;
        public MycartViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFruitName = itemView.findViewById(R.id.fruit_name_in_mycart);
            tvPrice = itemView.findViewById(R.id.fruit_price_in_mycart);
            fruitIcon = itemView.findViewById(R.id.fruit_img_in_mycart);
            tvunit_mycart = itemView.findViewById(R.id.fruit_unit_in_mycart);
            tvTotalPrice = itemView.findViewById(R.id.fruit_total_in_mycart);
            daleteicon=itemView.findViewById(R.id.fruit_cancel_in_mycart);
           // tvquanity = itemView.findViewById(R.id.fruit_quantity_in_mycart);
        }
    }

    public void setTotalrequest(OnTotalrequest totalrequest) {
        this.Totalrequest = totalrequest;
    }
}

