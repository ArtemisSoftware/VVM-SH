package com.vvm.sh.servicos;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Handler;

import com.vvm.sh.api.modelos.pedido.IAtividadeExecutada;
import com.vvm.sh.api.modelos.pedido.IAnomalia;
import com.vvm.sh.api.modelos.pedido.IAtividadePendente;
import com.vvm.sh.api.modelos.pedido.ICliente;
import com.vvm.sh.api.modelos.pedido.ITarefa;
import com.vvm.sh.api.modelos.pedido.IDados;
import com.vvm.sh.api.modelos.pedido.IOcorrencia;
import com.vvm.sh.api.modelos.SessaoResposta;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.baseDados.entidades.Anomalia;
import com.vvm.sh.baseDados.entidades.AtividadeExecutada;
import com.vvm.sh.baseDados.entidades.AtividadePendente;
import com.vvm.sh.baseDados.entidades.Cliente;
import com.vvm.sh.baseDados.entidades.Ocorrencia;
import com.vvm.sh.baseDados.entidades.OcorrenciaHistorico;
import com.vvm.sh.repositorios.TransferenciasRepositorio;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.mapeamento.DownloadMapping;
import com.vvm.sh.util.metodos.ConversorUtil;
import com.vvm.sh.util.metodos.DatasUtil;

import java.util.ArrayList;
import java.util.List;

public class TrabalhoAsyncTask extends AsyncTask<SessaoResposta, Void, Void> {

    protected String errorMessage, idUtilizador;
    private VvmshBaseDados vvmshBaseDados;
    protected TransferenciasRepositorio repositorio;
    protected AtualizacaoUI atualizacaoUI;

    public TrabalhoAsyncTask(VvmshBaseDados vvmshBaseDados, TransferenciasRepositorio repositorio, Handler handler, String idUtilizador){
        this.vvmshBaseDados = vvmshBaseDados;
        this.repositorio = repositorio;
        this.idUtilizador = idUtilizador;
        atualizacaoUI = new AtualizacaoUI(handler);
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

                    inserirTrabalho(trabalho, data);

                }
                catch(SQLiteConstraintException throwable){
                    errorMessage = throwable.getMessage();
                }
            }
        });

        return null;
    }


    /**
     * Metodo que permite inserir as tarefas existentes no trabalho
     * @param trabalho os dados do trabalho
     * @param data a data do trabalho
     */
    protected void inserirTrabalho(List<SessaoResposta.TrabalhoInfo> trabalho, String data) {


        for(int index = 0; index < trabalho.size(); ++index){

            inserirTarefas(data, trabalho.get(index));
            atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_DADOS, "Tarefa " + (index + 1), (index + 1), trabalho.size());
        }

        atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_DOWNLOAD_CONCLUIDO);

    }


    /**
     * Metodo que permite inserir uma tarefa
     * @param data a data do trabalho
     * @param info os dados da tarefa
     */
    protected void inserirTarefas(String data, SessaoResposta.TrabalhoInfo info) {

        int idTarefa = inserirTarefa (info.tarefas.dados, data);

        inserirAtividadesExecutadas(info.tarefas.atividadesExecutadas, idTarefa);

        inserirCliente(info.tarefas.cliente, info.tarefas.dados, info.tarefas, idTarefa);

        inserirAnomalias(info.tarefas.anomalias, idTarefa);

        inserirOcorrencias(info.tarefas.ocorrencias, idTarefa);

        inserirAtividadesPendentes(info.tarefas.atividadesPendentes, idTarefa);
    }


    /**
     * Metodo que permite inserir as atividades pendentes
     * @param atividadesPendentes os dados das atividades pendentes
     * @param idTarefa o identificador da tarefa
     */
    private void inserirAtividadesPendentes(List<IAtividadePendente> atividadesPendentes, int idTarefa) {

        List<AtividadePendente> registos = new ArrayList<>();

        for(IAtividadePendente IAtividadePendente : atividadesPendentes){

            AtividadePendente registo = DownloadMapping.INSTANCE.map(IAtividadePendente);
            registo.formacao = ConversorUtil.converter_Integer_Para_Boolean(IAtividadePendente.formacao);
            registo.idTarefa = idTarefa;
            registos.add(registo);
        }

        repositorio.inserirAtividadesPendentes(registos);
    }


    /**
     * Metodo que permite inserir as ocorrencias
     * @param ocorrencias os dados das ocorrencias
     * @param idTarefa o identificador da tarefa
     */
    private void inserirOcorrencias(List<IOcorrencia> ocorrencias, int idTarefa) {

        for(IOcorrencia ocorrenciaResultado : ocorrencias){

            Ocorrencia registo = DownloadMapping.INSTANCE.map(ocorrenciaResultado);
            registo.idTarefa = idTarefa;
            int idOcorrencia = (int) repositorio.inserirOcorrencia(registo);

            List<OcorrenciaHistorico> registos = new ArrayList<>();

            for(IOcorrencia.IOcorrenciaHistorico IOcorrenciaHistorico : ocorrenciaResultado.historico){

                OcorrenciaHistorico item = DownloadMapping.INSTANCE.map(IOcorrenciaHistorico);
                item.idOcorrencia = idOcorrencia;
                registos.add(item);
            }

            repositorio.inserirHistoricoOcorrencias(registos);
        }
    }


    /**
     * Metodo que permite inserir anomalias
     * @param anomalias uma lista de anomalias
     * @param idTarefa o identificador da tarefa
     */
    private void inserirAnomalias(List<IAnomalia> anomalias, int idTarefa) {

        List<Anomalia> registos = new ArrayList<>();

        for(IAnomalia anomaliaResultado : anomalias){

            Anomalia registo = DownloadMapping.INSTANCE.map(anomaliaResultado);
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
    private void inserirCliente(ICliente cliente, IDados dados, ITarefa tarefa, int idTarefa) {

        Cliente registo = DownloadMapping.INSTANCE.map(cliente, dados, tarefa);
        registo.idTarefa = idTarefa;

        repositorio.inserirCliente(registo);
    }


    /**
     * Metodo que permite inserir as atividades executadas
     * @param atividades lista de atividades executadas
     * @param idTarefa o identificador da tarefa
     */
    private void inserirAtividadesExecutadas(List<IAtividadeExecutada> atividades, int idTarefa) {

        List<AtividadeExecutada> atividadesExecutadas = new ArrayList<>();

        for(IAtividadeExecutada IAtividadeExecutada : atividades){

            AtividadeExecutada atividadeExecutada = DownloadMapping.INSTANCE.map(IAtividadeExecutada);
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
    private int inserirTarefa(IDados dados, String data){

        Tarefa tarefa = DownloadMapping.INSTANCE.map(dados);
        tarefa.data = DatasUtil.converterString(data, DatasUtil.FORMATO_YYYY_MM_DD);
        tarefa.idUtilizador = idUtilizador;
        tarefa.app = Identificadores.App.APP_SA; //TODO: mudar isto consoante SA ou SHT

        return (int) repositorio.inserirTarefa(tarefa);
    }


}
