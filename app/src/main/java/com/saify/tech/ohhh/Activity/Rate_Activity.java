package com.saify.tech.ohhh.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.saify.tech.ohhh.R;

import butterknife.ButterKnife;

public class Rate_Activity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        ButterKnife.bind(this);

    }
}
