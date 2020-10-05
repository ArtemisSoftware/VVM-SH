package com.vvm.sh.util.viewmodel;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vvm.sh.api.modelos.pedido.Codigo;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.dao.ResultadoDao;
import com.vvm.sh.baseDados.entidades.CategoriaProfissionalResultado;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.servicos.ResultadoAsyncTask;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.ResultadoId;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.TiposConstantes;
import com.vvm.sh.util.excepcoes.MetodoWsInvalidoException;
import com.vvm.sh.util.excepcoes.RespostaWsInvalidaException;
import com.vvm.sh.util.excepcoes.TipoInexistenteException;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel extends ViewModel {


    protected final CompositeDisposable disposables;
    protected MutableLiveData<Recurso> messagemLiveData;


    public MutableLiveData<Integer> loading;


    @Inject
    protected VvmshBaseDados vvmshBaseDados;

    public BaseViewModel() {

        this.disposables = new CompositeDisposable();
        messagemLiveData = new MutableLiveData<>();
        loading = new MutableLiveData<>();

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




    /**
     * Metodo que permite obter os generos
     */
    protected void obterGeneros(MutableLiveData<List<Tipo>> generos){

        List<Tipo> estado = new ArrayList<>();

        estado.add(TiposConstantes.GENERO_MASCULINO);
        estado.add(TiposConstantes.GENERO_FEMININO);
        generos.setValue(estado);

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

            messagemLiveData.setValue(Recurso.erro(codigo, "Upload"));
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
    protected void onCleared() {
        disposables.clear();
    }

}

