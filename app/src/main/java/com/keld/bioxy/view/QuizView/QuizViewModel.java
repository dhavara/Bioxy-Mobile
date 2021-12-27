package com.keld.bioxy.view.QuizView;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.keld.bioxy.model.Soal;
import com.keld.bioxy.model.User;
import com.keld.bioxy.repositories.QuizRepository;

public class QuizViewModel extends AndroidViewModel {
    private QuizRepository quizRepository;
    private static final String TAG = "QuizViewModel";

    public QuizViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String token){
        Log.d(TAG, "init: "+token);
        quizRepository = QuizRepository.getInstance(token);
    }

    //== Begin of view model to get all course
    private MutableLiveData<Soal> resultQuiz = new MutableLiveData<>();
    public void getQuiz(){
        resultQuiz = quizRepository.getQuiz();
    }
    public LiveData<Soal> getResultQuiz(){
        return resultQuiz;
    }

    //==Begin of view model to get detail course
    private MutableLiveData<User> resultUser = new MutableLiveData<>();
    public void getUser(String code){
        resultUser = quizRepository.getUser(code);
    }
    public LiveData<User> getResultUser(){return resultUser;}

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        quizRepository.resetInstances();
    }
}
