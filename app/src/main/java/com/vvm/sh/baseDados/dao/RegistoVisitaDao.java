package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.RegistoVisitaResultado;
import com.vvm.sh.documentos.DadosCliente;
import com.vvm.sh.ui.registoVisita.modelos.RelatorioRegistoVisita;
import com.vvm.sh.util.email.CredenciaisEmail;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.TiposUtil;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
abstract public class RegistoVisitaDao implements BaseDao<RegistoVisitaResultado> {


    @Transaction
    @Query("SELECT * FROM tarefas WHERE idTarefa =:idTarefa")
    abstract public Maybe<DadosCliente> obterDadosCliente(int idTarefa);

    @Query("SELECT clienteValido, trabalhoValido, numeroTrabalhos, email, assinaturaValido, sincronizacao, " +
            "CASE WHEN emailValido = 1 AND clienteValido = 1 AND trabalhoValido = 1  THEN 1 ELSE 0 END as podeAssinar, " +
            "CASE WHEN emailValido = 1 AND clienteValido = 1 AND trabalhoValido = 1 AND assinaturaValido = 1  THEN 1 ELSE 0 END as valido " +

            "FROM ( " +
            "SELECT rg_visit_res.idTarefa as idTarefa, " +
            "CASE WHEN recebidoPor IS NULL OR funcao IS NULL THEN 0  ELSE 1 END as clienteValido, " +
            "trabalhoValido, numeroTrabalhos, email, sincronizacao, " +
            "CASE WHEN IFNULL(img.idTarefa, 0) = 0 THEN 0 ELSE 1 END as assinaturaValido, " +
            "CASE WHEN email IS NULL OR email = '' THEN 0 ELSE 1 END as emailValido " +

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
    abstract public Observable<RelatorioRegistoVisita> obterRelatorioRegistoVisita(int idTarefa);



    @Query("UPDATE registoVisitaResultado SET homologado = :homologado WHERE idTarefa =:idTarefa")
    abstract public Single<Integer> atualizarHomologacao(int idTarefa, boolean homologado);


    //---------------
    //Misc
    //----------------


    @Query("UPDATE registoVisitaResultado SET sincronizacao = 1 WHERE idTarefa =:idTarefa")
    abstract public Single<Integer> sincronizar(int idTarefa);


    @Query("SELECT COUNT(1) FROM registoVisitaResultado WHERE idTarefa =:idTarefa")
    abstract public Single<Boolean> existeRelatorio(int idTarefa);

}
