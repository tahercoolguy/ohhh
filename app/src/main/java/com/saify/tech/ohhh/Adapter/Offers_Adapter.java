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
import com.saify.tech.ohhh.DataModel.OffersDM;
import com.saify.tech.ohhh.DataModel.ShopssDM;
import com.saify.tech.ohhh.Fragments.Deep_and_Deep_1_Fragment;
import com.saify.tech.ohhh.Fragments.Deep_and_Deep_2_Fragment;
import com.saify.tech.ohhh.Fragments.Feed_Categories_Fragment;
import com.saify.tech.ohhh.R;

import java.util.ArrayList;

public class Offers_Adapter extends RecyclerView.Adapter<Offers_Adapter.Programming_AdapterViewHolder> {

    private Context context;
    private ArrayList<OffersDM> offersDMS;

    public Offers_Adapter(Context context, ArrayList<OffersDM> offersDMS) {
        this.context = context;
        this.offersDMS = offersDMS;
    }

    public Offers_Adapter(ArrayList<OffersDM> offersDMS) {
    }

    public Offers_Adapter(MainActivity context, ArrayList<OffersDM> offersDMS) {
    }

    @NonNull
    @Override
    public Programming_AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_item_offers, parent, false);
        return new Programming_AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Programming_AdapterViewHolder holder, int position) {

        OffersDM offersDM = offersDMS.get(position);

        holder.textView.setText(offersDM.getOffer_chocklate_1());

        holder.img.setImageResource(offersDM.getChocklate_img_1());

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
        return offersDMS.size();
    }

    public class Programming_AdapterViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView img;
        RelativeLayout shop_cake_RL;


        public Programming_AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.offer_chocklate_cake_txt_1);
            img = (ImageView) itemView.findViewById(R.id.offer_chocklate_img_1);
            shop_cake_RL = itemView.findViewById(R.id.shop_cake_RL);

        }
    }
}
