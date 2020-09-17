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

    @ColumnInfo(name = "cap")
    public String cap;

    @NonNull
    @ColumnInfo(name = "api")
    public int api;

    @ColumnInfo(name = "email")
    public String email;


    @Ignore
    public String palavraChave;

    @Ignore
    public Utilizador() {}



    public Utilizador(@NonNull String id, @NonNull String area, @NonNull String nome, String email, int api, String cap) {
        this.id = id;
        this.area = area;
        this.nome = nome;
        this.email = email;
        this.api = api;
        this.cap = cap;
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
