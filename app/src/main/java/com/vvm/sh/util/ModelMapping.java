package com.vvm.sh.util;

import com.vvm.sh.api.modelos.TipoResposta;
import com.vvm.sh.api.modelos.TipoResultado;
import com.vvm.sh.api.modelos.UtilizadorResultado;
import com.vvm.sh.ui.autenticacao.modelos.Utilizador;
import com.vvm.sh.ui.opcoes.modelos.Atualizacao;
import com.vvm.sh.ui.opcoes.modelos.Tipo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
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


/*
    @Mapping(source = "id", target = "number")
    PokemonResponse map(Pokemon item);
    */

}
