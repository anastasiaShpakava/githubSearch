package com.softteco.searchGitHub.model;

import com.google.gson.annotations.SerializedName;


public class Item {

    @SerializedName("full_name")
    private String full_name;

    @SerializedName("description")
    private String description;

    @SerializedName("html_url")
    private String url;

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
