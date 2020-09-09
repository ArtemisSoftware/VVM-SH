package com.vvm.sh.ui.tarefa.quadroPessoal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.util.viewmodel.BaseViewModel;

public class QuadroPessoalActivity extends BaseDaggerActivity {


    @Override
    protected void intActivity(Bundle savedInstanceState) {

    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_quadro_pessoal;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return null;
    }

    @Override
    protected void subscreverObservadores() {

    }
}
