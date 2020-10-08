package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.CrossSellingDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.entidades.CrossSellingResultado;
import com.vvm.sh.ui.crossSelling.modelos.CrossSelling;
import com.vvm.sh.util.constantes.TiposConstantes;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

public class CrossSellingRepositorio implements Repositorio<CrossSellingResultado>{

    private final int idApi;
    private final CrossSellingDao crossSellingDao;
    private final TipoDao tipoDao;
    public final ResultadoDao resultadoDao;

    public CrossSellingRepositorio(int idApi, @NonNull CrossSellingDao crossSellingDao, @NonNull TipoDao tipoDao, @NonNull ResultadoDao resultadoDao) {
        this.idApi = idApi;
        this.crossSellingDao = crossSellingDao;
        this.tipoDao = tipoDao;
        this.resultadoDao = resultadoDao;
    }


    /**
     * Metodo que permite obter o cross selling
     * @param idTarefa o identificador da tarefa
     * @param idProduto o identificador do produto
     * @return uma lista de registos
     */
    public Observable<List<CrossSelling>> obterCrossSelling(int idTarefa, String idProduto){
        return crossSellingDao.obterCrossSelling(idTarefa, idProduto, idApi);
    }

    @Override
    public Single<Long> inserir(CrossSellingResultado registo){
        return crossSellingDao.inserir(registo);
    }



    public Single<Integer> remover(CrossSelling registo){
        return crossSellingDao.remover(registo.idTarefa, registo.tipo.id);
    }

    /**
     * Metodo que permite obter os produtos
     * @return uma lista de produtos
     */
    public Single<List<Tipo>> obterProdutos(){
        return tipoDao.obterTipos_(TiposUtil.MetodosTipos.CROSS_SELLING_PRODUTOS, idApi, "");
    }


    public Single<List<Tipo>> obterDimensoes(){
        return tipoDao.obterTipos_(TiposUtil.MetodosTipos.CROSS_SELLING_DIMENSAO, idApi);
    }

    public Single<List<Tipo>> obterTipos(){
        return tipoDao.obterTipos_(TiposUtil.MetodosTipos.CROSS_SELLING_TIPO, idApi);
    }



    @Override
    public Single<Integer> atualizar(CrossSellingResultado item) {
        return null;
    }

    @Override
    public Single<Integer> remover(CrossSellingResultado item) {
        return null;
    }
}
