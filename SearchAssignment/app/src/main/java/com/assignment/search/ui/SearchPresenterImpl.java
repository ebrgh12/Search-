package com.assignment.search.ui;

import android.util.Log;

import com.assignment.search.BuildConfig;
import com.assignment.search.model.SearchResults;
import com.assignment.search.network.SearchAPI;
import com.assignment.search.util.Constants;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchPresenterImpl implements SearchPresenter {

    private SearchView searchView;
    private SearchAPI searchAPI;
    private Call<SearchResults> call;
// Method to search
    public SearchPresenterImpl(SearchView searchView) {
        this.searchView = searchView;

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if(BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(new StethoInterceptor());
        }
        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        searchAPI = retrofit.create(SearchAPI.class);
    }
//Method to get the searched results.
    @Override
    public void getSearchResults(String query) {
        call = searchAPI.getSearchResults(query);
        call.enqueue(new Callback<SearchResults>() {

            @Override
            public void onResponse(Call<SearchResults> call, Response<SearchResults> response) {
                if(response.code() == 200 && response.body() != null) {
                    SearchResults searchResults = response.body();
                    if(searchResults.getError() == null) {
                        searchView.updateSearchResults(response.body());
                    }else {
                        searchView.showError();
                    }
                }else {
                    searchView.showError();
                }
            }

            @Override
            public void onFailure(Call<SearchResults> call, Throwable t) {
                Log.e("SearchPresenterImpl","Exception",t);
                searchView.showError();
            }
        });
    }

    @Override
    public void onDestroy(){
        if(call != null && !call.isCanceled()){
            call.cancel();
        }
    }
}
