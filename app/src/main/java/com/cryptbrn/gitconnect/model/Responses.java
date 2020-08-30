package com.cryptbrn.gitconnect.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Responses {

    @SerializedName("total_count")
    private int totalCount;

    @SerializedName("incomplete_results")
    private boolean incompleteResults;

    @SerializedName("items")
    private List<User> items;

    public void setTotalCount(int totalCount){
        this.totalCount = totalCount;
    }

    public int getTotalCount(){
        return totalCount;
    }

    public void setIncompleteResults(boolean incompleteResults){
        this.incompleteResults = incompleteResults;
    }

    public boolean isIncompleteResults(){
        return incompleteResults;
    }

    public void setItems(List<User> items){
        this.items = items;
    }

    public List<User> getItems(){
        return items;
    }


}