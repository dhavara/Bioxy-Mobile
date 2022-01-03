package com.keld.bioxy.retrofit;

import com.google.gson.JsonObject;
import com.keld.bioxy.helper.Const;
import com.keld.bioxy.model.Difficulty;
import com.keld.bioxy.model.Soal;
import com.keld.bioxy.model.TokenResponse;
import com.keld.bioxy.model.User;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private final ApiEndPoints api;
    private static RetrofitService service;
    private static final String TAG = "RetrofitService";

    public RetrofitService(String token){
        OkHttpClient.Builder client = new OkHttpClient.Builder();

        if (token.equals("")) {
            client.addInterceptor(chain -> {
                Request request = chain.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .build();
                return chain.proceed(request);
            });
        } else {
            client.addInterceptor(chain -> {
                Request request = chain.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Authorization", token)
                        .build();
                return chain.proceed(request);
            });
        }

        api = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build().create(ApiEndPoints.class);
    }

    public static RetrofitService getInstance(String token) {
        if (service == null) {
            service = new RetrofitService(token);
        } else if (!token.equals("")) {
            service = new RetrofitService(token);
        }
        return service;
    }

    public Call<TokenResponse> login(String username, String password) {
        return api.login(username, password);
    }

    public Call<Soal> getQuiz(int id){
        return api.getQuiz(id);
    }

    public Call<Difficulty> getDifficulties(){
        return api.getDifficulties();
    }

    public Call<User> getUser(int id){
        return api.getUser(id);
    }

    public Call<JsonObject> logout() {
        return api.logout();
    }
}
