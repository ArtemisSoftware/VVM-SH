package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.ColaboradorResultado;
import com.vvm.sh.baseDados.entidades.Morada;
import com.vvm.sh.ui.quadroPessoal.modelos.ColaboradorRegisto;
import com.vvm.sh.util.constantes.AppConfig;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
abstract public class QuadroPessoalDao implements BaseDao<ColaboradorResultado> {


    @Transaction
    @Query("SELECT clb.id as id, nome, idMorada, morada, estado, origem, posto, idRegisto, idResultado, dataNascimento, sexo, idCategoriaProfissional, categoriaProfissional, profissao, dataAdmissao, dataAdmissaoFuncao " +
            "FROM (" +

            "SELECT idTarefa, clb.id as id, nome, " +
            "IFNULL(clb_res.idMorada, clb.idMorada) as idMorada, " +
            "IFNULL(clb_res.estado, clb.estado) as estado, " + Identificadores.Origens.ORIGEM_WS + " as origem, posto, " +
            "idRegisto, idResultado, " +
            "IFNULL(clb_res.dataNascimento, clb.dataNascimento) as dataNascimento, sexo, idCategoriaProfissional, profissao, dataAdmissao, dataAdmissaoFuncao " +
            "FROM colaboradores as clb " +
            "LEFT JOIN(SELECT id as idResultado, idRegisto, posto, estado, idCategoriaProfissional, idMorada, dataNascimento, profissao, dataAdmissao, dataAdmissaoFuncao FROM colaboradoresResultado) as clb_res " +
            "ON  clb.id =  CAST(clb_res.idRegisto AS INTEGER)  " +

            "UNION " +

            "SELECT idTarefa, id, nome, idMorada, estado, origem, posto, idRegisto, id as idResultado, dataNascimento, sexo, idCategoriaProfissional, profissao, dataAdmissao, dataAdmissaoFuncao " +
            "FROM colaboradoresResultado " +
            "WHERE origem = " + Identificadores.Origens.ORIGEM_BD + " "+
            ") as clb " +
            "" +
            "" +
            "" +
            "" +
            "LEFT JOIN (SELECT id, endereco || '\n' || cp4 || ' ' || localidade as morada, idTarefa FROM moradas WHERE tipo = " + Identificadores.Origens.ORIGEM_MORADA + ") as mrd " +
            "ON  clb.idMorada = mrd.id  AND clb.idTarefa = mrd.idTarefa " +
            "LEFT JOIN (SELECT id, descricao as categoriaProfissional FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.CATEGORIAS_PROFISSIONAIS + "') as tp_cat " +
            "ON clb.idCategoriaProfissional = tp_cat.id " +
            "WHERE clb.idTarefa = :idTarefa  ORDER BY nome ASC " +
            "LIMIT :offset OFFSET 0 ")
    abstract public Observable<List<ColaboradorRegisto>> obterQuadroPessoal(int idTarefa, int offset);



