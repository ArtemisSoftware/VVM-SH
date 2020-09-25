package com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import com.vvm.sh.baseDados.entidades.ItemChecklist;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.util.constantes.TiposConstantes;

public class Questao {

    @Embedded
    public ItemChecklist registo;


    @ColumnInfo(name = "idRegistoItem")
    public int id;

    @ColumnInfo(name = "resposta")
    public String resposta;

    @ColumnInfo(name = "ni")
    public String ni;

    @ColumnInfo(name = "observacao")
    public String observacao;




    @ColumnInfo(name = "ut1Descricao")
    public String ut1;

    @ColumnInfo(name = "ut1_CategoriasRisco")
    public int ut1_CategoriasRisco;


    @ColumnInfo(name = "ut1_LocalRisco_A")
    public boolean ut1_LocalRisco_A;

    @ColumnInfo(name = "ut1_LocalRisco_B")
    public boolean ut1_LocalRisco_B;

    @ColumnInfo(name = "ut1_LocalRisco_C")
    public boolean ut1_LocalRisco_C;

    @ColumnInfo(name = "ut1_LocalRisco_D")
    public boolean ut1_LocalRisco_D;

    @ColumnInfo(name = "ut1_LocalRisco_E")
    public boolean ut1_LocalRisco_E;

    @ColumnInfo(name = "ut1_LocalRisco_F")
    public boolean ut1_LocalRisco_F;



    @ColumnInfo(name = "ut2Descricao")
    public String ut2;

    @ColumnInfo(name = "ut2_CategoriasRisco")
    public int ut2_CategoriasRisco;

    @ColumnInfo(name = "ut2_LocalRisco_A")
    public boolean ut2_LocalRisco_A;

    @ColumnInfo(name = "ut2_LocalRisco_B")
    public boolean ut2_LocalRisco_B;

    @ColumnInfo(name = "ut2_LocalRisco_C")
    public boolean ut2_LocalRisco_C;

    @ColumnInfo(name = "ut2_LocalRisco_D")
    public boolean ut2_LocalRisco_D;

    @ColumnInfo(name = "ut2_LocalRisco_E")
    public boolean ut2_LocalRisco_E;

    @ColumnInfo(name = "ut2_LocalRisco_F")
    public boolean ut2_LocalRisco_F;



    @ColumnInfo(name = "numeroImagens")
    public int numeroImagens;








    public String obterUt1_CategoriasRisco(){

        String resultado = "";

        for (Tipo tipo : TiposConstantes.Checklist.CATEGORIAS_RISCO) {
            if(ut1_CategoriasRisco == tipo.id){
                return tipo.descricao;
            }
        }

        return resultado;
    }


    public String obterUt1_Locais(){

        String resultado = "";

        if(ut1_LocalRisco_A == true){
            resultado += "A ";
        }
        if(ut1_LocalRisco_B == true){
            resultado += "B ";
        }
        if(ut1_LocalRisco_C == true){
            resultado += "C ";
        }
        if(ut1_LocalRisco_D == true){
            resultado += "D ";
        }
        if(ut1_LocalRisco_E == true){
            resultado += "E ";
        }
        if(ut1_LocalRisco_F == true){
            resultado += "F ";
        }

        return resultado;
    }



    public String obterUt2_CategoriasRisco(){

        String resultado = "";

        for (Tipo tipo : TiposConstantes.Checklist.CATEGORIAS_RISCO) {
            if(ut2_CategoriasRisco == tipo.id){
                return tipo.descricao;
            }
        }

        return resultado;
    }


    public String obterUt2_Locais(){

        String resultado = "";

        if(ut2_LocalRisco_A == true){
            resultado += "A ";
        }
        if(ut2_LocalRisco_B == true){
            resultado += "B ";
        }
        if(ut2_LocalRisco_C == true){
            resultado += "C ";
        }
        if(ut2_LocalRisco_D == true){
            resultado += "D ";
        }
        if(ut2_LocalRisco_E == true){
            resultado += "E ";
        }
        if(ut2_LocalRisco_F == true){
            resultado += "F ";
        }

        return resultado;
    }
}
