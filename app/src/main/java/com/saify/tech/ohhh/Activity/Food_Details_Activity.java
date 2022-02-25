
package com.saify.tech.ohhh.Activity;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.saify.tech.ohhh.Adapter.Order_Info_Adapter;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.OrderInfoDM;
import com.saify.tech.ohhh.Fragments.Deep_and_Deep_2_Fragment;
import com.saify.tech.ohhh.Fragments.Feed_Categories_Fragment;
import com.saify.tech.ohhh.Fragments.Fragment_Home_Screen;
import com.saify.tech.ohhh.Fragments.Fragment_Shops;
import com.saify.tech.ohhh.Helper.DialogUtil;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.R;
import com.saify.tech.ohhh.Utils.ConnectionDetector;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.MaybeOnSubscribe;

public class Food_Details_Activity extends AppCompatActivity {

    AppController appController;

    private User user;
    Context context;
    Dialog progress;
    ConnectionDetector connectionDetector;
    DialogUtil dialogUtil;
    int count = 0;

    String strCounter;

    @BindView(R.id.smalll_RL)
    ImageView smalll_RL;

    @BindView(R.id.medium_RL)
    ImageView medium_RL;

    @BindView(R.id.large_RL)
    ImageView large_RL;

    @BindView(R.id.cheese_1RL)
    ImageView cheese_1RL;

    @BindView(R.id.mayonese_2Rl)
    ImageView mayonese_2Rl;

    @BindView(R.id.extra_chocklate_3Rl)
    ImageView extra_chocklate_3Rl;

    @BindView(R.id.add_to_cart_Btn)
    TextView add_to_cart_Btn;

    @BindView(R.id.cart_RL)
    RelativeLayout cart_rl;

    @BindView(R.id.back_RL)
    RelativeLayout back;

    @BindView(R.id.minus_cake_Rl)
    RelativeLayout minus;


    @BindView(R.id.plus_cake_RL)
    RelativeLayout plus;


    @BindView(R.id.minus_plus_Txt)
    TextView minus_plus_text;


    @OnClick(R.id.minus_cake_Rl)
    public void Minus() {

        if (count > 0)
            count--;
        strCounter = Integer.toString(count);
        minus_plus_text.setText(strCounter);

    }

    @OnClick(R.id.plus_cake_RL)
    public void Plus() {

        count++;
        strCounter = Integer.toString(count);
        minus_plus_text.setText(strCounter);

    }


    @OnClick(R.id.back_RL)
    public void bacck() {

        finish();

    }

    @OnClick(R.id.cart_RL)
    public void cartt() {

        startActivity(new Intent(Food_Details_Activity.this, Cart_Activity.class));
    }

    @OnClick(R.id.add_to_cart_Btn)
    public void cart() {

        startActivity(new Intent(Food_Details_Activity.this, Cart_Activity.class));
    }

    @OnClick(R.id.cheese_1RL)
    public void cheese() {

        ifHome11 = true;
        cheese1();
    }

    @OnClick(R.id.mayonese_2Rl)
    public void mayonese() {

        ifHome21 = true;
        Mayonese1();

    }

    @OnClick(R.id.extra_chocklate_3Rl)
    public void chocklate() {

        ifHome31 = true;
        ExtraChocklate1();

    }


    @OnClick(R.id.smalll_RL)
    public void Small() {

        ifHome1 = true;
        Home1();
    }

    @OnClick(R.id.medium_RL)
    public void Medium() {
        ifHome2 = true;
        Home2();
    }

    @OnClick(R.id.large_RL)
    public void Large() {

        ifHome3 = true;
        Home3();
    }


//    @NotEmpty
//    @BindView(R.id.order_info_Rcv)
//    RecyclerView order_info_Rcvv;
//
//
//    @OnClick(R.id.knet_RL)
//    public void Knet() {
//
//        Knett();
//        ifknet=true;
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        ButterKnife.bind(this);
        dialogUtil = new DialogUtil();
        context = ((MainActivity) context);
        appController = (AppController) getApplicationContext();
        connectionDetector = new ConnectionDetector(getApplicationContext());
        user = new User(Food_Details_Activity.this);
        user = new User(this);
        idMappings();


