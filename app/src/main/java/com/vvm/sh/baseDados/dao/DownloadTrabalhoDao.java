package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.vvm.sh.ui.anomalias.modelos.Anomalia;

import java.util.List;

@Dao
abstract public class DownloadTrabalhoDao {

    @Insert
    abstract public List<Long> inserir(List<Anomalia> registos);

}
