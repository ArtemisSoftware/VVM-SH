package com.vvm.sh.ui.autenticacao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import butterknife.OnClick;

public class CarregamentoActivity extends BaseDaggerActivity {



    @Override
    protected void intActivity(Bundle savedInstanceState) {

    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_carregamento;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return null;
    }

    @Override
    protected void subscreverObservadores() {

    }


    @OnClick({R.id.crl_prosseguir})
    public void crl_prosseguir_ButtonClick(View view) {

        finish();
    }

}
