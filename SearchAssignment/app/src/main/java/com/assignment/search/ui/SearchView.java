package com.assignment.search.ui;

import com.assignment.search.model.SearchResults;
//View interfce
public interface SearchView {

    void updateSearchResults(SearchResults searchResults);

    void showError();

}
