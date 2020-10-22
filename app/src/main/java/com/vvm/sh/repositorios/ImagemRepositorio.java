package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.ImagemDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;

public class ImagemRepositorio {


    private final ImagemDao imagemDao;
    public final ResultadoDao resultadoDao;

    public ImagemRepositorio(@NonNull ImagemDao imagemDao, @NonNull ResultadoDao resultadoDao) {

        this.imagemDao = imagemDao;
        this.resultadoDao = resultadoDao;
    }
}
