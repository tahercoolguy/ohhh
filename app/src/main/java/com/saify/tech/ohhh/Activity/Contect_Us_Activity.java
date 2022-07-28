package com.saify.tech.ohhh.Activity;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.iid.FirebaseInstanceId;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.ContactusDM;
import com.saify.tech.ohhh.DataModel.TermsConditionDM;
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

public class Contect_Us_Activity extends AppCompatActivity {

    AppController appController;
    ConnectionDetector connectionDetector;
    Dialog progress;
    DialogUtil dialogUtil;
    User user;


    @BindView(R.id.userNameET)
    EditText userNameET;

    @BindView(R.id.emailET)
    EditText emailET;

    @BindView(R.id.mobileET)
    EditText mobileET;

    @BindView(R.id.pinCodeTxt)
    EditText pinCodeTxt;

    @BindView(R.id.commentET)
    EditText commentET;

    @OnClick(R.id.submitTxtt)
    public void ssubmitTxtt()
    {
        Binding();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contect_us);
        ButterKnife.bind(this);
        user = new User(this);
        appController = (AppController)  getApplicationContext();
        connectionDetector = new ConnectionDetector(Contect_Us_Activity.this);
        user = new User(Contect_Us_Activity.this);
        dialogUtil = new DialogUtil();
   setDetails();

    }

    public void Binding() {

        if (connectionDetector.isConnectingToInternet()) {
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
            progress = dialogUtil.showProgressDialog(Contect_Us_Activity.this, getString(R.string.please_wait));
            appController.paServices.ContactUs(String.valueOf(user.getId()),userNameET.getText().toString(),emailET.getText().toString(),mobileET.getText().toString(),pinCodeTxt.getText().toString(),commentET.getText().toString(),new Callback<ContactusDM>() {
                @Override

                public void success(ContactusDM contactusDM, Response response) {
                    progress.dismiss();
                    if (contactusDM.getOutput().getSuccess().equalsIgnoreCase("1"))

                        Helper.showToast(Contect_Us_Activity.this, contactusDM.getOutput().getData());

                    else
                        Helper.showToast(Contect_Us_Activity.this, getString(R.string.Api_data_not_found));
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    progress.dismiss();
                    Log.e("error", retrofitError.toString());

                }
            });
        } else
            Helper.showToast(Contect_Us_Activity.this, getString(R.string.no_internet_connection));

    }


    private void setDetails() {
       userNameET.setText(user.getName());
        emailET.setText(user.getEmail());
//        mobileET.setText(user.getMobileNumber());

    }


}
