package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.vvm.sh.util.constantes.Identificadores;

@Entity(tableName = "tiposNovos")
public class TipoNovo {


    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;


    @NonNull
    @ColumnInfo(name = "idProvisorio", defaultValue = Identificadores.VALOR_0 + "")
    public int idProvisorio;


    @NonNull
    @ColumnInfo(name = "idDefinitivo", defaultValue = Identificadores.VALOR_0 + "")
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
    @ColumnInfo(name = "estado", defaultValue = Identificadores.Estados.Equipamentos.ESTADO_PENDENTE + "")
    public int estado;

    public TipoNovo(@NonNull String tipo, @NonNull String descricao) {
        this.idProvisorio = idProvisorio;
        this.tipo = tipo;
        this.descricao = descricao;
        this.ativo = 1;
        this.estado = Identificadores.Estados.Equipamentos.ESTADO_PENDENTE ;
    }
}
