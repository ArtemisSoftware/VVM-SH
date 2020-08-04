package com.vvm.sh.util.mapeamento;

import com.vvm.sh.api.Anomalia;
import com.vvm.sh.api.CrossSelling;
import com.vvm.sh.api.Email;
import com.vvm.sh.api.Ocorrencia;
import com.vvm.sh.baseDados.entidades.CrossSellingResultado;
import com.vvm.sh.baseDados.entidades.EmailResultado;
import com.vvm.sh.ui.anomalias.modelos.AnomaliaResultado;
import com.vvm.sh.baseDados.entidades.OcorrenciaResultado;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses= DataMapper.class)
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
}
