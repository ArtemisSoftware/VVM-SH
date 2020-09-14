package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.CategoriaProfissionalDao;
import com.vvm.sh.baseDados.dao.LevantamentoDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.entidades.LevantamentoRiscoResultado;

import java.util.List;

import io.reactivex.Observable;

public class LevantamentoRepositorio {


    private final LevantamentoDao levantamentoDao;
    private final CategoriaProfissionalDao categoriaProfissionalDao;

    private final TipoDao tipoDao;
    public final ResultadoDao resultadoDao;


    public LevantamentoRepositorio(@NonNull LevantamentoDao levantamentoDao, @NonNull CategoriaProfissionalDao categoriaProfissionalDao,
                                   @NonNull TipoDao tipoDao, @NonNull ResultadoDao resultadoDao) {

        this.levantamentoDao = levantamentoDao;
        this.categoriaProfissionalDao = categoriaProfissionalDao;


        this.tipoDao = tipoDao;
        this.resultadoDao = resultadoDao;
    }


    /**
     * Metodo que permite obter os levantamentos
     * @param idAtividade o identificador da atividade
     * @return uma lista de registos
     */
    public Observable<List<LevantamentoRiscoResultado>> obterLevantamentos(int idAtividade){
        return levantamentoDao.obterLevantamentos(idAtividade);
    }
}
