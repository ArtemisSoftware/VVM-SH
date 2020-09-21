package com.vvm.sh.ui.atividadesPendentes.relatorios.registoVisita;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.ui.atividadesPendentes.relatorios.registoVisita.adaptadores.OnRegistoVisitaListener;
import com.vvm.sh.util.metodos.PreferenciasUtil;

public class TrabalhoRealizadoActivity extends AppCompatActivity
    implements OnRegistoVisitaListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trabalho_realizado);
    }



    @Override
    public void onItemChecked(int idTrabalhoRealizado, boolean selecao) {

        if(selecao == true) {

            DialogoTrabalhoRealizado dialogo = DialogoTrabalhoRealizado.newInstance(idTrabalhoRealizado);
            dialogo.show(getSupportFragmentManager(), "example dialog");
        }
        else{
            //--viewmodel.remover(PreferenciasUtil.obterIdTarefa(this), idTrabalhoRealizado);
        }
    }


    //---------------------
    //Eventos
    //---------------------
}
