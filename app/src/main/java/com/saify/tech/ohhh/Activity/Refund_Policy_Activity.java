
package com.saify.tech.ohhh.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Refund_Policy_Activity extends AppCompatActivity{


    private User user;
    @NotEmpty
    @BindView(R.id.back_term_and_condition)
    ImageView back;


    @OnClick(R.id.back_term_and_condition)
    public void Back_Term() {
        startActivity(new Intent(Refund_Policy_Activity.this,Account_Activity.class));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund_policy);
        ButterKnife.bind(this);
        user = new User(this);

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
    }












