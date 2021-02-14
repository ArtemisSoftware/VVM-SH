package com.vvm.sh.ui.transferencias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vvm.sh.MainActivity;
import com.vvm.sh.R;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.apresentacao.ApresentacaoActivity;
import com.vvm.sh.ui.autenticacao.AutenticacaoActivity;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import butterknife.OnClick;

public class AtualizacaoTiposActivity extends BaseDaggerActivity {


    private TransferenciasViewModel viewModel;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_atualizacao_tipos;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return null;
    }

    @Override
    protected void subscreverObservadores() {

    }



    //--------------------
    //EVENTOS
    //--------------------


    @OnClick({R.id.crl_prosseguir})
    public void crl_prosseguir_ButtonClick(View view) {


            finish();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);


    }
}