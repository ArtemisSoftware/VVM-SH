package com.vvm.sh.util.mapeamento;

import com.vvm.sh.api.modelos.pedido.IAvaliacaoRiscosAnterior;
import com.vvm.sh.api.modelos.pedido.IColaborador;
import com.vvm.sh.api.modelos.pedido.IFormando;
import com.vvm.sh.api.modelos.pedido.IMorada;
import com.vvm.sh.api.modelos.pedido.IParqueExtintor;
import com.vvm.sh.api.modelos.pedido.IPlanoAcao;
import com.vvm.sh.api.modelos.pedido.IRelatorioAvaliacaoRiscos;
import com.vvm.sh.api.modelos.pedido.ITipo;
import com.vvm.sh.api.modelos.pedido.ITipoAtividadePlaneavel;
import com.vvm.sh.api.modelos.pedido.ITipoAtividadePlaneavelListagem;
import com.vvm.sh.api.modelos.pedido.ITipoChecklist;
import com.vvm.sh.api.modelos.pedido.ITipoExtintor;
import com.vvm.sh.api.modelos.pedido.ITipoListagem;
import com.vvm.sh.api.modelos.pedido.ITipoTemplateAvrLevantamento;
import com.vvm.sh.api.modelos.pedido.ITipoTemplateAvrLevantamentoListagem;
import com.vvm.sh.api.modelos.pedido.ITipoTemplateAvrRisco;
import com.vvm.sh.api.modelos.pedido.ITipoTemplateAvrRiscoListagem;
import com.vvm.sh.api.modelos.pedido.IUtilizador;
import com.vvm.sh.api.modelos.pedido.IAtividadeExecutada;
import com.vvm.sh.api.modelos.pedido.IAnomalia;
import com.vvm.sh.api.modelos.pedido.IAtividadePendente;
import com.vvm.sh.api.modelos.pedido.ICliente;
import com.vvm.sh.api.modelos.pedido.ITarefa;
import com.vvm.sh.api.modelos.pedido.IDados;
import com.vvm.sh.api.modelos.pedido.IOcorrencia;
import com.vvm.sh.baseDados.entidades.AreaChecklist;
import com.vvm.sh.baseDados.entidades.AreaChecklistResultado;
import com.vvm.sh.baseDados.entidades.CheckList;
import com.vvm.sh.baseDados.entidades.Colaborador;
import com.vvm.sh.baseDados.entidades.FormandoResultado;
import com.vvm.sh.baseDados.entidades.ItemChecklist;
import com.vvm.sh.baseDados.entidades.Morada;
import com.vvm.sh.baseDados.entidades.ParqueExtintor;
import com.vvm.sh.baseDados.entidades.PlanoAcao;
import com.vvm.sh.baseDados.entidades.PlanoAcaoAtividade;
import com.vvm.sh.baseDados.entidades.QuestionarioChecklistResultado;
import com.vvm.sh.baseDados.entidades.RelatorioAveriguacao;
import com.vvm.sh.baseDados.entidades.RiscoResultado;
import com.vvm.sh.baseDados.entidades.SeccaoChecklist;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.baseDados.entidades.Anomalia;
import com.vvm.sh.baseDados.entidades.AtividadeExecutada;
import com.vvm.sh.baseDados.entidades.AtividadePendente;
import com.vvm.sh.baseDados.entidades.TipoAtividadePlaneavel;
import com.vvm.sh.baseDados.entidades.TipoExtintor;
import com.vvm.sh.baseDados.entidades.TipoTemplateAvrLevantamento;
import com.vvm.sh.baseDados.entidades.TipoTemplateAvrRisco;
import com.vvm.sh.baseDados.entidades.Utilizador;
import com.vvm.sh.baseDados.entidades.Cliente;
import com.vvm.sh.baseDados.entidades.Ocorrencia;
import com.vvm.sh.baseDados.entidades.OcorrenciaHistorico;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.util.constantes.Identificadores;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses= DataMapper.class)
public interface DownloadMapping {

    static final DownloadMapping INSTANCE = Mappers.getMapper( DownloadMapping.class );


    @Mapping(target = "descricao", source = "metodo")
    @Mapping(target = "seloTemporal", source = "seloTemporal")
    @Mapping(target = "tipo", constant = Identificadores.Atualizacoes.TIPO + "")
    Atualizacao map(ITipoListagem resposta);


