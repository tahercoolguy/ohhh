package com.saify.tech.ohhh.Services;


//import io.opencensus.stats.Stats;


import com.saify.tech.ohhh.DataModel.AddQuantityDM;
import com.saify.tech.ohhh.DataModel.AddressListDM;
import com.saify.tech.ohhh.DataModel.AddtoCartDM;
import com.saify.tech.ohhh.DataModel.AllAreaDM;
import com.saify.tech.ohhh.DataModel.AreaDM;
import com.saify.tech.ohhh.DataModel.BannerDM;
import com.saify.tech.ohhh.DataModel.BestDM;
import com.saify.tech.ohhh.DataModel.CatgoryListDM;
import com.saify.tech.ohhh.DataModel.ChangePasswordDM;
import com.saify.tech.ohhh.DataModel.EventsDM;
import com.saify.tech.ohhh.DataModel.FShopsDM;
import com.saify.tech.ohhh.DataModel.FlavoursDM;
import com.saify.tech.ohhh.DataModel.GovernatesDM;
import com.saify.tech.ohhh.DataModel.LoginDM;
import com.saify.tech.ohhh.DataModel.MyCartDM;
import com.saify.tech.ohhh.DataModel.MyOrderDM;
import com.saify.tech.ohhh.DataModel.MyWishlistDM;
import com.saify.tech.ohhh.DataModel.MyprofileDM;
import com.saify.tech.ohhh.DataModel.NewsDM;
import com.saify.tech.ohhh.DataModel.OffersApiDM;
import com.saify.tech.ohhh.DataModel.OrderNowDM;
import com.saify.tech.ohhh.DataModel.PrivacyPolicyDM;
import com.saify.tech.ohhh.DataModel.ProductsByIdDM;
import com.saify.tech.ohhh.DataModel.ProductsBycatIdDM;
import com.saify.tech.ohhh.DataModel.ProductsbyAreaIdDM;
import com.saify.tech.ohhh.DataModel.RemoveCartDM;
import com.saify.tech.ohhh.DataModel.RemoveWishlistDM;
import com.saify.tech.ohhh.DataModel.RestaurentDM;
import com.saify.tech.ohhh.DataModel.SaveAddressDM;
import com.saify.tech.ohhh.DataModel.ShopByIdDM;
import com.saify.tech.ohhh.DataModel.ShopsBycatIdDM;
import com.saify.tech.ohhh.DataModel.ShopsDM;
import com.saify.tech.ohhh.DataModel.SignUpDM;
import com.saify.tech.ohhh.DataModel.SizesDM;
import com.saify.tech.ohhh.DataModel.SubCategorylistByCatIdForShopDM;
import com.saify.tech.ohhh.DataModel.TermsConditionDM;
import com.saify.tech.ohhh.DataModel.UpdateProfileDM;
import com.saify.tech.ohhh.DataModel.VideoDM;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.mime.MultipartTypedOutput;

public interface PAServices {
    @Headers("Cache-Control: no-cache;")
    @POST("/ohhh/SignUp")
    void SignUp(@Body MultipartTypedOutput multipartTypedOutput, Callback<SignUpDM> signUpDMCallback);

    @Headers("Cache-Control: no-cache;")
    @POST("/auth/record_video")
    void RecordedVideo(@Body MultipartTypedOutput multipartTypedOutput, Callback<VideoDM> videoDMCallback);

    @Headers("Cache-Control: no-cache;")
    @POST("/auth/user_login")
    void LoginIn(@Body MultipartTypedOutput multipartTypedOutput, Callback<SignUpDM> signUpDMCallback);

    @Headers("Cache-Control: no-cache;")
   @GET("/user/view_home_banner")
    void Banner(Callback<BannerDM> bannerDMCallback);

    @Headers("Cache-Control: no-cache;")
    @GET("/user/view_news")
    void News(Callback<NewsDM> newsDMCallback);

    @Headers("Cache-Control: no-cache;")
    @GET("/user/view_events")
    void Events(Callback<EventsDM> eventsDMCallback);

    @Headers("Cache-Control: no-cache;")
    @GET("/user/view_restaurants")
    void Restaurent(Callback<RestaurentDM> restaurentDMCallback);


    @Headers("Cache-Control: no-cache;")
    @GET("/user/view_shops")
    void Shops(Callback<ShopsDM> shopsDMCallback);

//
//    @Headers("Cache-Control: no-cache;")
//    @POST("/ohhh/webservices/ohhh/SignUp")
//    void signUp(@Body MultipartTypedOutput multipartTypedOutput, Callback<SignUpDM> simpleDMCallback);
//


