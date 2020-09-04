package com.vvm.sh.baseDados.entidades;

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


//    @NonNull
//    @ColumnInfo(name = "api")
//    public int api;


    @Ignore
    public String palavraChave;

    @Ignore
    public Utilizador() {}


    public Utilizador(String id, String area, String nome, String email/*, int api*/) {
        this.id = id;
        this.area = area;
        this.nome = nome;
        this.email = email;
        //this.api = api;
    }

    @Ignore
    public Utilizador(String id, String palavraChave, String area, String nome, String email) {
        this.id = id;
        this.area = area;
        this.nome = nome;
        this.email = email;
        this.palavraChave = palavraChave;
    }



    @Override
    public String toString() {
        return id + " - " + nome;
    }
}
