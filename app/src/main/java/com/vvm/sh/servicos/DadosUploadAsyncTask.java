package com.vvm.sh.servicos;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Handler;

import com.vvm.sh.api.modelos.bd.AreaBd;
import com.vvm.sh.api.modelos.bd.AtividadePlanoAcaoBd;
import com.vvm.sh.api.modelos.bd.ColaboradorBd;
import com.vvm.sh.api.modelos.bd.ExtintorBd;
import com.vvm.sh.api.modelos.bd.RegistoVisitaBd;
import com.vvm.sh.api.modelos.bd.RelatorioAmbientalBd;
import com.vvm.sh.api.modelos.envio.AcaoFormacao;
import com.vvm.sh.api.modelos.envio.Anomalia;
import com.vvm.sh.api.modelos.envio.AtividadePendente;
import com.vvm.sh.api.modelos.envio.AtividadePendenteExecutada;
import com.vvm.sh.api.modelos.envio.AtividadePendenteNaoExecutada;
import com.vvm.sh.api.modelos.bd.AtividadePendenteBd;
import com.vvm.sh.api.modelos.envio.AtividadePlanoAcao;
import com.vvm.sh.api.modelos.envio.AvaliacaoIluminacao;
import com.vvm.sh.api.modelos.envio.AvaliacaoRiscos;
import com.vvm.sh.api.modelos.envio.AvaliacaoTemperaturaHumidade;
import com.vvm.sh.api.modelos.envio.Checklist;
import com.vvm.sh.api.modelos.envio.Colaborador;
import com.vvm.sh.api.modelos.envio.CrossSelling;
import com.vvm.sh.api.modelos.envio.DadosFormulario;
import com.vvm.sh.api.modelos.envio.Email;
import com.vvm.sh.api.modelos.envio.Equipamento;
import com.vvm.sh.api.modelos.envio.Extintor;
import com.vvm.sh.api.modelos.envio.Formando;
import com.vvm.sh.api.modelos.bd.FormandoBd;
import com.vvm.sh.api.modelos.envio.Imagem;
import com.vvm.sh.api.modelos.envio.ItemSeccaoChecklist;
import com.vvm.sh.api.modelos.envio.Levantamento;
import com.vvm.sh.api.modelos.envio.Observacao;
import com.vvm.sh.api.modelos.envio.Ocorrencia;
import com.vvm.sh.api.modelos.envio.ParqueExtintor;
import com.vvm.sh.api.modelos.envio.Pergunta;
import com.vvm.sh.api.modelos.envio.RegistoVisita;
import com.vvm.sh.api.modelos.envio.RelatorioAmbiental;
import com.vvm.sh.api.modelos.envio.Risco;
import com.vvm.sh.api.modelos.envio.Seccao;
import com.vvm.sh.api.modelos.envio.Sinistralidade;
import com.vvm.sh.api.modelos.envio.TrabalhadorVulneravel;
import com.vvm.sh.api.modelos.envio.TrabalhoRealizado;
import com.vvm.sh.api.modelos.envio.Ut;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.AvaliacaoAmbientalResultado;
import com.vvm.sh.baseDados.entidades.CrossSellingResultado;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.baseDados.entidades.LevantamentoRiscoResultado;
import com.vvm.sh.baseDados.entidades.QuestionarioChecklistResultado;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.baseDados.entidades.RiscoResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.baseDados.entidades.TipoNovo;
import com.vvm.sh.baseDados.entidades.TrabalhadorVulneravelResultado;
import com.vvm.sh.baseDados.entidades.TrabalhoRealizadoResultado;
import com.vvm.sh.repositorios.TransferenciasRepositorio;
import com.vvm.sh.baseDados.entidades.AnomaliaResultado;
import com.vvm.sh.baseDados.entidades.OcorrenciaResultado;
import com.vvm.sh.repositorios.UploadRepositorio;
import com.vvm.sh.ui.transferencias.modelos.DadosUpload;
import com.vvm.sh.ui.transferencias.modelos.Upload;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.constantes.AppConfig;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.mapeamento.UploadMapping;
import com.vvm.sh.util.metodos.DatasUtil;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import static com.vvm.sh.util.constantes.Identificadores.Resultados.*;

