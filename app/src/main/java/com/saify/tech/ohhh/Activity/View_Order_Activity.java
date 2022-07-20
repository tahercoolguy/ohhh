
package com.saify.tech.ohhh.Activity;


import android.app.Dialog;
import android.os.Bundle;
import android.text.Html;
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
import com.saify.tech.ohhh.Adapter.Adapter_Address;
import com.saify.tech.ohhh.Adapter.Adapter_Info;
import com.saify.tech.ohhh.Adapter.Order_Info_Adapter;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.MyOrderDetailDM;
import com.saify.tech.ohhh.DataModel.OrderInfoDM;
import com.saify.tech.ohhh.DataModel.TermsConditionDM;
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

public class View_Order_Activity extends AppCompatActivity {

    AppController appController;

    private User user;
    Dialog progress;
    ConnectionDetector connectionDetector;
    DialogUtil dialogUtil;
    String SaleCode;
    @NotEmpty
    @BindView(R.id.back_term_and_condition)
    LinearLayout back;

    @BindView(R.id.phoneNumber)
    TextView phoneNumberTV;

    @BindView(R.id.addressTV)
    TextView addressTV;

    @BindView(R.id.subTotalTV)
    TextView subTotalTV;

    @BindView(R.id.codeTV)
    TextView codeTV;

    @BindView(R.id.discountTV)
    TextView discountTV;

    @BindView(R.id.deliveryChargesTV)
    TextView deliveryChargesTV;

    @BindView(R.id.recycleview1)
    RecyclerView recyclerView1;



    @OnClick(R.id.back_term_and_condition)
    public void Back_Term() {
        finish();
    }




//    @OnClick(R.id.done_Btn)
//    public void DoneBtn()
//    {
//        finish();
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);
        ButterKnife.bind(this);
        dialogUtil = new DialogUtil();
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(getApplicationContext());
        user = new User(View_Order_Activity.this);
        user = new User(this);

        SaleCode=getIntent().getStringExtra("Sale_Code");

        idMappings();




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
            progress = dialogUtil.showProgressDialog(View_Order_Activity.this, getString(R.string.please_wait));
            appController.paServices.MyOrderDetail(String.valueOf(user.getId()),SaleCode,new Callback<MyOrderDetailDM>() {
                @Override

                public void success(MyOrderDetailDM myOrderDetailDM, Response response) {
                    progress.dismiss();
                    if (myOrderDetailDM.getOutput().getSuccess().equalsIgnoreCase("1")) {
//                        termsAndCondition.setText(Html.fromHtml(termsConditionDM.getOutput().getInfo().get(0).getDescription_en(), Html.FROM_HTML_MODE_COMPACT));
                              if(myOrderDetailDM.getOutput().getSub_total()!=null){
                        subTotalTV.setText(myOrderDetailDM.getOutput().getSub_total());}
                        if(myOrderDetailDM.getOutput().getCode_applied()!=null){
                        codeTV.setText(myOrderDetailDM.getOutput().getCode_applied());}
                        if(myOrderDetailDM.getOutput().getDiscount()!=null){
                            discountTV.setText(myOrderDetailDM.getOutput().getDiscount());}
                        if(myOrderDetailDM.getOutput().getDelivery_charges()!=null){
                        deliveryChargesTV.setText(myOrderDetailDM.getOutput().getDelivery_charges());}
//                    data.getGovernate()+","+data.getArea()+","+data.getBlock()+","+data.getStreet()+","+data.getBuilding_no()+","+data.getFloor_no()+","+data.getGovernatesname()+","+data.getAreaname()
                        addressTV.setText(myOrderDetailDM.getOutput().getInfo().get(0).getShipping_address());
//                        phoneNumberTV.setText(myOrderDetailDM.getOutput().getShipping().get(0).getMobile_no());

                        Adapter_Info dm = new Adapter_Info(View_Order_Activity.this, myOrderDetailDM.getOutput().getInfo());
                        LinearLayoutManager l = new LinearLayoutManager(View_Order_Activity.this);
                        recyclerView1.setLayoutManager(l);
                        recyclerView1.setAdapter(dm);

                    }

                    else
                        Helper.showToast(View_Order_Activity.this,getString(R.string.Api_data_not_found));
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    progress.dismiss();
                    Log.e("error", retrofitError.toString());

                }
            });
        } else
            Helper.showToast(View_Order_Activity.this, getString(R.string.no_internet_connection));

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












