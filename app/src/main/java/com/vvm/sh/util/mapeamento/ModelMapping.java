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
import com.vvm.sh.ui.anomalias.modelos.Anomalia;
import com.vvm.sh.ui.atividadesExecutadas.modelos.AtividadeExecutada;
import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendente;
import com.vvm.sh.ui.autenticacao.modelos.Utilizador;
import com.vvm.sh.ui.cliente.Cliente;
import com.vvm.sh.baseDados.entidades.Ocorrencia;
import com.vvm.sh.baseDados.entidades.OcorrenciaHistorico;
import com.vvm.sh.ui.opcoes.modelos.Atualizacao;
import com.vvm.sh.ui.opcoes.modelos.Tipo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses= DataMapper.class)
public interface ModelMapping {

    static final ModelMapping INSTANCE = Mappers.getMapper( ModelMapping.class );

    @Mapping(source = "tipo", target = "descricao")
    @Mapping(source = "seloTemporal", target = "seloTemporal")
    Atualizacao map(TipoResposta item);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "descricao", target = "descricao")
    @Mapping(source = "codigo", target = "codigo")
    @Mapping(source = "idPai", target = "idPai")
    @Mapping(source = "ativo", target = "ativo")
    @Mapping(source = "detalhe", target = "detalhe")
    Tipo map(TipoResultado item);


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



    Cliente map(ClienteResultado cliente, DadosResultado dados, TarefaResultado tarefa);


    Anomalia map(AnomaliaResposta item);

    AtividadePendente map(AtividadePendenteResposta item);

    Ocorrencia map(OcorrenciaResposta item);

    OcorrenciaHistorico map(OcorrenciaResposta.OcorrenciaHistoricoResultado item);

/*
    @Mapping(source = "id", target = "number")
    PokemonResponse map(Pokemon item);
    */

}
