package com.saify.tech.ohhh.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import com.saify.tech.ohhh.Activity.Food_Details_Activity;
import com.saify.tech.ohhh.Activity.MainActivity;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.AddtoWishlistDM;
import com.saify.tech.ohhh.DataModel.Info;
import com.saify.tech.ohhh.DataModel.OffersApiDM;
import com.saify.tech.ohhh.DataModel.OffersDM;
import com.saify.tech.ohhh.DataModel.RemoveWishlistDM;
import com.saify.tech.ohhh.DataModel.ShopssDM;
import com.saify.tech.ohhh.Fragments.Deep_and_Deep_1_Fragment;
import com.saify.tech.ohhh.Fragments.Deep_and_Deep_2_Fragment;
import com.saify.tech.ohhh.Fragments.Feed_Categories_Fragment;
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

public class Offers_Adapter extends RecyclerView.Adapter<Offers_Adapter.Programming_AdapterViewHolder> {

    private Context context;
    private ArrayList<Info> arrayList;
    AppController appController;
    private User user;
    ConnectionDetector connectionDetector;
    DialogUtil dialogUtil;


    public Offers_Adapter(Context context, ArrayList<Info> arrayList) {
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
        View view = inflater.inflate(R.layout.custom_item_offers, parent, false);
        return new Programming_AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Programming_AdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Info data = arrayList.get(position);

        holder.textView.setText(data.getTitle_en());
        holder.perText.setText(data.getDiscount() +" %");
 //       holder.img.setImageResource(data.getImage());
        if(arrayList.get(position).getImage()!=null && !data.getImage().equalsIgnoreCase("")) {
            Picasso.with(context).load(data.getImage()).into(holder.img);
        }
        holder.shop_cake_RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                 ((MainActivity)context).addFragment(new Food_Details_Activity(), false);

                Intent intent=new Intent(context, Food_Details_Activity.class);
                intent.putExtra("id",data.getId());
                intent.putExtra("Rating",data.getRating());
                intent.putExtra("RatingCount",data.getRating_count());
                context.startActivity(intent);
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

        TextView textView,perText;
        ImageView img;
        RelativeLayout shop_cake_RL;
        LinearLayout likeImgAddTowishlist,dislikeImgRemovewishlist;


        public Programming_AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.offer_chocklate_cake_txt_1);
            img = (ImageView) itemView.findViewById(R.id.offer_chocklate_img_1);
            shop_cake_RL = itemView.findViewById(R.id.shop_cake_RL);
            perText = (TextView) itemView.findViewById(R.id.offer_10_percent);
            likeImgAddTowishlist=(LinearLayout)itemView.findViewById(R.id.like_LL);
            dislikeImgRemovewishlist=(LinearLayout)itemView.findViewById(R.id.dislike_LL);

        }
    }
}
