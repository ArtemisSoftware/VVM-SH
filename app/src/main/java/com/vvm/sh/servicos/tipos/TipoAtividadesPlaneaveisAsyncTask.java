package com.vvm.sh.servicos.tipos;

import com.vvm.sh.api.modelos.pedido.ITipoAtividadePlaneavel;
import com.vvm.sh.api.modelos.pedido.ITipoAtividadePlaneavelListagem;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.TipoAtividadePlaneavel;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.util.mapeamento.DownloadMapping;

import java.util.ArrayList;
import java.util.List;

public abstract class TipoAtividadesPlaneaveisAsyncTask extends CarregamentoTipoAsyncTask{


    public TipoAtividadesPlaneaveisAsyncTask(VvmshBaseDados vvmshBaseDados, CarregamentoTiposRepositorio repositorio) {
        super(vvmshBaseDados, repositorio);
    }

    @Override
    protected List<Object> filtarRegistos(List<Object> respostas) {

        List<Object> registos = new ArrayList<>();

        for (Object item : respostas) {

            if (item instanceof ITipoAtividadePlaneavelListagem) {
                registos.add((ITipoAtividadePlaneavelListagem) item);
            }
        }

        return registos;
    }

    @Override
    protected Atualizacao obterAtualizacao(Object resposta) {
        return DownloadMapping.INSTANCE.map((ITipoAtividadePlaneavelListagem)resposta);
    }

    @Override
    protected void inserirRegisto(Object resposta, Atualizacao atualizacao) {

        ITipoAtividadePlaneavelListagem registo = (ITipoAtividadePlaneavelListagem)resposta;

        List<TipoAtividadePlaneavel> dadosNovos = new ArrayList<>();
        List<TipoAtividadePlaneavel> dadosAlterados = new ArrayList<>();

        for (ITipoAtividadePlaneavel item : registo.dadosNovos) {
            dadosNovos.add(DownloadMapping.INSTANCE.map((ITipoAtividadePlaneavel)item, registo));
        }

        for (ITipoAtividadePlaneavel item : registo.dadosAlterados) {
            dadosAlterados.add(DownloadMapping.INSTANCE.map(item, registo));
        }

        //--repositorio.atualizarTipoAtividadesPlaneaveis_(atualizacao, dadosNovos, dadosAlterados);
    }


    //
//    @Override
//    protected void inserirRegisto(ITipoListagem resposta, Atualizacao atualizacao) {
//
//        if(atualizacao.tipo == Identificadores.Atualizacoes.ATIVIDADES_PLANEAVEIS) {
//
//            List<TipoAtividadePlaneavel> dadosNovos = new ArrayList<>();
//            List<TipoAtividadePlaneavel> dadosAlterados = new ArrayList<>();
//
//            for (ITipo item : resposta.dadosNovos) {
//                //--dadosNovos.add(DownloadMapping.INSTANCE.map((ITipoAtividadePlaneavel)item, resposta));
//            }
//
////            for (ITipoAtividadePlaneavel item : resposta.dadosAlterados) {
////                dadosNovos.add(DownloadMapping.INSTANCE.map(item, resposta));
////            }
//
//            //--repositorio.atualizarTipoAtividadesPlaneaveis(atualizacao, dadosNovos, dadosAlterados);
//            //--listener.atualizarTransferencia(new AtualizacaoUI_(AtualizacaoUI_.Estado.PROCESSAMENTO_ATIVIDADES_PLANEAVEIS, ++index, dados.size(), obterDescricaoApi(atualizacao) + " " + atualizacao.descricao));
//        }
//    }


}
