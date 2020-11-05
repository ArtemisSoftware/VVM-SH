package com.vvm.sh.util.mapeamento;

import com.vvm.sh.api.modelos.bd.AtividadePendenteBd;
import com.vvm.sh.api.modelos.envio.sht.Email;
import com.vvm.sh.api.modelos.envio.sht.AtividadePendenteExecutada;
import com.vvm.sh.api.modelos.envio.sht.AtividadePendenteNaoExecutada;
import com.vvm.sh.baseDados.entidades.EmailResultado;
import com.vvm.sh.util.metodos.DatasUtil;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(uses= ImagemMapper.class)
public interface UploadSHMapping {


    static final UploadSHMapping INSTANCE = Mappers.getMapper( UploadSHMapping.class );

    @Mapping(target = "servId", source = "atividade.servId")
    @Mapping(target = "idAnomalia", source = "resultado.idAnomalia")
    @Mapping(target = "observacao", source = "resultado.observacao")
    AtividadePendenteNaoExecutada mapAtividadeNaoExecutada(AtividadePendenteBd item);


    @Mapping(target = "servId", source = "atividade.servId")
    @Mapping(target = "tempoExecucao", source = "resultado.tempoExecucao")
    @Mapping(target = "dataExecucao", source = "resultado.dataExecucao", dateFormat = DatasUtil.FORMATO_YYYY_MM_DD)
    AtividadePendenteExecutada mapAtividadeExecutada(AtividadePendenteBd item);


