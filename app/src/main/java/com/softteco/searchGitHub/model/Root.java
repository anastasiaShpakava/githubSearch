package com.softteco.searchGitHub.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Root {

    @SerializedName("items")
    private List<Item> items;

    @SerializedName("total_count")
    private int total_count;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }
}
