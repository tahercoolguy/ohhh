
package com.saify.tech.ohhh.Activity;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    @BindView(R.id.back_saved_address)
    LinearLayout back;

     @BindView(R.id.change_Btn)
     TextView change_Btn;

    @BindView(R.id.proceed_to_payment_Btn)
    TextView proceed;

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
        startActivity(new Intent(Address_Activity.this,Payment_Activity.class));

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












