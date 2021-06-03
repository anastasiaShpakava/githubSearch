package com.softteco.searchGitHub.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.softteco.searchGitHub.model.Item;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {

    private LiveData<List<Item>> allItems;


    public ItemViewModel(@NonNull Application application) {
        super(application);
    }
}
