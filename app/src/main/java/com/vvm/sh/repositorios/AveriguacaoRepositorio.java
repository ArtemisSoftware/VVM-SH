package com.vvm.sh.repositorios;

import com.vvm.sh.baseDados.dao.AveriguacaoDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.entidades.RelatorioAveriguacaoResultado;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.modelos.Averiguacao;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.modelos.AveriguacaoRegisto;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public class AveriguacaoRepositorio implements Repositorio<RelatorioAveriguacaoResultado>{

    private final AveriguacaoDao averiguacaoDao;
    public final ResultadoDao resultadoDao;

    public AveriguacaoRepositorio(AveriguacaoDao averiguacaoDao, ResultadoDao resultadoDao) {
        this.averiguacaoDao = averiguacaoDao;
        this.resultadoDao = resultadoDao;
    }


    public Observable<List<Averiguacao>> obterRelatorio(int idTarefa, int tipo){
        return averiguacaoDao.obterRelatorio(idTarefa, tipo);

    }

    public Observable<List<AveriguacaoRegisto>> obterRegistos(int idRelatorio){
        return averiguacaoDao.obterRegistos(idRelatorio);
    }

    public Single<AveriguacaoRegisto> obterRegisto(int idRegisto){
        return averiguacaoDao.obterRegisto(idRegisto);
    }


    @Override
    public Single<Long> inserir(RelatorioAveriguacaoResultado item) {
        return averiguacaoDao.inserir(item);
    }

    @Override
    public Single<Integer> atualizar(RelatorioAveriguacaoResultado item) {
        return averiguacaoDao.atualizar(item);
    }

    @Override
    public Single<Integer> remover(RelatorioAveriguacaoResultado item) {
        return null;
    }
}