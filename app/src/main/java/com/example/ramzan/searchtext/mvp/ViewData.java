package com.example.ramzan.searchtext.mvp;

import com.example.ramzan.searchtext.dataModel.Datum;

import java.util.List;

public interface ViewData {

    void startLoading();
    void stopLoading();
    void showError(String message);
    void showStudentList(List<Datum> studentsList);

}
