package com.keld.bioxy.repositories;

import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.DiffUtil;

import com.keld.bioxy.model.Difficulty;
import com.keld.bioxy.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DifficultyRepository {
    private RetrofitService apiService;
    private static DifficultyRepository difficultyRepository;

    private DifficultyRepository(String token) {
        apiService = RetrofitService.getInstance(token);
    }

    public static DifficultyRepository getInstance(String token) {
        if (difficultyRepository == null) {
            difficultyRepository = new DifficultyRepository(token);
        }
        return difficultyRepository;
    }
    public synchronized void resetInstances() {
        if (difficultyRepository != null) {
            difficultyRepository = null;
        } else {
            difficultyRepository = null;
        }
    }

    public MutableLiveData<Difficulty> getDifficulties() {
        final MutableLiveData<Difficulty> listDifficulty = new MutableLiveData<>();
        apiService.getDifficulties().enqueue(new Callback<Difficulty>() {
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
