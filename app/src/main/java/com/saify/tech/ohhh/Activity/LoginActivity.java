package com.saify.tech.ohhh.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.SignUpDM;
import com.saify.tech.ohhh.Helper.DialogUtil;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;
import com.saify.tech.ohhh.Utils.ConnectionDetector;
import com.saify.tech.ohhh.Utils.Helper;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.MultipartTypedOutput;
import retrofit.mime.TypedString;

public class LoginActivity extends AppCompatActivity implements  Validator.ValidationListener{
    AppController appController;

    Dialog progress;
    ConnectionDetector connectionDetector;
    User user;
    DialogUtil dialogUtil;

    @NotEmpty
    @Email
    @BindView(R.id.emailET)
    EditText EmailET;

    @NotEmpty
    @BindView(R.id.passwordET)
    EditText passwordET;

    @OnClick(R.id.signInBtn)
    public void SignIn()
    {

    }

    @NotEmpty
    @BindView(R.id.signUpTxt)
    TextView SignUpTxt;


    @OnClick(R.id.signUpTxt)
    public void signUpTxt()
    {
        Intent intent=new Intent(LoginActivity.this,SignUpActivity.class);
        startActivity(intent);
    }

    @NotEmpty
    @BindView(R.id.to_facebook_login)
    RelativeLayout To_Facebook_Login;

    @NotEmpty
    @BindView(R.id.to_google_login)
    RelativeLayout To_Google_Login;

    @OnClick(R.id.to_google_login)
    public void To_Google_Login()
    {

    }

    @OnClick(R.id.to_facebook_login)
    public void To_Facebook_Login()
    {

    }


//
//    @OnClick(R.id.guestBtn)
//    public void guestBtn()
//    {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        ButterKnife.bind(this);
        dialogUtil = new DialogUtil();
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(getApplicationContext());
        user = new User(LoginActivity.this);
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
                Helper.showToast(LoginActivity.this,message);
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
