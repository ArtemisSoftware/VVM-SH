package com.vvm.sh.ui.atividadesPendentes.relatorios;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

public class Formando {

    @Embedded
    public FormandoResultado resultado;


    @ColumnInfo(name = "assinatura")
    public byte[] assinatura;


    /**
     * Metodo que indica se os dados do formando estão completos
     * @return true caso esteja completo ou false caso contrario
     */
    public boolean estado(){

        if(resultado != null & assinatura != null){
            return true;
        }
        else{
            return false;
        }
    }

}
