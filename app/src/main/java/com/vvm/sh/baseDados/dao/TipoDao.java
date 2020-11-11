package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.AreaChecklist;
import com.vvm.sh.baseDados.entidades.CheckList;
import com.vvm.sh.baseDados.entidades.ItemChecklist;
import com.vvm.sh.baseDados.entidades.SeccaoChecklist;
import com.vvm.sh.baseDados.entidades.TipoAtividadePlaneavel;
import com.vvm.sh.baseDados.entidades.TipoNovo;
import com.vvm.sh.baseDados.entidades.TipoTemplateAvrLevantamento;
import com.vvm.sh.baseDados.entidades.TipoTemplateAvrRisco;
import com.vvm.sh.baseDados.entidades.TipoTemplatesAVRMedidaRisco;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.ui.opcoes.modelos.ResumoChecklist;
import com.vvm.sh.ui.opcoes.modelos.ResumoTipo;
import com.vvm.sh.ui.pesquisa.modelos.Medida;
import com.vvm.sh.util.constantes.AppConfig;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
abstract public class TipoDao implements BaseDao<Tipo> {

    //-------------------
    //Tipos
    //--------------------


    @Insert
    abstract public List<Long> inserir(List<Tipo> tipo);

    @Update
    abstract public Integer atualizar(List<Tipo> tipo);

    @Query("DELETE FROM tipos WHERE tipo = :tipo ")
    abstract public void removerTipo(String tipo);




    @Insert
    abstract public List<Long> inserirAtividadesPlaneaiveis(List<TipoAtividadePlaneavel> registo);

    @Update
    abstract public Integer atualizarAtividadesPlaneaiveis(List<TipoAtividadePlaneavel> tipo);






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








    @Query("SELECT CASE WHEN ativo = 1 THEN 1 ELSE 0 END valido " +
            "FROM tipos WHERE id  =:id AND tipo ='" + TiposUtil.MetodosTipos.MEDIDAS_PREVENCAO_RECOMENDADAS +"' ")
    abstract public boolean filtrarMedidaRecomendadasTemplate(int id);

    @Query("SELECT CASE WHEN ativo = 1 THEN 1 ELSE 0 END valido " +
            "FROM tipos WHERE id  =:id AND tipo ='" + TiposUtil.MetodosTipos.MEDIDAS_PREVENCAO_ADOPTADAS +"' ")
    abstract public boolean filtrarMedidaExistentesTemplate(int id);


    @Query("DELETE FROM tiposTemplateAvrLevantamentos ")
    abstract public void removerTemplatesAVRLevantamentos();

    @Query("DELETE FROM tiposTemplateAvrRiscos ")
    abstract public void removerTemplatesAVRRisco();

    @Query("DELETE FROM tiposTemplatesAVRMedidasRisco ")
    abstract public void removerTemplatesAVRMedidaRisco();

    @Query("DELETE FROM tiposTemplatesAVRMedidasRisco WHERE id =:id AND origem =:origem")
    abstract public void removerTemplatesAVRMedidaRisco(int id, int origem);




    @Query("DELETE FROM tiposAtividadesPlaneaveis ")
    abstract public void removerAtividadesPlaneaveis();



    @Query("SELECT *, numeroRegistosSA, numeroRegistosSHT " +
            "FROM atualizacoes as atl " +
            "LEFT JOIN (SELECT tipo, COUNT(id) as numeroRegistosSA FROM tipos WHERE ativo = 1 AND api = " + Identificadores.App.APP_SA + " GROUP BY tipo) as tp_sa ON atl.descricao = tp_sa.tipo " +
            "LEFT JOIN (SELECT tipo, COUNT(id) as numeroRegistosSHT FROM tipos WHERE ativo = 1 AND api = " + Identificadores.App.APP_ST + " GROUP BY tipo) as tp_sht ON atl.descricao = tp_sht.tipo " +
            "WHERE atl.tipo = " + Identificadores.Atualizacoes.TIPO + " " +
            "ORDER BY descricao ASC")
    abstract public Observable<List<ResumoTipo>> obterResumoTipos();


    @Query("SELECT *, ct_areas as numeroAreas, ct_seccoes as numeroSeccoes, ct_itens as numeroItens " +
            "FROM checklist as chk " +

            "LEFT JOIN (SELECT idChecklist, IFNULL(COUNT(descricao), 0) as ct_areas FROM areasChecklist GROUP BY idChecklist) as area_chk " +
            "ON chk.id = area_chk.idChecklist " +

            "LEFT JOIN (SELECT idChecklist, IFNULL(COUNT(uid), 0) as ct_seccoes FROM seccoesChecklist GROUP BY idChecklist, idArea) as seccoes_chk " +
            "ON chk.id = seccoes_chk.idChecklist " +

            "LEFT JOIN (SELECT idChecklist, IFNULL(COUNT(uid), 0) as ct_itens FROM itensChecklist GROUP BY idChecklist, idArea, idSeccao) as itens_chk " +
            "ON chk.id = itens_chk.idChecklist " +

            "ORDER BY descricao ASC")
    abstract public Observable<List<ResumoChecklist>> obterResumoChecklist();



