package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import com.vvm.sh.baseDados.entidades.CategoriaProfissionalResultado;

import java.util.Objects;

public class CategoriaProfissional {

    @Embedded
    public CategoriaProfissionalResultado categoria;

    @ColumnInfo(name = "descricao")
    public String descricao;

    @ColumnInfo(name = "valido")
    public boolean valido;


    public boolean completarRegisto(){


        if(categoria.homens > 0){
            return false;
        }

        if(categoria.mulheres > 0){
            return false;
        }

        return true;
    }
}
