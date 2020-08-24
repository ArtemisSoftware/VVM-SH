package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.modelos.TipoResposta;
import com.vvm.sh.baseDados.AtualizacaoDao;
import com.vvm.sh.baseDados.TipoDao;
import com.vvm.sh.ui.contaUtilizador.Colecao;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.Tipo;

import java.util.List;

import io.reactivex.Flowable;
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


    public Flowable<List<Colecao>> obterTipos() {
        return tipoDao.obterTipos();
    }

    public Flowable<List<Atualizacao>> obterAtualizacoes() {
        return atualizacaoDao.obterAtualizacoes();
    }


    /**
     * Metodo que permite obter um tipo a partir do web service
     * @param metodo o nome do metodo associado ao tipo
     * @return os dados de um tipo
     */
    public Single<TipoResposta> obterTipo(String metodo) {
        return api.obterTipo(metodo);
    }


    /**
     * Metodo que permite obter um tipo a partir do web service
     * @param metodo o nome do metodo associado ao tipo
     * @param seloTemporal o selo temporal do ultimo pedido (ex: 2020-08-06 11:34:21.696)
     * @return os dados de um tipo
     */
    public Single<TipoResposta> obterTipo(String metodo, String seloTemporal) {
        return api.obterTipo(metodo, seloTemporal);
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
        atualizacaoDao.inserirRegisto(atualizacao);
        tipoDao.inserir(dadosNovos);
        tipoDao.atualizar(dadosAlteradaos);
    }

}
