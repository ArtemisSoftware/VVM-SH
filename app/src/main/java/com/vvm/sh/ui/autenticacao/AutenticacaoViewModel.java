package com.vvm.sh.ui.autenticacao;

import androidx.lifecycle.MutableLiveData;

import com.himanshurawat.hasher.HashType;
import com.himanshurawat.hasher.Hasher;
import com.vvm.sh.api.modelos.pedido.IUtilizador;
import com.vvm.sh.api.modelos.pedido.IUtilizadorListagem;
import com.vvm.sh.repositorios.AutenticacaoRepositorio;
import com.vvm.sh.baseDados.entidades.Utilizador;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.excepcoes.UtilizadorException;
import com.vvm.sh.util.mapeamento.DownloadMapping;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class AutenticacaoViewModel extends BaseViewModel {


    private final AutenticacaoRepositorio autenticacaoRepositorio;

    public MutableLiveData<Utilizador> utilizador;


    @Inject
    public AutenticacaoViewModel(AutenticacaoRepositorio autenticacaoRepositorio){

        this.autenticacaoRepositorio = autenticacaoRepositorio;
        utilizador = new MutableLiveData<>();
    }


    /**
     * Metodo que permite autenticar o utilizador
     * @param idUtilizador o identificador do utilizador
     * @param palavraChave a palavra chave do utilizador
     */
    public void autenticar(String idUtilizador, String palavraChave) {

        showProgressBar(true);

        autenticacaoRepositorio.autenticarUtilizador_(idUtilizador, palavraChave)
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
                                messagemLiveData.setValue(Recurso.successo());
                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                formatarErro(e);
                                showProgressBar(false);
                            }
                        }
                );

    }



    //-----------------------
    //OBTER
    //-----------------------


    /**
     * Metodo que permite obter os dados do utilizador
     * @param idUtilizador o identificador do utilizador
     */
    public void obterUtilizador(String idUtilizador){

        showProgressBar(true);

        autenticacaoRepositorio.obterUtilizador(idUtilizador)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<Utilizador>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(Utilizador resultado) {
                                utilizador.setValue(resultado);
                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                messagemLiveData.setValue(Recurso.erro(Sintaxe.Alertas.DADOS_INEXISTENTES));
                                showProgressBar(false);
                            }
                        }
                );
    }


}
