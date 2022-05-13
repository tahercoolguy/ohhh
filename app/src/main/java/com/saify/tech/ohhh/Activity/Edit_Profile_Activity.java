package com.saify.tech.ohhh.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.iid.FirebaseInstanceId;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.UpdateProfileDM;
import com.saify.tech.ohhh.Fragments.Fragment_Account;
import com.saify.tech.ohhh.Helper.DialogUtil;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;
import com.saify.tech.ohhh.Utils.ConnectionDetector;
import com.saify.tech.ohhh.Utils.Helper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Edit_Profile_Activity extends AppCompatActivity implements Validator.ValidationListener {
    AppController appController;

    Dialog progress;
    ConnectionDetector connectionDetector;
    User user;
    DialogUtil dialogUtil;
    Context context;

    @NotEmpty
     @BindView(R.id.full_name_ET)
    EditText full_name_ET;

    @NotEmpty
    @BindView(R.id.email_ET)
    EditText email_ET;


    @NotEmpty
    @BindView(R.id.mobileET)
    EditText mobileET;




    @OnClick(R.id.save_Btn)
     public void  save_Btn()
    {
        Binding();
    }


    @NotEmpty
    @BindView(R.id.back_term_and_condition)
    LinearLayout back;


    @OnClick(R.id.back_term_and_condition)
    public void Back_Term() {
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);

        dialogUtil = new DialogUtil();
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(getApplicationContext());
        user = new User(Edit_Profile_Activity.this);
        validator = new Validator(this);
        validator.setValidationListener(this);

    }

    @Override
    public void onValidationSucceeded() {

    }

    boolean o = true;
    Validator validator;


    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            o = false;
            // Display error messages ;)
            if (view instanceof TextInputEditText) {
                ((TextInputEditText) view).setError(message);
            } else {
                Helper.showToast(Edit_Profile_Activity.this, message);
            }
        }
    }

    public void isValid() {
        boolean done = true;
        o = true;
        validator.validate();
        //o=done;
        if (!done)
            o = done;

    }


public void Binding()
{
    try {
        if (connectionDetector.isConnectingToInternet()) {
            {
                String refreshedToken = FirebaseInstanceId.getInstance().getToken();

                progress = dialogUtil.showProgressDialog(Edit_Profile_Activity.this, getString(R.string.please_wait));

                appController.paServices.UpdateProfile(String.valueOf(user.getId()),full_name_ET.getText().toString(),"", email_ET.getText().toString(),mobileET.getText().toString(), new Callback<UpdateProfileDM>() {
                    @Override
                    public void success(UpdateProfileDM updateProfileDM, Response response) {
                        progress.dismiss();
                        if (updateProfileDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                            Helper.showToast(Edit_Profile_Activity.this, updateProfileDM.getOutput().getMessage());

                        } else

                            Helper.showToast(Edit_Profile_Activity.this,updateProfileDM.getOutput().getMessage() );
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("String", error.toString());
                    }
                });
            }
        } else
            Helper.showToast(Edit_Profile_Activity.this, getString(R.string.no_internet_connection));
    } catch (Exception e) {
        Log.e("String", e.toString());
    }
}



}
