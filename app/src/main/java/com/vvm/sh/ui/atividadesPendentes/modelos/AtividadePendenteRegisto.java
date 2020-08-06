package com.vvm.sh.ui.atividadesPendentes.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Relation;

import com.vvm.sh.baseDados.entidades.AtividadePendente;
import com.vvm.sh.baseDados.entidades.AtividadePendenteResultado;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;

public class AtividadePendenteRegisto {


    @Embedded
    public AtividadePendente atividade;


    @Relation(
            parentColumn = "id",
            entityColumn = "id"
    )
    public AtividadePendenteResultado resultado;


    @ColumnInfo(name = "idRelatorio")
    public int idRelatorio;

    @ColumnInfo(name = "nomeRelatorio")
    public String nomeRelatorio;


    @ColumnInfo(name = "possuiRelatorio")
    public boolean possuiRelatorio;

    @ColumnInfo(name = "relatorioCompleto")
    public boolean relatorioCompleto;





    /**
     * Metodo que indica se existe um relatorio associado à atividade
     * @return true caso exista ou false caso contrario
     */
    public boolean existeRelatorio(){

        if(atividade.formacao == true){
            return true;
        }
        else{
            return true; //TODO: para teste. Mudar para false quando acabar os testes
        }
    }


    /**
     * Metodo que devolve a descricao do relatorio
     * @return uma descricao
     */
    public String obterRelatorio(){

        if(atividade.formacao == true){
            return Sintaxe.Palavras.FORMACAO;
        }
        else{
            return "";
        }
    }


    /**
     * Metodo que permite obter o identificador do relatorio
     * @return o identificador do relatorio
     */
    public int obterIdRelatorio(){

        if(atividade.formacao == true){
            return Identificadores.Relatorios.ID_RELATORIO_FORMACAO;
        }
        else{
            return Identificadores.Relatorios.ID_RELATORIO_FORMACAO; //TODO: para teste. Mudar para Identificadores.Estados.SEM_RELATORIO quando acabar os testes
        }
    }


    /**
     * Metodo que indica se existe um resultado
     * @return true caso exista
     */
    public boolean obterCompletudeResultatorio(){
        return true; //TODO por logica aqui
    }


    /**
     * Metodo que indica se existe um resultado
     * @return true caso exista
     */
    public boolean existeResultado(){
        return resultado != null;
    }




}
