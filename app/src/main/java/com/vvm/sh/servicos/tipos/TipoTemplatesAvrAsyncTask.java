package com.vvm.sh.servicos.tipos;

import com.vvm.sh.api.modelos.pedido.ITipo;
import com.vvm.sh.api.modelos.pedido.ITipoListagem;
import com.vvm.sh.api.modelos.pedido.ITipoTemplateAvrLevantamento;
import com.vvm.sh.api.modelos.pedido.ITipoTemplateAvrLevantamentoListagem;
import com.vvm.sh.api.modelos.pedido.ITipoTemplateAvrRisco;
import com.vvm.sh.api.modelos.pedido.ITipoTemplateAvrRiscoListagem;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.baseDados.entidades.TipoTemplateAvrLevantamento;
import com.vvm.sh.baseDados.entidades.TipoTemplateAvrRisco;
import com.vvm.sh.baseDados.entidades.TipoTemplatesAVRMedidaRisco;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.util.AtualizacaoUI_;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.mapeamento.DownloadMapping;

import java.util.ArrayList;
import java.util.List;

public abstract class TipoTemplatesAvrAsyncTask extends CarregamentoTipoAsyncTask {

    private ITipoTemplateAvrLevantamentoListagem finalDadosLevantamento;
    private ITipoTemplateAvrRiscoListagem finalDadosRisco;


    public TipoTemplatesAvrAsyncTask(VvmshBaseDados vvmshBaseDados, CarregamentoTiposRepositorio repositorio) {
        super(vvmshBaseDados, repositorio);
    }

    @Override
    protected List<Object> filtarRegistos(List<Object> respostas) {

        List<Object> registos = new ArrayList<>();

        ITipoTemplateAvrLevantamentoListagem dadosLevantamento = null;
        ITipoTemplateAvrRiscoListagem dadosRisco  = null;

        for (Object item : respostas) {

            if (item instanceof ITipoTemplateAvrLevantamentoListagem) {
                dadosLevantamento = (ITipoTemplateAvrLevantamentoListagem) item;
               registos.add(dadosLevantamento);
            }

            if (item instanceof ITipoTemplateAvrRiscoListagem) {
                dadosRisco = (ITipoTemplateAvrRiscoListagem) item;
            }
        }

        finalDadosLevantamento = dadosLevantamento;
        finalDadosRisco = dadosRisco;
        return registos;
    }

    @Override
    protected Atualizacao obterAtualizacao(Object resposta) {
        return DownloadMapping.INSTANCE.map((ITipoTemplateAvrLevantamentoListagem)resposta);
    }

    @Override
    protected void inserirRegisto(Object resposta, Atualizacao atualizacao) {


        Atualizacao atualizacaoLevantamento = null;
        List<TipoTemplateAvrLevantamento> dadosNovos = new ArrayList<>();
        List<TipoTemplateAvrLevantamento> dadosAlterados = new ArrayList<>();


        if(finalDadosLevantamento != null) {

            atualizacaoLevantamento = DownloadMapping.INSTANCE.map(finalDadosLevantamento);

            for (ITipoTemplateAvrLevantamento item : finalDadosLevantamento.dadosNovos) {

                TipoTemplateAvrLevantamento registo = DownloadMapping.INSTANCE.map(item, finalDadosLevantamento);
                dadosNovos.add(registo);
            }

            for (ITipoTemplateAvrLevantamento item : finalDadosLevantamento.dadosAlterados) {
                dadosAlterados.add(DownloadMapping.INSTANCE.map(item, finalDadosLevantamento));
            }
        }


        Atualizacao atualizacaoRisco = null;
        List<TipoTemplateAvrRisco> dadosNovosRiscos = new ArrayList<>();
        List<TipoTemplateAvrRisco> dadosAlteradosRiscos = new ArrayList<>();

        List<TipoTemplatesAVRMedidaRisco> medidasExistentes = new ArrayList<>();
        List<TipoTemplatesAVRMedidaRisco> medidasAlteradasExistentes = new ArrayList<>();
        List<TipoTemplatesAVRMedidaRisco> medidasRecomendadas = new ArrayList<>();
        List<TipoTemplatesAVRMedidaRisco> medidasAlteradasRecomendadas = new ArrayList<>();

        if(finalDadosRisco != null) {

            atualizacaoRisco = DownloadMapping.INSTANCE.map(finalDadosRisco);

            for (ITipoTemplateAvrRisco item : finalDadosRisco.dadosNovos) {

                TipoTemplateAvrRisco registo = DownloadMapping.INSTANCE.map(item, finalDadosRisco);
                dadosNovosRiscos.add(registo);

                for (int medida : item.medidasExistentes) {
                    medidasExistentes.add(new TipoTemplatesAVRMedidaRisco(registo.id, Identificadores.Origens.MEDIDAS_RISCO_EXISTENTES, medida));
                }

                for (int medida : item.medidasRecomendadas) {
                    medidasRecomendadas.add(new TipoTemplatesAVRMedidaRisco(registo.id, Identificadores.Origens.MEDIDAS_RISCO_RECOMENDADAS, medida));
                }
            }

            for (ITipoTemplateAvrRisco item : finalDadosRisco.dadosAlterados) {

                TipoTemplateAvrRisco registo = DownloadMapping.INSTANCE.map(item, finalDadosRisco);
                dadosAlteradosRiscos.add(registo);

                for (int medida : item.medidasExistentes) {
                    medidasAlteradasExistentes.add(new TipoTemplatesAVRMedidaRisco(registo.id, Identificadores.Origens.MEDIDAS_RISCO_EXISTENTES, medida));
                }

                for (int medida : item.medidasRecomendadas) {
                    medidasAlteradasRecomendadas.add(new TipoTemplatesAVRMedidaRisco(registo.id, Identificadores.Origens.MEDIDAS_RISCO_RECOMENDADAS, medida));
                }
            }

        }


//        repositorio.atualizarTipoTemplateAvr(
//                atualizacaoLevantamento, dadosNovos, dadosAlterados,
//                atualizacaoRisco, dadosNovosRiscos, dadosAlteradosRiscos,
//                medidasExistentes, medidasAlteradasExistentes, medidasRecomendadas, medidasAlteradasRecomendadas);

    }
}
