
package com.saify.tech.ohhh.Activity;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.saify.tech.ohhh.Adapter.Saved_Address_DM_Adapter;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.SavedAddressDM;
import com.saify.tech.ohhh.Helper.DialogUtil;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;
import com.saify.tech.ohhh.Utils.ConnectionDetector;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Address_Activity extends AppCompatActivity {

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
    ImageView back;

     @BindView(R.id.change_Btn)
     Button change_Btn;

    @BindView(R.id.proceed_to_payment_Btn)
    Button proceed;

    @BindView(R.id.now_RL)
    RelativeLayout now_RLL;

    @BindView(R.id.customize_RL)
    RelativeLayout customize_RLL;

    @OnClick(R.id.now_RL)
    public void Now_RL() {
//        startActivity(new Intent(Address_Activity.this,Account_Activity.class));

    } @OnClick(R.id.customize_RL)
    public void Customize_Rl() {
//        startActivity(new Intent(Address_Activity.this,Account_Activity.class));

    }

    @OnClick(R.id.change_Btn)
    public void Change_btn() {
//        startActivity(new Intent(Address_Activity.this,Account_Activity.class));

    }

    @OnClick(R.id.proceed_to_payment_Btn)
    public void ProceedToPayment() {
//        startActivity(new Intent(Address_Activity.this,Account_Activity.class));

    }


    @OnClick(R.id.back_saved_address)
    public void Back_address() {
//        startActivity(new Intent(Address_Activity.this,Account_Activity.class));

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
        idMappings();


        ArrayList<SavedAddressDM> savedAddressDMS = new ArrayList<>();

        savedAddressDMS.add(new SavedAddressDM("Al Nouf Tower, 11th Floor، Jaber \n" +
                "Al-Mubarak St, Kuwait City", R.drawable.ic_home_logoooo, R.drawable.ic_delete, R.drawable.ic_edit));
        savedAddressDMS.add(new SavedAddressDM("Al Nouf Tower, 11th Floor، Jaber \n" +
                "Al-Mubarak St, Kuwait City", R.drawable.ic_home_logoooo, R.drawable.ic_delete, R.drawable.ic_edit));


//            HistoryRcv.setLayoutManager(new LinearLayoutManager(context));
//            HistoryRcv.setAdapter(new HistoryDM_Adapter((context), historyDMS));

//
        Saved_Address_DM_Adapter dm = new Saved_Address_DM_Adapter(Address_Activity.this, savedAddressDMS);
        LinearLayoutManager l = new LinearLayoutManager(this);
        saved_address_Rcvv.setLayoutManager(l);
        saved_address_Rcvv.setAdapter(dm);


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
}











