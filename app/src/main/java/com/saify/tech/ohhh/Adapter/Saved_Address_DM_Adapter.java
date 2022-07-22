package com.saify.tech.ohhh.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saify.tech.ohhh.Activity.Address_Activity;
import com.saify.tech.ohhh.Activity.SaveAddressActivity;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.HistoryDM;
import com.saify.tech.ohhh.DataModel.Info;
import com.saify.tech.ohhh.DataModel.RemoveAddressDM;
import com.saify.tech.ohhh.DataModel.SavedAddressDM;
import com.saify.tech.ohhh.Helper.DialogUtil;
import com.saify.tech.ohhh.Helper.Helper;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;
import com.saify.tech.ohhh.Utils.ConnectionDetector;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Saved_Address_DM_Adapter extends RecyclerView.Adapter<Saved_Address_DM_Adapter.Programming_AdapterViewHolder> {

    private Context context;
    private ArrayList<Info> arrayList;
    private Info selected;

    User user;

    Dialog progress;
    DialogUtil dialogUtil;

    AppController appController;
    ConnectionDetector connectionDetector;


    public
    Saved_Address_DM_Adapter(Context context, ArrayList<Info> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        user=new User(context);
        dialogUtil = new DialogUtil();
        appController = (AppController) context.getApplicationContext();
        connectionDetector = new ConnectionDetector(context);
    }


    @NonNull
    @Override
    public Programming_AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.custum_saved_addres,parent,false);
        return new Programming_AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Saved_Address_DM_Adapter.Programming_AdapterViewHolder holder, int position) {
        setDetails(holder, position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    private void setDetails(Saved_Address_DM_Adapter.Programming_AdapterViewHolder holder, int position) {


        Info data= arrayList.get(position);
        holder.address.setText(data.getGovernate()+","+data.getArea()+","+data.getBlock()+","+data.getStreet()+","+data.getBuilding_no()+","+data.getFloor_no()+","+data.getGovernatesname()+","+data.getAreaname());
//        holder.home.setText(savedAddressDM.getHome());
//        holder.home_img.setImageResource(savedAddressDM.getHome_img());
        holder.delete_Address_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (connectionDetector.isConnectingToInternet()) {
                    progress = dialogUtil.showProgressDialog(context, context.getString(R.string.please_wait));

                    appController.paServices.RemoveAddress(String.valueOf(user.getId()),data.getId(), new Callback<RemoveAddressDM>() {
                        @Override
                        public void success(RemoveAddressDM removeAddressDM, Response response) {
                            progress.dismiss();
                            if (removeAddressDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                                arrayList.remove(position);
                                notifyItemRemoved(position);
                                notifyDataSetChanged();
                                Helper.showToast(context, removeAddressDM.getOutput().getMessage());


//                                openHelper.deleteProduct(shoppingCart.getId());
//                                shoppingCartList.remove(position);
//                                notifyItemRemoved(position);

                            } else
                                Helper.showToast(context, "item does not deleted");
                        }

                        @Override
                        public void failure(RetrofitError retrofitError) {
                            progress.dismiss();
                            Log.e("error", retrofitError.toString());

                        }
                    });
                } else
                    Helper.showToast(context, String.valueOf(R.string.no_internet_connection));
            }
        });

        holder.edit_address_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, SaveAddressActivity.class);
                intent.putExtra("name", data.getName());
                intent.putExtra("email", data.getEmail());
                intent.putExtra("code", data.getCountrycode());
                intent.putExtra("mobileNo", data.getMobile_no());
                intent.putExtra("governate", data.getGovernate());
                intent.putExtra("area", data.getArea());
                intent.putExtra("building", data.getBuilding_no());
                intent.putExtra("block", data.getBlock());
                intent.putExtra("street", data.getStreet());
                intent.putExtra("floor",data.getFloor_no());
                intent.putExtra("AddressId",data.getId());

//                intent.putExtra("direction", myEventData.get(position).getCreatorcoach());


                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Programming_AdapterViewHolder extends RecyclerView.ViewHolder{

        TextView address,home;
         ImageView home_img,delete_Address_img,edit_address_img;


        public Programming_AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            address = (TextView) itemView.findViewById(R.id.address_txt);
            home=(TextView) itemView.findViewById(R.id.home_txt);
            home_img=(ImageView) itemView.findViewById(R.id.home_img);
            delete_Address_img=(ImageView) itemView.findViewById(R.id.delete_address_img);
            edit_address_img=(ImageView) itemView.findViewById(R.id.edit_address_Img);

        }
    }
}
