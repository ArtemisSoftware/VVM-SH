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
import com.vvm.sh.util.excepcoes.TipoInexistenteException;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
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



    /**
     * Metodo que permite obter todos os tipos
     * @return os dados de todos os tipos
     * @throws TipoInexistenteException
     */
    public Single<List<ITipoListagem>> obterTipos() throws TipoInexistenteException {

        List<SingleSource> tipos = new ArrayList<>();

        for(TiposUtil.MetodoApi metodo : TiposUtil.obterMetodos()) {

            if(metodo.sa != null){
                tipos.add(apiSA.obterTipo(SegurancaAlimentarApi.HEADER_TIPO, metodo.sa));
            }

            if(metodo.sht != null){
                tipos.add(apiST.obterTipo(SegurancaTrabalhoApi.HEADER_TIPO, metodo.sht));
            }
        }

        SingleSource[] source = new SingleSource[tipos.size()];

        for(int index = 0; index < tipos.size(); ++index){
            source[index] = tipos.get(index);
        }

        return Single.concatArray(source).toList();
    }


    public Single<List<ITipoListagem>> obterTipos(List<TiposUtil.MetodoApi> atualizacoes) {

        List<SingleSource> tipos = new ArrayList<>();

        for(TiposUtil.MetodoApi metodo : atualizacoes){

            if(metodo.sa != null & metodo.sht != null) {

                tipos.add(apiSA.obterTipo(SegurancaAlimentarApi.HEADER_TIPO, metodo.sa, metodo.seloTemporal));
                tipos.add(apiST.obterTipo(SegurancaTrabalhoApi.HEADER_TIPO, metodo.sht, metodo.seloTemporal));

            }
            else if(metodo.sa != null){
                tipos.add(apiSA.obterTipo(SegurancaAlimentarApi.HEADER_TIPO, metodo.sa, metodo.seloTemporal));
            }
            else{
                tipos.add(apiST.obterTipo(SegurancaTrabalhoApi.HEADER_TIPO, metodo.sht, metodo.seloTemporal));
            }
        }

        SingleSource[] source = new SingleSource[tipos.size()];

        for(int index = 0; index < tipos.size(); ++index){
            source[index] = tipos.get(index);
        }

        return Single.concatArray(source).toList();

    }



    /**
     * Metodo que permite obter um tipo a partir do web service
     * @param resumo os dados da atualizacao do tipo
     * @return os dados de um tipo
     */
    public Single<List<ITipoListagem>> obterTipo(ResumoTipo resumo) throws TipoInexistenteException {

        TiposUtil.MetodoApi metodo = TiposUtil.obterMetodos(resumo.atualizacao.descricao);
        List<SingleSource> tipos = new ArrayList<>();

        if(metodo.sa != null & metodo.sht != null) {

            tipos.add(apiSA.obterTipo(SegurancaAlimentarApi.HEADER_TIPO, metodo.sa));
            tipos.add(apiST.obterTipo(SegurancaTrabalhoApi.HEADER_TIPO, metodo.sht));

        }
        else if(metodo.sa != null){
            tipos.add(apiSA.obterTipo(SegurancaAlimentarApi.HEADER_TIPO, metodo.sa));
        }
        else{
            tipos.add(apiST.obterTipo(SegurancaTrabalhoApi.HEADER_TIPO, metodo.sht));
        }

        SingleSource[] source = new SingleSource[tipos.size()];

        for(int index = 0; index < tipos.size(); ++index){
            source[index] = tipos.get(index);
        }

        return Single.concatArray(source).toList();
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


    //------------
    //templates
    //------------

    public Observable<List<ResumoTipo>> obterResumoTemplate() {
        return tipoDao.obterResumoTemplates();
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
     * Metodo que permite atualizar um tipo<br>
     *     1->Remover a atualizacao e os dados<br>
     *     2->Inserir novo timestamp<br>
     *     3->inserir novos dados
     * @param atualizacao os dados da atualizacao
     * @param tipos os dados do tipo
     */
    public void atualizarTipo(Atualizacao atualizacao, List<Tipo> tipos){

        if(atualizacaoDao.existeRegisto(atualizacao.descricao) == true){
            atualizacaoDao.atualizarRegisto(atualizacao);
        }
        else{
            atualizacaoDao.inserirRegisto(atualizacao);
        }

//        tipoDao.inserir(tipos);
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


    public void carregarChecklist(Atualizacao atualizacao, List<Tipo> tipos){
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
    public void carregarChecklist(CheckList checkList, List<AreaChecklist> areas, List<SeccaoChecklist> seccoes, List<ItemChecklist> itens) {

        tipoDao.remover(checkList);

        tipoDao.inserir(checkList);
        tipoDao.inserirAreasChecklist(areas);
        tipoDao.inserirSeccoesChecklis(seccoes);
        tipoDao.inserirItensChecklis(itens);

    }



    public void carregarTipoTemplateAvr(Atualizacao atualizacaoLevantamento, List<TipoTemplateAvrLevantamento> dadosNovosLevantamento, List<TipoTemplateAvrLevantamento> dadosAlteradosLevantamento,
                                        Atualizacao atualizacaoRisco,
                                        List<TipoTemplateAvrRisco> dadosNovosRiscos, List<TipoTemplateAvrRisco> dadosAlteradosRiscos,
                                        List<TipoTemplatesAVRMedidaRisco> medidasExistentes, List<TipoTemplatesAVRMedidaRisco> medidasAlteradasExistentes,
                                        List<TipoTemplatesAVRMedidaRisco> medidasRecomendadas, List<TipoTemplatesAVRMedidaRisco> medidasAlteradasRecomendadas) {


        atualizacaoDao.remover(atualizacaoRisco.descricao);
        atualizacaoDao.remover(atualizacaoLevantamento.descricao);

        tipoDao.removerTemplatesAVRMedidaRisco();
        tipoDao.removerTemplatesAVRRisco();
        tipoDao.removerTemplatesAVRLevantamentos();


        //levantamento

        atualizacaoDao.inserirRegisto(atualizacaoLevantamento);
        tipoDao.inserirTemplateAvrLevantamento(dadosNovosLevantamento);
        tipoDao.atualizarTemplateAvrLevantamento(dadosAlteradosLevantamento);


        //riscos

        atualizacaoDao.inserirRegisto(atualizacaoRisco);
        tipoDao.inserirTemplateAvrRisco(dadosNovosRiscos);
        tipoDao.atualizarTemplateAvrRisco(dadosAlteradosRiscos);


        //medidas

        List<TipoTemplatesAVRMedidaRisco> medidas = new ArrayList<>();
        List<TipoTemplatesAVRMedidaRisco> medidasAlteradas = new ArrayList<>();

        for(TipoTemplatesAVRMedidaRisco item : medidasExistentes){

            if(tipoDao.filtrarMedidaExistentesTemplate(item.id) == true){
                medidas.add(item);
            }
        }

        for(TipoTemplatesAVRMedidaRisco item : medidasAlteradasExistentes){

            if(tipoDao.filtrarMedidaExistentesTemplate(item.id) == true){
                medidasAlteradas.add(item);
            }
        }


        for(TipoTemplatesAVRMedidaRisco item : medidasRecomendadas){

            if(tipoDao.filtrarMedidaRecomendadasTemplate(item.id) == true){
                medidas.add(item);
            }
        }

        for(TipoTemplatesAVRMedidaRisco item : medidasAlteradasRecomendadas){

            if(tipoDao.filtrarMedidaRecomendadasTemplate(item.id) == true){
                medidasAlteradas.add(item);
            }
        }

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
        tipoDao.removerAtividadesPlaneaveis();

        atualizacaoDao.inserirRegisto(atualizacao);
        tipoDao.inserirAtividadesPlaneaiveis(dadosNovos);
        tipoDao.atualizarAtividadesPlaneaiveis(dadosAlteradaos);
    }


}
