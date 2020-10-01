package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.AreaChecklist;
import com.vvm.sh.baseDados.entidades.CheckList;
import com.vvm.sh.baseDados.entidades.ItemChecklist;
import com.vvm.sh.baseDados.entidades.SeccaoChecklist;
import com.vvm.sh.baseDados.entidades.TipoTemplateAvrLevantamento;
import com.vvm.sh.baseDados.entidades.TipoTemplateAvrRisco;
import com.vvm.sh.baseDados.entidades.TipoTemplatesAVRMedidaRisco;
import com.vvm.sh.ui.atividadesPendentes.relatorios.equipamentos.Equipamento;
import com.vvm.sh.ui.opcoes.modelos.Colecao;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.ui.opcoes.modelos.ResumoTipo;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
abstract public class TipoDao implements BaseDao<Tipo> {


    @Insert
    abstract public List<Long> inserir(List<Tipo> tipo);

    @Update
    abstract public Integer atualizar(List<Tipo> tipo);


    @Delete
    abstract public Integer remover(CheckList registo);

    @Insert
    abstract public Long inserir(CheckList registo);

    @Insert
    abstract public List<Long> inserirAreasChecklist(List<AreaChecklist> registo);

    @Insert
    abstract public List<Long> inserirSeccoesChecklis(List<SeccaoChecklist> registo);

    @Insert
    abstract public List<Long> inserirItensChecklis(List<ItemChecklist> registo);


    @Insert
    abstract public List<Long> inserirTemplateAvrLevantamento(List<TipoTemplateAvrLevantamento> tipo);

    @Update
    abstract public Integer atualizarTemplateAvrLevantamento(List<TipoTemplateAvrLevantamento> tipo);


    @Insert
    abstract public List<Long> inserirTemplateAvrRisco(List<TipoTemplateAvrRisco> tipo);

    @Update
    abstract public Integer atualizarTemplateAvrRisco(List<TipoTemplateAvrRisco> tipo);


    @Insert
    abstract public List<Long> inserirTemplatesAVRMedidaRisco(List<TipoTemplatesAVRMedidaRisco> tipo);

    @Update
    abstract public Integer atualizarTemplatesAVRMedidaRisco(List<TipoTemplatesAVRMedidaRisco> tipo);


    @Query("DELETE FROM tiposTemplatesAVRMedidasRisco WHERE id = :id AND origem = :origem")
    abstract public void removerTemplatesAVRMedidaRisco(int id, int origem);




/*
    @Query("SELECT descricao, numeroRegistos, seloTemporal " +
            "FROM atualizacoes as atl " +
            "LEFT JOIN (SELECT tipo, COUNT(id) as numeroRegistos FROM tipos GROUP BY tipo) as tp ON atl.descricao = tp.tipo " +
            "ORDER BY descricao ASC")
    abstract public Maybe<List<Colecao>> obterResumoTipos();
*/

    @Query("SELECT descricao, numeroRegistosSA, numeroRegistosSHT, seloTemporal " +
            "FROM atualizacoes as atl " +
            "LEFT JOIN (SELECT tipo, COUNT(id) as numeroRegistosSA FROM tipos WHERE ativo = 1 AND api = " + Identificadores.App.APP_SA + " GROUP BY tipo) as tp_sa ON atl.descricao = tp_sa.tipo " +
            "LEFT JOIN (SELECT tipo, COUNT(id) as numeroRegistosSHT FROM tipos WHERE ativo = 1 AND api = " + Identificadores.App.APP_ST + " GROUP BY tipo) as tp_sht ON atl.descricao = tp_sht.tipo " +
            "ORDER BY descricao ASC")
    abstract public Observable<List<ResumoTipo>> obterResumoTipos();




    @Query("SELECT * FROM tipos WHERE tipo = :tipo AND idPai = '' AND ativo = 1")
    abstract public Single<List<Tipo>> obterCrossSellingProdutos(String tipo);



