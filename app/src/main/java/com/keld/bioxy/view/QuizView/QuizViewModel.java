package com.keld.bioxy.view.QuizView;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.keld.bioxy.model.Difficulty;
import com.keld.bioxy.model.Soal;
import com.keld.bioxy.model.User;
import com.keld.bioxy.repositories.DifficultyRepository;
import com.keld.bioxy.repositories.QuizRepository;

public class QuizViewModel extends AndroidViewModel {
    private QuizRepository quizRepository;
    private DifficultyRepository difficultyRepository;

    public QuizViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String token){
        quizRepository = QuizRepository.getInstance(token);
    }

    //== Begin of view model to get Soal
    private MutableLiveData<Soal> resultQuiz = new MutableLiveData<>();
    public void getQuiz(){
        resultQuiz = quizRepository.getQuiz();
    }
    public LiveData<Soal> getResultQuiz(){
        return resultQuiz;
    }

    //==Begin of view model to get Difficulty
    private MutableLiveData<Difficulty> resultDifficulty = new MutableLiveData<>();
    public void getDifficulty(){
        resultDifficulty = difficultyRepository.getDifficulty();
    }
    public LiveData<Difficulty> getResultDifficulty(){return resultDifficulty;}

    //==Begin of view model to Store Quiz Result
    private MutableLiveData<User> resultUser = new MutableLiveData<>();
    public void getUser(int id){
        resultUser = quizRepository.getUser(id);
    }
    public LiveData<User> getResultUser(){return resultUser;}

    @Override
    protected void onCleared() {
        super.onCleared();
        quizRepository.resetInstances();
    }
}
