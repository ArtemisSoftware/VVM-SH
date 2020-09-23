package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.SegurancaTrabalhoApi;
import com.vvm.sh.api.modelos.pedido.ITipoChecklist;
import com.vvm.sh.api.modelos.pedido.ITipoListagem;
import com.vvm.sh.baseDados.dao.AtualizacaoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.entidades.AreaChecklist;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.CheckList;
import com.vvm.sh.baseDados.entidades.ItemChecklist;
import com.vvm.sh.baseDados.entidades.SeccaoChecklist;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.ui.opcoes.modelos.ResumoTipo;
import com.vvm.sh.util.excepcoes.TipoInexistenteException;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public class TiposRepositorio {



    private final SegurancaAlimentarApi apiSA;
    private final SegurancaTrabalhoApi apiST;
    private final AtualizacaoDao atualizacaoDao;
    private final TipoDao tipoDao;

    public TiposRepositorio(@NonNull SegurancaAlimentarApi apiSA, @NonNull SegurancaTrabalhoApi apiST, @NonNull AtualizacaoDao atualizacaoDao, @NonNull TipoDao tipoDao) {
        this.apiSA = apiSA;
        this.apiST = apiST;
        this.atualizacaoDao = atualizacaoDao;
        this.tipoDao = tipoDao;
    }


    /**
     * Metodo que permite obter o resumo dos tipos
     * @return uma lista
     */
    public Observable<List<ResumoTipo>> obterResumoTipos() {
        return tipoDao.obterResumoTipos();
    }


    /**
     * Metodo que permite obter um tipo a partir do web service
     * @param descricao o metodo associado ao tipo
     * @return os dados de um tipo
     */
    public Observable<ITipoListagem> obterTipo(String descricao) throws TipoInexistenteException {

        TiposUtil.MetodoApi metodo = TiposUtil.obterMetodos(descricao);


        if(metodo.sa != null & metodo.sht != null) {
            return Observable.concat(apiSA.obterTipo(SegurancaAlimentarApi.HEADER_TIPO, metodo.sa).toObservable(), apiST.obterTipo(SegurancaTrabalhoApi.HEADER_TIPO, metodo.sht).toObservable());
        }
        else if(metodo.sa != null){
            return apiSA.obterTipo(SegurancaAlimentarApi.HEADER_TIPO, metodo.sa).toObservable();
        }

        else{
            return apiST.obterTipo(SegurancaTrabalhoApi.HEADER_TIPO, metodo.sht).toObservable();
        }
    }


    /**
     * Metodo que permite obter todos os tipos
     * @return os dados de todos os tipos
     */
    public Observable<ITipoListagem> obterTipos() throws TipoInexistenteException {

        List<Observable<ITipoListagem>> pedidos = new ArrayList<>();

        for(TiposUtil.MetodoApi metodo : TiposUtil.obterMetodos()) {

            if(metodo.sa != null){
                pedidos.add(apiSA.obterTipo(SegurancaAlimentarApi.HEADER_TIPO, metodo.sa).toObservable());
            }

            if(metodo.sht != null){
                pedidos.add(apiST.obterTipo(SegurancaTrabalhoApi.HEADER_TIPO, metodo.sht).toObservable());
            }
        }

        return Observable.concat(pedidos);
    }



    /**
     * Metodo que permite obter os tipos com o seu selo temporal
     * @param atualizacoes as atualizacoes dos tipos
     * @return os dados dos tipos
     */
    public Observable<ITipoListagem> obterTipos(List<Atualizacao> atualizacoes) throws TipoInexistenteException {

        List<Observable<ITipoListagem>> pedidos = new ArrayList<>();

        for(Atualizacao atualizacao : atualizacoes){

            TiposUtil.MetodoApi metodo = TiposUtil.obterMetodos(atualizacao.descricao);

            if(metodo.sa != null){
                pedidos.add(apiSA.obterTipo(SegurancaAlimentarApi.HEADER_TIPO, metodo.sa, atualizacao.seloTemporal).toObservable());
            }

            if(metodo.sa != null){
                pedidos.add(apiST.obterTipo(SegurancaTrabalhoApi.HEADER_TIPO, metodo.sht, atualizacao.seloTemporal).toObservable());
            }
        }

        return Observable.concat(pedidos);
    }



    public Observable<ITipoChecklist> obterChecklists(List<Integer> ids) throws TipoInexistenteException {

        List<Observable<ITipoChecklist>> pedidos = new ArrayList<>();

        for (int id: ids) {
            pedidos.add(apiST.obterChecklist(SegurancaTrabalhoApi.HEADER_TIPO, ids +"").toObservable());
        }


        return Observable.concat(pedidos);
    }






    /**
     * Metodo que permite obter as atualizacoes
     * @return uma lista de atualizacoes
     */
    public Maybe<List<Atualizacao>> obterAtualizacoes() {
        return atualizacaoDao.obterAtualizacoes();
    }



    /**
     * Metodo que permite obter as atualizacoes
     * @return uma lista de atualizacoes
     */
    public Maybe<List<Integer>> obterIdChecklists() {
        return tipoDao.obterChecklists(2);
    }



    public Flowable<List<Tipo>> obterTipos(String metodo, int api) {
        return tipoDao.obterTipos(metodo, api);
    }


    /**
     * Metodo que permite obter uma lista de tipo excluido determinados registos
     * @param metodo o nome do metodo associado ao tipo
     * @param registos os registos a excluir
     * @param api o identificador da apiSA
     * @return uma lista de registos
     */
    public Flowable<List<Tipo>> obterTipos_Excluir(String metodo, List<Integer> registos, int api) {
        return tipoDao.obterTipos_Excluir(metodo, registos, api);
    }

    public Maybe<List<Tipo>> obterTipos_Excluir(String metodo, List<Integer> registos, String pesquisa, int api) {
        return tipoDao.obterTipos_Excluir(metodo, registos, pesquisa, api);
    }


    /**
     * Metodo que permite obter uma lista de tipos apenas com base em registos especificos
     * @param metodo o nome do metodo associado ao tipo
     * @param registos os registos a incluir
     * @param api o identificador da apiSA
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


    public void inserirChecklist(Atualizacao atualizacao, List<Tipo> tipos){
        atualizacaoDao.remover(atualizacao.descricao);
        atualizacaoDao.inserirRegisto(atualizacao);
        tipoDao.inserir(tipos);
    }

    /**
     * Metodo que permite inserir uma checklist
     * @param checkList
     * @param areas
     * @param seccoes
     * @param itens
     */
    public void inserirChecklist(CheckList checkList, List<AreaChecklist> areas, List<SeccaoChecklist> seccoes, List<ItemChecklist> itens) {


        tipoDao.remover(checkList);


        tipoDao.inserir(checkList);
        tipoDao.inserirAreasChecklist(areas);
        tipoDao.inserirSeccoesChecklis(seccoes);
        tipoDao.inserirItensChecklis(itens);

    }
}
