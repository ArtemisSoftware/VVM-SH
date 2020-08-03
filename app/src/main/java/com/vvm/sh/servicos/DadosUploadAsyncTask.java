package com.vvm.sh.servicos;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import com.vvm.sh.api.Anomalia;
import com.vvm.sh.api.DadosFormularios;
import com.vvm.sh.api.Email;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.repositorios.UploadRepositorio;
import com.vvm.sh.ui.anomalias.modelos.AnomaliaResultado;
import com.vvm.sh.util.mapeamento.ModelMapping;
import com.vvm.sh.util.mapeamento.UploadMapping;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import static com.vvm.sh.util.constantes.Identificadores.Resultados.*;

public class DadosUploadAsyncTask  extends AsyncTask<List<Resultado>, Void, Void> {

    private String errorMessage, idUtilizador;
    private VvmshBaseDados vvmshBaseDados;
    private UploadRepositorio repositorio;
    private JSONArray dadosTarefas = new JSONArray();

    public DadosUploadAsyncTask(VvmshBaseDados vvmshBaseDados, UploadRepositorio repositorio, String idUtilizador){
        this.vvmshBaseDados = vvmshBaseDados;
        this.repositorio = repositorio;
        this.idUtilizador = idUtilizador;
    }

    @Override
    protected Void doInBackground(List<Resultado>... resultados) {

        if(resultados[0] == null)
            return null;

        List<Resultado> resposta = resultados[0];


        this.vvmshBaseDados.runInTransaction(new Runnable(){
            @Override
            public void run(){

                try {

                    DadosFormularios dadosFormularios = new DadosFormularios();


                    for (Resultado resultado : resposta) {

                        switch (resultado.id){

                            case ID_EMAIL:

                                dadosFormularios.fixarEmail(adicionarEmail(resultado.idTarefa));
                                break;

                            case ID_ANOMALIA_CLIENTE:

                                dadosFormularios.fixarAnomalias(adicionarAnomaliaCliente(resultado.idTarefa));
                                break;


                            default:
                                break;
                        }
                    }

                }
                catch(SQLiteConstraintException throwable){
                    errorMessage = throwable.getMessage();
                }
            }
        });


        return null;
    }


    private List<Anomalia> adicionarAnomaliaCliente(int idTarefa) {

        List<Anomalia> registos = new ArrayList<>();

        for (AnomaliaResultado item : repositorio.obterAnomalias(idTarefa)) {
            registos.add(UploadMapping.INSTANCE.map(item));
        }

        return registos;
    }


    private Email adicionarEmail(int idTarefa) {

        Email email = UploadMapping.INSTANCE.map(repositorio.obterEmail(idTarefa));
        return email;
    }
}
