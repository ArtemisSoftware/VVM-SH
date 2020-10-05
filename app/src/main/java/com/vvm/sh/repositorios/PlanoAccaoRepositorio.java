package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.PlanoAccaoDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.entidades.PlanoAccaoResultado;
import com.vvm.sh.ui.planoAccao.modelo.AtividadeRegisto;
import com.vvm.sh.ui.planoAccao.modelo.Plano;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

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

    public Single<Plano> obterPlano(int idTarefa){
        return planoAccaoDao.obterPlano(idTarefa);
    }

    public Single<Long> inserir(PlanoAccaoResultado registo){
        return planoAccaoDao.inserir(registo);
    }


    public Single<Integer> atualizar(PlanoAccaoResultado registo){
        return planoAccaoDao.atualizar(registo);
    }
}
