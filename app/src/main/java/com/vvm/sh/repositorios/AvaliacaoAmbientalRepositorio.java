package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.AvaliacaoAmbientalDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.entidades.AvaliacaoAmbientalResultado;
import com.vvm.sh.baseDados.entidades.RelatorioAmbientalResultado;
import com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.AvaliacaoAmbiental;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public class AvaliacaoAmbientalRepositorio {

    private final AvaliacaoAmbientalDao avaliacaoAmbientalDao;
    private final TipoDao tipoDao;
    public final ResultadoDao resultadoDao;

    public AvaliacaoAmbientalRepositorio(@NonNull AvaliacaoAmbientalDao avaliacaoAmbientalDao,
                                         @NonNull TipoDao tipoDao, @NonNull ResultadoDao resultadoDao) {

        this.avaliacaoAmbientalDao = avaliacaoAmbientalDao;
        this.tipoDao = tipoDao;
        this.resultadoDao = resultadoDao;
    }

    /**
     * Metodo que permite inserir um registo
     * @param registo os dados a inserir
     * @return o resultado da insercao
     */
    public Single<Long> inserir(RelatorioAmbientalResultado registo){
        return avaliacaoAmbientalDao.inserir(registo);
    }

    /**
     * Metodo que permite atualizar um registo
     * @param registo os dados a inserir
     * @return o resultado da insercao
     */
    public Single<Integer> atualizar(RelatorioAmbientalResultado registo){
        return avaliacaoAmbientalDao.atualizar(registo);
    }


    /**
     * Metodo que permite obter os dados gerais do relatorio
     * @param idAtividade o identificador da atividade
     * @param tipo o tipo de relatorio
     * @return os dados
     */
    public Maybe<RelatorioAmbientalResultado> obterGeral(int idAtividade, int tipo) {
        return avaliacaoAmbientalDao.obterGeral(idAtividade, tipo);
    }

    /**
     * Metodo que permite obter a medida recomendada
     * @param idAtividade o identificador da atividade
     * @param tipo o tipo de relatorio
     * @return a medida
     */
    public Observable<String> obterMedidaRecomendada(int idAtividade, int tipo) {

        if(tipo == Identificadores.Relatorios.ID_RELATORIO_ILUMINACAO) {

            return avaliacaoAmbientalDao.obterMedidaRecomendadaIluminacao(idAtividade, tipo);
        }
        else{
            return avaliacaoAmbientalDao.obterMedidaRecomendadaTermico(idAtividade, tipo);
        }
    }



    /**
     * Metodo que permite obter as avaliacoes
     * @param idRelatorio o identificador do relatorio
     * @param tipo o tipo de relatorio
     * @return os registos
     */
    public Observable<List<AvaliacaoAmbiental>> obterAvaliacoes(int idRelatorio, int tipo) {

        if(tipo == Identificadores.Relatorios.ID_RELATORIO_ILUMINACAO) {

            return avaliacaoAmbientalDao.obterAvaliacoesIluminacao(idRelatorio);
        }
        else{
            return avaliacaoAmbientalDao.obterAvaliacoesTemperaturaHumidade(idRelatorio);
        }
    }



    public Maybe<Boolean> obterValidadeAvaliacoes(int idAtividade, int tipo) {

        if(tipo == Identificadores.Relatorios.ID_RELATORIO_ILUMINACAO) {

            return avaliacaoAmbientalDao.obterValidadeAvaliacoesIluminacao(idAtividade, tipo);
        }
        else{
            return avaliacaoAmbientalDao.obterValidadeAvaliacoesTemperaturaHumidade(idAtividade, tipo);
        }
    }


    /**
     * Metodo que permite obter as avaliacoes
     * @param idRelatorio o identificador do relatorio
     * @return uma lista de registos
     */
    public Observable<List<AvaliacaoAmbientalResultado>> obterAvaliacoes(int idRelatorio) {
        return avaliacaoAmbientalDao.obterAvaliacoes(idRelatorio);
    }






    /**
     * Metodo que permite obter uma avaliacao
     * @param id o identificador da avaliacao
     * @return um registo
     */
    public Maybe<AvaliacaoAmbientalResultado> obterAvaliacao(int id) {
        return avaliacaoAmbientalDao.obterAvaliacao(id);
    }



    /**
     * Metodo que permite obter o identificador do relatorio
     * @param idAtividade o identificador da atividade
     * @param tipo o tipo de relatorio
     * @return o identificador
     */
    public Maybe<Integer> obterIdRelatorio(int idAtividade, int tipo) {
        return avaliacaoAmbientalDao.obterIdRelatorio(idAtividade, tipo);
    }


    /**
     * Metodo que indica a validade dos dados gerais
     * @param idAtividade o identificador da atividade
     * @param tipo o tipo de relatorio
     * @return true caso seja valido ou false caso contrario
     */
    public Maybe<Boolean> obterValidadeGeral(int idAtividade, int tipo) {
        return avaliacaoAmbientalDao.obterValidadeGeral(idAtividade, tipo);
    }
}
