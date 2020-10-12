package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.RegistoVisitaResultado;
import com.vvm.sh.ui.registoVisita.modelos.DadosCliente;
import com.vvm.sh.ui.registoVisita.modelos.RelatorioRegistoVisita;
import com.vvm.sh.util.constantes.Identificadores;

import io.reactivex.Maybe;
import io.reactivex.Observable;

@Dao
abstract public class RegistoVisitaDao implements BaseDao<RegistoVisitaResultado> {


    @Transaction
    @Query("SELECT * FROM tarefas WHERE idTarefa =:idTarefa")
    abstract public Maybe<DadosCliente> obterDadosCliente(int idTarefa);

    @Query("SELECT CASE WHEN recebidoPor IS NULL OR funcao IS NULL THEN 0  ELSE 1 END as clienteValido, " +
            "trabalhoValido, numeroTrabalhos, " +
            "CASE WHEN IFNULL(img.idTarefa, 0) = 0 THEN 0 ELSE 1 END as assinaturaValido " +
            "FROM registoVisitaResultado as rg_visit_res " +
            "" +
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

            "WHERE rg_visit_res.idTarefa =:idTarefa")
    abstract public Observable<RelatorioRegistoVisita> obterValidadeRegistoVisita(int idTarefa);


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
