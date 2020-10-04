package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.PropostaPlanoAcaoDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.entidades.PropostaPlanoAcaoResultado;
import com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao.modelos.Proposta;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public class PropostaPlanoAcaoRepositorio {

    public final PropostaPlanoAcaoDao propostaPlanoAcaoDao;
    public final ResultadoDao resultadoDao;


    public PropostaPlanoAcaoRepositorio(PropostaPlanoAcaoDao propostaPlanoAcaoDao,
                                        @NonNull ResultadoDao resultadoDao) {

        this.propostaPlanoAcaoDao = propostaPlanoAcaoDao;
        this.resultadoDao = resultadoDao;
    }


    public Observable<List<Proposta>> obterCondicoesSt(int idAtividade){
        return propostaPlanoAcaoDao.obterPropostasSt(idAtividade);
    }


    public Observable<List<Proposta>> obterMedidasAvaliacao(int idAtividade){
        return propostaPlanoAcaoDao.obterMedidasAvaliacao(idAtividade);
    }


    public Single<Long> inserir(PropostaPlanoAcaoResultado registo){
        return propostaPlanoAcaoDao.inserir(registo);
    }

    public Single<Integer> atualizar(PropostaPlanoAcaoResultado registo){
        return propostaPlanoAcaoDao.atualizar(registo);
    }

    public Completable selecionarTudo(int idAtividade, boolean selecionado){
        return propostaPlanoAcaoDao.selecionarTudo(idAtividade, selecionado);
    }
}
