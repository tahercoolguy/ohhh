package com.saify.tech.ohhh.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.Helper.DialogUtil;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;
import com.saify.tech.ohhh.Utils.ConnectionDetector;
import com.saify.tech.ohhh.Utils.Helper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Account_Activity extends AppCompatActivity implements Validator.ValidationListener {
    AppController appController;

    Dialog progress;
    ConnectionDetector connectionDetector;
    User user;
    DialogUtil dialogUtil;
    Context context;

    @NotEmpty
    @BindView(R.id.edit_user_name_Img)
    ImageView Edit_Name;

    @NotEmpty
    @BindView(R.id.user_nameTxt)
    TextView User_Name;

    @NotEmpty
    @BindView(R.id.order_history_LL)
    LinearLayout Order_History;

    @NotEmpty
    @BindView(R.id.saved_address_LL)
    LinearLayout Saved_Address;

    @NotEmpty
    @BindView(R.id.push_notification_LL)
    LinearLayout Push_Notification;

    @NotEmpty
    @BindView(R.id.change_password_LL)
    LinearLayout Change_Password;

    @NotEmpty
    @BindView(R.id.term_and_condition_LL)
    LinearLayout Term_and_Condition;

    @NotEmpty
    @BindView(R.id.refund_policy_LL)
    LinearLayout Refund_Policy;

    @NotEmpty
    @BindView(R.id.privacy_policy_LL)
    LinearLayout Privacy_Policy;

    @NotEmpty
    @BindView(R.id.contact_us_LL)
    LinearLayout Contact_Us;

    @NotEmpty
    @BindView(R.id.logout_LL)
    LinearLayout Logout;


    @OnClick(R.id.edit_user_name_Img)
    public void Edit_USer_Name() {
        startActivity(new Intent(Account_Activity.this, Edit_Profile_Activity.class));

    }

    @OnClick(R.id.order_history_LL)
    public void Order() {
        startActivity(new Intent(Account_Activity.this, My_Order_Activity.class));
    }

    @OnClick(R.id.saved_address_LL)
    public void Saved() {
        startActivity(new Intent(Account_Activity.this, Saved_Address_Activity.class));

    }


    @OnClick(R.id.push_notification_LL)
    public void Push() {

    }


    @OnClick(R.id.change_password_LL)
    public void ChangePassword() {
        startActivity(new Intent(Account_Activity.this, Change_Password_Activity.class));

    }


    @OnClick(R.id.term_and_condition_LL)
    public void Term_condition() {
        startActivity(new Intent(Account_Activity.this, Term_And_Condition_Activity.class));


    }


    @OnClick(R.id.refund_policy_LL)
    public void Refund_Policy() {
        startActivity(new Intent(Account_Activity.this, Refund_Policy_Activity.class));

    }


    @OnClick(R.id.privacy_policy_LL)
    public void PrivacyPolicy() {

        startActivity(new Intent(Account_Activity.this, Privacy_Policy_Activity.class));

    }


    @OnClick(R.id.contact_us_LL)
    public void Contact_Us() {

    }


    @OnClick(R.id.logout_LL)
    public void Log_Out() {
        startActivity(new Intent(Account_Activity.this, LoginActivity.class));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        context = ((MainActivity) context);


        ButterKnife.bind(this);
        dialogUtil = new DialogUtil();
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(getApplicationContext());
        user = new User(Account_Activity.this);
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
                Helper.showToast(Account_Activity.this, message);
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

}
