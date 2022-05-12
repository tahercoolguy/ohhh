package com.saify.tech.ohhh.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.iid.FirebaseInstanceId;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.MyprofileDM;
import com.saify.tech.ohhh.Helper.DialogUtil;
import com.saify.tech.ohhh.Helper.Helper;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;
import com.saify.tech.ohhh.Utils.ConnectionDetector;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MyProfileActivity extends AppCompatActivity {

    User user;
    AppController appController;
    ConnectionDetector connectionDetector;
    DialogUtil dialogUtil;
    Dialog progress;

    @OnClick(R.id.submitTxtt)
    public void submitTxtt() {
        Binding();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        user = new User(MyProfileActivity.this);
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(MyProfileActivity.this);
        dialogUtil = new DialogUtil();
        ButterKnife.bind(this);


    }

    public void Binding() {
        if (connectionDetector.isConnectingToInternet()) {
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
            progress = dialogUtil.showProgressDialog(MyProfileActivity.this, getString(R.string.please_wait));
            appController.paServices.Myprofile(String.valueOf(user.getId()),  new Callback<MyprofileDM>() {
                @Override
                public void success(MyprofileDM myprofileDM, Response response) {
                    progress.dismiss();
                    if (myprofileDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                        Helper.showToast(MyProfileActivity.this, "Profile Updated successfully");

                    } else
                        Helper.showToast(MyProfileActivity.this, myprofileDM.getOutput().getMessage());
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    Log.e("error", retrofitError.toString());
                    progress.dismiss();

                }
            });
        } else
            Helper.showToast(MyProfileActivity.this, getString(R.string.no_internet_connection));

    }


}
