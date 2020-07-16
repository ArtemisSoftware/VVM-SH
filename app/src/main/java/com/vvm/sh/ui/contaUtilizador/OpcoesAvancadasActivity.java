package com.vvm.sh.ui.contaUtilizador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.ui.opcoes.TiposActivity;
import com.vvm.sh.util.metodos.Avancado;

import butterknife.OnClick;

public class OpcoesAvancadasActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcoes_avancadas);

    }


    @OnClick(R.id.lnr_lyt_tipos)
    public void lnr_lyt_tipos_OnClickListener(View view) {

        Intent intent;
        intent = new Intent(this, TiposActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.lnr_lyt_exportar_bd)
    public void lnr_lyt_exportar_bd_OnClickListener(View view) {

        Avancado.exportarBaseDados(this);
    }
}
