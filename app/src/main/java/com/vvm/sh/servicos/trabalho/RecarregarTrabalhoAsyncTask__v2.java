package com.vvm.sh.servicos.trabalho;

import com.vvm.sh.api.modelos.pedido.ISessao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.DownloadTrabalhoRepositorio;
import com.vvm.sh.servicos.tipos.TrabalhoAsyncTask;
import com.vvm.sh.ui.transferencias.adaptadores.OnTransferenciaListener;
import com.vvm.sh.ui.transferencias.modelos.Sessao;
import com.vvm.sh.util.AtualizacaoUI_;

import java.util.List;

public class RecarregarTrabalhoAsyncTask__v2 extends TrabalhoAsyncTask {

    private long dataTrabalho;
    private int api = 0;


    public RecarregarTrabalhoAsyncTask__v2(OnTransferenciaListener listener, VvmshBaseDados vvmshBaseDados, DownloadTrabalhoRepositorio repositorio, String idUtilizador, long dataTrabalho, int api) {
        super(listener, vvmshBaseDados, repositorio, idUtilizador);

        this.listener = listener;
        this.dataTrabalho = dataTrabalho;
        this.api = api;
    }

    @Override
    protected void inserirTrabalho(List<ISessao.TrabalhoInfo> trabalho, String data, int api) {

        repositorio.eliminarTrabalho(idUtilizador, dataTrabalho, api);

        for(int index = 0; index < trabalho.size(); ++index){

            inserirTarefas(data, trabalho.get(index), api);
            listener.atualizarTransferencia(new AtualizacaoUI_(AtualizacaoUI_.Estado.PROCESSAMENTO_TRABALHO, (index + 1), trabalho.size(), "Tarefa " + (index + 1)));
        }
    }


}
