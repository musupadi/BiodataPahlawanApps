package com.destinyapp.skripsiapps.API;

import com.destinyapp.skripsiapps.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiRequest {
    @FormUrlEncoded
    @POST("UserLogin.php")
    Call<ResponseModel> login(@Field("username") String username,
                                  @Field("password") String password);
}
