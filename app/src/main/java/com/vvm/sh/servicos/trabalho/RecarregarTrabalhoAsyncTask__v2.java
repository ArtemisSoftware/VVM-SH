package com.vvm.sh.servicos.trabalho;

import com.vvm.sh.api.modelos.pedido.ISessao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.DownloadTrabalhoRepositorio;
import com.vvm.sh.servicos.tipos.CarregamentoTrabalhoAsyncTask;
import com.vvm.sh.ui.transferencias.adaptadores.OnTransferenciaListener;
import com.vvm.sh.ui.transferencias.modelos.Sessao;
import com.vvm.sh.util.AtualizacaoUI;

import java.util.List;

public class RecarregarTrabalhoAsyncTask__v2 extends CarregamentoTrabalhoAsyncTask {

    private OnTransferenciaListener listener;
    private long dataTrabalho;
    private int api = 0;
    protected String idUtilizador;


    public RecarregarTrabalhoAsyncTask__v2(OnTransferenciaListener listener, VvmshBaseDados vvmshBaseDados, DownloadTrabalhoRepositorio repositorio, String idUtilizador, long dataTrabalho, int api) {
        super(vvmshBaseDados, repositorio);

        this.listener = listener;
        this.idUtilizador = idUtilizador;
        this.dataTrabalho = dataTrabalho;
        this.api = api;
    }

    @Override
    protected void executar(Sessao resposta) {

    }

//
//    @Override
//    protected void inserirTrabalho(List<ISessao.TrabalhoInfo> trabalho, String data, int api) {
//
//        repositorio.eliminarTrabalho(idUtilizador, dataTrabalho);
//
//        for(int index = 0; index < trabalho.size(); ++index){
//
//            inserirTarefas(data, trabalho.get(index), api);
//            atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_TRABALHO, "Tarefa " + (index + 1), (index + 1), trabalho.size());
//        }
//
//        atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_DOWNLOAD_CONCLUIDO);
//    }

}
