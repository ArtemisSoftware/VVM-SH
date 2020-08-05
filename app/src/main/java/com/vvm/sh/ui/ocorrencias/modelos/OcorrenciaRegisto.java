package com.vvm.sh.ui.ocorrencias.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Ignore;
import androidx.room.Relation;

import com.vvm.sh.baseDados.entidades.OcorrenciaResultado;
import com.vvm.sh.ui.opcoes.modelos.Tipo;

public class OcorrenciaRegisto {

    @Embedded
    public OcorrenciaResultado resultado;

    @Relation(
            parentColumn = "id",
            entityColumn = "id"
    )
    public Tipo tipo;


    @ColumnInfo(name = "ultimoRegisto")
    public int ultimoRegisto;

    public OcorrenciaRegisto() {
    }

    @Ignore
    public OcorrenciaRegisto(Ocore item) {

        this.resultado = item.resultado;
        this.tipo = item.tipo;
        this.ultimoRegisto = item.ultimoRegisto;

    }


    public String obterDescricao(){
        return tipo.codigo + " " + tipo.descricao;
    }

    public boolean itemRegisto(){

        if(ultimoRegisto == 0){
            return true;
        }
        else if(tipo.detalhe.equals("") == true){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean existeDetalhe(){

        if(tipo.detalhe.equals("") == true){
            return false;
        }
        else{
            return true;
        }
    }


    public boolean estadoFiscalizacao(){
        return  resultado.fiscalizado;
    }


    public boolean existeResultado(){
        return  resultado.id > 0;
    }
}
