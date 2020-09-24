package com.vvm.sh.servicos;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Handler;

import com.vvm.sh.api.modelos.pedido.IAtividadeExecutada;
import com.vvm.sh.api.modelos.pedido.IAnomalia;
import com.vvm.sh.api.modelos.pedido.IAtividadePendente;
import com.vvm.sh.api.modelos.pedido.ICliente;
import com.vvm.sh.api.modelos.pedido.IColaborador;
import com.vvm.sh.api.modelos.pedido.IMorada;
import com.vvm.sh.api.modelos.pedido.IParqueExtintor;
import com.vvm.sh.api.modelos.pedido.ITarefa;
import com.vvm.sh.api.modelos.pedido.IDados;
import com.vvm.sh.api.modelos.pedido.IOcorrencia;
import com.vvm.sh.api.modelos.pedido.ISessao;
import com.vvm.sh.api.modelos.pedido.ITipoExtintor;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Colaborador;
import com.vvm.sh.baseDados.entidades.Morada;
import com.vvm.sh.baseDados.entidades.ParqueExtintor;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.baseDados.entidades.Anomalia;
import com.vvm.sh.baseDados.entidades.AtividadeExecutada;
import com.vvm.sh.baseDados.entidades.AtividadePendente;
import com.vvm.sh.baseDados.entidades.Cliente;
import com.vvm.sh.baseDados.entidades.Ocorrencia;
import com.vvm.sh.baseDados.entidades.OcorrenciaHistorico;
import com.vvm.sh.baseDados.entidades.TipoExtintor;
import com.vvm.sh.repositorios.TransferenciasRepositorio;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.mapeamento.DownloadMapping;
import com.vvm.sh.util.metodos.ConversorUtil;
import com.vvm.sh.util.metodos.DatasUtil;

import java.util.ArrayList;
import java.util.List;

public class TrabalhoAsyncTask extends AsyncTask<ISessao, Void, Void> {

    protected String errorMessage, idUtilizador;
    private VvmshBaseDados vvmshBaseDados;
    protected TransferenciasRepositorio repositorio;
    protected AtualizacaoUI atualizacaoUI;
    private int api;

    public TrabalhoAsyncTask(VvmshBaseDados vvmshBaseDados, TransferenciasRepositorio repositorio, Handler handler, String idUtilizador){
        this.vvmshBaseDados = vvmshBaseDados;
        this.repositorio = repositorio;
        this.idUtilizador = idUtilizador;
        atualizacaoUI = new AtualizacaoUI(handler);
    }


