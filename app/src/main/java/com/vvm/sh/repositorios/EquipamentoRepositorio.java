package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.dao.TipoNovoDao;
import com.vvm.sh.baseDados.entidades.TipoNovo;
import com.vvm.sh.ui.atividadesPendentes.relatorios.equipamentos.Equipamento;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public class EquipamentoRepositorio implements Repositorio<TipoNovo>{

    private final int idApi;
    private final TipoNovoDao tipoNovoDao;
    private final TipoDao tipoDao;
    private final ResultadoDao resultadoDao;


    public EquipamentoRepositorio(int idApi, @NonNull TipoNovoDao tipoNovoDao, @NonNull TipoDao tipoDao, @NonNull ResultadoDao resultadoDao) {
        this.idApi = idApi;
        this.tipoNovoDao = tipoNovoDao;
        this.tipoDao = tipoDao;
        this.resultadoDao = resultadoDao;
    }


    public Observable<List<Equipamento>> obterEquipamentos_Excluir(int idAtividade, List<String> registos) {
        return tipoNovoDao.obterEquipamentos_Excluir(idApi, registos); //-idAtividade,
    }


    public Observable<List<Equipamento>> obterEquipamentos_Incluir(int idAtividade, List<String> registos) {
        return tipoNovoDao.obterEquipamentos_Incluir(idApi, registos); //-idAtividade,
    }

    public Maybe<List<Equipamento>> obterEquipamentos_Excluir(int idAtividade, List<String> registos, String pesquisa) {
        return tipoNovoDao.obterEquipamentos_Excluir(registos, pesquisa, idApi);
    }


    public Single<Boolean> validarEquipamento(String descricao) {
        return tipoNovoDao.validarEquipamento(idApi, descricao);
    }

    @Override
    public Single<Long> inserir(TipoNovo registo) {
        return tipoNovoDao.inserir(registo);
    }

    @Override
    public Single<Integer> atualizar(TipoNovo item) {
        return null;
    }

    @Override
    public Single<Integer> remover(TipoNovo item) {
        return null;
    }


}
