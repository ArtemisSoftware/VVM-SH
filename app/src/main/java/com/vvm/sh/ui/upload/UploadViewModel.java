package com.vvm.sh.ui.upload;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.repositorios.UploadRepositorio;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class UploadViewModel extends BaseViewModel {

    private final UploadRepositorio uploadRepositorio;


    @Inject
    public UploadViewModel(UploadRepositorio uploadRepositorio){

        this.uploadRepositorio = uploadRepositorio;

    }
}
