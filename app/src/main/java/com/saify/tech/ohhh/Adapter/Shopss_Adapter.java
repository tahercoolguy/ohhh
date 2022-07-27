package com.saify.tech.ohhh.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.google.firebase.iid.FirebaseInstanceId;
import com.saify.tech.ohhh.Activity.Food_Details_Activity;
import com.saify.tech.ohhh.Activity.MainActivity;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.AddtoWishlistDM;
import com.saify.tech.ohhh.DataModel.HistoryDM;
import com.saify.tech.ohhh.DataModel.Info;
import com.saify.tech.ohhh.DataModel.OrderDM;
import com.saify.tech.ohhh.DataModel.Info;
import com.saify.tech.ohhh.DataModel.RemoveWishlistDM;
import com.saify.tech.ohhh.DataModel.ShopssDM;
import com.saify.tech.ohhh.Fragments.Deep_and_Deep_1_Fragment;
import com.saify.tech.ohhh.Helper.DialogUtil;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;
import com.saify.tech.ohhh.Utils.ConnectionDetector;
import com.saify.tech.ohhh.Utils.Helper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Shopss_Adapter extends RecyclerView.Adapter<Shopss_Adapter.Programming_AdapterViewHolder> {

    private Context context;
    private ArrayList<Info> arrayList;
    AppController appController;
    private User user;
    ConnectionDetector connectionDetector;
    DialogUtil dialogUtil;

    public Shopss_Adapter(Context context, ArrayList<Info> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        dialogUtil = new DialogUtil();
        user=new User(context);
        appController = (AppController) context.getApplicationContext();
        connectionDetector = new ConnectionDetector(context);
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
        holder.free_delivry.setText(info.getFree_delivery());
        if(info.getPrepartion_time()!=null){
        holder.deliverTime.setText(info.getPrepartion_time());}
        holder.rating.setText(info.getRating());
        holder.rating_Count.setText(info.getRating_count());

        holder.shop_cake_Rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Deep_and_Deep_1_Fragment deep_and_deep_1_fragment=new Deep_and_Deep_1_Fragment();
                Bundle bd = new Bundle();
                bd.putString("id", info.getId());
                bd.putString("rating",info.getRating());
                bd.putString("ratingCount",info.getRating_count());
                bd.putString("free_delivery",info.getFree_delivery());

                if(info.getPrepartion_time()!=null){
                bd.putString("deliver_time",info.getPrepartion_time());}

                deep_and_deep_1_fragment.setArguments(bd);
                ((MainActivity)context).addFragment(deep_and_deep_1_fragment, false);

//                ((MainActivity)context).addFragment(new Deep_and_Deep_1_Fragment(),false);
            }});

        holder.likeImgAddTowishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (connectionDetector.isConnectingToInternet()) {
                    {
                        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

//                progress = dialogUtil.showProgressDialog(Food_Details_Activity.this, getString(R.string.please_wait));

                        appController.paServices.AddtoWishlistShop(String.valueOf(user.getId()),info.getId(), new Callback<AddtoWishlistDM>() {
                            @Override
                            public void success(AddtoWishlistDM addtoWishlistDM, Response response) {
//                        progress.dismiss();
                                if (addtoWishlistDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                                    holder.dislikeImgRemovewishlist.setVisibility(View.VISIBLE);
                                    holder.likeImgAddTowishlist.setVisibility(View.GONE);

                                    Helper.showToast(context,addtoWishlistDM.getOutput().getMessage());

                                } else
                                    Helper.showToast(context, "item does not Add to Wishlist");
                            }

                            @Override
                            public void failure(RetrofitError error) {

                                Log.e("String", error.toString());
                            }
                        });
                    }
                } else
                    Helper.showToast(context, String.valueOf(R.string.no_internet_connection));
            }
        });


        holder.dislikeImgRemovewishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (connectionDetector.isConnectingToInternet()) {
                    {
                        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

//                progress = dialogUtil.showProgressDialog(Food_Details_Activity.this, getString(R.string.please_wait));

                        appController.paServices.RemoveWishlistShop(String.valueOf(user.getId()),info.getId(), new Callback<RemoveWishlistDM>() {
                            @Override
                            public void success(RemoveWishlistDM removeWishlistDM, Response response) {
//                        progress.dismiss();
                                if (removeWishlistDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                                    holder.dislikeImgRemovewishlist.setVisibility(View.GONE);
                                    holder.likeImgAddTowishlist.setVisibility(View.VISIBLE);

                                    Helper.showToast(context,removeWishlistDM.getOutput().getMessage());

                                } else
                                    Helper.showToast(context, "item does not Remove From Wishlist");
                            }

                            @Override
                            public void failure(RetrofitError error) {

                                Log.e("String", error.toString());
                            }
                        });
                    }
                } else
                    Helper.showToast(context, String.valueOf(R.string.no_internet_connection));
            }
        });




    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Programming_AdapterViewHolder extends RecyclerView.ViewHolder {

        TextView ratingstar,ratingcounting,dip_and_dip,free_delivry,minutes_time,rating_Count,freeDelivery,deliverTime,rating;
//        Button desert,kuwaiti;
        ImageView star,delivery_boy,time_img,cakeshopimg;
//        LinearLayout like;

        RelativeLayout shop_cake_Rl;
        RelativeLayout likeImgAddTowishlist,dislikeImgRemovewishlist;


        public Programming_AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            ratingstar = (TextView) itemView.findViewById(R.id.rating_starTxt);
            ratingcounting = (TextView) itemView.findViewById(R.id.rating_count_Txt);
            dip_and_dip = (TextView) itemView.findViewById(R.id.dip_and_dip_Txt);
            free_delivry = (TextView) itemView.findViewById(R.id.free_delivery);
            deliverTime = (TextView) itemView.findViewById(R.id.deliver_time);
            rating= (TextView) itemView.findViewById(R.id.rating_starTxt);
            rating_Count = (TextView) itemView.findViewById(R.id.rating_count_Txt);

            star = (ImageView) itemView.findViewById(R.id.star);
//            like = (LinearLayout) itemView.findViewById(R.id.like_LL);
            delivery_boy = (ImageView) itemView.findViewById(R.id.dlivery_Img);
            time_img = (ImageView) itemView.findViewById(R.id.timer_delivery);
            cakeshopimg=(ImageView) itemView.findViewById(R.id.cake_shop_img1111);
            shop_cake_Rl=itemView.findViewById(R.id.shop_cake_Rl);

            likeImgAddTowishlist=(RelativeLayout) itemView.findViewById(R.id.like_LL);
            dislikeImgRemovewishlist=(RelativeLayout) itemView.findViewById(R.id.dislike_LL);

//            desert=(Button) itemView.findViewById(R.id.desert_Btn);
//            kuwaiti=(Button) itemView.findViewById(R.id.kuwait_Btn);
        }
    }
}

