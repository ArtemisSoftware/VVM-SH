package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.api.SegurancaTrabalhoApi;
import com.vvm.sh.baseDados.dao.AtualizacaoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.dao.TipoNovoDao;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.baseDados.entidades.TipoAtividadePlaneavel;
import com.vvm.sh.baseDados.entidades.TipoNovo;
import com.vvm.sh.baseDados.entidades.TipoTemplateAvrLevantamento;
import com.vvm.sh.baseDados.entidades.TipoTemplateAvrRisco;
import com.vvm.sh.baseDados.entidades.TipoTemplatesAVRMedidaRisco;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.ArrayList;
import java.util.List;

public class CarregamentoTiposRepositorio {


    private final AtualizacaoDao atualizacaoDao;
    private final TipoDao tipoDao;
    private final TipoNovoDao tipoNovoDao;

    public CarregamentoTiposRepositorio(@NonNull AtualizacaoDao atualizacaoDao, @NonNull TipoDao tipoDao, @NonNull TipoNovoDao tipoNovoDao) {

        this.atualizacaoDao = atualizacaoDao;
        this.tipoDao = tipoDao;
        this.tipoNovoDao = tipoNovoDao;
    }


    private void inserirTipo(Atualizacao atualizacao, List<Tipo> tiposNovos, List<Tipo> tiposAlterados){

        if(atualizacaoDao.existeRegisto(atualizacao.descricao, atualizacao.api) == true){
            atualizacaoDao.atualizarRegisto(atualizacao);
        }
        else{
            atualizacaoDao.inserirRegisto(atualizacao);
        }

        tipoDao.inserir(tiposNovos);
        tipoDao.atualizar(tiposAlterados);
    }


    /**
     * Metodo que permite atualizar um tipo<br>
     *     1->Atualizar timestamp<br>
     *     2->inserir novos dados
     * @param atualizacao os dados da atualizacao
     * @param tiposNovos tipos novos a inserir
     * @param tiposAlterados tipos a alterar
     */
    public void atualizarTipo(Atualizacao atualizacao, List<Tipo> tiposNovos, List<Tipo> tiposAlterados){
        inserirTipo(atualizacao, tiposNovos, tiposAlterados);
    }


    /**
     * Metodo que permite recarregar um tipo<br>
     *     1->remover o timestamp + dados já inseridos<br>
     *     2->inserir novos dados
     * @param atualizacao
     * @param dadosNovos
     * @param dadosAlteradaos
     */
    public void recarregarTipo(Atualizacao atualizacao, List<Tipo> dadosNovos, List<Tipo> dadosAlteradaos){

        atualizacaoDao.remover(atualizacao.descricao, atualizacao.api);
        inserirTipo(atualizacao, dadosNovos, dadosAlteradaos);
    }


























    //----------------------------------------


    /**
     * Metodo que permite eliminar uma atualização e os tipos associados
     * @param atualizacao os dados da atualização
     */
    public void eliminarAtualizacao(Atualizacao atualizacao){
        atualizacaoDao.remover(atualizacao.descricao);
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

        atualizacaoDao.remover(atualizacao.descricao, atualizacao.api);

        atualizacaoDao.inserirRegisto(atualizacao);
        tipoDao.inserir(dadosNovos);
        tipoDao.atualizar(dadosAlteradaos);
    }





    public void eliminarAtualizacao(String descricao){
        atualizacaoDao.remover(descricao);
    }






    /**
     * Metodo que permite atualizar um tipo<br>
     *     1->Atualizar timestamp<br>
     *     2->inserir novos dados
     * @param atualizacao os dados da atualizacao
     * @param tiposNovos tipos novos a inserir
     * @param tiposAlterados tipos a alterar
     */
    public void atualizarTipoAtividadesPlaneaveis(Atualizacao atualizacao, List<TipoAtividadePlaneavel> tiposNovos, List<TipoAtividadePlaneavel> tiposAlterados){

        if(atualizacaoDao.existeRegisto(atualizacao.descricao, atualizacao.api) == true){
            atualizacaoDao.atualizarRegisto(atualizacao);
        }
        else{
            atualizacaoDao.inserirRegisto(atualizacao);
        }

        tipoDao.inserirAtividadesPlaneaiveis(tiposNovos);
        tipoDao.atualizarAtividadesPlaneaiveis(tiposAlterados);
    }



