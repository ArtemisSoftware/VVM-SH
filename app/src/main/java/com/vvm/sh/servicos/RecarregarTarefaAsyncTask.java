package com.vvm.sh.servicos;

import android.os.AsyncTask;
import android.os.Handler;

import com.vvm.sh.api.modelos.SessaoResposta;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.repositorios.TransferenciasRepositorio;
import com.vvm.sh.util.AtualizacaoUI;

import java.util.List;

public class RecarregarTarefaAsyncTask extends TrabalhoAsyncTask {

    private Tarefa tarefa;

    public RecarregarTarefaAsyncTask(VvmshBaseDados vvmshBaseDados, TransferenciasRepositorio repositorio, Handler handler, Tarefa tarefa) {
        super(vvmshBaseDados, repositorio, handler, tarefa.idUtilizador);

        this.tarefa = tarefa;
    }

    @Override
    protected void inserirTrabalho(List<SessaoResposta.TrabalhoInfo> trabalho, String data) {

        for (SessaoResposta.TrabalhoInfo tarefa : trabalho) {

            if(tarefa.tarefas.dados.prefixoCt.equals(this.tarefa.prefixoCt) == true || tarefa.tarefas.dados.ordem.equals(this.tarefa.ordem) == true){

                repositorio.eliminarTarefa(this.tarefa.idTarefa);
                inserirTarefas(data, tarefa);
            }
        }

        atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_DOWNLOAD_CONCLUIDO);
    }

}
