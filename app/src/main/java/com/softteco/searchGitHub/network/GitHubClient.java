package com.softteco.searchGitHub.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GitHubClient {
    @GET("/search/repositories")
    Call<ResponseObject> searchRepos(
            @Query("q") String q
    );
}
