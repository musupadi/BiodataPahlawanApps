package com.destinyapp.skripsiapps.API;

import com.destinyapp.skripsiapps.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiRequest {
    @FormUrlEncoded
    @POST("Login.php")
    Call<ResponseModel> login(@Field("username") String username,
                                  @Field("password") String password);

    @FormUrlEncoded
    @POST("Register.php")
    Call<ResponseModel> register(@Field("username") String username,
                              @Field("password") String password,
                                 @Field("nama") String nama,
                                 @Field("email") String email,
                                 @Field("profile") String profile);

    @FormUrlEncoded
    @POST("DataUser.php")
    Call<ResponseModel> Datauser(@Field("username") String username);
}
