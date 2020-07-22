package com.vvm.sh.baseDados;

import androidx.room.Dao;
import androidx.room.Insert;

import com.vvm.sh.ui.anomalias.modelos.Anomalia;

import java.util.List;

@Dao
abstract public class AnomaliaDao implements BaseDao<Anomalia>{

    @Insert
    abstract public List<Long> inserir(List<Anomalia> registos);
}
