package com.vvm.sh.util.mapeamento;

import com.vvm.sh.api.modelos.bd.AtividadePendenteBd;
import com.vvm.sh.api.modelos.envio.sht.AtividadePendenteExecutada;
import com.vvm.sh.api.modelos.envio.sht.AtividadePendenteNaoExecutada;
import com.vvm.sh.util.metodos.DatasUtil;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public interface UploadSHMapping {


    static final UploadSHMapping INSTANCE = Mappers.getMapper( UploadSHMapping.class );


    @Mapping(source = "atividade.servId", target = "servId")
    @Mapping(source = "resultado.tempoExecucao", target = "tempoExecucao")
    @Mapping(source = "resultado.dataExecucao", target = "dataExecucao", dateFormat = DatasUtil.FORMATO_YYYY_MM_DD)
    AtividadePendenteExecutada mapeamentoAtividadeExecutada(AtividadePendenteBd item);

    @Mapping(source = "atividade.servId", target = "servId")
    @Mapping(source = "resultado.idAnomalia", target = "idAnomalia")
    @Mapping(source = "resultado.observacao", target = "observacao")
    AtividadePendenteNaoExecutada mapAtividadeNaoExecutada(AtividadePendenteBd item);
}
