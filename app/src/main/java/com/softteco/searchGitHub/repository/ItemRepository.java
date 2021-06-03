package com.softteco.searchGitHub.repository;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softteco.searchGitHub.network.GitHubClient;
import com.softteco.searchGitHub.network.GithubApiBuilder;
import com.softteco.searchGitHub.network.ResponseObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemRepository {
    private MutableLiveData<ResponseObject> itemsResponseLiveData;
    private GitHubClient gitHubClient;
    private Call<ResponseObject> callItems;

    public ItemRepository() {
        itemsResponseLiveData = new MutableLiveData<>();
        gitHubClient = new GithubApiBuilder().getService();
    }

    public void fetchData(String q) {
        callItems = gitHubClient.searchRepos(q);
        callItems.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                itemsResponseLiveData.postValue(response.body());
            }
            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
            }
        });
    }

    public LiveData<ResponseObject> getItemsResponseLiveData() {
        return itemsResponseLiveData;
    }
}