    @Transaction
    @Query("SELECT clb.id as id, nome, idMorada, morada, estado, origem, posto, idRegisto, idResultado, dataNascimento, sexo, idCategoriaProfissional, categoriaProfissional, profissao, dataAdmissao, dataAdmissaoFuncao " +
            "FROM (" +

            "SELECT idTarefa, clb.id as id, nome, " +
            "IFNULL(clb_res.idMorada, clb.idMorada) as idMorada, " +
            "IFNULL(clb_res.estado, clb.estado) as estado, " + Identificadores.Origens.ORIGEM_WS + " as origem, posto, " +
            "idRegisto, idResultado, " +
            "IFNULL(clb_res.dataNascimento, clb.dataNascimento) as dataNascimento, sexo, idCategoriaProfissional, profissao, dataAdmissao, dataAdmissaoFuncao " +
            "FROM colaboradores as clb " +
            "LEFT JOIN(SELECT id as idResultado, idRegisto, posto, estado, idCategoriaProfissional, idMorada, dataNascimento, profissao, dataAdmissao, dataAdmissaoFuncao FROM colaboradoresResultado) as clb_res " +
            "ON  clb.id =  CAST(clb_res.idRegisto AS INTEGER)  " +

            "UNION " +

            "SELECT idTarefa, id, nome, idMorada, estado, origem, posto, idRegisto, id as idResultado, dataNascimento, sexo, idCategoriaProfissional, profissao, dataAdmissao, dataAdmissaoFuncao " +
            "FROM colaboradoresResultado " +
            "WHERE origem = " + Identificadores.Origens.ORIGEM_BD + " "+
            ") as clb " +


            "LEFT JOIN (SELECT id, endereco || '\n' || cp4 || ' ' || localidade as morada, idTarefa FROM moradas WHERE tipo = " + Identificadores.Origens.ORIGEM_MORADA + ") as mrd " +
            "ON  clb.idMorada = mrd.id  AND clb.idTarefa = mrd.idTarefa " +
            "LEFT JOIN (SELECT id, descricao as categoriaProfissional FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.CATEGORIAS_PROFISSIONAIS + "') as tp_cat " +
            "ON clb.idCategoriaProfissional = tp_cat.id " +
            "WHERE clb.idTarefa = :idTarefa AND (nome LIKE '%' || :nome || '%')   ")
    abstract public Single<List<ColaboradorRegisto>> pesquisarQuadroPessoal(int idTarefa, String nome);







    @Transaction
    @Query("SELECT clb.id as id, nome, idMorada, morada, estado, origem, posto, idRegisto, idResultado, dataNascimento, sexo, idCategoriaProfissional, categoriaProfissional, profissao, dataAdmissao, dataAdmissaoFuncao " +
            "FROM (" +

            "SELECT idTarefa, clb.id as id, nome, " +
            "IFNULL(clb_res.idMorada, clb.idMorada) as idMorada, " +
            "IFNULL(clb_res.estado, clb.estado) as estado, " + Identificadores.Origens.ORIGEM_WS + " as origem, posto, " +
            "idRegisto, idResultado, " +
            "IFNULL(clb_res.dataNascimento, clb.dataNascimento) as dataNascimento, sexo, idCategoriaProfissional, profissao, dataAdmissao, dataAdmissaoFuncao " +
            "FROM colaboradores as clb " +
            "LEFT JOIN(SELECT id as idResultado, idRegisto, posto, estado, idCategoriaProfissional, idMorada, dataNascimento, profissao, dataAdmissao, dataAdmissaoFuncao FROM colaboradoresResultado) as clb_res " +
            "ON  clb.id =  CAST(clb_res.idRegisto AS INTEGER)  " +

            "UNION " +

            "SELECT idTarefa, id, nome, idMorada, estado, origem, posto, idRegisto, id as idResultado, dataNascimento, sexo, idCategoriaProfissional, profissao, dataAdmissao, dataAdmissaoFuncao " +
            "FROM colaboradoresResultado " +
            "WHERE origem = " + Identificadores.Origens.ORIGEM_BD + " "+
            ") as clb " +
            "" +
            "" +
            "" +
            "" +
            "LEFT JOIN (SELECT id, endereco || '\n' || cp4 || ' ' || localidade as morada, idTarefa FROM moradas WHERE tipo = " + Identificadores.Origens.ORIGEM_MORADA + ") as mrd " +
            "ON  clb.idMorada = mrd.id  AND clb.idTarefa = mrd.idTarefa " +
            "LEFT JOIN (SELECT id, descricao as categoriaProfissional FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.CATEGORIAS_PROFISSIONAIS + "') as tp_cat " +
            "ON clb.idCategoriaProfissional = tp_cat.id " +
            "WHERE clb.id = :id AND origem = :origem")
    abstract public Maybe<ColaboradorRegisto> obterColaborador(int id, int origem);


    @Query("DELETE FROM colaboradoresResultado WHERE id = :id")
    abstract public Single<Integer> removerColaborador(int id);



    @Query("SELECT * FROM moradas WHERE idTarefa =:idTarefa AND tipo = " + Identificadores.Origens.ORIGEM_MORADA +"")
    abstract public Maybe<List<Morada>> obterMoradas(int idTarefa);


}
