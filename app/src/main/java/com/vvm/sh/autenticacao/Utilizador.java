package com.vvm.sh.autenticacao;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "utilizadores")
public class Utilizador {


    @PrimaryKey
    @NonNull
    private String id;

    @ColumnInfo(name = "area")
    private String area;

    @ColumnInfo(name = "nome")
    private String nome;

    @ColumnInfo(name = "email")
    private String email;


    @Ignore
    public Utilizador() {}


    public Utilizador(String id, String area, String nome, String email) {
        this.id = id;
        this.area = area;
        this.nome = nome;
        this.email = email;
    }



    /**
     * Metodo que permite obter as inicias do utilizador
     * @return as iniciais
     */
    public  String obterIniciais(){
        return nome.replaceAll("^\\s*([a-zA-Z]).*\\s+([a-zA-Z])\\S+$", "$1$2").toUpperCase();
    }
}
