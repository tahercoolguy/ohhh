package com.saify.tech.ohhh.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saify.tech.ohhh.Adapter.Adapter_MyWishlist;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.MyWishlistDM;
import com.saify.tech.ohhh.DataModel.SaveAddressDM;
import com.saify.tech.ohhh.Helper.DialogUtil;
import com.saify.tech.ohhh.Helper.Helper;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;
import com.saify.tech.ohhh.Utils.ConnectionDetector;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SaveAddressActivity extends AppCompatActivity {


    User user;
    AppController appController;
    ConnectionDetector connectionDetector;
    DialogUtil dialogUtil;
    Dialog progress;

     @BindView(R.id.firstNameET)
      EditText full_name_ET;

     @BindView(R.id.emailET)
     EditText emailET;

     @BindView(R.id.pinCodeTxt)
     EditText pinCodeTxt;

     @BindView(R.id.mobileET)
     EditText mobileET;

     @BindView(R.id.areaET)
     EditText areaET;

      @BindView(R.id.governateET)
      EditText governateET;

     @BindView(R.id.building_no)
     EditText building_no;

     @BindView(R.id.block)
     EditText block;

    @BindView(R.id.streetET)
    EditText streetET;

    @BindView(R.id.floor_noET)
    EditText floor_noET;

    @BindView(R.id.latET)
    EditText latET;

    @BindView(R.id.lanET)
    EditText lanET;




    @OnClick(R.id.saveTxtt)
    public void saveTxtt() {
       Binding();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_address);
        user = new User(SaveAddressActivity.this);
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(SaveAddressActivity.this);
        dialogUtil = new DialogUtil();
        ButterKnife.bind(this);
//        Binding();

    }


        public void Binding() {

            if (connectionDetector.isConnectingToInternet()) {
//            progress = dialogUtil.showProgressDialog(getActivity(),getString(R.string.please_wait));
                String user_id= String.valueOf(user.getId());
                appController.paServices.SaveAddress(user_id,full_name_ET.getText().toString(),emailET.getText().toString(),pinCodeTxt.getText().toString(),
                        mobileET.getText().toString(),areaET.getText().toString(),governateET.getText().toString(),building_no.getText().toString(),block.getText().toString(),
                        streetET.getText().toString(),floor_noET.getText().toString(),latET.getText().toString(),lanET.getText().toString(), new Callback<SaveAddressDM>() {
                    @Override
                    public void success(SaveAddressDM saveAddressDM, Response response) {
                        //                   progress.dismiss();
                        if (saveAddressDM.getOutput().getSuccess().equalsIgnoreCase("1")) {
                            Helper.showToast(SaveAddressActivity.this,saveAddressDM.getOutput().getMessage());
                        } else
                            Helper.showToast(SaveAddressActivity.this, getString(R.string.Api_data_not_found));
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
//                    progress.dismiss();

                        Log.e("error", retrofitError.toString());

                    }
                });
            } else
                Helper.showToast(SaveAddressActivity.this, getString(R.string.no_internet_connection));
            }

}
