package com.vvm.sh.baseDados;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.vvm.sh.ui.cliente.Cliente;

import io.reactivex.Flowable;

@Dao
abstract public class ClienteDao  implements BaseDao<Cliente>{

    @Insert
    abstract public void inserirRegisto(Cliente registo);


    @Query("SELECT * FROM clientes WHERE idTarefa = :idTarefa")
    abstract public Flowable<Cliente> obterCliente(int idTarefa);

}
