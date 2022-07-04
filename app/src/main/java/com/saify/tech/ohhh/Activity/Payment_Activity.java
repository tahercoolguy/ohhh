
package com.saify.tech.ohhh.Activity;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.saify.tech.ohhh.Adapter.Order_Info_Adapter;
import com.saify.tech.ohhh.Adapter.Saved_Address_DM_Adapter;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.MyCartDM;
import com.saify.tech.ohhh.DataModel.OrderInfoDM;
import com.saify.tech.ohhh.DataModel.OrderNowDM;
import com.saify.tech.ohhh.DataModel.SavedAddressDM;
import com.saify.tech.ohhh.Fragments.Fragment_Home_Screen;
import com.saify.tech.ohhh.Helper.DialogUtil;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;
import com.saify.tech.ohhh.Utils.ConnectionDetector;
import com.saify.tech.ohhh.Utils.Helper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Payment_Activity extends AppCompatActivity {

    AppController appController;

    private User user;
    Dialog progress;
    ConnectionDetector connectionDetector;
    DialogUtil dialogUtil;
    String Shipping_AddressID;
    String Shop_id;
    String Sub_total;
    String Applied_coupon;




    @NotEmpty
    @BindView(R.id.back_LL)
    LinearLayout back;

    @BindView(R.id.place_order)
    TextView place_order;

    @BindView(R.id.cash_RL)
    RelativeLayout cash;

    @BindView(R.id.visa_RL)
    RelativeLayout visa;

    @BindView(R.id.knet_RL)
    RelativeLayout knet;

    @BindView(R.id.codeET)
    EditText code;

    @BindView(R.id.subTotal_Txt)
    TextView subTotal_Txt;

    @BindView(R.id.CodeApplied)
    TextView CodeAppliedTxt;

    @BindView(R.id.total)
    TextView totalTxt;


    @OnClick(R.id.place_order)
    public void PlaceOrder() {
      Binding();
      startActivity(new Intent(Payment_Activity.this,Thank_You_Activity.class));
        finish();
    }



    @OnClick(R.id.cash_RL)
    public void Cash() {

        Cashh();
        ifcash = true;

    }

    @OnClick(R.id.visa_RL)
    public void Visa() {
        Cashh1();
        ifcard = true;


    }

    @OnClick(R.id.knet_RL)
    public void Knet() {
        Cashh2();

        ifknet = true;
    }

    @OnClick(R.id.back_LL)
    public void Back() {
            finish();
    }


    @OnClick(R.id.place_order)
    public void Place_Order() {
//        startActivity(new Intent(Address_Activity.this,Account_Activity.class));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);
        dialogUtil = new DialogUtil();
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(getApplicationContext());
        user = new User(Payment_Activity.this);
        user = new User(this);
        Shipping_AddressID=getIntent().getStringExtra("AddressId");
        Shop_id=getIntent().getStringExtra("shop__id");
        Sub_total=getIntent().getStringExtra("Sub_total");
        Applied_coupon=getIntent().getStringExtra("Applied_coupon");

        subTotal_Txt.setText(Sub_total);
        CodeAppliedTxt.setText(Applied_coupon);
        totalTxt.setText(Sub_total);

        idMappings();
        Cashh();
        Visa();

//
//        ArrayList<OrderInfoDM> orderInfoDMS = new ArrayList<>();
//
//        orderInfoDMS.add(new OrderInfoDM("Subtotal", "5.000 KWD"));
//        orderInfoDMS.add(new OrderInfoDM("Code Applied", "Mx3029"));
//        orderInfoDMS.add(new OrderInfoDM("Discount", "50%"));
//        orderInfoDMS.add(new OrderInfoDM("Delivery Charges", "3.000 KWD"));
////        orderInfoDMS.add(new OrderInfoDM("Total", "2.500 KWD"));
//
//
////            HistoryRcv.setLayoutManager(new LinearLayoutManager(context));
////            HistoryRcv.setAdapter(new HistoryDM_Adapter((context), historyDMS));
//
////
//        Order_Info_Adapter dm = new Order_Info_Adapter(Payment_Activity.this, orderInfoDMS);
//        LinearLayoutManager l = new LinearLayoutManager(this);
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


    boolean ifcash = false;
    boolean ifknet = false;
    boolean ifcard = false;

    public void Cashh() {

        ifcash = true;
        cash.setBackground(getDrawable(R.drawable.ic_payment_card_selected));
        knet.setBackground(getDrawable(R.drawable.ic_payment_card_bg_unselected));
        visa.setBackground(getDrawable(R.drawable.ic_payment_card_bg_unselected));

//        }
    }


    public void Cashh1() {

        ifcard = true;
        cash.setBackground(getDrawable(R.drawable.ic_payment_card_bg_unselected));
        knet.setBackground(getDrawable(R.drawable.ic_payment_card_bg_unselected));
        visa.setBackground(getDrawable(R.drawable.ic_payment_card_selected));

     }

    public void Cashh2() {

        ifknet = true;
        cash.setBackground(getDrawable(R.drawable.ic_payment_card_bg_unselected));
        knet.setBackground(getDrawable(R.drawable.ic_payment_card_selected));
        visa.setBackground(getDrawable(R.drawable.ic_payment_card_bg_unselected));

     }

//    public void Knett() {
//
//        if (ifknet) {
//
//
//            cash.setBackground(getDrawable(R.drawable.ic_payment_card_bg_unselected));
//            knet.setBackground(getDrawable(R.drawable.ic_payment_card_selected));
//            visa.setBackground(getDrawable(R.drawable.ic_payment_card_bg_unselected));
//        }
//    }
//
//    public void Cardd() {
//
//        if (ifcard) {
//
//
//            cash.setBackground(getDrawable(R.drawable.ic_payment_card_bg_unselected));
//            knet.setBackground(getDrawable(R.drawable.ic_payment_card_bg_unselected));
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

    public void Binding()
    {
        if (connectionDetector.isConnectingToInternet()) {
            {
                String refreshedToken = FirebaseInstanceId.getInstance().getToken();

//                progress = dialogUtil.showProgressDialog(Food_Details_Activity.this, getString(R.string.please_wait));

                appController.paServices.OrderNow(String.valueOf(user.getId()),Shop_id,Shipping_AddressID,"COD", new Callback<OrderNowDM>() {
                    @Override
                    public void success(OrderNowDM orderNowDM, Response response) {
//                        progress.dismiss();
                        if (orderNowDM.getOutput().getSuccess().equalsIgnoreCase("1")) {



                            Helper.showToast(Payment_Activity.this, orderNowDM.getOutput().getData());


                        } else

                            Helper.showToast(Payment_Activity.this, getString(R.string.Api_data_not_found));
                    }

                @Override
                public void failure(RetrofitError error) {

                Log.e("String", error.toString());
            }
            });
        }
    } else
            Helper.showToast(Payment_Activity.this, getString(R.string.no_internet_connection));

    }

}












