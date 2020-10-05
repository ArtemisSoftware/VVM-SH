package com.vvm.sh.baseDados.entidades;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "propostaPlanoAcaoResultado",
        indices = {@Index(value="idAtividade", unique = false) },

        foreignKeys = @ForeignKey(entity = AtividadePendente.class,
                parentColumns = "id",
                childColumns = "idAtividade",
                onDelete = CASCADE))
public class PropostaPlanoAcaoResultado {


    @NonNull
    @ColumnInfo(name = "idAtividade")
    public int idAtividade;

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;


    @NonNull
    @ColumnInfo(name = "origem", defaultValue = Identificadores.VALOR_0 + "")
    public int origem;


    @ColumnInfo(name = "idQuestaoChecklis", defaultValue = Identificadores.VALOR_0 + "")
    public int idQuestaoChecklis;

    @ColumnInfo(name = "idMedida", defaultValue = Identificadores.VALOR_0 + "")
    public int idMedida;

    @ColumnInfo(name = "idNi", defaultValue = Identificadores.VALOR_0 + "")
    public int idNi;

    @ColumnInfo(name = "idPrazo", defaultValue = Identificadores.VALOR_0 + "")
    public int idPrazo;


    @NonNull
    @ColumnInfo(name = "selecionado", defaultValue = Sintaxe.Codigos.NAO_SELECIONADO)
    public boolean selecionado;

}
