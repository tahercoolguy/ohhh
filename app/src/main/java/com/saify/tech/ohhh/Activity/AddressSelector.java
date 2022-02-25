package com.saify.tech.ohhh.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    @BindView(R.id.english_Txt)
    TextView Englishh;

    @NotEmpty
    @BindView(R.id.arabic_Txt)
    TextView Arabicc;

    @OnClick(R.id.english_Txt)
    public void Englishh() {
        startActivity(new Intent(AddressSelector.this, MainActivity.class));
        finish();

    }

    @OnClick(R.id.arabic_Txt)
    public void Arabic() {
        startActivity(new Intent(AddressSelector.this, MainActivity.class));
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_selector);
        ButterKnife.bind(this);

    }
}
