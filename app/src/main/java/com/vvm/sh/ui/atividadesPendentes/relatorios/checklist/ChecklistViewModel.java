package com.vvm.sh.ui.atividadesPendentes.relatorios.checklist;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.baseDados.entidades.AreaChecklist;
import com.vvm.sh.baseDados.entidades.AreaChecklistResultado;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.baseDados.entidades.QuestionarioChecklistResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.repositorios.ChecklistRepositorio;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Item;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Questao;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.constantes.TiposConstantes;
import com.vvm.sh.util.metodos.ConversorUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ChecklistViewModel extends BaseViewModel {

    private final ChecklistRepositorio checklistRepositorio;

    public MutableLiveData<Tipo> checklist;
    public MutableLiveData<List<Item>> itens;


    public MutableLiveData<List<Tipo>> respostas;
    public MutableLiveData<List<Tipo>> tiposNi;
    public MutableLiveData<List<Tipo>> tiposCategoriasRisco;
    public MutableLiveData<List<Tipo>> tiposUts;
    public MutableLiveData<List<AreaChecklist>> tipoAreas;

    public MutableLiveData<Boolean> completudeChecklist;

    public MutableLiveData<List<Questao>> questionario;
    public QuestionarioChecklistResultado resposta;


    @Inject
    public ChecklistViewModel(ChecklistRepositorio checklistRepositorio){

        this.checklistRepositorio = checklistRepositorio;
        checklist = new MutableLiveData<>();
        itens = new MutableLiveData<>();
        tipoAreas = new MutableLiveData<>();
        questionario = new MutableLiveData<>();
        respostas = new MutableLiveData<>();
        tiposNi = new MutableLiveData<>();

        tiposCategoriasRisco = new MutableLiveData<>();
        tiposUts = new MutableLiveData<>();


        completudeChecklist = new MutableLiveData<>();

    }


    public MutableLiveData<Tipo> observarChecklist(){
        return checklist;
    }




    //--------------------
    //GRAVAR
    //--------------------



    /**
     * Metodo que permite inserir a area geral caso ela não exista
     * @param idAtividade o identificador da atividade
     * @param idChecklist o identificador da checklist
     */
    public void inserirAreaGeral(int idTarefa, int idAtividade, int idChecklist){

        checklistRepositorio.validarAreaGeral(idAtividade, idChecklist)
                .flatMap(new Function<Boolean, SingleSource<?>>() {
                    @Override
                    public SingleSource<?> apply(Boolean existe) throws Exception {

                        if(existe == false){
                            AreaChecklistResultado resultado = new AreaChecklistResultado(idAtividade, idChecklist, Identificadores.Checklist.ID_AREA_GERAL, Identificadores.Checklist.AREA_GERAL_SUB_DESCRICAO);
                            return checklistRepositorio.inserir(resultado);
                        }

                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<Object>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(Object o) {
                                abaterAtividadePendente(checklistRepositorio.resultadoDao, idTarefa, idAtividade);
                                obterAreas(idAtividade, idChecklist);
                            }

                            @Override
                            public void onError(Throwable e) {
                                obterAreas(idAtividade, idChecklist);
                            }
                        }
                );
    }


    /**
     * Metodo que permite inserir uma nova área
     * @param area
     */
    public void inserNovaArea(int idTarefa, AreaChecklistResultado area){

        checklistRepositorio.validarSubDescricaoArea(area.idAtividade, area.idChecklist, area.idArea, area.subDescricao)
                .flatMap(new Function<Boolean, SingleSource<?>>() {
                    @Override
                    public SingleSource<?> apply(Boolean resultado) throws Exception {

                        if(resultado == false){
                            return checklistRepositorio.inserir(area);
                        }

                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<Object>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(Object o) {
                                abaterAtividadePendente(checklistRepositorio.resultadoDao, idTarefa, area.idAtividade);
                                messagemLiveData.setValue(Recurso.successo(true, Sintaxe.Frases.DADOS_GRAVADOS_SUCESSO));
                            }

                            @Override
                            public void onError(Throwable e) {
                                messagemLiveData.setValue(Recurso.erro("A descrição já existe"));
                            }
                        }

                );

    }



    /**
     * Metodo que permite inserir uma nova área
     * @param area
     */
    public void editarArea(int idTarefa, AreaChecklistResultado area){

        checklistRepositorio.validarSubDescricaoArea(area.idAtividade, area.idChecklist, area.idArea, area.subDescricao)
                .flatMap(new Function<Boolean, SingleSource<?>>() {
                    @Override
                    public SingleSource<?> apply(Boolean resultado) throws Exception {

                        if(resultado == false){
                            return checklistRepositorio.atualizar(area);
                        }

                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<Object>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(Object o) {
                                abaterAtividadePendente(checklistRepositorio.resultadoDao, idTarefa, area.idAtividade);
                                messagemLiveData.setValue(Recurso.successo(false, Sintaxe.Frases.DADOS_GRAVADOS_SUCESSO));
                            }

                            @Override
                            public void onError(Throwable e) {
                                messagemLiveData.setValue(Recurso.erro("A descrição já existe"));
                            }
                        }

                );

    }







    public void inserArea(AreaChecklistResultado area){

        checklistRepositorio.inserir(area)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<Long>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(Long aLong) {
                                messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_GRAVADOS_SUCESSO));
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }

                );

    }






    /**
     * Metodo que permite gravar uma questão
     * @param idAtividade o identificador da atividade
     * @param idRegisto o identificador do registo da questão
     * @param resultado os dados da questao
     */
    public void inserir(int idTarefa, int idAtividade, int idRegisto, QuestionarioChecklistResultado resultado) {

        if(idRegisto == 0) {
            checklistRepositorio.inserir(resultado)
                    .flatMap(new Function<Long, SingleSource<?>>() {
                        @Override
                        public SingleSource<?> apply(Long aLong) throws Exception {
                            if(resultado.tipo.equals(Identificadores.Checklist.TIPO_QUESTAO) == true) {
                                return checklistRepositorio.gravarPropostaPlanoAcao(idAtividade, ConversorUtil.converter_long_Para_int(aLong), resultado);
                            }
                            else {
                                return Single.just(aLong);
                            }
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new SingleObserver<Object>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    disposables.add(d);
                                }

                                @Override
                                public void onSuccess(Object o) {
                                    abaterAtividadePendente(checklistRepositorio.resultadoDao, idTarefa, idAtividade);
                                    messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_GRAVADOS_SUCESSO));
                                }

                                @Override
                                public void onError(Throwable e) {

                                }
                            }
                    );
        }
        else{

            resultado.id = idRegisto;

            checklistRepositorio.atualizar(resultado)
                    .flatMap(new Function<Integer, SingleSource<?>>() {
                        @Override
                        public SingleSource<?> apply(Integer integer) throws Exception {

                            if(resultado.tipo.equals(Identificadores.Checklist.TIPO_QUESTAO) == true) {
                                return checklistRepositorio.gravarPropostaPlanoAcao(idAtividade, idRegisto, resultado);
                            }
                            else {
                                return Single.just(integer);
                            }
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new SingleObserver<Object>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    disposables.add(d);
                                }

                                @Override
                                public void onSuccess(Object o) {
                                    abaterAtividadePendente(checklistRepositorio.resultadoDao, idTarefa, idAtividade);
                                    messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_EDITADOS_SUCESSO));
                                }

                                @Override
                                public void onError(Throwable e) {

                                }
                            }
                    );
        }

    }


    /**
     * Metodo que permite gravar a secção como não aplicavel
     * @param registo os dados da seccao
     */
    public void gravarNaoAplicavel(int idTarefa, int idAtividade, Item registo) {

        checklistRepositorio.gravarNaoAplicavel(registo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new CompletableObserver() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onComplete() {
                                abaterAtividadePendente(checklistRepositorio.resultadoDao, idTarefa, idAtividade);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }

                );

    }


    /**
     * Metodo que permite gravar uma imagem
     * @param idTarefa
     * @param idAtividade o identificador da atividade
     * @param resultado os dados da imagem
     */
    public void gravar(int idTarefa, int idAtividade, ImagemResultado resultado) {

        checklistRepositorio.inserir(resultado)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<Long>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(Long aLong) {
                                abaterAtividadePendente(checklistRepositorio.resultadoDao, idTarefa, idAtividade);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }

    //----------------------
    //REMOVER
    //----------------------

    /**
     * Metodo que permite remover uma checklist
     * @param idTarefa
     * @param idAtividade
     * @param idNovaChecklist o identificador da nova checklist a substituir a que será removida
     */
    public void remover(int idTarefa, int idAtividade, int idNovaChecklist) {

        checklistRepositorio.removerChecklist(idAtividade)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new CompletableObserver() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onComplete() {
                                inserirAreaGeral(idTarefa, idAtividade, idNovaChecklist);
                                abaterAtividadePendente(checklistRepositorio.resultadoDao, idTarefa, idAtividade);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }

                );
    }


    /**
     * Metodo que permite remover uma area da checklist
     * @param id o identificador do registo da area
     */
    public void removerArea(int idTarefa, int idAtividade, int id) {

        checklistRepositorio.removerArea(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<List<Integer>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(List<Integer> integers) {
                                messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_REMOVIDOS_SUCESSO));
                                abaterAtividadePendente(checklistRepositorio.resultadoDao, idTarefa, idAtividade);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }

                );

    }


    //--------------------
    //OBTER
    //--------------------


    /**
     * Metodo que permite obter as areas para criar uma nova área
     * @param idChecklist o identificador da checklist
     */
    public void obterAreasChecklist(int idChecklist){

        checklistRepositorio.obterAreasChecklist(idChecklist)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<List<AreaChecklist>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(List<AreaChecklist> areaChecklists) {
                                tipoAreas.setValue(areaChecklists);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }










    public void obterChecklist(int idAtividade){

        checklistRepositorio.obterChecklist(idAtividade)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<Tipo>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(Tipo tipo) {
                                checklist.setValue(tipo);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );

        checklistRepositorio.obterCompletudeChecklist(idAtividade)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<Boolean>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(Boolean registo) {
                                completudeChecklist.setValue(registo);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );

    }




    public void obterAreas(int idAtividade, int idChecklist) {

        showProgressBar(true);


        checklistRepositorio.obterAreas(idAtividade, idChecklist)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<Item>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(List<Item> items) {
                                itens.setValue(items);
                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );

    }


    public void obterSeccoes(int idRegistoArea) {

        showProgressBar(true);


        checklistRepositorio.obterSeccoes(idRegistoArea)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<Item>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(List<Item> items) {
                                itens.setValue(items);
                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        }
                );

    }



    public void obterQuestoes(Item item){

        showProgressBar(true);

        checklistRepositorio.obterQuestoes(item.id, item.uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<Questao>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<Questao> resultado) {

                                questionario.setValue(resultado);
                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                showProgressBar(false);
                            }

                            @Override
                            public void onComplete() {
                                showProgressBar(false);
                            }
                        }

                );

    }


    //--------------------
    //MiSC
    //--------------------



    /**
     * Metodo que permite obter as respostas
     * @param idRegisto o identificador da resposta
     */
    public void obterRespostas(int idRegisto){

        List<Tipo> estado = new ArrayList<>();

        for (Tipo tipo : TiposConstantes.Checklist.RESPOSTAS) {
            estado.add(tipo);
        }

        respostas.setValue(estado);


        List<Tipo> categorias = new ArrayList<>();

        for (Tipo tipo : TiposConstantes.Checklist.CATEGORIAS_RISCO) {
            categorias.add(tipo);
        }

        tiposCategoriasRisco.setValue(categorias);


        checklistRepositorio.obterNI()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<List<Tipo>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(List<Tipo> tipos) {
                                tiposNi.setValue(tipos);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }

                );


        checklistRepositorio.obterUts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<List<Tipo>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(List<Tipo> tipos) {
                                tiposUts.setValue(tipos);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }

                );

        checklistRepositorio.obterQuestao(idRegisto)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<QuestionarioChecklistResultado>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(QuestionarioChecklistResultado questionarioChecklistResultado) {
                                resposta = questionarioChecklistResultado;
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }

                );
    }



}