    @Mapping(target = "email", source = "endereco")
    Email map(EmailResultado item);



//    @Mapping(source = "idAnomalia", target = "idAnomalia")
//    @Mapping(source = "observacao", target = "observacoes")
//    Anomalia map(AnomaliaResultado item);
//
//
//
//    CrossSelling map(CrossSellingResultado item);
//
//    @Mapping(source = "id", target = "idOcorrencia")
//    @Mapping(source = "observacao", target = "observacao")
//    Ocorrencia map(OcorrenciaResultado item);
//
//
//
//
//    @Mapping(source = "data", target = "data", dateFormat = DatasUtil.FORMATO_YYYY_MM_DD)
//    @Mapping(source = "inicio", target = "inicio", dateFormat = DatasUtil.HORA_FORMATO_HH_MM)
//    @Mapping(source = "termino", target = "termino", dateFormat = DatasUtil.HORA_FORMATO_HH_MM)
//    AcaoFormacao map(AcaoFormacaoResultado item);
//
//    @Mapping(source = "sexo", target = "genero")
//    @Mapping(source = "dataNascimento", target = "dataNascimento", dateFormat = DatasUtil.FORMATO_YYYY_MM_DD)
//    Formando map(FormandoResultado item);
//
//    @Mapping(source = "prefixoCt", target = "prefixoCT")
//    @Mapping(source = "data", target = "data", dateFormat = DatasUtil.FORMATO_YYYY_MM_DD)
//    Sessao map(Tarefa item);
//
//
//    RegistoVisita map(RegistoVisitaResultado obterRegistoVisita);
//
//    TrabalhoRealizado map(TrabalhoRealizadoResultado item);
//
//
//    @Mapping(target = "servId", source = "servId")
//    @Mapping(target = "servicoTP", source = "servicoTp")
//    @Mapping(target = "ordem", source = "ordem")
//    @Mapping(target = "data", source = "data")
//    AtividadePlanoAcao map(AtividadePlanoAcaoBd item);
//
//    //TODO: verificar e completar
//
//    @Mapping(target = "idChecklist", source = "id")
//    @Mapping(target = "versao", source = "idPai")
//    Checklist map(Tipo checklist);
//
//    @Mapping(target = "idArea", source = "area.resultado.idArea")
//    @Mapping(target = "descricao", source = "descricao")
//    @Mapping(target = "subDescricao", source = "area.resultado.subDescricao")
//    Area map(AreaBd area);
//
//
//
//    @Mapping(target = "idItem", source = "idItem")
//    @Mapping(target = "resposta", source = "resposta")
//    @Mapping(target = "nr", source = "ni")
//    Pergunta mapPerguntaChecklist(QuestionarioChecklistResultado questao);
//
//    @Mapping(target = "idItem", source = "idItem")
//    @Mapping(target = "resposta", source = "observacao")
//    Observacao mapObservacaoChecklist(QuestionarioChecklistResultado observacao);
//
//    @Mapping(target = "idItem", source = "idItem")
//    @Mapping(target = "idUT1", source = "ut1")
//    @Mapping(target = "idCategoriasRiscoUT_1", source = "ut1_CategoriasRisco")
//    @Mapping(target = "localRiscoA_ut_1", source = "ut1_LocalRisco_A")
//    @Mapping(target = "localRiscoB_ut_1", source = "ut1_LocalRisco_B")
//    @Mapping(target = "localRiscoC_ut_1", source = "ut1_LocalRisco_C")
//    @Mapping(target = "localRiscoD_ut_1", source = "ut1_LocalRisco_D")
//    @Mapping(target = "localRiscoE_ut_1", source = "ut1_LocalRisco_E")
//    @Mapping(target = "localRiscoF_ut_1", source = "ut1_LocalRisco_F")
//    @Mapping(target = "idUT2", source = "ut2")
//    @Mapping(target = "idCategoriasRiscoUT_2", source = "ut2_CategoriasRisco")
//    @Mapping(target = "localRiscoA_ut_2", source = "ut2_LocalRisco_A")
//    @Mapping(target = "localRiscoB_ut_2", source = "ut2_LocalRisco_B")
//    @Mapping(target = "localRiscoC_ut_2", source = "ut2_LocalRisco_C")
//    @Mapping(target = "localRiscoD_ut_2", source = "ut2_LocalRisco_D")
//    @Mapping(target = "localRiscoE_ut_2", source = "ut2_LocalRisco_E")
//    @Mapping(target = "localRiscoF_ut_2", source = "ut2_LocalRisco_F")
//    Ut mapUtChecklist(QuestionarioChecklistResultado ut);
//
////    @Mapping(target = "idItem", source = "idImagem")
////    ItemSeccaoChecklist mapImagemChecklist(ImagemResultado imagem);
//
//
//    @Mapping(target = "quantidadeHomens", source = "homens")
//    @Mapping(target = "quantidadeMulheres", source = "mulheres")
//    TrabalhadorVulneravel map(TrabalhadorVulneravelResultado item);
//
//    @Mapping(target = "medidaRecomendada", source = "idMedidaRecomendada")
//    @Mapping(target = "marca", source = "relatorio.resultado.marca")
//    @Mapping(target = "numeroSerie", source = "relatorio.resultado.numeroSerie")
//    @Mapping(target = "nebulosidade", source = "relatorio.resultado.idNebulosidade")
//    RelatorioAmbiental map(RelatorioAmbientalBd relatorio);
//
//
//
//
//    @Mapping(target = "descricaoArea", source = "anexoArea")
//    AvaliacaoTemperaturaHumidade map(AvaliacaoAmbientalResultado item);
//
//    @Mapping(target = "idTipoIluminacao", source = "tipoIluminacao")
//    @Mapping(target = "emedioElx", source = "emedioLx")
//    @Mapping(target = "descricaoArea", source = "anexoArea")
//    AvaliacaoIluminacao mapeamentoIluminacao(AvaliacaoAmbientalResultado item);
//
//
//    Risco map(RiscoResultado item);
//    Levantamento map(LevantamentoRiscoResultado item);
//
//
//    @Mapping(source = "idImagem", target = "idFoto")
//    @Mapping(source = "imagem", target = "foto")
//    Imagem map(ImagemResultado item);
//
//    @Mapping(target = "dadosFormulario", source = "dados")
//    @Mapping(target = "novasMaquinas", source = "equipamentos")
//    @Mapping(target = "versaoApp", source = "versao")
//    @Mapping(target = "numeroFicheiroImagens", source = "idBloco")
//    BlocoDados map(DadosUpload dadosUpload);
//
//    @Mapping(target = "dadosFormulario", source = "dados")
//    @Mapping(target = "versaoApp", source = "versao")
//    @Mapping(target = "numeroFicheiroImagens", source = "idBloco")
//    BlocoDados mapDadosSA(DadosUpload dadosUpload);
//
//
//
//    @Mapping(target = "diasUteis", source = "diasUteisPerdidos")
//    Sinistralidade map(SinistralidadeResultado item);
//
//    @Mapping(target = "idColaborador", source = "id")
//    @Mapping(target = "genero", source = "sexo")
//    @Mapping(target = "dataAdmissao", ignore = true)
//    @Mapping(target = "dataAdmissaoFuncao", ignore = true)
//    @Mapping(target = "dataNascimento", ignore = true)
//    Colaborador map(ColaboradorBd item);
//
//    @Mapping(target = "id", ignore = true)
//    Equipamento map(TipoNovo item);
//
//    @Mapping(target = "idServico", source = "extintor.idServico")
//    @Mapping(target = "dataValidade", ignore = true)
//    Extintor map(ParqueExtintor extintor, ParqueExtintorResultado resultado);
//
//    @Mapping(target = "seccao", source = "descricao")
//    RelatorioAveriguacao map(RelatorioAveriguacaoBd item);
//
//    @Mapping(target = "medidaImplementada", source = "implementado")
//    @Mapping(target = "risco", source = "risco")
//    @Mapping(target = "ponderacao", source = "idPonderacao")
//    @Mapping(target = "idMedida", source = "idMedida")
//        //@Mapping(target = "novaMedida", source = "descricao")
//    MedidaAveriguacao map(RelatorioAveriguacaoResultado medida);


//
//    static final UploadSHMapping INSTANCE = Mappers.getMapper( UploadSHMapping.class );
//
//

}
