package com.vvm.sh.servicos.tipos;

import com.vvm.sh.api.modelos.pedido.ITipo;
import com.vvm.sh.api.modelos.pedido.ITipoAtividadePlaneavel;
import com.vvm.sh.api.modelos.pedido.ITipoListagem;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.TipoAtividadePlaneavel;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.util.AtualizacaoUI_;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.mapeamento.DownloadMapping;

import java.util.ArrayList;
import java.util.List;

public abstract class TipoAtividadesPlaneaveisAsyncTask extends CarregamentoTipoAsyncTask{


    public TipoAtividadesPlaneaveisAsyncTask(VvmshBaseDados vvmshBaseDados, CarregamentoTiposRepositorio repositorio) {
        super(vvmshBaseDados, repositorio);
    }

    @Override
    protected void inserirRegisto(ITipoListagem resposta, Atualizacao atualizacao) {

        if(atualizacao.tipo == Identificadores.Atualizacoes.ATIVIDADES_PLANEAVEIS) {

            List<TipoAtividadePlaneavel> dadosNovos = new ArrayList<>();
            List<TipoAtividadePlaneavel> dadosAlterados = new ArrayList<>();

            for (ITipo item : resposta.dadosNovos) {
                //--dadosNovos.add(DownloadMapping.INSTANCE.map((ITipoAtividadePlaneavel)item, resposta));
            }

//            for (ITipoAtividadePlaneavel item : resposta.dadosAlterados) {
//                dadosNovos.add(DownloadMapping.INSTANCE.map(item, resposta));
//            }

            //--repositorio.atualizarTipoAtividadesPlaneaveis(atualizacao, dadosNovos, dadosAlterados);
            //--listener.atualizarTransferencia(new AtualizacaoUI_(AtualizacaoUI_.Estado.PROCESSAMENTO_ATIVIDADES_PLANEAVEIS, ++index, dados.size(), obterDescricaoApi(atualizacao) + " " + atualizacao.descricao));
        }
    }


}
