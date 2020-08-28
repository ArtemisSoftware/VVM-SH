package com.vvm.sh.ui.contaUtilizador;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.ui.opcoes.TiposActivity;
import com.vvm.sh.util.interfaces.OnPermissaoConcedidaListener;
import com.vvm.sh.util.metodos.Avancado;
import com.vvm.sh.util.metodos.DiretoriasUtil;
import com.vvm.sh.util.metodos.Permissoes;

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

        OnPermissaoConcedidaListener listener = new OnPermissaoConcedidaListener() {
            @Override
            public void executar() {

                if(DiretoriasUtil.criarDirectoria(DiretoriasUtil.BASE_DADOS) == true){
                    Avancado.exportarBaseDados(OpcoesAvancadasActivity.this);
                }

            }
        };

        Permissoes.pedirPermissaoApp(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, listener);

    }

    public View getView() {
        return findViewById(android.R.id.content);
    }
}
