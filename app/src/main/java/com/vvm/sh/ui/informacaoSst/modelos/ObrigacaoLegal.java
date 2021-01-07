package com.vvm.sh.ui.informacaoSst.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import com.vvm.sh.baseDados.entidades.Tipo;

public class ObrigacaoLegal {

    @Embedded
    public Tipo tipo;


    @ColumnInfo(name = "idRegisto")
    public int id;



    public boolean selecionado(){

        if(id == 0){
            return false;
        }

        return true;
    }

}
