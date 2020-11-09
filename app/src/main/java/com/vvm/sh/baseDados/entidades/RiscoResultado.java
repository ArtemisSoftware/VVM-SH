package com.vvm.sh.baseDados.entidades;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.vvm.sh.util.constantes.Identificadores;

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
    public String nd;

    @NonNull
    public String ne;

    @NonNull
    public String nc;

    @NonNull
    public String ni;

    @NonNull
    public int idTipoRisco;


    @NonNull
    public int origem;


    @Ignore
    public RiscoResultado(int idLevantamento, int idRisco, int idRiscoEspecifico, String consequencias, String nd, String ne, String nc, String ni) {
        this.idLevantamento = idLevantamento;

        this.idRisco = idRisco;
        this.idRiscoEspecifico = idRiscoEspecifico;
        this.consequencias = consequencias;
        this.idTipoRisco = -1;
        this.nd = nd;
        this.ne = ne;
        this.nc = nc;

        this.ni = ni;
        this.origem = Identificadores.Origens.ORIGEM_BD;
    }

    public RiscoResultado(int idLevantamento, int id, int idRisco, int idRiscoEspecifico, @NonNull String consequencias, String nd, String ne, String nc, String ni, int idTipoRisco, int origem) {
        this.idLevantamento = idLevantamento;
        this.id = id;
        this.idRisco = idRisco;
        this.idRiscoEspecifico = idRiscoEspecifico;
        this.consequencias = consequencias;
        this.nd = nd;
        this.ne = ne;
        this.nc = nc;
        this.ni = ni;
        this.idTipoRisco = idTipoRisco;
        this.origem = origem;
    }

    @Ignore
    public RiscoResultado() {
    }
}
