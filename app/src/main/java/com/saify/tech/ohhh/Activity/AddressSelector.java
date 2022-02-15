package com.saify.tech.ohhh.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.saify.tech.ohhh.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;


public class AddressSelector extends AppCompatActivity {

    @NotEmpty
    @BindView(R.id.english_Btn)
    Button Englishh;

    @NotEmpty
    @BindView(R.id.arabic_Btn)
    Button Arabicc;

    @OnClick(R.id.english_Btn)
    public void Englishh() {
        startActivity(new Intent(AddressSelector.this, Account_Activity.class));

    }

    @OnClick(R.id.arabic_Btn)
    public void Arabic() {
        startActivity(new Intent(AddressSelector.this, Saved_Address_Activity.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_selector);
        ButterKnife.bind(this);

    }
}
