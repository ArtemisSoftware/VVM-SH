package com.vvm.sh.baseDados;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.vvm.sh.ui.agenda.modelos.Tarefa;
import com.vvm.sh.ui.autenticacao.modelos.Utilizador;
import com.vvm.sh.ui.cliente.Cliente;
import com.vvm.sh.ui.opcoes.modelos.Atualizacao;
import com.vvm.sh.ui.opcoes.modelos.Tipo;

@Database(
            entities = {
                    Atualizacao.class, Tipo.class, Utilizador.class,
                    Tarefa.class,
                    Cliente.class
            },
            version = BaseDadosContantes.VERSAO
)
@TypeConverters({Conversor.class})
public abstract class VvmshBaseDados extends RoomDatabase {

    public abstract AtualizacaoDao obterAtualizacaoDao();

    public abstract TipoDao obterTipoDao();


    public abstract UtilizadorDao obterUtilizadorDao();
}
