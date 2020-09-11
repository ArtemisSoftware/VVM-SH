package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.AvaliacaoAmbientalDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.entidades.RelatorioAmbientalResultado;

import io.reactivex.Maybe;

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
     * Metodo que permite obter os dados gerais do relatorio
     * @param idAtividade o identificador da atividade
     * @param tipo o tipo de relatorio
     * @return os dados
     */
    public Maybe<RelatorioAmbientalResultado> obterGeral(int idAtividade, int tipo) {
        return avaliacaoAmbientalDao.obterGeral(idAtividade, tipo);
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
