package com.vvm.sh.baseDados;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.vvm.sh.ui.opcoes.modelos.Atualizacao;
import com.vvm.sh.ui.opcoes.modelos.Tipo;

import java.util.List;

import io.reactivex.Completable;

@Dao
abstract public class TipoDao implements BaseDao<Atualizacao>{


    @Insert
    abstract public List<Long> inserir(List<Tipo> tipo);

}
