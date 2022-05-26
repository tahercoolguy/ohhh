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
import com.saify.tech.ohhh.DataModel.Info;
import com.saify.tech.ohhh.DataModel.ShopssDM;
import com.saify.tech.ohhh.Fragments.Deep_and_Deep_1_Fragment;
import com.saify.tech.ohhh.Fragments.Feed_Categories_Fragment;
import com.saify.tech.ohhh.Fragments.Fragment_Home_Screen;
import com.saify.tech.ohhh.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Featured_Shopss_Adapter extends RecyclerView.Adapter<Featured_Shopss_Adapter.Programming_AdapterViewHolder> {

    private Context context;
    private ArrayList<Info> arrayList;

    public Featured_Shopss_Adapter(Context context, ArrayList<Info> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }



    @NonNull
    @Override
    public Programming_AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_item_featured_shops, parent, false);
        return new Programming_AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Programming_AdapterViewHolder holder, int position) {

        Info data= arrayList.get(position);

//        holder.ratingstar.setText(data.getRating_star());
//        holder.ratingcounting.setText(shopssDM.getRating_count());
//        holder.free_delivry.setText(shopssDM.getFree_delivery());
//        holder.minutes_time.setText(shopssDM.getDelivery_time());
//        holder.delivery_boy.setImageResource(R.drawable.ic_delivery_boy);
//        holder.time_img.setImageResource(R.drawable.ic_time);

//        holder.img.setImageResource(data.getImage());
        if(arrayList.get(position).getImage()!=null && !data.getImage().equalsIgnoreCase("")) {
            Picasso.with(context).load(data.getImage()).into(holder.img);
        }
        holder.text1.setText(data.getShop_name_en());


        holder.shop_cake_RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)context).addFragment(new Deep_and_Deep_1_Fragment(), false);

            }
        });




    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Programming_AdapterViewHolder extends RecyclerView.ViewHolder {

        TextView text1;
//        Button desert,kuwaiti;
        ImageView img;
//        LinearLayout like;

        RelativeLayout shop_cake_RL;


        public Programming_AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
//            ratingstar = (TextView) itemView.findViewById(R.id.rating_starTxt);
//            ratingcounting = (TextView) itemView.findViewById(R.id.rating_count_Txt);
//            dip_and_dip = (TextView) itemView.findViewById(R.id.dip_and_dip_Txt);
//            free_delivry = (TextView) itemView.findViewById(R.id.free_delivery);
//            minutes_time = (TextView) itemView.findViewById(R.id.deliver_time);
//
//            star = (ImageView) itemView.findViewById(R.id.star);
////            like = (LinearLayout) itemView.findViewById(R.id.like_LL);
//            delivery_boy = (ImageView) itemView.findViewById(R.id.dlivery_Img);
//            time_img = (ImageView) itemView.findViewById(R.id.timer_delivery);

            img = (ImageView) itemView.findViewById(R.id.img1);
            text1 = (TextView) itemView.findViewById(R.id.text1);
            shop_cake_RL=itemView.findViewById(R.id.shop_cake_RL);


//            desert=(Button) itemView.findViewById(R.id.desert_Btn);
//            kuwaiti=(Button) itemView.findViewById(R.id.kuwait_Btn);
        }
    }
}
