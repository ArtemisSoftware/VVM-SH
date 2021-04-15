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
import com.vvm.sh.baseDados.entidades.TipoNovo;
import com.vvm.sh.baseDados.entidades.TipoTemplateAvrLevantamento;
import com.vvm.sh.baseDados.entidades.TipoTemplateAvrRisco;
import com.vvm.sh.baseDados.entidades.TipoTemplatesAVRMedidaRisco;
import com.vvm.sh.ui.opcoes.modelos.ResumoChecklist;
import com.vvm.sh.ui.opcoes.modelos.ResumoTipo;
import com.vvm.sh.ui.opcoes.modelos.TemplateAvr;
import com.vvm.sh.ui.transferencias.modelos.AtualizacaoTipos;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.excepcoes.TipoInexistenteException;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function5;

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
    //Geral
    //------------

    /**
     * Metodo que permite obter as atualizacoes
     * @return uma lista de atualizacoes
     */
    public Maybe<AtualizacaoTipos> obterAtualizacoes() {

        return Maybe.zip(
                atualizacaoDao.obterAtualizacoes(Identificadores.Atualizacoes.TIPO),
                atualizacaoDao.obterAtualizacoes(Identificadores.Atualizacoes.TEMPLATE),
                atualizacaoDao.obterAtualizacoes(Identificadores.Atualizacoes.ATIVIDADES_PLANEAVEIS),
                tipoDao.obterEquipamentosNaoValidados(),
                tipoDao.obterChecklistDados(TiposUtil.MetodosTiposChecklist.ID_CHECKLISTS__),

                new Function5<List<Atualizacao>, List<Atualizacao>, List<Atualizacao>, List<TipoNovo>, List<CheckList>, AtualizacaoTipos>() {
                    @Override
                    public AtualizacaoTipos apply(List<Atualizacao> atualizacaoTipo, List<Atualizacao> atualizacaoTemplate, List<Atualizacao> atualizacaoAtividadesPlaneaveis,
                                                  List<TipoNovo> tipoNovos, List<CheckList> checkLists) throws Exception {

                        AtualizacaoTipos atualizacaoTipos = new AtualizacaoTipos();
                        atualizacaoTipos.atualizacoes = TiposUtil.fixarSeloTemporal(atualizacaoTipo, atualizacaoAtividadesPlaneaveis, atualizacaoTemplate);
                        atualizacaoTipos.tiposNovos = tipoNovos;
                        atualizacaoTipos.checklists = checkLists;
                        return atualizacaoTipos;
                    }
                });

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



    public Single<Integer> eliminarChecklists(){
        return tipoDao.elimiarChecklists();
    }

    //------------
    //Atividades planeaveis
    //------------


    //------------
    //Templates
    //------------



































    //------------
    //tipos
    //------------

    public Maybe<AtualizacaoTipos> obterAtualizacoes(boolean primeiraUtilizacao) {

        return Maybe.zip(
                atualizacaoDao.obterAtualizacoes(Identificadores.Atualizacoes.TIPO),
                atualizacaoDao.obterAtualizacoes(Identificadores.Atualizacoes.TEMPLATE),
                atualizacaoDao.obterAtualizacoes(Identificadores.Atualizacoes.ATIVIDADES_PLANEAVEIS),
                tipoDao.obterEquipamentosNaoValidados(),
                tipoDao.obterChecklistDados(TiposUtil.MetodosTiposChecklist.ID_CHECKLISTS__),

                new Function5<List<Atualizacao>, List<Atualizacao>, List<Atualizacao>, List<TipoNovo>, List<CheckList>, AtualizacaoTipos>() {
                    @Override
                    public AtualizacaoTipos apply(List<Atualizacao> atualizacaoTipo, List<Atualizacao> atualizacaoTemplate, List<Atualizacao> atualizacaoAtividadesPlaneaveis,
                                                  List<TipoNovo> tipoNovos, List<CheckList> checkLists) throws Exception {

                        AtualizacaoTipos atualizacaoTipos = new AtualizacaoTipos();
                        atualizacaoTipos.atualizacoes = TiposUtil.fixarSeloTemporal(atualizacaoTipo, atualizacaoAtividadesPlaneaveis, atualizacaoTemplate);
                        atualizacaoTipos.tiposNovos = tipoNovos;
                        atualizacaoTipos.checklists = checkLists;
                        return atualizacaoTipos;
                    }
                });

    }





    /**
     * Metodo que permite obter todos os tipos
     * @return os dados de todos os tipos
     * @throws TipoInexistenteException
     */
    public Single<List<ITipoListagem>> obterTipos() throws TipoInexistenteException {

        List<SingleSource> tipos = new ArrayList<>();

        for(TiposUtil.MetodoApi metodo : TiposUtil.obterMetodos()) {
            tipos.addAll(obterInvocacoesTipos(metodo));
        }

        SingleSource[] source = new SingleSource[tipos.size()];

        for(int index = 0; index < tipos.size(); ++index){
            source[index] = tipos.get(index);
        }

        return  Single.concatArray(source).toList();
    }


    public Single<List<Object>> obterTipos(AtualizacaoTipos atualizacoes) {

        List<SingleSource> tipos = new ArrayList<>();

        for(TiposUtil.MetodoApi metodo : atualizacoes.atualizacoes){

            tipos.addAll(obterInvocacoesTipos(metodo));



            if(metodo.tipo == Identificadores.Atualizacoes.ATIVIDADES_PLANEAVEIS) {

                if(metodo.descricao.equals(TiposUtil.MetodosTipos.AtividadesPlaneaveis.ATIVIDADES_PLANEAVEIS) == true){
                    tipos.add(apiST.obterTipoAtividadesPlaneaveis(SegurancaTrabalhoApi.HEADER_TIPO, metodo.seloTemporalSA));
                }
            }

            if(metodo.tipo == Identificadores.Atualizacoes.TEMPLATE) {

                if(metodo.descricao.equals(TiposUtil.MetodosTipos.TemplateAvr.TEMPLATE_AVALIACAO_RISCOS_LEVANTAMENTOS) == true){
                    tipos.add(apiST.obterTipoTemplatesAVR_Levantamentos(SegurancaTrabalhoApi.HEADER_TIPO, metodo.seloTemporalSA));
                }

                if(metodo.descricao.equals(TiposUtil.MetodosTipos.TemplateAvr.TEMPLATE_AVALIACAO_RISCOS_RISCOS) == true){
                    tipos.add(apiST.obterTipoTemplatesAVR_Riscos(SegurancaTrabalhoApi.HEADER_TIPO, metodo.seloTemporalSA));
                }

            }
        }

        for(TipoNovo tipo : atualizacoes.tiposNovos){

            Map<String, String> cabecalho = SegurancaTrabalhoApi.HEADER_EQUIPAMENTO;
            cabecalho.put(Sintaxe.API.ID_EQUIPAMENTO_PROVISORIO, tipo.idProvisorio + "");

            tipos.add(apiST.obterEstadoEquipamento(cabecalho, tipo.descricao));
        }

        return obterSingleSource(tipos);
    }






    private List<SingleSource> obterInvocacoesTipos(TiposUtil.MetodoApi metodo){

        List<SingleSource> tipos = new ArrayList<>();

        if(metodo.tipo == Identificadores.Atualizacoes.TIPO) {

            if (metodo.sa != null) {
                tipos.add(apiSA.obterTipo(SegurancaAlimentarApi.HEADER_TIPO, metodo.sa, metodo.seloTemporalSA));
            }

            if (metodo.sht != null) {
                tipos.add(apiST.obterTipo(SegurancaTrabalhoApi.HEADER_TIPO, metodo.sht, metodo.seloTemporalSHT));
            }
        }

        return tipos;
    }

    private Single<List<Object>> obterSingleSource(List<SingleSource> lista){

        SingleSource[] source = new SingleSource[lista.size()];

        for(int index = 0; index < lista.size(); ++index){
            source[index] = lista.get(index);
        }

        return Single.concatArray(source).toList();
    }


    //------------
    //Checklist
    //------------


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




    public Single<AtualizacaoTipos> eliminarAtualizacoes(int tipo){

        return atualizacaoDao.elimiarAtualizacoes(Identificadores.Atualizacoes.TIPO)
                .flatMap(new Function<Integer, SingleSource<AtualizacaoTipos>>() {
                    @Override
                    public SingleSource<AtualizacaoTipos> apply(Integer integer) throws Exception {
                        return obterAtualizacoes(false).toSingle();
                    }
                });

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
