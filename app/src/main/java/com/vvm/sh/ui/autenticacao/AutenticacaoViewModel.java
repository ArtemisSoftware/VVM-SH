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

        autenticacaoRepositorio.obterUtilizadores()
                .map(new Function<IUtilizadorListagem, Observable<IUtilizador>>() {
                    @Override
                    public Observable<IUtilizador> apply(IUtilizadorListagem iUtilizadorListagem) throws Exception {
                        return  Observable.fromIterable(iUtilizadorListagem.dadosNovos);
                    }
                })
                .flatMap(new Function<Observable<IUtilizador>, ObservableSource<IUtilizador>>() {
                    @Override
                    public ObservableSource<IUtilizador> apply(Observable<IUtilizador> iUtilizadorObservable) throws Exception {
                        return iUtilizadorObservable;
                    }
                })
                .filter(new Predicate<IUtilizador>() {
                    @Override
                    public boolean test(IUtilizador iUtilizador) throws Exception {

                        if(iUtilizador.id.equals(idUtilizador)){
                            return true;
                        }

                        return false;
                    }
                })

                .toList()
                .map(new Function<List<IUtilizador>, IUtilizador>() {
                    @Override
                    public IUtilizador apply(List<IUtilizador> resposta) throws Exception {

                        IUtilizador resultado = autenticarUtilizador(resposta, palavraChave);

                        if(resultado == null){
                            throw new UtilizadorException(Sintaxe.Alertas.ERRO_AUTENTICACAO);
                        }

                        return resultado;
                    }
                })
                .flatMap(new Function<IUtilizador, SingleSource<?>>() {
                    @Override
                    public SingleSource<?> apply(IUtilizador iUtilizador) throws Exception {
                        Utilizador utilizador = DownloadMapping.INSTANCE.map((IUtilizador) iUtilizador);
                        return autenticacaoRepositorio.inserir(utilizador);
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
                                messagemLiveData.setValue(Recurso.successo());
                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                messagemLiveData.setValue(Recurso.erro(e.getMessage()));
                                showProgressBar(false);
                            }
                        }
                );

    }



    /**
     * Metodo que permite verificar as credenciais do utilizador
     * @param resposta os dados da api
     * @param palavraChave a palavra chave do utilizador
     * @return  um utilizador
     */
    private IUtilizador autenticarUtilizador(List<IUtilizador> resposta, String palavraChave){

        IUtilizador utilizador = null;
        String cap = null, email = null;

        for (IUtilizador registo : resposta) {

            String messageDigest = Hasher.Companion.hash(palavraChave, HashType.MD5);

            if(registo.ativo == true & registo.palavraChave.equals(messageDigest) == true){

                if(registo.email != null){
                    email = registo.email;
                }
                if(registo.cap != null){
                    cap = registo.cap;
                }
            }
            else{
                return null;
            }
        }

        if(resposta.size() != 0){
            utilizador = resposta.get(0);
            utilizador.api = 0;
            utilizador.email = email;
            utilizador.cap = cap;
        }


        return utilizador;
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
