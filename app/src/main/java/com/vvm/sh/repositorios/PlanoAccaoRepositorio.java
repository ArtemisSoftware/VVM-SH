package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.PlanoAccaoDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.ui.planoAccao.modelo.AtividadeRegisto;

import java.util.List;

import io.reactivex.Observable;

public class PlanoAccaoRepositorio {


    public final PlanoAccaoDao planoAccaoDao;
    public final ResultadoDao resultadoDao;


    public PlanoAccaoRepositorio(@NonNull PlanoAccaoDao planoAccaoDao,
                                 @NonNull ResultadoDao resultadoDao) {

        this.planoAccaoDao = planoAccaoDao;
        this.resultadoDao = resultadoDao;
    }


    public Observable<List<AtividadeRegisto>> obterAtividades(int idTarefa){
        return planoAccaoDao.obterAtividades(idTarefa);
    }

}
