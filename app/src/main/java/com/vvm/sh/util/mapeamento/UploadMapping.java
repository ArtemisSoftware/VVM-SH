package com.vvm.sh.util.mapeamento;

import com.vvm.sh.api.modelos.bd.AreaBd;
import com.vvm.sh.api.modelos.bd.AtividadePendenteBd;
import com.vvm.sh.api.modelos.bd.AtividadePlanoAcaoBd;
import com.vvm.sh.api.modelos.bd.RelatorioAmbientalBd;
import com.vvm.sh.api.modelos.envio.Area;
import com.vvm.sh.api.modelos.envio.AtividadePlanoAcao;
import com.vvm.sh.api.modelos.envio.AvaliacaoIluminacao;
import com.vvm.sh.api.modelos.envio.AvaliacaoTemperaturaHumidade;
import com.vvm.sh.api.modelos.envio.Checklist;
import com.vvm.sh.api.modelos.envio.ItemSeccaoChecklist;
import com.vvm.sh.api.modelos.envio.Levantamento;
import com.vvm.sh.api.modelos.envio.Observacao;
import com.vvm.sh.api.modelos.envio.Pergunta;
import com.vvm.sh.api.modelos.envio.RegistoVisita;
import com.vvm.sh.api.modelos.envio.RelatorioAmbiental;
import com.vvm.sh.api.modelos.envio.Risco;
import com.vvm.sh.api.modelos.envio.Sessao;
import com.vvm.sh.api.modelos.envio.AcaoFormacao;
import com.vvm.sh.api.modelos.envio.Anomalia;
import com.vvm.sh.api.modelos.envio.AtividadePendenteExecutada;
import com.vvm.sh.api.modelos.envio.AtividadePendenteNaoExecutada;
import com.vvm.sh.api.BlocoDados;
import com.vvm.sh.api.modelos.envio.CrossSelling;
import com.vvm.sh.api.modelos.envio.Email;
import com.vvm.sh.api.modelos.envio.Formando;
import com.vvm.sh.api.modelos.envio.Imagem;
import com.vvm.sh.api.modelos.envio.Ocorrencia;
import com.vvm.sh.api.modelos.envio.TrabalhadorVulneravel;
import com.vvm.sh.api.modelos.envio.TrabalhoRealizado;
import com.vvm.sh.api.modelos.envio.Ut;
import com.vvm.sh.baseDados.entidades.AvaliacaoAmbientalResultado;
import com.vvm.sh.baseDados.entidades.CrossSellingResultado;
import com.vvm.sh.baseDados.entidades.EmailResultado;
import com.vvm.sh.baseDados.entidades.FormandoResultado;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.baseDados.entidades.ItemChecklist;
import com.vvm.sh.baseDados.entidades.LevantamentoRiscoResultado;
import com.vvm.sh.baseDados.entidades.QuestionarioChecklistResultado;
import com.vvm.sh.baseDados.entidades.RegistoVisitaResultado;
import com.vvm.sh.baseDados.entidades.RiscoResultado;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.baseDados.entidades.AnomaliaResultado;
import com.vvm.sh.baseDados.entidades.OcorrenciaResultado;
import com.vvm.sh.baseDados.entidades.AcaoFormacaoResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.baseDados.entidades.TrabalhadorVulneravelResultado;
import com.vvm.sh.baseDados.entidades.TrabalhoRealizadoResultado;
import com.vvm.sh.ui.transferencias.modelos.DadosUpload;
import com.vvm.sh.util.metodos.DatasUtil;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

//@Mapper//(uses= DataMapper.class)
@Mapper(uses= ImagemMapper.class)
public interface UploadMapping {

    static final UploadMapping INSTANCE = Mappers.getMapper( UploadMapping.class );

    @Mapping(source = "endereco", target = "email")
    @Mapping(source = "idAutorizacao", target = "estadoEmail")
    Email map(EmailResultado item);

    @Mapping(source = "idAnomalia", target = "idAnomalia")
    @Mapping(source = "observacao", target = "observacoes")
    Anomalia map(AnomaliaResultado item);



    CrossSelling map(CrossSellingResultado item);

    @Mapping(source = "id", target = "idOcorrencia")
    @Mapping(source = "observacao", target = "observacao")
    Ocorrencia map(OcorrenciaResultado item);

    @Mapping(source = "atividade.servId", target = "servId")
    @Mapping(source = "resultado.idAnomalia", target = "idAnomalia")
    @Mapping(source = "resultado.observacao", target = "observacao")
    AtividadePendenteNaoExecutada map(AtividadePendenteBd item);

    @Mapping(source = "atividade.servId", target = "servId")
    @Mapping(source = "resultado.tempoExecucao", target = "tempoExecucao")
    @Mapping(source = "resultado.dataExecucao", target = "dataExecucao", dateFormat = DatasUtil.FORMATO_YYYY_MM_DD)
    AtividadePendenteExecutada mapeamentoTemperaturaHumidade(AtividadePendenteBd item);


