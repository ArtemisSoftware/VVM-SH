package com.vvm.sh.ui.pesquisa;

import androidx.lifecycle.MutableLiveData;

import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.baseDados.entidades.TipoNovo;
import com.vvm.sh.baseDados.entidades.VerificacaoEquipamentoResultado;
import com.vvm.sh.repositorios.EquipamentoRepositorio;
import com.vvm.sh.repositorios.PesquisaRepositorio;
import com.vvm.sh.repositorios.TiposRepositorio;
import com.vvm.sh.ui.atividadesPendentes.relatorios.equipamentos.modelos.Equipamento;
import com.vvm.sh.ui.pesquisa.modelos.Medida;
import com.vvm.sh.ui.pesquisa.modelos.Pesquisa;
import com.vvm.sh.ui.pesquisa.modelos.PesquisaTipos;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.AppConfig;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.excepcoes.TipoInexistenteException;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PesquisaViewModel extends BaseViewModel {

    private final TiposRepositorio tiposRepositorio;
    private final EquipamentoRepositorio equipamentoRepositorio;
    private final PesquisaRepositorio pesquisaRepositorio;
    private final int idApi;

    public MutableLiveData<List<Tipo>> tiposSelecionados;
    public MutableLiveData<List<Tipo>> tipos;


    public MutableLiveData<List<Equipamento>> equipamentos;
    public MutableLiveData<List<Equipamento>> equipamentosSelecionados;

    public MutableLiveData<List<String>> equipamentosRegistados;



    public MutableLiveData<List<Medida>> medidas;


    private int pagina = 0;
    private int limite = AppConfig.NUMERO_RESULTADOS_QUERY;

    @Inject
    public PesquisaViewModel(int idApi, TiposRepositorio tiposRepositorio, EquipamentoRepositorio equipamentoRepositorio, PesquisaRepositorio pesquisaRepositorio){

        this.equipamentoRepositorio = equipamentoRepositorio;
        this.tiposRepositorio = tiposRepositorio;
        this.pesquisaRepositorio = pesquisaRepositorio;
        this.idApi = idApi;

        tiposSelecionados = new MutableLiveData<>();
        tipos = new MutableLiveData<>();
        equipamentos = new MutableLiveData<>();
        equipamentosSelecionados = new MutableLiveData<>();
        equipamentosRegistados = new MutableLiveData<>();
        medidas = new MutableLiveData<>();
    }



    public MutableLiveData<List<String>> observarEquipamentos(){
        return equipamentosRegistados;
    }


    /**
     * Metodo que permite gravar um novo tipo
     * @param registo
     */
    public void gravar(int idProvisorio, TipoNovo registo) {

        equipamentoRepositorio.validarEquipamento(registo.descricao)
                .flatMap(new Function<Boolean, SingleSource<?>>() {
                    @Override
                    public SingleSource<?> apply(Boolean resultado) throws Exception {
                        if(resultado == true) {
                            throw new TipoInexistenteException(Sintaxe.Alertas.EQUIPAMENTO_REGISTADO);
                        }
                        else{

                            registo.idProvisorio = idProvisorio;
                            return equipamentoRepositorio.inserir(registo);
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
                                messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.EQUIPAMENTO_REGISTADO_SUCESSO ));
                            }

                            @Override
                            public void onError(Throwable e) {
                                formatarErro(e);
                            }
                        }

                );
    }


    /**
     * Metodo que permite gravar os equipamentos selecionados
     * @param idTarefa o identificador da tarefa
     * @param idAtividade o identificador da atividade
     * @param itens os dados a gravar
     */
    public void gravar(int idTarefa, int idAtividade, List<VerificacaoEquipamentoResultado> itens) {

        equipamentoRepositorio.inserir(idAtividade, itens)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<List<Object>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(List<Object> objects) {
                                messagemLiveData.setValue(Recurso.successo(Sintaxe.Frases.DADOS_GRAVADOS_SUCESSO));
                                abaterAtividadePendente(equipamentoRepositorio.resultadoDao, idTarefa, idAtividade);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }
                );
    }


    //-----------------------
    //OBTER - Equipamentos
    //-----------------------

    public void obterEquipamentos(List<String> registos, boolean reiniciar){

        if(reiniciar == true){
            limite = AppConfig.NUMERO_RESULTADOS_QUERY;
            //equipamentosRegistados.setValue(new ArrayList<>());
        }

        obterEquipamentos(registos);
    }



    /**
     * Metodo que permite obter os equipamentos
     * @param idAtividade o identificador da atividade
     */
    public void obterRegistadosEquipamentos(int idAtividade) {

        equipamentoRepositorio.obterEquipamentos(idAtividade)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new MaybeObserver<List<String>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(List<String> equipamentos) {
                                equipamentosRegistados.setValue(equipamentos);
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




    /**
     * Metodo que permite carregar mais medidas
     */
    public void carregarEquipamentos(List<String> registos){
        limite += AppConfig.NUMERO_RESULTADOS_QUERY;
        obterEquipamentos(registos);
    }



    /**
     * Metodo que permite obter os equipamentos
     * @param registos os registos exitentes
     */
    private void obterEquipamentos(List<String> registos) {

        showProgressBar(true);

        Observable<PesquisaEquipamentos> observable = Observable.zip(
                equipamentoRepositorio.obterEquipamentos_Excluir(registos, limite),
                equipamentoRepositorio.obterEquipamentos_Incluir(registos),
                new BiFunction<List<Equipamento>, List<Equipamento>, PesquisaEquipamentos>() {
                    @Override
                    public PesquisaEquipamentos apply(List<Equipamento> tipos, List<Equipamento> tiposSelecionados) throws Exception {
                        return new PesquisaEquipamentos(tipos, tiposSelecionados);
                    }
                }
        );

        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new Observer<PesquisaEquipamentos>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onNext(PesquisaEquipamentos registos) {

                                equipamentos.setValue(registos.registos);
                                equipamentosSelecionados.setValue(registos.registado);
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


    public void carregarEquipamentos(List<String> registos, String texto){
        limite += AppConfig.NUMERO_RESULTADOS_QUERY;
        pesquisarEquipamento(registos, texto);
    }


    /**
     * Metodo que permite pesquisar um equipamento
     * @param registos os registos
     * @param pesquisa
     */
    private void pesquisarEquipamento(List<String> registos, String pesquisa) {

        equipamentoRepositorio.obterEquipamentos_Excluir(registos, pesquisa, limite)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new MaybeObserver<List<Equipamento>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(List<Equipamento> registos) {
                                equipamentos.setValue(registos);
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
















    public void obterRegistos(Pesquisa pesquisa, boolean reiniciar){

        if(reiniciar == true){
            limite = AppConfig.NUMERO_RESULTADOS_QUERY;
            tipos.setValue(new ArrayList<>());
        }

        obterRegistos_(pesquisa.metodo, pesquisa.registosSelecionados);
    }

    public void carregarRegistos(Pesquisa pesquisa){
        limite += AppConfig.NUMERO_RESULTADOS_QUERY;
        obterRegistos_(pesquisa.metodo, pesquisa.registosSelecionados);
    }

    private void obterRegistos_(String metodo, List<Integer> registos){

        pesquisaRepositorio.obterRegistos(metodo, registos, limite)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<PesquisaTipos>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(PesquisaTipos pesquisaTipos) {
                                adicionarRegistos(pesquisaTipos.registos);
                                tiposSelecionados.setValue(pesquisaTipos.registado);
                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                showProgressBar(false);
                            }
                        }
                );
    }




    public void obterPesquisa(Pesquisa pesquisa, String texto, boolean reiniciar){

        if(reiniciar == true){
            limite = AppConfig.NUMERO_RESULTADOS_QUERY;
            tipos.setValue(new ArrayList<>());
        }

        pesquisar(pesquisa.metodo, pesquisa.registosSelecionados, texto);
    }

    public void carregarPesquisa(Pesquisa pesquisa, String texto){
        limite += AppConfig.NUMERO_RESULTADOS_QUERY;
        pesquisar(pesquisa.metodo, pesquisa.registosSelecionados, texto);
    }

    private void pesquisar(String metodo, List<Integer> registos, String texto) {

        pesquisaRepositorio.pesquisar(metodo, registos, texto, limite)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<PesquisaTipos>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(PesquisaTipos registo) {
                                tiposSelecionados.setValue(registo.registado);
                                adicionarRegistos(registo.registos);
                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }

//                        new MaybeObserver<List<Tipo>>() {
//                            @Override
//                            public void onSubscribe(Disposable d) {
//                                disposables.add(d);
//                            }
//
//                            @Override
//                            public void onSuccess(List<Tipo> registos) {
//                                adicionarRegistos(registos);
//                                showProgressBar(false);
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                showProgressBar(false);
//                            }
//
//                            @Override
//                            public void onComplete() {
//                                showProgressBar(false);
//                            }
//                        }

                );

    }





    //--------------------
    //Pesquisar medidas
    //--------------------



    /**
     * Metodo que permite medidas
     * @param pesquisa
     */
    public void obterMedidas(Pesquisa pesquisa){

        if(pesquisa.codigo != null) {
            obterMedidas(pesquisa.metodo, pesquisa.codigo, pesquisa.registosSelecionados);
        }
        else if(pesquisa.idPai != null) {
            obterMedidas(pesquisa.metodo, pesquisa.registosSelecionados, pesquisa.idPai);
        }
        else{
            obterMedidas(pesquisa.metodo, pesquisa.registosSelecionados);
        }
    }


    /**
     * Metodo que permite carregar mais medidas
     * @param pesquisa
     */
    public void carregarMedidas(Pesquisa pesquisa){

        pagina += AppConfig.NUMERO_RESULTADOS_QUERY;
        obterMedidas(pesquisa);
    }




    /**
     * Metodo que permite obter as medidas
     * @param metodo
     * @param registos os registos a excluir
     * @param idPai
     */
    private void obterMedidas(String metodo, List<Integer> registos, String idPai){

        showProgressBar(true);

        pesquisaRepositorio.obterMedidas(metodo, registos, idPai, pagina)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<List<Medida>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(List<Medida> registos) {
                                adicionarMedidas(registos);
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
     * Metodo que permite obter as medidas
     * @param metodo o nome do metodo das medidas
     * @param codigo o codigo das medidas
     * @param registos os registos a excluir
     */
    private void obterMedidas(String metodo, String codigo, List<Integer> registos){

        showProgressBar(true);

        pesquisaRepositorio.obterMedidas(metodo, codigo, registos, pagina)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<List<Medida>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(List<Medida> registos) {
                                adicionarMedidas(registos);
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
     * Metodo que permite obter as medidas
     * @param metodo o nome do metodo das medidas
     * @param registos os registos a excluir
     */
    private void obterMedidas(String metodo, List<Integer> registos){

        showProgressBar(true);

        pesquisaRepositorio.obterMedidas(metodo, registos, pagina)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        new SingleObserver<List<Medida>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposables.add(d);
                            }

                            @Override
                            public void onSuccess(List<Medida> registos) {
                                adicionarMedidas(registos);
                                showProgressBar(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                showProgressBar(false);
                            }
                        }

                );
    }












    //----------------------
    //
    //----------------------


    /**
     * Metodo que permite adicionar novas medidas
     * @param registos as novas medidas a adicionar
     */
    private void adicionarMedidas(List<Medida> registos){

        List<Medida> resultados = medidas.getValue();

        if(resultados == null){
            resultados = new ArrayList<>();
        }

        resultados.addAll(registos);
        medidas.setValue(resultados);
    }

    /**
     * Metodo que permite adicionar novos registos
     * @param registos as novos registos a adicionar
     */
    private void adicionarRegistos(List<Tipo> registos){
        tipos.setValue(registos);
//        List<Tipo> resultados = tipos.getValue();
//
//        if(resultados == null){
//            tipos.setValue(registos);
//        }
//        else{
//            if(registos.size() == resultados.size()){
//                return;
//            }
//            else{
//                tipos.setValue(registos);
//            }
//        }
    }

    //----------------------
    //classe
    //----------------------



    private class PesquisaEquipamentos{

        List<Equipamento> registos;
        List<Equipamento> registado;

        public PesquisaEquipamentos(List<Equipamento> registos, List<Equipamento> registado) {
            this.registos = registos;
            this.registado = registado;
        }
    }

}
