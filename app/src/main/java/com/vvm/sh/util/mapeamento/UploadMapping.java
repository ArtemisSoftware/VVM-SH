package com.vvm.sh.util.mapeamento;

import com.vvm.sh.api.AcaoFormacao;
import com.vvm.sh.api.Anomalia;
import com.vvm.sh.api.AtividadePendenteExecutada;
import com.vvm.sh.api.AtividadePendenteNaoExecutada;
import com.vvm.sh.api.AtividadePendenteResultado_;
import com.vvm.sh.api.CrossSelling;
import com.vvm.sh.api.Email;
import com.vvm.sh.api.Formando;
import com.vvm.sh.api.Ocorrencia;
import com.vvm.sh.api.Tarefa_;
import com.vvm.sh.baseDados.entidades.CrossSellingResultado;
import com.vvm.sh.baseDados.entidades.EmailResultado;
import com.vvm.sh.baseDados.entidades.FormandoResultado;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.ui.anomalias.modelos.AnomaliaResultado;
import com.vvm.sh.baseDados.entidades.OcorrenciaResultado;
import com.vvm.sh.baseDados.entidades.AcaoFormacaoResultado;
import com.vvm.sh.util.metodos.DatasUtil;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper//(uses= DataMapper.class)
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

    @Mapping(source = "atividade.servId", target = "servId")
    @Mapping(source = "resultado.idAnomalia", target = "idAnomalia")
    @Mapping(source = "resultado.observacao", target = "observacao")
    AtividadePendenteNaoExecutada map(AtividadePendenteResultado_ item);

    @Mapping(source = "atividade.servId", target = "servId")
    @Mapping(source = "resultado.tempoExecucao", target = "tempoExecucao")
    @Mapping(source = "resultado.dataExecucao", target = "dataExecucao", dateFormat = DatasUtil.FORMATO_YYYY_MM_DD)
    AtividadePendenteExecutada mapeamento(AtividadePendenteResultado_ item);


    @Mapping(source = "data", target = "data", dateFormat = DatasUtil.FORMATO_YYYY_MM_DD)
    @Mapping(source = "inicio", target = "inicio", dateFormat = DatasUtil.HORA_FORMATO_HH_MM)
    @Mapping(source = "termino", target = "termino", dateFormat = DatasUtil.HORA_FORMATO_HH_MM)
    AcaoFormacao map(AcaoFormacaoResultado obterAcaoFormacao);

    @Mapping(source = "sexo", target = "genero")
    @Mapping(source = "dataNascimento", target = "dataNascimento", dateFormat = DatasUtil.FORMATO_YYYY_MM_DD)
    Formando map(FormandoResultado obterFormandos);

    @Mapping(source = "data", target = "data", dateFormat = DatasUtil.FORMATO_YYYY_MM_DD)
    Tarefa_ map(Tarefa obterTarefa);
}
