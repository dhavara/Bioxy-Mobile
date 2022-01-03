package com.keld.bioxy.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.keld.bioxy.model.Difficulty;
import com.keld.bioxy.model.Soal;
import com.keld.bioxy.model.User;
import com.keld.bioxy.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizRepository {
    private static QuizRepository quizRepository;
    private RetrofitService apiService;

    private QuizRepository(String token) {
        apiService = RetrofitService.getInstance(token);
    }

    public static QuizRepository getInstance(String token) {
        if (quizRepository == null) {
            quizRepository = new QuizRepository(token);
        }
        return quizRepository;
    }

    public synchronized void resetInstances() {
        if (quizRepository != null) {
            quizRepository = null;
        } else {
            quizRepository = null;
        }
    }

    public MutableLiveData<Soal> getQuiz(int id) {
        final MutableLiveData<Soal> listSoal = new MutableLiveData<>();
        apiService.getQuiz(id).enqueue(new Callback<Soal>() {
            @Override
            public void onResponse(Call<Soal> call, Response<Soal> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        listSoal.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Soal> call, Throwable t) {
            }
        });

        return listSoal;
    }

    public MutableLiveData<User> getUser(int id) {
        final MutableLiveData<User> listUser = new MutableLiveData<>();
        apiService.getUser(id).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        listUser.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });

        return listUser;
    }
}
