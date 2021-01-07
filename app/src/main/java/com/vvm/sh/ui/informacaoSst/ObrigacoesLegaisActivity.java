package com.vvm.sh.ui.informacaoSst;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.ObrigacaoLegalResultado;
import com.vvm.sh.baseDados.entidades.TrabalhoRealizadoResultado;
import com.vvm.sh.databinding.ActivityObrigacoesLegaisBinding;
import com.vvm.sh.databinding.ActivityTrabalhoRealizadoBinding;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.informacaoSst.adaptadores.OnInformacaoSstListener;
import com.vvm.sh.ui.registoVisita.RegistoVisitaViewModel;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

public class ObrigacoesLegaisActivity extends BaseDaggerActivity implements OnInformacaoSstListener {


    private ActivityObrigacoesLegaisBinding activityObrigacoesLegaisBinding;

    private InformacaoSstViewModel viewModel;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory_).get(InformacaoSstViewModel.class);

        activityObrigacoesLegaisBinding = (ActivityObrigacoesLegaisBinding) activityBinding;
        activityObrigacoesLegaisBinding.setLifecycleOwner(this);
        activityObrigacoesLegaisBinding.setListener(this);
        activityObrigacoesLegaisBinding.setViewmodel(viewModel);


        viewModel.obterObrigacoesLegais(PreferenciasUtil.obterIdTarefa(this));

    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_obrigacoes_legais;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

    }

    @Override
    public void onItemChecked(int idRegisto, boolean selecao) {

        ObrigacaoLegalResultado resultado = new ObrigacaoLegalResultado(PreferenciasUtil.obterIdTarefa(this), idRegisto);
        viewModel.gravar(PreferenciasUtil.obterIdTarefa(this), resultado, selecao);
    }
}