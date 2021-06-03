package com.softteco.searchGitHub.repository;

import android.util.Log;

import com.softteco.searchGitHub.model.Item;
import com.softteco.searchGitHub.network.GitHubClient;
import com.softteco.searchGitHub.network.GithubApiBuilder;
import com.softteco.searchGitHub.network.ResponseObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemRepository {
    private static ItemRepository itemRepository;

    public static ItemRepository getInstance(){
        if (itemRepository == null){
            itemRepository = new ItemRepository();
        }
        return itemRepository;
    }


}
