package com.keld.bioxy.view.ProfileView;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.keld.bioxy.model.Frame;
import com.keld.bioxy.model.User;
import com.keld.bioxy.repositories.ItemRepository;
import com.keld.bioxy.repositories.ProfileRepository;

public class ProfileViewModel extends AndroidViewModel {
    private ProfileRepository profileRepository;
    private ItemRepository itemRepository;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String token) {
        profileRepository = ProfileRepository.getInstance(token);
    }

    private MutableLiveData<User> resultUserDetail = new MutableLiveData<>();
    public void getUserDetail(int id){
        resultUserDetail = profileRepository.getUserDetail(id);
    }
    public LiveData<User> getResultUserDetail(){
        return resultUserDetail;
    }

    private MutableLiveData<Frame> resultFrameDetail = new MutableLiveData<>();
    public void getFrameDetail(int id){
        resultFrameDetail = profileRepository.getFrameDetail(id);
    }
    public LiveData<Frame> getResultFrameDetail(){
        return resultFrameDetail;
    }

    public LiveData<String> logout() {
        profileRepository.resetInstances();
        return profileRepository.logout();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        profileRepository.resetInstances();
    }
}
