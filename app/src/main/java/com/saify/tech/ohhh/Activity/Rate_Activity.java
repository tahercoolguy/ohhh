package com.saify.tech.ohhh.Activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.iid.FirebaseInstanceId;
import com.saify.tech.ohhh.Adapter.Adapter_Address;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.AddProductRatingDM;
import com.saify.tech.ohhh.DataModel.AddressListDM;
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

public class Rate_Activity extends AppCompatActivity {


    AppController appController;

    private User user;
    Context context;
    Dialog dialog;
    ConnectionDetector connectionDetector;
    DialogUtil dialogUtil;

    String ProdtID,Prodtrating;

    @OnClick(R.id.submit_Btn)
    public void submit_Btn()
    {
        if (connectionDetector.isConnectingToInternet()) {
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
            dialog = dialogUtil.showProgressDialog(Rate_Activity.this, getString(R.string.please_wait));
            appController.paServices.AddProductRating(String.valueOf(user.getId()),ProdtID,Prodtrating, new Callback<AddProductRatingDM>() {
                @Override
                public void success(AddProductRatingDM addProductRatingDM, Response response) {
                    dialog.dismiss();
                    if (addProductRatingDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                        Helper.showToast(Rate_Activity.this, addProductRatingDM.getOutput().getMessage());

                    } else
                        Helper.showToast(Rate_Activity.this, addProductRatingDM.getOutput().getMessage());
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    Log.e("error", retrofitError.toString());
                    dialog.dismiss();

                }
            });
        } else
            Helper.showToast(Rate_Activity.this, getString(R.string.no_internet_connection));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        ButterKnife.bind(this);
        dialogUtil = new DialogUtil();
        context = ((MainActivity) context);
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(getApplicationContext());
        user = new User(Rate_Activity.this);
        user = new User(this);

        ProdtID = getIntent().getStringExtra("ProductId");
        Prodtrating = getIntent().getStringExtra("ProductRating");

//        Binding();
    }

    public void Binding()
    {

    }
}
