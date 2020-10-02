package com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.adaptadores.OnAveriguacaoListener;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.modelos.Averiguacao;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.modelos.AveriguacaoRegisto;
import com.vvm.sh.util.viewmodel.BaseViewModel;

public class AveriguacaoListagemActivity extends BaseDaggerActivity
        implements OnAveriguacaoListener.OnAveriguacaoItemListener {


    @Override
    protected void intActivity(Bundle savedInstanceState) {

    }

    @Override
    protected int obterLayout() {
        return 0;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return null;
    }

    @Override
    protected void subscreverObservadores() {

    }

    @Override
    public void OnItemClick(AveriguacaoRegisto registo) {

    }
}
