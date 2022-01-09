package com.keld.bioxy.retrofit;

import com.google.gson.JsonObject;
import com.keld.bioxy.model.Difficulty;
import com.keld.bioxy.model.Frame;
import com.keld.bioxy.model.Leaderboard;
import com.keld.bioxy.model.ResultResponse;
import com.keld.bioxy.model.Soal;
import com.keld.bioxy.model.TokenResponse;
import com.keld.bioxy.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
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

    @GET("leaderboard")
    Call<Leaderboard> getLeaderboard();

    @GET("users/{id}")
    Call<User> getUserDetail (@Path("id") int id); //id user

    @GET("frames/{id}")
    Call<Frame> getFrameDetail (@Path("id") int id); //id frame

    @POST("quiz/store")
    @FormUrlEncoded
    Call<ResultResponse> result(@Field("difficulty") String difficulty,
                                  @Field("point") int point,
                                  @Field("total_correct") int total_correct,
                                  @Field("total_question") int total_question);

    @POST("logout")
    Call<JsonObject> logout();
}
