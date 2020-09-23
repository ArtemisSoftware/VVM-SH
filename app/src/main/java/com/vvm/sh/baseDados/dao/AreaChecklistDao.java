package com.vvm.sh.baseDados.dao;


import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.AreaChecklist;
import com.vvm.sh.baseDados.entidades.AreaChecklistResultado;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
abstract public class AreaChecklistDao implements BaseDao<AreaChecklistResultado> {

//
//    @Query("SELECT * FROM areasChecklistResultado WHERE idTarefa = :idTarefa")
//    abstract public Observable<List<Anomalia>> obterAreas(int idTarefa);



    @Query("SELECT * FROM areasChecklist WHERE idChecklist = :idChecklist AND idArea NOT IN(" + Identificadores.ID_AREA_GERAL + ")")
    abstract public Single<List<AreaChecklist>> obterAreas(int idChecklist);



    @Query("DELETE FROM areasChecklistResultado WHERE id = :id")
    abstract public Single<Integer> remover(int id);

}
