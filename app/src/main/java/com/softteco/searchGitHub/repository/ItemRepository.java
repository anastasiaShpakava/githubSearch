package com.softteco.searchGitHub.repository;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softteco.searchGitHub.model.Item;
import com.softteco.searchGitHub.model.Root;
import com.softteco.searchGitHub.network.RestClient;
import com.softteco.searchGitHub.network.RestApiBuilder;
import com.softteco.searchGitHub.ui.ItemAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemRepository {
    private final MutableLiveData<Root> itemsResponseLiveData;
    private final RestClient restClient;
    private List<Item> newItems;
    private ItemAdapter itemAdapter;

    public ItemRepository() {
        itemsResponseLiveData = new MutableLiveData<>();
        restClient = new RestApiBuilder().getService();
        itemAdapter = new ItemAdapter();
    }

    public void fetchData(String q, int page, int resultsPerPage) {
        Call<Root> callItems = restClient.searchRepos(q, page, resultsPerPage);
        callItems.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(@NonNull Call<Root> call, @NonNull Response<Root> response) {
                itemsResponseLiveData.postValue(response.body());
                newItems = response.body().getItems();
                itemAdapter.addItems(newItems);
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
