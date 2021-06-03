package com.softteco.searchGitHub.network;

import com.softteco.searchGitHub.model.Item;

import java.util.List;

public class ResponseObject {
    private List<Item> items;

    public ResponseObject(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }
}
