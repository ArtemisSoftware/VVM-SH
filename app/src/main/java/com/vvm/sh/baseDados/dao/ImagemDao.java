package com.vvm.sh.baseDados.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.vvm.sh.baseDados.BaseDao;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.ui.imagens.modelos.ImagemRegisto;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
abstract public class ImagemDao implements BaseDao<ImagemResultado> {


    @Query("DELETE FROM imagensResultado WHERE id = :id AND origem = :origem")
    abstract public Single<Integer> remover(int id, int origem);


    @Query("SELECT * FROM imagensResultado WHERE id = :id AND origem = :origem")
    abstract public Maybe<ImagemResultado> obterImagem(int id, int origem);

    @Query("SELECT *, " +
            "CASE " +
            "WHEN origem = " + Identificadores.Imagens.IMAGEM_CHECKLIST + " THEN '" + Sintaxe.Palavras.CHECKLIST + "' " +
            "WHEN origem = " + Identificadores.Imagens.IMAGEM_RISCO + " THEN '" + Sintaxe.Palavras.RISCO + "' " +
            "ELSE '' END as descricao " +
            "FROM imagensResultado")
    abstract public Observable<List<ImagemRegisto>> obterImagem();

}
