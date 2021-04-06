package com.vvm.sh.servicos.trabalho;

import com.vvm.sh.api.modelos.pedido.ISessao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.repositorios.DownloadTrabalhoRepositorio;
import com.vvm.sh.servicos.tipos.CarregamentoTrabalhoAsyncTask;
import com.vvm.sh.ui.transferencias.adaptadores.OnTransferenciaListener;
import com.vvm.sh.ui.transferencias.modelos.Sessao;
import com.vvm.sh.util.AtualizacaoUI;

import java.util.List;

public class RecarregarTarefaAsyncTask__v2 extends CarregamentoTrabalhoAsyncTask {


    private OnTransferenciaListener listener;
    private Tarefa tarefa;


    public RecarregarTarefaAsyncTask__v2(OnTransferenciaListener listener, VvmshBaseDados vvmshBaseDados, DownloadTrabalhoRepositorio repositorio, Tarefa tarefa) {
        super(vvmshBaseDados, repositorio);

        this.listener = listener;
        this.tarefa = tarefa;
    }


    @Override
    protected void executar(Sessao resposta) {

    }



//    @Override
//    protected void inserirTrabalho(List<ISessao.TrabalhoInfo> trabalho, String data, int api) {
//
//        for (ISessao.TrabalhoInfo tarefa : trabalho) {
//
//            if(tarefa.tarefas.dados.prefixoCt.equals(this.tarefa.prefixoCt) == true || tarefa.tarefas.dados.ordem.equals(this.tarefa.ordem) == true){
//
//                repositorio.eliminarTarefa(this.tarefa.idTarefa);
//                inserirTarefas(data, tarefa, api);
//
//                atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_TRABALHO, "Tarefa 1", 1, trabalho.size());
//            }
//        }
//
//        atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_DOWNLOAD_CONCLUIDO);
//    }
}
