package com.saify.tech.ohhh.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.saify.tech.ohhh.Activity.Food_Details_Activity;
import com.saify.tech.ohhh.Activity.MainActivity;
import com.saify.tech.ohhh.DataModel.Info;
import com.saify.tech.ohhh.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Feed_Categories_Adapter111 extends RecyclerView.Adapter<Feed_Categories_Adapter111.Programming_AdapterViewHolder> {
    private Context context;
    private ArrayList<Info> feed_categoriesDMS;

    public Feed_Categories_Adapter111(Context context, ArrayList<Info> feed_categoriesDMS) {
        this.context = context;
        this.feed_categoriesDMS = feed_categoriesDMS;
    }


    @NonNull
    @Override
    public Programming_AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custum_feed_category_item, parent, false);
        return new Programming_AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Programming_AdapterViewHolder holder, int position) {

        Info feed_categoriesDM = feed_categoriesDMS.get(position);


        Picasso.with(context).load(feed_categoriesDMS.get(position).getImage()).into(holder.feed_category_img);

//        holder.feed_category_img.setImageResource(feed_categoriesDM.getShop_image());
        holder.pricekwds.setText(feed_categoriesDM.getPrice());
        holder.pieces.setText(feed_categoriesDM.getQuantity());
        holder.pastriess.setText(feed_categoriesDM.getTitle_en());



        holder.feed_cakeLinearLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)context).startActivity(new Intent(context, Food_Details_Activity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return feed_categoriesDMS.size();
    }

    public class Programming_AdapterViewHolder extends RecyclerView.ViewHolder {

        RoundedImageView feed_category_img;
        TextView pastriess;
        TextView pricekwds;
        TextView pieces;
        LinearLayout feed_cakeLinearLL;


        public Programming_AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            pastriess = (TextView) itemView.findViewById(R.id.pastries_txt);
            pricekwds = (TextView) itemView.findViewById(R.id.price_kwd_Txt);
            pieces = (TextView) itemView.findViewById(R.id.pieces_Txt);
            feed_category_img=itemView.findViewById(R.id.feed_category_img);
            feed_cakeLinearLL=itemView.findViewById(R.id.feed_cakeLinearLL);
        }
    }

}
