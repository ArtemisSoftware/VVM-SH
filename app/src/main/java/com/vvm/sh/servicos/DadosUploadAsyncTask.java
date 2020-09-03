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
import com.vvm.sh.api.DadosFormulario;
import com.vvm.sh.api.Email;
import com.vvm.sh.api.Formando;
import com.vvm.sh.api.FormandoResultado_;
import com.vvm.sh.api.Imagem;
import com.vvm.sh.api.Ocorrencia;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.CrossSellingResultado;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.repositorios.TransferenciasRepositorio;
import com.vvm.sh.baseDados.entidades.AnomaliaResultado;
import com.vvm.sh.baseDados.entidades.OcorrenciaResultado;
import com.vvm.sh.ui.transferencias.modelos.DadosUpload;
import com.vvm.sh.ui.transferencias.modelos.Upload;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.constantes.AppConfig;
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
    private List<Integer> idImagens;
    private DadosUpload dadosUpload;

    private AtualizacaoUI atualizacaoUI;


    public DadosUploadAsyncTask(VvmshBaseDados vvmshBaseDados, Handler handler, TransferenciasRepositorio repositorio, String idUtilizador){
        this.vvmshBaseDados = vvmshBaseDados;
        this.repositorio = repositorio;
        this.idUtilizador = idUtilizador;
        this.idImagens = new ArrayList<>();
        this.dadosUpload = new DadosUpload(idUtilizador);
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

                    obterDados(resposta);
                    obterImagens();

                    atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_UPLOAD_CONCLUIDO, dadosUpload);
                    
                }
                catch(SQLiteConstraintException throwable){
                    errorMessage = throwable.getMessage();
                }
            }
        });


        return null;
    }

    /**
     * Metodo que permite obter os dados
     * @param resposta
     */
    private void obterDados(List<Upload> resposta) {

        int posicao = 0;

        for(Upload upload : resposta) {

            ++posicao;
            DadosFormulario dadosFormulario = new DadosFormulario();

            int index = 0;

            for (Resultado resultado : upload.resultados) {

                ++index;

                atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_DADOS, resultado.id + " - recarregarTipos", index, upload.resultados.size());

                switch (resultado.id){

                    case ID_EMAIL:

                        dadosFormulario.fixarEmail(adicionarEmail(resultado.idTarefa));
                        break;

                    case ID_ANOMALIA_CLIENTE:

                        dadosFormulario.fixarAnomalias(adicionarAnomaliaCliente(resultado.idTarefa));
                        break;


                    case ID_CROSS_SELLING:

                        dadosFormulario.fixarCrossSelling(adicionarCrossSelling(resultado.idTarefa));
                        break;


                    case ID_OCORRENCIA:

                        dadosFormulario.fixarOcorrencias(adicionarOcorrencias(resultado.idTarefa));
                        break;


                    case ID_ATIVIDADE_PENDENTE:

                        dadosFormulario.fixarAtividadesPendentes(adicionarAtividadesPendentes(resultado.idTarefa));
                        break;


                    default:
                        break;
                }
            }

            dadosFormulario.idUtilizador = idUtilizador;
            dadosFormulario.id = UploadMapping.INSTANCE.map(repositorio.obterTarefa(upload.tarefa.idTarefa));

            dadosUpload.fixarDados(dadosFormulario);

            atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_DADOS, "Tarefa: " + upload.tarefa.idTarefa, posicao, resposta.size());
        }
    }


    /**
     * Metodo que permite obter as imagens
     */
    private void obterImagens() {

        List<Imagem> registos = new ArrayList<>();

        for (ImagemResultado imagem : repositorio.obterImagens(idImagens)) {

            Imagem registo = UploadMapping.INSTANCE.map(imagem);
            registos.add(registo);
        }


        int totalBytesBoco = 0;
        List<Imagem> jaBloco =  new ArrayList<>();

        for (int index = 0; index < registos.size(); ++index) {

            Imagem imagem = registos.get(index);

            int numeroBytes = imagem.foto.length();
            totalBytesBoco += numeroBytes;

            if (totalBytesBoco > AppConfig.LIMITE_BYTES_FICHEIRO_JSON) {

                dadosUpload.fixarImagens(jaBloco);
                atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_DADOS, "Bloco de imagem " + dadosUpload.imagens.size() + "(" + totalBytesBoco + ")", index + 1, registos.size());

                jaBloco = new ArrayList<>();
                totalBytesBoco = 0;
            }

            jaBloco.add(imagem);

            if (index + 1 >= registos.size()) {
                dadosUpload.fixarImagens(jaBloco);
                atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_DADOS, "Bloco de imagem " + dadosUpload.imagens.size() + "(" + totalBytesBoco + ")", index + 1, registos.size());
            }
        }
    }




    /**
     * Metodo que permite obter a acao de formacao
     * @param idAtividade o identificador da atividade
     * @return uma acao de formacao
     */
    private AcaoFormacao obterAcaoFormacao(int idAtividade){

        AcaoFormacao acaoFormacao = UploadMapping.INSTANCE.map(repositorio.obterAcaoFormacao(idAtividade));

        if(acaoFormacao == null){
            return null;
        }

        List<Formando> registos = new ArrayList<>();

        for (FormandoResultado_ item : repositorio.obterFormandos(idAtividade)) {

            Formando registo = UploadMapping.INSTANCE.map(item.resultado);
            registo.album = new ArrayList<>();
            registo.album.add(item.idImagem + "");
            registos.add(registo);

            idImagens.add(item.idImagem);
        }

        acaoFormacao.formandos = registos;
        return acaoFormacao;
    }


    /**
     * Metodo que permite obter as atividades pendentes
     * @param idTarefa o identificador da tarefa
     * @return uma lista de atividades
     */
    private List<AtividadePendente> adicionarAtividadesPendentes(int idTarefa) {

        List<AtividadePendente> registos = new ArrayList<>();

        for (AtividadePendenteResultado_ item : repositorio.obterAtividadesPendentes(idTarefa)) {

            if(item.resultado.idEstado == Identificadores.Estados.ESTADO_EXECUTADO){

                AtividadePendenteExecutada registo = UploadMapping.INSTANCE.mapeamento(item);

                //if(item.atividade.formacao == true){
                    registo.formacao = obterAcaoFormacao(item.resultado.id);
                //}

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


    /**
     * Metodo que permite obter o cross Selling
     * @param idTarefa o identficador da tarefa
     * @return o cross selling
     */
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


    /**
     * Metodo que permite adicionar as anomalias de um cliente
     * @param idTarefa o identificador da tarefa
     * @return os dados das anomalias
     */
    private List<Anomalia> adicionarAnomaliaCliente(int idTarefa) {

        List<Anomalia> registos = new ArrayList<>();

        for (AnomaliaResultado item : repositorio.obterAnomalias(idTarefa)) {
            registos.add(UploadMapping.INSTANCE.map(item));
        }

        return registos;
    }


    /**
     * Metodo que permite adicionar um email
     * @param idTarefa o identificador da tarefa
     * @return os dados do email
     */
    private Email adicionarEmail(int idTarefa) {

        Email email = UploadMapping.INSTANCE.map(repositorio.obterEmail(idTarefa));
        return email;
    }

}
