package com.vvm.sh.ui.informacaoSst;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.TrabalhoRealizadoResultado;
import com.vvm.sh.ui.informacaoSst.adaptadores.OnInformacaoSstListener;
import com.vvm.sh.util.metodos.PreferenciasUtil;

public class ObrigacoesLegaisActivity extends AppCompatActivity implements OnInformacaoSstListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obrigacoes_legais);
    }

    @Override
    public void onItemChecked(int idRegisto, boolean selecao) {

//        TrabalhoRealizadoResultado resultado = new TrabalhoRealizadoResultado(PreferenciasUtil.obterIdTarefa(this), idTrabalhoRealizado);
//        viewModel.gravar(PreferenciasUtil.obterIdTarefa(this), resultado, selecao);
    }
}