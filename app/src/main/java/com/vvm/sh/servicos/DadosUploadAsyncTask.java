package com.vvm.sh.servicos;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Handler;

import com.vvm.sh.api.modelos.bd.AreaBd;
import com.vvm.sh.api.modelos.bd.CertificadoBd;
import com.vvm.sh.api.modelos.envio.AcaoFormacao;
import com.vvm.sh.api.modelos.envio.AtividadePendente;
import com.vvm.sh.api.modelos.envio.AvaliacaoRiscos;
import com.vvm.sh.api.modelos.envio.CertificadoTratamento;
import com.vvm.sh.api.modelos.envio.Checklist;
import com.vvm.sh.api.modelos.envio.CrossSelling;
import com.vvm.sh.api.modelos.envio.DadosFormulario;
import com.vvm.sh.api.modelos.envio.Email;
import com.vvm.sh.api.modelos.envio.Formando;
import com.vvm.sh.api.modelos.bd.FormandoBd;
import com.vvm.sh.api.modelos.envio.Imagem;
import com.vvm.sh.api.modelos.envio.ImagemChecklist;
import com.vvm.sh.api.modelos.envio.ItemSeccaoChecklist;
import com.vvm.sh.api.modelos.envio.Levantamento;
import com.vvm.sh.api.modelos.envio.Observacao;
import com.vvm.sh.api.modelos.envio.Ocorrencia;
import com.vvm.sh.api.modelos.envio.Pergunta;
import com.vvm.sh.api.modelos.envio.Risco;
import com.vvm.sh.api.modelos.envio.Seccao;
import com.vvm.sh.api.modelos.envio.TrabalhadorVulneravel;
import com.vvm.sh.api.modelos.envio.Ut;
import com.vvm.sh.baseDados.Conversor;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.CrossSellingResultado;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.baseDados.entidades.LevantamentoRiscoResultado;
import com.vvm.sh.baseDados.entidades.QuestionarioChecklistResultado;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.baseDados.entidades.RiscoResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.baseDados.entidades.TrabalhadorVulneravelResultado;
import com.vvm.sh.baseDados.entidades.OcorrenciaResultado;
import com.vvm.sh.repositorios.UploadRepositorio;
import com.vvm.sh.ui.transferencias.modelos.DadosUpload;
import com.vvm.sh.ui.transferencias.modelos.Upload;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.ResultadoId;
import com.vvm.sh.util.constantes.AppConfig;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.constantes.TiposConstantes;
import com.vvm.sh.util.mapeamento.UploadMapping;
import com.vvm.sh.util.metodos.ConversorUtil;
import com.vvm.sh.util.metodos.MensagensUtil;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


import static com.vvm.sh.util.constantes.Identificadores.Resultados.ID_OCORRENCIA;
import static com.vvm.sh.util.constantes.Identificadores.Resultados.ID_EMAIL;
import static com.vvm.sh.util.constantes.Identificadores.Resultados.ID_CROSS_SELLING;
import static com.vvm.sh.util.constantes.Identificadores.Resultados.ID_ATIVIDADE_PENDENTE;

public abstract class DadosUploadAsyncTask  extends AsyncTask<List<Upload>, Void, Void> {

    private String errorMessage;
    private VvmshBaseDados vvmshBaseDados;

    private JSONArray dadosTarefas = new JSONArray();

    protected UploadRepositorio repositorio;

    /**
     * variavel que contem os identificadores das imagens a fazer upload
     */
    protected List<Integer> idImagens;
    protected DadosUpload dadosUpload;
    protected String idUtilizador;

    private AtualizacaoUI atualizacaoUI;
    private MensagensUtil dialogo;


    public DadosUploadAsyncTask(VvmshBaseDados vvmshBaseDados, Handler handler, UploadRepositorio repositorio, String idUtilizador){
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

                atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_DADOS, ResultadoId.obterDescricao(resultado.id) , index, upload.resultados.size());



                switch (resultado.id){

                    case ID_EMAIL:

                        dadosFormulario.fixarEmail(obterEmail(resultado.idTarefa));
                        break;

                    case ID_CROSS_SELLING:

                        dadosFormulario.fixarCrossSelling(obterCrossSelling(resultado.idTarefa));
                        break;

                    case ID_OCORRENCIA:

                        dadosFormulario.fixarOcorrencias(obterOcorrencias(resultado.idTarefa));
                        break;


                    case ID_ATIVIDADE_PENDENTE:

                        dadosFormulario.fixarAtividadesPendentes(obterAtividadesPendentes(resultado.idTarefa));
                        break;



                    default:
                        break;
                }

