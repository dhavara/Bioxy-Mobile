package com.keld.bioxy.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.keld.bioxy.model.Soal;
import com.keld.bioxy.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SoalRepository {
    private static SoalRepository soalRepository;
    private RetrofitService apiService;
    private static final String TAG = "soalRepository";

    private SoalRepository(String token) {
        Log.d(TAG, "token: " + token);
        apiService = RetrofitService.getInstance(token);
    }

    public static SoalRepository getInstance(String token) {
        if (soalRepository == null) {
            soalRepository = new SoalRepository(token);
        }
        return soalRepository;
    }

    public synchronized void resetInstances() {
        if (soalRepository != null) {
            soalRepository = null;
        } else {
            soalRepository = null;
        }
    }

    public MutableLiveData<Soal> getSoal() {
        final MutableLiveData<Soal> listSoal = new MutableLiveData<>();
        apiService.getSoal().enqueue(new Callback<Soal>() {
            @Override
            public void onResponse(Call<Soal> call, Response<Soal> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: " + response.body().getSoal().size());
                        listSoal.postValue(response.body());
                    }
                }
            }


            @Override
            public void onFailure(Call<Soal> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listSoal;
    }
}
