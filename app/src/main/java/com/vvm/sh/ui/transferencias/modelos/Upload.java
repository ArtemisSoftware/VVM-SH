package com.vvm.sh.ui.transferencias.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Relation;

import com.vvm.sh.baseDados.entidades.Cliente;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.baseDados.entidades.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class Upload {

    @Embedded
    public Tarefa tarefa;

    @Relation(parentColumn = "idTarefa", entityColumn = "idTarefa")
    public List<Resultado> resultados;

    @Relation(parentColumn = "idTarefa", entityColumn = "idTarefa")
    public Cliente cliente;

    @ColumnInfo(name = "sincronizado")
    public int sincronizado;


    /**
     * Metodo que permite filtrar os resultados
     * @param reupload se false só os resultados não sincronizados serão selecionados, se true todos os resultados serão selecionados
     */
    public void filtrarResultados(boolean reupload){

        if(reupload == false){

            List<Resultado> registos = new ArrayList<>();

            for (Resultado item : resultados) {

                if(item.sincronizado == false){
                    registos.add(item);
                }
            }

            this.resultados = registos;
        }
    }

}
