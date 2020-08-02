package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.FormandoDao;
import com.vvm.sh.ui.atividadesPendentes.relatorios.Formando;

import java.util.List;

import io.reactivex.Flowable;

public class FormacaoRepositorio {

    private final FormandoDao formandoDao;

    public FormacaoRepositorio(@NonNull FormandoDao formandoDao) {
        this.formandoDao = formandoDao;
    }


    public Flowable<List<Formando>> obterFormandos(int idAtividade) {
        return formandoDao.obterFormandos(idAtividade);
    }


}
