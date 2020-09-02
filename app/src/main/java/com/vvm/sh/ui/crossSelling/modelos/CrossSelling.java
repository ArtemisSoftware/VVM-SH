package com.vvm.sh.ui.crossSelling.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import com.vvm.sh.baseDados.entidades.Tipo;

public class CrossSelling {

    @Embedded
    public Tipo tipo;


    @ColumnInfo(name = "idTarefa")
    public int idTarefa;

    @ColumnInfo(name = "idAreaRecomendacao")
    public int idAreaRecomendacao;

    @ColumnInfo(name = "idDimensaoSinaletica")
    public int idDimensaoSinaletica;

    @ColumnInfo(name = "idTipoSinaletica")
    public int idTipoSinaletica;

    @ColumnInfo(name = "dimensaoSinaletica")
    public String dimensaoSinaletica;

    @ColumnInfo(name = "tipoSinaletica")
    public String tipoSinaletica;


    @ColumnInfo(name = "possuiSinaletica")
    public boolean possuiSinaletica;

    @ColumnInfo(name = "selecionado")
    public boolean selecionado;


//
//    /**
//     * Metodo que indica se o registo foi selecionado
//     * @return true caso tenha sido selecionado ou false caso contrário
//     */
//    public boolean obterSelecao() {
//        if(idAreaRecomendacao == 0){
//            return false;
//        }
//        else{
//            return true;
//        }
//    }
//
//
//    /**
//     * Metodo que indica se o registo possui sinaletica
//     * @return true caso possua ou false caso contrário
//     */
//    public boolean possuiSinaletica() {
//        if(idDimensao == 0 & idTipo == 0){
//            return false;
//        }
//        else{
//            return true;
//        }
//    }


}
