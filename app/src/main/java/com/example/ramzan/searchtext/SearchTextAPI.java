package com.example.ramzan.searchtext;

import com.example.ramzan.searchtext.dataModel.Search;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SearchTextAPI {

    @GET("orko_s2/public/api/hcp/problem/criteria")
    Call<ArrayList<Search>> getResponse();
}

