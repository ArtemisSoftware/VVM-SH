package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.vvm.sh.baseDados.entidades.AvaliacaoAmbientalResultado;
import com.vvm.sh.baseDados.entidades.RelatorioAmbientalResultado;
import com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.AvaliacaoAmbiental;
import com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.modelos.RelatorioAmbiental;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
abstract public class AvaliacaoAmbientalDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract public Single<Long> inserir(RelatorioAmbientalResultado registo);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    abstract public Single<Integer> atualizar(RelatorioAmbientalResultado registo);

    @Query("SELECT * FROM relatorioAmbientalResultado WHERE idAtividade = :idAtividade AND tipo = :tipo")
    abstract public Maybe<RelatorioAmbientalResultado> obterGeral(int idAtividade, int tipo);





    @Query("SELECT * FROM avaliacoesAmbientaisResultado WHERE idRelatorio = :idRelatorio")
    abstract public Observable<List<AvaliacaoAmbientalResultado>> obterAvaliacoes(int idRelatorio);


    @Query("SELECT * FROM avaliacoesAmbientaisResultado WHERE id = :id")
    abstract public Maybe<AvaliacaoAmbientalResultado> obterAvaliacao(int id);












/*
    String query = "SELECT idAvaliacao,  anexoArea, IFNULL(numeroCategoriasProfissionais, 0) as numeroCategoriasProfissionais, IFNULL(numeroMedidas, 0) as numeroMedidas, ";
    query += "nome, sexo, tp_iluminacao.tipoIluminacao as tipoIluminacao, emedioLx, idElx, eLxArea, eLx, local_geral || ' -> ' || local_especifico as local, ";
    query += "idArea, av_am_res.tipoIluminacao as idTipoIluminacao,   ";

    query += "   ";
    query += " ";
    query += "  ";

    query += " ";
    query += "  ";

    query += "FROM avaliacaoAmbiental_resultado as av_am_res ";
    query += "OUTER LEFT JOIN (SELECT id, descricao as area FROM tipos WHERE tipo = 'TiposArea') as tp_area ON av_am_res.idArea = tp_area.id ";
    query += "OUTER LEFT JOIN (SELECT id, descricao as tipoIluminacao FROM tipos WHERE tipo = 'TiposIluminacao') as tp_iluminacao ON  av_am_res.tipoIluminacao = tp_iluminacao.id ";

    query += "OUTER LEFT JOIN (SELECT id, descricao as local_geral FROM tipos WHERE tipo = 'Iluminancia') as tp_local ON  av_am_res.eLxArea = tp_local.id ";
    query += "OUTER LEFT JOIN (SELECT id, codigo, descricao as local_especifico FROM tipos WHERE tipo = 'Iluminancia') as tp_local_especifico ON  av_am_res.idElx = tp_local_especifico.id AND av_am_res.eLx = tp_local_especifico.codigo  ";

    query += "OUTER LEFT JOIN (SELECT id, COUNT(idCategoria) as numeroCategoriasProfissionais  FROM categoriasProfissionais_resultado WHERE origem = ? GROUP BY id) as ctPro ON  av_am_res.idAvaliacao = ctPro.id ";
    query += "OUTER LEFT JOIN (SELECT id, COUNT(idMedida) as numeroMedidas  FROM medidas_resultado WHERE origem = ? GROUP BY id) as med ON  av_am_res.idAvaliacao = med.id ";

    query += " ";

    String argumentos [] = {
            IdentificadoresIF.ORIGEM_RELATORIO_ILUMINACAO + "", IdentificadoresIF.ORIGEM_RELATORIO_ILUMINACAO + "", idRelatorio
    };
*/

    @Query("SELECT *," +

            "CASE WHEN  (CAST(emedioLx AS INTEGER) <  (CAST(eLx AS INTEGER) - ((10 * CAST(eLx AS INTEGER)) / 100))) = 0 THEN 1 " +
            //--"WHEN  CAST(emedioLx AS INTEGER) <  (CAST(eLx AS INTEGER) - ((10 * CAST(eLx AS INTEGER)) / 100)) AND  IFNULL(numeroMedidas, 0) > 0 THEN 1 " +
            "ELSE 0 END as valido, " +

            "CASE WHEN  CAST(emedioLx AS INTEGER) <  (CAST(eLx AS INTEGER) - ((10 * CAST(eLx AS INTEGER)) / 100)) THEN 1  " +
            "ELSE 0 END as necessitaMedidas " +

            //--"area, local_geral || ' -> ' || local_especifico as local" +

            "FROM avaliacoesAmbientaisResultado " +
            "WHERE idRelatorio = :idRelatorio")
    abstract public Observable<List<AvaliacaoAmbiental>> obterAvaliacoesIluminacao(int idRelatorio);





