package com.vvm.sh.baseDados;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.ui.opcoes.modelos.Atualizacao;

import io.reactivex.Completable;

@Dao
abstract public class TipoDao implements BaseDao<Atualizacao>{

}
