
package com.saify.tech.ohhh.Activity;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.iid.FirebaseInstanceId;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.PrivacyPolicyDM;
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

public class Refund_Policy_Activity extends AppCompatActivity{


    AppController appController;
    ConnectionDetector connectionDetector;

    private User user;
    @NotEmpty
    @BindView(R.id.back_term_and_condition)
    LinearLayout back;


    @OnClick(R.id.back_term_and_condition)
    public void Back_Term() {
        finish();
    }

    @BindView(R.id.refundPolicy)
    TextView refundPolicy;

    Dialog progress;
    DialogUtil dialogUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund_policy);
        ButterKnife.bind(this);
        user = new User(this);
        appController = (AppController)  getApplicationContext();
        connectionDetector = new ConnectionDetector(Refund_Policy_Activity.this);
        dialogUtil = new DialogUtil();
        TermCondition();


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




    public void TermCondition () {
        if (connectionDetector.isConnectingToInternet()) {
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
            progress = dialogUtil.showProgressDialog(Refund_Policy_Activity.this, getString(R.string.please_wait));
            appController.paServices.refundPolicy(new Callback<PrivacyPolicyDM>() {
                @Override

                public void success(PrivacyPolicyDM privacyPolicyDM, Response response) {
                    progress.dismiss();
                    if (privacyPolicyDM.getOutput().getSuccess().equalsIgnoreCase("1"))

                        refundPolicy.setText(Html.fromHtml(privacyPolicyDM.getOutput().getInfo().get(0).getDescription_en(), Html.FROM_HTML_MODE_COMPACT));

                        //termAndCondition.setText(dataTerm.getItem().getDescription());

                    else
                        Helper.showToast(Refund_Policy_Activity.this,getString(R.string.Api_data_not_found));
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    progress.dismiss();
                    Log.e("error", retrofitError.toString());

                }
            });
        } else
            Helper.showToast(Refund_Policy_Activity.this, getString(R.string.no_internet_connection));

    }



    }












