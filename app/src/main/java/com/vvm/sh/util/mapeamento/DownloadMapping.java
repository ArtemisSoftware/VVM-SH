package com.vvm.sh.util.mapeamento;

import com.vvm.sh.api.modelos.pedido.IColaborador;
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
import com.vvm.sh.baseDados.entidades.CheckList;
import com.vvm.sh.baseDados.entidades.Colaborador;
import com.vvm.sh.baseDados.entidades.ItemChecklist;
import com.vvm.sh.baseDados.entidades.Morada;
import com.vvm.sh.baseDados.entidades.ParqueExtintor;
import com.vvm.sh.baseDados.entidades.PlanoAcao;
import com.vvm.sh.baseDados.entidades.PlanoAcaoAtividade;
import com.vvm.sh.baseDados.entidades.RelatorioAveriguacao;
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

//    @Mapping(source = "metodo", target = "descricao")
//    @Mapping(source = "seloTemporal", target = "seloTemporal")
//    Atualizacao map(ITipoListagem item);

    @Mapping(target = "descricao", source = "metodo")
    @Mapping(target = "seloTemporal", source = "seloTemporal")
    @Mapping(target = "tipo", constant = Identificadores.Atualizacoes.TIPO + "")
    Atualizacao map(ITipoListagem resposta);


    @Mapping(target = "descricao", source = "metodo")
    @Mapping(target = "tipo", constant = Identificadores.Atualizacoes.TEMPLATE + "")
    Atualizacao map(ITipoTemplateAvrLevantamentoListagem levantamentos);

    @Mapping(target = "descricao", source = "metodo")
    @Mapping(target = "tipo", constant = Identificadores.Atualizacoes.TEMPLATE + "")
    Atualizacao map(ITipoTemplateAvrRiscoListagem riscos);

    @Mapping(target = "descricao", source = "metodo")
    @Mapping(target = "tipo", constant = Identificadores.Atualizacoes.ATIVIDADES_PLANEAVEIS + "")
    Atualizacao map(ITipoAtividadePlaneavelListagem resposta);



    @Mapping(source = "item.id", target = "id")
    @Mapping(source = "item.descricao", target = "descricao")
    @Mapping(source = "item.codigo", target = "codigo")
    @Mapping(source = "item.idPai", target = "idPai")
    @Mapping(source = "item.ativo", target = "ativo")
    @Mapping(source = "item.detalhe", target = "detalhe")
    @Mapping(source = "resposta.metodo", target = "tipo")
    Tipo map(ITipo item, ITipoListagem resposta);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "area", target = "area")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "email", target = "email")
    Utilizador map(IUtilizador item);


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


    TipoTemplateAvrLevantamento map(ITipoTemplateAvrLevantamento item);


    TipoTemplateAvrRisco map(ITipoTemplateAvrRisco item);


    @Mapping(source = "item.codigo", target = "codigo")
    TipoAtividadePlaneavel map(ITipoAtividadePlaneavel item, ITipoAtividadePlaneavelListagem resposta);

    @Mapping(target = "descricao", source = "descricao")
    @Mapping(target = "tipo", constant = Identificadores.Origens.AVERIGUACAO_AVALIACAO_RISCOS + "")
    RelatorioAveriguacao map(IRelatorioAvaliacaoRiscos.ICategoriaProfissional item);
}
