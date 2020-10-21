package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.documentos.Rubrica;
import com.vvm.sh.ui.registoVisita.modelos.DadosCliente;
import com.vvm.sh.ui.registoVisita.modelos.TrabalhoRealizado;
import com.vvm.sh.util.metodos.DatasUtil;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.List;

import io.reactivex.Maybe;

@Dao
abstract public class PdfDao {


    @Transaction
    @Query("SELECT * FROM tarefas WHERE idTarefa =:idTarefa")
    abstract public Maybe<DadosCliente> obterDadosCliente(int idTarefa);



    @Query("SELECT *, tb_rel_res.id as idRegisto, informacao " +
            "FROM tipos as tp " +
            "LEFT JOIN (SELECT id, informacao FROM trabalhoRealizadoResultado WHERE idTarefa = :idTarefa) as tb_rel_res " +
            "ON tp.id = tb_rel_res.id " +
            "WHERE  ativo = 1 AND api =:api AND tipo ='" + TiposUtil.MetodosTipos.ATIVIDADES_RELATORIO_VISITA + "' ")
    abstract public Maybe<List<TrabalhoRealizado>> obterTrabalhosRealizadosRegistados(int idTarefa, int api);



    @Query("SELECT cap, nome, imagem " +
            "FROM imagensResultado as img " +
            "LEFT JOIN (SELECT nome, cap FROM utilizadores WHERE id = :idUtilizador)" +
            "WHERE id = :id AND origem = :origem")
    abstract public Maybe<Rubrica> obterRubrica(int id, int origem, int idUtilizador);
}
