package com.saify.tech.ohhh.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.saify.tech.ohhh.Adapter.Adapter_Rate2;
import com.saify.tech.ohhh.Adapter.Nornoya_Dip_Adapter;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.AllProductRatingsByidsDM;
import com.saify.tech.ohhh.DataModel.MyCartDM;
import com.saify.tech.ohhh.Helper.DialogUtil;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;
import com.saify.tech.ohhh.Utils.ConnectionDetector;
import com.saify.tech.ohhh.Utils.Helper;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Rate2_Activity extends AppCompatActivity {
    AppController appController;
    ConnectionDetector connectionDetector;
    Dialog progress;
    DialogUtil dialogUtil;
    private User user;
    String ProdtID;


    @NotEmpty
    @BindView(R.id.recycleRate2)
    RecyclerView recycleRate2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate2);
        ButterKnife.bind(this);
        user = new User(this);
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(Rate2_Activity.this);
        user = new User(Rate2_Activity.this);
        dialogUtil = new DialogUtil();

        ProdtID = getIntent().getStringExtra("ProductId");

        rateAPI();
    }


    private void rateAPI() {
        if (connectionDetector.isConnectingToInternet()) {
            {
                String refreshedToken = FirebaseInstanceId.getInstance().getToken();

                progress = dialogUtil.showProgressDialog(Rate2_Activity.this, getString(R.string.please_wait));
                String user_id = String.valueOf(user.getId());
                appController.paServices.allProductRatingsByids(user_id,ProdtID, new Callback<AllProductRatingsByidsDM>() {
                    @Override
                    public void success(AllProductRatingsByidsDM allProductRatingsByidsDM, Response response) {
                        progress.dismiss();
                        if (allProductRatingsByidsDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                            Adapter_Rate2 dm = new Adapter_Rate2(Rate2_Activity.this, allProductRatingsByidsDM.getOutput().getData());
                            LinearLayoutManager l = new LinearLayoutManager(Rate2_Activity.this, LinearLayoutManager.VERTICAL, false);
                            recycleRate2.setLayoutManager(l);
                            recycleRate2.setAdapter(dm);


                        } else {
                            Helper.showToast(Rate2_Activity.this, getString(R.string.Api_data_not_found));
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        progress.dismiss();
                        Log.e("String", error.toString());
                    }
                });

            }
        } else
            Helper.showToast(Rate2_Activity.this, getString(R.string.no_internet_connection));

    }
}
