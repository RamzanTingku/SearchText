package com.example.ramzan.searchtext.mvp;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.ramzan.searchtext.MainActivity;
import com.example.ramzan.searchtext.SearchTextAPI;
import com.example.ramzan.searchtext.SearchTextAdapter;
import com.example.ramzan.searchtext.dataModel.Datum;
import com.example.ramzan.searchtext.dataModel.Search;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Model {

    ModelInterface modelInterface;
    Context context;

    SearchTextAdapter searchTextAdapter;
    ArrayList<Datum>datumArrayList;


    private final String BASE_URL = "http://182.160.109.132/";
    private SearchTextAPI searchTextAPI;

    Model(ModelInterface modelInterface, Context context) {

        this.context=context;
        this.modelInterface=modelInterface;

    }

    // TODO: get mathod
    public ArrayList<Datum> getData() {

        Retrofit retrofit =  new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        searchTextAPI = retrofit.create(SearchTextAPI.class);

        Call<ArrayList<Search>> arrayListCall = searchTextAPI.getResponse();
        arrayListCall.enqueue(new Callback<ArrayList<Search>>() {
            @Override
            public void onResponse(Call<ArrayList<Search>> call, Response<ArrayList<Search>> response) {

                Log.d("Test", ""+response.body());

                if(response.code() == 200){

                    ArrayList<Search> searches = response.body();
                    for (Search search: searches) {
                        Datum datum = (Datum) search.getData();
                        datumArrayList.add(datum);

                    }
                    searchTextAdapter.notifyDataSetChanged();


                    Toast.makeText(context, ""+searches.get(0).getData().get(0).getKey(), Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Search>> call, Throwable t) {

                Toast.makeText(context, "failed response", Toast.LENGTH_SHORT).show();

            }
        });


        return datumArrayList;

    }


    // TODO: save mathod
    void saveSData(Search search) {

        boolean success = true;

        if(success){

            modelInterface.onStudentsDataSaveSuccess("Students data saved");

        }

        else {

            modelInterface.onStudentDataSaveFailed("Students data save failed");

        }

    }

}
