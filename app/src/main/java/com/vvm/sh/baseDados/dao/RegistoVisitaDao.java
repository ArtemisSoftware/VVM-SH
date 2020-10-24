package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.RegistoVisitaResultado;
import com.vvm.sh.ui.registoVisita.modelos.DadosCliente;
import com.vvm.sh.ui.registoVisita.modelos.RelatorioRegistoVisita;
import com.vvm.sh.util.email.CredenciaisEmail;
import com.vvm.sh.util.email.Email;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.TiposUtil;

import io.reactivex.Maybe;
import io.reactivex.Observable;

@Dao
abstract public class RegistoVisitaDao implements BaseDao<RegistoVisitaResultado> {


    @Transaction
    @Query("SELECT * FROM tarefas WHERE idTarefa =:idTarefa")
    abstract public Maybe<DadosCliente> obterDadosCliente(int idTarefa);

    @Query("SELECT clienteValido, trabalhoValido, numeroTrabalhos, email, assinaturaValido, " +
            "CASE WHEN clienteValido = 1 AND trabalhoValido = 1 AND assinaturaValido = 1 THEN 1 ELSE 0 END as valido " +

            "FROM ( " +
            "SELECT rg_visit_res.idTarefa as idTarefa, CASE WHEN recebidoPor IS NULL OR funcao IS NULL THEN 0  ELSE 1 END as clienteValido, " +
            "trabalhoValido, numeroTrabalhos, email, " +
            "CASE WHEN IFNULL(img.idTarefa, 0) = 0 THEN 0 ELSE 1 END as assinaturaValido " +

            "FROM registoVisitaResultado as rg_visit_res " +

            "LEFT JOIN(" +
            "SELECT idTarefa, CASE WHEN COUNT(VALIDO) = SUM(VALIDO) AND COUNT(VALIDO) > 0 THEN 1 ELSE 0 END trabalhoValido " +
            "FROM ( " +
            "SELECT idTarefa, CASE WHEN COUNT(id) = 0 THEN 0 ELSE 1 END as valido " +
            "FROM trabalhoRealizadoResultado " +
            "GROUP BY idTarefa " +
            ") as resultado" +
            ") as validade_trab_real ON rg_visit_res.idTarefa = validade_trab_real.idTarefa " +

            "LEFT JOIN(SELECT idTarefa, COUNT(id) as numeroTrabalhos FROM trabalhoRealizadoResultado GROUP BY idTarefa) as ct_trab_rl " +
            "ON rg_visit_res.idTarefa = ct_trab_rl.idTarefa " +


            "LEFT JOIN(SELECT idTarefa FROM imagensResultado WHERE origem = " + Identificadores.Imagens.IMAGEM_ASSINATURA_REGISTO_VISITA + ") as img " +
            "ON rg_visit_res.idTarefa = img.idTarefa " +



            "LEFT JOIN(" +
            "SELECT clt.idTarefa as idTarefa, CASE WHEN (endereco_email IS NULL OR endereco_email != '') THEN email ELSE endereco_email END as email " +
            "FROM clientes as clt " +
            "LEFT JOIN (SELECT idTarefa, endereco as endereco_email FROM emailsResultado) as eml_res " +
            "ON clt.idTarefa = eml_res.idTarefa " +
            ") as clt " +
            "ON rg_visit_res.idTarefa = clt.idTarefa" +
            ") as registo_visita " +

            "WHERE registo_visita.idTarefa =:idTarefa")
    abstract public Observable<RelatorioRegistoVisita> obterValidadeRegistoVisita(int idTarefa);



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
            "ELSE '' END as idTexto " +
            "FROM tarefas) as trf " +
            "ON clt.idTarefa = trf.idTarefa " +

            "LEFT JOIN (SELECT id, detalhe as corpoEmail FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.FRASES_APOIO + "' AND ativo = 1 AND api = :api) as tp_frase " +
            "ON trf.idTexto = tp_frase.id " +

            "WHERE clt.idTarefa = :idTarefa")
    abstract public Maybe<CredenciaisEmail> obterDadosEmail(int idTarefa, int api);

}
