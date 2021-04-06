package com.vvm.sh.servicos.trabalho;

import com.vvm.sh.api.modelos.pedido.ISessao;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.DownloadTrabalhoRepositorio;
import com.vvm.sh.servicos.tipos.TrabalhoAsyncTask;
import com.vvm.sh.ui.transferencias.adaptadores.OnTransferenciaListener;
import com.vvm.sh.ui.transferencias.modelos.Sessao;
import com.vvm.sh.util.AtualizacaoUI_;

import java.util.List;

public class AtualizarTrabalhoAsyncTask__v2 extends TrabalhoAsyncTask {



    public AtualizarTrabalhoAsyncTask__v2(OnTransferenciaListener listener, VvmshBaseDados vvmshBaseDados, DownloadTrabalhoRepositorio repositorio, String idUtilizador){
        super(listener, vvmshBaseDados, repositorio, idUtilizador);
    }



    /**
     * Metodo que permite inserir as tarefas existentes no trabalho
     * @param trabalho os dados do trabalho
     * @param data a data do trabalho
     */
    protected void inserirTrabalho(List<ISessao.TrabalhoInfo> trabalho, String data, int api) {

        for(int index = 0; index < trabalho.size(); ++index){

            inserirTarefas(data, trabalho.get(index), api);
            listener.atualizarTransferencia(new AtualizacaoUI_(AtualizacaoUI_.Estado.PROCESSAMENTO_TRABALHO, (index + 1), trabalho.size(), "Tarefa " + (index + 1)));
        }
    }



}
