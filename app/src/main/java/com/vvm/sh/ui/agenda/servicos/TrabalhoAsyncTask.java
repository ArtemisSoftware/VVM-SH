package com.vvm.sh.ui.agenda.servicos;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import com.vvm.sh.api.modelos.AtividadeExecutadasResultado;
import com.vvm.sh.api.modelos.SessaoResposta;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.AgendaRepositorio;
import com.vvm.sh.ui.agenda.modelos.Tarefa;
import com.vvm.sh.ui.atividadesExecutadas.AtividadeExecutada;
import com.vvm.sh.ui.cliente.Cliente;
import com.vvm.sh.util.mapeamento.ModelMapping;
import com.vvm.sh.util.metodos.Datas;

import java.util.ArrayList;
import java.util.List;

public class TrabalhoAsyncTask extends AsyncTask<SessaoResposta, Void, Void> {

    private String errorMessage, idUtilizador;
    private VvmshBaseDados vvmshBaseDados;
    private AgendaRepositorio repositorio;

    public TrabalhoAsyncTask(VvmshBaseDados vvmshBaseDados, AgendaRepositorio repositorio, String idUtilizador){
        this.vvmshBaseDados = vvmshBaseDados;
        this.repositorio = repositorio;
        this.idUtilizador = idUtilizador;
    }


    @Override
    protected Void doInBackground(SessaoResposta... sessaoRespostas) {


        if(sessaoRespostas[0] == null)
            return null;

        SessaoResposta resposta = sessaoRespostas[0];

        String data = resposta.sessoes.get(0).data;
        List<SessaoResposta.TrabalhoInfo> trabalho = resposta.sessoes.get(0).trabalho;

        this.vvmshBaseDados.runInTransaction(new Runnable(){
            @Override
            public void run(){

                try {


                    for (SessaoResposta.TrabalhoInfo info : trabalho) {

                        Tarefa tarefa = ModelMapping.INSTANCE.map(info.tarefas.dados);
                        tarefa.data = Datas.converterString(data, Datas.FORMATO_YYYY_MM_DD);
                        tarefa.idUtilizador = idUtilizador;
                        int idTarefa = 0;//--repositorio.inserirTarefa(tarefa);

                        List<AtividadeExecutada> atividadesExecutadas = new ArrayList<>();

                        for(AtividadeExecutadasResultado atividadeExecutadasResultado : info.tarefas.atividadesExecutadas){

                            AtividadeExecutada atividadeExecutada = ModelMapping.INSTANCE.map(atividadeExecutadasResultado);
                            atividadeExecutada.idTarefa = idTarefa;
                            atividadesExecutadas.add(atividadeExecutada);
                        }

                        //repositorio.inserirAtividadesExecutadas(atividadesExecutadas);

                        Cliente cliente = ModelMapping.INSTANCE.map(info.tarefas.cliente, info.tarefas.dados);
                        cliente.idTarefa = idTarefa;

                        //repositorio.inserirCliente(cliente);
                    }





                }
                catch(SQLiteConstraintException throwable){
                    errorMessage = throwable.getMessage();
                }
            }
        });

        return null;
    }
}
