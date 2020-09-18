package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.ColaboradorResultado;
import com.vvm.sh.baseDados.entidades.Morada;
import com.vvm.sh.ui.quadroPessoal.modelos.ColaboradorRegisto;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
abstract public class QuadroPessoalDao implements BaseDao<ColaboradorResultado> {

    /*
    String query = "SELECT idRegisto, qp.id as id, morada, IFNULL(nome, '') as nome, mrd.idMorada as idMorada, IFNULL(posto, '') as posto, sexo, categoria, idCategoriaProfissional, profissao, ";
    query += "IFNULL(dataNascimento, '') as dataNascimento, IFNULL(dataAdmissao, '') as dataAdmissao, IFNULL(dataAdmissaoFuncao, '') as dataAdmissaoFuncao, ";
    query += "estado , origem, IFNULL(profissao, '') as profissao, IFNULL(nacionalidade, '') as nacionalidade ";

    query += "FROM( ";
    query += "SELECT qp.idTarefa, qp.id, qp_res.idRegisto,  ";
    query += "CASE WHEN (qp_res.nome   IS NULL OR qp_res.nome = '') THEN qp.nome   ELSE qp_res.nome   END as nome,  ";
    //query += "qp_res.nome  as nome,  ";
    query += "CASE WHEN qp_res.idMorada IS NULL THEN qp.idMorada ELSE qp_res.idMorada END as idMorada,  ";
    query += "CASE WHEN qp_res.estado   IS NULL THEN qp.estado   ELSE qp_res.estado   END as estado,  ";
    query += "CASE WHEN origem          IS NULL THEN " + IdentificadoresIF.ORIGEM_DADOS_WS + "  ELSE origem          END as origem,  ";
    query += "posto, ";
    query += "CASE WHEN qp_res.dataNascimento   IS NULL THEN qp.dataNascimento   ELSE qp_res.dataNascimento   END as dataNascimento,  ";
    query += "dataAdmissao, dataAdmissaoFuncao, ";
    query += "CASE WHEN qp_res.sexo   IS NULL THEN qp.sexo   ELSE qp_res.sexo   END as sexo,  ";
    query += "idCategoriaProfissional, ";
    query += "profissao, ";
    query += "CASE WHEN qp_res.nacionalidade   IS NULL THEN qp.nacionalidade   ELSE qp_res.nacionalidade   END as nacionalidade  ";
    query += "FROM quadroPessoal as qp ";

    query += "OUTER LEFT JOIN (";
    query += "SELECT idTarefa, id, idRegisto, nome, idMorada, estado, origem, posto, dataNascimento, dataAdmissao, dataAdmissaoFuncao, sexo, idCategoriaProfissional, profissao, nacionalidade ";
    query += "FROM quadroPessoal_resultado) as qp_res ";
    query += "ON qp.idTarefa = qp_res.idTarefa AND qp.id = qp_res.id ";

    query += "UNION ";

    query += "SELECT idTarefa, id, idRegisto, nome, idMorada, estado, origem, posto, dataNascimento, dataAdmissao, dataAdmissaoFuncao, sexo, idCategoriaProfissional, profissao, nacionalidade   ";
    query += "FROM quadroPessoal_resultado WHERE id IS NULL ";
    query += ") as qp ";

    query += "OUTER LEFT JOIN (SELECT idMorada, rua || '   ' || cp4 || ' ' || localidade as morada, idTarefa FROM moradas WHERE origem = ?) as mrd ON  qp.idMorada = mrd.idMorada  AND qp.idTarefa = mrd.idTarefa ";
    query += "OUTER LEFT JOIN (SELECT id, descricao as categoria FROM tipos WHERE tipo = 'CategoriasProfissionais') as tp_categoria ON qp.idCategoriaProfissional = tp_categoria.id	 ";
    query += "WHERE qp.idTarefa = ? ";
    */

