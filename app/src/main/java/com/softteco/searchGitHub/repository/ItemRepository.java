package com.softteco.searchGitHub.repository;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softteco.searchGitHub.model.Root;
import com.softteco.searchGitHub.network.RestClient;
import com.softteco.searchGitHub.network.RestApiBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemRepository {
    private final MutableLiveData<Root> itemsResponseLiveData;
    private final RestClient restClient;

    public ItemRepository() {
        itemsResponseLiveData = new MutableLiveData<>();
        restClient = new RestApiBuilder().getService();
    }

    public void fetchData(String q) {
        Call<Root> callItems = restClient.searchRepos(q);
        callItems.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(@NonNull Call<Root> call, @NonNull Response<Root> response) {
                itemsResponseLiveData.postValue(response.body());
            }
            @Override
            public void onFailure(@NonNull Call<Root> call, @NonNull Throwable t) {
            }
        });
    }

    public LiveData<Root> getItemsResponseLiveData() {
        return itemsResponseLiveData;
    }
}
