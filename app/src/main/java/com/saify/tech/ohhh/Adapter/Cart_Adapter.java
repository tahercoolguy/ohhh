package com.saify.tech.ohhh.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.saify.tech.ohhh.Activity.Cart_Activity;
import com.saify.tech.ohhh.Activity.MainActivity;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.AddQuantityDM;
import com.saify.tech.ohhh.DataModel.CartDM;
import com.saify.tech.ohhh.DataModel.Info;
import com.saify.tech.ohhh.DataModel.MyCartDM;
import com.saify.tech.ohhh.DataModel.Output;
import com.saify.tech.ohhh.DataModel.RemoveCartDM;
import com.saify.tech.ohhh.DataModel.ShopssDM;
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

public class Cart_Adapter extends RecyclerView.Adapter<Cart_Adapter.Programming_AdapterViewHolder> {

    private Context context;
    private ArrayList<Info> arrayList;

    AppController appController;
    private User user;
    ConnectionDetector connectionDetector;
    DialogUtil dialogUtil;
    String product_id,cart_id;
    int selectedPosition=0;


    public Cart_Adapter(Context context, ArrayList<Info> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

        dialogUtil = new DialogUtil();
        user=new User(context);
        appController = (AppController) context.getApplicationContext();
        connectionDetector = new ConnectionDetector(context);

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
    public void onBindViewHolder(@NonNull Programming_AdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Info data = arrayList.get(position);
        if(data.getTitle()!=null && !data.getTitle().equalsIgnoreCase("")){
            holder.heading.setText(data.getTitle());}
//        holder.extra.setText(data.getExtra_meyonese());
//        holder.count0.setText(data.getItem_count());
        holder.price.setText(data.getPrice());
        if(data.getImage()!=null && !data.getImage().equalsIgnoreCase("")){
        Picasso.with(context).load(data.getImage()).into(holder.img);}
        holder.minus_plus_Txt.setText(data.getQuantity());
        product_id=data.getProduct_id();
        cart_id=data.getId();


        holder.minus_cake_Rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if (holder.count > 0) {
//                    holder.count--;
//                    holder.minus_plus_Txt.setText(String.valueOf(holder.count));
//                }

                if (connectionDetector.isConnectingToInternet()) {
                    {
                        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

//                progress = dialogUtil.showProgressDialog(Food_Details_Activity.this, getString(R.string.please_wait));

                        appController.paServices.AddQuantity(String.valueOf(user.getId()),product_id,cart_id,"decrease", new Callback<AddQuantityDM>() {
                            @Override
                            public void success(AddQuantityDM addQuantityDM, Response response) {
//                        progress.dismiss();
                                if (addQuantityDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                                    holder.minus_plus_Txt.setText(addQuantityDM.getOutput().getTotal_quantity());
                                    Helper.showToast(context,addQuantityDM.getOutput().getMessage());

                                } else
                                    Helper.showToast(context, addQuantityDM.getOutput().getMessage());
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

        holder.plus_cake_RL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                holder.count++;
//                holder.minus_plus_Txt.setText(String.valueOf(holder.count));



                if (connectionDetector.isConnectingToInternet()) {
                    {
                        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

//                progress = dialogUtil.showProgressDialog(Food_Details_Activity.this, getString(R.string.please_wait));

                        appController.paServices.AddQuantity(String.valueOf(user.getId()),product_id,cart_id,"increase", new Callback<AddQuantityDM>() {
                            @Override
                            public void success(AddQuantityDM addQuantityDM, Response response) {
//                        progress.dismiss();
                                if (addQuantityDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                                    holder.minus_plus_Txt.setText(addQuantityDM.getOutput().getTotal_quantity());
                                    Helper.showToast(context,addQuantityDM.getOutput().getMessage());

                                } else
                                    Helper.showToast(context, addQuantityDM.getOutput().getMessage());
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

        holder.cancel_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (connectionDetector.isConnectingToInternet()) {
                    {
                        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

//                progress = dialogUtil.showProgressDialog(Food_Details_Activity.this, getString(R.string.please_wait));

                        appController.paServices.RemoveCart(String.valueOf(user.getId()),product_id,cart_id, new Callback<RemoveCartDM>() {
                            @Override
                            public void success(RemoveCartDM removeCartDM, Response response) {
//                        progress.dismiss();
                                if (removeCartDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                                    arrayList.remove(selectedPosition);
                                    notifyItemRemoved(position);
                                    notifyDataSetChanged();
                                    Helper.showToast(context,removeCartDM.getOutput().getMessage());

                                } else
                                    Helper.showToast(context, "item does not deleted");
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

        TextView heading, price, count0, minus_plus_Txt;
        ImageView img,cancel_cart;
        RelativeLayout minus_cake_Rl, plus_cake_RL;
        int count = 0;

        public Programming_AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            heading = (TextView) itemView.findViewById(R.id.heading_txt);
//            extra = (TextView) itemView.findViewById(R.id.extra_mayonese);
//            count0 = (TextView) itemView.findViewById(R.id.minus_plus_Txt);
            price= (TextView) itemView.findViewById(R.id.price_Kwd_Txt);
            img = (ImageView) itemView.findViewById(R.id.img_11);
            cancel_cart= (ImageView) itemView.findViewById(R.id.cancel_cart);
            minus_cake_Rl = (RelativeLayout) itemView.findViewById(R.id.minus_cake_Rl);
            plus_cake_RL = (RelativeLayout) itemView.findViewById(R.id.plus_cake_RL);
            minus_plus_Txt = (TextView) itemView.findViewById(R.id.minus_plus_Txt);


        }
    }
}
