package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.baseDados.entidades.CertificadoTratamentoResultado;
import com.vvm.sh.documentos.Rubrica;
import com.vvm.sh.ui.informacaoSst.modelos.ObrigacaoLegal;
import com.vvm.sh.documentos.DadosCliente;
import com.vvm.sh.ui.registoVisita.modelos.TrabalhoRealizado;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.email.CredenciaisEmail;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.List;

import io.reactivex.Maybe;

@Dao
abstract public class PdfDao {

    @Transaction
    @Query("SELECT CASE WHEN (endereco_email IS NULL OR endereco_email != '') THEN email " +
            "ELSE endereco_email END as destino, '" + Sintaxe.Palavras.REGISTO_VISITA +"' as titulo, corpoEmail " +
            "FROM clientes as clt " +

            "LEFT JOIN (SELECT idTarefa, endereco as endereco_email FROM emailsResultado) as eml_res " +
            "ON clt.idTarefa = eml_res.idTarefa " +

            "LEFT JOIN (" +
            "SELECT idTarefa, " +
            "CASE " +
            "WHEN prefixoCT = 'KMED' THEN '" + Identificadores.ID_EMAIL_REGISTO_VISITA_KMED + "' " +
            "WHEN prefixoCT = 'SH' THEN '" + Identificadores.ID_EMAIL_REGISTO_VISITA_VM + "' " +
            "ELSE '" + Identificadores.ID_EMAIL_REGISTO_VISITA_KMED + "' END as idTexto " +
            "FROM tarefas) as trf " +
            "ON clt.idTarefa = trf.idTarefa " +

            "LEFT JOIN (SELECT id, detalhe as corpoEmail FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.FRASES_APOIO + "' AND ativo = 1 AND api = :api) as tp_frase " +
            "ON trf.idTexto = tp_frase.id " +

            "WHERE clt.idTarefa = :idTarefa")
    abstract public Maybe<CredenciaisEmail> obterDadosEmailRegistoVisita(int idTarefa, int api);




    @Transaction
    @Query("SELECT CASE WHEN (endereco_email IS NULL OR endereco_email = '') THEN email " +
            "ELSE endereco_email END as destino, " +
            ":titulo as titulo, IFNULL(corpoEmail, '') as corpoEmail " +
            "FROM clientes as clt " +

            "LEFT JOIN (SELECT idTarefa, endereco as endereco_email FROM emailsResultado) as eml_res " +
            "ON clt.idTarefa = eml_res.idTarefa " +




            "LEFT JOIN (SELECT id, detalhe as corpoEmail FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.FRASES_APOIO + "' AND ativo = 1 AND api = :api) as tp_frase " +
            "ON tp_frase.id =:idFrase " +

            "WHERE clt.idTarefa = :idTarefa")
    abstract public Maybe<CredenciaisEmail> obterDadosEmail(int idTarefa, String titulo, int idFrase, int api);



    //------------------------
    //Registo visita
    //------------------------




    @Query("SELECT *, tb_rel_res.id as idRegisto, informacao " +
            "FROM tipos as tp " +
            "LEFT JOIN (SELECT id, informacao FROM trabalhoRealizadoResultado WHERE idTarefa = :idTarefa) as tb_rel_res " +
            "ON tp.id = tb_rel_res.id " +
            "WHERE  ativo = 1 AND api =:api AND tipo ='" + TiposUtil.MetodosTipos.ATIVIDADES_RELATORIO_VISITA + "' ")
    abstract public Maybe<List<TrabalhoRealizado>> obterTrabalhosRealizadosRegistados(int idTarefa, int api);


    //------------------------
    //Certificado de tratamento
    //------------------------

    @Query("SELECT * FROM certificadoTratamentoResultado WHERE  idAtividade =:idAtividade")
    abstract public Maybe<CertificadoTratamentoResultado> obterCertificado(int idAtividade);

    //------------------------
    //Informacao sst
    //------------------------



    @Query("SELECT *, obrigacao_legal_res.id as idRegisto " +
            "FROM tipos as tp " +
            "LEFT JOIN (SELECT id FROM obrigacaoLegalResultado WHERE idTarefa = :idTarefa) as obrigacao_legal_res " +
            "ON tp.id = obrigacao_legal_res.id " +
            "WHERE  ativo = 1 AND api =:api AND tipo ='" + TiposUtil.MetodosTipos.OBRIGACOES_LEGAIS + "' ")
    abstract public Maybe<List<ObrigacaoLegal>> obterObrigacoesLegais(int idTarefa, int api);

    //------------------------
    //Misc
    //------------------------

    @Transaction
    @Query("SELECT * FROM tarefas WHERE idTarefa =:idTarefa")
    abstract public Maybe<DadosCliente> obterDadosCliente(int idTarefa);


    @Query("SELECT cap, nome, imagem " +
            "FROM imagensResultado as img " +
            "LEFT JOIN (SELECT nome, cap FROM utilizadores WHERE id = :idUtilizador)" +
            "WHERE id = :id AND origem = :origem")
    abstract public Maybe<Rubrica> obterRubrica(int id, int origem, String idUtilizador);


    @Query("SELECT detalhe FROM tipos WHERE id =:id AND tipo = '" + TiposUtil.MetodosTipos.FRASES_APOIO + "' AND ativo = 1 AND api = :api")
    abstract public Maybe<String> obterFraseApoio(int id, int api);





}
