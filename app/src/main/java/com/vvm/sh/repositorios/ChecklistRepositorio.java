package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.AreaChecklistDao;
import com.vvm.sh.baseDados.dao.QuestionarioChecklistDao;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.dao.TipoDao;
import com.vvm.sh.baseDados.entidades.AreaChecklist;
import com.vvm.sh.baseDados.entidades.AreaChecklistResultado;
import com.vvm.sh.baseDados.entidades.QuestionarioChecklistResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Item;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Questao;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;

public class ChecklistRepositorio {

    private final AreaChecklistDao areaChecklistDao;
    private final QuestionarioChecklistDao questionarioChecklistDao;
    private final TipoDao tipoDao;
    public final ResultadoDao resultadoDao;
    private int api;



    public ChecklistRepositorio(@NonNull int api, @NonNull AreaChecklistDao areaChecklistDao, @NonNull QuestionarioChecklistDao questionarioChecklistDao,
                                         @NonNull TipoDao tipoDao, @NonNull ResultadoDao resultadoDao) {

        this.areaChecklistDao = areaChecklistDao;
        this.questionarioChecklistDao = questionarioChecklistDao;
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
     * Metodo que permite inserir um registo
     * @param registo os dados a inserir
     * @return o resultado da insercao
     */
    public Single<Long> inserir(QuestionarioChecklistResultado registo){
        return questionarioChecklistDao.inserir(registo);
    }


    /**
     * Metodo que permite atualizar um registo
     * @param registo os dados a inserir
     * @return o resultado da insercao
     */
    public Single<Integer> atualizar(AreaChecklistResultado registo){
        return areaChecklistDao.atualizar(registo);
    }


    /**
     * Metodo que permite atualizar um registo
     * @param registo os dados a inserir
     * @return o resultado da insercao
     */
    public Single<Integer> atualizar(QuestionarioChecklistResultado registo){
        return questionarioChecklistDao.atualizar(registo);
    }



    public Single<Boolean> validarAreaGeral(int idAtividade, int idChecklist){
        return areaChecklistDao.validarAreaGeral(idAtividade, idChecklist);
    }


    public Single<Tipo> obterChecklist(int idAtividade){
        return areaChecklistDao.obterChecklist(idAtividade, api);
    }

    public Single<List<AreaChecklist>> obterAreasChecklist(int idChecklist){
        return areaChecklistDao.obterAreasChecklist(idChecklist);
    }

    public Observable<List<Item>> obterAreas(int idAtividade, int idChecklist){
        return areaChecklistDao.obterAreas(idAtividade, idChecklist);
    }

    public Observable<List<Item>> obterSeccoes(int idRegistoArea){
        return areaChecklistDao.obterSeccoes(idRegistoArea);
    }


    public Single<Boolean> validarSubDescricaoArea(int idAtividade, int idChecklist, int idArea, String subDescricao){
        return areaChecklistDao.validarSubDescricaoArea(idAtividade, idChecklist, idArea, subDescricao);
    }



    public Observable<List<Questao>> obterQuestoes(int idRegistoArea, String idSeccao){

        Observable<List<Questao>> single = Observable.zip(
                questionarioChecklistDao.obterQuestoes(idRegistoArea, idSeccao).toObservable(),
                questionarioChecklistDao.obterObservacao(idRegistoArea, idSeccao).toObservable(),
                new BiFunction<List<Questao>, Questao, List<Questao>>() {
                    @Override
                    public List<Questao> apply(List<Questao> questoes, Questao observacao) throws Exception {

                        List<Questao> resultado = new ArrayList<>();
                        resultado.addAll(questoes);
                        resultado.add(observacao);
                        return resultado;
                    }
                });


        return single;
    }


    public Single<List<Tipo>> obterNI(){
        return tipoDao.obterTipos_(TiposUtil.MetodosTipos.TIPOS_NI, api);

    }

}
