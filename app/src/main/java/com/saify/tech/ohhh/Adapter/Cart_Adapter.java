package com.saify.tech.ohhh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saify.tech.ohhh.Activity.MainActivity;
import com.saify.tech.ohhh.DataModel.CartDM;
import com.saify.tech.ohhh.DataModel.Info;
import com.saify.tech.ohhh.DataModel.Output;
import com.saify.tech.ohhh.DataModel.ShopssDM;
import com.saify.tech.ohhh.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Cart_Adapter extends RecyclerView.Adapter<Cart_Adapter.Programming_AdapterViewHolder> {

    private Context context;
    private ArrayList<Info> arrayList;


    public Cart_Adapter(Context context, ArrayList<Info> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    public Cart_Adapter(ArrayList<Info> arrayList) {
    }

    public Cart_Adapter(MainActivity context, ArrayList<Info> arrayList) {
    }

    @NonNull
    @Override
    public Programming_AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custum_item_cart, parent, false);
        return new Programming_AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Programming_AdapterViewHolder holder, int position) {

        Info data = arrayList.get(position);
        if(!data.getTitle().equalsIgnoreCase("")){
        holder.heading.setText(data.getTitle());}
//        holder.extra.setText(data.getExtra_meyonese());
//        holder.count0.setText(data.getItem_count());
        holder.price.setText(data.getPrice());
        if(!data.getImage().equalsIgnoreCase("")){
        Picasso.with(context).load(data.getImage()).into(holder.img);}
        holder.minus_plus_Txt.setText(data.getQuantity());


        holder.minus_cake_Rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (holder.count > 0) {
                    holder.count--;
                    holder.minus_plus_Txt.setText(String.valueOf(holder.count));
                }
            }
        });

        holder.plus_cake_RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.count++;
                holder.minus_plus_Txt.setText(String.valueOf(holder.count));
            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Programming_AdapterViewHolder extends RecyclerView.ViewHolder {

        TextView heading, price, count0, minus_plus_Txt;
        ImageView img;
        RelativeLayout minus_cake_Rl, plus_cake_RL;
        int count = 0;

        public Programming_AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            heading = (TextView) itemView.findViewById(R.id.heading_txt);
//            extra = (TextView) itemView.findViewById(R.id.extra_mayonese);
//            count0 = (TextView) itemView.findViewById(R.id.minus_plus_Txt);
            price= (TextView) itemView.findViewById(R.id.price_Kwd_Txt);
            img = (ImageView) itemView.findViewById(R.id.img_11);
            minus_cake_Rl = (RelativeLayout) itemView.findViewById(R.id.minus_cake_Rl);
            plus_cake_RL = (RelativeLayout) itemView.findViewById(R.id.plus_cake_RL);
            minus_plus_Txt = (TextView) itemView.findViewById(R.id.minus_plus_Txt);


        }
    }
}
