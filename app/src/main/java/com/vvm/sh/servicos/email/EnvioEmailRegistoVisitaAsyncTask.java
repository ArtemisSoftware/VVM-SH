package com.vvm.sh.servicos.email;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;

import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.repositorios.RegistoVisitaRepositorio;

public class EnvioEmailRegistoVisitaAsyncTask extends EnvioEmailAsyncTask{

    private VvmshBaseDados vvmshBaseDados;
    private RegistoVisitaRepositorio repositorio;
    private int idTarefa;

    public EnvioEmailRegistoVisitaAsyncTask(Context contexto, VvmshBaseDados vvmshBaseDados, RegistoVisitaRepositorio repositorio, int idTarefa) {
        super(contexto);

        this.vvmshBaseDados = vvmshBaseDados;
        this.repositorio = repositorio;
        this.idTarefa = idTarefa;
    }


    @Override
    protected void completarEnvio() {
        super.completarEnvio();

        this.vvmshBaseDados.runInTransaction(new Runnable(){
            @Override
            public void run(){

                try {
                    //repositorio.sincronizar(idTarefa);
                }
                catch(SQLiteConstraintException throwable){
                    erro = throwable.getMessage();
                }
            }
        });
    }
}
