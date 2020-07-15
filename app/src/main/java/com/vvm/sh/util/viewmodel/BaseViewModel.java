package com.vvm.sh.util.viewmodel;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel extends ViewModel {


    protected final CompositeDisposable disposables;
    //protected MutableLiveData<Resource> messageLiveData;


    public MutableLiveData<Integer> loading;

    public BaseViewModel() {

        this.disposables = new CompositeDisposable();
        //messageLiveData = new MutableLiveData<>();
        loading = new MutableLiveData<>();

        showProgressBar(false);
    }



//    public MutableLiveData<Resource> observeMessages(){
//        return messageLiveData;
//    }


    protected void showProgressBar(boolean visible) {
        loading.setValue(visible ? View.VISIBLE : View.INVISIBLE);
    }



    @Override
    protected void onCleared() {
        disposables.clear();
    }

}

