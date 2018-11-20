package com.assignment.search.network;

import com.assignment.search.model.SearchResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchAPI {

    @GET("/api/assessment_search_wrapper")
    Call<SearchResults> getSearchResults(@Query("query") String query);
}
