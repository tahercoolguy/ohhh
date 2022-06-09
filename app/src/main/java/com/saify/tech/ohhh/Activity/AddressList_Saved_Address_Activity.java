
package com.saify.tech.ohhh.Activity;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.saify.tech.ohhh.Adapter.Saved_Address_DM_Adapter;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.AddressListDM;
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

public class AddressList_Saved_Address_Activity extends AppCompatActivity {

    AppController appController;

    private User user;
    Dialog progress;
    ConnectionDetector connectionDetector;
    DialogUtil dialogUtil;

    @NotEmpty
    @BindView(R.id.saved_address_Rcv)
    RecyclerView saved_address_Rcvv;



    @NotEmpty
    @BindView(R.id.back_saved_address)
    LinearLayout back;

    @OnClick(R.id.saveTxtt)
    public void saveTxtt() {
        Intent intent=new Intent(AddressList_Saved_Address_Activity.this,SaveAddressActivity.class);
        startActivity(intent);
    }



    @OnClick(R.id.back_saved_address)
    public void Back_address() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list_saved_address);
        ButterKnife.bind(this);
        dialogUtil = new DialogUtil();
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(getApplicationContext());
        user = new User(AddressList_Saved_Address_Activity.this);
        user = new User(this);
        idMappings();


        Binding();

//        ArrayList<SavedAddressDM> savedAddressDMS = new ArrayList<>();
//
//        savedAddressDMS.add(new SavedAddressDM("Al Nouf Tower, 11th Floor، Jaber \n" +
//                "Al-Mubarak St, Kuwait City","Home",R.drawable.ic_home_logoooo));
//
//        savedAddressDMS.add(new SavedAddressDM( "Al Nouf Tower, 11th Floor، Jaber \n" +
//                "Al-Mubarak St, Kuwait City","Home",R.drawable.ic_home_2));

//        Saved_Address_DM_Adapter dm = new Saved_Address_DM_Adapter(AddressList_Saved_Address_Activity.this, savedAddressDMS);
//        LinearLayoutManager l = new LinearLayoutManager(this);
//        saved_address_Rcvv.setLayoutManager(l);
//        saved_address_Rcvv.setAdapter(dm);


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


    public void Binding()
    {
        if (connectionDetector.isConnectingToInternet()) {
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
            progress = dialogUtil.showProgressDialog(AddressList_Saved_Address_Activity.this, getString(R.string.please_wait));
            appController.paServices.AddressList(String.valueOf(user.getId()),  new Callback<AddressListDM>() {
                @Override
                public void success(AddressListDM addressListDM, Response response) {
                    progress.dismiss();
                    if (addressListDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                        Saved_Address_DM_Adapter dm = new Saved_Address_DM_Adapter(AddressList_Saved_Address_Activity.this, addressListDM.getOutput().getInfo());
                        LinearLayoutManager l = new LinearLayoutManager(AddressList_Saved_Address_Activity.this);
                        saved_address_Rcvv.setLayoutManager(l);
                        saved_address_Rcvv.setAdapter(dm);

                    } else
                        Helper.showToast(AddressList_Saved_Address_Activity.this, addressListDM.getOutput().getMessage());
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    Log.e("error", retrofitError.toString());
                    progress.dismiss();

                }
            });
        } else
            Helper.showToast(AddressList_Saved_Address_Activity.this, getString(R.string.no_internet_connection));
    }



}












