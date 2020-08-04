package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.vvm.sh.ui.anomalias.modelos.Anomalia;
import com.vvm.sh.baseDados.entidades.Ocorrencia;
import com.vvm.sh.baseDados.entidades.OcorrenciaHistorico;

import java.util.List;

@Dao
abstract public class DownloadTrabalhoDao {

    @Insert
    abstract public List<Long> inserirAnomalias(List<Anomalia> registos);


    @Insert
    abstract public Long inserirRegisto(Ocorrencia registo);

    @Insert
    abstract public List<Long> inserir(List<OcorrenciaHistorico> registos);

}
