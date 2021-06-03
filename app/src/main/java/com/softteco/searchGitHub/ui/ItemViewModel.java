package com.softteco.searchGitHub.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.softteco.searchGitHub.model.Item;
import com.softteco.searchGitHub.network.ResponseObject;
import com.softteco.searchGitHub.repository.ItemRepository;


public class ItemViewModel extends AndroidViewModel {

    private ItemRepository itemRepository;
    private LiveData<ResponseObject> itemsResponseLiveData;

    public ItemViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        itemRepository = new ItemRepository();
        itemsResponseLiveData = itemRepository.getItemsResponseLiveData();
    }

    public void searchItems(String q) {
     itemRepository.fetchData(q);
    }

    public LiveData<ResponseObject> getItemsResponseLiveData() {
        return itemsResponseLiveData;
    }
}
