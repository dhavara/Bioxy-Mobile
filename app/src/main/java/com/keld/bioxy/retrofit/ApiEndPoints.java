package com.keld.bioxy.retrofit;

import com.google.gson.JsonObject;
import com.keld.bioxy.model.Soal;
import com.keld.bioxy.model.TokenResponse;
import com.keld.bioxy.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiEndPoints {
    @POST("login")
    @FormUrlEncoded
    Call<TokenResponse> login(@Field("username") String username, @Field("password") String password);

    @GET("quiz")
    Call<Soal> getQuiz();

    @POST("quiz/{store}")
    Call<User> getUser(@Path("user") String code);

    @POST("logout")
    Call<JsonObject> logout();
}
