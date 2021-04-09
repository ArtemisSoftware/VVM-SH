package com.vvm.sh.servicos.tipos;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import com.vvm.sh.api.modelos.pedido.IAtividadeExecutada;
import com.vvm.sh.api.modelos.pedido.IAnomalia;
import com.vvm.sh.api.modelos.pedido.IAtividadePendente;
import com.vvm.sh.api.modelos.pedido.IAvaliacaoRiscosAnterior;
import com.vvm.sh.api.modelos.pedido.ICliente;
import com.vvm.sh.api.modelos.pedido.IColaborador;
import com.vvm.sh.api.modelos.pedido.IFormando;
import com.vvm.sh.api.modelos.pedido.IMorada;
import com.vvm.sh.api.modelos.pedido.IParqueExtintor;
import com.vvm.sh.api.modelos.pedido.IPlanoAcao;
import com.vvm.sh.api.modelos.pedido.IRelatorioAvaliacaoRiscos;
import com.vvm.sh.api.modelos.pedido.ITarefa;
import com.vvm.sh.api.modelos.pedido.IDados;
import com.vvm.sh.api.modelos.pedido.IOcorrencia;
import com.vvm.sh.api.modelos.pedido.ISessao;
import com.vvm.sh.api.modelos.pedido.ITipoExtintor;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.AreaChecklistResultado;
import com.vvm.sh.baseDados.entidades.CategoriaProfissionalResultado;
import com.vvm.sh.baseDados.entidades.Colaborador;
import com.vvm.sh.baseDados.entidades.FormandoResultado;
import com.vvm.sh.baseDados.entidades.LevantamentoRiscoResultado;
import com.vvm.sh.baseDados.entidades.MedidaAveriguacao;
import com.vvm.sh.baseDados.entidades.MedidaResultado;
import com.vvm.sh.baseDados.entidades.Morada;
import com.vvm.sh.baseDados.entidades.ParqueExtintor;
import com.vvm.sh.baseDados.entidades.PlanoAcao;
import com.vvm.sh.baseDados.entidades.PlanoAcaoAtividade;
import com.vvm.sh.baseDados.entidades.ProcessoProdutivoResultado;
import com.vvm.sh.baseDados.entidades.PropostaPlanoAcaoResultado;
import com.vvm.sh.baseDados.entidades.QuestionarioChecklistResultado;
import com.vvm.sh.baseDados.entidades.RelatorioAveriguacao;
import com.vvm.sh.baseDados.entidades.RiscoResultado;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.baseDados.entidades.Anomalia;
import com.vvm.sh.baseDados.entidades.AtividadeExecutada;
import com.vvm.sh.baseDados.entidades.AtividadePendente;
import com.vvm.sh.baseDados.entidades.Cliente;
import com.vvm.sh.baseDados.entidades.Ocorrencia;
import com.vvm.sh.baseDados.entidades.OcorrenciaHistorico;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.baseDados.entidades.TipoExtintor;
import com.vvm.sh.baseDados.entidades.TrabalhadorVulneravelResultado;
import com.vvm.sh.baseDados.entidades.VerificacaoEquipamentoResultado;
import com.vvm.sh.repositorios.DownloadTrabalhoRepositorio;
import com.vvm.sh.repositorios.TransferenciasRepositorio;
import com.vvm.sh.servicos.tipos.atualizacao.AtualizarTipoAtividadesPlaneaveisAsyncTask__v2;
import com.vvm.sh.ui.atividadesPendentes.relatorios.formacao.modelos.Formando;
import com.vvm.sh.ui.transferencias.adaptadores.OnTransferenciaListener;
import com.vvm.sh.ui.transferencias.modelos.Sessao;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.AtualizacaoUI_;
import com.vvm.sh.util.constantes.AppConfig;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.TiposConstantes;
import com.vvm.sh.util.mapeamento.DownloadMapping;
import com.vvm.sh.util.metodos.ConversorUtil;
import com.vvm.sh.util.metodos.DatasUtil;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.List;


