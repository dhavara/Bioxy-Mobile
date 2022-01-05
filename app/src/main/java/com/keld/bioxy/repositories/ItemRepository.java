package com.keld.bioxy.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.keld.bioxy.model.Frame;
import com.keld.bioxy.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemRepository {
    private RetrofitService apiService;
    private static ItemRepository itemRepository;

    private ItemRepository(String token) {
        apiService = RetrofitService.getInstance(token);
    }

    public static ItemRepository getInstance(String token) {
        if (itemRepository == null) {
            itemRepository = new ItemRepository(token);
        }
        return itemRepository;
    }
    public synchronized void resetInstances() {
        if (itemRepository != null) {
            itemRepository = null;
        } else {
            itemRepository = null;
        }
    }

    public MutableLiveData<Frame> getFrameDetail(int id) {
        final MutableLiveData<Frame> listFrameDetail = new MutableLiveData<>();
        apiService.getFrameDetail(id).enqueue(new Callback<Frame>() {
            @Override
            public void onResponse(Call<Frame> call, Response<Frame> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        listFrameDetail.postValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Frame> call, Throwable t) {
            }
        });
        return listFrameDetail;
    }
}