                obterDados(dadosFormulario, resultado);
            }

            dadosFormulario.idUtilizador = idUtilizador;
            dadosFormulario.id = UploadMapping.INSTANCE.map(repositorio.obterTarefa(upload.tarefa.idTarefa));


            dadosUpload.fixarDados(dadosFormulario);
            atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_DADOS, "Tarefa: " + upload.tarefa.idTarefa, posicao, resposta.size());
        }

        camposAdicionais(resposta);


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
    protected AcaoFormacao obterAcaoFormacao(int idAtividade){

        AcaoFormacao acaoFormacao = UploadMapping.INSTANCE.map(repositorio.obterAcaoFormacao(idAtividade));

        if(acaoFormacao == null){
            return null;
        }

        List<Formando> registos = new ArrayList<>();

        for (FormandoBd item : repositorio.obterFormandos(idAtividade)) {

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
     * Metodo que permite obter as ocorrencias
     * @param idTarefa o identficador da tarefa
     * @return as ocorrencias
     */
    private List<Ocorrencia> obterOcorrencias(int idTarefa) {

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
    private List<CrossSelling> obterCrossSelling(int idTarefa) {

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
     * Metodo que permite obter um certificado de tratamento
     * @param idAtividade o identificador da atividade
     * @return um certificado de tratamento
     */
    protected CertificadoTratamento obterCertificadoTratamento(int idAtividade){

        CertificadoBd certificado = repositorio.obterCertificado(idAtividade);

        CertificadoTratamento certificadoTratamento = UploadMapping.INSTANCE.map(certificado.resultado);


        certificadoTratamento.praga = TiposConstantes.formatarTipo(certificado.resultado.idPraga, TiposConstantes.CertificadoTratamento.Pragas.PRAGAS);
        certificadoTratamento.produtoAplicado = TiposConstantes.formatarTipo(certificado.resultado.idProdutoAplicado, TiposConstantes.CertificadoTratamento.Produtos.PRODUTOS);
        certificadoTratamento.visita = TiposConstantes.formatarTipo(certificado.resultado.idVisita, TiposConstantes.CertificadoTratamento.Visitas.VISITAS);
        certificadoTratamento.avaliacaoCondicoesArmazenamento = TiposConstantes.formatarTipo(certificado.resultado.avaliacaoCondicoesArmazenamento, TiposConstantes.CertificadoTratamento.Avaliacoes.AVALIACOES);
        certificadoTratamento.avaliacaoCondicoesHigiene = TiposConstantes.formatarTipo(certificado.resultado.avaliacaoCondicoesHigiene, TiposConstantes.CertificadoTratamento.Avaliacoes.AVALIACOES);
        certificadoTratamento.avaliacaoManutencaoInstalacoes = TiposConstantes.formatarTipo(certificado.resultado.avaliacaoManutencaoInstalacoes, TiposConstantes.CertificadoTratamento.Avaliacoes.AVALIACOES);

        certificadoTratamento.observacaoProdutosEmGel = ConversorUtil.converter_Boolean_Para_Integer(certificado.resultado.observacaoProdutosEmGel);
        certificadoTratamento.observacaoVestigiosPragas = ConversorUtil.converter_Boolean_Para_Integer(certificado.resultado.observacaoVestigiosPragas);

        certificadoTratamento.album = new ArrayList<>();
        certificadoTratamento.album.add(certificado.idImagem + "");

        idImagens.add(certificado.idImagem);

        return certificadoTratamento;
    }



    //-----------------------
    //Metodos abstratos
    //-----------------------

    protected abstract void obterDados(DadosFormulario dadosFormulario, Resultado resultado);


    /**
     * Metodo que permite obter as atividades pendentes
     * @param idTarefa o identificador da tarefa
     * @return uma lista de atividades
     */
    protected abstract List<AtividadePendente> obterAtividadesPendentes(int idTarefa);

    /**
     * Metodo que permite obter um email
     * @param idTarefa o identificador da tarefa
     * @return os dados do email
     */
    protected abstract Email obterEmail(int idTarefa);


    /**
     * Metodo que permite adicionar campos adicionais
     * @param resposta
     */
    protected abstract void camposAdicionais(List<Upload> resposta);


}
