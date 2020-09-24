package com.vvm.sh.baseDados.dao;


import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.AreaChecklist;
import com.vvm.sh.baseDados.entidades.AreaChecklistResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Item;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
abstract public class AreaChecklistDao implements BaseDao<AreaChecklistResultado> {


    @Query("SELECT tp.* " +
            "FROM tipos as tp " +
            "LEFT JOIN( " +
            "SELECT atp.id as idAtividade, CASE WHEN area_chk_res.idChecklist IS NULL THEN  atp.idChecklist ELSE area_chk_res.idChecklist END as idChecklist " +
            "FROM atividadesPendentes as atp " +
            "LEFT JOIN (SELECT idChecklist, idAtividade FROM areasChecklistResultado WHERE idArea = " + Identificadores.ID_AREA_GERAL + " ) as area_chk_res " +
            "ON atp.id = area_chk_res.idChecklist AND atp.id = area_chk_res.idAtividade " +
            ") as chk_res " +
            "ON tp.id = chk_res.idChecklist " +

            "WHERE tipo = '" + TiposUtil.MetodosTipos.TIPOS_CHECKLIST + "' AND api = :api AND idAtividade = :idAtividade")
    abstract public Single<Tipo> obterChecklist(int idAtividade, int api);






    @Query("SELECT area_chk_res.idArea as idArea, id, descricao, IFNULL(subDescricao,'') as subDescricao, " +
            "" + Identificadores.Checklist.TIPO_AREA + " as tipo, 0 as completos, 0 as  total, '' as uid " +
            "FROM areasChecklistResultado as area_chk_res " +
            "LEFT JOIN (SELECT idArea, descricao, idChecklist FROM areasChecklist) as area_chk " +
            "ON area_chk_res.idChecklist = area_chk.idChecklist AND area_chk_res.idArea = area_chk.idArea " +


            "WHERE area_chk_res.idChecklist = :idChecklist AND idAtividade = :idAtividade " +
            "ORDER BY " +
            "CASE WHEN descricao = 'Geral' THEN 0 ELSE 1 END, " +
            "descricao, subDescricao ")
    abstract public Observable<List<Item>> obterAreas(int idAtividade, int idChecklist);



    @Query("SELECT area_chk_res.idArea as idArea, id, descricao, '' as subDescricao, " +
            "" + Identificadores.Checklist.TIPO_SECCAO + " as tipo, 0 as completos, 0 as  total, uid " +
            "FROM seccoesChecklist as seccao_chk " +
            "LEFT JOIN (SELECT idChecklist, idArea, id FROM areasChecklistResultado) as area_chk_res " +
            "ON seccao_chk.idChecklist = area_chk_res.idChecklist AND seccao_chk.idArea = area_chk_res.idArea  " +
            "WHERE area_chk_res.id = :idRegisto " +
            "")
    abstract public Observable<List<Item>> obterSeccoes(int idRegisto);









    @Query("SELECT * FROM areasChecklist WHERE idChecklist = :idChecklist AND idArea NOT IN(" + Identificadores.ID_AREA_GERAL + ")")
    abstract public Single<List<AreaChecklist>> obterAreasChecklist(int idChecklist);



    @Query("DELETE FROM areasChecklistResultado WHERE id = :id")
    abstract public Single<Integer> remover(int id);


    @Query("SELECT CASE WHEN COUNT(*) = 0 THEN 0 ELSE 1 END as valido " +
            "FROM areasChecklistResultado " +
            "WHERE idChecklist = :idChecklist AND idAtividade = :idAtividade AND idArea = :idArea " +
            "AND subDescricao = :subDescricao")
    abstract public Single<Boolean> validarSubDescricaoArea(int idAtividade, int idChecklist, int idArea, String subDescricao);


