package com.vvm.sh.ui.atividadesPendentes.relatorios.equipamentos.modelos;

import androidx.room.ColumnInfo;

public class Equipamento {

    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "descricao")
    public String descricao;

    @ColumnInfo(name = "estado")
    public int estado;


    public String obterEstado(){

        String resultado = "";

        if(estado == 1){
            resultado = "Novo equipamento";
        }

        return resultado;
    }


    public boolean novoEquipamento(){

        boolean resultado = false;

        if(estado == 1){
            resultado = true;
        }

        return resultado;
    }

}
