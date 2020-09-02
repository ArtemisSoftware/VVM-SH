package com.vvm.sh.util.mapeamento;

import com.vvm.sh.api.modelos.AnomaliaResposta;
import com.vvm.sh.api.modelos.AtividadeExecutadasResultado;
import com.vvm.sh.api.modelos.AtividadePendenteResposta;
import com.vvm.sh.api.modelos.ClienteResultado;
import com.vvm.sh.api.modelos.DadosResultado;
import com.vvm.sh.api.modelos.OcorrenciaResposta;
import com.vvm.sh.api.modelos.TarefaResultado;
import com.vvm.sh.api.modelos.TipoResposta;
import com.vvm.sh.api.modelos.TipoResultado;
import com.vvm.sh.api.modelos.UtilizadorResultado;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.baseDados.entidades.Anomalia;
import com.vvm.sh.baseDados.entidades.AtividadeExecutada;
import com.vvm.sh.baseDados.entidades.AtividadePendente;
import com.vvm.sh.baseDados.entidades.Utilizador;
import com.vvm.sh.baseDados.entidades.Cliente;
import com.vvm.sh.baseDados.entidades.Ocorrencia;
import com.vvm.sh.baseDados.entidades.OcorrenciaHistorico;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.Tipo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses= DataMapper.class)
public interface ModelMapping {

    static final ModelMapping INSTANCE = Mappers.getMapper( ModelMapping.class );

    @Mapping(source = "metodo", target = "descricao")
    @Mapping(source = "seloTemporal", target = "seloTemporal")
    Atualizacao map(TipoResposta item);


    @Mapping(source = "item.id", target = "id")
    @Mapping(source = "item.descricao", target = "descricao")
    @Mapping(source = "item.codigo", target = "codigo")
    @Mapping(source = "item.idPai", target = "idPai")
    @Mapping(source = "item.ativo", target = "ativo")
    @Mapping(source = "item.detalhe", target = "detalhe")
    @Mapping(source = "resposta.metodo", target = "tipo")
    Tipo map(TipoResultado item, TipoResposta resposta);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "area", target = "area")
    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "email", target = "email")
    Utilizador map(UtilizadorResultado item);


    @Mapping(source = "ordem", target = "ordem")
    @Mapping(source = "prefixoCt", target = "prefixoCt")
    Tarefa map(DadosResultado item);


    @Mapping(source = "ordem", target = "ordem")
    @Mapping(source = "idServico", target = "idServico")
    @Mapping(source = "descricao", target = "descricao")
    @Mapping(source = "dataProgramada", target = "dataProgramada")
    @Mapping(source = "dataExecucao", target = "dataExecucao")
    AtividadeExecutada map(AtividadeExecutadasResultado item);


    @Mapping( source = "cliente.email", target = "email", defaultValue = "")
    Cliente map(ClienteResultado cliente, DadosResultado dados, TarefaResultado tarefa);


    Anomalia map(AnomaliaResposta item);

    @Mapping(target = "formacao", ignore = true)
    AtividadePendente map(AtividadePendenteResposta item);

    Ocorrencia map(OcorrenciaResposta item);

    OcorrenciaHistorico map(OcorrenciaResposta.OcorrenciaHistoricoResultado item);


}
