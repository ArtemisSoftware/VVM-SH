package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.vvm.sh.baseDados.entidades.TipoTemplateAvrLevantamento;

import java.util.List;


@Dao
abstract public class TipoDadosDao {



    //----------------------------
    //Templates
    //-----------------------------

    @Insert
    abstract public List<Long> inserirTemplateAvrLevantamento(List<TipoTemplateAvrLevantamento> tipo);

    @Update
    abstract public Integer atualizarTemplateAvrLevantamento(List<TipoTemplateAvrLevantamento> tipo);

    @Query("SELECT ativo as valido FROM tipos WHERE id  =:id AND tipo =:tipoMedida ")
    abstract public boolean filtrarMedidasTemplate(int id, String tipoMedida);

}
