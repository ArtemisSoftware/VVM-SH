package com.vvm.sh.util.viewmodel;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.vvm.sh.util.Recurso;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel extends ViewModel {


    protected final CompositeDisposable disposables;
    protected MutableLiveData<Recurso> messagemLiveData;


    public MutableLiveData<Integer> loading;

    public BaseViewModel() {

        this.disposables = new CompositeDisposable();
        messagemLiveData = new MutableLiveData<>();
        loading = new MutableLiveData<>();

        showProgressBar(false);
    }



    public MutableLiveData<Recurso> observarMessagem(){
        return messagemLiveData;
    }


    protected void showProgressBar(boolean visible) {
        loading.setValue(visible ? View.VISIBLE : View.INVISIBLE);
    }



    @Override
    protected void onCleared() {
        disposables.clear();
    }

}

