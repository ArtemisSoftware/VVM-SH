package com.vvm.sh.baseDados;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.vvm.sh.ui.opcoes.modelos.Atualizacao;
import com.vvm.sh.ui.opcoes.modelos.Tipo;

@Database(
            entities = {Atualizacao.class, Tipo.class},
            version = BaseDadosContantes.VERSAO
)
public abstract class VvmshBaseDados extends RoomDatabase {

    public abstract AtualizacaoDao obterAtualizacaoDao();

    public abstract TipoDao obterTipoDao();

}
