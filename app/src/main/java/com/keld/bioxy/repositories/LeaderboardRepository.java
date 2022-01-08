package com.keld.bioxy.repositories;

import androidx.lifecycle.MutableLiveData;

import com.keld.bioxy.model.Leaderboard;
import com.keld.bioxy.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaderboardRepository {
    private RetrofitService apiService;
    private static LeaderboardRepository leaderboardRepository;

    private LeaderboardRepository(String token) {
        apiService = RetrofitService.getInstance(token);
    }

    public static LeaderboardRepository getInstance(String token) {
        if (leaderboardRepository == null) {
            leaderboardRepository = new LeaderboardRepository(token);
        }
        return leaderboardRepository;
    }
    public synchronized void resetInstances() {
        if (leaderboardRepository != null) {
            leaderboardRepository = null;
        } else {
            leaderboardRepository = null;
        }
    }

    public MutableLiveData<Leaderboard> getleaderboard() {
        final MutableLiveData<Leaderboard> listLeaderboard = new MutableLiveData<>();
        apiService.getLeaderboard().enqueue(new Callback<Leaderboard>() {
            @Override
            public void onResponse(Call<Leaderboard> call, Response<Leaderboard> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        listLeaderboard.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Leaderboard> call, Throwable t) {
            }
        });

        return listLeaderboard;
    }
}
