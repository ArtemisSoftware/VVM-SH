package com.vvm.sh.baseDados;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.vvm.sh.baseDados.entidades.Cliente;

import io.reactivex.Flowable;

@Dao
abstract public class ClienteDao  implements BaseDao<Cliente>{



    @Query("SELECT * FROM clientes WHERE idTarefa = :idTarefa")
    abstract public Flowable<Cliente> obterCliente(int idTarefa);

}
