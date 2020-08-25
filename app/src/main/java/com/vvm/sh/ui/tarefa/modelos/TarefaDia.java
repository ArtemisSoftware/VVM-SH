package com.vvm.sh.ui.tarefa.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Relation;

import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.baseDados.entidades.Cliente;
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


    @ColumnInfo(name = "anomalias")
    public int anomalias;

    /**
     * Metodo que permite obter a marca e a ordem da tarefa
     * @return a marca e a ordem
     */
    public String obterMarcaOrdem(){
        return tarefa.prefixoCt + " / " + tarefa.ordem;
    }

    /**
     * Metodo que permite obter a morada
     * @return a morada
     */
    public String obterMorada(){
        return cliente.morada + "\n" + cliente.codigoPostal + " " + cliente.freguesia;
    }


    /**
     * Metodo que permite obter o cae e a atividade
     * @return cae - atividade
     */
    public String obterAtividade(){
        return cliente.cae + " - " + cliente.actividade;
    }


    /**
     * Metodo que permite obter o segundo cae e a atividade
     * @return cae - atividade
     */
    public String obterAtividade2(){
        return cliente.cae1 + " - " + cliente.actividade1;
    }

    /**
     * Metodo que indica se existe o segundo cae e a atividade
     * @return true caso exista ou false caso contrario
     */
    public boolean existeAtividade2(){

        if(cliente.cae1.equals(Sintaxe.SEM_TEXTO) == true){
            return false;
        }
        else{
            return true;
        }
    }


    /**
     * Metodo que permite obter os contactos
     * @return telefone + telemovel
     */
    public String obterContactos(){
        return cliente.telemovel + ", " + cliente.telefone;
    }


    /**
     * Metodo que permite obter o servico
     * @return o servico
     */
    public String obterServico(){
        return cliente.servicoTp + " - " + cliente.servico;
    }






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