    // 1
    @FormUrlEncoded
    @POST("/ohhh/SignUp")
    public void SignUp(@Field("firstname") String firstname,
                       @Field("lastname") String lastname,
                       @Field("email") String email,
                       @Field("password") String password,
                       @Field("confpassword") String confpassword,
                       @Field("phone") String phone,
                       @Field("countrycode") String countrycode,

                       Callback<SignUpDM> signUpDMCallback);


    //2
    @FormUrlEncoded
    @POST("/ohhh/login")
    public void Login(@Field("email") String email,
                      @Field("password") String password,
                      Callback<LoginDM> loginDMCallback);

    //3
    @FormUrlEncoded
    @POST("/ohhh/changePassword")
    public void ChangePassword(@Field("userid") String userid,
                         @Field("password") String password,
                         @Field("confpassword") String confpassword,
                       Callback<ChangePasswordDM> changePasswordDMCallback);

    //4
    @GET("/ohhh/catgorylist")
    public void CatgoryList(Callback<CatgoryListDM> catgoryListDMCallback);


    //5
    @GET("/ohhh/shops")
    public void ohhhShops(Callback<ShopsDM> shopsDMCallback);

    //6
    @FormUrlEncoded
    @POST("/ohhh/ShopsBycatId")
    public void ShopsBycatId(@Field("cat_id") String cat_id,
                               Callback<ShopsBycatIdDM> shopsBycatIdDMCallback);


    //7
    @FormUrlEncoded
    @POST("/ohhh/ProductsBycatId")
    public void ProductsBycatId(@Field("cat_id") String cat_id,
                               Callback<ProductsBycatIdDM> productsBycatIdDMCallback);


    //8
    @GET("/ohhh/governates")
    public void Governates(Callback<GovernatesDM> governatesDMCallback);

    //9
    @FormUrlEncoded
    @POST("/ohhh/area")
    public void Area(@Field("governates_id") String governates_id,
                     Callback<AreaDM> areaDMCallback);

     //9
     @FormUrlEncoded
     @POST("/ohhh/Myprofile")
     public void Myprofile(@Field("user_id") String user_id,
                  Callback<MyprofileDM> myprofileDMCallback);

      //10
      @FormUrlEncoded
      @POST("/ohhh/UpdateProfile")
      public void UpdateProfile(@Field("user_id") String user_id,
                                @Field("fname") String fname,
                                @Field("lname") String lname,
                                @Field("phone") String phone,
                                @Field("email") String email,
                       Callback<UpdateProfileDM> updateProfileDMCallback);

     //11
      @FormUrlEncoded
      @POST("/ohhh/MyWishlist")
      public void MyWishlist(@Field("user_id") String user_id,
                       Callback<MyWishlistDM> myWishlistDMCallback);


     //12
     @FormUrlEncoded
     @POST("/ohhh/offersbyAreaId")
     public void Offers(@Field("area_id") String area_id,
                       Callback<OffersApiDM> offersApiDMCallback);

     //13
     @FormUrlEncoded
     @POST("/ohhh/bestbyAreaId")
     public void Best(@Field("area_id") String area_id,
                      Callback<BestDM> offersApiDMCallback);

      //14
      @FormUrlEncoded
     @POST("/ohhh/fshopsbyAreaId")
     public void FShops(@Field("area_id") String area_id,
                      Callback<FShopsDM> fShopsDMCallback);

     //15
    @FormUrlEncoded
    @POST("/ohhh/Shop")
    public void Shop(@Field("id") String id,
                       Callback<ShopByIdDM> shopByIdDMCallback);

     //16
     @FormUrlEncoded
     @POST("/ohhh/AddressList")
     public void AddressList(@Field("user_id") String user_id,
                       Callback<AddressListDM> addressListDMCallback);


    //17
     @FormUrlEncoded
    @POST("/ohhh/SaveAddress")
    public void SaveAddress(@Field("user_id") String user_id,
                            @Field("name") String name,
                            @Field("email") String email,
                            @Field("countrycode") String countrycode,
                            @Field("mobile_no") String mobile_no,
                            @Field("area") String area,
                            @Field("governate") String governate,
                            @Field("building_no") String building_no,
                            @Field("block") String block,
                            @Field("street") String street,
                            @Field("floor_no") String floor_no,
                            @Field("lat") String lat,
                            @Field("lan") String lan,
                         Callback<SaveAddressDM> saveAddressDMCallback);


