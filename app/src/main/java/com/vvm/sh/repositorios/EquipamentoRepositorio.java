package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.dao.TipoNovoDao;
import com.vvm.sh.baseDados.dao.VerificacaoEquipamentoDao;
import com.vvm.sh.baseDados.entidades.TipoNovo;
import com.vvm.sh.baseDados.entidades.VerificacaoEquipamentoResultado;
import com.vvm.sh.ui.atividadesPendentes.relatorios.equipamentos.Equipamento;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public class EquipamentoRepositorio{

    private final int idApi;
    private final TipoNovoDao tipoNovoDao;
    private final TipoDao tipoDao;
    private final VerificacaoEquipamentoDao verificacaoEquipamentoDao;
    private final ResultadoDao resultadoDao;


    public EquipamentoRepositorio(int idApi, @NonNull TipoNovoDao tipoNovoDao, @NonNull TipoDao tipoDao, @NonNull VerificacaoEquipamentoDao verificacaoEquipamentoDao,
                                  @NonNull ResultadoDao resultadoDao) {
        this.idApi = idApi;
        this.tipoNovoDao = tipoNovoDao;
        this.tipoDao = tipoDao;
        this.verificacaoEquipamentoDao = verificacaoEquipamentoDao;
        this.resultadoDao = resultadoDao;
    }


    public Observable<List<Equipamento>> obterEquipamentos_Excluir(List<String> registos) {
        return tipoNovoDao.obterEquipamentos_Excluir(idApi, registos); //-idAtividade,
    }


    public Observable<List<Equipamento>> obterEquipamentos_Incluir(List<String> registos) {
        return tipoNovoDao.obterEquipamentos_Incluir(idApi, registos); //-idAtividade,
    }

    public Maybe<List<Equipamento>> obterEquipamentos_Excluir(int idAtividade, List<String> registos, String pesquisa) {
        return tipoNovoDao.obterEquipamentos_Excluir(registos, pesquisa, idApi);
    }


    public Single<Boolean> validarEquipamento(String descricao) {
        return tipoNovoDao.validarEquipamento(descricao);
    }

    public Single<Long> inserir(TipoNovo registo) {
        return tipoNovoDao.inserir(registo);
    }



    public Single<List<Object>> inserir(int idAtividade, List<VerificacaoEquipamentoResultado> registos) {

        return Single.concat(verificacaoEquipamentoDao.remover(idAtividade), verificacaoEquipamentoDao.inserir(registos)).toList();
    }


    public Maybe<List<String>> obterEquipamentos(int idAtividade) {
        return tipoNovoDao.obterEquipamentos(idAtividade, idApi);
    }

}
