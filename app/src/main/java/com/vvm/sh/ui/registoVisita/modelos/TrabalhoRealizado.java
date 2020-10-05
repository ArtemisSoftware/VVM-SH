package com.vvm.sh.ui.registoVisita.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import com.vvm.sh.baseDados.entidades.Tipo;

public class TrabalhoRealizado {

    @Embedded
    public Tipo tipo;


    @ColumnInfo(name = "informacao")
    public String informacao;

    @ColumnInfo(name = "idRegisto")
    public int id;

    public boolean possuiInformacao(){

        if(informacao == null){
            return false;
        }

        return true;
    }


    public boolean selecionado(){

        if(id == 0){
            return false;
        }

        return true;
    }

}