public class DadosUploadAsyncTask  extends AsyncTask<List<Upload>, Void, Void> {

    private String errorMessage, idUtilizador;
    private VvmshBaseDados vvmshBaseDados;
    private UploadRepositorio repositorio;
    private JSONArray dadosTarefas = new JSONArray();
    private List<Integer> idImagens;
    private DadosUpload dadosUpload;

    private AtualizacaoUI atualizacaoUI;


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

                atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_DADOS, resultado.id + " - recarregarTipos", index, upload.resultados.size());

                switch (resultado.id){

                    case ID_EMAIL:

                        dadosFormulario.fixarEmail(obterEmail(resultado.idTarefa));
                        break;

                    case ID_ANOMALIA_CLIENTE:

                        dadosFormulario.fixarAnomalias(obterAnomaliaCliente(resultado.idTarefa));
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


                    case ID_REGISTO_VISITA:

                        dadosFormulario.registoVisita = obterRegistoVisita(resultado.idTarefa);
                        break;


                    case ID_PLANO_ACAO:

                        dadosFormulario.fixarAtividadePlanoAcao(obterPlanoAcao(resultado.idTarefa));
                        break;


                    case ID_SINISTRALIDADE:

                        dadosFormulario.sinistralidade = obterSinistralidade(resultado.idTarefa);
                        break;

                    case ID_QUADRO_PESSOAL:

                        dadosFormulario.fixarQuadroPessoal(obterQuadroPessoal(resultado.idTarefa));
                        break;


                    case ID_PARQUE_EXTINTOR:

                        dadosFormulario.parqueExtintor = obterParqueExtintor(resultado.idTarefa);
                        break;

                    default:
                        break;
                }
            }

            dadosFormulario.idUtilizador = idUtilizador;
            dadosFormulario.id = UploadMapping.INSTANCE.map(repositorio.obterTarefa(upload.tarefa.idTarefa));

            dadosUpload.fixarDados(dadosFormulario);
            dadosUpload.equipamentos = obterEquipamentos(upload.resultados);
            atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_DADOS, "Tarefa: " + upload.tarefa.idTarefa, posicao, resposta.size());
        }
    }

    private ParqueExtintor obterParqueExtintor(int idTarefa) {

        List<Extintor> registos = new ArrayList<>();

        for (ExtintorBd item : repositorio.obterParqueExtintor(idTarefa)) {

            Extintor registo = UploadMapping.INSTANCE.map(item.extintor, item.resultado);

            registos.add(registo);
        }

        ParqueExtintor parqueExtintor = new ParqueExtintor();
        parqueExtintor.extintores = registos;
        parqueExtintor.inalterado = repositorio.obterNumeroExtintoresInalterados(idTarefa) + "";
        return parqueExtintor;
    }

    private List<Equipamento> obterEquipamentos(List<Resultado> resultados) {

        List<Integer> ids = new ArrayList<>();
        List<Equipamento> registos = new ArrayList<>();

        for(Resultado item : resultados){
            ids.add(item.id);
        }

        for (TipoNovo item : repositorio.obterNovosEquipamentos(ids)) {

            Equipamento equipamento = UploadMapping.INSTANCE.map(item);
            equipamento.id = Identificadores.Codigos.EQUIPAMENTO + item.idProvisorio;
            equipamento.idUtilizador = idUtilizador;
            registos.add(equipamento);
        }

        return registos;
    }


    private List<Colaborador> obterQuadroPessoal(int idTarefa) {

        List<Colaborador> registos = new ArrayList<>();

        for (ColaboradorBd item : repositorio.obterQuadroPessoal(idTarefa)) {

            Colaborador colaborador = UploadMapping.INSTANCE.map(item);
            colaborador.dataAdmissao = DatasUtil.converterData(item.dataAdmissao, DatasUtil.FORMATO_YYYY_MM_DD);
            colaborador.dataNascimento = DatasUtil.converterData(item.dataNascimento, DatasUtil.FORMATO_YYYY_MM_DD);
            colaborador.dataAdmissaoFuncao = DatasUtil.converterData(item.dataAdmissaoFuncao, DatasUtil.FORMATO_YYYY_MM_DD);
            registos.add(colaborador);
        }

        return registos;
    }

    private Sinistralidade obterSinistralidade(int idTarefa) {

        return UploadMapping.INSTANCE.map(repositorio.obterSinistralidade(idTarefa));
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
     * Metodo que permite obter o registo de visita
     * @param idTarefa o identificador da tarefa
     * @return os dados do registo
     */
    private RegistoVisita obterRegistoVisita(int idTarefa){

        RegistoVisitaBd registo = repositorio.obterRegistoVisita(idTarefa);

        RegistoVisita registoVisita = UploadMapping.INSTANCE.map(registo.resultado);
        registoVisita.data = DatasUtil.obterDataAtual(DatasUtil.DATA_FORMATO_YYYY_MM_DD__HH_MM_SS);
        registoVisita.album = new ArrayList<>();
        registoVisita.album.add(registo.idImagem + "");
        idImagens.add(registo.idImagem);

        List<TrabalhoRealizado> registos = new ArrayList<>();

        for (TrabalhoRealizadoResultado item : repositorio.obterTrabalhoRealizado(idTarefa)) {

            TrabalhoRealizado trabalhoRealizado = UploadMapping.INSTANCE.map(item);
            registos.add(trabalhoRealizado);
        }

        registoVisita.trabalhosRealizados = registos;
        return registoVisita;

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


    private AvaliacaoRiscos obterAvaliacaoRiscos(int idTarefa, int idAtividade){

        AvaliacaoRiscos avaliacaoRiscos = new AvaliacaoRiscos();

        avaliacaoRiscos.checklist = obterChecklist(idAtividade);
        avaliacaoRiscos.trabalhadoresVulneraveis = obterTrabalhadoresVulneraveis(idAtividade);
        avaliacaoRiscos.equipamentos = repositorio.obterEquipamentos(idAtividade);
        avaliacaoRiscos.processoProdutivo = repositorio.obterProcessoProdutivo(idAtividade);
        avaliacaoRiscos.levantamentosRisco = obterLevantamentoRisco(idAtividade);
        avaliacaoRiscos.propostasPlanoAcao = repositorio.obterPropostaPlanoAcao(idAtividade);
        avaliacaoRiscos.capaRelatorio = repositorio.obterCapaRelatorio(idTarefa);
        return avaliacaoRiscos;
    }

    private List<TrabalhadorVulneravel> obterTrabalhadoresVulneraveis(int idAtividade) {

        List<TrabalhadorVulneravel> registos = new ArrayList<>();

        for(TrabalhadorVulneravelResultado item : repositorio.obterTrabalhadoresVulneraveis(idAtividade)){
            TrabalhadorVulneravel registo = UploadMapping.INSTANCE.map(item);

            registo.quantidadeHomens = repositorio.obterNumeroHomens_TrabalhadoresVulneraveis(item.id) + "";
            registo.quantidadeMulheres = repositorio.obterNumeroMulheres_TrabalhadoresVulneraveis(item.id) + "";

            registo.categoriasProfissionaisHomens = repositorio.obterCategoriasProfissionais_TrabalhadoresVulneraveis(item.id, Identificadores.Origens.VULNERABILIDADE_CATEGORIAS_PROFISSIONAIS_HOMENS);
            registo.categoriasProfissionaisMulheres = repositorio.obterCategoriasProfissionais_TrabalhadoresVulneraveis(item.id, Identificadores.Origens.VULNERABILIDADE_CATEGORIAS_PROFISSIONAIS_MULHERES);

            registos.add(registo);
        }

        return registos;
    }

    private List<Checklist> obterChecklist(int idAtividade) {

        int index = -1;
        List<Checklist> registos = new ArrayList<>();

        Tipo tipoChecklist = repositorio.obterChecklist(idAtividade);
        Checklist checklist = UploadMapping.INSTANCE.map(tipoChecklist);
        checklist.versao = checklist.versao.split(".json")[0].split("_")[2];

        checklist.areas = new ArrayList<>();

        for(AreaBd area : repositorio.obterAreas(idAtividade)){

            ++index;
            checklist.areas.add(UploadMapping.INSTANCE.map(area));
            checklist.areas.get(index).seccoes = new ArrayList<>();

            for(String idSeccao : repositorio.obterSeccoes(area.resultado.id)){

                List<ItemSeccaoChecklist> itens = new ArrayList<>();

                for(QuestionarioChecklistResultado item : repositorio.obterItens(area.resultado.id, idSeccao, Identificadores.Checklist.TIPO_QUESTAO)){
                    Pergunta pergunta = UploadMapping.INSTANCE.mapPerguntaChecklist(item);
                    itens.add(pergunta);
                }

                for(QuestionarioChecklistResultado item : repositorio.obterItens(area.resultado.id, idSeccao, Identificadores.Checklist.TIPO_UTS)){
                    Ut ut = UploadMapping.INSTANCE.mapUtChecklist(item);
                    itens.add(ut);
                }

                for(QuestionarioChecklistResultado item : repositorio.obterItens(area.resultado.id, idSeccao, Identificadores.Checklist.TIPO_OBSERVACOES)){
                    Observacao observacao = UploadMapping.INSTANCE.mapObservacaoChecklist(item);
                    itens.add(observacao);
                }

                for(ImagemResultado imagem : repositorio.obterImagens(area.resultado.id, Identificadores.Imagens.IMAGEM_CHECKLIST)){
                    //TODO: duvidas em relacao a isto, deveria ser uma lista de ids
                    //itens.add(UploadMapping.INSTANCE.mapImagemChecklist(imagem));
                    //idImagens.add(registo.idImagem);
                }

                checklist.areas.get(index).seccoes.add(new Seccao(idSeccao, itens));
            }

        }


        registos.add(checklist);
        return registos;

    }


    private RelatorioAmbiental obterRelatorioIluminacao(int idAtividade) {

        RelatorioAmbientalBd registo = repositorio.obterRelatorioIluminacao(idAtividade);
        RelatorioAmbiental relatorioAmbiental = UploadMapping.INSTANCE.map(registo);
        relatorioAmbiental.equipamento = Sintaxe.Palavras.EQUIPAMENTO_RELATORIO_ILUMINACAO;

        for(AvaliacaoAmbientalResultado item : repositorio.obterAvaliacoesAmbiental(registo.resultado.id)){

            List<Integer> categoriasProfissionais = repositorio.obterCategoriasProfissionais(item.id, Identificadores.Origens.ORIGEM_RELATORIO_ILUMINACAO);
            List<Integer> medidas = repositorio.obterMedidas(item.id, Identificadores.Origens.ORIGEM_RELATORIO_ILUMINACAO_MEDIDAS_RECOMENDADAS);

            AvaliacaoIluminacao avaliacao = UploadMapping.INSTANCE.mapeamentoIluminacao(item);
            avaliacao.categoriasProfissionais = categoriasProfissionais;
            avaliacao.medidasRecomendadas = medidas;

            relatorioAmbiental.avaliacoes.add(avaliacao);
        }

        return relatorioAmbiental;
    }

    private RelatorioAmbiental obterRelatorioTemperaturaHumidade(int idAtividade) {

        RelatorioAmbientalBd registo = repositorio.obterRelatorioTemperaturaHumidade(idAtividade);
        RelatorioAmbiental relatorioAmbiental = UploadMapping.INSTANCE.map(registo);
        relatorioAmbiental.equipamento = Sintaxe.Palavras.EQUIPAMENTO_RELATORIO_TEMPERATURA_HUMIDADE;

        for(AvaliacaoAmbientalResultado item : repositorio.obterAvaliacoesAmbiental(registo.resultado.id)){

            List<Integer> categoriasProfissionais = repositorio.obterCategoriasProfissionais(item.id, Identificadores.Origens.ORIGEM_RELATORIO_TEMPERATURA_HUMIDADE);
            List<Integer> medidas = repositorio.obterMedidas(item.id, Identificadores.Origens.ORIGEM_RELATORIO_TEMPERATURA_HUMIDADE_MEDIDAS_RECOMENDADAS);

            AvaliacaoTemperaturaHumidade avaliacao = UploadMapping.INSTANCE.map(item);
            avaliacao.categoriasProfissionais = categoriasProfissionais;
            avaliacao.medidasRecomendadas = medidas;

            relatorioAmbiental.avaliacoes.add(avaliacao);
        }

        return relatorioAmbiental;
    }


    private List<Levantamento> obterLevantamentoRisco(int idAtividade) {

        List<Levantamento> registos = new ArrayList<>();

        for(LevantamentoRiscoResultado itemLevantamento : repositorio.obterLevantamentos(idAtividade)){

            Levantamento levantamento = UploadMapping.INSTANCE.map(itemLevantamento);

            for(RiscoResultado item : repositorio.obterRiscos(itemLevantamento.id)){

                Risco risco = UploadMapping.INSTANCE.map(item);
                risco.idsMedidasExistentes = repositorio.obterMedidas(item.id, Identificadores.Origens.LEVANTAMENTO_MEDIDAS_ADOPTADAS);
                risco.idsMedidasRecomendadas = repositorio.obterMedidas(item.id, Identificadores.Origens.LEVANTAMENTO_MEDIDAS_RECOMENDADAS);
                risco.album = repositorio.obterImagens_(item.id, Identificadores.Imagens.IMAGEM_RISCO);
                //--idImagens.add(registo.idImagem);
                levantamento.riscos.add(risco);
            }

            levantamento.categoriasProfissionais = repositorio.obterCategoriasProfissionais(itemLevantamento.id, Identificadores.Origens.LEVANTAMENTO_CATEGORIAS_PROFISSIONAIS);
            registos.add(levantamento);
        }

        return registos;
    }


    /**
     * Metodo que permite obter as atividades pendentes
     * @param idTarefa o identificador da tarefa
     * @return uma lista de atividades
     */
    private List<AtividadePendente> obterAtividadesPendentes(int idTarefa) {

        List<AtividadePendente> registos = new ArrayList<>();

        for (AtividadePendenteBd item : repositorio.obterAtividadesPendentes(idTarefa)) {

            if(item.resultado.idEstado == Identificadores.Estados.ESTADO_EXECUTADO){

                AtividadePendenteExecutada registo = UploadMapping.INSTANCE.mapeamentoTemperaturaHumidade(item);

//                if(item.atividade.idRelatorio == Identificadores.Relatorios.ID_RELATORIO_FORMACAO){
//                    registo.formacao = obterAcaoFormacao(item.resultado.id);
//                }
//                else if(item.atividade.idRelatorio == Identificadores.Relatorios.ID_RELATORIO_AVALIACAO_RISCO){
//                    registo.avaliacaoRiscos = obterAvaliacaoRiscos(item.resultado.id);
//                }

                registo.formacao = obterAcaoFormacao(item.resultado.id);
                registo.avaliacaoRiscos = obterAvaliacaoRiscos(idTarefa, item.resultado.id);
                registo.iluminacao = obterRelatorioIluminacao(item.resultado.id);
                registo.temperaturaHumidade = obterRelatorioTemperaturaHumidade(item.resultado.id);

                registos.add(registo);
            }
            else {
                AtividadePendenteNaoExecutada registo = UploadMapping.INSTANCE.map(item);
                registos.add(registo);
            }
        }

        return registos;
    }


    private List<AtividadePlanoAcao> obterPlanoAcao(int idTarefa) {

        List<AtividadePlanoAcao> registos = new ArrayList<>();

        for (AtividadePlanoAcaoBd item : repositorio.obterPlanoAcao(idTarefa)) {

            AtividadePlanoAcao registo = UploadMapping.INSTANCE.map(item);
            registos.add(registo);
        }

        return registos;
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
     * Metodo que permite obter as anomalias de um cliente
     * @param idTarefa o identificador da tarefa
     * @return os dados das anomalias
     */
    private List<Anomalia> obterAnomaliaCliente(int idTarefa) {

        List<Anomalia> registos = new ArrayList<>();

        for (AnomaliaResultado item : repositorio.obterAnomalias(idTarefa)) {
            registos.add(UploadMapping.INSTANCE.map(item));
        }

        return registos;
    }


    /**
     * Metodo que permite obter um email
     * @param idTarefa o identificador da tarefa
     * @return os dados do email
     */
    private Email obterEmail(int idTarefa) {

        Email email = UploadMapping.INSTANCE.map(repositorio.obterEmail(idTarefa));
        return email;
    }

}
