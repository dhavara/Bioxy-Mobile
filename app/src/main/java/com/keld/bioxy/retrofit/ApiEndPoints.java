package com.keld.bioxy.retrofit;

import com.google.gson.JsonObject;
import com.keld.bioxy.model.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiEndPoints {
    @POST("login")
    @FormUrlEncoded
    Call<TokenResponse> login(@Field("username") String username, @Field("password") String password);

    @POST("logout")
    Call<JsonObject> logout();
}
