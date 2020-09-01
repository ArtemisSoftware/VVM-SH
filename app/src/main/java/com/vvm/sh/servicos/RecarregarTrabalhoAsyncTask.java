package com.vvm.sh.servicos;

import android.os.Handler;

import com.vvm.sh.api.modelos.SessaoResposta;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.TransferenciasRepositorio;
import com.vvm.sh.util.AtualizacaoUI;

import java.util.List;

public class RecarregarTrabalhoAsyncTask extends TrabalhoAsyncTask {

    private long dataTrabalho;

    public RecarregarTrabalhoAsyncTask(VvmshBaseDados vvmshBaseDados, TransferenciasRepositorio repositorio, Handler handler, String idUtilizador, long dataTrabalho) {
        super(vvmshBaseDados, repositorio, handler, idUtilizador);

        this.dataTrabalho = dataTrabalho;
    }


    @Override
    protected void inserirTrabalho(List<SessaoResposta.TrabalhoInfo> trabalho, String data) {

        repositorio.eliminarTrabalho(idUtilizador, dataTrabalho);

        for(int index = 0; index < trabalho.size(); ++index){

            inserirTarefas(data, trabalho.get(index));
            atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_DADOS, "Tarefa " + (index + 1), (index + 1), trabalho.size());
        }

//        for (SessaoResposta.TrabalhoInfo tarefa : trabalho) {
//            inserirTarefas(data, tarefa);
//        }

        atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_DOWNLOAD_CONCLUIDO);
    }

}
