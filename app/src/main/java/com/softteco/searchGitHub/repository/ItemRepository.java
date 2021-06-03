package com.softteco.searchGitHub.repository;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softteco.searchGitHub.model.Root;
import com.softteco.searchGitHub.network.RestClient;
import com.softteco.searchGitHub.network.RestApiBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemRepository {
    private MutableLiveData<Root> itemsResponseLiveData;
    private RestClient restClient;
    private Call<Root> callItems;

    public ItemRepository() {
        itemsResponseLiveData = new MutableLiveData<>();
        restClient = new RestApiBuilder().getService();
    }

    public void fetchData(String q) {
        callItems = restClient.searchRepos(q);
        callItems.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                itemsResponseLiveData.postValue(response.body());
            }
            @Override
            public void onFailure(Call<Root> call, Throwable t) {
            }
        });
    }

    public LiveData<Root> getItemsResponseLiveData() {
        return itemsResponseLiveData;
    }
}
