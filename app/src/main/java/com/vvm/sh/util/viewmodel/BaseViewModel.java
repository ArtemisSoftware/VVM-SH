package com.vvm.sh.util.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.titan.pdfdocumentlibrary.bundle.Template;
import com.vvm.sh.api.modelos.pedido.Codigo;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.entidades.CategoriaProfissionalResultado;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.documentos.DadosTemplate;
import com.vvm.sh.documentos.modelos.DocumentoPdf;
import com.vvm.sh.servicos.ResultadoAsyncTask;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.ResultadoId;
import com.vvm.sh.util.constantes.AppConfig;
import com.vvm.sh.util.constantes.TiposConstantes;
import com.vvm.sh.util.excepcoes.MetodoWsInvalidoException;
import com.vvm.sh.util.excepcoes.RespostaWsInvalidaException;
import com.vvm.sh.util.excepcoes.TipoInexistenteException;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel extends ViewModel implements DocumentoPdf {


    protected final CompositeDisposable disposables;
    protected MutableLiveData<Recurso> messagemLiveData;


    public MutableLiveData<Integer> loading;



    public MutableLiveData<List<Tipo>> estados;



    @Inject
    protected VvmshBaseDados vvmshBaseDados;

    protected int limite = AppConfig.NUMERO_RESULTADOS_QUERY;



    public BaseViewModel() {

        this.disposables = new CompositeDisposable();
        messagemLiveData = new MutableLiveData<>();
        loading = new MutableLiveData<>();
        estados = new MutableLiveData<>();

        showProgressBar(false);
    }



    public MutableLiveData<Recurso> observarMessagem(){
        return messagemLiveData;
    }


    /**
     * Metodo que permite gravar o resultado de forma aos dados poderem ser enviados para o ws
     * @param resultadoDao
     * @param idTarefa o identificador da tarefa
     * @param idResultado o identificador do resultado
     */
    protected void gravarResultado(ResultadoDao resultadoDao, int idTarefa, ResultadoId idResultado){

        ResultadoAsyncTask servico = new ResultadoAsyncTask(vvmshBaseDados, resultadoDao);
        servico.execute(new Resultado(idTarefa, idResultado));
    }


    protected void abaterAtividadePendente(ResultadoDao resultadoDao, int idTarefa, int idAtividade){

        ResultadoAsyncTask servico = new ResultadoAsyncTask(vvmshBaseDados, resultadoDao, idAtividade);
        servico.execute(new Resultado(idTarefa, ResultadoId.ATIVIDADE_PENDENTE));
    }





    /**
     * Metodo que permite obter os generos
     */
    protected void obterGeneros(MutableLiveData<List<Tipo>> generos){

        List<Tipo> estado = new ArrayList<>();

        estado.add(TiposConstantes.GENERO_MASCULINO);
        estado.add(TiposConstantes.GENERO_FEMININO);
        generos.setValue(estado);

    }

    /**
     * Metodo que permite obter as opcoes dos registos
     */
    protected void obterOpcoesRegistos() {
        List<Tipo> estado = new ArrayList<>();

        estado.add(TiposConstantes.OpcoesRegistos.CONSULTAR);
        estado.add(TiposConstantes.OpcoesRegistos.NOVOS_REGISTOS);
        estados.setValue(estado);
    }


    protected void obterOpcoes(MutableLiveData<List<Tipo>> liveData, Tipo [] opcoes){

        List<Tipo> estado = new ArrayList<>();

        for (Tipo tipo : opcoes) {
            estado.add(tipo);
        }

        liveData.setValue(estado);
    }



    public ArrayList<Integer> obterRegistosSelecionados(List<Tipo> registos){

        ArrayList<Integer> resultado = new ArrayList<>();

        if(registos != null) {

            for (Tipo item : registos) {
                resultado.add(item.id);
            }
        }

        return resultado;
    }







    protected List<CategoriaProfissionalResultado> ObterCategoriasProfissionais(List<Tipo> registo, int id, int origem){

        List<CategoriaProfissionalResultado> categorias = new ArrayList<>();
        for (Tipo categoria : registo) {
            categorias.add(new CategoriaProfissionalResultado(id, categoria.id, origem));
        }

        return categorias;
    }



    protected void showProgressBar(boolean visible) {
        loading.setValue(visible ? View.VISIBLE : View.INVISIBLE);
    }


    /**
     * Metodo que permite formatar um erro
     * @param e
     */
    protected void formatarErro(Throwable e){

        Gson gson = new GsonBuilder().create();

        if (e instanceof RespostaWsInvalidaException){

            Codigo codigo = gson.fromJson(e.getMessage(), Codigo.class);

            messagemLiveData.setValue(Recurso.erro(codigo, "Upload/Download"));
        }
        else if (e instanceof MetodoWsInvalidoException){

            Codigo codigo = gson.fromJson(e.getMessage(), Codigo.class);
            messagemLiveData.setValue(Recurso.erro(codigo, "Comunicação"));
        }
        else if (e instanceof TipoInexistenteException){

            messagemLiveData.setValue(Recurso.erro(e.getMessage(), "Atualizacoes"));
        }
        else {
            messagemLiveData.setValue(Recurso.erro(e.getMessage()));
        }
    }

    @Override
    public Template obterTemplate(Context contexto, int idTarefa, int idAtividade, DadosTemplate registo) {
        return null;
    }

//    private void preVisualizarPdf(Context contexto, int idTarefa, DadosTemplate registo){
//
//        Template template = obterTemplate();
//
//        if(template != null) {
//
//            DocumentoPdfAsyncTask servico = new DocumentoPdfAsyncTask(contexto, template);
//            servico.execute();
//        }
//    }



    @Override
    protected void onCleared() {
        disposables.clear();
    }

}

