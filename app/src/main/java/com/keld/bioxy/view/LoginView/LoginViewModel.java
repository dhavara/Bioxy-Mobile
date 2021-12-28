package com.keld.bioxy.view.LoginView;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.keld.bioxy.model.TokenResponse;
import com.keld.bioxy.repositories.AuthRepository;

public class LoginViewModel extends AndroidViewModel {
    private AuthRepository authRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        authRepository = AuthRepository.getInstance();
    }

    public MutableLiveData<TokenResponse> login(String username, String password){
        return authRepository.login(username, password);
    }
}
