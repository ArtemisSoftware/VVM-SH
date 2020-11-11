package com.vvm.sh.ui.imagens;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.repositorios.AtividadePendenteRepositorio;
import com.vvm.sh.repositorios.ImagemRepositorio;
import com.vvm.sh.ui.imagens.modelos.Galeria;
import com.vvm.sh.ui.imagens.modelos.ImagemRegisto;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.DiretoriasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ImagemViewModel extends BaseViewModel {

    private final ImagemRepositorio imagemRepositorio;

    public MutableLiveData<List<Tipo>> galerias;
    public MutableLiveData<List<String>> caminhos;
    public MutableLiveData<List<ImagemRegisto>> imagens;

    @Inject
    public ImagemViewModel(ImagemRepositorio imagemRepositorio){

        this.imagemRepositorio = imagemRepositorio;
        galerias = new MutableLiveData<>();
        caminhos = new MutableLiveData<>();
        imagens = new MutableLiveData<>();
    }


    //---------------
    //GRAVAR
    //---------------

    /**
     * Metodo que permite gravar a capa do relatorio
     * @param idTarefa o identificador da tarefa
     * @param idAtividade
     * @param idImagem
     */
    public void gravarCapaRelatorio(int idTarefa, int idAtividade, int idImagem) {

        imagemRepositorio.fixarCapaRelatorio(idTarefa, idImagem)
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
                                messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.REGISTADA_CAPA_RELATORIO_SUCESSO));
                                abaterAtividadePendente(imagemRepositorio.resultadoDao, idTarefa, idAtividade);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }

                );
    }

    //---------------
    //Remover
    //---------------

    /**
     * Metodo que permite remover a capa do relatorio
     * @param idTarefa
     * @param idAtividade
     */
    public void removerCapaRelatorio(int idTarefa, int idAtividade){

        imagemRepositorio.removerCapaRelatorio(idTarefa)
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
                                messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.REMOVID_CAPA_RELATORIO_SUCESSO));
                                abaterAtividadePendente(imagemRepositorio.resultadoDao, idTarefa, idAtividade);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }

                );
    }


    /**
     * Metodo que permite remover uma imagem
     * @param idTarefa
     * @param idAtividade
     * @param imagemResultado
     */
    public void removerImagem(int idTarefa, int idAtividade, ImagemResultado imagemResultado){

        imagemRepositorio.remover(imagemResultado)
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
                                messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.IMAGEM_REMOVIDA_SUCESSO));
                                abaterAtividadePendente(imagemRepositorio.resultadoDao, idTarefa, idAtividade);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }
    //---------------
    //OBTER
    //---------------


    /**
     * Metodo que permite obter as imagens
     */
    public void obterGaleria(Galeria galeria) {

        Observable<List<ImagemRegisto>> observable = null;

        switch (galeria.idGaleria){

            case Galeria.GALERIA_LEVANTAMENTO:

                observable = imagemRepositorio.obterImagemLevantamento(galeria.id);
                break;

            case Identificadores.Imagens.IMAGEM_CHECKLIST:

                observable = imagemRepositorio.obterGaleria(galeria.id, galeria.idGaleria);
                break;

            case Galeria.GALERIA_CAPA_RELATORIO:

                observable = imagemRepositorio.obterCapasRelatorio(galeria.id);
                break;

            default:
                break;
        }

        if(observable != null) {

            observable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(

                            new Observer<List<ImagemRegisto>>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    disposables.add(d);
                                }

                                @Override
                                public void onNext(List<ImagemRegisto> registos) {
                                    imagens.setValue(registos);
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

    }


    //---------------
    //Misc
    //---------------


    public void obterDiretoriasImagens(){

        List<String> nomeDiretorias = new ArrayList<>();
        List<Tipo> diretorias = new ArrayList<>();
//        diretorias.add(new Tipo(1, "Fotos", DiretoriasUtil.obterCaminho("Pictures")));
//


        String picturesDir = DiretoriasUtil.obterCaminho("Pictures");
        String cameraDir = DiretoriasUtil.obterCaminho("DCIM" + File.separator + "Camera");

        if (DiretoriasUtil.getDirectoryPaths(picturesDir) != null) {
            nomeDiretorias = DiretoriasUtil.getDirectoryPaths(picturesDir);
        }

        diretorias.add(new Tipo(1, "Camera", cameraDir));

        for (int i = 0; i < nomeDiretorias.size(); i++) {

            int index = nomeDiretorias.get(i).lastIndexOf("/");
            String string = nomeDiretorias.get(i).substring(index);

            diretorias.add(new Tipo((1 + index), string, nomeDiretorias.get(i)));
        }

        galerias.setValue(diretorias);

    }


    public void obterCaminhoImagens(List<String> filePaths){
        caminhos.setValue(filePaths);
    }


}