    public void atualizarTipoTemplateAvr(Atualizacao atualizacaoLevantamento, List<TipoTemplateAvrLevantamento> dadosNovosLevantamento, List<TipoTemplateAvrLevantamento> dadosAlteradosLevantamento,
                                        Atualizacao atualizacaoRisco,
                                        List<TipoTemplateAvrRisco> dadosNovosRiscos, List<TipoTemplateAvrRisco> dadosAlteradosRiscos,
                                        List<TipoTemplatesAVRMedidaRisco> medidasExistentes, List<TipoTemplatesAVRMedidaRisco> medidasAlteradasExistentes,
                                        List<TipoTemplatesAVRMedidaRisco> medidasRecomendadas, List<TipoTemplatesAVRMedidaRisco> medidasAlteradasRecomendadas) {


        if(atualizacaoLevantamento != null) {

            if (atualizacaoDao.existeRegisto(atualizacaoLevantamento.descricao, atualizacaoLevantamento.api) == true) {
                atualizacaoDao.atualizarRegisto(atualizacaoLevantamento);
            } else {
                atualizacaoDao.inserirRegisto(atualizacaoLevantamento);
            }

            tipoDao.inserirTemplateAvrLevantamento(dadosNovosLevantamento);
            tipoDao.atualizarTemplateAvrLevantamento(dadosAlteradosLevantamento);
        }


        if(atualizacaoRisco != null) {

            if (atualizacaoDao.existeRegisto(atualizacaoRisco.descricao, atualizacaoRisco.api) == true) {
                atualizacaoDao.atualizarRegisto(atualizacaoRisco);
            } else {
                atualizacaoDao.inserirRegisto(atualizacaoRisco);
            }

            tipoDao.inserirTemplateAvrRisco(dadosNovosRiscos);
            tipoDao.atualizarTemplateAvrRisco(dadosAlteradosRiscos);



            List<TipoTemplatesAVRMedidaRisco> medidas = new ArrayList<>();
            List<TipoTemplatesAVRMedidaRisco> medidasAlteradas = new ArrayList<>();

            for(TipoTemplatesAVRMedidaRisco item : medidasExistentes){

                if(tipoDao.filtrarMedidaExistentesTemplate(item.id) == true){
                    medidas.add(item);
                }
            }

            for(TipoTemplatesAVRMedidaRisco item : medidasRecomendadas){

                if(tipoDao.filtrarMedidaRecomendadasTemplate(item.id) == true){
                    medidas.add(item);
                }
            }

            tipoDao.inserirTemplatesAVRMedidaRisco(medidas);





            for(TipoTemplatesAVRMedidaRisco item : medidasAlteradasExistentes){

                tipoDao.removerTemplatesAVRMedidaRisco(item.id, item.origem);

                if(tipoDao.filtrarMedidaExistentesTemplate(item.id) == true){
                    medidasAlteradas.add(item);
                }
            }

            for(TipoTemplatesAVRMedidaRisco item : medidasAlteradasRecomendadas){

                tipoDao.removerTemplatesAVRMedidaRisco(item.id, item.origem);

                if(tipoDao.filtrarMedidaRecomendadasTemplate(item.id) == true){
                    medidasAlteradas.add(item);
                }
            }


            tipoDao.inserirTemplatesAVRMedidaRisco(medidasAlteradas);
        }

    }


    public void atualizarEquipamentos(List<Integer> rejeitados, List<Integer> aprovados) {

        for (int idProvisorio : aprovados) {

            int id = tipoDao.obterIdTipo(idProvisorio, TiposUtil.MetodosTipos.TIPOS_MAQUINA);

            if(id != 0) {
                tipoNovoDao.aprovarEquipamentos(id, idProvisorio);
            }
        }

        tipoNovoDao.rejeitarEquipamentos(rejeitados);
    }
}
