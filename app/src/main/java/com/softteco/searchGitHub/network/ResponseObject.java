package com.softteco.searchGitHub.network;

import com.google.gson.annotations.SerializedName;
import com.softteco.searchGitHub.model.Item;

import java.util.List;

public class ResponseObject {
    @SerializedName("items")
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }
}
