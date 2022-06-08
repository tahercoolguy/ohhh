package com.saify.tech.ohhh.Services;


//import io.opencensus.stats.Stats;


import com.saify.tech.ohhh.DataModel.AreaDM;
import com.saify.tech.ohhh.DataModel.BannerDM;
import com.saify.tech.ohhh.DataModel.BestDM;
import com.saify.tech.ohhh.DataModel.CatgoryListDM;
import com.saify.tech.ohhh.DataModel.ChangePasswordDM;
import com.saify.tech.ohhh.DataModel.EventsDM;
import com.saify.tech.ohhh.DataModel.FShopsDM;
import com.saify.tech.ohhh.DataModel.GovernatesDM;
import com.saify.tech.ohhh.DataModel.LoginDM;
import com.saify.tech.ohhh.DataModel.MyWishlistDM;
import com.saify.tech.ohhh.DataModel.MyprofileDM;
import com.saify.tech.ohhh.DataModel.NewsDM;
import com.saify.tech.ohhh.DataModel.OffersApiDM;
import com.saify.tech.ohhh.DataModel.ProductsBycatIdDM;
import com.saify.tech.ohhh.DataModel.RestaurentDM;
import com.saify.tech.ohhh.DataModel.ShopByIdDM;
import com.saify.tech.ohhh.DataModel.ShopsBycatIdDM;
import com.saify.tech.ohhh.DataModel.ShopsDM;
import com.saify.tech.ohhh.DataModel.SignUpDM;
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
     @GET("/ohhh/offers")
     public void Offers(Callback<OffersApiDM> offersApiDMCallback);

     //13
     @GET("/ohhh/best")
     public void Best(Callback<BestDM> offersApiDMCallback);

      //14
     @GET("/ohhh/fshops")
     public void FShops(Callback<FShopsDM> fShopsDMCallback);

     //15
    @FormUrlEncoded
    @POST("/ohhh/Shop")
    public void Shop(@Field("id") String id,
                       Callback<ShopByIdDM> shopByIdDMCallback);

}
