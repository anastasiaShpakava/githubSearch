package com.softteco.searchGitHub.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softteco.searchGitHub.model.Item;
import com.softteco.searchGitHub.model.Root;
import com.softteco.searchGitHub.repository.ItemRepository;

import java.util.ArrayList;
import java.util.List;


public class ItemViewModel extends AndroidViewModel {

    private ItemRepository itemRepository;
    private LiveData<Root> itemsResponseLiveData;
    private MutableLiveData<List<Item>> addedItems;
    private List<Item> newItems;

    public ItemViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        itemRepository = new ItemRepository();
        itemsResponseLiveData = itemRepository.getItemsResponseLiveData();
        addedItems = new MutableLiveData<List<Item>>();
        newItems = new ArrayList<>();
        addItem();
    }

    public void addItem() {
        newItems.addAll(itemsResponseLiveData.getValue().getItems());
        addedItems.setValue(newItems);
    }

    public void searchItems(String q, int page, int resultsPerPage) {
     itemRepository.fetchData(q, page, resultsPerPage);
    }

    public LiveData<Root> getItemsResponseLiveData() {
        return itemsResponseLiveData;
    }
    public MutableLiveData<List<Item>> getNewItem(){
        return addedItems;
    }
}
