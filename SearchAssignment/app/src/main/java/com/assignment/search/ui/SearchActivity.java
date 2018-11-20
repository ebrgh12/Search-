package com.assignment.search.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.assignment.search.R;
import com.assignment.search.adapter.SearchResultAdapter;
import com.assignment.search.model.ItemListElement;
import com.assignment.search.model.SearchResults;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subjects.PublishSubject;

public class SearchActivity extends AppCompatActivity implements SearchView{

    private SearchPresenterImpl presenter;
    private SearchResultAdapter adapter;
    private List<ItemListElement> items = new ArrayList<>();
    private TextView noResultsTV;
    private RecyclerView resultsRV;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        presenter = new SearchPresenterImpl(this);
        setUpViews();
    }
    // Setup views
    private void setUpViews() {
        resultsRV = findViewById(R.id.resultsRV);
        noResultsTV = findViewById(R.id.noResultsTV);
        progressBar = findViewById(R.id.progressBar);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        resultsRV.setLayoutManager(layoutManager);
        adapter = new SearchResultAdapter(items);
        resultsRV.setAdapter(adapter);

        final EditText searchET = findViewById(R.id.searchET);
        searchET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED){
                    validateTextAndSearch(searchET.getText().toString());
                    return true;
                }
                return false;
            }
        });

    }
//Method to validate the text
    private void validateTextAndSearch(String query) {
        if(!query.isEmpty()){
            presenter.getSearchResults(query);
            noResultsTV.setVisibility(View.GONE);
            resultsRV.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            hideKeyboard();
        }else {
            Toast.makeText(this, "Please enter query", Toast.LENGTH_SHORT).show();
        }
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.toggleSoftInput(0, 0);
    }
//Method to update the results
    @Override
    public void updateSearchResults(SearchResults searchResults) {
        progressBar.setVisibility(View.GONE);
        if(searchResults != null && searchResults.getItemListElement().size() > 0){
            items.clear();
            items.addAll(searchResults.getItemListElement());
            adapter.notifyDataSetChanged();
            noResultsTV.setVisibility(View.GONE);
            resultsRV.setVisibility(View.VISIBLE);
        }else{
            noResultsTV.setVisibility(View.VISIBLE);
            resultsRV.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError() {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, "Some error occurred, please try again later...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
