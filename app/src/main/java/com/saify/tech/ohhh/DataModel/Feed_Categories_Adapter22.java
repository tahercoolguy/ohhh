package com.saify.tech.ohhh.DataModel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.makeramen.roundedimageview.RoundedImageView;
import com.saify.tech.ohhh.Activity.Food_Details_Activity;
import com.saify.tech.ohhh.Adapter.Feed_Categories_Adapter;
import com.saify.tech.ohhh.Adapter.Feed_Categories_Adapter11;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Feed_Categories_Adapter22 extends RecyclerView.Adapter<Feed_Categories_Adapter22.ViewHolder> {

    private Context context;
    private ArrayList<Items> arrayList;
    User user;


    public Feed_Categories_Adapter22(Context context, ArrayList<Items> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        user=new User(context);
    }


    View v;
    @NonNull
    @Override
    public Feed_Categories_Adapter22.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custum_feed_category_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        Feed_Categories_Adapter22.ViewHolder vh = new Feed_Categories_Adapter22.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Feed_Categories_Adapter22.ViewHolder holder, int position) {
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




    private void setDetails(Feed_Categories_Adapter22.ViewHolder viewHolder, int position) {


        Items data = arrayList.get(position);
       if(data!=null){
        if (!data.getImage().equalsIgnoreCase("") && data.getImage()!=null) {
            Picasso.with(context).load(data.getImage()).into(viewHolder.feed_category_img);
        }
            viewHolder.pricekwds.setText(data.getPrice()+" KD");
            viewHolder.text.setText(data.getTitle_en());
//            viewHolder.quantity.setText(data.getQuantity());

        viewHolder.feed_cakeLinearLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context, Food_Details_Activity.class);
                intent.putExtra("id",data.getId());
                context.startActivity(intent);

    }
        });

    }}

    public static class ViewHolder   extends RecyclerView.ViewHolder{
        RoundedImageView feed_category_img;

        TextView text,quantity,pricekwds;
//        RecyclerView sub_category_2_Rcv;
        LinearLayout feed_cakeLinearLL;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            quantity = (TextView) itemView.findViewById(R.id.quantity);
            pricekwds = (TextView) itemView.findViewById(R.id.price_kwd_Txt11);
            text = (TextView) itemView.findViewById(R.id.text111);
//            sub_category_2_Rcv=itemView.findViewById(R.id.sub_category_2_Rcv);
            feed_category_img=itemView.findViewById(R.id.feed_category_img);
            feed_cakeLinearLL=itemView.findViewById(R.id.feed_cakeLinearLL);
        }
    }

}
