package com.softteco.searchGitHub.model;

import com.google.gson.annotations.SerializedName;

public class License {
    @SerializedName("key")
    public String key;
    @SerializedName("name")
    public String name;
    @SerializedName("spdx_id")
    public String spdx_id;
    @SerializedName("url")
    public String url;
    @SerializedName("node_id")
    public String node_id;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpdx_id() {
        return spdx_id;
    }

    public void setSpdx_id(String spdx_id) {
        this.spdx_id = spdx_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }
}
