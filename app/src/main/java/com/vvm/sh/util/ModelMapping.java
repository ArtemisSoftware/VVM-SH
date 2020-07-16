package com.vvm.sh.util;

import com.vvm.sh.api.modelos.TipoResposta;
import com.vvm.sh.ui.opcoes.modelos.Atualizacao;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ModelMapping {

    static final ModelMapping INSTANCE = Mappers.getMapper( ModelMapping.class );

    @Mapping(source = "tipo", target = "descricao")
        //@Mapping(source = "height", target = "especie")
    Atualizacao map(TipoResposta item);

/*
    @Mapping(source = "id", target = "number")
    PokemonResponse map(Pokemon item);
    */

}
