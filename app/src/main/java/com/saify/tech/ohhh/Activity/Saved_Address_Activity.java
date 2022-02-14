
package com.saify.tech.ohhh.Activity;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;

public class Saved_Address_Activity extends AppCompatActivity{


    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_address);
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