        ArrayList<OrderInfoDM> orderInfoDMS = new ArrayList<>();

        orderInfoDMS.add(new OrderInfoDM("Subtotal", "5.000 KWD"));
        orderInfoDMS.add(new OrderInfoDM("Code Applied", "Mx3029"));
        orderInfoDMS.add(new OrderInfoDM("Discount", "50%"));
        orderInfoDMS.add(new OrderInfoDM("Delivery Charges", "3.000 KWD"));
        orderInfoDMS.add(new OrderInfoDM("Total", "2.500 KWD"));


//            HistoryRcv.setLayoutManager(new LinearLayoutManager(context));
//            HistoryRcv.setAdapter(new HistoryDM_Adapter((context), historyDMS));

//
        Order_Info_Adapter dm = new Order_Info_Adapter(Food_Details_Activity.this, orderInfoDMS);
        LinearLayoutManager l = new LinearLayoutManager(this);
//        order_info_Rcvv.setLayoutManager(l);
//        order_info_Rcvv.setAdapter(dm);


    }


    boolean ifHome11 = false;
    boolean ifHome21 = false;
    boolean ifHome31 = false;

    public void cheese1() {

        if (ifHome11) {

            cheese_1RL.setBackground(getDrawable(R.drawable.ic_freee));
            mayonese_2Rl.setBackground(getDrawable(R.drawable.ic_unfree));
            extra_chocklate_3Rl.setBackground(getDrawable(R.drawable.ic_unfree));


        }

    }

    public void Mayonese1() {

        if (ifHome21) {

            cheese_1RL.setBackground(getDrawable(R.drawable.ic_unfree));
            mayonese_2Rl.setBackground(getDrawable(R.drawable.ic_freee));
            extra_chocklate_3Rl.setBackground(getDrawable(R.drawable.ic_unfree));


        }

    }

    public void ExtraChocklate1() {

        if (ifHome31) {

            cheese_1RL.setBackground(getDrawable(R.drawable.ic_unfree));
            mayonese_2Rl.setBackground(getDrawable(R.drawable.ic_unfree));
            extra_chocklate_3Rl.setBackground(getDrawable(R.drawable.ic_freee));


        }

    }


    boolean ifHome1 = false;
    boolean ifHome2 = false;
    boolean ifHome3 = false;

    public void Home1() {

        if (ifHome1) {

            smalll_RL.setBackground(getDrawable(R.drawable.ic_freee));
            medium_RL.setBackground(getDrawable(R.drawable.ic_unfree));
            large_RL.setBackground(getDrawable(R.drawable.ic_unfree));


        }

    }

    public void Home2() {

        if (ifHome2) {

            smalll_RL.setBackground(getDrawable(R.drawable.ic_unfree));
            medium_RL.setBackground(getDrawable(R.drawable.ic_freee));
            large_RL.setBackground(getDrawable(R.drawable.ic_unfree));


        }

    }

    public void Home3() {

        if (ifHome3) {

            smalll_RL.setBackground(getDrawable(R.drawable.ic_unfree));
            medium_RL.setBackground(getDrawable(R.drawable.ic_unfree));
            large_RL.setBackground(getDrawable(R.drawable.ic_freee));


        }

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

//
//    boolean ifcash = false;
//    boolean ifknet = false;
//    boolean ifcard = false;
//
//    public void Cashh() {
//
//        if (ifcash) {
//
//            cash.setBackground(getDrawable(R.drawable.ic_payment_card_selected));
//        }
//    }
//
//    public void Knett() {
//
//        if (ifknet) {
//
//            knet.setBackground(getDrawable(R.drawable.ic_payment_card_selected));
//        }
//    }
//
//    public void Cardd() {
//
//        if (ifcard) {
//
//            visa.setBackground(getDrawable(R.drawable.ic_payment_card_selected));
//        }
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












