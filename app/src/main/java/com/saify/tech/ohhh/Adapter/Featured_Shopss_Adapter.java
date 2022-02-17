package com.saify.tech.ohhh.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saify.tech.ohhh.Activity.MainActivity;
import com.saify.tech.ohhh.DataModel.ShopssDM;
import com.saify.tech.ohhh.R;

import java.util.ArrayList;

public class Featured_Shopss_Adapter extends RecyclerView.Adapter<Featured_Shopss_Adapter.Programming_AdapterViewHolder> {

    private Context context;
    private ArrayList<ShopssDM> shopssDMS;

    public Featured_Shopss_Adapter(Context context, ArrayList<ShopssDM> shopssDMS) {
        this.context = context;
        this.shopssDMS = shopssDMS;
    }

    public Featured_Shopss_Adapter(ArrayList<ShopssDM> shopssDMS) {
    }

    public Featured_Shopss_Adapter(MainActivity context, ArrayList<ShopssDM> shopssDMS) {
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

        ShopssDM shopssDM = shopssDMS.get(position);

        holder.ratingstar.setText(shopssDM.getRating_star());
        holder.ratingcounting.setText(shopssDM.getRating_count());
        holder.dip_and_dip.setText(shopssDM.getDip_and_dip());
        holder.free_delivry.setText(shopssDM.getFree_delivery());
        holder.minutes_time.setText(shopssDM.getDelivery_time());


        holder.delivery_boy.setImageResource(R.drawable.ic_delivery_boy);
        holder.time_img.setImageResource(R.drawable.ic_time);
        holder.cake.setImageResource(shopssDM.getCake_img());




    }

    @Override
    public int getItemCount() {
        return shopssDMS.size();
    }

    public class Programming_AdapterViewHolder extends RecyclerView.ViewHolder {

        TextView ratingstar,ratingcounting,dip_and_dip,free_delivry,minutes_time;
//        Button desert,kuwaiti;
        ImageView star,delivery_boy,time_img,cake;
//        LinearLayout like;


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
            cake = (ImageView) itemView.findViewById(R.id.cake_shop_img1);


//            desert=(Button) itemView.findViewById(R.id.desert_Btn);
//            kuwaiti=(Button) itemView.findViewById(R.id.kuwait_Btn);
        }
    }
}
