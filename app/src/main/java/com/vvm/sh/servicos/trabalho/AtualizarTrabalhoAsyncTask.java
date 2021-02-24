package com.vvm.sh.servicos.trabalho;

import android.os.Handler;

import com.vvm.sh.api.modelos.pedido.ISessao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.TransferenciasRepositorio;
import com.vvm.sh.util.AtualizacaoUI;

import java.util.List;

public class AtualizarTrabalhoAsyncTask extends CarregarTrabalhoAsyncTask {

    private long dataTrabalho;

    public AtualizarTrabalhoAsyncTask(VvmshBaseDados vvmshBaseDados, TransferenciasRepositorio repositorio, Handler handler, String idUtilizador, long dataTrabalho) {
        super(vvmshBaseDados, repositorio, handler, idUtilizador);

        this.dataTrabalho = dataTrabalho;
    }


    @Override
    protected void inserirTrabalho(List<ISessao.TrabalhoInfo> trabalho, String data) {

        for(int index = 0; index < trabalho.size(); ++index){

            if(repositorio.existeTarefa(idUtilizador, trabalho.get(index).tarefas.dados,  this.dataTrabalho) == false) {

                inserirTarefas(data, trabalho.get(index));
                atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_TRABALHO, "Tarefa " + (index + 1), (index + 1), trabalho.size());
            }
        }

        atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_DOWNLOAD_CONCLUIDO);
    }

}