    @Query("SELECT CASE WHEN COUNT(*) = 0 THEN 0 ELSE 1 END as valido " +
            "FROM areasChecklistResultado " +
            "WHERE idChecklist = :idChecklist AND idAtividade = :idAtividade AND idArea = " + Identificadores.ID_AREA_GERAL + " ")
    abstract public Single<Boolean> validarAreaGeral(int idAtividade, int idChecklist);


//
//
//
//
//    /**
//     * Metodo que permite obter as areas da checklist de uma atividade
//     * @param idAtividade o identificador da atividade pendente
//     * @return uma lista
//     */
//    public ArrayList<ItemAdaptador> obterAreas(Context contexto, String idAtividade, String idChecklist) {
//
//        String query = "SELECT idRegisto, area, descricao as subDescricao, ar_chk_res.idArea as idArea  ";
//        query += "FROM areas_checklist_resultado as ar_chk_res ";
//        query += "OUTER LEFT JOIN (SELECT idArea, area, idChecklist FROM areas_checklist) as chk_ar ON   ar_chk_res.idChecklist = chk_ar.idChecklist  AND ar_chk_res.idArea = chk_ar.idArea ";
//        query += "WHERE idAtividadePendente = ?  AND ar_chk_res.idChecklist = ? ";
//
//        query += "ORDER BY ";
//        query += "CASE WHEN area = 'Geral' THEN 0 ELSE 1 END, ";
//        query += "area, descricao ";
//
//        String argumentos [] = { idAtividade, idChecklist };
//
//        ArrayList<ItemAdaptador> resultado = new ArrayList<ItemAdaptador>();
//
//        SQLiteDatabase bd = this.getReadableDatabase();
//        Cursor cursor = bd.rawQuery(query, argumentos);
//
//        if(cursor.moveToFirst() == true){
//
//            for (int i = 0; i < cursor.getCount(); ++i) {
//
//                resultado.add(new ItemArea(contexto, cursor.getString(cursor.getColumnIndex("idRegisto")), cursor.getString(cursor.getColumnIndex("area")), cursor.getString(cursor.getColumnIndex("subDescricao")),
//                        cursor.getString(cursor.getColumnIndex("idArea")),
//                        idAtividade));
//                cursor.moveToNext();
//            }
//        }
//
//        cursor.close();
//        bd.close();
//        return resultado;
//    }
//
//
//
//    /**
//     * Metodo que permite validar uma area da checklist
//     * @param idRegistoArea o identificador do registo da area
//     * @return [numero de seccoes preenchidas, o total das seccoes que exigem validacao, validade (0/1)]
//     * @category CHK sql # 5.1
//     */
//    public int [] validarArea(String idAtividade, String idRegistoArea) {
//
//        String query = "SELECT SUM(VALIDO) as respostas, COUNT(seccao) as total,  CASE WHEN COUNT(VALIDO) = SUM(VALIDO) AND COUNT(VALIDO) > 0 THEN 1 ELSE 0 END valido   ";
//        query += "FROM (   ";
//        query += "SELECT idAtividadePendente, ar_chk_res.idRegisto as idRegisto, chk_scs.idSeccao as idSeccao, seccao, chk_scs.tipo as tipo, IFNULL(numeroRespostas,0) as numeroRespostas,  total,   ";
//        query += "CASE WHEN itens.tipo = 'q' AND IFNULL(numeroRespostas,0) = 0 THEN 0   ";
//        query += "WHEN itens.tipo = 'q' AND IFNULL(numeroRespostas,0) != total THEN 0   ";
//        query += "ELSE 1 END as valido   ";
//        query += "FROM areas_checklist_resultado as ar_chk_res   ";
//        query += "OUTER LEFT JOIN (SELECT idChecklist, idArea, idSeccao, seccao, tipo  FROM seccoes_checklist) as chk_scs ON ar_chk_res.idChecklist = chk_scs.idChecklist AND ar_chk_res.idArea = chk_scs.idArea   ";
//        query += "OUTER LEFT JOIN (   ";
//        query += "SELECT   COUNT(tipo)as total, idChecklist, idArea, idSeccao, tipo FROM itens_checklist   ";
//        query += "WHERE    tipo = 'q'    ";
//        query += "GROUP BY idChecklist, idArea , idSeccao   ";
//        query += ") as itens    ";
//        query += "ON ar_chk_res.idChecklist = itens.idChecklist AND ar_chk_res.idArea = itens.idArea AND chk_scs.idSeccao = itens.idSeccao   ";
//        query += "OUTER LEFT JOIN (   ";
//        query += "SELECT idRegistoArea, idSeccao, COUNT(idItem) as numeroRespostas FROM questionario_checklist_resultado   ";
//        query += "WHERE  tipo = 'q'   ";
//        query += "GROUP BY idRegistoArea, idSeccao   ";
//        query += ") as qst_res ON ar_chk_res.idRegisto = qst_res.idRegistoArea AND chk_scs.idSeccao = qst_res.idSeccao   ";
//        query += ")T1   ";
//        query += "WHERE idAtividadePendente = ? AND idRegisto = ?   ";
//
//        String argumentos [] = { idAtividade, idRegistoArea };
//
//        int resultado [] = new int [3];
//
//        SQLiteDatabase bd = this.getReadableDatabase();
//        Cursor cursor = bd.rawQuery(query, argumentos);
//
//        if(cursor.moveToFirst() == true){
//
//            for (int i = 0; i < cursor.getCount(); ++i) {
//
//                resultado [0] = cursor.getInt(cursor.getColumnIndex("respostas"));
//                resultado [1] = cursor.getInt(cursor.getColumnIndex("total"));
//                resultado [2] = cursor.getInt(cursor.getColumnIndex("valido"));
//
//                cursor.moveToNext();
//            }
//        }
//
//        cursor.close();
//        bd.close();
//        return resultado;
//    }
//
//
//
//
//
//
//    /**
//     * Metodo que permite obter as seccoes de uma area da checklist
//     * @param idRegistoArea o identificador do registo da area
//     * @return uma lista
//     * @category CHK sql #1
//     */
//    public ArrayList<ItemAdaptador> obterSeccoes(Context contexto, String idRegistoArea) {
//
//        String query = "SELECT chk_scs.idSeccao as idSeccao, seccao, chk_scs.tipo as tipo  ";
//        query += "FROM areas_checklist_resultado as ar_chk_res ";
//        query += "OUTER LEFT JOIN (SELECT idChecklist, idArea, idSeccao, seccao, tipo  FROM seccoes_checklist) as chk_scs ON ar_chk_res.idChecklist = chk_scs.idChecklist AND ar_chk_res.idArea = chk_scs.idArea ";
//        query += "WHERE ar_chk_res.idRegisto = ? ";
//
//        String argumentos [] = {	idRegistoArea };
//
//        ArrayList<ItemAdaptador> resultado = new ArrayList<ItemAdaptador>();
//
//        SQLiteDatabase bd = this.getReadableDatabase();
//        Cursor cursor = bd.rawQuery(query, argumentos);
//
//        if(cursor.moveToFirst() == true){
//
//            for (int i = 0; i < cursor.getCount(); ++i) {
//
//                resultado.add(new ItemSeccao(contexto,
//                        cursor.getString(cursor.getColumnIndex("idSeccao")), cursor.getString(cursor.getColumnIndex("seccao")), cursor.getString(cursor.getColumnIndex("tipo")), idRegistoArea));
//                cursor.moveToNext();
//            }
//        }
//
//        cursor.close();
//        bd.close();
//        return resultado;
//    }
//
//
//    /**
//     * Metodo que permite validar uma seccao de uma area da checklist
//     * @param idRegistoArea o identificador do registo da area
//     * @param idSeccao o identificador da seccao
//     * @return [numero de respostas preenchidas, o total das respostas que exigem validacao, validade (0/1)]
//     * @category CHK sql # 1.1
//     */
//    public int [] validarSeccao(String idRegistoArea, String idSeccao) {
//
//        String query = "SELECT  IFNULL(numeroRespostas,0) as numeroRespostas,  total,  ";
//        query += "CASE WHEN itens.tipo = 'q' AND IFNULL(numeroRespostas,0) = 0 THEN 0 ";
//        query += "WHEN itens.tipo = 'q' AND IFNULL(numeroRespostas,0) != total THEN 0 ";
//        query += "ELSE 1 END as valido ";
//
//        query += "FROM areas_checklist_resultado as ar_chk_res ";
//
//        query += "OUTER LEFT JOIN (SELECT idChecklist, idArea, idSeccao, seccao, tipo  FROM seccoes_checklist) as chk_scs ON ar_chk_res.idChecklist = chk_scs.idChecklist AND ar_chk_res.idArea = chk_scs.idArea ";
//
//        query += "OUTER LEFT JOIN ( ";
//        query += "SELECT   COUNT(tipo)as total, idChecklist, idArea, idSeccao, tipo FROM itens_checklist ";
//        query += "WHERE    tipo = 'q'  ";
//        query += "GROUP BY idChecklist, idArea , idSeccao ";
//        query += ") as itens  ";
//        query += "ON ar_chk_res.idChecklist = itens.idChecklist AND ar_chk_res.idArea = itens.idArea AND chk_scs.idSeccao = itens.idSeccao ";
//
//        query += "OUTER LEFT JOIN ( ";
//        query += "SELECT idRegistoArea, idSeccao, COUNT(idItem) as numeroRespostas FROM questionario_checklist_resultado ";
//        query += "WHERE  tipo = 'q' ";
//        query += "GROUP BY idRegistoArea, idSeccao ";
//        query += ") as qst_res ON ar_chk_res.idRegisto = qst_res.idRegistoArea AND chk_scs.idSeccao = qst_res.idSeccao ";
//
//        query += "WHERE ar_chk_res.idRegisto = ?	AND chk_scs.idSeccao = ? ";
//
//        String argumentos [] = {
//                idRegistoArea, idSeccao
//        };
//
//        int resultado [] = new int [3];
//
//        SQLiteDatabase bd = this.getReadableDatabase();
//        Cursor cursor = bd.rawQuery(query, argumentos);
//
//        if(cursor.moveToFirst() == true){
//
//            for (int i = 0; i < cursor.getCount(); ++i) {
//
//                resultado [0] = cursor.getInt(cursor.getColumnIndex("numeroRespostas"));
//                resultado [1] = cursor.getInt(cursor.getColumnIndex("total"));
//                resultado [2] = cursor.getInt(cursor.getColumnIndex("valido"));
//
//                cursor.moveToNext();
//            }
//        }
//
//        cursor.close();
//        bd.close();
//        return resultado;
//    }
//





}
