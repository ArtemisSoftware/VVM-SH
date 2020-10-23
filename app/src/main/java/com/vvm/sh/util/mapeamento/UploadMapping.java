package com.vvm.sh.util.mapeamento;

import com.vvm.sh.api.modelos.bd.AtividadePendenteBd;
import com.vvm.sh.api.modelos.envio.RegistoVisita;
import com.vvm.sh.api.modelos.envio.Sessao;
import com.vvm.sh.api.modelos.envio.AcaoFormacao;
import com.vvm.sh.api.modelos.envio.Anomalia;
import com.vvm.sh.api.modelos.envio.AtividadePendenteExecutada;
import com.vvm.sh.api.modelos.envio.AtividadePendenteNaoExecutada;
import com.vvm.sh.api.BlocoDados;
import com.vvm.sh.api.modelos.envio.CrossSelling;
import com.vvm.sh.api.modelos.envio.Email;
import com.vvm.sh.api.modelos.envio.Formando;
import com.vvm.sh.api.modelos.envio.Imagem;
import com.vvm.sh.api.modelos.envio.Ocorrencia;
import com.vvm.sh.api.modelos.envio.TrabalhoRealizado;
import com.vvm.sh.baseDados.entidades.CrossSellingResultado;
import com.vvm.sh.baseDados.entidades.EmailResultado;
import com.vvm.sh.baseDados.entidades.FormandoResultado;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.baseDados.entidades.RegistoVisitaResultado;
import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.baseDados.entidades.AnomaliaResultado;
import com.vvm.sh.baseDados.entidades.OcorrenciaResultado;
import com.vvm.sh.baseDados.entidades.AcaoFormacaoResultado;
import com.vvm.sh.baseDados.entidades.TrabalhoRealizadoResultado;
import com.vvm.sh.ui.transferencias.modelos.DadosUpload;
import com.vvm.sh.util.metodos.DatasUtil;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

//@Mapper//(uses= DataMapper.class)
@Mapper(uses= ImagemMapper.class)
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
    AtividadePendenteNaoExecutada map(AtividadePendenteBd item);

    @Mapping(source = "atividade.servId", target = "servId")
    @Mapping(source = "resultado.tempoExecucao", target = "tempoExecucao")
    @Mapping(source = "resultado.dataExecucao", target = "dataExecucao", dateFormat = DatasUtil.FORMATO_YYYY_MM_DD)
    AtividadePendenteExecutada mapeamento(AtividadePendenteBd item);


    @Mapping(source = "data", target = "data", dateFormat = DatasUtil.FORMATO_YYYY_MM_DD)
    @Mapping(source = "inicio", target = "inicio", dateFormat = DatasUtil.HORA_FORMATO_HH_MM)
    @Mapping(source = "termino", target = "termino", dateFormat = DatasUtil.HORA_FORMATO_HH_MM)
    AcaoFormacao map(AcaoFormacaoResultado item);

    @Mapping(source = "sexo", target = "genero")
    @Mapping(source = "dataNascimento", target = "dataNascimento", dateFormat = DatasUtil.FORMATO_YYYY_MM_DD)
    Formando map(FormandoResultado item);

    @Mapping(source = "prefixoCt", target = "prefixoCT")
    @Mapping(source = "data", target = "data", dateFormat = DatasUtil.FORMATO_YYYY_MM_DD)
    Sessao map(Tarefa item);


    RegistoVisita map(RegistoVisitaResultado obterRegistoVisita);


    TrabalhoRealizado map(TrabalhoRealizadoResultado item);

    @Mapping(source = "idImagem", target = "idFoto")
    @Mapping(source = "imagem", target = "foto")
    Imagem map(ImagemResultado item);

    @Mapping(source = "dados", target = "dadosFormulario")
    @Mapping(source = "versao", target = "versaoApp")
    @Mapping(source = "idBloco", target = "numeroFicheiroImagens")
    BlocoDados map(DadosUpload dadosUpload);


}