    @Override
    protected Void doInBackground(ISessao... sessoes) {

        if(sessoes[0] == null)
            return null;

        ISessao resposta = sessoes[0];

        api = resposta.api;
        String data = resposta.sessoes.get(0).data;
        List<ISessao.TrabalhoInfo> trabalho = resposta.sessoes.get(0).trabalho;

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
    protected void inserirTrabalho(List<ISessao.TrabalhoInfo> trabalho, String data) {


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
    protected void inserirTarefas(String data, ISessao.TrabalhoInfo info) {


        int idTarefa = inserirTarefa (info.tarefas.dados, data);

        inserirAtividadesExecutadas(info.tarefas.atividadesExecutadas, idTarefa);

        inserirCliente(info.tarefas.cliente, info.tarefas.dados, info.tarefas, idTarefa);

        inserirAnomalias(info.tarefas.anomalias, idTarefa);

        inserirOcorrencias(info.tarefas.ocorrencias, idTarefa);

        inserirAtividadesPendentes(info.tarefas.atividadesPendentes, idTarefa);

        inserirMoradas(info.tarefas.moradas, info.tarefas.moradasExtintores, idTarefa);

        inserirParqueExtintores(info.tarefas.parqueExtintores, info.tarefas.tiposExtintor, idTarefa);

        inserirQuadroPessoal(info.tarefas.quadroPessoal, idTarefa);


    }

    private void inserirQuadroPessoal(List<IColaborador> quadroPessoal, int idTarefa) {

        List<Colaborador> registos = new ArrayList<>();

        for(IColaborador item : quadroPessoal){

            Colaborador registo = DownloadMapping.INSTANCE.map(item);
            registo.idTarefa = idTarefa;
            registos.add(registo);
        }

        repositorio.inserirQuadroPessoal(registos);

    }


    /**
     * Metodo que permite inserir o parque extintores
     * @param parqueExtintores os dados do parque
     * @param tiposExtintor os tipos do extintor
     * @param idTarefa o identificador da tarefa
     */
    private void inserirParqueExtintores(List<IParqueExtintor> parqueExtintores, List<ITipoExtintor> tiposExtintor, int idTarefa) {

        List<TipoExtintor> registos = new ArrayList<>();

        for(ITipoExtintor item : tiposExtintor){

            TipoExtintor registo = DownloadMapping.INSTANCE.map(item);
            registo.idTarefa = idTarefa;
            registos.add(registo);
        }

        repositorio.inserirTipoExtintor(registos);


        List<ParqueExtintor> registosExtintores = new ArrayList<>();

        for(IParqueExtintor item : parqueExtintores){

            ParqueExtintor registo = DownloadMapping.INSTANCE.map(item);
            registo.idTarefa = idTarefa;
            registosExtintores.add(registo);
        }

        repositorio.inserirParqueExtintores(registosExtintores);

    }


    /**
     * Metodo que permite inserir as moradas
     * @param moradas dados das moradas
     * @param moradasExtintores dados das moradas dos extintores
     * @param idTarefa o identificador da tarefa
     */
    private void inserirMoradas(List<IMorada> moradas, List<IMorada> moradasExtintores, int idTarefa) {

        List<Morada> registos = new ArrayList<>();

        for(IMorada morada : moradas){

            Morada registo = DownloadMapping.INSTANCE.map(morada);
            registo.tipo = Identificadores.Origens.ORIGEM_MORADA;
            registo.idTarefa = idTarefa;
            registos.add(registo);
        }

        for(IMorada morada : moradasExtintores){

            Morada registo = DownloadMapping.INSTANCE.map(morada);
            registo.tipo = Identificadores.Origens.ORIGEM_MORADA_EXTINTOR;
            registo.idTarefa = idTarefa;
            registos.add(registo);
        }
        
        repositorio.inserirMoradas(registos);
    }
    
    /**
     * Metodo que permite inserir as atividades pendentes
     * @param atividadesPendentes os dados das atividades pendentes
     * @param idTarefa o identificador da tarefa
     */
    private void inserirAtividadesPendentes(List<IAtividadePendente> atividadesPendentes, int idTarefa) {

        List<AtividadePendente> registos = new ArrayList<>();

        for(IAtividadePendente atividadePendente : atividadesPendentes){

            AtividadePendente registo = DownloadMapping.INSTANCE.map(atividadePendente);

            try {
                registo.idChecklist = Integer.parseInt(atividadePendente.idChecklist);
            }
            catch (NumberFormatException e){}

            registo.idRelatorio = obterIdRelatorioAtividadePendente(atividadePendente);
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
        registo.emailAutenticado = ConversorUtil.converter_String_Para_Boolean(cliente.emailAutenticado);

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
        tarefa.api = api;

        return (int) repositorio.inserirTarefa(tarefa);
    }


    /**
     * Metodo que permite obter o identificador do relatorio da atividade pendente
     * @param atividadePendente os dados da atividade pendente
     * @return um identificador
     */
    private int obterIdRelatorioAtividadePendente(IAtividadePendente atividadePendente){

        if(atividadePendente.formacao == 1){
            return Identificadores.Relatorios.ID_RELATORIO_FORMACAO;
        }
        else if(atividadePendente.relatorioIluminacao == 1){
            return Identificadores.Relatorios.ID_RELATORIO_ILUMINACAO;
        }
        if(atividadePendente.relatorioTermico == 1){
            return Identificadores.Relatorios.ID_RELATORIO_TEMPERATURA_HUMIDADE;
        }
        if(atividadePendente.relatorioAvaliacaoRisco == 1){
            return Identificadores.Relatorios.ID_RELATORIO_AVALIACAO_RISCO;
        }

        return Identificadores.Relatorios.SEM_RELATORIO;

    }


}
