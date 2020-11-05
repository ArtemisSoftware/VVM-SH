package com.vvm.sh.util.mapeamento;

import com.vvm.sh.api.modelos.bd.AtividadePendenteBd;
import com.vvm.sh.api.modelos.envio.sa.Email;
import com.vvm.sh.api.modelos.envio.sa.AtividadePendenteExecutada;
import com.vvm.sh.api.modelos.envio.sa.AtividadePendenteNaoExecutada;
import com.vvm.sh.baseDados.entidades.EmailResultado;
import com.vvm.sh.util.metodos.DatasUtil;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public interface UploadSAMapping {


    static final UploadSAMapping INSTANCE = Mappers.getMapper( UploadSAMapping.class );

    @Mapping(target = "servId", source = "atividade.servId")
    @Mapping(target = "idAnomalia", source = "resultado.idAnomalia")
    @Mapping(target = "observacao", source = "resultado.observacao")
    AtividadePendenteNaoExecutada mapAtividadeNaoExecutada(AtividadePendenteBd item);

    @Mapping(target = "servId", source = "atividade.servId")
    @Mapping(target = "tempoExecucao", source = "resultado.tempoExecucao")
    @Mapping(target = "dataExecucao", source = "resultado.dataExecucao", dateFormat = DatasUtil.FORMATO_YYYY_MM_DD)
    AtividadePendenteExecutada mapAtividadeExecutada(AtividadePendenteBd item);

    @Mapping(target = "email", source = "endereco")
    @Mapping(target = "estadoEmail", source = "idAutorizacao")
    Email map(EmailResultado item);

}
