package com.example.ramzan.searchtext.mvp;

import java.util.List;
import com.example.ramzan.searchtext.dataModel.Search;

public interface ModelInterface {

    void onStudentsDataLoadSuccess(List<Search> students);
    void onStudentsDataSaveSuccess(String message);
    void onStudentDataLoadFailed(String message);
    void onStudentDataSaveFailed(String message);

}