public abstract class TrabalhoAsyncTask extends AsyncTask<Sessao, Void, Void> {

    protected String erro;
    protected VvmshBaseDados vvmshBaseDados;
    protected DownloadTrabalhoRepositorio repositorio;
    protected String idUtilizador;
    protected OnTransferenciaListener listener;


    public TrabalhoAsyncTask(OnTransferenciaListener listener, VvmshBaseDados vvmshBaseDados, DownloadTrabalhoRepositorio repositorio, String idUtilizador) {
        this.vvmshBaseDados = vvmshBaseDados;
        this.repositorio = repositorio;
        this.idUtilizador = idUtilizador;
        this.erro = null;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Sessao... sessoes) {


        if(sessoes[0] == null)
            return null;

        Sessao resposta = sessoes[0];

        this.vvmshBaseDados.runInTransaction(new Runnable(){
            @Override
            public void run(){

                try {

                    if(resposta.iSessaoSA != null){

                        int api = resposta.iSessaoSA.api;
                        String data = resposta.iSessaoSA.sessoes.get(0).data;
                        List<ISessao.TrabalhoInfo> trabalho = resposta.iSessaoSA.sessoes.get(0).trabalho;

                        inserirTrabalho(trabalho, data, api);
                    }

                    if(resposta.iSessaoSHT != null){

                        int api = resposta.iSessaoSHT.api;
                        String data = resposta.iSessaoSHT.sessoes.get(0).data;
                        List<ISessao.TrabalhoInfo> trabalho = resposta.iSessaoSHT.sessoes.get(0).trabalho;

                        inserirTrabalho(trabalho, data, api);
                    }
                }
                catch(SQLiteConstraintException throwable){
                    erro = throwable.getMessage();
                }

            }
        });

        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {

        listener.atualizarTransferencia(new AtualizacaoUI_(AtualizacaoUI_.Estado.PROCESSAMENTO_TRABALHO, erro));

//        if(erro == null){
//            listener.terminarTransferencia();
//        }
//        else{
//            listener.terminarTransferencia();
//        }
    }

    /**
     * Metodo que permite inserir uma tarefa
     * @param data a data do trabalho
     * @param info os dados da tarefa
     */
    protected void inserirTarefas(String data, ISessao.TrabalhoInfo info, int api) {

        //se não houver atividades pendentes a tarefa não deve ser inserida
        if(info.tarefas.atividadesPendentes.size() == 0){
            return;
        }

        int idTarefa = inserirTarefa (info.tarefas.dados, data, api);

        inserirAtividadesExecutadas(info.tarefas.atividadesExecutadas, idTarefa);

        inserirCliente(info.tarefas.cliente, info.tarefas.dados, info.tarefas, idTarefa);

        inserirAnomalias(info.tarefas.anomalias, idTarefa);

        inserirOcorrencias(info.tarefas.ocorrencias, idTarefa);

        inserirAtividadesPendentes(info.tarefas.atividadesPendentes, info.tarefas.avaliacaoRiscosAnterior, idTarefa);

        inserirMoradas(info.tarefas.moradas, info.tarefas.moradasExtintores, idTarefa);

        inserirParqueExtintores(info.tarefas.parqueExtintores, info.tarefas.tiposExtintor, idTarefa);

        inserirQuadroPessoal(info.tarefas.quadroPessoal, idTarefa);

        inserirPlanoAcao(info.tarefas.planoAcao, idTarefa);

        inserirAveriguacao_RelatorioAvaliacaoRiscos(info.tarefas.relatorioAvaliacaoRiscos, idTarefa);
    }

    private void inserirAveriguacao_RelatorioAvaliacaoRiscos(IRelatorioAvaliacaoRiscos relatorioAvaliacaoRiscos, int idTarefa) {

        if(relatorioAvaliacaoRiscos == null){
            return;
        }

        for (IRelatorioAvaliacaoRiscos.ICategoriaProfissional item : relatorioAvaliacaoRiscos.categoriasProfissionais) {

            RelatorioAveriguacao relatorio = DownloadMapping.INSTANCE.map(item);
            relatorio.idTarefa = idTarefa;
            relatorio.data = DatasUtil.converterString(relatorioAvaliacaoRiscos.data, DatasUtil.DATA_FORMATO_DD_MM_YYYY__HH_MM_SS_V2);

            int id = ConversorUtil.converter_long_Para_int(repositorio.inserirRelatorioAveriguacao(relatorio));

            List<MedidaAveriguacao> medidas = new ArrayList<>();

            for (String idMedida : item.medidas.split(",")) {
                medidas.add(new MedidaAveriguacao(id, Identificadores.Origens.AVERIGUACAO_AVALIACAO_RISCOS, Integer.parseInt(idMedida)));
            }

            repositorio.inserirMedidasAveriguacao(medidas);
        }

    }


    /**
     * Metodo que permite inserir o plano acao
     * @param planoAcao os dados do plano acao
     * @param idTarefa o identificador da tarefa
     */
    private void inserirPlanoAcao(IPlanoAcao planoAcao, int idTarefa) {

        if(planoAcao == null){
            return;
        }

        PlanoAcao registo = DownloadMapping.INSTANCE.map(planoAcao);
        registo.idTarefa = idTarefa;

        repositorio.inserirPlanoAcao(registo);


        List<PlanoAcaoAtividade> registos = new ArrayList<>();

        for(IPlanoAcao.IAtividadePlano IAtividadePlano : planoAcao.atividades){

            PlanoAcaoAtividade item = DownloadMapping.INSTANCE.map(IAtividadePlano);
            item.idTarefa = idTarefa;

            if(IAtividadePlano.sempreNecessario.equals("x") == false){
                item.sempreNecessario = 0;
            }
            else{
                item.sempreNecessario = 1;
            }


            if(IAtividadePlano.posicao == null){
                item.fixo = Identificadores.ID_POSICAO_MEIO;
            }
            else {

                if (IAtividadePlano.posicao.equals(Identificadores.PLANO_ACCAO_ATIVIDADE_TOPO) == true) {
                    item.fixo = Identificadores.ID_POSICAO_TOPO;
                } else if (IAtividadePlano.posicao.equals(Identificadores.PLANO_ACCAO_ATIVIDADE_FIM) == true) {
                    item.fixo = Identificadores.ID_POSICAO_FIM;
                } else {
                    item.fixo = Identificadores.ID_POSICAO_MEIO;
                }
            }

            registos.add(item);
        }

        repositorio.inserirPlanoAtividades(registos);

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
     * @param avaliacaoRiscosAnterior
     * @param idTarefa o identificador da tarefa
     */
    private void inserirAtividadesPendentes(List<IAtividadePendente> atividadesPendentes, IAvaliacaoRiscosAnterior avaliacaoRiscosAnterior, int idTarefa) {

        List<AtividadePendente> registos = new ArrayList<>();

        for(IAtividadePendente atividadePendente : atividadesPendentes){

            AtividadePendente registo = DownloadMapping.INSTANCE.map(atividadePendente);

            try {
                registo.idChecklist = Integer.parseInt(atividadePendente.idChecklist);
            }
            catch (NumberFormatException e){}

            registo.idRelatorio = obterIdRelatorioAtividadePendente(atividadePendente);
            registo.idTarefa = idTarefa;

            if(registo.idRelatorio == Identificadores.Relatorios.ID_RELATORIO_AVALIACAO_RISCO & avaliacaoRiscosAnterior != null){
                inserirAvaliacaoRiscosAnterior(registo, avaliacaoRiscosAnterior);
            }
            else if(registo.idRelatorio == Identificadores.Relatorios.ID_RELATORIO_FORMACAO & atividadePendente.formandos != null){
                inserirFormandosAnteriores(registo, atividadePendente.formandos);
            }
            else {
                registos.add(registo);
            }
        }

        repositorio.inserirAtividadesPendentes(registos);
    }

    private void inserirFormandosAnteriores(AtividadePendente registo, List<IFormando> formandos) {

        int idAtividade = ConversorUtil.converter_long_Para_int(repositorio.inserirAtividadePendente(registo));

        List<FormandoResultado> registos = new ArrayList<>();

        for (IFormando item : formandos) {

            FormandoResultado formando = DownloadMapping.INSTANCE.map(item);
            formando.idAtividade = idAtividade;
            formando.origem = Identificadores.Origens.ORIGEM_BD;

            if(item.dataNascimento.equals("") == false){
                formando.dataNascimento = DatasUtil.obterDataAtual_Date();
            }
            else{
                formando.dataNascimento = DatasUtil.converterString(item.dataNascimento, DatasUtil.FORMATO_YYYY_MM_DD);
            }

            registos.add(formando);
        }

        repositorio.inserirFormandos(registos);
    }


    private void inserirAvaliacaoRiscosAnterior(AtividadePendente registo, IAvaliacaoRiscosAnterior avaliacaoRiscosAnterior) {

        int idAtividade = ConversorUtil.converter_long_Para_int(repositorio.inserirAtividadePendente(registo));

        inserirProcessoProdutivo(avaliacaoRiscosAnterior, idAtividade);

        inserirEquipamentos(avaliacaoRiscosAnterior, idAtividade);

        inserirTrabalhadoresVulneraveis(avaliacaoRiscosAnterior, idAtividade);

        List<PropostaPlanoAcaoResultado> medidasRiscos = inserirLevantamento(avaliacaoRiscosAnterior, idAtividade);


        List<PropostaPlanoAcaoResultado> medidasChecklist = new ArrayList<>();
        List<Tipo> tipoUt = repositorio.obterUts();

        IAvaliacaoRiscosAnterior.IChecklist checklist = avaliacaoRiscosAnterior.checklist.get(0);

        for (IAvaliacaoRiscosAnterior.IArea itemArea : checklist.areas) {

            AreaChecklistResultado area = DownloadMapping.INSTANCE.map(itemArea);
            area.idAtividade = idAtividade;
            area.idChecklist = Integer.parseInt(checklist.id);

            int idRegistoArea = ConversorUtil.converter_long_Para_int(repositorio.inserirAreaChecklist(area));

            for (IAvaliacaoRiscosAnterior.ISeccao itemSeccao : itemArea.seccoes) {

                String idSeccao = itemSeccao.id;

                List<QuestionarioChecklistResultado> questoes = new ArrayList<>();

                for (IAvaliacaoRiscosAnterior.IItem item : itemSeccao.itens) {

                    if(repositorio.verificarItemChecklist(checklist.id, itemArea.id, idSeccao, item.id) == true){

                        String tipo = repositorio.obterTipoItemChecklist(checklist.id, itemArea.id, idSeccao, item.id);

                        if(tipo.equals(Identificadores.Checklist.TIPO_QUESTAO) == true){
                            QuestionarioChecklistResultado questao = DownloadMapping.INSTANCE.mapQuestao(item);
                            questao.idArea = idRegistoArea;
                            questao.idSeccao = idSeccao;
                            questao.tipo = tipo;

                            int idQuestao = ConversorUtil.converter_long_Para_int(repositorio.inserirQuestao(questao));

                            if(item.resposta.equals(TiposConstantes.Checklist.NAO.descricao) == true){
                                medidasChecklist.add(new PropostaPlanoAcaoResultado(idAtividade, idQuestao));
                            }

                        }
                        if(tipo.equals(Identificadores.Checklist.TIPO_UTS) == true){
                            QuestionarioChecklistResultado questao = DownloadMapping.INSTANCE.mapUt(item);
                            questao.idArea = idRegistoArea;
                            questao.idSeccao = idSeccao;
                            questao.tipo = tipo;

                            try{
                                questao.ut1_CategoriasRisco = obterCategoriasRiscoUT(item.idCategoriasRiscoUT_1);
                                questao.ut2_CategoriasRisco = obterCategoriasRiscoUT(item.idCategoriasRiscoUT_2);
                            }
                            catch (NumberFormatException e){}

                            questao.ut1 = obterUt(item.idUT1, tipoUt);
                            questao.ut2 = obterUt(item.idUT2, tipoUt);
                            questoes.add(questao);
                        }
                        if(tipo.equals(Identificadores.Checklist.TIPO_OBSERVACOES) == true){
                            QuestionarioChecklistResultado questao = DownloadMapping.INSTANCE.mapObservacao(item);
                            questao.idArea = idRegistoArea;
                            questao.idSeccao = idSeccao;
                            questao.tipo = tipo;

                            questoes.add(questao);
                        }
                    }
                }

                repositorio.inserirQuestoes(questoes);

            }
        }

        repositorio.inserirPropostaPlanoAcao(medidasChecklist);
        repositorio.inserirPropostaPlanoAcao(medidasRiscos);
    }

    private int obterUt(String idUT, List<Tipo> tipoUt) {

        for (Tipo tipo : tipoUt) {

            if(tipo.codigo.equals(idUT) == true){
                return tipo.id;
            }
        }

        return Identificadores.VALOR_INT_0;
    }


    private int obterCategoriasRiscoUT(String idCategoriasRiscoUT) {

        for (Tipo tipo : TiposConstantes.Checklist.CATEGORIAS_RISCO) {

            if(tipo.descricao.equals(idCategoriasRiscoUT) == true){
                return tipo.id;
            }
        }

        return Identificadores.VALOR_INT_0;
    }


    private List<PropostaPlanoAcaoResultado> inserirLevantamento(IAvaliacaoRiscosAnterior avaliacaoRiscosAnterior, int idAtividade) {

        List<PropostaPlanoAcaoResultado> medidasRiscos = new ArrayList<>();
        List<Tipo> tipoNi = repositorio.obterNi();

        for (IAvaliacaoRiscosAnterior.ILevantamentoRisco item : avaliacaoRiscosAnterior.levantamentosRisco) {

            LevantamentoRiscoResultado levantamento = new LevantamentoRiscoResultado(idAtividade, item.tarefa, item.perigo);

            int idLevantamento = ConversorUtil.converter_long_Para_int(repositorio.inserirLevantamento(levantamento));


            List<CategoriaProfissionalResultado> categorias = new ArrayList<>();

            for (IAvaliacaoRiscosAnterior.ICategoriaProfissional categoriaProfissional : item.categoriasProfissionais) {
                categorias.add(new CategoriaProfissionalResultado(idLevantamento, Integer.parseInt(categoriaProfissional.id), Identificadores.Origens.LEVANTAMENTO_CATEGORIAS_PROFISSIONAIS, Integer.parseInt(categoriaProfissional.numeroHomens), Integer.parseInt(categoriaProfissional.numeroMulheres)));
            }

            repositorio.inserirCategoriasProfissionais(categorias);


            for(IAvaliacaoRiscosAnterior.IRisco itemRisco : item.riscos){

                RiscoResultado risco = DownloadMapping.INSTANCE.map(itemRisco);
                risco.idLevantamento = idLevantamento;
                risco.ni = ConversorUtil.obterNI(itemRisco.nd, itemRisco.ne, itemRisco.nc, tipoNi);

                int idRisco = ConversorUtil.converter_long_Para_int(repositorio.inserirRisco(risco));

                List<MedidaResultado> medidas = new ArrayList<>();

                for(String idMedida : itemRisco.idsMedidasExistentes){
                    if(repositorio.validarMedida(Integer.parseInt(idMedida), TiposUtil.MetodosTipos.MEDIDAS_PREVENCAO_ADOPTADAS) == true){
                        medidas.add(new MedidaResultado(idRisco, Identificadores.Origens.LEVANTAMENTO_MEDIDAS_ADOPTADAS, Integer.parseInt(idMedida)));
                    }
                }


                for(String idMedida : itemRisco.idsMedidasRecomendadas){
                    if(repositorio.validarMedida(Integer.parseInt(idMedida), TiposUtil.MetodosTipos.MEDIDAS_PREVENCAO_RECOMENDADAS) == true){

                        medidasRiscos.add(new PropostaPlanoAcaoResultado(idAtividade, idRisco, Integer.parseInt(idMedida)));

                        medidas.add(new MedidaResultado(idRisco, Identificadores.Origens.LEVANTAMENTO_MEDIDAS_RECOMENDADAS, Integer.parseInt(idMedida)));
                    }
                }

                repositorio.inserirMedidas(medidas);
            }
        }

        return medidasRiscos;
    }

    private void inserirTrabalhadoresVulneraveis(IAvaliacaoRiscosAnterior avaliacaoRiscosAnterior, int idAtividade) {
        for (IAvaliacaoRiscosAnterior.IVulnerabilidade vulnerabilidade :avaliacaoRiscosAnterior.vulnerabilidades) {

            if(vulnerabilidade.homens != 0 || vulnerabilidade.mulheres != 0){

                TrabalhadorVulneravelResultado trabalhadorVulneravel = new TrabalhadorVulneravelResultado(idAtividade, vulnerabilidade.id, vulnerabilidade.homens, vulnerabilidade.mulheres);
                int idTrabalhador = ConversorUtil.converter_long_Para_int(repositorio.inserirTrabalhadorVulneravel(trabalhadorVulneravel));

                List<CategoriaProfissionalResultado> categorias = new ArrayList<>();

                for (String idCategoria : vulnerabilidade.categoriasProfissionaisHomens) {
                    categorias.add(new CategoriaProfissionalResultado(idTrabalhador, Integer.parseInt(idCategoria), Identificadores.Origens.VULNERABILIDADE_CATEGORIAS_PROFISSIONAIS_HOMENS));
                }

                for (String idCategoria : vulnerabilidade.categoriasProfissionaisMulheres) {
                    categorias.add(new CategoriaProfissionalResultado(idTrabalhador, Integer.parseInt(idCategoria), Identificadores.Origens.VULNERABILIDADE_CATEGORIAS_PROFISSIONAIS_MULHERES));
                }

                repositorio.inserirCategoriasProfissionais(categorias);

            }
        }
    }

    private void inserirEquipamentos(IAvaliacaoRiscosAnterior avaliacaoRiscosAnterior, int idAtividade) {
        List<VerificacaoEquipamentoResultado> equipamentos = new ArrayList<>();

        for (String idEquipamento :avaliacaoRiscosAnterior.equipamentos) {
            equipamentos.add(new VerificacaoEquipamentoResultado(idAtividade, Integer.parseInt(idEquipamento)));
        }

        repositorio.inserirEquipamentos(equipamentos);
    }

    private void inserirProcessoProdutivo(IAvaliacaoRiscosAnterior avaliacaoRiscosAnterior, int idAtividade) {

        ProcessoProdutivoResultado processoProdutivo = new ProcessoProdutivoResultado(idAtividade, avaliacaoRiscosAnterior.processoProdutivo);
        repositorio.inserirProcessoProdutivo(processoProdutivo);
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
    private int inserirTarefa(IDados dados, String data, int api){

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

        if(atividadePendente.relatorioCertificadoTratamento > 0){
            return Identificadores.Relatorios.ID_RELATORIO_CERTIFICADO_TRATAMENTO;
        }

        if(atividadePendente.verificacao != null) {
            if (atividadePendente.verificacao.equals("arpt") == true) {
                return Identificadores.Relatorios.ID_RELATORIO_AVERIGUACAO_AVALIACAO_RISCO;
            }
        }

        return Identificadores.Relatorios.SEM_RELATORIO;

    }





    //-----------------------
    //Metodos abstratos
    //-----------------------

    protected abstract void inserirTrabalho(List<ISessao.TrabalhoInfo> trabalho, String data, int api) ;
}
