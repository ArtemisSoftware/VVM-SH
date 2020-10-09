package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.vvm.sh.baseDados.entidades.AvaliacaoAmbientalResultado;
import com.vvm.sh.baseDados.entidades.RelatorioAmbientalResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.modelos.AvaliacaoAmbiental;
import com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.modelos.RelatorioAmbiental;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
abstract public class AvaliacaoAmbientalDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract public Single<Long> inserir(RelatorioAmbientalResultado registo);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    abstract public Single<Integer> atualizar(RelatorioAmbientalResultado registo);


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract public Single<Long> inserir(AvaliacaoAmbientalResultado registo);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    abstract public Single<Integer> atualizar(AvaliacaoAmbientalResultado registo);



    @Delete
    abstract public Single<Integer> remover(AvaliacaoAmbientalResultado registo);




    @Query("SELECT * FROM relatorioAmbientalResultado WHERE idAtividade = :idAtividade AND tipo = :origem")
    abstract public Maybe<RelatorioAmbientalResultado> obterGeral(int idAtividade, int origem);



    @Query("SELECT t2.id as id, " +
            "CASE WHEN t1.id = t2.id THEN t1.descricao " +
            "ELSE t1.descricao || '-' || t2.descricao END as descricao, " +
            "t1.codigo as codigo, t1.idPai as idPai, " +
            "t1.ativo as ativo, t1.detalhe as detalhe, t1.tipo as tipo, t1.api as api   " +
            "FROM tipos as t1 " +
            "LEFT JOIN tipos as t2 ON ( CAST(t2.idPai AS INTEGER) = t1.id OR (t2.id = t1.id AND t1.idPai ='' )) AND t2.codigo = '' " +
            "WHERE t1.tipo = '"+ TiposUtil.MetodosTipos.ILUMINANCIA + "'  AND t2.tipo = '"+ TiposUtil.MetodosTipos.ILUMINANCIA +"'  AND t1.codigo = '' ")
    abstract public Single<List<Tipo>> obterElxArea();


    @Query("SELECT * " +
            "FROM tipos " +
            "WHERE tipo = '" + TiposUtil.MetodosTipos.ILUMINANCIA + "' AND idPai = :id AND ativo = 1 AND codigo <> '' AND api = :api")
    abstract public Single<List<Tipo>> obterElx(int id, int api);



    @Query("SELECT * FROM avaliacoesAmbientaisResultado WHERE id = :id")
    abstract public Maybe<AvaliacaoAmbientalResultado> obterAvaliacao(int id);










    @Query("SELECT *," +

            "CASE WHEN  (CAST(emedioLx AS INTEGER) <  (CAST(eLx AS INTEGER) - ((10 * CAST(eLx AS INTEGER)) / 100))) = 0 THEN 1 " +
            //--"WHEN  CAST(emedioLx AS INTEGER) <  (CAST(eLx AS INTEGER) - ((10 * CAST(eLx AS INTEGER)) / 100)) AND  IFNULL(numeroMedidas, 0) > 0 THEN 1 " +
            "ELSE 0 END as valido, " +

            "CASE WHEN  CAST(emedioLx AS INTEGER) <  (CAST(eLx AS INTEGER) - ((10 * CAST(eLx AS INTEGER)) / 100)) THEN 1  " +
            "ELSE 0 END as necessitaMedidas, " +
            "area, local_geral || ' - ' || local_especifico as local, descricaoTipoIluminacao " +

            "FROM avaliacoesAmbientaisResultado as av_am_res " +
            "LEFT JOIN (SELECT id, descricao as area FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.TIPOS_AREA + "') as tp_area ON av_am_res.idArea = tp_area.id " +
            "LEFT JOIN (SELECT id, descricao as local_geral FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.ILUMINANCIA + "') as tp_local ON  av_am_res.eLxArea = tp_local.id " +
            "LEFT JOIN (SELECT id, codigo, descricao as local_especifico FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.ILUMINANCIA + "') as tp_local_especifico ON  av_am_res.idElx = tp_local_especifico.id AND av_am_res.eLx = tp_local_especifico.codigo " +
            "LEFT JOIN (SELECT id, descricao as descricaoTipoIluminacao FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.TIPOS_ILUMINACAO + "') as tp_iluminacao ON  av_am_res.tipoIluminacao = tp_iluminacao.id " +
            "WHERE idRelatorio = :idRelatorio")
    abstract public Observable<List<AvaliacaoAmbiental>> obterAvaliacoesIluminacao(int idRelatorio);





    @Query("SELECT *, " +
            "CASE  " +
            "WHEN  (CAST(temperatura AS INTEGER) < 18 OR CAST(temperatura AS INTEGER) > 22) AND  IFNULL(numeroMedidas, 0) = 0  THEN 0 " +
            "WHEN  (CAST(temperatura AS INTEGER) < 18 OR CAST(temperatura AS INTEGER) > 22) AND  IFNULL(numeroMedidas, 0) > 0  THEN 1 " +
            "WHEN  (CAST(humidadeRelativa AS INTEGER) < 50 OR CAST(humidadeRelativa AS INTEGER) > 70)  AND  IFNULL(numeroMedidas, 0) = 0 THEN 0 " +
            "WHEN  (CAST(humidadeRelativa AS INTEGER) < 50 OR CAST(humidadeRelativa AS INTEGER) > 70)  AND  IFNULL(numeroMedidas, 0) > 0 THEN 1 " +
            "ELSE 1 END as valido, " +

            "CASE WHEN  (CAST(temperatura AS INTEGER) < 18 OR CAST(temperatura AS INTEGER) > 22)  THEN 1 " +
            "WHEN  (CAST(humidadeRelativa AS INTEGER) < 50 OR CAST(humidadeRelativa AS INTEGER) > 70)  THEN 1 " +
            "ELSE 0 END as necessitaMedidas, " +
            "area " +

            "FROM avaliacoesAmbientaisResultado as av_amb_res " +
            "LEFT JOIN (SELECT id, descricao as area FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.TIPOS_AREA + "') as tp_area ON av_amb_res.idArea = tp_area.id " +

            "LEFT JOIN (SELECT id, COUNT(idMedida) as numeroMedidas FROM medidasResultado WHERE origem = " + Identificadores.Origens.ORIGEM_RELATORIO_TEMPERATURA_HUMIDADE_MEDIDAS_RECOMENDADAS + " GROUP BY id) as ct_medidas " +
            "ON av_amb_res.id = ct_medidas.id	" +

            "WHERE idRelatorio = :idRelatorio")
    abstract public Observable<List<AvaliacaoAmbiental>> obterAvaliacoesTemperaturaHumidade(int idRelatorio);








    //--------------
    //Validacao
    //--------------

    @Query("SELECT rel_amb_res.id as idRelatorio, " +
            "CASE WHEN marca IS NULL OR numeroSerie IS NULL OR data IS NULL THEN 0 ELSE 1 " +
            "END as geralValido, " +
            "numeroAvaliacoes, avaliacoesValido, medida " +

            "FROM relatorioAmbientalResultado as rel_amb_res " +

            "LEFT JOIN( " +

            "SELECT idRelatorio, " +
            "CASE WHEN COUNT(VALIDO) = SUM(VALIDO) AND COUNT(VALIDO) > 0 THEN 1 ELSE 0 END as avaliacoesValido," +
            "CASE WHEN SUM(validade_medida_recomendada) > 0 AND COUNT(validade_medida_recomendada) > 0 THEN 2 ELSE 1 END as idMedidaRecomendada "  +

            "FROM(   " +
            "SELECT idRelatorio,   " +
            "CASE WHEN CAST(emedioLx AS INTEGER) <  (CAST(eLx AS INTEGER) - ((10 * CAST(eLx AS INTEGER)) / 100)) AND IFNULL(numeroMedidas, 0) = 0 THEN 0  " +
            "ELSE 1 END as valido,  " +
            "CASE WHEN  CAST(emedioLx AS INTEGER) <  (CAST(eLx AS INTEGER) - ((10 * CAST(eLx AS INTEGER)) / 100)) THEN 1 ELSE 0 END as validade_medida_recomendada " +
            "FROM avaliacoesAmbientaisResultado as av_amb_res  " +
            "LEFT JOIN (SELECT id, COUNT(idMedida) as numeroMedidas FROM medidasResultado WHERE origem = " + Identificadores.Origens.ORIGEM_RELATORIO_ILUMINACAO_MEDIDAS_RECOMENDADAS + " GROUP BY id) as ct_medidas " +
            "ON av_amb_res.id = ct_medidas.id  " +
            ") as validacao " +
            "GROUP BY idRelatorio" +

            ") as vald_iluminacao ON rel_amb_res.id = vald_iluminacao.idRelatorio " +

            "LEFT JOIN (SELECT idRelatorio, COUNT(id) as numeroAvaliacoes FROM avaliacoesAmbientaisResultado WHERE tipoIluminacao > 0 GROUP BY idRelatorio) as ct_avaliacoes " +
            "ON rel_amb_res.id = ct_avaliacoes.idRelatorio " +

            "LEFT JOIN (SELECT id, descricao as medida FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.CONCLUSAO_MEDIDAS_RECOMENDADAS + "' AND codigo = 'iluminacao') as ccl " +
            "ON vald_iluminacao.idMedidaRecomendada = ccl.id " +

            "WHERE  idAtividade = :idAtividade AND tipo = :tipo")
    abstract public Observable<RelatorioAmbiental> obterRelatorioIlumiacao(int idAtividade, int tipo);



    @Query("SELECT rel_amb_res.id as idRelatorio, " +
            "CASE WHEN marca IS NULL OR numeroSerie IS NULL OR data IS NULL THEN 0 ELSE 1 " +
            "END as geralValido, " +
            "numeroAvaliacoes, avaliacoesValido, medida " +

            "FROM relatorioAmbientalResultado as rel_amb_res " +

            "LEFT JOIN( " +
            "" +
            "SELECT idRelatorio, " +
            "CASE WHEN COUNT(VALIDO) = SUM(VALIDO) AND COUNT(VALIDO) > 0 THEN 1 ELSE 0 END as avaliacoesValido, " +
            "CASE WHEN SUM(validade_medida_recomendada) > 0 AND COUNT(validade_medida_recomendada) > 0 THEN 4 ELSE 3 END as idMedidaRecomendada "  +
            "FROM (" +
            "SELECT  idRelatorio,	" +
            "CASE WHEN  (CAST(temperatura AS INTEGER) < 18 OR CAST(temperatura AS INTEGER) > 22) AND  IFNULL(numeroMedidas, 0)  = 0 THEN 0	" +
            "WHEN  (CAST(humidadeRelativa AS INTEGER) < 50 OR CAST(humidadeRelativa AS INTEGER) > 70) AND  IFNULL(numeroMedidas, 0)  = 0 THEN 0 " +
            "ELSE 1 END as valido,  " +

            "CASE WHEN  (CAST(temperatura AS INTEGER) < 18 OR CAST(temperatura AS INTEGER) > 22) AND  IFNULL(numeroMedidas, 0)  = 0 THEN 0	" +
            "WHEN  (CAST(humidadeRelativa AS INTEGER) < 50 OR CAST(humidadeRelativa AS INTEGER) > 70) AND  IFNULL(numeroMedidas, 0)  = 0 THEN 0 " +
            "ELSE 1 END as validade_medida_recomendada  " +

            "FROM avaliacoesAmbientaisResultado as av_amb_res	" +

            "LEFT JOIN (SELECT id, COUNT(idMedida) as numeroMedidas FROM medidasResultado WHERE origem = " + Identificadores.Origens.ORIGEM_RELATORIO_TEMPERATURA_HUMIDADE_MEDIDAS_RECOMENDADAS + " GROUP BY id) as ct_medidas " +
            "ON av_amb_res.id = ct_medidas.id	" +
            ") as validacao " +
            "GROUP BY idRelatorio" +

            ") as vald_iluminacao ON rel_amb_res.id = vald_iluminacao.idRelatorio " +


            "LEFT JOIN (SELECT idRelatorio, COUNT(id) as numeroAvaliacoes FROM avaliacoesAmbientaisResultado WHERE temperatura > 0 GROUP BY idRelatorio) as ct_avaliacoes " +
            "ON rel_amb_res.id = ct_avaliacoes.idRelatorio " +

            "LEFT JOIN (SELECT id, descricao as medida FROM tipos WHERE tipo = '" + TiposUtil.MetodosTipos.CONCLUSAO_MEDIDAS_RECOMENDADAS + "' AND codigo = '" + Identificadores.Codigos.TERMICO + "') as ccl " +
            "ON vald_iluminacao.idMedidaRecomendada = ccl.id " +

            "WHERE  idAtividade = :idAtividade AND tipo = :tipo")
    abstract public Observable<RelatorioAmbiental> obterRelatorioTermico(int idAtividade, int tipo);


















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