/*
    String query = "SELECT idAvaliacao, area , anexoArea, IFNULL(numeroCategoriasProfissionais, 0) as numeroCategoriasProfissionais, IFNULL(numeroMedidas, 0) as numeroMedidas, temperatura, humidadeRelativa, homens, mulheres,   ";
    query += "idArea, ";

    query += "  ";
    query += " ";
    query += " ";
    query += "  ";


    query += " ";
    query += " ";
    query += "  ";

    query += "FROM avaliacaoAmbiental_resultado as av_am_res ";
    query += "OUTER LEFT JOIN (SELECT id, descricao as area FROM tipos WHERE tipo = 'TiposArea') as tp_area ON av_am_res.idArea = tp_area.id ";
    query += "OUTER LEFT JOIN (SELECT id, descricao as tipoIluminacao FROM tipos WHERE tipo = 'TiposIluminacao') as tp_iluminacao ON  av_am_res.tipoIluminacao = tp_iluminacao.id ";

    query += "OUTER LEFT JOIN (SELECT id, COUNT(idCategoria) as numeroCategoriasProfissionais  FROM categoriasProfissionais_resultado WHERE origem = ? GROUP BY id) as ctPro ON  av_am_res.idAvaliacao = ctPro.id ";
    query += "OUTER LEFT JOIN (SELECT id, COUNT(idMedida) as numeroMedidas  FROM medidas_resultado WHERE origem = ? GROUP BY id) as med ON  av_am_res.idAvaliacao = med.id ";

    query += "WHERE idRelatorio = ? ";

    String argumentos [] = {
            IdentificadoresIF.ORIGEM_RELATORIO_TEMPERATURA_HUMIDADE + "", IdentificadoresIF.ORIGEM_RELATORIO_TEMPERATURA_HUMIDADE + "", idRelatorio
    };
    */
    @Query("SELECT *, " +
            "CASE  " +
            "WHEN  (CAST(temperatura AS INTEGER) < 18 OR CAST(temperatura AS INTEGER) > 22) AND  IFNULL(/*numeroMedidas*/ 0, 0) = 0  THEN 0 " +
            "WHEN  (CAST(humidadeRelativa AS INTEGER) < 50 OR CAST(humidadeRelativa AS INTEGER) > 70)  AND  IFNULL(/*numeroMedidas*/ 0, 0) = 0 THEN 0 " +
            "ELSE 1 END as valido, " +

            "CASE WHEN  (CAST(temperatura AS INTEGER) < 18 OR CAST(temperatura AS INTEGER) > 22)  THEN 1 " +
            "WHEN  (CAST(humidadeRelativa AS INTEGER) < 50 OR CAST(humidadeRelativa AS INTEGER) > 70)  THEN 1 " +
            "ELSE 0 END as necessitaMedidas " +
            "" +
            "FROM avaliacoesAmbientaisResultado " +
            "WHERE idRelatorio = :idRelatorio")
    abstract public Observable<List<AvaliacaoAmbiental>> obterAvaliacoesTemperaturaHumidade(int idRelatorio);








    //--------------
    //Validacao
    //--------------

    @Query("SELECT id as idRelatorio, " +
            "CASE WHEN marca IS NULL OR numeroSerie IS NULL OR data IS NULL THEN 0 ELSE 1 " +
            "END as valido " +
            "FROM relatorioAmbientalResultado " +
            "WHERE  idAtividade = :idAtividade AND tipo = :tipo")
    abstract public Observable<RelatorioAmbiental> obterValidadeRelatorio(int idAtividade, int tipo);



    @Query("SELECT id FROM relatorioAmbientalResultado WHERE idAtividade = :idAtividade AND tipo = :tipo")
    abstract public Maybe<Integer> obterIdRelatorio(int idAtividade, int tipo);

    @Query("SELECT " +
            "CASE WHEN marca IS NULL OR numeroSerie IS NULL OR data IS NULL THEN 0 ELSE 1 " +
            "END as valido " +
            "FROM relatorioAmbientalResultado " +
            "WHERE  idAtividade = :idAtividade AND tipo = :tipo")
    abstract public Maybe<Boolean> obterValidadeGeral(int idAtividade, int tipo);




    @Query("SELECT CASE WHEN COUNT(VALIDO) = SUM(VALIDO) AND COUNT(VALIDO) > 0 THEN 1 ELSE 0 END valido " +
            "FROM(   " +
            "SELECT idRelatorio,   " +
            "CASE WHEN CAST(emedioLx AS INTEGER) <  (CAST(eLx AS INTEGER) - ((10 * CAST(eLx AS INTEGER)) / 100)) AND IFNULL(numeroMedidas, 0) = 0 THEN 0  " +
            "ELSE 1 END as valido  " +
            "FROM avaliacoesAmbientaisResultado as av_amb_res  " +
            "LEFT JOIN (SELECT id, COUNT(idMedida) as numeroMedidas FROM medidasResultado WHERE origem = " + Identificadores.Origens.ORIGEM_RELATORIO_ILUMINACAO + " GROUP BY id) as ct_medidas " +
            "ON av_amb_res.id = ct_medidas.id  " +
            ") as validacao " +
            "WHERE idRelatorio = (SELECT id FROM relatorioAmbientalResultado WHERE idAtividade = :idAtividade AND tipo = :tipo) ")
    abstract public Maybe<Boolean> obterValidadeAvaliacoesIluminacao(int idAtividade, int tipo);




    @Query("SELECT CASE WHEN COUNT(VALIDO) = SUM(VALIDO) AND COUNT(VALIDO) > 0 THEN 1 ELSE 0 END valido	" +
            "FROM(	" +
            "SELECT  idRelatorio,	" +
            "CASE WHEN  (CAST(temperatura AS INTEGER) < 18 OR CAST(temperatura AS INTEGER) > 22) AND  IFNULL(numeroMedidas, 0)  = 0 THEN 0	" +
            "WHEN  (CAST(humidadeRelativa AS INTEGER) < 50 OR CAST(humidadeRelativa AS INTEGER) > 70) AND  IFNULL(numeroMedidas, 0)  = 0 THEN 0 " +
            "WHEN IFNULL(numeroCategorias, 0)  = 0 THEN 0 " +
            "ELSE 1 END as valido  " +
            "FROM avaliacoesAmbientaisResultado as av_amb_res	" +
            "LEFT JOIN (SELECT id, COUNT(idMedida) as numeroMedidas FROM medidasResultado WHERE origem = " + Identificadores.Origens.ORIGEM_RELATORIO_TEMPERATURA_HUMIDADE + " GROUP BY id) as ct_medidas " +
            "ON av_amb_res.id = ct_medidas.id	" +
            "LEFT JOIN ( " +
            "SELECT id, COUNT(idCategoriaProfissional) as numeroCategorias FROM categoriasProfissionaisResultado " +
            "WHERE origem = " + Identificadores.Origens.ORIGEM_RELATORIO_TEMPERATURA_HUMIDADE + " AND idCategoriaProfissional NOT NULL AND idCategoriaProfissional != '' " +
            "GROUP BY id) " +
            "as ct_categorias_profissionais ON av_amb_res.id = ct_categorias_profissionais.id	" +
            ") as validacao	" +
            "WHERE idRelatorio =  (SELECT id FROM relatorioAmbientalResultado WHERE idAtividade = :idAtividade AND tipo = :tipo) ")
    abstract public Maybe<Boolean> obterValidadeAvaliacoesTemperaturaHumidade(int idAtividade, int tipo);





    @Query("SELECT medida " +
            "FROM ( " +
            "SELECT  " +
            "CASE WHEN SUM(VALIDO) > 0 AND COUNT(VALIDO) > 0 THEN 2 ELSE 1 END as idMedidaRecomendada, " +
            "idRelatorio    " +
            "FROM (" +
            "SELECT " +
            "CASE WHEN  CAST(emedioLx AS INTEGER) <  (CAST(eLx AS INTEGER) - ((10 * CAST(eLx AS INTEGER)) / 100)) THEN 1 ELSE 0 END as VALIDO, " +
            "idRelatorio     " +
            "FROM avaliacoesAmbientaisResultado) as medidas GROUP BY idRelatorio) as recm    " +
            "LEFT JOIN (SELECT id, descricao as medida FROM tipos WHERE tipo = 'ConclusaoMedidasRecomendadas' AND codigo = 'iluminacao') as ccl " +
            "ON recm.idMedidaRecomendada = ccl.id  " +
            "WHERE idRelatorio = (SELECT id FROM relatorioAmbientalResultado WHERE idAtividade = :idAtividade AND tipo = :tipo) LIMIT 1 ")
    abstract public Observable<String> obterMedidaRecomendadaIluminacao(int idAtividade, int tipo);



    @Query("SELECT medida " +
            "FROM ( " +
            "SELECT CASE WHEN SUM(VALIDO) > 0 AND COUNT(VALIDO) > 0 THEN 3 ELSE 4 END as  idMedidaRecomendada, idRelatorio " +
            "FROM ( " +
            "SELECT CASE WHEN  (CAST(temperatura AS INTEGER) < 18 OR CAST(temperatura AS INTEGER) > 22) OR CAST(humidadeRelativa AS INTEGER) < 50 OR CAST(humidadeRelativa AS INTEGER) > 70 THEN 0  " +
            "ELSE 1 END as VALIDO, idRelatorio " +
            "FROM avaliacoesAmbientaisResultado " +
            ") as medidas " +
            "GROUP BY idRelatorio " +
            ") as recm " +
            "LEFT JOIN (SELECT id, descricao as medida FROM tipos WHERE tipo = 'ConclusaoMedidasRecomendadas' AND codigo = 'termico') as ccl " +
            "ON recm.idMedidaRecomendada = ccl.id " +
            "WHERE idRelatorio = (SELECT id FROM relatorioAmbientalResultado WHERE idAtividade = :idAtividade AND tipo = :tipo) LIMIT 1	")
    abstract public Observable<String> obterMedidaRecomendadaTermico(int idAtividade, int tipo);








}
