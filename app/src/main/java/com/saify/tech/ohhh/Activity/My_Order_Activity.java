package com.saify.tech.ohhh.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Optional;
import com.saify.tech.ohhh.Adapter.VPAdapter;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.HistoryDM;
import com.saify.tech.ohhh.DataModel.OrderDM;
import com.saify.tech.ohhh.Fragments.History_My_Order_Fragment;
import com.saify.tech.ohhh.Fragments.Ongoing_My_Order_Fragment;
import com.saify.tech.ohhh.Helper.DialogUtil;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;
import com.saify.tech.ohhh.Utils.ConnectionDetector;
import com.saify.tech.ohhh.Utils.Helper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class My_Order_Activity extends AppCompatActivity implements Validator.ValidationListener {
    AppController appController;


    Dialog progress;
    ConnectionDetector connectionDetector;
    User user;
    DialogUtil dialogUtil;


    @Optional
    @NotEmpty
    @BindView(R.id.my_order_tab_layout)
    com.google.android.material.tabs.TabLayout tabLayout;

    @Optional
    @NotEmpty
    @BindView(R.id.viewpager_my_order)
    androidx.viewpager.widget.ViewPager ViewPager;

    @BindView(R.id.back_my_order)
    LinearLayout back;


    @OnClick(R.id.back_my_order)
    public void OrderBack() {

        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        ButterKnife.bind(this);


        dialogUtil = new DialogUtil();
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(getApplicationContext());
        user = new User(My_Order_Activity.this);
        validator = new Validator(this);
        validator.setValidationListener(this);
        tabLayout.setupWithViewPager(ViewPager);


        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new Ongoing_My_Order_Fragment(), getString(R.string.ongoing));
        vpAdapter.addFragment(new History_My_Order_Fragment(), getString(R.string.history));
        ViewPager.setAdapter(vpAdapter);

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
                Helper.showToast(My_Order_Activity.this, message);
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
