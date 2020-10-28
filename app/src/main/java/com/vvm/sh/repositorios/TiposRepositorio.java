package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.SegurancaTrabalhoApi;
import com.vvm.sh.api.modelos.pedido.ITipoAtividadePlaneavelListagem;
import com.vvm.sh.api.modelos.pedido.ITipoChecklist;
import com.vvm.sh.api.modelos.pedido.ITipoListagem;
import com.vvm.sh.api.modelos.pedido.ITipoTemplateAvrLevantamentoListagem;
import com.vvm.sh.api.modelos.pedido.ITipoTemplateAvrRiscoListagem;
import com.vvm.sh.baseDados.dao.AtualizacaoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.entidades.AreaChecklist;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.CheckList;
import com.vvm.sh.baseDados.entidades.ItemChecklist;
import com.vvm.sh.baseDados.entidades.SeccaoChecklist;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.baseDados.entidades.TipoAtividadePlaneavel;
import com.vvm.sh.baseDados.entidades.TipoTemplateAvrLevantamento;
import com.vvm.sh.baseDados.entidades.TipoTemplateAvrRisco;
import com.vvm.sh.baseDados.entidades.TipoTemplatesAVRMedidaRisco;
import com.vvm.sh.ui.opcoes.modelos.ResumoChecklist;
import com.vvm.sh.ui.opcoes.modelos.ResumoTipo;
import com.vvm.sh.ui.opcoes.modelos.TemplateAvr;
import com.vvm.sh.ui.pesquisa.modelos.Medida;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.excepcoes.TipoInexistenteException;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;

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


    //------------
    //tipos
    //------------



    /**
     * Metodo que permite obter o resumo dos tipos
     * @return uma lista
     */
    public Observable<List<ResumoTipo>> obterResumoTipos() {
        return tipoDao.obterResumoTipos();
    }



    //------------
    //Checklist
    //------------

    /**
     * Metodo que permite obter o resumo das checklists
     * @return uma lista
     */
    public Observable<List<ResumoChecklist>> obterResumoChecklist() {
        return tipoDao.obterResumoChecklist();
    }


    /**
     * Metodo que permite obter as checklists do web service
     * @param ids os identificadores das checklists
     * @return
     * @throws TipoInexistenteException
     */
    public Observable<ITipoChecklist> obterChecklists(List<Integer> ids) throws TipoInexistenteException {

        List<Observable<ITipoChecklist>> pedidos = new ArrayList<>();

        for (int id: ids) {
            pedidos.add(apiST.obterChecklist(SegurancaTrabalhoApi.HEADER_TIPO, id +"").toObservable());
        }

        return Observable.concat(pedidos);
    }



    //------------
    //Atividades planeaveis
    //------------

    /**
     * Metodo que permite obter o resumo das atividades planeaveis
     * @return uma lista
     */
    public Observable<List<ResumoTipo>> obterResumoAtividadesPlaneaveis() {
        return tipoDao.obterResumoAtividadesPlaneaveis();
    }


    public Single<ITipoAtividadePlaneavelListagem> obterAtividadesPlaneaveis() throws TipoInexistenteException {
        return apiST.obterTipoAtividadesPlaneaveis(SegurancaTrabalhoApi.HEADER_TIPO);
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


    public Observable<ITipoListagem> obterTipo(TiposUtil.MetodoApi metodo) {

        if(metodo.sa != null & metodo.sht != null) {
            return Observable.concat(
                    apiSA.obterTipo(SegurancaAlimentarApi.HEADER_TIPO, metodo.sa, metodo.seloTemporal).toObservable(),
                    apiST.obterTipo(SegurancaTrabalhoApi.HEADER_TIPO, metodo.sht, metodo.seloTemporal).toObservable()
            );
        }
        else if(metodo.sa != null){
            return apiSA.obterTipo(SegurancaAlimentarApi.HEADER_TIPO, metodo.sa, metodo.seloTemporal).toObservable();
        }
        else{
            return apiST.obterTipo(SegurancaTrabalhoApi.HEADER_TIPO, metodo.sht, metodo.seloTemporal).toObservable();
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





    public Single<TemplateAvr> obterTemplateAvr() throws TipoInexistenteException {

        Single<TemplateAvr> single = Single.zip(
                apiST.obterTipoTemplatesAVR_Levantamentos(SegurancaTrabalhoApi.HEADER_TIPO),
                apiST.obterTipoTemplatesAVR_Riscos(SegurancaTrabalhoApi.HEADER_TIPO),
                new BiFunction<ITipoTemplateAvrLevantamentoListagem, ITipoTemplateAvrRiscoListagem, TemplateAvr>() {
                    @Override
                    public TemplateAvr apply(ITipoTemplateAvrLevantamentoListagem levantamentos, ITipoTemplateAvrRiscoListagem riscos) throws Exception {

                        TemplateAvr resultado = new TemplateAvr();
                        resultado.levantamentos = levantamentos;
                        resultado.riscos = riscos;

                        return resultado;
                    }
                });


        return single;
    }





    /**
     * Metodo que permite obter as atualizacoes
     * @return uma lista de atualizacoes
     */
    public Maybe<List<Atualizacao>> obterAtualizacoes(int tipo) {
        return atualizacaoDao.obterAtualizacoes(tipo);
    }



    /**
     * Metodo que permite obter as atualizacoes
     * @return uma lista de atualizacoes
     */
    public Maybe<List<Integer>> obterIdChecklists() {
        return tipoDao.obterChecklists(2);
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



    public Observable<List<Medida>> obterMedidas(String metodo, String codigo, int api, List<Integer> registos) {
        return tipoDao.obterMedidas(metodo, codigo, api, registos);
    }

    public Observable<List<Medida>> obterMedidas(String metodo, int api, List<Integer> registos) {
        return tipoDao.obterMedidas(metodo, api, registos);
    }

    public Observable<List<Medida>> obterMedidas(String metodo, int api, List<Integer> registos, String idPai) {
        return tipoDao.obterMedidas(metodo, api, registos, idPai);
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

        if(atualizacaoDao.existeRegisto(atualizacao.descricao) == true){
            atualizacaoDao.atualizarRegisto(atualizacao);
        }
        else{
            atualizacaoDao.inserirRegisto(atualizacao);
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

    public void carregarTipoTemplateAvr(Atualizacao atualizacaoLevantamento, List<TipoTemplateAvrLevantamento> dadosNovosLevantamento, List<TipoTemplateAvrLevantamento> dadosAlteradosLevantamento,
                                        Atualizacao atualizacaoRisco,
                                        List<TipoTemplateAvrRisco> dadosNovosRiscos, List<TipoTemplatesAVRMedidaRisco> medidas,
                                        List<TipoTemplateAvrRisco> dadosAlteradosRiscos, List<TipoTemplatesAVRMedidaRisco> medidasAlteradas) {

        //levantamento

        if(atualizacaoDao.existeRegisto(atualizacaoLevantamento.descricao) == true){
            atualizacaoDao.atualizarRegisto(atualizacaoLevantamento);
        }
        else{
            atualizacaoDao.inserirRegisto(atualizacaoLevantamento);
        }


        tipoDao.inserirTemplateAvrLevantamento(dadosNovosLevantamento);
        tipoDao.atualizarTemplateAvrLevantamento(dadosAlteradosLevantamento);


        //riscos

        if(atualizacaoDao.existeRegisto(atualizacaoRisco.descricao) == true){
            atualizacaoDao.atualizarRegisto(atualizacaoRisco);
        }
        else{
            atualizacaoDao.inserirRegisto(atualizacaoRisco);
        }


        tipoDao.inserirTemplateAvrRisco(dadosNovosRiscos);
        tipoDao.atualizarTemplateAvrRisco(dadosAlteradosRiscos);

        /*Medidas*/

        for(TipoTemplateAvrRisco item : dadosAlteradosRiscos){

            tipoDao.removerTemplatesAVRMedidaRisco(item.id, Identificadores.Origens.MEDIDAS_RISCO_RECOMENDADAS);
            tipoDao.removerTemplatesAVRMedidaRisco(item.id, Identificadores.Origens.MEDIDAS_RISCO_EXISTENTES);
        }


        //TODO: verificar se as medidas estão ativias , depois filtrar e depois inserir

        tipoDao.inserirTemplatesAVRMedidaRisco(medidas);
        tipoDao.inserirTemplatesAVRMedidaRisco(medidasAlteradas);
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
    public void carregarAtividadesPlaneaveis(Atualizacao atualizacao, List<TipoAtividadePlaneavel> dadosNovos, List<TipoAtividadePlaneavel> dadosAlteradaos){

        atualizacaoDao.remover(atualizacao.descricao);

        atualizacaoDao.inserirRegisto(atualizacao);
        tipoDao.inserirAtividadesPlaneaiveis(dadosNovos);
        tipoDao.atualizarAtividadesPlaneaiveis(dadosAlteradaos);
    }

}
