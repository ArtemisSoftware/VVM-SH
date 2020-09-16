package com.vvm.sh.baseDados.entidades;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "riscosResultado",
        indices = {@Index(value="idLevantamento", unique = false) },

        foreignKeys = @ForeignKey(entity = LevantamentoRiscoResultado.class,
                parentColumns = "id",
                childColumns = "idLevantamento",
                onDelete = CASCADE))
public class RiscoResultado {

    @NonNull
    @ColumnInfo(name = "idLevantamento")
    public int idLevantamento;

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public int idRisco;


    @NonNull
    public int idRiscoEspecifico;

    @NonNull
    public String consequencias;


    @NonNull
    public int nd;

    @NonNull
    public int ne;

    @NonNull
    public int nc;

//TODO: completar
    public int idTipoRisco;


    @NonNull
    public int origem;


    //CAMPO_ORIGEM + " INTEGER DEFAULT " + IdentificadoresIF.ORIGEM_DADOS_BD + ", " +
    //CAMPO_ID_TIPO_RISCO + " TEXT, " +

}
