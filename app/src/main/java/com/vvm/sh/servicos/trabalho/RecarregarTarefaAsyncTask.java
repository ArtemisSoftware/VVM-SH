package com.vvm.sh.servicos.trabalho;

import android.os.Handler;

import com.vvm.sh.api.modelos.pedido.ISessao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.repositorios.TransferenciasRepositorio;
import com.vvm.sh.util.AtualizacaoUI;

import java.util.List;

public class RecarregarTarefaAsyncTask extends CarregarTrabalhoAsyncTask {

    private Tarefa tarefa;

    public RecarregarTarefaAsyncTask(VvmshBaseDados vvmshBaseDados, TransferenciasRepositorio repositorio, Handler handler, Tarefa tarefa) {
        super(vvmshBaseDados, repositorio, handler, tarefa.idUtilizador);

        this.tarefa = tarefa;
    }

    @Override
    protected void inserirTrabalho(List<ISessao.TrabalhoInfo> trabalho, String data, int api) {

        for (ISessao.TrabalhoInfo tarefa : trabalho) {

            if(tarefa.tarefas.dados.prefixoCt.equals(this.tarefa.prefixoCt) == true || tarefa.tarefas.dados.ordem.equals(this.tarefa.ordem) == true){

                repositorio.eliminarTarefa(this.tarefa.idTarefa);
                inserirTarefas(data, tarefa, api);

                atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_TRABALHO, "Tarefa 1", 1, trabalho.size());
            }
        }

        atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_DOWNLOAD_CONCLUIDO);
    }

}
