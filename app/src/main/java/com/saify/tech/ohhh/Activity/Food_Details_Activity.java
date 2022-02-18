
package com.saify.tech.ohhh.Activity;


import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.saify.tech.ohhh.Adapter.Order_Info_Adapter;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.OrderInfoDM;
import com.saify.tech.ohhh.Helper.DialogUtil;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;
import com.saify.tech.ohhh.Utils.ConnectionDetector;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Food_Details_Activity extends AppCompatActivity {

    AppController appController;

    private User user;
    Dialog progress;
    ConnectionDetector connectionDetector;
    DialogUtil dialogUtil;

//    @NotEmpty
//    @BindView(R.id.order_info_Rcv)
//    RecyclerView order_info_Rcvv;
//
//
//    @OnClick(R.id.knet_RL)
//    public void Knet() {
//
//        Knett();
//        ifknet=true;
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        ButterKnife.bind(this);
        dialogUtil = new DialogUtil();
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(getApplicationContext());
        user = new User(Food_Details_Activity.this);
        user = new User(this);
        idMappings();


        ArrayList<OrderInfoDM> orderInfoDMS = new ArrayList<>();

        orderInfoDMS.add(new OrderInfoDM("Subtotal", "5.000 KWD"));
        orderInfoDMS.add(new OrderInfoDM("Code Applied", "Mx3029"));
        orderInfoDMS.add(new OrderInfoDM("Discount", "50%"));
        orderInfoDMS.add(new OrderInfoDM("Delivery Charges", "3.000 KWD"));
        orderInfoDMS.add(new OrderInfoDM("Total", "2.500 KWD"));


//            HistoryRcv.setLayoutManager(new LinearLayoutManager(context));
//            HistoryRcv.setAdapter(new HistoryDM_Adapter((context), historyDMS));

//
        Order_Info_Adapter dm = new Order_Info_Adapter(Food_Details_Activity.this, orderInfoDMS);
        LinearLayoutManager l = new LinearLayoutManager(this);
//        order_info_Rcvv.setLayoutManager(l);
//        order_info_Rcvv.setAdapter(dm);


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

//
//    boolean ifcash = false;
//    boolean ifknet = false;
//    boolean ifcard = false;
//
//    public void Cashh() {
//
//        if (ifcash) {
//
//            cash.setBackground(getDrawable(R.drawable.ic_payment_card_selected));
//        }
//    }
//
//    public void Knett() {
//
//        if (ifknet) {
//
//            knet.setBackground(getDrawable(R.drawable.ic_payment_card_selected));
//        }
//    }
//
//    public void Cardd() {
//
//        if (ifcard) {
//
//            visa.setBackground(getDrawable(R.drawable.ic_payment_card_selected));
//        }
//    }

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












