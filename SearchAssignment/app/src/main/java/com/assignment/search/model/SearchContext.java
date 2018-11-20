
package com.assignment.search.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchContext {

    @SerializedName("@vocab")
    @Expose
    private String vocab;
    @SerializedName("goog")
    @Expose
    private String goog;
    @SerializedName("EntitySearchResult")
    @Expose
    private String entitySearchResult;
    @SerializedName("detailedDescription")
    @Expose
    private String detailedDescription;
    @SerializedName("resultScore")
    @Expose
    private String resultScore;
    @SerializedName("kg")
    @Expose
    private String kg;

    public String getVocab() {
        return vocab;
    }

    public void setVocab(String vocab) {
        this.vocab = vocab;
    }

    public String getGoog() {
        return goog;
    }

    public void setGoog(String goog) {
        this.goog = goog;
    }

    public String getEntitySearchResult() {
        return entitySearchResult;
    }

    public void setEntitySearchResult(String entitySearchResult) {
        this.entitySearchResult = entitySearchResult;
    }

    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public String getResultScore() {
        return resultScore;
    }

    public void setResultScore(String resultScore) {
        this.resultScore = resultScore;
    }

    public String getKg() {
        return kg;
    }

    public void setKg(String kg) {
        this.kg = kg;
    }

}
