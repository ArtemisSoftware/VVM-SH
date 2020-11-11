package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.PesquisaDao;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.ui.pesquisa.modelos.Medida;
import com.vvm.sh.ui.pesquisa.modelos.PesquisaTipos;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;

public class PesquisaRepositorio {

    private final int idApi;
    private final PesquisaDao pesquisaDao;

    public PesquisaRepositorio(int idApi, @NonNull PesquisaDao pesquisaDao) {

        this.idApi = idApi;
        this.pesquisaDao = pesquisaDao;
    }

    public Single<List<Medida>> obterMedidas(String metodo, List<Integer> registos, String idPai, int limite) {
        return pesquisaDao.obterMedidas(metodo, idApi, registos, idPai, limite);
    }

    public Single<List<Medida>> obterMedidas(String metodo, String codigo, List<Integer> registos, int pagina) {
        return pesquisaDao.obterMedidas(metodo, codigo, idApi, registos, pagina);
    }

    public Single<List<Medida>> obterMedidas(String metodo, List<Integer> registos, int pagina) {
        return pesquisaDao.obterMedidas(metodo, idApi, registos, pagina);
    }



    public Single<PesquisaTipos> obterRegistos(String metodo, List<Integer> registos, int pagina){

        return Single.zip(
                pesquisaDao.obterTipos_Excluir(metodo, registos, idApi, pagina),
                pesquisaDao.obterTipos_Incluir(metodo, registos, idApi),
                new BiFunction<List<Tipo>, List<Tipo>, PesquisaTipos>() {
                    @Override
                    public PesquisaTipos apply(List<Tipo> tipos, List<Tipo> tiposSelecionados) throws Exception {
                        return new PesquisaTipos(tipos, tiposSelecionados);
                    }
                }
        );
    }



    /**
     * Metodo que permite obter uma lista de tipo excluido determinados registos
     * @param metodo o nome do metodo associado ao tipo
     * @param registos os registos a excluir
     * @return uma lista de registos
     */
    public Single<List<Tipo>> obterTipos_Excluir(String metodo, List<Integer> registos, int pagina) {
        return pesquisaDao.obterTipos_Excluir(metodo, registos, idApi, pagina);
    }


    /**
     * Metodo que permite obter uma lista de tipos apenas com base em registos especificos
     * @param metodo o nome do metodo associado ao tipo
     * @param registos os registos a incluir
     * @return uma lista de registos
     */
    public Single<List<Tipo>> obterTipos_Incluir(String metodo, List<Integer> registos) {
        return pesquisaDao.obterTipos_Incluir(metodo, registos, idApi);
    }



//    public Maybe<List<Tipo>> pesquisar(String metodo, List<Integer> registos, String pesquisa, int pagina) {
//
//        return pesquisaDao.pesquisar(metodo, registos, pesquisa, idApi, pagina);
//    }


    public Single<PesquisaTipos> pesquisar(String metodo, List<Integer> registos, String pesquisa, int pagina){

        return Single.zip(
                pesquisaDao.pesquisar(metodo, registos, pesquisa, idApi, pagina).toSingle(),
                pesquisaDao.obterTipos_Incluir(metodo, registos, idApi),
                new BiFunction<List<Tipo>, List<Tipo>, PesquisaTipos>() {
                    @Override
                    public PesquisaTipos apply(List<Tipo> tipos, List<Tipo> tiposSelecionados) throws Exception {
                        return new PesquisaTipos(tipos, tiposSelecionados);
                    }
                }
        );
    }

}
