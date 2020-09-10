package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.QuadroPessoalDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.ui.tarefa.quadroPessoal.ColaboradorRegisto;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public class QuadroPessoalRepositorio {

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
    public Flowable<List<ColaboradorRegisto>> obterQuadroPessoal(int idTarefa) {
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
}
