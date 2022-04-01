package com.saify.tech.ohhh.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.saify.tech.ohhh.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.aabhasjindal.otptextview.OtpTextView;

public class VerifyActivity extends AppCompatActivity {


    @NotEmpty
    @BindView(R.id.back_verification_otp_page_Img)
    ImageView BackVerification;

    @OnClick(R.id.back_verification_otp_page_Img)
    public void BackVerify() {
        startActivity(new Intent(VerifyActivity.this, MainActivity.class));

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        ButterKnife.bind(this);


    }


}
