package com.saify.tech.ohhh.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saify.tech.ohhh.Activity.Food_Details_Activity;
import com.saify.tech.ohhh.Activity.MainActivity;
import com.saify.tech.ohhh.DataModel.Info;
import com.saify.tech.ohhh.DataModel.OffersDM;
import com.saify.tech.ohhh.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BestFromDesert_Adapter  extends RecyclerView.Adapter<BestFromDesert_Adapter.Programming_AdapterViewHolder>{

    private Context context;
    private ArrayList<Info> arrayList;

    public BestFromDesert_Adapter(Context context, ArrayList<Info> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public BestFromDesert_Adapter.Programming_AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_item_offers, parent, false);
        return new BestFromDesert_Adapter.Programming_AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BestFromDesert_Adapter.Programming_AdapterViewHolder holder, int position) {

        Info data = arrayList.get(position);

        holder.textView.setText(data.getTitle_en());

        holder.perText.setText(data.getDiscount() +" %");
       if(data.getDiscount()==null)
        {
            holder.perText.setVisibility(View.GONE);
        }
        //       holder.img.setImageResource(data.getImage());
        if(arrayList.get(position).getImage()!=null && !data.getImage().equalsIgnoreCase("")) {
            Picasso.with(context).load(data.getImage()).into(holder.img);
        }

        holder.shop_cake_RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                 ((MainActivity)context).addFragment(new Food_Details_Activity(), false);
                ((MainActivity) context).startActivity(new Intent(context, Food_Details_Activity.class));
            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Programming_AdapterViewHolder extends RecyclerView.ViewHolder {

        TextView textView,perText;
        ImageView img;
        RelativeLayout shop_cake_RL;


        public Programming_AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.offer_chocklate_cake_txt_1);
            img = (ImageView) itemView.findViewById(R.id.offer_chocklate_img_1);
            shop_cake_RL = itemView.findViewById(R.id.shop_cake_RL);
            perText = (TextView) itemView.findViewById(R.id.offer_10_percent);

        }
    }

}
