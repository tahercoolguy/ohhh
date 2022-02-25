
package com.saify.tech.ohhh.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.saify.tech.ohhh.Adapter.Cart_Adapter;
import com.saify.tech.ohhh.Adapter.Nornoya_Dip_Adapter;
import com.saify.tech.ohhh.Adapter.Offers_Adapter;
import com.saify.tech.ohhh.DataModel.CartDM;
import com.saify.tech.ohhh.DataModel.Nornoya_Dip_Dm;
import com.saify.tech.ohhh.DataModel.OffersDM;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Cart_Activity extends AppCompatActivity {


    private User user;
    @NotEmpty
    @BindView(R.id.back_cart)
    ImageView back;
//
//    @BindView(R.id.nornoya_Rcv)
//    RecyclerView nonoya;
//
//    @BindView(R.id.checkout)
//    TextView checkout1;
//
//
//    @BindView(R.id.checkout2)
//    TextView checkout2;
//
//
//    @BindView(R.id.dip_and_dip_Rcv)
//    RecyclerView dip_and_dip;
//
//    @OnClick(R.id.checkout)
//    public void Check1() {
//
//        startActivity(new Intent(Cart_Activity.this,Address_Activity.class));
//    }
//
//    @OnClick(R.id.checkout2)
//    public void Check2() {
//
//        startActivity(new Intent(Cart_Activity.this,Address_Activity.class));
//    }

    @OnClick(R.id.back_cart)
    public void Back_Cart() {
         finish();

    }
    @BindView(R.id.nornoya_deep_Rcv)
    RecyclerView nornoya_deep_Rcvv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);
        user = new User(this);
//        Nornoya();
//        Dip_N_Dip();
        Nornoya_Dip_Adapt();

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

//    private void Nornoya() {
//        ArrayList<CartDM> cartDMS = new ArrayList<>();
//        cartDMS.add(new CartDM("Pastries", "Extra Mayonese", "10.000 KWD", "01", R.drawable.nornoya_1));
//        cartDMS.add(new CartDM("Pastries", "Extra Mayonese", "10.000 KWD", "02", R.drawable.nornoya_2));
//        cartDMS.add(new CartDM("Pastries", "Extra Mayonese", "10.000 KWD", "03", R.drawable.nornoya_3));
//
//        Cart_Adapter dm = new Cart_Adapter(this, cartDMS);
//
//        LinearLayoutManager l
//                = new LinearLayoutManager(Cart_Activity.this, LinearLayoutManager.VERTICAL, false);
//        nonoya.setLayoutManager(l);
//        nonoya.setAdapter(dm);
//    }


    private void Nornoya_Dip_Adapt() {
        ArrayList<Nornoya_Dip_Dm> nornoya_dip_dms = new ArrayList<>();
        nornoya_dip_dms.add(new Nornoya_Dip_Dm("Nornoya",R.drawable.nornoya));
        nornoya_dip_dms.add(new Nornoya_Dip_Dm("Dip N Dip",R.drawable.deep_small));


        Nornoya_Dip_Adapter dm = new Nornoya_Dip_Adapter(this, nornoya_dip_dms);

        LinearLayoutManager l
                = new LinearLayoutManager(Cart_Activity.this, LinearLayoutManager.VERTICAL, false);
        nornoya_deep_Rcvv.setLayoutManager(l);
        nornoya_deep_Rcvv.setAdapter(dm);
    }

//    private void Dip_N_Dip() {
//        ArrayList<CartDM> cartDMS = new ArrayList<>();
//
//
//        Cart_Adapter dm = new Cart_Adapter(this, cartDMS);
//        cartDMS.add(new CartDM("Pastries", "Extra Mayonese", "10.000 KWD", "01", R.drawable.nornoya_1));
//        cartDMS.add(new CartDM("Pastries", "Extra Mayonese", "10.000 KWD", "02", R.drawable.nornoya_2));
//        cartDMS.add(new CartDM("Pastries", "Extra Mayonese", "10.000 KWD", "03", R.drawable.nornoya_3));
//        LinearLayoutManager l
//                = new LinearLayoutManager(Cart_Activity.this, LinearLayoutManager.VERTICAL, false);
//        dip_and_dip.setLayoutManager(l);
//        dip_and_dip.setAdapter(dm);
//    }

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












