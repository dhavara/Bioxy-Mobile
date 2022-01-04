package com.keld.bioxy.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.keld.bioxy.model.User;
import com.keld.bioxy.retrofit.RetrofitService;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileRepository {
    private static ProfileRepository profileRepository;
    private RetrofitService apiService;
    private static final String TAG = "ProfileRepository";

    private ProfileRepository(String token){
        Log.d(TAG, "token" +token);
        apiService = RetrofitService.getInstance(token);
    }

    public static ProfileRepository getInstance(String token){
        if (profileRepository == null){
            profileRepository = new ProfileRepository(token);
        }

        return profileRepository;
    }

    public synchronized void resetInstances(){
        if (profileRepository !=null){
            profileRepository = null;
        }
    }

    public MutableLiveData<User> getUserDetail(int id){
        final MutableLiveData<User> listUserDetail = new MutableLiveData<>();
        apiService.getUserDetail(id).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: " + response.body());
                        listUserDetail.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        return listUserDetail;
    }

    public LiveData<String> logout(){
        MutableLiveData<String> message = new MutableLiveData<>();
        apiService.logout().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()){
                    if (response.body() != null){
                        try{
                            JSONObject object = new JSONObject(new Gson().toJson(response.body()));
                            String msg = object.getString("message");
                            Log.d(TAG, "onResponse: " +msg);
                            message.postValue(msg);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        return message;
    }
}
