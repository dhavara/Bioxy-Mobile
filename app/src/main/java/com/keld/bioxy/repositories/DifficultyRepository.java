package com.keld.bioxy.repositories;

import androidx.lifecycle.MutableLiveData;

import com.keld.bioxy.model.Difficulty;
import com.keld.bioxy.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DifficultyRepository {
    private static DifficultyRepository difficultyRepository;
    private RetrofitService apiService;

    public MutableLiveData<Difficulty> getDifficulty() {
        final MutableLiveData<Difficulty> listDifficulty = new MutableLiveData<>();
        apiService.getDifficulty().enqueue(new Callback<Difficulty>() {
            @Override
            public void onResponse(Call<Difficulty> call, Response<Difficulty> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        listDifficulty.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Difficulty> call, Throwable t) {
            }
        });

        return listDifficulty;
    }
}
