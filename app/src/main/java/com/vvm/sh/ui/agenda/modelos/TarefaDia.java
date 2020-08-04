package com.vvm.sh.ui.agenda.modelos;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.ui.cliente.Cliente;
import com.vvm.sh.baseDados.entidades.EmailResultado;
import com.vvm.sh.util.constantes.Sintaxe;

public class TarefaDia {

    @Embedded
    public Tarefa tarefa;


    @Relation(
            parentColumn = "idTarefa",
            entityColumn = "idTarefa"
    )
    public Cliente cliente;


    @Relation(
            parentColumn = "idTarefa",
            entityColumn = "idTarefa"
    )
    public EmailResultado email;


    /**
     * Metodo que indica se a tarefa já foi validada
     * @return true caso a tarefa já tenho sido validada ou false caso contrario
     */
    public boolean tarefaValidada(){

        if(email == null){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Metodo que permite obter o email
     * @return o endereco de email e a sual autorizacao
     */
    public String obterEmail(){

        if(email == null){

            if(cliente.emailAutenticado == true){
                return cliente.email + " " + Sintaxe.Palavras.AUTENTICADO;
            }
            else{
                return cliente.email + " " + Sintaxe.Palavras.NAO_AUTENTICADO;
            }

        }
        else{
            return email.endereco + " (" + email.autorizacao + ")";
        }
    }


    /**
     * Metodo que permite obter o endereco de email
     * @return o endereco de email
     */
    public String obterEnderecoEmail(){

        if(email == null){
            return cliente.email;
        }
        else{
            return email.endereco;
        }
    }

}
