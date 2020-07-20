package com.vvm.sh.ui.autenticacao;

import com.vvm.sh.api.modelos.UtilizadorResposta;
import com.vvm.sh.api.modelos.UtilizadorResultado;
import com.vvm.sh.repositorios.AutenticacaoRepositorio;
import com.vvm.sh.ui.autenticacao.modelos.Utilizador;
import com.vvm.sh.util.ModelMapping;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AutenticacaoViewModel extends BaseViewModel {


    private final AutenticacaoRepositorio autenticacaoRepositorio;


    @Inject
    public AutenticacaoViewModel(AutenticacaoRepositorio autenticacaoRepositorio){

        this.autenticacaoRepositorio = autenticacaoRepositorio;
    }


    /**
     * Metodo que permite autenticar o utilizador
     * @param idUtilizador o identificador do utilizador
     * @param palavraChave a palavra chave do utilizador
     */
    public void autenticar(String idUtilizador, String palavraChave) {

        showProgressBar(true);

        autenticacaoRepositorio.obterUtilizadores()
                .toObservable()
                .map(new Function<UtilizadorResposta, Object>() {
                    @Override
                    public UtilizadorResultado apply(UtilizadorResposta resposta) throws Exception {

                        UtilizadorResultado resultado = autenticarUtilizador(resposta, idUtilizador, palavraChave);

                        if(resultado == null){
                            //TODO: criar excepcao
                            throw new Exception("lololololo");
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

                                //TODO: adicionar mensagem de erro

                                messagemLiveData.setValue(Recurso.erro("Erro na autenticação"));
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

            //TODO: adicionar a verificacao da palavra chave

            if(registo.id.equals(idUtilizador) == true & registo.ativo == true){

                return registo;
            }
        }

        return null;
    }


}
