package com.saify.tech.ohhh.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.iid.FirebaseInstanceId;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.LoginDM;
import com.saify.tech.ohhh.Helper.DialogUtil;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;
import com.saify.tech.ohhh.Utils.ConnectionDetector;
import com.saify.tech.ohhh.Utils.Helper;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.twitter.sdk.android.core.models.TwitterCollection;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class LoginActivity extends AppCompatActivity implements Validator.ValidationListener {
    AppController appController;
    Dialog progress;
    ConnectionDetector connectionDetector;
    User user;
    DialogUtil dialogUtil;
    int position=0;

    @NotEmpty
    @Email
    @BindView(R.id.emailET)
    EditText EmailET;


    @NotEmpty
    @BindView(R.id.passwordET)
    EditText passwordET;

//    @OnClick(R.id.emailET)
//    public void em() {
////        ifemail = true;
//        EmailET.setBackground(getDrawable(R.drawable.box2));
//        passwordET.setBackground(getDrawable(R.drawable.box));
//
////        Email_Selected();
//     }

//    @OnClick(R.id.passwordET)
//    public void pass() {
////        ifpassword = true;
////        Password_Selected();
//
//        EmailET.setBackground(getDrawable(R.drawable.box));
//        passwordET.setBackground(getDrawable(R.drawable.box2));
//    }


    @OnClick(R.id.signInTxt)
    public void SignIn() {

        if(connectionDetector.isConnectingToInternet())
        {

            boolean correct = true;
            if(EmailET.getText().toString().equalsIgnoreCase(""))
            {
                correct=false;
                Helper.showToast(LoginActivity.this,"kindly enter your email");
            }

            else if(passwordET.getText().toString().equalsIgnoreCase(""))
            {
                correct=false;
                Helper.showToast(LoginActivity.this,"kindly enter your password");
            }

            else if (correct) {
                String refreshedToken = FirebaseInstanceId.getInstance().getToken();

                progress = dialogUtil.showProgressDialog(LoginActivity.this, getString(R.string.please_wait));

                appController.paServices.Login(EmailET.getText().toString(), passwordET.getText().toString(), new Callback<LoginDM>() {

                            @Override

                            public void success ( LoginDM loginDM, Response response ) {
                                progress.dismiss();
                                if (loginDM.getOutput().getSuccess().equalsIgnoreCase("1")) {
//                        Helper.shwToast(LoginActivity.this,customerRegisterDM.getMessage());
                                    user.setId(Integer.valueOf(loginDM.getOutput().getData().get(position).getId()));

                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();

                                } else
                                    Helper.showToast(LoginActivity.this, loginDM.getOutput().getMessage());
                            }

                            @Override
                            public void failure ( RetrofitError retrofitError ) {
                                progress.dismiss();

                                Log.e("error", retrofitError.toString());

                            }
                        });
            }
        }else
            Helper.showToast(LoginActivity.this,getString(R.string.no_internet_connection));




//        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @NotEmpty
    @BindView(R.id.signUpTxt)
    TextView SignUpTxt;


    @OnClick(R.id.signUpTxt)
    public void signUpTxt() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    @NotEmpty
    @BindView(R.id.to_facebook_login)
    RelativeLayout To_Facebook_Login;

    @NotEmpty
    @BindView(R.id.to_google_login)
    RelativeLayout To_Google_Login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        ButterKnife.bind(this);
        dialogUtil = new DialogUtil();
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(getApplicationContext());
        user = new User(LoginActivity.this);
        validator = new Validator(this);
        validator.setValidationListener(this);


    }


//    boolean ifemail = false;
//    boolean ifpassword = false;
//
//    public void Email_Selected() {
//
//        EmailET.setBackground(getDrawable(R.drawable.box2));
//        passwordET.setBackground(getDrawable(R.drawable.box));
//
//    }
//
//    public void Password_Selected() {
//
//        EmailET.setBackground(getDrawable(R.drawable.box));
//        passwordET.setBackground(getDrawable(R.drawable.box2));
//    }

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
                Helper.showToast(LoginActivity.this, message);
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