    @Mapping(target = "descricao", source = "metodo")
    @Mapping(target = "tipo", constant = Identificadores.Atualizacoes.ATIVIDADES_PLANEAVEIS + "")
    Atualizacao map(ITipoAtividadePlaneavelListagem resposta);


    @Mapping(target = "descricao", source = "metodo")
    @Mapping(target = "tipo", constant = Identificadores.Atualizacoes.TEMPLATE + "")
    Atualizacao map(ITipoTemplateAvrLevantamentoListagem levantamentos);

    @Mapping(target = "descricao", source = "metodo")
    @Mapping(target = "tipo", constant = Identificadores.Atualizacoes.TEMPLATE + "")
    Atualizacao map(ITipoTemplateAvrRiscoListagem riscos);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "area", source = "area")
    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "email", source = "email")
    Utilizador map(IUtilizador item);




    //-------------------------
    //Tipos
    //-------------------------


    @Mapping(target = "id", source = "item.id")
    @Mapping(target = "descricao", source = "item.descricao")
    @Mapping(target = "codigo", source = "item.codigo")
    @Mapping(target = "idPai", source = "item.idPai")
    @Mapping(target = "ativo", source = "item.ativo")
    @Mapping(target = "detalhe", source = "item.detalhe")
    @Mapping(target = "tipo", source = "resposta.metodo")
    Tipo map(ITipo item, ITipoListagem resposta);


    @Mapping(target = "codigo", source = "item.codigo")
    @Mapping(target = "tipo", source = "resposta.metodo")
    TipoAtividadePlaneavel map(ITipoAtividadePlaneavel item, ITipoAtividadePlaneavelListagem resposta);


    @Mapping(target = "tipo", source = "resposta.metodo")
    TipoTemplateAvrLevantamento map(ITipoTemplateAvrLevantamento item, ITipoTemplateAvrLevantamentoListagem resposta);


    @Mapping(target = "tipo", source = "resposta.metodo")
    TipoTemplateAvrRisco map(ITipoTemplateAvrRisco item, ITipoTemplateAvrRiscoListagem resposta);














    @Mapping(source = "ordem", target = "ordem")
    @Mapping(source = "prefixoCt", target = "prefixoCt")
    Tarefa map(IDados item);


    @Mapping(source = "ordem", target = "ordem")
    @Mapping(source = "idServico", target = "idServico")
    @Mapping(source = "descricao", target = "descricao")
    @Mapping(source = "dataProgramada", target = "dataProgramada")
    @Mapping(source = "dataExecucao", target = "dataExecucao")
    AtividadeExecutada map(IAtividadeExecutada item);



    @Mapping(source = "cliente.email", target = "email", defaultValue = "")
    Cliente map(ICliente cliente, IDados dados, ITarefa tarefa);


    Anomalia map(IAnomalia item);

    @Mapping(target = "idChecklist", ignore = true)
    AtividadePendente map(IAtividadePendente item);

    Ocorrencia map(IOcorrencia item);

    OcorrenciaHistorico map(IOcorrencia.IOcorrenciaHistorico item);

    @Mapping(target = "endereco", source = "rua")
    Morada map(IMorada item);

    @Mapping(target = "id", source = "idTipoExtintor")
    TipoExtintor map(ITipoExtintor item);

    @Mapping(target = "idExtintor", source = "idTipoExtintor")
    ParqueExtintor map(IParqueExtintor item);

    Colaborador map(IColaborador item);

    CheckList map(ITipoChecklist resposta);

    @Mapping(target = "idChecklist", source = "checkList.id")
    @Mapping(target = "idArea", source = "itemArea.id")
    @Mapping(target = "descricao", source = "itemArea.descricao")
    AreaChecklist map(ITipoChecklist.IArea itemArea,  CheckList checkList);

    @Mapping(target = "idChecklist", source = "checkList.id")
    @Mapping(target = "idArea", source = "itemArea.id")
    @Mapping(target = "uid", source = "itemSeccao.uid")
    @Mapping(target = "descricao", source = "itemSeccao.descricao")
    @Mapping(target = "tipo", source = "itemSeccao.tipo")
    SeccaoChecklist map(ITipoChecklist.ISeccao itemSeccao, CheckList checkList, ITipoChecklist.IArea itemArea);



    @Mapping(target = "idChecklist", source = "checkList.id")
    @Mapping(target = "idArea", source = "itemArea.id")
    @Mapping(target = "idSeccao", source = "itemSeccao.uid")
    @Mapping(target = "uid", source = "itemItem.uid")
    @Mapping(target = "descricao", source = "itemItem.descricao")
    @Mapping(target = "tipo", source = "itemItem.tipo")
    @Mapping(target = "codigo", source = "itemItem.codigo")
    ItemChecklist map(ITipoChecklist.IItem item, CheckList checkList, ITipoChecklist.IArea itemArea, ITipoChecklist.ISeccao itemSeccao, ITipoChecklist.IItem itemItem);

    PlanoAcao map(IPlanoAcao planoAcao);

    @Mapping(target = "sempreNecessario", ignore = true)
    PlanoAcaoAtividade map(IPlanoAcao.IAtividadePlano iAtividadePlano);




    @Mapping(target = "descricao", source = "descricao")
    @Mapping(target = "tipo", constant = Identificadores.Origens.AVERIGUACAO_AVALIACAO_RISCOS + "")
    RelatorioAveriguacao map(IRelatorioAvaliacaoRiscos.ICategoriaProfissional item);


    @Mapping(target = "idRisco", source = "idRisco")
    @Mapping(target = "idRiscoEspecifico", source = "idRiscoEspecifico")
    @Mapping(target = "consequencias", source = "consequencias")
    @Mapping(target = "nc", source = "nc")
    @Mapping(target = "nd", source = "nd")
    @Mapping(target = "ne", source = "ne")
    RiscoResultado map(IAvaliacaoRiscosAnterior.IRisco itemRisco);


    @Mapping(target = "idArea", source = "id")
    @Mapping(target = "subDescricao", source = "descricao")
    @Mapping(target = "id", ignore = true)
    AreaChecklistResultado map(IAvaliacaoRiscosAnterior.IArea itemArea);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "idItem", source = "id")
    @Mapping(target = "resposta", source = "resposta")
    @Mapping(target = "ni", source = "nr")
    QuestionarioChecklistResultado mapQuestao(IAvaliacaoRiscosAnterior.IItem item);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "idItem", source = "id")
    @Mapping(target = "ut1", ignore = true)
    @Mapping(target = "ut1_CategoriasRisco", ignore = true)
    @Mapping(target = "ut1_LocalRisco_A", source = "localRiscoA_ut_1")
    @Mapping(target = "ut1_LocalRisco_B", source = "localRiscoB_ut_1")
    @Mapping(target = "ut1_LocalRisco_C", source = "localRiscoC_ut_1")
    @Mapping(target = "ut1_LocalRisco_D", source = "localRiscoD_ut_1")
    @Mapping(target = "ut1_LocalRisco_E", source = "localRiscoE_ut_1")
    @Mapping(target = "ut1_LocalRisco_F", source = "localRiscoF_ut_1")
    @Mapping(target = "ut2", ignore = true)
    @Mapping(target = "ut2_CategoriasRisco", ignore = true)
    @Mapping(target = "ut2_LocalRisco_A", source = "localRiscoA_ut_2")
    @Mapping(target = "ut2_LocalRisco_B", source = "localRiscoB_ut_2")
    @Mapping(target = "ut2_LocalRisco_C", source = "localRiscoC_ut_2")
    @Mapping(target = "ut2_LocalRisco_D", source = "localRiscoD_ut_2")
    @Mapping(target = "ut2_LocalRisco_E", source = "localRiscoE_ut_2")
    @Mapping(target = "ut2_LocalRisco_F", source = "localRiscoF_ut_2")
    QuestionarioChecklistResultado mapUt(IAvaliacaoRiscosAnterior.IItem item);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "idItem", source = "id")
    @Mapping(target = "resposta", source = "resposta")
    QuestionarioChecklistResultado mapObservacao(IAvaliacaoRiscosAnterior.IItem item);


    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "biCartaoCidadao", source = "biCartaoCidadao")
    @Mapping(target = "niss", source = "niss")
    @Mapping(target = "nacionalidade", source = "nacionalidade")
    @Mapping(target = "naturalidade", source = "naturalidade")
    @Mapping(target = "sexo", source = "genero")
    @Mapping(target = "dataNascimento", ignore = true)
    FormandoResultado map(IFormando item);
}
