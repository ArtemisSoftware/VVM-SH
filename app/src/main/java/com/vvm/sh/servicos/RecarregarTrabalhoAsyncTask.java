package com.vvm.sh.servicos;

import com.vvm.sh.api.modelos.SessaoResposta;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.TransferenciasRepositorio;

import java.util.List;

public class RecarregarTrabalhoAsyncTask extends TrabalhoAsyncTask {

    private long dataTrabalho;

    public RecarregarTrabalhoAsyncTask(VvmshBaseDados vvmshBaseDados, TransferenciasRepositorio repositorio, String idUtilizador, long dataTrabalho) {
        super(vvmshBaseDados, repositorio, idUtilizador);

        this.dataTrabalho = dataTrabalho;
    }


    @Override
    protected void inserirTrabalho(List<SessaoResposta.TrabalhoInfo> trabalho, String data) {

        repositorio.eliminarTrabalho(idUtilizador, dataTrabalho);

        for (SessaoResposta.TrabalhoInfo tarefa : trabalho) {
            inserirTarefas(data, tarefa);
        }

        //TODO:deve atualizar o UI
    }

}
