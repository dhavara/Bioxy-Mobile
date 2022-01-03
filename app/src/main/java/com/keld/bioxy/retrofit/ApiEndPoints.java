package com.keld.bioxy.retrofit;

import com.google.gson.JsonObject;
import com.keld.bioxy.model.Difficulty;
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

    @GET("difficulty")
    Call<Difficulty> getDifficulties();

    @GET("quiz/{id}")
    Call<Soal> getQuiz(@Path("id") int id); //id difficulty

    @GET("user/{id}")
    Call<User> getUser (@Path("id") int id); //id user

    @POST("logout")
    Call<JsonObject> logout();
}
