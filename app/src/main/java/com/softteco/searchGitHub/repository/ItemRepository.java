package com.softteco.searchGitHub.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softteco.searchGitHub.model.Item;
import com.softteco.searchGitHub.network.GitHubClient;
import com.softteco.searchGitHub.network.GithubApiBuilder;
import com.softteco.searchGitHub.network.ResponseObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemRepository {
    private MutableLiveData<ResponseObject> itemsResponseLiveData;

    public ItemRepository() {
        itemsResponseLiveData = new MutableLiveData<>();
    }

   public void fetchData(String q) {

        GitHubClient gitHubClient = new GithubApiBuilder().getService();
        Call<ResponseObject> callEntities = gitHubClient.searchRepos(q);
        callEntities.enqueue(new Callback<ResponseObject>() {
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
