package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.AtividadePendenteDao;
import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendente;

import java.util.List;

import io.reactivex.Flowable;

public class AtividadePendenteRepositorio {

    private final AtividadePendenteDao atividadePendenteDao;

    public AtividadePendenteRepositorio(@NonNull AtividadePendenteDao atividadePendenteDao) {
        this.atividadePendenteDao = atividadePendenteDao;
    }


    public Flowable<List<AtividadePendente>> obterAtividades(int idTarefa) {
        return atividadePendenteDao.obterAtividades(idTarefa);
    }
}
