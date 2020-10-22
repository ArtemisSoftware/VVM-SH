package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.ImagemDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.entidades.ImagemResultado;

import java.util.List;

import io.reactivex.Observable;

public class ImagemRepositorio {


    private final ImagemDao imagemDao;
    public final ResultadoDao resultadoDao;

    public ImagemRepositorio(@NonNull ImagemDao imagemDao, @NonNull ResultadoDao resultadoDao) {

        this.imagemDao = imagemDao;
        this.resultadoDao = resultadoDao;
    }

    public Observable<List<ImagemResultado>> obterImagens() {
        return imagemDao.obterImagem();
    }
}
