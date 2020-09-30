package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "tiposTemplatesAVRMedidasRisco",
        primaryKeys = {"id", "origem", "idMedida"},
        foreignKeys = @ForeignKey(entity = TipoTemplateAvrRisco.class,
                parentColumns = "id",
                childColumns = "id",
                onDelete = CASCADE))
public class TipoTemplatesAVRMedidaRisco {

    @NonNull
    public int id;


    @NonNull
    public int origem;

    @NonNull
    public int idMedida;


    public TipoTemplatesAVRMedidaRisco(int id, int origem, int idMedida) {
        this.id = id;
        this.origem = origem;
        this.idMedida = idMedida;
    }
}
