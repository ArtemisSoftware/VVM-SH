package com.vvm.sh.ui.autenticacao.modelos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "utilizadores")
public class Utilizador {


    @PrimaryKey
    @NonNull
    public String id;

    @NonNull
    @ColumnInfo(name = "area")
    public String area;

    @NonNull
    @ColumnInfo(name = "nome")
    public String nome;

    @NonNull
    @ColumnInfo(name = "email")
    public String email;

    @Ignore
    public String palavraChave;

    @Ignore
    public Utilizador() {}


    public Utilizador(String id, String area, String nome, String email) {
        this.id = id;
        this.area = area;
        this.nome = nome;
        this.email = email;
    }

    @Ignore
    public Utilizador(String id, String palavraChave, String area, String nome, String email) {
        this.id = id;
        this.area = area;
        this.nome = nome;
        this.email = email;
        this.palavraChave = palavraChave;
    }


    /**
     * Metodo que permite obter as inicias do utilizador
     * @return as iniciais
     */
    public  String obterIniciais(){
        return nome.replaceAll("^\\s*([a-zA-Z]).*\\s+([a-zA-Z])\\S+$", "$1$2").toUpperCase();
    }
}
