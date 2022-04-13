package com.saify.tech.ohhh.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saify.tech.ohhh.Activity.Food_Details_Activity;
import com.saify.tech.ohhh.Activity.MainActivity;
import com.saify.tech.ohhh.DataModel.HistoryDM;
import com.saify.tech.ohhh.DataModel.Info;
import com.saify.tech.ohhh.DataModel.OrderDM;
import com.saify.tech.ohhh.DataModel.Info;
import com.saify.tech.ohhh.DataModel.ShopssDM;
import com.saify.tech.ohhh.Fragments.Deep_and_Deep_1_Fragment;
import com.saify.tech.ohhh.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Shopss_Adapter extends RecyclerView.Adapter<Shopss_Adapter.Programming_AdapterViewHolder> {

    private Context context;
    private ArrayList<Info> arrayList;

    public Shopss_Adapter(Context context, ArrayList<Info> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    public Shopss_Adapter(ArrayList<Info> arrayList) {
    }

    public Shopss_Adapter(MainActivity context, ArrayList<Info> arrayList) {
    }

    @NonNull
    @Override
    public Programming_AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_item_shops, parent, false);
        return new Programming_AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Programming_AdapterViewHolder holder, int position) {

        Info info= arrayList.get(position);


        holder.dip_and_dip.setText(info.getShop_name_en());
        if(!info.getImage().equalsIgnoreCase(""))
        {
            Picasso.with(context).load(info.getImage()).into(holder.cakeshopimg);
        }


//        holder.ratingstar.setText(Info.getRating_star());
//        holder.ratingcounting.setText(Info.getRating_count());

//        holder.free_delivry.setText(shopssDM.getFree_delivery());
//        holder.minutes_time.setText(shopssDM.getDelivery_time());


        holder.delivery_boy.setImageResource(R.drawable.ic_delivery_boy);
        holder.time_img.setImageResource(R.drawable.ic_time);

        holder.shop_cake_Rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((MainActivity)context).addFragment(new Deep_and_Deep_1_Fragment(),false);

              }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Programming_AdapterViewHolder extends RecyclerView.ViewHolder {

        TextView ratingstar,ratingcounting,dip_and_dip,free_delivry,minutes_time;
//        Button desert,kuwaiti;
        ImageView star,delivery_boy,time_img,cakeshopimg;
//        LinearLayout like;

        RelativeLayout shop_cake_Rl;


        public Programming_AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            ratingstar = (TextView) itemView.findViewById(R.id.rating_starTxt);
            ratingcounting = (TextView) itemView.findViewById(R.id.rating_count_Txt);
            dip_and_dip = (TextView) itemView.findViewById(R.id.dip_and_dip_Txt);
            free_delivry = (TextView) itemView.findViewById(R.id.free_delivery);
            minutes_time = (TextView) itemView.findViewById(R.id.deliver_time);

            star = (ImageView) itemView.findViewById(R.id.star);
//            like = (LinearLayout) itemView.findViewById(R.id.like_LL);
            delivery_boy = (ImageView) itemView.findViewById(R.id.dlivery_Img);
            time_img = (ImageView) itemView.findViewById(R.id.timer_delivery);
            cakeshopimg=(ImageView) itemView.findViewById(R.id.cake_shop_img1111);
            shop_cake_Rl=itemView.findViewById(R.id.shop_cake_Rl);

//            desert=(Button) itemView.findViewById(R.id.desert_Btn);
//            kuwaiti=(Button) itemView.findViewById(R.id.kuwait_Btn);
        }
    }
}

