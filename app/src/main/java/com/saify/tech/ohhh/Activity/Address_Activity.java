
package com.saify.tech.ohhh.Activity;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.saify.tech.ohhh.Adapter.Adapter_Address;
import com.saify.tech.ohhh.Adapter.Saved_Address_DM_Adapter;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.AddressListDM;
import com.saify.tech.ohhh.DataModel.SavedAddressDM;
import com.saify.tech.ohhh.Helper.DialogUtil;
import com.saify.tech.ohhh.Helper.Helper;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;
import com.saify.tech.ohhh.Utils.ConnectionDetector;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Address_Activity extends AppCompatActivity {

    AppController appController;

    private User user;
    Dialog progress;
    ConnectionDetector connectionDetector;
    DialogUtil dialogUtil;
    String AddressId;
    String ShopId;
    String Sub__total;
    String Applied__coupon;




    @NotEmpty
    @BindView(R.id.back_saved_address)
    LinearLayout back;

    @NotEmpty
    @BindView(R.id.recycleAddress)
    RecyclerView recycleAddress;


    @BindView(R.id.proceed_to_payment_Btn)
    TextView proceed;

    @BindView(R.id.now_RL)
    RelativeLayout now_RLL;

    @BindView(R.id.customize_RL)
    RelativeLayout customize_RLL;

    @OnClick(R.id.now_RL)
    public void Now_RL() {
//        startActivity(new Intent(Address_Activity.this,Account_Activity.class));

    }

    @OnClick(R.id.customize_RL)
    public void Customize_Rl() {
//        startActivity(new Intent(Address_Activity.this,Account_Activity.class));

    }


    @OnClick(R.id.proceed_to_payment_Btn)
    public void ProceedToPayment() {
        Intent intent=new Intent(Address_Activity.this, Payment_Activity.class);
        intent.putExtra("AddressId", AddressId);
        intent.putExtra("shop__id", ShopId);
        intent.putExtra("Sub_total", Sub__total);
        intent.putExtra("Applied_coupon", Applied__coupon);
        startActivity(intent);
        finish();

    }


    @OnClick(R.id.back_saved_address)
    public void Back_address() {
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);
        dialogUtil = new DialogUtil();
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(getApplicationContext());
        user = new User(Address_Activity.this);
        user = new User(this);
        ShopId= getIntent().getStringExtra("Shop_id");
        Sub__total= getIntent().getStringExtra("Sub_total");
        Applied__coupon= getIntent().getStringExtra("Applied_coupon");


        idMappings();


//


    }


    @Override
    protected void onResume() {
        super.onResume();
        setDetails();
    }

    public void setDetails() {

    }

    private void setOnClickListeners() {

    }

    private void idMappings() {

        if (connectionDetector.isConnectingToInternet()) {
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
            progress = dialogUtil.showProgressDialog(Address_Activity.this, getString(R.string.please_wait));
            appController.paServices.AddressList(String.valueOf(user.getId()),  new Callback<AddressListDM>() {
                @Override
                public void success(AddressListDM addressListDM, Response response) {
                    progress.dismiss();
                    if (addressListDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                        AddressId= addressListDM.getOutput().getInfo().get(0).getId();

                        Adapter_Address dm = new Adapter_Address(Address_Activity.this, addressListDM.getOutput().getInfo());
                        LinearLayoutManager l = new LinearLayoutManager(Address_Activity.this);
                        recycleAddress.setLayoutManager(l);
                        recycleAddress.setAdapter(dm);


                    } else
                        Helper.showToast(Address_Activity.this, addressListDM.getOutput().getMessage());
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    Log.e("error", retrofitError.toString());
                    progress.dismiss();

                }
            });
        } else
            Helper.showToast(Address_Activity.this, getString(R.string.no_internet_connection));
    }





//    private void exitDialog() {
//        DialogUtil.showDialogTwoButton(this, R.drawable.app_icon, getString(R.string.app_name), getString(R.string.are_you_sure_you_want_to_exit_the_app), getString(R.string.ok), getString(R.string.cancel), new DialogUtil.CallBack() {
//            @Override
//            public void onDismiss(boolean isPressedOK) {
//                if (isPressedOK) {
//                    LanguageActivity.this.finish();
//                }
//            }
//        });
}












