package com.vvm.sh.ui.imagens;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.repositorios.AtividadePendenteRepositorio;
import com.vvm.sh.repositorios.ImagemRepositorio;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class ImagemViewModel extends BaseViewModel {

    private final ImagemRepositorio imagemRepositorio;

    @Inject
    public ImagemViewModel(ImagemRepositorio imagemRepositorio){

        this.imagemRepositorio = imagemRepositorio;
    }



    //---------------
    //Misc
    //---------------


    public void obterDiretoriasImagens(){

    }

}
