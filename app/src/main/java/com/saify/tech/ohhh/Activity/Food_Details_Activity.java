
package com.saify.tech.ohhh.Activity;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.saify.tech.ohhh.Adapter.Adapter_Food_Detail_Your_Choice;
import com.saify.tech.ohhh.Controller.AppController;
import com.saify.tech.ohhh.DataModel.Addons;
import com.saify.tech.ohhh.DataModel.AddtoCartDM;
import com.saify.tech.ohhh.DataModel.ProductsByIdDM;
import com.saify.tech.ohhh.Helper.DialogUtil;
import com.saify.tech.ohhh.Helper.User;
import com.saify.tech.ohhh.Models.ParentChildDataModel;
import com.saify.tech.ohhh.R;
import com.saify.tech.ohhh.Utils.ConnectionDetector;
import com.saify.tech.ohhh.Utils.Helper;
import com.squareup.picasso.Picasso;

import org.jdom2.Parent;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Food_Details_Activity extends AppCompatActivity {

    AppController appController;

    private User user;
    Context context;
    Dialog dialog;
    ConnectionDetector connectionDetector;
    DialogUtil dialogUtil;



    String ID;
    String ShopId;




    @BindView(R.id.your_choiceRV)
    RecyclerView your_choiceRV;

//
//    @BindView(R.id.flavourRV)
//    RecyclerView flavourRV;


    @BindView(R.id.add_to_cart_Btn)
    TextView add_to_cart_Btn;

    @BindView(R.id.cart_RL)
    RelativeLayout cart_rl;

    @BindView(R.id.back_RL)
    RelativeLayout back;

    @BindView(R.id.quantity_txt)
    TextView quantity_txt;

    @BindView(R.id.cake_name)
    TextView cake_name;

    @BindView(R.id.cake_Description_Txt)
    TextView cake_Description_Txt;


    @BindView(R.id.price_tv)
    TextView price_tv;

    @BindView(R.id.cake_img1)
    ImageView cake_img1;

int quantity=1;

    @OnClick(R.id.minus_img)
    public void minusClicked()
    {
        quantity = quantity-1;
        if(quantity!=0)
        {
            quantity_txt.setText(String.valueOf(quantity));
        }

    }


    @OnClick(R.id.plus_img)
    public void plusClicked()
    {
        quantity = quantity+1;

        quantity_txt.setText(String.valueOf(quantity));


    }


    @OnClick(R.id.back_RL)
    public void bacck() {

        finish();

    }

    @OnClick(R.id.cart_RL)
    public void cartt() {

        startActivity(new Intent(Food_Details_Activity.this, Cart_Activity.class));
    }
//
//    public String Size_id="";
//    public String Flavour_id="";

    public boolean checkminimun(int mininumValue , ArrayList<ParentChildDataModel> parentChildDataModels,String parent_id,String ParentName)
    {
        int count=0;
        for (ParentChildDataModel par: parentChildDataModels
             ) {
            if(par.getParent().equalsIgnoreCase(parent_id))
                count = count +1;
        }

        if(mininumValue < count)
        {
            Toast.makeText(Food_Details_Activity.this, "Kinldy Select Atleast"+mininumValue+ ParentName, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean checkmax(int maxValue , ArrayList<ParentChildDataModel> parentChildDataModels,String parent_id,String ParentName)
    {
        int count=0;
        for (ParentChildDataModel par: parentChildDataModels
        ) {
            if(par.getParent().equalsIgnoreCase(parent_id))
                count = count +1;
        }

        if(maxValue > count) {
            Toast.makeText(Food_Details_Activity.this, "You can select Max" + maxValue + ParentName, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

  public   ArrayList<ParentChildDataModel> parentChildDataModels=new ArrayList<>();

    public boolean checker()
    {
        boolean checkerMain=true;

        for (Addons addons:temp
             ) {
            checkerMain = checkminimun(addons.getOption_minimum(),parentChildDataModels,addons.getId(),addons.getOption_group_name());
            checkerMain = checkmax(Integer.parseInt(addons.getOption_maximum()),parentChildDataModels,addons.getId(),addons.getOption_group_name());
            if(!checkerMain)
                break;
        }


        return checkerMain;
    }
    

    @OnClick(R.id.add_to_cart_Btn)
    public void cart() {


        if (checker()) {
            if (connectionDetector.isConnectingToInternet()) {
                String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//
//            if (Size_id.equalsIgnoreCase("")) {
//                Helper.showToast(Food_Details_Activity.this, "Kindly select Size..");
//            }  if (Flavour_id.equalsIgnoreCase("")) {
//                Helper.showToast(Food_Details_Activity.this, "Kindly select Flavour..");
//            } else {
                String addons="";
                int count = 0;
                for (ParentChildDataModel parent:parentChildDataModels
                     ) {
                    if(count == 0)
                        addons = parent.getChild();
                    else
                        addons = addons +"," +parent.getChild();

                    count++;
                }
                dialog = dialogUtil.showProgressDialog(Food_Details_Activity.this, getString(R.string.please_wait));
                appController.paServices.AddtoCart(String.valueOf(user.getId()), ID, ShopId, String.valueOf(quantity), "best", addons, new Callback<AddtoCartDM>() {
                    @Override
                    public void success(AddtoCartDM addtoCartDM, Response response) {
                        dialog.dismiss();
                        if (addtoCartDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                             startActivity(new Intent(Food_Details_Activity.this, Cart_Activity.class));

                            Helper.showToast(Food_Details_Activity.this, addtoCartDM.getOutput().getMessage());

                        } else
                            Helper.showToast(Food_Details_Activity.this,addtoCartDM.getOutput().getMessage());
                    }
                    @Override
                    public void failure(RetrofitError retrofitError) {
                        Log.e("error", retrofitError.toString());
                    }
                });
            } else
                Helper.showToast(Food_Details_Activity.this, getString(R.string.no_internet_connection));
        }
    }
//
//    @OnClick(R.id.cheese_1RL)
//    public void cheese() {
//
//        ifHome11 = true;
//        cheese1();
//    }
//
//    @OnClick(R.id.mayonese_2Rl)
//    public void mayonese() {
//
//        ifHome21 = true;
//        Mayonese1();
//
//    }
//
//    @OnClick(R.id.extra_chocklate_3Rl)
//    public void chocklate() {
//
//        ifHome31 = true;
//        ExtraChocklate1();
//
//    }

//
//    @OnClick(R.id.smalll_Img)
//    public void Small() {
//
//        ifHome1 = true;
//        Home1();
//    }
//
//    @OnClick(R.id.medium_RL)
//    public void Medium() {
//        ifHome2 = true;
//        Home2();
//    }
//
//    @OnClick(R.id.large_RL)
//    public void Large() {
//
//        ifHome3 = true;
//        Home3();
//    }


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

        ID = getIntent().getStringExtra("id");
        idMappings();
        Binding();
//       size();
//        flavour();

//        ArrayList<OrderInfoDM> orderInfoDMS = new ArrayList<>();
//
//        orderInfoDMS.add(new OrderInfoDM("Subtotal", "5.000 KWD"));
//        orderInfoDMS.add(new OrderInfoDM("Code Applied", "Mx3029"));
//        orderInfoDMS.add(new OrderInfoDM("Discount", "50%"));
//        orderInfoDMS.add(new OrderInfoDM("Delivery Charges", "3.000 KWD"));
//        orderInfoDMS.add(new OrderInfoDM("Total", "2.500 KWD"));
//
//
////            HistoryRcv.setLayoutManager(new LinearLayoutManager(context));
////            HistoryRcv.setAdapter(new HistoryDM_Adapter((context), historyDMS));
//
////
//        Order_Info_Adapter dm = new Order_Info_Adapter(Food_Details_Activity.this, orderInfoDMS);
//        LinearLayoutManager l = new LinearLayoutManager(this);
//        order_info_Rcvv.setLayoutManager(l);
//        order_info_Rcvv.setAdapter(dm);


    }


    boolean ifHome11 = false;
    boolean ifHome21 = false;
    boolean ifHome31 = false;

//    public void cheese1() {
//
//        if (ifHome11) {
//
//            cheese_1RL.setBackground(getDrawable(R.drawable.ic_freee));
//            mayonese_2Rl.setBackground(getDrawable(R.drawable.ic_unfree));
//            extra_chocklate_3Rl.setBackground(getDrawable(R.drawable.ic_unfree));
//
//
//        }
//
//    }
//
//    public void Mayonese1() {
//
//        if (ifHome21) {
//
//            cheese_1RL.setBackground(getDrawable(R.drawable.ic_unfree));
//            mayonese_2Rl.setBackground(getDrawable(R.drawable.ic_freee));
//            extra_chocklate_3Rl.setBackground(getDrawable(R.drawable.ic_unfree));
//
//
//        }
//
//    }
//
//    public void ExtraChocklate1() {
//
//        if (ifHome31) {
//
//            cheese_1RL.setBackground(getDrawable(R.drawable.ic_unfree));
//            mayonese_2Rl.setBackground(getDrawable(R.drawable.ic_unfree));
//            extra_chocklate_3Rl.setBackground(getDrawable(R.drawable.ic_freee));
//
//
//        }
//
//    }


    boolean ifHome1 = false;
    boolean ifHome2 = false;
    boolean ifHome3 = false;

//    public void Home1() {
//
//        if (ifHome1) {
//
//            smalll_RL.setBackground(getDrawable(R.drawable.ic_freee));
//            medium_RL.setBackground(getDrawable(R.drawable.ic_unfree));
//            large_RL.setBackground(getDrawable(R.drawable.ic_unfree));
//
//
//        }
//
//    }

//    public void Home2() {
//
//        if (ifHome2) {
//
//            smalll_RL.setBackground(getDrawable(R.drawable.ic_unfree));
//            medium_RL.setBackground(getDrawable(R.drawable.ic_freee));
//            large_RL.setBackground(getDrawable(R.drawable.ic_unfree));
//
//
//        }
//
//    }

//    public void Home3() {
//
//        if (ifHome3) {
//
//            smalll_RL.setBackground(getDrawable(R.drawable.ic_unfree));
//            medium_RL.setBackground(getDrawable(R.drawable.ic_unfree));
//            large_RL.setBackground(getDrawable(R.drawable.ic_freee));
//
//
//        }
//
//    }

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

public ArrayList<Addons> temp;
    
    public void Binding() {
        if (connectionDetector.isConnectingToInternet()) {
            {
                String refreshedToken = FirebaseInstanceId.getInstance().getToken();

//                progress = dialogUtil.showProgressDialog(Food_Details_Activity.this, getString(R.string.please_wait));

                appController.paServices.ProductsById(ID, new Callback<ProductsByIdDM>() {
                    @Override
                    public void success(ProductsByIdDM productsByIdDM, Response response) {
//                        progress.dismiss();
                        temp=productsByIdDM.getOutput().getAddons();
                        if (productsByIdDM.getOutput().getSuccess().equalsIgnoreCase("1")) {

                            ShopId=productsByIdDM.getOutput().getInfo().get(0).getShop_id();
                            cake_name.setText(productsByIdDM.getOutput().getInfo().get(0).getTitle_en());
                            price_tv.setText(productsByIdDM.getOutput().getInfo().get(0).getPrice());
                            cake_Description_Txt.setText(Html.fromHtml(productsByIdDM.getOutput().getInfo().get(0).getContent_en(), Html.FROM_HTML_MODE_COMPACT));

                            if (!productsByIdDM.getOutput().getInfo().get(0).getImage().equalsIgnoreCase("")) {
                                Picasso.with(context).load(productsByIdDM.getOutput().getInfo().get(0).getImage()).into(cake_img1);
                            }

                            if(productsByIdDM.getOutput().getAddons()!=null) {
                                Adapter_Food_Detail_Your_Choice dm = new Adapter_Food_Detail_Your_Choice(Food_Details_Activity.this, productsByIdDM.getOutput().getAddons());
                                LinearLayoutManager l = new LinearLayoutManager(Food_Details_Activity.this);
                                your_choiceRV.setLayoutManager(l);
                                your_choiceRV.setAdapter(dm);
                            }
//
//                            Helper.showToast(Food_Details_Activity.this, productsByIdDM.getOutput().getMessage());

                        } else

                            Helper.showToast(Food_Details_Activity.this, getString(R.string.Api_data_not_found));
                    }

                    @Override
                    public void failure(RetrofitError error) {

                        Log.e("String", error.toString());
                    }
                });
            }
        } else
            Helper.showToast(Food_Details_Activity.this, getString(R.string.no_internet_connection));
    }

//    public void size()
//
//        {
////    ArrayList<Size> shopssDMS = new ArrayList<>();
////               shopssDMS.add(new Size("small","1000"));
////              shopssDMS.add(new Size("medium","2000"));
//
//
//        if (connectionDetector.isConnectingToInternet()) {
//            {
//                String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//
////                progress = dialogUtil.showProgressDialog(Food_Details_Activity.this, getString(R.string.please_wait));
//
//                appController.paServices.Size(ID, new Callback<SizesDM>() {
//                    @Override
//                    public void success(SizesDM sizesDM, Response response) {
////                        progress.dismiss();
//                        if (sizesDM.getOutput().getSuccess().equalsIgnoreCase("1")) {
//
//
//                            Adapter_Food_Detail_Size_flavour dm = new Adapter_Food_Detail_Size_flavour(Food_Details_Activity.this, sizesDM.getOutput().getInfo());
//                            LinearLayoutManager l = new LinearLayoutManager(Food_Details_Activity.this);
//                            sizeRV.setLayoutManager(l);
//                            sizeRV.setAdapter(dm);
//
//
//                        } else
//
//                            Helper.showToast(Food_Details_Activity.this,getString(R.string.Api_data_not_found));
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//
//                        Log.e("String", error.toString());
//                    }
//                });
//            }
//        } else
//            Helper.showToast(Food_Details_Activity.this, getString(R.string.no_internet_connection));
//
//    }
//
//    public void flavour()
//    {
//
////        ArrayList<Size> shopssDMS = new ArrayList<>();
////        shopssDMS.add(new Size("small","1000"));
//
//        if (connectionDetector.isConnectingToInternet()) {
//            {
//                String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//
////                progress = dialogUtil.showProgressDialog(Food_Details_Activity.this, getString(R.string.please_wait));
//
//                appController.paServices.Flavours(ID, new Callback<FlavoursDM>() {
//                    @Override
//                    public void success(FlavoursDM flavoursDM, Response response) {
////                        progress.dismiss();
//                        if (flavoursDM.getOutput().getSuccess().equalsIgnoreCase("1")) {
//
//              Adapter_Food_Detail_Flavour dm = new Adapter_Food_Detail_Flavour (Food_Details_Activity.this, flavoursDM.getOutput().getInfo());
//              LinearLayoutManager l = new LinearLayoutManager(Food_Details_Activity.this);
//              flavourRV.setLayoutManager(l);
//              flavourRV.setAdapter(dm);
//
//                        } else
//
//                            Helper.showToast(Food_Details_Activity.this,getString(R.string.Api_data_not_found));
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//
//                        Log.e("String", error.toString());
//                    }
//                });
//            }
//        } else
//            Helper.showToast(Food_Details_Activity.this, getString(R.string.no_internet_connection));
//
//    }

}















