package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.TipoNovo;
import com.vvm.sh.ui.atividadesPendentes.relatorios.equipamentos.modelos.Equipamento;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
abstract public class TipoNovoDao implements BaseDao<TipoNovo> {


    //equipamentos

    @Query("SELECT id, descricao, estado " +
            "FROM (" +
            "SELECT idProvisorio as id, descricao, " + Identificadores.ESTADO_PENDENTE + " as estado FROM tiposNovos WHERE tipo = '" + TiposUtil.MetodosTipos.TIPOS_MAQUINA + "' AND estado = " + Identificadores.ESTADO_PENDENTE + " " +
            "UNION " +
            "SELECT id, descricao, " + Identificadores.ESTADO_DEFINITIVO + " as estado FROM tipos  WHERE tipo = '" + TiposUtil.MetodosTipos.TIPOS_MAQUINA + "' AND api = :api AND ativo = 1 " +
            ") " +
            "WHERE descricao NOT IN (:registos) ORDER BY estado, id ")
    abstract public Observable<List<Equipamento>> obterEquipamentos_Excluir(int api, List<String> registos); //-int idAtividade,


    @Query("SELECT id, descricao, estado " +
            "FROM (" +
            "SELECT idProvisorio as id, descricao, " + Identificadores.ESTADO_PENDENTE + " as estado FROM tiposNovos WHERE tipo = '" + TiposUtil.MetodosTipos.TIPOS_MAQUINA + "' AND estado = " + Identificadores.ESTADO_PENDENTE + " " +
            "UNION " +
            "SELECT id, descricao, " + Identificadores.ESTADO_DEFINITIVO + " as estado FROM tipos  WHERE tipo = '" + TiposUtil.MetodosTipos.TIPOS_MAQUINA + "' AND api = :api AND ativo = 1 " +
            ") " +
            "WHERE descricao IN (:registos)")
    abstract public Observable<List<Equipamento>> obterEquipamentos_Incluir( int api, List<String> registos);


    @Query("SELECT id, descricao, estado " +
            "FROM (" +
            "SELECT idProvisorio as id, descricao, " + Identificadores.ESTADO_PENDENTE + " as estado FROM tiposNovos WHERE tipo = '" + TiposUtil.MetodosTipos.TIPOS_MAQUINA + "' AND estado = " + Identificadores.ESTADO_PENDENTE + " " +
            "UNION " +
            "SELECT id, descricao, " + Identificadores.ESTADO_DEFINITIVO + " as estado FROM tipos  WHERE tipo = '" + TiposUtil.MetodosTipos.TIPOS_MAQUINA + "' AND api = :api AND ativo = 1 " +
            ") " +
            "WHERE descricao NOT IN (:registos) AND descricao LIKE '%' || :pesquisa || '%' ")
    abstract public Maybe<List<Equipamento>> obterEquipamentos_Excluir(List<String> registos, String pesquisa, int api); //-int idAtividade,


    @Query("SELECT CASE WHEN IFNULL(COUNT(*), 0) > 0 THEN 1 ELSE 0 END as existente " +
            "FROM (" +
            "SELECT descricao FROM tiposNovos WHERE tipo = '" + TiposUtil.MetodosTipos.TIPOS_MAQUINA + "'   " +
            "UNION " +
            "SELECT descricao FROM tipos  WHERE tipo = '" + TiposUtil.MetodosTipos.TIPOS_MAQUINA + "'   " +
            ") " +
            "WHERE descricao = :descricao COLLATE NOCASE")
    abstract public Single<Boolean> validarEquipamento(String descricao);



    @Query("SELECT " +
            "CASE WHEN vrf_eq_res.codigo = " + Identificadores.ESTADO_PENDENTE + " THEN  tp_novos.descricao " +
            "ELSE tp_registados.descricao END as descricao " +


            "FROM verificacaoEquipamentosResultado as vrf_eq_res " +

            "LEFT JOIN (SELECT idProvisorio as id, descricao FROM tiposNovos WHERE tipo = '" + TiposUtil.MetodosTipos.TIPOS_MAQUINA + "') as tp_novos " +
            "ON vrf_eq_res.idEquipamento = tp_novos.id " +

            "LEFT JOIN (SELECT id, descricao FROM tipos  WHERE tipo = '" + TiposUtil.MetodosTipos.TIPOS_MAQUINA + "' AND api = :api AND ativo = 1) as tp_registados " +
            "ON vrf_eq_res.idEquipamento = tp_registados.id " +

            "WHERE vrf_eq_res.idAtividade = :idAtividade " +
            "ORDER BY codigo, vrf_eq_res.idEquipamento ")
    abstract public Maybe<List<String>> obterEquipamentos(int idAtividade, int api);

    /**
     * Metodo que permite obter o numero a ser utilizado para identificar uma maquina
     * @return o numero a ser utilizado para identificar uma maquina
     * @category GRL sql # 1
     */
    /*
    public int obterNumero(){

        int resultado = 0;

        String query = "SELECT ";
        query += "CASE WHEN contagemInterna IS NULL THEN numero ";
        query += "WHEN contagemInterna >= numero THEN  (contagemInterna + 1) ";
        query += "ELSE numero END as identificador ";
        query += "FROM registoContagem  ";
        query += "OUTER LEFT JOIN (SELECT MAX(id) as contagemInterna FROM tiposNovos WHERE tipo = 'TiposMaquina') ";
        query += "WHERE referencia = ? ";

        String argumentos [] = {IntendenteBdIF.REGISTO_equipamentos};

        SQLiteDatabase bd = this.getReadableDatabase();

        Cursor cursor = bd.rawQuery(query, argumentos);

        if(cursor.moveToFirst() == true){
            resultado = cursor.getInt(0);
        }

        cursor.close();
        bd.close();

        return resultado;

    }
    */


}
