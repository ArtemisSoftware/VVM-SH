package com.vvm.sh;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.vvm.sh.databinding.ActivityAcaoFormacaoBindingImpl;
import com.vvm.sh.databinding.ActivityAnomaliasBindingImpl;
import com.vvm.sh.databinding.ActivityAtividadesExecutadasBindingImpl;
import com.vvm.sh.databinding.ActivityAtividadesPendentesBindingImpl;
import com.vvm.sh.databinding.ActivityAtualizacaoAppBindingImpl;
import com.vvm.sh.databinding.ActivityAutenticacaoBindingImpl;
import com.vvm.sh.databinding.ActivityAvaliacaoGeralBindingImpl;
import com.vvm.sh.databinding.ActivityAvaliacaoIluminacaoRegistoBindingImpl;
import com.vvm.sh.databinding.ActivityAvaliacaoTemperaturaHumidadeRegistoBindingImpl;
import com.vvm.sh.databinding.ActivityAvaliacoesAmbientaisBindingImpl;
import com.vvm.sh.databinding.ActivityBaseDaggerBindingImpl;
import com.vvm.sh.databinding.ActivityCarregamentoTiposBindingImpl;
import com.vvm.sh.databinding.ActivityCategoriasProfissionaisBindingImpl;
import com.vvm.sh.databinding.ActivityChecklistBindingImpl;
import com.vvm.sh.databinding.ActivityColaboradorBindingImpl;
import com.vvm.sh.databinding.ActivityCrossSellingBindingImpl;
import com.vvm.sh.databinding.ActivityDadosClienteBindingImpl;
import com.vvm.sh.databinding.ActivityDownloadTrabalhoBindingImpl;
import com.vvm.sh.databinding.ActivityExtintoresBindingImpl;
import com.vvm.sh.databinding.ActivityFormacaoBindingImpl;
import com.vvm.sh.databinding.ActivityFormandoBindingImpl;
import com.vvm.sh.databinding.ActivityInformacaoBindingImpl;
import com.vvm.sh.databinding.ActivityLevantamentosBindingImpl;
import com.vvm.sh.databinding.ActivityMainBindingImpl;
import com.vvm.sh.databinding.ActivityOcorrenciaRegistarBindingImpl;
import com.vvm.sh.databinding.ActivityOcorrenciasBindingImpl;
import com.vvm.sh.databinding.ActivityOcorrenciasHistoricoBindingImpl;
import com.vvm.sh.databinding.ActivityOcorrenciasRegistoBindingImpl;
import com.vvm.sh.databinding.ActivityPerfilBindingImpl;
import com.vvm.sh.databinding.ActivityPerigoTarefaBindingImpl;
import com.vvm.sh.databinding.ActivityPesquisaBindingImpl;
import com.vvm.sh.databinding.ActivityPlanoAccaoBindingImpl;
import com.vvm.sh.databinding.ActivityPropostaPlanoAccaoBindingImpl;
import com.vvm.sh.databinding.ActivityQuadroPessoalBindingImpl;
import com.vvm.sh.databinding.ActivityQuestoesChecklistBindingImpl;
import com.vvm.sh.databinding.ActivityRegistoVisitaBindingImpl;
import com.vvm.sh.databinding.ActivityRelatorioAvaliacaoAmbientalBindingImpl;
import com.vvm.sh.databinding.ActivityRiscoRegistoBindingImpl;
import com.vvm.sh.databinding.ActivityRiscosBindingImpl;
import com.vvm.sh.databinding.ActivitySinistralidadeBindingImpl;
import com.vvm.sh.databinding.ActivityTarefaBindingImpl;
import com.vvm.sh.databinding.ActivityTiposBindingImpl;
import com.vvm.sh.databinding.ActivityTrabalhadorVulneravelRegistoBindingImpl;
import com.vvm.sh.databinding.ActivityTrabalhadoresVulneraveisBindingImpl;
import com.vvm.sh.databinding.ActivityTrabalhoRealizadoBindingImpl;
import com.vvm.sh.databinding.ActivityUploadBindingImpl;
import com.vvm.sh.databinding.DialogoAnomaliaBindingImpl;
import com.vvm.sh.databinding.DialogoAreaChecklistBindingImpl;
import com.vvm.sh.databinding.DialogoAtividadePendenteBindingImpl;
import com.vvm.sh.databinding.DialogoAtividadePendenteExecutadaBindingImpl;
import com.vvm.sh.databinding.DialogoAtividadePendenteNaoExecutadaBindingImpl;
import com.vvm.sh.databinding.DialogoCategoriaProfissionalBindingImpl;
import com.vvm.sh.databinding.DialogoChecklistPerguntaBindingImpl;
import com.vvm.sh.databinding.DialogoColaboradorBindingImpl;
import com.vvm.sh.databinding.DialogoEmailBindingImpl;
import com.vvm.sh.databinding.DialogoOpcoesColaboradorBindingImpl;
import com.vvm.sh.databinding.DialogoProcessoProdutivoBindingImpl;
import com.vvm.sh.databinding.DialogoSinaleticaBindingImpl;
import com.vvm.sh.databinding.DialogoTrabalhoRealizadoBindingImpl;
import com.vvm.sh.databinding.FragmentAnuidadeBindingImpl;
import com.vvm.sh.databinding.ItemAnomaliaBindingImpl;
import com.vvm.sh.databinding.ItemAnomaliaRegistadaBindingImpl;
import com.vvm.sh.databinding.ItemAtividadeExecutadaBindingImpl;
import com.vvm.sh.databinding.ItemAtividadePendenteBindingImpl;
import com.vvm.sh.databinding.ItemAvaliacaoIluminacaoBindingImpl;
import com.vvm.sh.databinding.ItemAvaliacaoTemperaturaHumidadeBindingImpl;
import com.vvm.sh.databinding.ItemCategoriaProfissionalBindingImpl;
import com.vvm.sh.databinding.ItemChecklistAreaBindingImpl;
import com.vvm.sh.databinding.ItemChecklistFotoBindingImpl;
import com.vvm.sh.databinding.ItemChecklistObservacaoBindingImpl;
import com.vvm.sh.databinding.ItemChecklistPerguntaBindingImpl;
import com.vvm.sh.databinding.ItemChecklistSeccaoBindingImpl;
import com.vvm.sh.databinding.ItemChecklistUtsBindingImpl;
import com.vvm.sh.databinding.ItemColaboradorBindingImpl;
import com.vvm.sh.databinding.ItemCrossSellingBindingImpl;
import com.vvm.sh.databinding.ItemExtintorBindingImpl;
import com.vvm.sh.databinding.ItemFormandoBindingImpl;
import com.vvm.sh.databinding.ItemLevantamentoBindingImpl;
import com.vvm.sh.databinding.ItemMarcacaoBindingImpl;
import com.vvm.sh.databinding.ItemOcorrenciaBindingImpl;
import com.vvm.sh.databinding.ItemOcorrenciaHistoricoBindingImpl;
import com.vvm.sh.databinding.ItemOcorrenciaRegistoBindingImpl;
import com.vvm.sh.databinding.ItemOpcaoClienteBindingImpl;
import com.vvm.sh.databinding.ItemPendenciaBindingImpl;
import com.vvm.sh.databinding.ItemPesquisaBindingImpl;
import com.vvm.sh.databinding.ItemPlanoAcaoProgramacaoBindingImpl;
import com.vvm.sh.databinding.ItemPlanoAccaoMedidasAvaliacaoBindingImpl;
import com.vvm.sh.databinding.ItemPlanoAccaoStBindingImpl;
import com.vvm.sh.databinding.ItemRiscoBindingImpl;
import com.vvm.sh.databinding.ItemTipoBindingImpl;
import com.vvm.sh.databinding.ItemTrabalhadorVulneravelBindingImpl;
import com.vvm.sh.databinding.ItemTrabalhoRealizadoBindingImpl;
import com.vvm.sh.databinding.ItemUploadBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYACAOFORMACAO = 1;

  private static final int LAYOUT_ACTIVITYANOMALIAS = 2;

  private static final int LAYOUT_ACTIVITYATIVIDADESEXECUTADAS = 3;

  private static final int LAYOUT_ACTIVITYATIVIDADESPENDENTES = 4;

  private static final int LAYOUT_ACTIVITYATUALIZACAOAPP = 5;

  private static final int LAYOUT_ACTIVITYAUTENTICACAO = 6;

  private static final int LAYOUT_ACTIVITYAVALIACAOGERAL = 7;

  private static final int LAYOUT_ACTIVITYAVALIACAOILUMINACAOREGISTO = 8;

  private static final int LAYOUT_ACTIVITYAVALIACAOTEMPERATURAHUMIDADEREGISTO = 9;

  private static final int LAYOUT_ACTIVITYAVALIACOESAMBIENTAIS = 10;

  private static final int LAYOUT_ACTIVITYBASEDAGGER = 11;

  private static final int LAYOUT_ACTIVITYCARREGAMENTOTIPOS = 12;

  private static final int LAYOUT_ACTIVITYCATEGORIASPROFISSIONAIS = 13;

  private static final int LAYOUT_ACTIVITYCHECKLIST = 14;

  private static final int LAYOUT_ACTIVITYCOLABORADOR = 15;

  private static final int LAYOUT_ACTIVITYCROSSSELLING = 16;

  private static final int LAYOUT_ACTIVITYDADOSCLIENTE = 17;

  private static final int LAYOUT_ACTIVITYDOWNLOADTRABALHO = 18;

  private static final int LAYOUT_ACTIVITYEXTINTORES = 19;

  private static final int LAYOUT_ACTIVITYFORMACAO = 20;

  private static final int LAYOUT_ACTIVITYFORMANDO = 21;

  private static final int LAYOUT_ACTIVITYINFORMACAO = 22;

  private static final int LAYOUT_ACTIVITYLEVANTAMENTOS = 23;

  private static final int LAYOUT_ACTIVITYMAIN = 24;

  private static final int LAYOUT_ACTIVITYOCORRENCIAREGISTAR = 25;

  private static final int LAYOUT_ACTIVITYOCORRENCIAS = 26;

  private static final int LAYOUT_ACTIVITYOCORRENCIASHISTORICO = 27;

  private static final int LAYOUT_ACTIVITYOCORRENCIASREGISTO = 28;

  private static final int LAYOUT_ACTIVITYPERFIL = 29;

  private static final int LAYOUT_ACTIVITYPERIGOTAREFA = 30;

  private static final int LAYOUT_ACTIVITYPESQUISA = 31;

  private static final int LAYOUT_ACTIVITYPLANOACCAO = 32;

  private static final int LAYOUT_ACTIVITYPROPOSTAPLANOACCAO = 33;

  private static final int LAYOUT_ACTIVITYQUADROPESSOAL = 34;

  private static final int LAYOUT_ACTIVITYQUESTOESCHECKLIST = 35;

  private static final int LAYOUT_ACTIVITYREGISTOVISITA = 36;

  private static final int LAYOUT_ACTIVITYRELATORIOAVALIACAOAMBIENTAL = 37;

  private static final int LAYOUT_ACTIVITYRISCOREGISTO = 38;

  private static final int LAYOUT_ACTIVITYRISCOS = 39;

  private static final int LAYOUT_ACTIVITYSINISTRALIDADE = 40;

  private static final int LAYOUT_ACTIVITYTAREFA = 41;

  private static final int LAYOUT_ACTIVITYTIPOS = 42;

  private static final int LAYOUT_ACTIVITYTRABALHADORVULNERAVELREGISTO = 43;

  private static final int LAYOUT_ACTIVITYTRABALHADORESVULNERAVEIS = 44;

  private static final int LAYOUT_ACTIVITYTRABALHOREALIZADO = 45;

  private static final int LAYOUT_ACTIVITYUPLOAD = 46;

  private static final int LAYOUT_DIALOGOANOMALIA = 47;

  private static final int LAYOUT_DIALOGOAREACHECKLIST = 48;

  private static final int LAYOUT_DIALOGOATIVIDADEPENDENTE = 49;

  private static final int LAYOUT_DIALOGOATIVIDADEPENDENTEEXECUTADA = 50;

  private static final int LAYOUT_DIALOGOATIVIDADEPENDENTENAOEXECUTADA = 51;

  private static final int LAYOUT_DIALOGOCATEGORIAPROFISSIONAL = 52;

  private static final int LAYOUT_DIALOGOCHECKLISTPERGUNTA = 53;

  private static final int LAYOUT_DIALOGOCOLABORADOR = 54;

  private static final int LAYOUT_DIALOGOEMAIL = 55;

  private static final int LAYOUT_DIALOGOOPCOESCOLABORADOR = 56;

  private static final int LAYOUT_DIALOGOPROCESSOPRODUTIVO = 57;

  private static final int LAYOUT_DIALOGOSINALETICA = 58;

  private static final int LAYOUT_DIALOGOTRABALHOREALIZADO = 59;

  private static final int LAYOUT_FRAGMENTANUIDADE = 60;

  private static final int LAYOUT_ITEMANOMALIA = 61;

  private static final int LAYOUT_ITEMANOMALIAREGISTADA = 62;

  private static final int LAYOUT_ITEMATIVIDADEEXECUTADA = 63;

  private static final int LAYOUT_ITEMATIVIDADEPENDENTE = 64;

  private static final int LAYOUT_ITEMAVALIACAOILUMINACAO = 65;

  private static final int LAYOUT_ITEMAVALIACAOTEMPERATURAHUMIDADE = 66;

  private static final int LAYOUT_ITEMCATEGORIAPROFISSIONAL = 67;

  private static final int LAYOUT_ITEMCHECKLISTAREA = 68;

  private static final int LAYOUT_ITEMCHECKLISTFOTO = 69;

  private static final int LAYOUT_ITEMCHECKLISTOBSERVACAO = 70;

  private static final int LAYOUT_ITEMCHECKLISTPERGUNTA = 71;

  private static final int LAYOUT_ITEMCHECKLISTSECCAO = 72;

  private static final int LAYOUT_ITEMCHECKLISTUTS = 73;

  private static final int LAYOUT_ITEMCOLABORADOR = 74;

  private static final int LAYOUT_ITEMCROSSSELLING = 75;

  private static final int LAYOUT_ITEMEXTINTOR = 76;

  private static final int LAYOUT_ITEMFORMANDO = 77;

  private static final int LAYOUT_ITEMLEVANTAMENTO = 78;

  private static final int LAYOUT_ITEMMARCACAO = 79;

  private static final int LAYOUT_ITEMOCORRENCIA = 80;

  private static final int LAYOUT_ITEMOCORRENCIAHISTORICO = 81;

  private static final int LAYOUT_ITEMOCORRENCIAREGISTO = 82;

  private static final int LAYOUT_ITEMOPCAOCLIENTE = 83;

  private static final int LAYOUT_ITEMPENDENCIA = 84;

  private static final int LAYOUT_ITEMPESQUISA = 85;

  private static final int LAYOUT_ITEMPLANOACAOPROGRAMACAO = 86;

  private static final int LAYOUT_ITEMPLANOACCAOMEDIDASAVALIACAO = 87;

  private static final int LAYOUT_ITEMPLANOACCAOST = 88;

  private static final int LAYOUT_ITEMRISCO = 89;

  private static final int LAYOUT_ITEMTIPO = 90;

  private static final int LAYOUT_ITEMTRABALHADORVULNERAVEL = 91;

  private static final int LAYOUT_ITEMTRABALHOREALIZADO = 92;

  private static final int LAYOUT_ITEMUPLOAD = 93;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(93);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_acao_formacao, LAYOUT_ACTIVITYACAOFORMACAO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_anomalias, LAYOUT_ACTIVITYANOMALIAS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_atividades_executadas, LAYOUT_ACTIVITYATIVIDADESEXECUTADAS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_atividades_pendentes, LAYOUT_ACTIVITYATIVIDADESPENDENTES);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_atualizacao_app, LAYOUT_ACTIVITYATUALIZACAOAPP);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_autenticacao, LAYOUT_ACTIVITYAUTENTICACAO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_avaliacao_geral, LAYOUT_ACTIVITYAVALIACAOGERAL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_avaliacao_iluminacao_registo, LAYOUT_ACTIVITYAVALIACAOILUMINACAOREGISTO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_avaliacao_temperatura_humidade_registo, LAYOUT_ACTIVITYAVALIACAOTEMPERATURAHUMIDADEREGISTO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_avaliacoes_ambientais, LAYOUT_ACTIVITYAVALIACOESAMBIENTAIS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_base_dagger, LAYOUT_ACTIVITYBASEDAGGER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_carregamento_tipos, LAYOUT_ACTIVITYCARREGAMENTOTIPOS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_categorias_profissionais, LAYOUT_ACTIVITYCATEGORIASPROFISSIONAIS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_checklist, LAYOUT_ACTIVITYCHECKLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_colaborador, LAYOUT_ACTIVITYCOLABORADOR);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_cross_selling, LAYOUT_ACTIVITYCROSSSELLING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_dados_cliente, LAYOUT_ACTIVITYDADOSCLIENTE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_download_trabalho, LAYOUT_ACTIVITYDOWNLOADTRABALHO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_extintores, LAYOUT_ACTIVITYEXTINTORES);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_formacao, LAYOUT_ACTIVITYFORMACAO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_formando, LAYOUT_ACTIVITYFORMANDO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_informacao, LAYOUT_ACTIVITYINFORMACAO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_levantamentos, LAYOUT_ACTIVITYLEVANTAMENTOS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_ocorrencia_registar, LAYOUT_ACTIVITYOCORRENCIAREGISTAR);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_ocorrencias, LAYOUT_ACTIVITYOCORRENCIAS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_ocorrencias_historico, LAYOUT_ACTIVITYOCORRENCIASHISTORICO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_ocorrencias_registo, LAYOUT_ACTIVITYOCORRENCIASREGISTO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_perfil, LAYOUT_ACTIVITYPERFIL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_perigo_tarefa, LAYOUT_ACTIVITYPERIGOTAREFA);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_pesquisa, LAYOUT_ACTIVITYPESQUISA);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_plano_accao, LAYOUT_ACTIVITYPLANOACCAO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_proposta_plano_accao, LAYOUT_ACTIVITYPROPOSTAPLANOACCAO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_quadro_pessoal, LAYOUT_ACTIVITYQUADROPESSOAL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_questoes_checklist, LAYOUT_ACTIVITYQUESTOESCHECKLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_registo_visita, LAYOUT_ACTIVITYREGISTOVISITA);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_relatorio_avaliacao_ambiental, LAYOUT_ACTIVITYRELATORIOAVALIACAOAMBIENTAL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_risco_registo, LAYOUT_ACTIVITYRISCOREGISTO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_riscos, LAYOUT_ACTIVITYRISCOS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_sinistralidade, LAYOUT_ACTIVITYSINISTRALIDADE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_tarefa, LAYOUT_ACTIVITYTAREFA);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_tipos, LAYOUT_ACTIVITYTIPOS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_trabalhador_vulneravel_registo, LAYOUT_ACTIVITYTRABALHADORVULNERAVELREGISTO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_trabalhadores_vulneraveis, LAYOUT_ACTIVITYTRABALHADORESVULNERAVEIS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_trabalho_realizado, LAYOUT_ACTIVITYTRABALHOREALIZADO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.activity_upload, LAYOUT_ACTIVITYUPLOAD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.dialogo_anomalia, LAYOUT_DIALOGOANOMALIA);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.dialogo_area_checklist, LAYOUT_DIALOGOAREACHECKLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.dialogo_atividade_pendente, LAYOUT_DIALOGOATIVIDADEPENDENTE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.dialogo_atividade_pendente_executada, LAYOUT_DIALOGOATIVIDADEPENDENTEEXECUTADA);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.dialogo_atividade_pendente_nao_executada, LAYOUT_DIALOGOATIVIDADEPENDENTENAOEXECUTADA);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.dialogo_categoria_profissional, LAYOUT_DIALOGOCATEGORIAPROFISSIONAL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.dialogo_checklist_pergunta, LAYOUT_DIALOGOCHECKLISTPERGUNTA);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.dialogo_colaborador, LAYOUT_DIALOGOCOLABORADOR);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.dialogo_email, LAYOUT_DIALOGOEMAIL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.dialogo_opcoes_colaborador, LAYOUT_DIALOGOOPCOESCOLABORADOR);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.dialogo_processo_produtivo, LAYOUT_DIALOGOPROCESSOPRODUTIVO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.dialogo_sinaletica, LAYOUT_DIALOGOSINALETICA);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.dialogo_trabalho_realizado, LAYOUT_DIALOGOTRABALHOREALIZADO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.fragment_anuidade, LAYOUT_FRAGMENTANUIDADE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.item_anomalia, LAYOUT_ITEMANOMALIA);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.item_anomalia_registada, LAYOUT_ITEMANOMALIAREGISTADA);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.item_atividade_executada, LAYOUT_ITEMATIVIDADEEXECUTADA);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.item_atividade_pendente, LAYOUT_ITEMATIVIDADEPENDENTE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.item_avaliacao_iluminacao, LAYOUT_ITEMAVALIACAOILUMINACAO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.item_avaliacao_temperatura_humidade, LAYOUT_ITEMAVALIACAOTEMPERATURAHUMIDADE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.item_categoria_profissional, LAYOUT_ITEMCATEGORIAPROFISSIONAL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.item_checklist_area, LAYOUT_ITEMCHECKLISTAREA);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.item_checklist_foto, LAYOUT_ITEMCHECKLISTFOTO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.item_checklist_observacao, LAYOUT_ITEMCHECKLISTOBSERVACAO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.item_checklist_pergunta, LAYOUT_ITEMCHECKLISTPERGUNTA);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.item_checklist_seccao, LAYOUT_ITEMCHECKLISTSECCAO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.item_checklist_uts, LAYOUT_ITEMCHECKLISTUTS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.item_colaborador, LAYOUT_ITEMCOLABORADOR);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.item_cross_selling, LAYOUT_ITEMCROSSSELLING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.item_extintor, LAYOUT_ITEMEXTINTOR);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.item_formando, LAYOUT_ITEMFORMANDO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.item_levantamento, LAYOUT_ITEMLEVANTAMENTO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.item_marcacao, LAYOUT_ITEMMARCACAO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.item_ocorrencia, LAYOUT_ITEMOCORRENCIA);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.item_ocorrencia_historico, LAYOUT_ITEMOCORRENCIAHISTORICO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.item_ocorrencia_registo, LAYOUT_ITEMOCORRENCIAREGISTO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.item_opcao_cliente, LAYOUT_ITEMOPCAOCLIENTE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.item_pendencia, LAYOUT_ITEMPENDENCIA);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.item_pesquisa, LAYOUT_ITEMPESQUISA);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.item_plano_acao_programacao, LAYOUT_ITEMPLANOACAOPROGRAMACAO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.item_plano_accao_medidas_avaliacao, LAYOUT_ITEMPLANOACCAOMEDIDASAVALIACAO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.item_plano_accao_st, LAYOUT_ITEMPLANOACCAOST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.item_risco, LAYOUT_ITEMRISCO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.item_tipo, LAYOUT_ITEMTIPO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.item_trabalhador_vulneravel, LAYOUT_ITEMTRABALHADORVULNERAVEL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.item_trabalho_realizado, LAYOUT_ITEMTRABALHOREALIZADO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.vvm.sh.R.layout.item_upload, LAYOUT_ITEMUPLOAD);
  }

  private final ViewDataBinding internalGetViewDataBinding0(DataBindingComponent component,
      View view, int internalId, Object tag) {
    switch(internalId) {
      case  LAYOUT_ACTIVITYACAOFORMACAO: {
        if ("layout/activity_acao_formacao_0".equals(tag)) {
          return new ActivityAcaoFormacaoBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_acao_formacao is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYANOMALIAS: {
        if ("layout/activity_anomalias_0".equals(tag)) {
          return new ActivityAnomaliasBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_anomalias is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYATIVIDADESEXECUTADAS: {
        if ("layout/activity_atividades_executadas_0".equals(tag)) {
          return new ActivityAtividadesExecutadasBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_atividades_executadas is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYATIVIDADESPENDENTES: {
        if ("layout/activity_atividades_pendentes_0".equals(tag)) {
          return new ActivityAtividadesPendentesBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_atividades_pendentes is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYATUALIZACAOAPP: {
        if ("layout/activity_atualizacao_app_0".equals(tag)) {
          return new ActivityAtualizacaoAppBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_atualizacao_app is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYAUTENTICACAO: {
        if ("layout/activity_autenticacao_0".equals(tag)) {
          return new ActivityAutenticacaoBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_autenticacao is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYAVALIACAOGERAL: {
        if ("layout/activity_avaliacao_geral_0".equals(tag)) {
          return new ActivityAvaliacaoGeralBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_avaliacao_geral is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYAVALIACAOILUMINACAOREGISTO: {
        if ("layout/activity_avaliacao_iluminacao_registo_0".equals(tag)) {
          return new ActivityAvaliacaoIluminacaoRegistoBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_avaliacao_iluminacao_registo is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYAVALIACAOTEMPERATURAHUMIDADEREGISTO: {
        if ("layout/activity_avaliacao_temperatura_humidade_registo_0".equals(tag)) {
          return new ActivityAvaliacaoTemperaturaHumidadeRegistoBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_avaliacao_temperatura_humidade_registo is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYAVALIACOESAMBIENTAIS: {
        if ("layout/activity_avaliacoes_ambientais_0".equals(tag)) {
          return new ActivityAvaliacoesAmbientaisBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_avaliacoes_ambientais is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYBASEDAGGER: {
        if ("layout/activity_base_dagger_0".equals(tag)) {
          return new ActivityBaseDaggerBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_base_dagger is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYCARREGAMENTOTIPOS: {
        if ("layout/activity_carregamento_tipos_0".equals(tag)) {
          return new ActivityCarregamentoTiposBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_carregamento_tipos is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYCATEGORIASPROFISSIONAIS: {
        if ("layout/activity_categorias_profissionais_0".equals(tag)) {
          return new ActivityCategoriasProfissionaisBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_categorias_profissionais is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYCHECKLIST: {
        if ("layout/activity_checklist_0".equals(tag)) {
          return new ActivityChecklistBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_checklist is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYCOLABORADOR: {
        if ("layout/activity_colaborador_0".equals(tag)) {
          return new ActivityColaboradorBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_colaborador is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYCROSSSELLING: {
        if ("layout/activity_cross_selling_0".equals(tag)) {
          return new ActivityCrossSellingBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_cross_selling is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYDADOSCLIENTE: {
        if ("layout/activity_dados_cliente_0".equals(tag)) {
          return new ActivityDadosClienteBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_dados_cliente is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYDOWNLOADTRABALHO: {
        if ("layout/activity_download_trabalho_0".equals(tag)) {
          return new ActivityDownloadTrabalhoBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_download_trabalho is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYEXTINTORES: {
        if ("layout/activity_extintores_0".equals(tag)) {
          return new ActivityExtintoresBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_extintores is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYFORMACAO: {
        if ("layout/activity_formacao_0".equals(tag)) {
          return new ActivityFormacaoBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_formacao is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYFORMANDO: {
        if ("layout/activity_formando_0".equals(tag)) {
          return new ActivityFormandoBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_formando is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYINFORMACAO: {
        if ("layout/activity_informacao_0".equals(tag)) {
          return new ActivityInformacaoBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_informacao is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYLEVANTAMENTOS: {
        if ("layout/activity_levantamentos_0".equals(tag)) {
          return new ActivityLevantamentosBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_levantamentos is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYMAIN: {
        if ("layout/activity_main_0".equals(tag)) {
          return new ActivityMainBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYOCORRENCIAREGISTAR: {
        if ("layout/activity_ocorrencia_registar_0".equals(tag)) {
          return new ActivityOcorrenciaRegistarBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_ocorrencia_registar is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYOCORRENCIAS: {
        if ("layout/activity_ocorrencias_0".equals(tag)) {
          return new ActivityOcorrenciasBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_ocorrencias is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYOCORRENCIASHISTORICO: {
        if ("layout/activity_ocorrencias_historico_0".equals(tag)) {
          return new ActivityOcorrenciasHistoricoBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_ocorrencias_historico is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYOCORRENCIASREGISTO: {
        if ("layout/activity_ocorrencias_registo_0".equals(tag)) {
          return new ActivityOcorrenciasRegistoBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_ocorrencias_registo is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYPERFIL: {
        if ("layout/activity_perfil_0".equals(tag)) {
          return new ActivityPerfilBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_perfil is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYPERIGOTAREFA: {
        if ("layout/activity_perigo_tarefa_0".equals(tag)) {
          return new ActivityPerigoTarefaBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_perigo_tarefa is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYPESQUISA: {
        if ("layout/activity_pesquisa_0".equals(tag)) {
          return new ActivityPesquisaBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_pesquisa is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYPLANOACCAO: {
        if ("layout/activity_plano_accao_0".equals(tag)) {
          return new ActivityPlanoAccaoBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_plano_accao is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYPROPOSTAPLANOACCAO: {
        if ("layout/activity_proposta_plano_accao_0".equals(tag)) {
          return new ActivityPropostaPlanoAccaoBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_proposta_plano_accao is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYQUADROPESSOAL: {
        if ("layout/activity_quadro_pessoal_0".equals(tag)) {
          return new ActivityQuadroPessoalBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_quadro_pessoal is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYQUESTOESCHECKLIST: {
        if ("layout/activity_questoes_checklist_0".equals(tag)) {
          return new ActivityQuestoesChecklistBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_questoes_checklist is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYREGISTOVISITA: {
        if ("layout/activity_registo_visita_0".equals(tag)) {
          return new ActivityRegistoVisitaBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_registo_visita is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYRELATORIOAVALIACAOAMBIENTAL: {
        if ("layout/activity_relatorio_avaliacao_ambiental_0".equals(tag)) {
          return new ActivityRelatorioAvaliacaoAmbientalBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_relatorio_avaliacao_ambiental is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYRISCOREGISTO: {
        if ("layout/activity_risco_registo_0".equals(tag)) {
          return new ActivityRiscoRegistoBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_risco_registo is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYRISCOS: {
        if ("layout/activity_riscos_0".equals(tag)) {
          return new ActivityRiscosBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_riscos is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYSINISTRALIDADE: {
        if ("layout/activity_sinistralidade_0".equals(tag)) {
          return new ActivitySinistralidadeBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_sinistralidade is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYTAREFA: {
        if ("layout/activity_tarefa_0".equals(tag)) {
          return new ActivityTarefaBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_tarefa is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYTIPOS: {
        if ("layout/activity_tipos_0".equals(tag)) {
          return new ActivityTiposBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_tipos is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYTRABALHADORVULNERAVELREGISTO: {
        if ("layout/activity_trabalhador_vulneravel_registo_0".equals(tag)) {
          return new ActivityTrabalhadorVulneravelRegistoBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_trabalhador_vulneravel_registo is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYTRABALHADORESVULNERAVEIS: {
        if ("layout/activity_trabalhadores_vulneraveis_0".equals(tag)) {
          return new ActivityTrabalhadoresVulneraveisBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_trabalhadores_vulneraveis is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYTRABALHOREALIZADO: {
        if ("layout/activity_trabalho_realizado_0".equals(tag)) {
          return new ActivityTrabalhoRealizadoBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_trabalho_realizado is invalid. Received: " + tag);
      }
      case  LAYOUT_ACTIVITYUPLOAD: {
        if ("layout/activity_upload_0".equals(tag)) {
          return new ActivityUploadBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for activity_upload is invalid. Received: " + tag);
      }
      case  LAYOUT_DIALOGOANOMALIA: {
        if ("layout/dialogo_anomalia_0".equals(tag)) {
          return new DialogoAnomaliaBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for dialogo_anomalia is invalid. Received: " + tag);
      }
      case  LAYOUT_DIALOGOAREACHECKLIST: {
        if ("layout/dialogo_area_checklist_0".equals(tag)) {
          return new DialogoAreaChecklistBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for dialogo_area_checklist is invalid. Received: " + tag);
      }
      case  LAYOUT_DIALOGOATIVIDADEPENDENTE: {
        if ("layout/dialogo_atividade_pendente_0".equals(tag)) {
          return new DialogoAtividadePendenteBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for dialogo_atividade_pendente is invalid. Received: " + tag);
      }
      case  LAYOUT_DIALOGOATIVIDADEPENDENTEEXECUTADA: {
        if ("layout/dialogo_atividade_pendente_executada_0".equals(tag)) {
          return new DialogoAtividadePendenteExecutadaBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for dialogo_atividade_pendente_executada is invalid. Received: " + tag);
      }
    }
    return null;
  }

  private final ViewDataBinding internalGetViewDataBinding1(DataBindingComponent component,
      View view, int internalId, Object tag) {
    switch(internalId) {
      case  LAYOUT_DIALOGOATIVIDADEPENDENTENAOEXECUTADA: {
        if ("layout/dialogo_atividade_pendente_nao_executada_0".equals(tag)) {
          return new DialogoAtividadePendenteNaoExecutadaBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for dialogo_atividade_pendente_nao_executada is invalid. Received: " + tag);
      }
      case  LAYOUT_DIALOGOCATEGORIAPROFISSIONAL: {
        if ("layout/dialogo_categoria_profissional_0".equals(tag)) {
          return new DialogoCategoriaProfissionalBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for dialogo_categoria_profissional is invalid. Received: " + tag);
      }
      case  LAYOUT_DIALOGOCHECKLISTPERGUNTA: {
        if ("layout/dialogo_checklist_pergunta_0".equals(tag)) {
          return new DialogoChecklistPerguntaBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for dialogo_checklist_pergunta is invalid. Received: " + tag);
      }
      case  LAYOUT_DIALOGOCOLABORADOR: {
        if ("layout/dialogo_colaborador_0".equals(tag)) {
          return new DialogoColaboradorBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for dialogo_colaborador is invalid. Received: " + tag);
      }
      case  LAYOUT_DIALOGOEMAIL: {
        if ("layout/dialogo_email_0".equals(tag)) {
          return new DialogoEmailBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for dialogo_email is invalid. Received: " + tag);
      }
      case  LAYOUT_DIALOGOOPCOESCOLABORADOR: {
        if ("layout/dialogo_opcoes_colaborador_0".equals(tag)) {
          return new DialogoOpcoesColaboradorBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for dialogo_opcoes_colaborador is invalid. Received: " + tag);
      }
      case  LAYOUT_DIALOGOPROCESSOPRODUTIVO: {
        if ("layout/dialogo_processo_produtivo_0".equals(tag)) {
          return new DialogoProcessoProdutivoBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for dialogo_processo_produtivo is invalid. Received: " + tag);
      }
      case  LAYOUT_DIALOGOSINALETICA: {
        if ("layout/dialogo_sinaletica_0".equals(tag)) {
          return new DialogoSinaleticaBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for dialogo_sinaletica is invalid. Received: " + tag);
      }
      case  LAYOUT_DIALOGOTRABALHOREALIZADO: {
        if ("layout/dialogo_trabalho_realizado_0".equals(tag)) {
          return new DialogoTrabalhoRealizadoBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for dialogo_trabalho_realizado is invalid. Received: " + tag);
      }
      case  LAYOUT_FRAGMENTANUIDADE: {
        if ("layout/fragment_anuidade_0".equals(tag)) {
          return new FragmentAnuidadeBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for fragment_anuidade is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMANOMALIA: {
        if ("layout/item_anomalia_0".equals(tag)) {
          return new ItemAnomaliaBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_anomalia is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMANOMALIAREGISTADA: {
        if ("layout/item_anomalia_registada_0".equals(tag)) {
          return new ItemAnomaliaRegistadaBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_anomalia_registada is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMATIVIDADEEXECUTADA: {
        if ("layout/item_atividade_executada_0".equals(tag)) {
          return new ItemAtividadeExecutadaBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_atividade_executada is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMATIVIDADEPENDENTE: {
        if ("layout/item_atividade_pendente_0".equals(tag)) {
          return new ItemAtividadePendenteBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_atividade_pendente is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMAVALIACAOILUMINACAO: {
        if ("layout/item_avaliacao_iluminacao_0".equals(tag)) {
          return new ItemAvaliacaoIluminacaoBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_avaliacao_iluminacao is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMAVALIACAOTEMPERATURAHUMIDADE: {
        if ("layout/item_avaliacao_temperatura_humidade_0".equals(tag)) {
          return new ItemAvaliacaoTemperaturaHumidadeBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_avaliacao_temperatura_humidade is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMCATEGORIAPROFISSIONAL: {
        if ("layout/item_categoria_profissional_0".equals(tag)) {
          return new ItemCategoriaProfissionalBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_categoria_profissional is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMCHECKLISTAREA: {
        if ("layout/item_checklist_area_0".equals(tag)) {
          return new ItemChecklistAreaBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_checklist_area is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMCHECKLISTFOTO: {
        if ("layout/item_checklist_foto_0".equals(tag)) {
          return new ItemChecklistFotoBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_checklist_foto is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMCHECKLISTOBSERVACAO: {
        if ("layout/item_checklist_observacao_0".equals(tag)) {
          return new ItemChecklistObservacaoBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_checklist_observacao is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMCHECKLISTPERGUNTA: {
        if ("layout/item_checklist_pergunta_0".equals(tag)) {
          return new ItemChecklistPerguntaBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_checklist_pergunta is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMCHECKLISTSECCAO: {
        if ("layout/item_checklist_seccao_0".equals(tag)) {
          return new ItemChecklistSeccaoBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_checklist_seccao is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMCHECKLISTUTS: {
        if ("layout/item_checklist_uts_0".equals(tag)) {
          return new ItemChecklistUtsBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_checklist_uts is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMCOLABORADOR: {
        if ("layout/item_colaborador_0".equals(tag)) {
          return new ItemColaboradorBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_colaborador is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMCROSSSELLING: {
        if ("layout/item_cross_selling_0".equals(tag)) {
          return new ItemCrossSellingBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_cross_selling is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMEXTINTOR: {
        if ("layout/item_extintor_0".equals(tag)) {
          return new ItemExtintorBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_extintor is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMFORMANDO: {
        if ("layout/item_formando_0".equals(tag)) {
          return new ItemFormandoBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_formando is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMLEVANTAMENTO: {
        if ("layout/item_levantamento_0".equals(tag)) {
          return new ItemLevantamentoBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_levantamento is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMMARCACAO: {
        if ("layout/item_marcacao_0".equals(tag)) {
          return new ItemMarcacaoBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_marcacao is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMOCORRENCIA: {
        if ("layout/item_ocorrencia_0".equals(tag)) {
          return new ItemOcorrenciaBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_ocorrencia is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMOCORRENCIAHISTORICO: {
        if ("layout/item_ocorrencia_historico_0".equals(tag)) {
          return new ItemOcorrenciaHistoricoBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_ocorrencia_historico is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMOCORRENCIAREGISTO: {
        if ("layout/item_ocorrencia_registo_0".equals(tag)) {
          return new ItemOcorrenciaRegistoBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_ocorrencia_registo is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMOPCAOCLIENTE: {
        if ("layout/item_opcao_cliente_0".equals(tag)) {
          return new ItemOpcaoClienteBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_opcao_cliente is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMPENDENCIA: {
        if ("layout/item_pendencia_0".equals(tag)) {
          return new ItemPendenciaBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_pendencia is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMPESQUISA: {
        if ("layout/item_pesquisa_0".equals(tag)) {
          return new ItemPesquisaBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_pesquisa is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMPLANOACAOPROGRAMACAO: {
        if ("layout/item_plano_acao_programacao_0".equals(tag)) {
          return new ItemPlanoAcaoProgramacaoBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_plano_acao_programacao is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMPLANOACCAOMEDIDASAVALIACAO: {
        if ("layout/item_plano_accao_medidas_avaliacao_0".equals(tag)) {
          return new ItemPlanoAccaoMedidasAvaliacaoBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_plano_accao_medidas_avaliacao is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMPLANOACCAOST: {
        if ("layout/item_plano_accao_st_0".equals(tag)) {
          return new ItemPlanoAccaoStBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_plano_accao_st is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMRISCO: {
        if ("layout/item_risco_0".equals(tag)) {
          return new ItemRiscoBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_risco is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMTIPO: {
        if ("layout/item_tipo_0".equals(tag)) {
          return new ItemTipoBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_tipo is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMTRABALHADORVULNERAVEL: {
        if ("layout/item_trabalhador_vulneravel_0".equals(tag)) {
          return new ItemTrabalhadorVulneravelBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_trabalhador_vulneravel is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMTRABALHOREALIZADO: {
        if ("layout/item_trabalho_realizado_0".equals(tag)) {
          return new ItemTrabalhoRealizadoBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_trabalho_realizado is invalid. Received: " + tag);
      }
      case  LAYOUT_ITEMUPLOAD: {
        if ("layout/item_upload_0".equals(tag)) {
          return new ItemUploadBindingImpl(component, view);
        }
        throw new IllegalArgumentException("The tag for item_upload is invalid. Received: " + tag);
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      // find which method will have it. -1 is necessary becausefirst id starts with 1;
      int methodIndex = (localizedLayoutId - 1) / 50;
      switch(methodIndex) {
        case 0: {
          return internalGetViewDataBinding0(component, view, localizedLayoutId, tag);
        }
        case 1: {
          return internalGetViewDataBinding1(component, view, localizedLayoutId, tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(34);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "bloquear");
      sKeys.put(2, "opcao");
      sKeys.put(3, "tipo");
      sKeys.put(4, "listenerRegisto");
      sKeys.put(5, "activity");
      sKeys.put(6, "levantamento");
      sKeys.put(7, "idArea");
      sKeys.put(8, "upload");
      sKeys.put(9, "listener");
      sKeys.put(10, "utilizadoresTestes");
      sKeys.put(11, "marcacao");
      sKeys.put(12, "anomalia");
      sKeys.put(13, "crossSelling");
      sKeys.put(14, "questao");
      sKeys.put(15, "baseviewmodel");
      sKeys.put(16, "atividades");
      sKeys.put(17, "extintor");
      sKeys.put(18, "item");
      sKeys.put(19, "viewmodel");
      sKeys.put(20, "proposta");
      sKeys.put(21, "categoria");
      sKeys.put(22, "vulnerabilidade");
      sKeys.put(23, "listenerSelecionado");
      sKeys.put(24, "avaliacao");
      sKeys.put(25, "colaborador");
      sKeys.put(26, "pesquisa");
      sKeys.put(27, "atividade");
      sKeys.put(28, "ocorrencia");
      sKeys.put(29, "trabalho");
      sKeys.put(30, "pendencia");
      sKeys.put(31, "formando");
      sKeys.put(32, "risco");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(93);

    static {
      sKeys.put("layout/activity_acao_formacao_0", com.vvm.sh.R.layout.activity_acao_formacao);
      sKeys.put("layout/activity_anomalias_0", com.vvm.sh.R.layout.activity_anomalias);
      sKeys.put("layout/activity_atividades_executadas_0", com.vvm.sh.R.layout.activity_atividades_executadas);
      sKeys.put("layout/activity_atividades_pendentes_0", com.vvm.sh.R.layout.activity_atividades_pendentes);
      sKeys.put("layout/activity_atualizacao_app_0", com.vvm.sh.R.layout.activity_atualizacao_app);
      sKeys.put("layout/activity_autenticacao_0", com.vvm.sh.R.layout.activity_autenticacao);
      sKeys.put("layout/activity_avaliacao_geral_0", com.vvm.sh.R.layout.activity_avaliacao_geral);
      sKeys.put("layout/activity_avaliacao_iluminacao_registo_0", com.vvm.sh.R.layout.activity_avaliacao_iluminacao_registo);
      sKeys.put("layout/activity_avaliacao_temperatura_humidade_registo_0", com.vvm.sh.R.layout.activity_avaliacao_temperatura_humidade_registo);
      sKeys.put("layout/activity_avaliacoes_ambientais_0", com.vvm.sh.R.layout.activity_avaliacoes_ambientais);
      sKeys.put("layout/activity_base_dagger_0", com.vvm.sh.R.layout.activity_base_dagger);
      sKeys.put("layout/activity_carregamento_tipos_0", com.vvm.sh.R.layout.activity_carregamento_tipos);
      sKeys.put("layout/activity_categorias_profissionais_0", com.vvm.sh.R.layout.activity_categorias_profissionais);
      sKeys.put("layout/activity_checklist_0", com.vvm.sh.R.layout.activity_checklist);
      sKeys.put("layout/activity_colaborador_0", com.vvm.sh.R.layout.activity_colaborador);
      sKeys.put("layout/activity_cross_selling_0", com.vvm.sh.R.layout.activity_cross_selling);
      sKeys.put("layout/activity_dados_cliente_0", com.vvm.sh.R.layout.activity_dados_cliente);
      sKeys.put("layout/activity_download_trabalho_0", com.vvm.sh.R.layout.activity_download_trabalho);
      sKeys.put("layout/activity_extintores_0", com.vvm.sh.R.layout.activity_extintores);
      sKeys.put("layout/activity_formacao_0", com.vvm.sh.R.layout.activity_formacao);
      sKeys.put("layout/activity_formando_0", com.vvm.sh.R.layout.activity_formando);
      sKeys.put("layout/activity_informacao_0", com.vvm.sh.R.layout.activity_informacao);
      sKeys.put("layout/activity_levantamentos_0", com.vvm.sh.R.layout.activity_levantamentos);
      sKeys.put("layout/activity_main_0", com.vvm.sh.R.layout.activity_main);
      sKeys.put("layout/activity_ocorrencia_registar_0", com.vvm.sh.R.layout.activity_ocorrencia_registar);
      sKeys.put("layout/activity_ocorrencias_0", com.vvm.sh.R.layout.activity_ocorrencias);
      sKeys.put("layout/activity_ocorrencias_historico_0", com.vvm.sh.R.layout.activity_ocorrencias_historico);
      sKeys.put("layout/activity_ocorrencias_registo_0", com.vvm.sh.R.layout.activity_ocorrencias_registo);
      sKeys.put("layout/activity_perfil_0", com.vvm.sh.R.layout.activity_perfil);
      sKeys.put("layout/activity_perigo_tarefa_0", com.vvm.sh.R.layout.activity_perigo_tarefa);
      sKeys.put("layout/activity_pesquisa_0", com.vvm.sh.R.layout.activity_pesquisa);
      sKeys.put("layout/activity_plano_accao_0", com.vvm.sh.R.layout.activity_plano_accao);
      sKeys.put("layout/activity_proposta_plano_accao_0", com.vvm.sh.R.layout.activity_proposta_plano_accao);
      sKeys.put("layout/activity_quadro_pessoal_0", com.vvm.sh.R.layout.activity_quadro_pessoal);
      sKeys.put("layout/activity_questoes_checklist_0", com.vvm.sh.R.layout.activity_questoes_checklist);
      sKeys.put("layout/activity_registo_visita_0", com.vvm.sh.R.layout.activity_registo_visita);
      sKeys.put("layout/activity_relatorio_avaliacao_ambiental_0", com.vvm.sh.R.layout.activity_relatorio_avaliacao_ambiental);
      sKeys.put("layout/activity_risco_registo_0", com.vvm.sh.R.layout.activity_risco_registo);
      sKeys.put("layout/activity_riscos_0", com.vvm.sh.R.layout.activity_riscos);
      sKeys.put("layout/activity_sinistralidade_0", com.vvm.sh.R.layout.activity_sinistralidade);
      sKeys.put("layout/activity_tarefa_0", com.vvm.sh.R.layout.activity_tarefa);
      sKeys.put("layout/activity_tipos_0", com.vvm.sh.R.layout.activity_tipos);
      sKeys.put("layout/activity_trabalhador_vulneravel_registo_0", com.vvm.sh.R.layout.activity_trabalhador_vulneravel_registo);
      sKeys.put("layout/activity_trabalhadores_vulneraveis_0", com.vvm.sh.R.layout.activity_trabalhadores_vulneraveis);
      sKeys.put("layout/activity_trabalho_realizado_0", com.vvm.sh.R.layout.activity_trabalho_realizado);
      sKeys.put("layout/activity_upload_0", com.vvm.sh.R.layout.activity_upload);
      sKeys.put("layout/dialogo_anomalia_0", com.vvm.sh.R.layout.dialogo_anomalia);
      sKeys.put("layout/dialogo_area_checklist_0", com.vvm.sh.R.layout.dialogo_area_checklist);
      sKeys.put("layout/dialogo_atividade_pendente_0", com.vvm.sh.R.layout.dialogo_atividade_pendente);
      sKeys.put("layout/dialogo_atividade_pendente_executada_0", com.vvm.sh.R.layout.dialogo_atividade_pendente_executada);
      sKeys.put("layout/dialogo_atividade_pendente_nao_executada_0", com.vvm.sh.R.layout.dialogo_atividade_pendente_nao_executada);
      sKeys.put("layout/dialogo_categoria_profissional_0", com.vvm.sh.R.layout.dialogo_categoria_profissional);
      sKeys.put("layout/dialogo_checklist_pergunta_0", com.vvm.sh.R.layout.dialogo_checklist_pergunta);
      sKeys.put("layout/dialogo_colaborador_0", com.vvm.sh.R.layout.dialogo_colaborador);
      sKeys.put("layout/dialogo_email_0", com.vvm.sh.R.layout.dialogo_email);
      sKeys.put("layout/dialogo_opcoes_colaborador_0", com.vvm.sh.R.layout.dialogo_opcoes_colaborador);
      sKeys.put("layout/dialogo_processo_produtivo_0", com.vvm.sh.R.layout.dialogo_processo_produtivo);
      sKeys.put("layout/dialogo_sinaletica_0", com.vvm.sh.R.layout.dialogo_sinaletica);
      sKeys.put("layout/dialogo_trabalho_realizado_0", com.vvm.sh.R.layout.dialogo_trabalho_realizado);
      sKeys.put("layout/fragment_anuidade_0", com.vvm.sh.R.layout.fragment_anuidade);
      sKeys.put("layout/item_anomalia_0", com.vvm.sh.R.layout.item_anomalia);
      sKeys.put("layout/item_anomalia_registada_0", com.vvm.sh.R.layout.item_anomalia_registada);
      sKeys.put("layout/item_atividade_executada_0", com.vvm.sh.R.layout.item_atividade_executada);
      sKeys.put("layout/item_atividade_pendente_0", com.vvm.sh.R.layout.item_atividade_pendente);
      sKeys.put("layout/item_avaliacao_iluminacao_0", com.vvm.sh.R.layout.item_avaliacao_iluminacao);
      sKeys.put("layout/item_avaliacao_temperatura_humidade_0", com.vvm.sh.R.layout.item_avaliacao_temperatura_humidade);
      sKeys.put("layout/item_categoria_profissional_0", com.vvm.sh.R.layout.item_categoria_profissional);
      sKeys.put("layout/item_checklist_area_0", com.vvm.sh.R.layout.item_checklist_area);
      sKeys.put("layout/item_checklist_foto_0", com.vvm.sh.R.layout.item_checklist_foto);
      sKeys.put("layout/item_checklist_observacao_0", com.vvm.sh.R.layout.item_checklist_observacao);
      sKeys.put("layout/item_checklist_pergunta_0", com.vvm.sh.R.layout.item_checklist_pergunta);
      sKeys.put("layout/item_checklist_seccao_0", com.vvm.sh.R.layout.item_checklist_seccao);
      sKeys.put("layout/item_checklist_uts_0", com.vvm.sh.R.layout.item_checklist_uts);
      sKeys.put("layout/item_colaborador_0", com.vvm.sh.R.layout.item_colaborador);
      sKeys.put("layout/item_cross_selling_0", com.vvm.sh.R.layout.item_cross_selling);
      sKeys.put("layout/item_extintor_0", com.vvm.sh.R.layout.item_extintor);
      sKeys.put("layout/item_formando_0", com.vvm.sh.R.layout.item_formando);
      sKeys.put("layout/item_levantamento_0", com.vvm.sh.R.layout.item_levantamento);
      sKeys.put("layout/item_marcacao_0", com.vvm.sh.R.layout.item_marcacao);
      sKeys.put("layout/item_ocorrencia_0", com.vvm.sh.R.layout.item_ocorrencia);
      sKeys.put("layout/item_ocorrencia_historico_0", com.vvm.sh.R.layout.item_ocorrencia_historico);
      sKeys.put("layout/item_ocorrencia_registo_0", com.vvm.sh.R.layout.item_ocorrencia_registo);
      sKeys.put("layout/item_opcao_cliente_0", com.vvm.sh.R.layout.item_opcao_cliente);
      sKeys.put("layout/item_pendencia_0", com.vvm.sh.R.layout.item_pendencia);
      sKeys.put("layout/item_pesquisa_0", com.vvm.sh.R.layout.item_pesquisa);
      sKeys.put("layout/item_plano_acao_programacao_0", com.vvm.sh.R.layout.item_plano_acao_programacao);
      sKeys.put("layout/item_plano_accao_medidas_avaliacao_0", com.vvm.sh.R.layout.item_plano_accao_medidas_avaliacao);
      sKeys.put("layout/item_plano_accao_st_0", com.vvm.sh.R.layout.item_plano_accao_st);
      sKeys.put("layout/item_risco_0", com.vvm.sh.R.layout.item_risco);
      sKeys.put("layout/item_tipo_0", com.vvm.sh.R.layout.item_tipo);
      sKeys.put("layout/item_trabalhador_vulneravel_0", com.vvm.sh.R.layout.item_trabalhador_vulneravel);
      sKeys.put("layout/item_trabalho_realizado_0", com.vvm.sh.R.layout.item_trabalho_realizado);
      sKeys.put("layout/item_upload_0", com.vvm.sh.R.layout.item_upload);
    }
  }
}
