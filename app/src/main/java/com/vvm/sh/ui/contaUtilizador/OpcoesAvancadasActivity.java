package com.vvm.sh.ui.contaUtilizador;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.vvm.sh.R;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.ui.opcoes.TiposActivity;
import com.vvm.sh.util.interfaces.OnPermissaoConcedidaListener;
import com.vvm.sh.util.metodos.BaseDadosUtil;
import com.vvm.sh.util.metodos.DiretoriasUtil;
import com.vvm.sh.util.metodos.PermissoesUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class OpcoesAvancadasActivity extends BaseActivity {

    @BindView(R.id.spnr_ficheiros_bd)
    MaterialSpinner spnr_ficheiros_bd;

    @BindView(R.id.btn_importar_bd)
    MaterialButton btn_importar_bd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcoes_avancadas);

        spnr_ficheiros_bd.setItems(BaseDadosUtil.obterFicheirosBds());

        if(spnr_ficheiros_bd.getItems().size() > 0){
            btn_importar_bd.setEnabled(true);
        }
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
                    BaseDadosUtil.exportarBaseDados(OpcoesAvancadasActivity.this);
                    spnr_ficheiros_bd.setItems(BaseDadosUtil.obterFicheirosBds());
                    btn_importar_bd.setEnabled(true);
                }
            }
        };

        PermissoesUtil.pedirPermissoesEscritaLeitura(this, listener);
    }

    @OnClick(R.id.btn_importar_bd)
    public void btn_importar_bd_OnClickListener(View view) {

        OnPermissaoConcedidaListener listener = new OnPermissaoConcedidaListener() {
            @Override
            public void executar() {
                BaseDadosUtil.importarBaseDados(OpcoesAvancadasActivity.this, (String) spnr_ficheiros_bd.getItems().get(spnr_ficheiros_bd.getSelectedIndex()));

                if(Build.VERSION.SDK_INT>=16 && Build.VERSION.SDK_INT<21){
                    finishAffinity();
                } else if(Build.VERSION.SDK_INT>=21){
                    finishAndRemoveTask();
                    System.exit(0);
                }
            }
        };

        PermissoesUtil.pedirPermissoesEscritaLeitura(this, listener);
    }


}
