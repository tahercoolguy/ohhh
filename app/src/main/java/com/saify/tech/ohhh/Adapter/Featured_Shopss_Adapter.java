package com.saify.tech.ohhh.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.saify.tech.ohhh.Activity.MainActivity;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.AddtoWishlistDM;
import com.saify.tech.ohhh.DataModel.Info;
import com.saify.tech.ohhh.DataModel.RemoveWishlistDM;
import com.saify.tech.ohhh.DataModel.ShopssDM;
import com.saify.tech.ohhh.Fragments.Deep_and_Deep_1_Fragment;
import com.saify.tech.ohhh.Fragments.Feed_Categories_Fragment;
import com.saify.tech.ohhh.Fragments.Fragment_Home_Screen;
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

public class Featured_Shopss_Adapter extends RecyclerView.Adapter<Featured_Shopss_Adapter.Programming_AdapterViewHolder> {

    private Context context;
    private ArrayList<Info> arrayList;
    Intent intent;
    AppController appController;
    private User user;
    ConnectionDetector connectionDetector;
    DialogUtil dialogUtil;

    public Featured_Shopss_Adapter(Context context, ArrayList<Info> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        dialogUtil = new DialogUtil();
        user=new User(context);
        appController = (AppController) context.getApplicationContext();
        connectionDetector = new ConnectionDetector(context);
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

        holder.freeDelivery.setText(data.getFree_delivery());
        holder.deliverTime.setText(data.getPrepartion_time());
        holder.rating.setText(data.getRating());
        holder.rating_Count.setText(data.getRating_count());


        holder.shop_cake_RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent=new Intent(context,Deep_and_Deep_1_Fragment.class);
//                intent.putExtra("id",data.getId());
//                context.startActivity(intent);
              Deep_and_Deep_1_Fragment deep_and_deep_1_fragment=new Deep_and_Deep_1_Fragment();
                Bundle bd = new Bundle();
                bd.putString("id", data.getId());
               deep_and_deep_1_fragment.setArguments(bd);
                ((MainActivity)context).addFragment(deep_and_deep_1_fragment, false);

            }
        });


        holder.likeImgAddTowishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (connectionDetector.isConnectingToInternet()) {
                    {
                        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

//                progress = dialogUtil.showProgressDialog(Food_Details_Activity.this, getString(R.string.please_wait));

                        appController.paServices.AddtoWishlist(String.valueOf(user.getId()),data.getId(),data.getShop_id(), new Callback<AddtoWishlistDM>() {
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

                        appController.paServices.RemoveWishlist(String.valueOf(user.getId()),data.getId(),data.getShop_id(), new Callback<RemoveWishlistDM>() {
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

        TextView text1,rating_Count,freeDelivery,deliverTime,rating;
//        Button desert,kuwaiti;
        ImageView img;
//        LinearLayout like;

        RelativeLayout shop_cake_RL;
        LinearLayout likeImgAddTowishlist,dislikeImgRemovewishlist;


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
            likeImgAddTowishlist=(LinearLayout)itemView.findViewById(R.id.like_LL);
            dislikeImgRemovewishlist=(LinearLayout)itemView.findViewById(R.id.dislike_LL);
            freeDelivery = (TextView) itemView.findViewById(R.id.free_delivery);
           deliverTime = (TextView) itemView.findViewById(R.id.deliver_time);
           rating= (TextView) itemView.findViewById(R.id.rating_starTxt);
            rating_Count = (TextView) itemView.findViewById(R.id.rating_count_Txt);



//            desert=(Button) itemView.findViewById(R.id.desert_Btn);
//            kuwaiti=(Button) itemView.findViewById(R.id.kuwait_Btn);
        }
    }
}
