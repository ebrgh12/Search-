
package com.assignment.search.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemListElement {

    @SerializedName("@type")
    @Expose
    private String type;
    @SerializedName("result")
    @Expose
    private Result result;
    @SerializedName("resultScore")
    @Expose
    private Double resultScore;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Double getResultScore() {
        return resultScore;
    }

    public void setResultScore(Double resultScore) {
        this.resultScore = resultScore;
    }

}
