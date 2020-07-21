package com.vvm.sh.baseDados;

import androidx.room.Dao;
import androidx.room.Insert;

import com.vvm.sh.ui.atividadesExecutadas.AtividadeExecutada;

import java.util.List;

@Dao
abstract public class AtividadeExecutadaDao implements BaseDao<AtividadeExecutada>{

    @Insert
    abstract public List<Long> inserir(List<AtividadeExecutada> registos);
}