    @Query("SELECT *, 0 as numeroRegistosSA, numeroRegistosSHT " +
            "FROM atualizacoes as atl " +
            "LEFT JOIN (SELECT " + Identificadores.Atualizacoes.ATIVIDADES_PLANEAVEIS + " as tipo, COUNT(id) as numeroRegistosSHT FROM tiposAtividadesPlaneaveis WHERE ativo = 1) as tp_sht " +
            "ON atl.tipo = tp_sht.tipo " +
            "WHERE atl.tipo = " + Identificadores.Atualizacoes.ATIVIDADES_PLANEAVEIS + " " +
            "ORDER BY descricao ASC")
    abstract public Observable<List<ResumoTipo>> obterResumoAtividadesPlaneaveis();


    @Query("SELECT *, 0 as numeroRegistosSA, numeroRegistosSHT, seloTemporal " +
            "FROM atualizacoes as atl " +

            "LEFT JOIN (" +
            "SELECT '" + TiposUtil.MetodosTipos.TemplateAvr.TEMPLATE_AVALIACAO_RISCOS_LEVANTAMENTOS + "' as tipo, COUNT(id) as numeroRegistosSHT FROM tiposTemplateAvrLevantamentos WHERE ativo = 1 " +
            "UNION " +
            "SELECT '" + TiposUtil.MetodosTipos.TemplateAvr.TEMPLATE_AVALIACAO_RISCOS_RISCOS + "' as tipo, COUNT(id) as numeroRegistosSHT FROM tiposTemplateAvrRiscos WHERE ativo = 1 " +
            ") as tp ON atl.descricao = tp.tipo " +
            "WHERE atl.tipo = " + Identificadores.Atualizacoes.TEMPLATE + " " +
            "ORDER BY descricao ASC")
    abstract public Observable<List<ResumoTipo>> obterResumoTemplates();





    @Transaction
    @Query("SELECT * FROM tiposNovos WHERE estado = " + Identificadores.ESTADO_PENDENTE + " AND tipo = '" +  TiposUtil.MetodosTipos.TIPOS_MAQUINA + "' ")
    abstract public Maybe<List<TipoNovo>> obterEquipamentosNaoValidados();












    @Query("SELECT * FROM tipos WHERE tipo = :tipo AND ativo = 1")
    abstract public Flowable<List<Tipo>> obterTipos(String tipo);

    @Query("SELECT * FROM tipos WHERE tipo = :tipo AND id = :id AND ativo = 1")
    abstract public Flowable<Tipo> obterTipo(String tipo, int id);



    @Query("SELECT * FROM tipos WHERE tipo = :tipo AND idPai = :idPai AND ativo = 1")
    abstract public Flowable<List<Tipo>> obterTipos(String tipo, String idPai);


    @Query("SELECT * FROM tipos WHERE tipo = :tipo AND api = :api AND ativo = 1")
    abstract public Flowable<List<Tipo>> obterTipos(String tipo, int api);








    @Query("SELECT * FROM tipos WHERE tipo = :tipo AND api = :api AND id NOT IN (:registos) AND descricao LIKE '%' || :pesquisa || '%' AND ativo = 1")
    abstract public Maybe<List<Tipo>> obterTipos_Excluir(String tipo, List<Integer> registos, String pesquisa, int api);




    @Query("SELECT * FROM tipos WHERE tipo = :metodo AND codigo = :codigo AND api = :api AND id IN (:registos) AND ativo = 1")
    abstract public Single<List<Tipo>> obterTipos_Incluir(String metodo, String codigo, int api, List<Integer> registos);

    @Query("SELECT * " +
            "FROM tipos " +
            "WHERE tipo = :tipo AND api = :api AND id IN (:registos) AND ativo = 1 ")
    abstract public Flowable<List<Tipo>> obterTipos_Incluir(String tipo, List<Integer> registos, int api);

    @Query("SELECT * " +
            "FROM tipos " +
            "WHERE tipo = :tipo AND api = :api AND id NOT IN (:registos) AND ativo = 1 ")
    abstract public Single<List<Tipo>> obterTipos_Excluir(String tipo, List<Integer> registos, int api);






    @Query("SELECT tp.id as id " +
            "FROM tipos as tp " +
            "LEFT JOIN (SELECT id FROM checklist) as chk ON tp.id = chk.id " +
            "WHERE tipo = '" + TiposUtil.MetodosTipos.TIPOS_CHECKLIST + "' AND api = :api  AND ativo = 1")
    abstract public Maybe<List<Integer>> obterChecklists(int api);

















    //-------------------------


    @Query("SELECT * FROM tipos WHERE tipo = :tipo AND api = :api AND ativo = 1")
    abstract public Single<List<Tipo>> obterTipos_(String tipo, int api);

    @Query("SELECT * FROM tipos WHERE tipo = :tipo AND api = :api AND idPai = :idPai AND ativo = 1")
    abstract public Single<List<Tipo>> obterTipos_(String tipo, int api, String idPai);

}
