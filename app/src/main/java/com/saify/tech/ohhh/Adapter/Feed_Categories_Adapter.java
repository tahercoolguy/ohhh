package com.saify.tech.ohhh.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.saify.tech.ohhh.Activity.Food_Details_Activity;
import com.saify.tech.ohhh.Activity.MainActivity;
import com.saify.tech.ohhh.DataModel.Data;
import com.saify.tech.ohhh.DataModel.Feed_CategoriesDM;
import com.saify.tech.ohhh.DataModel.HistoryDM;
import com.saify.tech.ohhh.Fragments.Deep_and_Deep_1_Fragment;
import com.saify.tech.ohhh.Fragments.Deep_and_Deep_2_Fragment;
import com.saify.tech.ohhh.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Feed_Categories_Adapter extends RecyclerView.Adapter<Feed_Categories_Adapter.Programming_AdapterViewHolder> {

    private Context context;
    private ArrayList<Data> feed_categoriesDMS;

    public Feed_Categories_Adapter(Context context, ArrayList<Data> feed_categoriesDMS) {
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

        Data feed_categoriesDM = feed_categoriesDMS.get(position);

        if(feed_categoriesDM.getImage()!=null) {
            Picasso.with(context).load(feed_categoriesDMS.get(position).getImage()).into(holder.feed_category_img);
        }

//        holder.feed_category_img.setImageResource(feed_categoriesDM.getPastri_img());
//        holder.pricekwds.setText(feed_categoriesDM.getPricekwd());
//        holder.pieces.setText(feed_categoriesDM.getPieces());
        if(feed_categoriesDM.getTitle_en()!=null) {
            holder.pastriess.setText(feed_categoriesDM.getTitle_en());
        }


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
            pastriess = (TextView) itemView.findViewById(R.id.text111);
            pricekwds = (TextView) itemView.findViewById(R.id.price_kwd_Txt);
             pieces = (TextView) itemView.findViewById(R.id.pieces_Txt);
            feed_category_img=itemView.findViewById(R.id.feed_category_img);
            feed_cakeLinearLL=itemView.findViewById(R.id.feed_cakeLinearLL);
         }
    }
}
