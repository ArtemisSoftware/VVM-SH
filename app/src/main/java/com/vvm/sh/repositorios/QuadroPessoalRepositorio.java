package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.QuadroPessoalDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.entidades.ColaboradorResultado;
import com.vvm.sh.baseDados.entidades.Morada;
import com.vvm.sh.ui.quadroPessoal.modelos.ColaboradorRegisto;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public class QuadroPessoalRepositorio implements Repositorio<ColaboradorResultado>{

    private final QuadroPessoalDao quadroPessoalDao;
    private final TipoDao tipoDao;
    public final ResultadoDao resultadoDao;

    public QuadroPessoalRepositorio(@NonNull QuadroPessoalDao quadroPessoalDao,
                                    @NonNull TipoDao tipoDao, @NonNull ResultadoDao resultadoDao) {

        this.quadroPessoalDao = quadroPessoalDao;
        this.resultadoDao = resultadoDao;
        this.tipoDao = tipoDao;
    }


    /**
     * Metodo que permite obter o quadro pessoal associado à tarefa
     * @param idTarefa o identificador da tarefa
     * @return uma lista de registos
     */
    public Observable<List<ColaboradorRegisto>> obterQuadroPessoal(int idTarefa) {
        return quadroPessoalDao.obterQuadroPessoal(idTarefa);
    }


    /**
     * Metodo que permite obter um colaborador
     * @param id o identificador do colaborador
     * @return os dados do colaborador
     */
    public Maybe<ColaboradorRegisto> obterColaborador(int id){
        return quadroPessoalDao.obterColaborador(id);
    }


    /**
     * Metodo que permite remover o colaborador
     * @param id o identificador do colaborador
     * @return o resultado da remoção
     */
    public Single<Integer> remover(int id){
        return quadroPessoalDao.removerColaborador(id);
    }


    /**
     * Metodo que permite obter as moradas
     * @param idTarefa o identificador da tarefa
     * @return uma lista de moradas
     */
    public Maybe<List<Morada>> obterMoradas(int idTarefa){
        return quadroPessoalDao.obterMoradas(idTarefa);
    }


    @Override
    public Single<Long> inserir(ColaboradorResultado item) {
        return quadroPessoalDao.inserir(item);
    }

    @Override
    public Single<Integer> atualizar(ColaboradorResultado item) {
        return quadroPessoalDao.atualizar(item);
    }

    @Override
    public Single<Integer> remover(ColaboradorResultado item) {
        return quadroPessoalDao.remover(item);
    }
}