    /*
    @Transaction
    @Query(" SELECT id, nome " +
            "FROM (" +
            "SELECT clb.idTarefa as idTarefa, clb.id as id, " +
            "CASE WHEN (clb_res.nome IS NULL OR clb_res.nome = '') THEN clb.nome ELSE clb_res.nome END as nome " +
            "FROM colaboradores as clb " +
            "LEFT JOIN (SELECT * FROM colaboradoresResultado as clb_res WHERE origem = " + Identificadores.Origens.ORIGEM_WS + ") as clb_res ON clb.id = clb_res.id " +
            "UNION " +
            "SELECT idTarefa, id, nome " +
            "FROM colaboradoresResultado WHERE origem = " + Identificadores.Origens.ORIGEM_BD + " " +
            ") " +
            "WHERE idTarefa = :idTarefa")
    */
    @Transaction
    @Query("SELECT clb.id as id, nome, clb.idMorada as idMorada, morada, " +
            "IFNULL(clb_res.estado, clb.estado) as estado, " + Identificadores.Origens.ORIGEM_WS + " as origem, posto, " +
            "idRegisto, idResultado, " +
            "dataNascimento, sexo " +
            "FROM colaboradores as clb " +
            "LEFT JOIN(SELECT id as idResultado, idRegisto, posto, estado FROM colaboradoresResultado) as clb_res ON  clb.id =  CAST(clb_res.idRegisto AS INTEGER)  " +
            "LEFT JOIN (SELECT id, endereco || '\n' || cp4 || ' ' || localidade as morada, idTarefa FROM moradas WHERE tipo = " + Identificadores.Origens.ORIGEM_MORADA + ") as mrd " +
            "ON  clb.idMorada = mrd.id  AND clb.idTarefa = mrd.idTarefa " +
            "WHERE clb.idTarefa = :idTarefa")
    abstract public Observable<List<ColaboradorRegisto>> obterQuadroPessoal(int idTarefa);


    /*
    @Transaction
    @Query(" SELECT id, nome " +
            "FROM (" +
            "SELECT clb.idTarefa as idTarefa, clb.id as id, " +
            "CASE WHEN (clb_res.nome IS NULL OR clb_res.nome = '') THEN clb.nome ELSE clb_res.nome END as nome " +
            "FROM colaboradores as clb " +
            "LEFT JOIN (SELECT * FROM colaboradoresResultado as clb_res WHERE origem = " + Identificadores.Origens.ORIGEM_WS + ") as clb_res ON clb.id = clb_res.id " +
            "UNION " +
            "SELECT idTarefa, id, nome " +
            "FROM colaboradoresResultado WHERE origem = " + Identificadores.Origens.ORIGEM_BD + " " +
            ") " +
            "WHERE id = :id")
    */
    @Transaction
    @Query("SELECT clb.id as id, nome, clb.idMorada as idMorada, " +
            "IFNULL(clb_res.estado, clb.estado) as estado ,  " + Identificadores.Origens.ORIGEM_WS + " as origem, posto, " +
            "idRegisto, idResultado, " +
            "dataNascimento, sexo " +
            "FROM colaboradores as clb " +
            "LEFT JOIN(SELECT id as idResultado, idRegisto, posto, estado FROM colaboradoresResultado) as clb_res ON  clb.id =  CAST(clb_res.idRegisto AS INTEGER)  " +
            "LEFT JOIN (SELECT id, endereco || '\n' || cp4 || ' ' || localidade as morada, idTarefa FROM moradas WHERE tipo = " + Identificadores.Origens.ORIGEM_MORADA + ") as mrd " +
            "ON  clb.idMorada = mrd.id  AND clb.idTarefa = mrd.idTarefa " +
            "WHERE clb.id = :id")
    abstract public Maybe<ColaboradorRegisto> obterColaborador(int id);


    @Query("DELETE FROM colaboradoresResultado WHERE id = :id")
    abstract public Single<Integer> removerColaborador(int id);



    @Query("SELECT * FROM moradas WHERE idTarefa =:idTarefa AND tipo = " + Identificadores.Origens.ORIGEM_MORADA +"")
    abstract public Maybe<List<Morada>> obterMoradas(int idTarefa);


}
