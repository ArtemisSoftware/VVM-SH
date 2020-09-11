package com.vvm.sh.ui.pesquisa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.util.viewmodel.BaseViewModel;

public class PesquisaActivity extends BaseDaggerActivity {


    @Override
    protected void intActivity(Bundle savedInstanceState) {

    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_pesquisa;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return null;
    }

    @Override
    protected void subscreverObservadores() {

    }
}