    @Query("SELECT * FROM tipos WHERE tipo = :tipo AND ativo = 1")
    abstract public Flowable<List<Tipo>> obterTipos(String tipo);

    @Query("SELECT * FROM tipos WHERE tipo = :tipo AND id = :id AND ativo = 1")
    abstract public Flowable<Tipo> obterTipo(String tipo, int id);



    @Query("SELECT * FROM tipos WHERE tipo = :tipo AND idPai = :idPai AND ativo = 1")
    abstract public Flowable<List<Tipo>> obterTipos(String tipo, String idPai);


    @Query("SELECT * FROM tipos WHERE tipo = :tipo AND api = :api AND ativo = 1")
    abstract public Flowable<List<Tipo>> obterTipos(String tipo, int api);

    @Query("SELECT * FROM tipos WHERE tipo = :tipo AND api = :api AND ativo = 1")
    abstract public Single<List<Tipo>> obterTipos_(String tipo, int api);

    @Query("SELECT * FROM tipos WHERE tipo = :tipo AND api = :api AND idPai = :idPai AND ativo = 1")
    abstract public Single<List<Tipo>> obterTipos_(String tipo, int api, String idPai);



    @Query("SELECT * FROM tipos WHERE tipo = :tipo AND api = :api AND id NOT IN (:registos) AND ativo = 1")
    abstract public Flowable<List<Tipo>> obterTipos_Excluir(String tipo, List<Integer> registos, int api);

    @Query("SELECT * FROM tipos WHERE tipo = :tipo AND api = :api AND id NOT IN (:registos) AND descricao LIKE '%' || :pesquisa || '%' AND ativo = 1")
    abstract public Maybe<List<Tipo>> obterTipos_Excluir(String tipo, List<Integer> registos, String pesquisa, int api);


    @Query("SELECT * FROM tipos WHERE tipo = :tipo AND api = :api AND id IN (:registos) AND ativo = 1")
    abstract public Flowable<List<Tipo>> obterTipos_Incluir(String tipo, List<Integer> registos, int api);



    @Query("SELECT tp.id as id " +
            "FROM tipos as tp " +
            "LEFT JOIN (SELECT id FROM checklist) as chk ON tp.id = chk.id " +
            "WHERE tipo = '" + TiposUtil.MetodosTipos.TIPOS_CHECKLIST + "' AND api = :api  AND ativo = 1")
    abstract public Maybe<List<Integer>> obterChecklists(int api);


    //equipamentos

    @Query("SELECT id, descricao, 0 as estado " +
            "FROM tipos " +
            "WHERE tipo = '" + TiposUtil.MetodosTipos.TIPOS_MAQUINA + "' AND api = :api AND ativo = 1 " +
            "AND id NOT IN (:registos)")
    abstract public Observable<List<Equipamento>> obterEquipamentos_Excluir(int api, List<Integer> registos); //-int idAtividade,


    @Query("SELECT id, descricao, 0 as estado " +
            "FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.TIPOS_MAQUINA + "' AND api = :api  AND ativo = 1 " +
            "AND id IN (:registos)")
    abstract public Observable<List<Equipamento>> obterEquipamentos_Incluir( int api, List<Integer> registos);


    @Query("SELECT id, descricao, 0 as estado " +
            "FROM tipos " +
            "WHERE tipo = '" + TiposUtil.MetodosTipos.TIPOS_MAQUINA + "' AND api = :api AND ativo = 1 " +
            "AND id NOT IN (:registos) AND descricao LIKE '%' || :pesquisa || '%' ")
    abstract public Maybe<List<Equipamento>> obterEquipamentos_Excluir(List<Integer> registos, String pesquisa, int api); //-int idAtividade,


    @Query("SELECT CASE WHEN IFNULL(COUNT(*), 0) > 0 THEN 1 ELSE 0 END as existente " +
            "FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.TIPOS_MAQUINA + "' AND api = :api  " +
            "AND descricao = :descricao")
    abstract public Single<Boolean> validarEquipamento(int api, String descricao);



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
