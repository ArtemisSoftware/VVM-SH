package com.vvm.sh.ui.imagens;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.repositorios.AtividadePendenteRepositorio;
import com.vvm.sh.repositorios.ImagemRepositorio;
import com.vvm.sh.util.metodos.DiretoriasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ImagemViewModel extends BaseViewModel {

    private final ImagemRepositorio imagemRepositorio;

    public MutableLiveData<List<Tipo>> galerias;

    @Inject
    public ImagemViewModel(ImagemRepositorio imagemRepositorio){

        this.imagemRepositorio = imagemRepositorio;
        galerias = new MutableLiveData<>();
    }



    //---------------
    //Misc
    //---------------


    public void obterDiretoriasImagens(){

        List<String> nomeDiretorias = new ArrayList<>();
        List<Tipo> diretorias = new ArrayList<>();
//        diretorias.add(new Tipo(1, "Fotos", DiretoriasUtil.obterCaminho("Pictures")));
//


        String picturesDir = DiretoriasUtil.obterCaminho("Pictures");
        String cameraDir = DiretoriasUtil.obterCaminho("DCIM" + File.separator + "Camera");

        if (DiretoriasUtil.getDirectoryPaths(picturesDir) != null) {
            nomeDiretorias = DiretoriasUtil.getDirectoryPaths(picturesDir);
        }

        diretorias.add(new Tipo(1, "Camera", cameraDir));

        for (int i = 0; i < nomeDiretorias.size(); i++) {

            int index = nomeDiretorias.get(i).lastIndexOf("/");
            String string = nomeDiretorias.get(i).substring(index);

            diretorias.add(new Tipo((1 + index), string, nomeDiretorias.get(i)));
        }

        galerias.setValue(diretorias);

    }

}
