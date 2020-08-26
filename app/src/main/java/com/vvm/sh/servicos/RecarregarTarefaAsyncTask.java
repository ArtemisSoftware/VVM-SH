package com.vvm.sh.servicos;

import android.os.AsyncTask;

import com.vvm.sh.api.modelos.SessaoResposta;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.repositorios.TransferenciasRepositorio;

import java.util.List;

public class RecarregarTarefaAsyncTask extends TrabalhoAsyncTask {

    private Tarefa tarefa;

    public RecarregarTarefaAsyncTask(VvmshBaseDados vvmshBaseDados, TransferenciasRepositorio repositorio, Tarefa tarefa) {
        super(vvmshBaseDados, repositorio, tarefa.idUtilizador);

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

        //TODO:deve atualizar o UI
    }

}
