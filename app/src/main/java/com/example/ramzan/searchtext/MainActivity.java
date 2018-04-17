package com.example.ramzan.searchtext;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import retrofit2.Call;

import com.example.ramzan.searchtext.dataModel.Datum;
import com.example.ramzan.searchtext.dataModel.Search;

import java.util.ArrayList;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SearchTextAdapter searchTextAdapter;
    ArrayList<Datum>datumArrayList;
    Context context;

    private final String BASE_URL = "http://182.160.109.132/";
    private SearchTextAPI searchTextAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;

        recyclerView = findViewById(R.id.searched_list_rv);
        datumArrayList = new ArrayList<>();

        searchTextAdapter = new SearchTextAdapter(context,datumArrayList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setReverseLayout(true);
        llm.setStackFromEnd(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(searchTextAdapter);


        Retrofit retrofit =  new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        searchTextAPI = retrofit.create(SearchTextAPI.class);


        Call<ArrayList<Search>> arrayListCall = searchTextAPI.getResponse();
        arrayListCall.enqueue(new Callback<ArrayList<Search>>() {
            @Override
            public void onResponse(Call<ArrayList<Search>> call, Response<ArrayList<Search>> response) {
                if(response.code() == 200){

                    ArrayList<Search> searches = response.body();
                    for (Search search: searches) {
                        Datum datum = (Datum) search.getData();
                        datumArrayList.add(datum);

                    }
                    searchTextAdapter.notifyDataSetChanged();

                    Toast.makeText(MainActivity.this, ""+searches.get(0).getData().get(0).getKey(), Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Search>> call, Throwable t) {

                Toast.makeText(MainActivity.this, "failed response", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
