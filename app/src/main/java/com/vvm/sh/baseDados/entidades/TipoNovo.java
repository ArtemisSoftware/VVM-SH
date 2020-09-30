package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.vvm.sh.util.constantes.Identificadores;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "tiposNovos")
public class TipoNovo {


    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "idDefinitivo")
    public int idDefinitivo;



    @NonNull
    public String tipo;


    @NonNull
    @ColumnInfo(name = "descricao")
    public String descricao;

    @ColumnInfo(name = "codigo")
    public String codigo;

    @ColumnInfo(name = "idPai")
    public String idPai;

    @NonNull
    @ColumnInfo(name = "ativo", defaultValue = Identificadores.VALOR_1 + "")
    public int ativo;

    @ColumnInfo(name = "detalhe")
    public String detalhe;

    @NonNull
    @ColumnInfo(name = "estado", defaultValue = Identificadores.ESTADO_PENDENTE + "")
    public int estado;
}
