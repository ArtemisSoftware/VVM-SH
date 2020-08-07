package com.vvm.sh.servicos;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Handler;

import com.vvm.sh.api.AcaoFormacao;
import com.vvm.sh.api.Anomalia;
import com.vvm.sh.api.AtividadePendente;
import com.vvm.sh.api.AtividadePendenteExecutada;
import com.vvm.sh.api.AtividadePendenteNaoExecutada;
import com.vvm.sh.api.AtividadePendenteResultado_;
import com.vvm.sh.api.CrossSelling;
import com.vvm.sh.api.DadosFormularios;
import com.vvm.sh.api.Email;
import com.vvm.sh.api.Formando;
import com.vvm.sh.api.Ocorrencia;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.CrossSellingResultado;
import com.vvm.sh.baseDados.entidades.FormandoResultado;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.repositorios.TransferenciasRepositorio;
import com.vvm.sh.baseDados.entidades.AnomaliaResultado;
import com.vvm.sh.baseDados.entidades.OcorrenciaResultado;
import com.vvm.sh.ui.transferencias.modelos.Upload;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.mapeamento.UploadMapping;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import static com.vvm.sh.util.constantes.Identificadores.Resultados.*;

public class DadosUploadAsyncTask  extends AsyncTask<List<Upload>, Void, Void> {

    private String errorMessage, idUtilizador;
    private VvmshBaseDados vvmshBaseDados;
    private TransferenciasRepositorio repositorio;
    private JSONArray dadosTarefas = new JSONArray();

    private AtualizacaoUI atualizacaoUI;


    public DadosUploadAsyncTask(VvmshBaseDados vvmshBaseDados, Handler handler, TransferenciasRepositorio repositorio, String idUtilizador){
        this.vvmshBaseDados = vvmshBaseDados;
        this.repositorio = repositorio;
        this.idUtilizador = idUtilizador;
        atualizacaoUI = new AtualizacaoUI(handler);
    }

    @Override
    protected Void doInBackground(List<Upload>... resultados) {

        if(resultados[0] == null)
            return null;

        List<Upload> resposta = resultados[0];


        this.vvmshBaseDados.runInTransaction(new Runnable(){
            @Override
            public void run(){

                try {

                    int posicao = 0;

                    for(Upload upload : resposta) {

                        ++posicao;
                        DadosFormularios dadosFormularios = new DadosFormularios();

                        int index = 0;

                        for (Resultado resultado : upload.resultados) {

                            ++index;

                            atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_DADOS, resultado.id + " - recarregarTipos", index, upload.resultados.size());

                            switch (resultado.id){

                                case ID_EMAIL:

                                    dadosFormularios.fixarEmail(adicionarEmail(resultado.idTarefa));
                                    break;

                                case ID_ANOMALIA_CLIENTE:

                                    dadosFormularios.fixarAnomalias(adicionarAnomaliaCliente(resultado.idTarefa));
                                    break;


                                case ID_CROSS_SELLING:

                                    dadosFormularios.fixarCrossSelling(adicionarCrossSelling(resultado.idTarefa));
                                    break;


                                case ID_OCORRENCIA:

                                    dadosFormularios.fixarOcorrencias(adicionarOcorrencias(resultado.idTarefa));
                                    break;


                                case ID_ATIVIDADE_PENDENTE:

                                    dadosFormularios.fixarAtividadesPendentes(adicionarAtividadesPendentes(resultado.idTarefa));
                                    break;


                                default:
                                    break;
                            }
                        }

                        dadosFormularios.idUtilizador = idUtilizador;
                        dadosFormularios.id = UploadMapping.INSTANCE.map(repositorio.obterTarefa(upload.tarefa.idTarefa));

                        atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_DADOS, upload.tarefa.idTarefa + " - res", posicao, resposta.size());
                    }


                }
                catch(SQLiteConstraintException throwable){
                    errorMessage = throwable.getMessage();
                }
            }
        });


        return null;
    }


    private AcaoFormacao obterAcaoFormacao(int idAtividade){

        AcaoFormacao acaoFormacao = UploadMapping.INSTANCE.map(repositorio.obterAcaoFormacao(idAtividade));

        if(acaoFormacao == null){
            return null;
        }

        List<Formando> registos = new ArrayList<>();

        for (FormandoResultado item : repositorio.obterFormandos(idAtividade)) {
            registos.add(UploadMapping.INSTANCE.map(item));
        }

        acaoFormacao.formandos = registos;
        return acaoFormacao;
    }


    private List<AtividadePendente> adicionarAtividadesPendentes(int idTarefa) {

        List<AtividadePendente> registos = new ArrayList<>();

        for (AtividadePendenteResultado_ item : repositorio.obterAtividadesPendentes(idTarefa)) {

            if(item.resultado.idEstado == Identificadores.Estados.ESTADO_EXECUTADO){

                AtividadePendenteExecutada registo = UploadMapping.INSTANCE.mapeamento(item);

                if(item.atividade.formacao == true){
                    registo.formacao = obterAcaoFormacao(item.resultado.id);
                }

                registos.add(registo);
            }
            else {
                AtividadePendenteNaoExecutada registo = UploadMapping.INSTANCE.map(item);
                registos.add(registo);
            }
        }

        return registos;
    }


    private List<Ocorrencia> adicionarOcorrencias(int idTarefa) {

        List<Ocorrencia> registos = new ArrayList<>();

        for (OcorrenciaResultado item : repositorio.obterOcorrencias(idTarefa)) {

            Ocorrencia registo = UploadMapping.INSTANCE.map(item);
            registos.add(registo);
        }

        return registos;
    }



    private List<CrossSelling> adicionarCrossSelling(int idTarefa) {

        List<CrossSelling> registos = new ArrayList<>();

        for (CrossSellingResultado item : repositorio.obterCrossSelling(idTarefa)) {

            CrossSelling registo = UploadMapping.INSTANCE.map(item);

            if(registo.idDimensao.equals("0") == true){
                registo.idDimensao = "";
                registo.idTipo = "";
            }

            registos.add(registo);
        }

        return registos;
    }



    private List<Anomalia> adicionarAnomaliaCliente(int idTarefa) {

        List<Anomalia> registos = new ArrayList<>();

        for (AnomaliaResultado item : repositorio.obterAnomalias(idTarefa)) {
            registos.add(UploadMapping.INSTANCE.map(item));
        }

        return registos;
    }


    private Email adicionarEmail(int idTarefa) {

        Email email = UploadMapping.INSTANCE.map(repositorio.obterEmail(idTarefa));
        return email;
    }
}
