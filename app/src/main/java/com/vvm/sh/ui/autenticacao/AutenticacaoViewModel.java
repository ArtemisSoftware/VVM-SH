package com.vvm.sh.ui.autenticacao;

import androidx.lifecycle.MutableLiveData;

import com.himanshurawat.hasher.HashType;
import com.himanshurawat.hasher.Hasher;
import com.vvm.sh.api.modelos.UtilizadorResposta;
import com.vvm.sh.api.modelos.UtilizadorResultado;
import com.vvm.sh.repositorios.AutenticacaoRepositorio;
import com.vvm.sh.baseDados.entidades.Utilizador;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.excepcoes.UtilizadorException;
import com.vvm.sh.util.mapeamento.ModelMapping;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
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

        autenticacaoRepositorio.obterUtilizadores().toObservable()
                .map(new Function<UtilizadorResposta, Object>() {
                    @Override
                    public UtilizadorResultado apply(UtilizadorResposta resposta) throws Exception {

                        UtilizadorResultado resultado = autenticarUtilizador(resposta, idUtilizador, palavraChave);

                        if(resultado == null){
                            throw new UtilizadorException(Sintaxe.Alertas.ERRO_AUTENTICACAO);
                        }

                        return resultado;
                    }
                })

                .flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Object o) throws Exception {

                        Utilizador utilizador = ModelMapping.INSTANCE.map((UtilizadorResultado) o);
                        return autenticacaoRepositorio.inserir(utilizador).toObservable();

                    }
                })

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(

                        new Observer<Object>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(Object o) {
                                messagemLiveData.setValue(Recurso.successo());
                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {

                                messagemLiveData.setValue(Recurso.erro(e.getMessage()));
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
     * Metodo que permite verificar as credenciais do utilizador
     * @param resposta os dados da api
     * @param idUtilizador o identificador do utilizador
     * @param palavraChave a palavra chave do utilizador
     * @return um utilizador
     */
    private UtilizadorResultado autenticarUtilizador(UtilizadorResposta resposta, String idUtilizador, String palavraChave){

        for (UtilizadorResultado registo : resposta.dadosNovos) {

            String messageDigest = Hasher.Companion.hash(palavraChave, HashType.MD5);

            if(registo.id.equals(idUtilizador) == true & registo.ativo == true & registo.palavraChave.equals(messageDigest) == true){

                return registo;
            }
        }

        return null;
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
                            public void onSuccess(Utilizador utilizador) {
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
