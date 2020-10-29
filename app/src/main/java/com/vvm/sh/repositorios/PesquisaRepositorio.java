package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.PesquisaDao;
import com.vvm.sh.ui.pesquisa.modelos.Medida;

import java.util.List;

import io.reactivex.Observable;

public class PesquisaRepositorio {

    private final int idApi;
    private final PesquisaDao pesquisaDao;

    public PesquisaRepositorio(int idApi, @NonNull PesquisaDao pesquisaDao) {

        this.idApi = idApi;
        this.pesquisaDao = pesquisaDao;
    }

    public Observable<List<Medida>> obterMedidas(String metodo, int api, List<Integer> registos, String idPai, int pagina) {
        return pesquisaDao.obterMedidas(metodo, api, registos, idPai, pagina);
    }

    public Observable<List<Medida>> obterMedidas(String metodo, String codigo, int api, List<Integer> registos, int pagina) {
        return pesquisaDao.obterMedidas(metodo, codigo, api, registos, pagina);
    }
}
