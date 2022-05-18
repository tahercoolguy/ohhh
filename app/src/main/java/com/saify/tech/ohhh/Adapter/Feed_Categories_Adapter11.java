package com.saify.tech.ohhh.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.saify.tech.ohhh.DataModel.Info;
import com.saify.tech.ohhh.DataModel.info111;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Feed_Categories_Adapter11 extends RecyclerView.Adapter<Feed_Categories_Adapter11.ViewHolder> {

    private Context context;
    private ArrayList<info111> arrayList;
    User user;


    public Feed_Categories_Adapter11(Context context, ArrayList<info111> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        user=new User(context);
    }


    View v;
    @NonNull
    @Override
    public Feed_Categories_Adapter11.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custum_feed_category_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        Feed_Categories_Adapter11.ViewHolder vh = new Feed_Categories_Adapter11.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Feed_Categories_Adapter11.ViewHolder holder, int position) {
        setDetails(holder, position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }




    private void setDetails(Feed_Categories_Adapter11.ViewHolder viewHolder, int position) {


       info111 data = arrayList.get(position);

        Picasso.with(context).load(data.getImage()).into(viewHolder.feed_category_img);
//        viewHolder.pricekwds.setText(data.get);
        viewHolder.pieces.setText(data.getShop_name_en());
//        viewHolder.pastriess.setText(data.getShop_name_en());


//
//        holder.feed_cakeLinearLL.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ((MainActivity)context).startActivity(new Intent(context, Food_Details_Activity.class));
//            }
//        });
    }


    public static class ViewHolder   extends RecyclerView.ViewHolder{
        RoundedImageView feed_category_img;
        TextView pastriess;
        TextView pricekwds;
        TextView pieces;
        LinearLayout feed_cakeLinearLL;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pastriess = (TextView) itemView.findViewById(R.id.pastries_txt);
            pricekwds = (TextView) itemView.findViewById(R.id.price_kwd_Txt);
            pieces = (TextView) itemView.findViewById(R.id.pieces_Txt);
            feed_category_img=itemView.findViewById(R.id.feed_category_img);
            feed_cakeLinearLL=itemView.findViewById(R.id.feed_cakeLinearLL);
        }
    }
}
