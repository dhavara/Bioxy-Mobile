package com.keld.bioxy.view.QuizView;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.keld.bioxy.model.Difficulty;
import com.keld.bioxy.model.Leaderboard;
import com.keld.bioxy.model.ResultResponse;
import com.keld.bioxy.model.Soal;
import com.keld.bioxy.model.User;
import com.keld.bioxy.repositories.DifficultyRepository;
import com.keld.bioxy.repositories.QuizRepository;

import javax.xml.transform.Result;

public class QuizViewModel extends AndroidViewModel {
    private QuizRepository quizRepository;
    private DifficultyRepository difficultyRepository;

    public QuizViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String token){
        quizRepository = QuizRepository.getInstance(token);
        difficultyRepository = DifficultyRepository.getInstance(token);
    }

    //== Begin of view model to get Soal
    private MutableLiveData<Soal> resultQuiz = new MutableLiveData<>();
    public void getQuiz(int id){
        resultQuiz = quizRepository.getQuiz(id);
    }
    public LiveData<Soal> getResultQuiz(){
        return resultQuiz;
    }

    //==Begin of view model to get Difficulty
    private MutableLiveData<Difficulty> resultDifficulty = new MutableLiveData<>();
    public void getDifficulties(){
        resultDifficulty = difficultyRepository.getDifficulties();
    }
    public LiveData<Difficulty> getResultDifficulties(){return resultDifficulty;}

    //==Begin of view model to Store Quiz Result
    public MutableLiveData<ResultResponse> result(String difficulty, int point, int total_correct, int total_number) {
        return quizRepository.result(difficulty, point, total_correct, total_number);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        quizRepository.resetInstances();
    }
}
