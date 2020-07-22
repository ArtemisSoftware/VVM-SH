package com.vvm.sh.ui.agenda.servicos;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import com.vvm.sh.api.modelos.AnomaliaResultado;
import com.vvm.sh.api.modelos.AtividadeExecutadasResultado;
import com.vvm.sh.api.modelos.ClienteResultado;
import com.vvm.sh.api.modelos.DadosResultado;
import com.vvm.sh.api.modelos.SessaoResposta;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.AgendaRepositorio;
import com.vvm.sh.ui.agenda.modelos.Tarefa;
import com.vvm.sh.ui.anomalias.modelos.Anomalia;
import com.vvm.sh.ui.atividadesExecutadas.modelos.AtividadeExecutada;
import com.vvm.sh.ui.cliente.Cliente;
import com.vvm.sh.util.mapeamento.ModelMapping;
import com.vvm.sh.util.metodos.DatasUtil;

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

                        int idTarefa = inserirTarefa (info.tarefas.dados, data);

                        inserirAtividadesExecutadas(info.tarefas.atividadesExecutadas, idTarefa);

                        inserirCliente(info.tarefas.cliente, info.tarefas.dados, idTarefa);

                        inserirAnomalias(info.tarefas.anomalias, idTarefa);
                    }

                }
                catch(SQLiteConstraintException throwable){
                    errorMessage = throwable.getMessage();
                }
            }
        });

        return null;
    }


    /**
     * Metodo que permite inserir anomalias
     * @param anomalias uma lista de anomalias
     * @param idTarefa o identificador da tarefa
     */
    private void inserirAnomalias(List<AnomaliaResultado> anomalias, int idTarefa) {

        List<Anomalia> registos = new ArrayList<>();

        for(AnomaliaResultado anomaliaResultado : anomalias){

            Anomalia registo = ModelMapping.INSTANCE.map(anomaliaResultado);
            registo.idTarefa = idTarefa;
            registos.add(registo);
        }

        repositorio.inserirAnomalias(registos);

    }


    /**
     * Metodo que permite inserir um cliente
     * @param cliente os dados do cliente
     * @param dados os dados da tarefa
     * @param idTarefa o identificador da tarefa
     */
    private void inserirCliente(ClienteResultado cliente, DadosResultado dados, int idTarefa) {

        Cliente registo = ModelMapping.INSTANCE.map(cliente,dados);
        registo.idTarefa = idTarefa;

        repositorio.inserirCliente(registo);
    }


    /**
     * Metodo que permite inserir as atividades executadas
     * @param atividades lista de atividades executadas
     * @param idTarefa o identificador da tarefa
     */
    private void inserirAtividadesExecutadas(List<AtividadeExecutadasResultado> atividades, int idTarefa) {

        List<AtividadeExecutada> atividadesExecutadas = new ArrayList<>();

        for(AtividadeExecutadasResultado atividadeExecutadasResultado : atividades){

            AtividadeExecutada atividadeExecutada = ModelMapping.INSTANCE.map(atividadeExecutadasResultado);
            atividadeExecutada.idTarefa = idTarefa;
            atividadesExecutadas.add(atividadeExecutada);
        }

        repositorio.inserirAtividadesExecutadas(atividadesExecutadas);
    }


    /**
     * Metodo que permite inserir uma tarefa
     * @param dados os dados da tarefa
     * @param data a data (yyyy-MM-dd)
     * @return o identificador da tarefa inserida
     */
    private int inserirTarefa(DadosResultado dados, String data){

        Tarefa tarefa = ModelMapping.INSTANCE.map(dados);
        tarefa.data = DatasUtil.converterString(data, DatasUtil.FORMATO_YYYY_MM_DD);
        tarefa.idUtilizador = idUtilizador;

        return (int) repositorio.inserirTarefa(tarefa);
    }


}
