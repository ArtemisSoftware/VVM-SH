package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.AreaChecklistDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.entidades.AreaChecklist;
import com.vvm.sh.baseDados.entidades.AreaChecklistResultado;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Item;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public class ChecklistRepositorio {

    private final AreaChecklistDao areaChecklistDao;
    private final TipoDao tipoDao;
    public final ResultadoDao resultadoDao;
    private int api;



    public ChecklistRepositorio(@NonNull int api, @NonNull AreaChecklistDao areaChecklistDao,
                                         @NonNull TipoDao tipoDao, @NonNull ResultadoDao resultadoDao) {

        this.areaChecklistDao = areaChecklistDao;
        this.tipoDao = tipoDao;
        this.resultadoDao = resultadoDao;

        this.api = api;
    }

    /**
     * Metodo que permite inserir um registo
     * @param registo os dados a inserir
     * @return o resultado da insercao
     */
    public Single<Long> inserir(AreaChecklistResultado registo){
        return areaChecklistDao.inserir(registo);
    }

    /**
     * Metodo que permite atualizar um registo
     * @param registo os dados a inserir
     * @return o resultado da insercao
     */
    public Single<Integer> atualizar(AreaChecklistResultado registo){
        return areaChecklistDao.atualizar(registo);
    }

    public Single<Boolean> validarAreaGeral(int idAtividade, int idChecklist){
        return areaChecklistDao.validarAreaGeral(idAtividade, idChecklist);
    }



    public Single<List<AreaChecklist>> obterAreasChecklist(int idChecklist){
        return areaChecklistDao.obterAreasChecklist(idChecklist);
    }

    public Observable<List<Item>> obterAreas(int idAtividade, int idChecklist){
        return areaChecklistDao.obterAreas(idAtividade, idChecklist);
    }

}
