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

public class EquipamentoRepositorio {

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


    public Observable<List<Equipamento>> obterEquipamentos_Excluir(int idAtividade, List<Integer> registos) {
        return tipoDao.obterEquipamentos_Excluir(idApi, registos); //-idAtividade,
    }


    public Observable<List<Equipamento>> obterEquipamentos_Incluir(int idAtividade, List<Integer> registos) {
        return tipoDao.obterEquipamentos_Incluir(idApi, registos); //-idAtividade,
    }

    public Maybe<List<Equipamento>> obterEquipamentos_Excluir(int idAtividade, List<Integer> registos, String pesquisa) {
        return tipoDao.obterEquipamentos_Excluir(registos, pesquisa, idApi);
    }


    public Single<Boolean> validarEquipamento(String descricao) {
        return tipoDao.validarEquipamento(idApi, descricao);
    }

    public Single<Long> inserir(TipoNovo registo) {
        return tipoNovoDao.inserir(registo);
    }


}
