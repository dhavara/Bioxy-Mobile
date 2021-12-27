package com.keld.bioxy.view.SoalView;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.keld.bioxy.model.Soal;
import com.keld.bioxy.repositories.SoalRepository;

public class SoalViewModel extends AndroidViewModel {
    private SoalRepository soalRepository;
    private static final String TAG = "SoalViewModel";

    public SoalViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String token){
        Log.d(TAG, "init: "+token);
        soalRepository = SoalRepository.getInstance(token);
    }

    //== Begin of view model to get all Quiz
    private MutableLiveData<Soal> resultSoal = new MutableLiveData<>();
    public void getSoal(){
        resultSoal = soalRepository.getSoal();
    }
    public LiveData<Soal> getResultSoal(){
        return resultSoal;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        soalRepository.resetInstances();
    }
}
