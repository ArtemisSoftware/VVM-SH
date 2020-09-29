package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "tiposAtividadesPlaneaveis")
public class TipoAtividadePlaneavel {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "codigo")
    public String codigo;


    @NonNull
    public String servId;

    @NonNull
    public String descricao;

    @NonNull
    public String descricaoCompleta;


    @NonNull
    public String equipa;


    @NonNull
    @ColumnInfo(name = "sempreNecessario")
    public int sempreNecessario;


    @NonNull
    @ColumnInfo(name = "ordem")
    public int ordem;


    @NonNull
    @ColumnInfo(name = "relatorio")
    public int relatorio;

    public String observacao;


    @NonNull
    @ColumnInfo(name = "ativo")
    public int ativo;
}