    //16
     @FormUrlEncoded
     @POST("/ohhh/productsById")
     public void ProductsById(@Field("id") String id,
                  Callback<ProductsByIdDM> shopByIdDMCallback);


    //17
    @FormUrlEncoded
    @POST("/ohhh/Sizes")
    public void Size(@Field("product_id") String product_id,
                          Callback<SizesDM> sizesDMCallbacks);

    //18
    @FormUrlEncoded
    @POST("/ohhh/Flavours")
    public void Flavours(@Field("product_id") String product_id,
                        Callback<FlavoursDM> flavoursDMCallback);

    //19
    @FormUrlEncoded
    @POST("/ohhh/AddtoCart")
    public void AddtoCart(@Field("user_id") String user_id,
                          @Field("product_id") String product_id,
                          @Field("shop_id") String shop_id,
                          @Field("quantity") String quantity,
                          @Field("comments") String comments,
                          @Field("addons") String addons,
                          Callback<AddtoCartDM> addtoCartDMCallback);

    //20
    @FormUrlEncoded
    @POST("/ohhh/MyCart")
    public void MyCart(@Field("user_id") String user_id,
                         Callback<MyCartDM> myCartDMCallback);


    //21
    @FormUrlEncoded
    @POST("/ohhh/AddQuantity")
    public void AddQuantity(@Field("user_id") String user_id,
                            @Field("product_id") String product_id,
                            @Field("cart_id") String cart_id,
                            @Field("type") String type,
                            Callback<AddQuantityDM> addQuantityDMCallbackk);


   //22
   @FormUrlEncoded
   @POST("/ohhh/RemoveCart")
    public void RemoveCart(@Field("user_id") String user_id,
                         @Field("product_id") String product_id,
                         @Field("cart_id") String cart_id,
                         Callback<RemoveCartDM> addQuantityDMCallbackk);

   //23
   @FormUrlEncoded
   @POST("/ohhh/AddtoWishlist")
   public void AddtoWishlist(@Field("user_id") String user_id,
                        @Field("product_id") String product_id,
                        @Field("shop_id") String shop_id,
                        Callback<AddtoCartDM> addtoCartDMCallback);

   //24
    @FormUrlEncoded
    @POST("/ohhh/RemoveWishlist")
    public void RemoveWishlist(@Field("user_id") String user_id,
                        @Field("product_id") String product_id,
                        @Field("shop_id") String shop_id,
                        Callback<RemoveWishlistDM> removeWishlistDMCallback);


   //25
   @FormUrlEncoded
   @POST("/ohhh/OrderNow")
   public void OrderNow(@Field("user_id") String user_id,
                        @Field("shop_id") String shop_id,
                        @Field("shipping_address") String shipping_address,
                        @Field("paymentMethod") String paymentMethod,
                        Callback<OrderNowDM> orderNowDMCallback);

   //26
    @FormUrlEncoded
    @POST("/ohhh/MyOrder")
    public void MyOrder(@Field("user_id") String user_id,
                         Callback<MyOrderDM> myOrderDMCallback);

    //27
     @GET("/ohhh/allArea")
     public void AllArea(Callback<AllAreaDM> allAreaDMCallback);

    //28
    @FormUrlEncoded
    @POST("/ohhh/productsbyAreaId")
    public void productsbyAreaId(@Field("area_id") String area_id,
                     Callback<ProductsbyAreaIdDM> productsbyAreaIdDMCallback);


     //29
    @GET("/ohhh/termsCondition")
    public void TermsCondition(Callback<TermsConditionDM> termsConditionDMCallback);


    //30
    @GET("/ohhh/privacyPolicy")
    public void privacyPolicy(Callback<PrivacyPolicyDM> privacyPolicyDMCallback);


    //31
    @GET("/ohhh/refundPolicy")
    public void refundPolicy(Callback<PrivacyPolicyDM> privacyPolicyDMCallback);


     //32
     @FormUrlEncoded
     @POST("/ohhh/subCategorylistByCatIdForShop")
     public void SubCategorylistByCatIdForShop(@Field("cat_id") String cat_id,
                                               @Field("shop_id") String shop_id,
                                               Callback<SubCategorylistByCatIdForShopDM> subCategorylistByCatIdForShopDMCallback);


}
