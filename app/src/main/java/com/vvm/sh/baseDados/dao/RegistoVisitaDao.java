package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.RegistoVisitaResultado;

import io.reactivex.Maybe;
import io.reactivex.Observable;

@Dao
abstract public class RegistoVisitaDao implements BaseDao<RegistoVisitaResultado> {


    @Query("SELECT * FROM registoVisitaResultado WHERE idTarefa =:idTarefa")
    abstract public Maybe<RegistoVisitaResultado> obterRegistoVisita(int idTarefa);

    @Query("SELECT CASE WHEN recebidoPor IS NULL OR funcao IS NULL THEN 0  ELSE 1 END as valido " +
            "FROM registoVisitaResultado " +
            "WHERE idTarefa =:idTarefa")
    abstract public Observable<Boolean> obterValidadeRegistoVisita(int idTarefa);


    /**
     * Metodo que permite validar o trabalho realizado
     * @param idRelatorio o identificador do relatorio
     * @return true caso seja valido ou false caso contrario
     */
//    public boolean validarTrabalhoRealizado(String idRelatorio) {
//
//        String query = "SELECT CASE WHEN COUNT(VALIDO) = SUM(VALIDO) AND COUNT(VALIDO) > 0 THEN 1 ELSE 0 END valido   ";
//        query += "FROM (			  ";
//        query += "SELECT idTarefa,			  ";
//        query += "CASE WHEN COUNT(id) = 0 THEN 0  			  ";
//        query += "ELSE 1 END as valido			  ";
//        query += "FROM registoVisita_trabalhoRealizado_resultado  		  ";
//        query += "GROUP BY idTarefa			  ";
//        query += ") as resultado			  ";
//        query += "WHERE idTarefa = ?		  ";
//
//        String argumentos [] = {
//                idRelatorio
//        };
//
//        return validar(query, argumentos);
//    }



    /**
     * Metodo que permite validar os dados do cliente de um registo de visita
     * @param idRelatorio o identificador do relatorio
     * @return true caso seja valido ou false caso contrario
     */
//    public boolean validarCliente(String idRelatorio) {
//
//        String query = " SELECT  ";
//
//        query += "CASE WHEN recebidoPor IS NULL OR funcao IS NULL THEN 0 ";
//        query += " ELSE 1 END as valido  ";
//
//        query += "FROM registoVisita_resultado ";
//
//        query += "WHERE idTarefa = ?   ";
//
//        String argumentos [] = {
//                idRelatorio
//        };
//
//        boolean resultado = false;
//
//        SQLiteDatabase bd = this.getReadableDatabase();
//        Cursor cursor = bd.rawQuery(query, argumentos);
//
//        if(cursor.moveToFirst() == true){
//            for (int i = 0; i < cursor.getCount(); ++i) {
//
//                resultado = MetodosConversor.converter_Integer_Para_Boolean(cursor.getInt(0));
//                cursor.moveToNext();
//            }
//        }
//
//        cursor.close();
//        bd.close();
//        return resultado;
//    }


}
