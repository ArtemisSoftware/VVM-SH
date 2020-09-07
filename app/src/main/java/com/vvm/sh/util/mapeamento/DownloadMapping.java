package com.vvm.sh.util.mapeamento;

import com.vvm.sh.api.modelos.pedido.ITipo;
import com.vvm.sh.api.modelos.pedido.ITipoListagem;
import com.vvm.sh.api.modelos.pedido.IUtilizador;
import com.vvm.sh.api.modelos.pedido.IAtividadeExecutada;
import com.vvm.sh.api.modelos.pedido.IAnomalia;
import com.vvm.sh.api.modelos.pedido.IAtividadePendente;
import com.vvm.sh.api.modelos.pedido.ICliente;
import com.vvm.sh.api.modelos.pedido.ITarefa;
import com.vvm.sh.api.modelos.pedido.IDados;
import com.vvm.sh.api.modelos.pedido.IOcorrencia;
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
public interface DownloadMapping {

    static final DownloadMapping INSTANCE = Mappers.getMapper( DownloadMapping.class );

    @Mapping(source = "metodo", target = "descricao")
    @Mapping(source = "seloTemporal", target = "seloTemporal")
    Atualizacao map(ITipoListagem item);


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


    @Mapping( source = "cliente.email", target = "email", defaultValue = "")
    Cliente map(ICliente cliente, IDados dados, ITarefa tarefa);


    Anomalia map(IAnomalia item);

    AtividadePendente map(IAtividadePendente item);

    Ocorrencia map(IOcorrencia item);

    OcorrenciaHistorico map(IOcorrencia.IOcorrenciaHistorico item);


}
