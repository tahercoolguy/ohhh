package com.saify.tech.ohhh.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
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

public class Change_Password_Activity extends AppCompatActivity implements  Validator.ValidationListener{
    AppController appController;

    Dialog progress;
    ConnectionDetector connectionDetector;
    User user;
    DialogUtil dialogUtil;

//    @NotEmpty
//     @BindView(R.id.current_passwordET)
//    EditText currentPassword;
//
//    @NotEmpty
//    @BindView(R.id.passwordET)
//    EditText password;
//
//
//
//
//    @NotEmpty
//    @BindView(R.id.confirm_passwordET)
//    EditText confirmPassword;
//
    @NotEmpty
    @BindView(R.id.done_Btn)
    TextView Done;

    @NotEmpty
    @BindView(R.id.back_term_and_condition)
    LinearLayout back;


    @OnClick(R.id.back_term_and_condition)
    public void Back_Term() {
        finish();
    }



    @OnClick(R.id.done_Btn)
    public void DoneBtn()
    {
            finish();
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);


        ButterKnife.bind(this);
        dialogUtil = new DialogUtil();
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(getApplicationContext());
        user = new User(Change_Password_Activity.this);
        validator=new Validator(this);
        validator.setValidationListener(this);

    }

    @Override
    public void onValidationSucceeded() {

    }
    boolean o=true;
    Validator validator;


    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            o=false;
            // Display error messages ;)
            if (view instanceof TextInputEditText) {
                ((TextInputEditText) view).setError(message);
            } else {
                Helper.showToast(Change_Password_Activity.this,message);
            }
        }
    }

    public void isValid() {
        boolean done = true;
        o=true;
        validator.validate();
        //o=done;
        if(!done)
            o=done;

    }

}
