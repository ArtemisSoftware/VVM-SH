package com.vvm.sh.ui.ocorrencias;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.repositorios.OcorrenciaRepositorio;
import com.vvm.sh.baseDados.entidades.Ocorrencia;
import com.vvm.sh.baseDados.entidades.OcorrenciaHistorico;
import com.vvm.sh.baseDados.entidades.OcorrenciaResultado;
import com.vvm.sh.ui.ocorrencias.modelos.OcorrenciaBase;
import com.vvm.sh.ui.ocorrencias.modelos.OcorrenciaRegisto;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.ResultadoId;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OcorrenciasViewModel extends BaseViewModel {

    private final OcorrenciaRepositorio ocorrenciaRepositorio;

    public MutableLiveData<List<Ocorrencia>> ocorrencias;
    public MutableLiveData<List<Tipo>> ocorrenciasGeral;
    public MutableLiveData<List<OcorrenciaRegisto>> ocorrenciasInseridas;


    public MutableLiveData<List<OcorrenciaRegisto>> ocorrenciasRegistos;
    public MutableLiveData<OcorrenciaRegisto> ocorrencia;

    public MutableLiveData<List<Tipo>> dias;

    public MutableLiveData<List<OcorrenciaHistorico>> historico;

    @Inject
    public OcorrenciasViewModel(OcorrenciaRepositorio ocorrenciaRepositorio){

        this.ocorrenciaRepositorio = ocorrenciaRepositorio;
        ocorrencias = new MutableLiveData<>();
        ocorrenciasGeral = new MutableLiveData<>();
        ocorrenciasRegistos = new MutableLiveData<>();
        ocorrenciasInseridas = new MutableLiveData<>();
        ocorrencia = new MutableLiveData<>();
        dias = new MutableLiveData<>();
        historico = new MutableLiveData<>();
    }


    //-----------------------
    //GRAVAR
    //-----------------------


    /**
     * Metodo que permite gravar um registo
     * @param registo os dados do registo
     */
    public void gravar(OcorrenciaResultado registo) {

        if(ocorrencia.getValue().resultado.id == 0){

            ocorrenciaRepositorio.inserir(registo)
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
                                    messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_GRAVADOS_SUCESSO));
                                    gravarResultado(ocorrenciaRepositorio.resultadoDao, registo.idTarefa, ResultadoId.OCORRENCIA);
                                }

                                @Override
                                public void onError(Throwable e) {

                                }
                            }
                    );
        }
        else{
            ocorrenciaRepositorio.atualizar(registo)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new SingleObserver<Integer>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    disposables.add(d);
                                }

                                @Override
                                public void onSuccess(Integer integer) {
                                    messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_EDITADOS_SUCESSO));
                                    gravarResultado(ocorrenciaRepositorio.resultadoDao, registo.idTarefa, ResultadoId.OCORRENCIA);
                                }

                                @Override
                                public void onError(Throwable e) {

                                }
                            }
                    );
        }

    }



    //---------------------
    //REMOVER
    //---------------------


    /**
     * Metodo que permite remover uma ocorrencia registada
     * @param idTarefa o identificador da tarefa
     * @param id o identificador da ocorrencia
     */
    public void remover(int idTarefa, int id) {

        ocorrenciaRepositorio.remover(idTarefa, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(Integer integer) {

                                gravarResultado(ocorrenciaRepositorio.resultadoDao, idTarefa, ResultadoId.OCORRENCIA);
                            }

                            @Override
                            public void onError(Throwable e) {
                                messagemLiveData.setValue(Recurso.erro(e.getMessage()));
                            }
                        }

                );

    }



    //------------------------
    //OBTER
    //------------------------


    /**
     * Metodo que permite obter as ocorrencias associadas a uma tarefa
     * @param idTarefa o identificador da tarefa
     */
    public void obterOcorrencias(int idTarefa){

        showProgressBar(true);
        obterOcorrenciasInseridas(idTarefa);
        obterOpcoesRegistos();

        ocorrenciaRepositorio.obterOcorrencias(idTarefa)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<List<Ocorrencia>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(List<Ocorrencia> resultado) {
                                ocorrencias.setValue(resultado);
                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                showProgressBar(false);
                            }
                        }
                );

    }


    /**
     * Metodo que permite obter as ocorrencias inseridas
     * @param idTarefa o identificador da tarefa
     */
    private void obterOcorrenciasInseridas(int idTarefa) {

        showProgressBar(true);

        ocorrenciaRepositorio.obterOcorrenciasRegistadas(idTarefa)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<OcorrenciaRegisto>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<OcorrenciaRegisto> resultado) {

                                ocorrenciasInseridas.setValue(resultado);
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


    /**
     * Metodo que permite obter os registos de ocorrencias
     * @param idTarefa o identificador da tarefa
     */
    public void obterRegistosOcorrencias(int idTarefa){

        showProgressBar(true);

        ocorrenciaRepositorio.obterOcorrencias()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<List<Tipo>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(List<Tipo> resultado) {
                                ocorrenciasGeral.setValue(resultado);
                                showProgressBar(false);

                                if(resultado.size() != 0) {
                                    obterRegistosOcorrencias(idTarefa, resultado.get(0).id);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                showProgressBar(false);
                            }
                        }
                );

    }


    /**
     * Metodo que permite obter os registos de ocorrencias
     * @param idTarefa o identificador da tarefa
     * @param id o identificador do grupo de ocorrencias
     */
    public void obterRegistosOcorrencias(int idTarefa, int id) {

        showProgressBar(true);

        ocorrenciaRepositorio.obterRegistoOcorrencias(idTarefa, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<List<OcorrenciaBase>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(List<OcorrenciaBase> resultado) {

                                List<OcorrenciaRegisto> resultados = new ArrayList<>();

                                for(OcorrenciaBase item : resultado){
                                    resultados.add(new OcorrenciaRegisto(item));
                                }

                                ocorrenciasRegistos.setValue(resultados);
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


    /**
     * Metodo que permite obter o registo de ocorrencia
     * @param idTarefa o identificador da tarefa
     * @param id o identificador do grupo de ocorrencias
     * @param idTipo o identificador do tipo de ocorrencia
     */
    public void obterOcorrencia(int idTarefa, int id, int idTipo) {

        showProgressBar(true);

        ocorrenciaRepositorio.obterRegistoOcorrencia(idTarefa, id, idTipo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<OcorrenciaBase>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(OcorrenciaBase ocorrenciaBase) {

                                OcorrenciaRegisto resultado = new OcorrenciaRegisto(ocorrenciaBase);
                                ocorrencia.setValue(resultado);
                                obterDias(resultado);
                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                showProgressBar(false);
                            }
                        }

                );

    }


    /**
     * Metodo que permite obter o historicao
     * @param id o identificador da ocorrencia
     */
    public void obterHistorico(int id) {

        showProgressBar(true);

        ocorrenciaRepositorio.obterHistorico(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<List<OcorrenciaHistorico>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(List<OcorrenciaHistorico> resultado) {
                                historico.setValue(resultado);
                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                showProgressBar(false);
                            }
                        }

                );
    }





    //------------------
    //MISC
    //------------------



    /**
     * Metodo que permite obter as opções dos dias de ocorrencia
     * @param resultado o registo
     */
    private void obterDias(OcorrenciaRegisto resultado){

        String dias [] = resultado.tipo.detalhe.split(",");

        List<Tipo> registos = new ArrayList<>();

        for (int index = 0; index < dias.length; ++index) {
            registos.add(new Tipo(index, dias[index]));
        }

        this.dias.setValue(registos);
    }





}
