package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.modelos.pedido.ITipoListagem;
import com.vvm.sh.baseDados.dao.AtualizacaoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.ui.opcoes.modelos.ResumoTipo;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public class TiposRepositorio {



    private final SegurancaAlimentarApi api;
    private final AtualizacaoDao atualizacaoDao;
    private final TipoDao tipoDao;

    public TiposRepositorio(@NonNull SegurancaAlimentarApi api, @NonNull AtualizacaoDao atualizacaoDao, @NonNull TipoDao tipoDao) {
        this.api = api;
        this.atualizacaoDao = atualizacaoDao;
        this.tipoDao = tipoDao;
    }


    /**
     * Metodo que permite obter o resumo dos tipos
     * @return uma lista
     */
    public Flowable<List<ResumoTipo>> obterResumoTipos() {
        return tipoDao.obterResumoTipos();
    }




    /**
     * Metodo que permite obter um tipo a partir do web service
     * @param metodo o nome do metodo associado ao tipo
     * @return os dados de um tipo
     */
    public Single<ITipoListagem> obterTipo(String metodo) {
        return api.obterTipo(SegurancaAlimentarApi.HEADER_TIPO, metodo);
    }


    /**
     * Metodo que permite obter um tipo a partir do web service
     * @param metodo o nome do metodo associado ao tipo
     * @param seloTemporal o selo temporal do ultimo pedido (ex: 2020-08-06 11:34:21.696)
     * @return os dados de um tipo
     */
    public Single<ITipoListagem> obterTipo(String metodo, String seloTemporal) {
        return api.obterTipo(SegurancaAlimentarApi.HEADER_TIPO, metodo, seloTemporal);
    }



    /**
     * Metodo que permite obter as atualizacoes
     * @return uma lista de atualizacoes
     */
    public Maybe<List<Atualizacao>> obterAtualizacoes() {
        return atualizacaoDao.obterAtualizacoes();
    }


    public Flowable<List<Tipo>> obterTipos(String metodo, int api) {
        return tipoDao.obterTipos(metodo, api);
    }


    /**
     * Metodo que permite obter uma lista de tipo excluido determinados registos
     * @param metodo o nome do metodo associado ao tipo
     * @param registos os registos a excluir
     * @param api o identificador da api
     * @return uma lista de registos
     */
    public Flowable<List<Tipo>> obterTipos_Excluir(String metodo, List<Integer> registos, int api) {
        return tipoDao.obterTipos_Excluir(metodo, registos, api);
    }


    /**
     * Metodo que permite obter uma lista de tipos apenas com base em registos especificos
     * @param metodo o nome do metodo associado ao tipo
     * @param registos os registos a incluir
     * @param api o identificador da api
     * @return uma lista de registos
     */
    public Flowable<List<Tipo>> obterTipos_Incluir(String metodo, List<Integer> registos, int api) {
        return tipoDao.obterTipos_Incluir(metodo, registos, api);
    }



    /**
     * Metodo que permite atualizar um tipo<br>
     *     1->Remover a atualizacao e os dados<br>
     *     2->Inserir novo timestamp<br>
     *     3->inserir novos dados
     * @param atualizacao os dados da atualizacao
     * @param tipos os dados do tipo
     */
    public void atualizarTipo(Atualizacao atualizacao, List<Tipo> tipos){
        atualizacaoDao.remover(atualizacao.descricao);
        atualizacaoDao.inserirRegisto(atualizacao);
        tipoDao.inserir(tipos);
    }


    /**
     * Metodo que permite carregar um tipo<br>
     *     1->Remover a atualizacao e os dados<br>
     *     2->Inserir novo timestamp<br>
     *     3->inserir novos dados
     * @param atualizacao os dados da atualizacao
     * @param dadosNovos os dados a inserir
     * @param dadosAlteradaos os dados a alterar
     */
    public void carregarTipo(Atualizacao atualizacao, List<Tipo> dadosNovos, List<Tipo> dadosAlteradaos){


        if(dadosNovos.size() == 0){
            atualizacaoDao.atualizarRegisto(atualizacao);
        }
        else if(dadosNovos.size() != 0 & dadosAlteradaos.size() == 0){
            atualizacaoDao.inserirRegisto(atualizacao);
        }
        else{
            atualizacaoDao.atualizarRegisto(atualizacao);
        }

        tipoDao.inserir(dadosNovos);
        tipoDao.atualizar(dadosAlteradaos);
    }

}