    @Mapping(source = "data", target = "data", dateFormat = DatasUtil.FORMATO_YYYY_MM_DD)
    @Mapping(source = "inicio", target = "inicio", dateFormat = DatasUtil.HORA_FORMATO_HH_MM)
    @Mapping(source = "termino", target = "termino", dateFormat = DatasUtil.HORA_FORMATO_HH_MM)
    AcaoFormacao map(AcaoFormacaoResultado item);

    @Mapping(source = "sexo", target = "genero")
    @Mapping(source = "dataNascimento", target = "dataNascimento", dateFormat = DatasUtil.FORMATO_YYYY_MM_DD)
    Formando map(FormandoResultado item);

    @Mapping(source = "prefixoCt", target = "prefixoCT")
    @Mapping(source = "data", target = "data", dateFormat = DatasUtil.FORMATO_YYYY_MM_DD)
    Sessao map(Tarefa item);


    RegistoVisita map(RegistoVisitaResultado obterRegistoVisita);

    TrabalhoRealizado map(TrabalhoRealizadoResultado item);


    @Mapping(target = "servId", source = "servId")
    @Mapping(target = "servicoTP", source = "servicoTp")
    @Mapping(target = "ordem", source = "ordem")
    @Mapping(target = "data", source = "data")
    AtividadePlanoAcao map(AtividadePlanoAcaoBd item);

    //TODO: verificar e completar

    @Mapping(target = "idChecklist", source = "id")
    //@Mapping(target = "versaoChecklist", source = "idPai")
    Checklist mapeamentoTemperaturaHumidade(Tipo checklist);

    @Mapping(target = "idItem", source = "idItem")
    @Mapping(target = "resposta", source = "resposta")
    @Mapping(target = "nr", source = "nr")
    Pergunta mapPerguntaChecklist(QuestionarioChecklistResultado questao);

    @Mapping(target = "idItem", source = "idItem")
    @Mapping(target = "resposta", source = "resposta")
    Observacao mapObservacaoChecklist(QuestionarioChecklistResultado observacao);

    @Mapping(target = "idItem", source = "idItem")
    @Mapping(target = "idUT1", source = "ut1")
    @Mapping(target = "idCategoriasRiscoUT_1", source = "ut1_CategoriasRisco")
    @Mapping(target = "localRiscoA_ut_1", source = "ut1_LocalRisco_A")
    @Mapping(target = "localRiscoB_ut_1", source = "ut1_LocalRisco_B")
    @Mapping(target = "localRiscoC_ut_1", source = "ut1_LocalRisco_C")
    @Mapping(target = "localRiscoD_ut_1", source = "ut1_LocalRisco_D")
    @Mapping(target = "localRiscoE_ut_1", source = "ut1_LocalRisco_E")
    @Mapping(target = "localRiscoF_ut_1", source = "ut1_LocalRisco_F")
    @Mapping(target = "idUT2", source = "ut2")
    @Mapping(target = "idCategoriasRiscoUT_2", source = "ut2_CategoriasRisco")
    @Mapping(target = "localRiscoA_ut_2", source = "ut2_LocalRisco_A")
    @Mapping(target = "localRiscoB_ut_2", source = "ut2_LocalRisco_B")
    @Mapping(target = "localRiscoC_ut_2", source = "ut2_LocalRisco_C")
    @Mapping(target = "localRiscoD_ut_2", source = "ut2_LocalRisco_D")
    @Mapping(target = "localRiscoE_ut_2", source = "ut2_LocalRisco_E")
    @Mapping(target = "localRiscoF_ut_2", source = "ut2_LocalRisco_F")
    Ut mapUtChecklist(QuestionarioChecklistResultado ut);

//    @Mapping(target = "idItem", source = "idImagem")
//    ItemSeccaoChecklist mapImagemChecklist(ImagemResultado imagem);

    //@Mapping(target = "idArea", source = "area.resultado.idArea")
    @Mapping(target = "descricao", source = "descricao")
    //@Mapping(target = "descricaoArea", source = "area.resultado.subDescricao")
    Area map(AreaBd area);

    TrabalhadorVulneravel map(TrabalhadorVulneravelResultado item);

    @Mapping(target = "medidaRecomendada", source = "idMedidaRecomendada")
    @Mapping(target = "marca", source = "relatorio.resultado.marca")
    RelatorioAmbiental mapeamentoTemperaturaHumidade(RelatorioAmbientalBd relatorio);


    @Mapping(target = "descricaoArea", source = "anexoArea")
    AvaliacaoTemperaturaHumidade mapeamentoTemperaturaHumidade(AvaliacaoAmbientalResultado item);

    @Mapping(target = "idTipoIluminacao", source = "tipoIluminacao")
    AvaliacaoIluminacao mapeamentoIluminacao(AvaliacaoAmbientalResultado item);


    Risco map(RiscoResultado item);
    Levantamento map(LevantamentoRiscoResultado item);


    @Mapping(source = "idImagem", target = "idFoto")
    @Mapping(source = "imagem", target = "foto")
    Imagem map(ImagemResultado item);

    @Mapping(source = "dados", target = "dadosFormulario")
    @Mapping(source = "versao", target = "versaoApp")
    @Mapping(source = "idBloco", target = "numeroFicheiroImagens")
    BlocoDados map(DadosUpload dadosUpload);


}
