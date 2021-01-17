package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.CertificadoTratamentoResultado;
import com.vvm.sh.baseDados.entidades.ParqueExtintorResultado;
import com.vvm.sh.ui.atividadesPendentes.relatorios.certificadoTratamento.modelos.RelatorioCertificadoTratamento;
import com.vvm.sh.ui.registoVisita.modelos.RelatorioRegistoVisita;
import com.vvm.sh.util.constantes.Identificadores;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
abstract public class CertificadoTratamentoDao implements BaseDao<CertificadoTratamentoResultado> {


    @Query("SELECT * FROM certificadoTratamentoResultado WHERE idAtividade = :idAtividade ")
    abstract public Maybe<CertificadoTratamentoResultado> obterCertificadoTratamento(int idAtividade);


    @Query("SELECT idAtividade, certificadoValido, assinaturaValido, sincronizacao, email, " +
            "CASE WHEN certificadoValido = 1 AND assinaturaValido = 1 AND email != '' THEN 1 ELSE 0 END as valido " +

            "FROM ( " +

            "SELECT id as idAtividade, sincronizacao, email, " +
            "CASE WHEN IFNULL(ct_crt, 0) = 0 THEN 0  ELSE 1 END as certificadoValido,  " +
            "CASE WHEN IFNULL(img.id, 0) = 0 THEN 0 ELSE 1 END as assinaturaValido " +

            "FROM atividadesPendentes as act_pend " +

            //imagens
            "LEFT JOIN(SELECT id FROM imagensResultado WHERE origem = " + Identificadores.Imagens.IMAGEM_ASSINATURA_CERTIFICADO_TRATAMENTO + ") as img " +
            "ON act_pend.id = img.id " +

            //certificado
            "LEFT JOIN(SELECT idAtividade, sincronizacao, COUNT(idAtividade) as ct_crt FROM certificadoTratamentoResultado GROUP BY idAtividade) as crt_trt_res " +
            "ON act_pend.id = crt_trt_res.idAtividade " +

            //dados cliente
            "LEFT JOIN(" +
            "SELECT clt.idTarefa as idTarefa, CASE WHEN (endereco_email IS NULL OR endereco_email != '') THEN email ELSE endereco_email END as email " +
            "FROM clientes as clt " +
            "LEFT JOIN (SELECT idTarefa, endereco as endereco_email FROM emailsResultado) as eml_res " +
            "ON clt.idTarefa = eml_res.idTarefa " +
            ") as clt " +
            "ON act_pend.idTarefa = clt.idTarefa" +

            ") as certificado_tratamento " +

            "WHERE certificado_tratamento.idAtividade =:idAtividade")
    abstract public Observable<RelatorioCertificadoTratamento> obterRelatorioCertificadoTratamento(int idAtividade);


    //---------------
    //Misc
    //----------------


    @Query("UPDATE certificadoTratamentoResultado SET sincronizacao = 1 WHERE idAtividade =:idAtividade")
    abstract public void sincronizar(int idAtividade);
}
