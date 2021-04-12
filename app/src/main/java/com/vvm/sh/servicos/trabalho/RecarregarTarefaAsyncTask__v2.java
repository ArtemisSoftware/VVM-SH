package com.vvm.sh.servicos.trabalho;

import com.vvm.sh.api.modelos.pedido.ISessao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.repositorios.DownloadTrabalhoRepositorio;
import com.vvm.sh.ui.transferencias.adaptadores.OnTransferenciaListener;
import com.vvm.sh.util.AtualizacaoUI_;

import java.util.List;

public class RecarregarTarefaAsyncTask__v2 extends TrabalhoAsyncTask {


    private Tarefa tarefa;


    public RecarregarTarefaAsyncTask__v2(OnTransferenciaListener listener, VvmshBaseDados vvmshBaseDados, DownloadTrabalhoRepositorio repositorio, Tarefa tarefa) {
        super(listener, vvmshBaseDados, repositorio, tarefa.idUtilizador);
        this.tarefa = tarefa;
    }

    @Override
    protected void inserirTrabalho(List<ISessao.TrabalhoInfo> trabalho, String data, int api) {

        for (ISessao.TrabalhoInfo tarefa : trabalho) {

            if(tarefa.tarefas.dados.prefixoCt.equals(this.tarefa.prefixoCt) == true && tarefa.tarefas.dados.ordem.equals(this.tarefa.ordem) == true){

                repositorio.eliminarTarefa(this.tarefa.idTarefa);
                inserirTarefas(data, tarefa, api);

                listener.atualizarTransferencia(new AtualizacaoUI_(AtualizacaoUI_.Estado.PROCESSAMENTO_TRABALHO, 1, 1, "Tarefa 1"));
            }
        }
    }



}
