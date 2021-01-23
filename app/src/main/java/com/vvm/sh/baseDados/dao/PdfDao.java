package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.documentos.Rubrica;
import com.vvm.sh.ui.informacaoSst.modelos.ObrigacaoLegal;
import com.vvm.sh.documentos.DadosCliente;
import com.vvm.sh.ui.registoVisita.modelos.TrabalhoRealizado;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.List;

import io.reactivex.Maybe;

@Dao
abstract public class PdfDao {


    //------------------------
    //Registo visita
    //------------------------




    @Query("SELECT *, tb_rel_res.id as idRegisto, informacao " +
            "FROM tipos as tp " +
            "LEFT JOIN (SELECT id, informacao FROM trabalhoRealizadoResultado WHERE idTarefa = :idTarefa) as tb_rel_res " +
            "ON tp.id = tb_rel_res.id " +
            "WHERE  ativo = 1 AND api =:api AND tipo ='" + TiposUtil.MetodosTipos.ATIVIDADES_RELATORIO_VISITA + "' ")
    abstract public Maybe<List<TrabalhoRealizado>> obterTrabalhosRealizadosRegistados(int idTarefa, int api);






    //------------------------
    //Informacao sst
    //------------------------



    @Query("SELECT *, obrigacao_legal_res.id as idRegisto " +
            "FROM tipos as tp " +
            "LEFT JOIN (SELECT id FROM obrigacaoLegalResultado WHERE idTarefa = :idTarefa) as obrigacao_legal_res " +
            "ON tp.id = obrigacao_legal_res.id " +
            "WHERE  ativo = 1 AND api =:api AND tipo ='" + TiposUtil.MetodosTipos.OBRIGACOES_LEGAIS + "' ")
    abstract public Maybe<List<ObrigacaoLegal>> obterObrigacoesLegais(int idTarefa, int api);

    //------------------------
    //Misc
    //------------------------

    @Transaction
    @Query("SELECT * FROM tarefas WHERE idTarefa =:idTarefa")
    abstract public Maybe<DadosCliente> obterDadosCliente(int idTarefa);


    @Query("SELECT cap, nome, imagem " +
            "FROM imagensResultado as img " +
            "LEFT JOIN (SELECT nome, cap FROM utilizadores WHERE id = :idUtilizador)" +
            "WHERE id = :id AND origem = :origem")
    abstract public Maybe<Rubrica> obterRubrica(int id, int origem, String idUtilizador);


    @Query("SELECT detalhe FROM tipos WHERE id =:id AND tipo = '" + TiposUtil.MetodosTipos.FRASES_APOIO + "' AND ativo = 1 AND api = :api")
    abstract public Maybe<String> obterFraseApoio(int id, int api);





}
