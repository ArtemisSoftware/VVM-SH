package com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityAveriguacaoBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.adaptadores.OnAveriguacaoListener;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.modelos.Averiguacao;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class AveriguacaoActivity extends BaseDaggerActivity
        implements OnAveriguacaoListener {


    private ActivityAveriguacaoBinding activityAveriguacaoBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private AveriguacaoViewModel viewModel;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(AveriguacaoViewModel.class);

        activityAveriguacaoBinding = (ActivityAveriguacaoBinding) activityBinding;
        activityAveriguacaoBinding.setLifecycleOwner(this);
        activityAveriguacaoBinding.setListener(this);
        activityAveriguacaoBinding.setViewmodel(viewModel);

        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            activityAveriguacaoBinding.setTipo(bundle.getInt(getString(R.string.argumento_tipo_relatorio)));
            viewModel.obterRelatorio(PreferenciasUtil.obterIdTarefa(this), bundle.getInt(getString(R.string.argumento_tipo_relatorio)));
        }
        else{
            finish();
        }
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_averiguacao;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

    }

    @Override
    public void OnItemClick(Averiguacao registo) {

        Bundle bundleLocal = new Bundle();
        bundleLocal.putInt(getString(R.string.argumento_id_relatorio), registo.id);
        bundleLocal.putInt(getString(R.string.argumento_id_tipo), registo.tipo);

        Bundle bundle = getIntent().getExtras();
        bundleLocal.putInt(getString(R.string.argumento_id_atividade), bundle.getInt(getString(R.string.argumento_id_atividade)));

        Intent intent = new Intent(this, AveriguacaoListagemActivity.class);
        intent.putExtras(bundleLocal);
        startActivity(intent);

    }

    @Override
    public void OnNaoImplementados(Averiguacao registo) {

        Bundle bundle = getIntent().getExtras();
        viewModel.gravarNaoImplementado(PreferenciasUtil.obterIdTarefa(this), bundle.getInt(getString(R.string.argumento_id_atividade)), registo.id);
    }
}
