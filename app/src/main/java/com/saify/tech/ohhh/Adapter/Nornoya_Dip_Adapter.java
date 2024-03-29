package com.saify.tech.ohhh.Adapter;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.saify.tech.ohhh.Activity.Address_Activity;
import com.saify.tech.ohhh.Activity.Cart_Activity;
import com.saify.tech.ohhh.Activity.MainActivity;
import com.saify.tech.ohhh.Activity.Payment_Activity;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.CartDM;
import com.saify.tech.ohhh.DataModel.Info;
import com.saify.tech.ohhh.DataModel.MyCartDM;
import com.saify.tech.ohhh.DataModel.Output;
import com.saify.tech.ohhh.Helper.DialogUtil;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;
import com.saify.tech.ohhh.Utils.ConnectionDetector;
import com.saify.tech.ohhh.Utils.Helper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ozaydin.serkan.com.image_zoom_view.Dialog;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Nornoya_Dip_Adapter extends RecyclerView.Adapter<Nornoya_Dip_Adapter.Programming_AdapterViewHolder> {

    private Context context;
    private ArrayList<Output> arrayList;

    AppController appController;
    private User user;
    ConnectionDetector connectionDetector;
    DialogUtil dialogUtil;
    String ShopID;
    Dialog progress;
    String user_id;
    String shopImg,shopName;


    public Nornoya_Dip_Adapter(Context context, ArrayList<Output> arrayList) {
        this.context = context;
        this.arrayList =arrayList;
        user = new User(context);
        dialogUtil = new DialogUtil();
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(getApplicationContext());
     }



    @NonNull
    @Override
    public Programming_AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_item_nornoya_dip_and_dip_cart, parent, false);
        return new Programming_AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Programming_AdapterViewHolder holder, int position) {

        Output data =arrayList.get(position);
        Nornoya_Dip_Adapt();

        holder.nornoya.setText(shopName);
        Picasso.with(context).load(shopImg).into(holder.nornoya_Img);

        holder.checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(context, Address_Activity.class);
                intent.putExtra("Shop_id", data.getInfo().get(0).getShop_id());
                intent.putExtra("Sub_total", data.getSub_total()+" KD");
                intent.putExtra("Applied_coupon", data.getInfo().get(0).getApplied_coupon());
                context.startActivity(intent);
                ((Activity)context).finish();


            }
        });
//
        ArrayList<CartDM> cartDMS = new ArrayList<>();
        cartDMS.add(new CartDM("Pastries", "Extra Mayonese", "10.000 KWD", "01","0", R.drawable.nornoya_1));
        cartDMS.add(new CartDM("Pastries", "Extra Mayonese", "10.000 KWD", "02","0", R.drawable.nornoya_2));
        cartDMS.add(new CartDM("Pastries", "Extra Mayonese", "10.000 KWD", "03","0", R.drawable.nornoya_3));

        Cart_Adapter dm2 = new Cart_Adapter(context, data.getInfo());

        LinearLayoutManager l2 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        holder.nornoya_sub_item.setLayoutManager(l2);
        holder.nornoya_sub_item.setAdapter(dm2);

    }

    private void Nornoya_Dip_Adapt() {
//        ArrayList<Nornoya_Dip_Dm> nornoya_dip_dms = new ArrayList<>();
//        nornoya_dip_dms.add(new Nornoya_Dip_Dm("Nornoya", R.drawable.nornoya));
//        nornoya_dip_dms.add(new Nornoya_Dip_Dm("Dip N Dip", R.drawable.deep_small));


        if (connectionDetector.isConnectingToInternet()) {
            {
                String refreshedToken = FirebaseInstanceId.getInstance().getToken();

//                progress = dialogUtil.showProgressDialog(Cart_Activity.this, getString(R.string.please_wait));
                user_id =   String.valueOf(user.getId());
                appController.paServices.MyCart(user_id, new Callback<MyCartDM>() {
                    @Override
                    public void success(MyCartDM myCartDM, Response response) {
//                        progress.dismiss();
                        if (myCartDM.getOutput().get(0).getSuccess().equalsIgnoreCase("1")) {
                            ShopID = myCartDM.getOutput().get(0).getInfo().get(0).getShop_id();
                            shopImg=myCartDM.getOutput().get(0).getInfo().get(0).getShop_image();
                            shopName=myCartDM.getOutput().get(0).getInfo().get(0).getShop_name();


                        } else

                            Helper.showToast(context, context.getString(R.string.Api_data_not_found));
                    }

                    @Override
                    public void failure(RetrofitError error) {
//                        progress.dismiss();
                        Log.e("String", error.toString());
                    }
                });

            }
        } else
            Helper.showToast(context, context.getString(R.string.no_internet_connection));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Programming_AdapterViewHolder extends RecyclerView.ViewHolder {

        TextView nornoya;

        ImageView nornoya_Img;
        TextView checkout;
        RecyclerView nornoya_sub_item;



        public Programming_AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            nornoya = (TextView) itemView.findViewById(R.id.nornoya_Txt);

            nornoya_Img = (ImageView) itemView.findViewById(R.id.nornoya_img);
            checkout=itemView.findViewById(R.id.checkout);
            nornoya_sub_item=itemView.findViewById(R.id.nornoya_sub_item);
        }
    }
}