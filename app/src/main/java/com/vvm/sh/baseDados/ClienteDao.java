package com.vvm.sh.baseDados;

import androidx.room.Dao;
import androidx.room.Insert;

import com.vvm.sh.ui.cliente.Cliente;

@Dao
abstract public class ClienteDao  implements BaseDao<Cliente>{

    @Insert
    abstract public void inserirRegisto(Cliente registo);
}
