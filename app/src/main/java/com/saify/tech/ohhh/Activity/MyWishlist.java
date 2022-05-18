package com.saify.tech.ohhh.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.saify.tech.ohhh.Adapter.Adapter_MyWishlist;
import com.saify.tech.ohhh.Adapter.Shopss_Adapter;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.MyWishlistDM;
import com.saify.tech.ohhh.DataModel.ShopsDM;
import com.saify.tech.ohhh.Helper.DialogUtil;
import com.saify.tech.ohhh.Helper.Helper;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;
import com.saify.tech.ohhh.Utils.ConnectionDetector;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MyWishlist extends AppCompatActivity {
    User user;
    AppController appController;
    ConnectionDetector connectionDetector;
    DialogUtil dialogUtil;
    Dialog progress;


    @NotEmpty
    @BindView(R.id.MyWishlist_Rcv)
    RecyclerView MyWishlist_Rcv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wishlist);
        user = new User(MyWishlist.this);
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(MyWishlist.this);
        dialogUtil = new DialogUtil();
        ButterKnife.bind(this);
      Binding();

    }

    public void Binding() {

        if (connectionDetector.isConnectingToInternet()) {
//            progress = dialogUtil.showProgressDialog(getActivity(),getString(R.string.please_wait));
           String user_id= String.valueOf(user.getId());
            appController.paServices.MyWishlist(user_id, new Callback<MyWishlistDM>() {
                @Override
                public void success(MyWishlistDM myWishlistDM, Response response) {
                    //                   progress.dismiss();
                    if (myWishlistDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                        Adapter_MyWishlist dm = new Adapter_MyWishlist(MyWishlist.this, myWishlistDM.getOutput().getInfo());
                        LinearLayoutManager l = new LinearLayoutManager(MyWishlist.this, RecyclerView.VERTICAL, false);
                        MyWishlist_Rcv.setLayoutManager(l);
                        MyWishlist_Rcv.setAdapter(dm);
                    } else
                        Helper.showToast(MyWishlist.this, getString(R.string.Api_data_not_found));
                }

                @Override
                public void failure(RetrofitError retrofitError) {
//                    progress.dismiss();

                    Log.e("error", retrofitError.toString());

                }
            });
        } else
            Helper.showToast(MyWishlist.this, getString(R.string.no_internet_connection));
    }

}