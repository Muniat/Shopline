package com.example.shopline.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shopline.Model.TitleModel;

import java.util.ArrayList;

public class TitleViewModel extends ViewModel {
    MutableLiveData<ArrayList<TitleModel>> titles;

    public void init(Context context){
        if(titles != null){
            return;
        }
    }

    public LiveData<ArrayList<TitleModel>> getTitles(){
        return titles;
    }
}
