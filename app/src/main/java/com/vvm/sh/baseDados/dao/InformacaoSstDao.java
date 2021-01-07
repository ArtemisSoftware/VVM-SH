package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.ObrigacaoLegalResultado;
import com.vvm.sh.baseDados.entidades.RegistoVisitaResultado;
import com.vvm.sh.ui.informacaoSst.modelos.RelatorioInformacaoSst;
import com.vvm.sh.ui.registoVisita.modelos.RelatorioRegistoVisita;
import com.vvm.sh.util.constantes.Identificadores;

import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
abstract public class InformacaoSstDao implements BaseDao<ObrigacaoLegalResultado> {


    @Query("SELECT numeroObrigacaoes, email, obrigacaoValido, assinaturaValido, " +
            "CASE WHEN obrigacaoValido = 1 AND assinaturaValido = 1 AND email != '' THEN 1 ELSE 0 END as valido " +

            "FROM ( " +
//            "SELECT rg_visit_res.idTarefa as idTarefa, " +
//            "CASE WHEN recebidoPor IS NULL OR funcao IS NULL THEN 0  ELSE 1 END as clienteValido, " +
//            "trabalhoValido, numeroTrabalhos, email, sincronizacao, " +
//            "CASE WHEN IFNULL(img.idTarefa, 0) = 0 THEN 0 ELSE 1 END as assinaturaValido " +
//
//            "FROM registoVisitaResultado as rg_visit_res " +

            "SELECT info_sst_res.idTarefa as idTarefa," +
            "email, numeroObrigacaoes, obrigacaoValido, " +
            "CASE WHEN IFNULL(img.idTarefa, 0) = 0 THEN 0 ELSE 1 END as assinaturaValido " +
            "FROM tarefas as info_sst_res " +

            //obrigacaoes
            "LEFT JOIN(" +
            "SELECT idTarefa, CASE WHEN COUNT(VALIDO) = SUM(VALIDO) AND COUNT(VALIDO) > 0 THEN 1 ELSE 0 END obrigacaoValido " +
            "FROM ( " +
            "SELECT idTarefa, CASE WHEN COUNT(id) = 0 THEN 0 ELSE 1 END as valido " +
            "FROM obrigacaoLegalResultado " +
            "GROUP BY idTarefa " +
            ") as resultado" +
            ") as validade_obrigacao_legal ON info_sst_res.idTarefa = validade_obrigacao_legal.idTarefa " +

            "LEFT JOIN(SELECT idTarefa, COUNT(id) as numeroObrigacaoes FROM obrigacaoLegalResultado GROUP BY idTarefa) as ct_obrigacao_legal " +
            "ON info_sst_res.idTarefa = ct_obrigacao_legal.idTarefa " +


            //Assinatura
            "LEFT JOIN(SELECT idTarefa FROM imagensResultado WHERE origem = " + Identificadores.Imagens.IMAGEM_ASSINATURA_INFORMACAO_SST + ") as img " +
            "ON info_sst_res.idTarefa = img.idTarefa " +


            //email
            "LEFT JOIN(" +
            "SELECT clt.idTarefa as idTarefa, CASE WHEN (endereco_email IS NULL OR endereco_email != '') THEN email ELSE endereco_email END as email " +
            "FROM clientes as clt " +
            "LEFT JOIN (SELECT idTarefa, endereco as endereco_email FROM emailsResultado) as eml_res " +
            "ON clt.idTarefa = eml_res.idTarefa " +
            ") as clt " +
            "ON info_sst_res.idTarefa = clt.idTarefa" +
            ") as info_sst " +

            "WHERE info_sst.idTarefa =:idTarefa")
    abstract public Observable<RelatorioInformacaoSst> obterRelatorioInformacaoSst(int idTarefa);



    @Query("DELETE FROM obrigacaoLegalResultado WHERE idTarefa =:idTarefa AND id = :id")
    abstract public Single<Integer> remover(int idTarefa, int id);

}
