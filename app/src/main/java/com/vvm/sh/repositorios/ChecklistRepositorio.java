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
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.TiposConstantes;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function4;

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
                questionarioChecklistDao.obterQuestoes(idRegistoArea, idSeccao, Identificadores.Checklist.TIPO_QUESTAO),
                questionarioChecklistDao.obterQuestoes(idRegistoArea, idSeccao, Identificadores.Checklist.TIPO_OBSERVACOES),
                questionarioChecklistDao.obterQuestoes(idRegistoArea, idSeccao, Identificadores.Checklist.TIPO_UTS),
                questionarioChecklistDao.obterQuestoes(idRegistoArea, idSeccao, Identificadores.Checklist.TIPO_FOTOS),
                new Function4<List<Questao>, List<Questao>, List<Questao>, List<Questao>, List<Questao>>() {
                    @Override
                    public List<Questao> apply(List<Questao> questoes, List<Questao> observacoes, List<Questao> uts, List<Questao> fotos) throws Exception {

                        List<Questao> resultado = new ArrayList<>();

                        resultado.addAll(questoes);
                        resultado.addAll(uts);

                        if(uts.size() == 0) {
                            resultado.addAll(observacoes);
                            resultado.addAll(fotos);
                        }

                        return resultado;
                    }
                }
        );

        return single;
    }


    public Single<List<Tipo>> obterNI(){
        return tipoDao.obterTipos_(TiposUtil.MetodosTipos.TIPOS_NI, api);
    }

    public Single<List<Tipo>> obterUts(){
        return tipoDao.obterTipos_(TiposUtil.MetodosTipos.TIPOS_UTS, api);
    }

    public Single<QuestionarioChecklistResultado> obterQuestao(int idRegisto){
        return questionarioChecklistDao.obterQuestao(idRegisto);

    }

    public Single<Integer> remover(int idAtividade) {
        return questionarioChecklistDao.remover(idAtividade);
    }

    public Single<Integer> removerArea(int id) {
        return questionarioChecklistDao.removerArea(id);
    }

    public Completable remover__(int idAtividade, Item registo) {

        Completable lolo2 = questionarioChecklistDao.removerArea(registo.idArea, registo.uid);
                Completable lolo = questionarioChecklistDao.inserirNaoAplicavel(registo.id, registo.uid, Identificadores.Checklist.TIPO_QUESTAO, TiposConstantes.Checklist.NAO_APLICAVEL.descricao);
        Completable all = Completable.concatArray(lolo2, lolo);

        return all;

    }
}
