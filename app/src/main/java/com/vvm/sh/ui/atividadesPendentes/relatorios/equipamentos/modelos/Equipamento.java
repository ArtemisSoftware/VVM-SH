package com.vvm.sh.ui.atividadesPendentes.relatorios.equipamentos.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;

import com.vvm.sh.util.interfaces.EstadoModelo;

public class Equipamento implements EstadoModelo {

    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "descricao")
    public String descricao;

    @ColumnInfo(name = "estado")
    public int estado;


    public Equipamento(int id, String descricao, int estado) {
        this.id = id;
        this.descricao = descricao;
        this.estado = estado;
        this.estadoModelo = EstadoModelo.MODELO;
    }

    public String obterEstadoEquipamento(){

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


    @Ignore
    public int estadoModelo;

    @Ignore
    public Equipamento(int estadoModelo){
        this.estadoModelo = estadoModelo;
    }


    @Override
    public int obterEstado() {
        return estadoModelo;
    }
}
