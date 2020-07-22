package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.AtividadeExecutadaDao;
import com.vvm.sh.baseDados.ClienteDao;
import com.vvm.sh.ui.atividadesExecutadas.modelos.AtividadeExecutada;
import com.vvm.sh.ui.cliente.Cliente;

import java.util.List;

import io.reactivex.Flowable;

public class TarefaRepositorio {

    private final ClienteDao clienteDao;
    private final AtividadeExecutadaDao atividadeExecutadaDao;

    public TarefaRepositorio(@NonNull ClienteDao clienteDao, @NonNull AtividadeExecutadaDao atividadeExecutadaDao) {
        this.clienteDao = clienteDao;
        this.atividadeExecutadaDao = atividadeExecutadaDao;
    }

    public Flowable<Cliente> obterCliente(int idTarefa) {
        return clienteDao.obterCliente(idTarefa);
    }



    public Flowable<List<AtividadeExecutada>> obterAtividadesExecutadas(int idTarefa) {
        return atividadeExecutadaDao.obterAtividades(idTarefa);
    }



}
