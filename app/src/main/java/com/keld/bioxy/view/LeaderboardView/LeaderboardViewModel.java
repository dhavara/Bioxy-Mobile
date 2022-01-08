package com.keld.bioxy.view.LeaderboardView;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.keld.bioxy.model.Leaderboard;
import com.keld.bioxy.repositories.LeaderboardRepository;

public class LeaderboardViewModel extends AndroidViewModel {
    private LeaderboardRepository leaderboardRepository;


    public LeaderboardViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String token){
        leaderboardRepository = LeaderboardRepository.getInstance(token);
    }

    //==Begin of view model to get Leaderboard
    private MutableLiveData<Leaderboard> resultLeaderboard = new MutableLiveData<>();
    public void getLeaderboard(){
        resultLeaderboard = leaderboardRepository.getleaderboard();
    }
    public LiveData<Leaderboard> getResultLeaderboard(){
        return resultLeaderboard;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        leaderboardRepository.resetInstances();
    }
}
