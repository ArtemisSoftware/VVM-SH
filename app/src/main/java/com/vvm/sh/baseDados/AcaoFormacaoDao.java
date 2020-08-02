package com.vvm.sh.baseDados;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.ui.atividadesPendentes.relatorios.AcaoFormacao;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
abstract public class AcaoFormacaoDao implements BaseDao<AcaoFormacao>{


    @Query("SELECT * FROM acoesFormacaoResultado WHERE idAtividade = :idAtividade")
    abstract public Maybe<AcaoFormacao> obterAcaoFormacao(int idAtividade);

}
