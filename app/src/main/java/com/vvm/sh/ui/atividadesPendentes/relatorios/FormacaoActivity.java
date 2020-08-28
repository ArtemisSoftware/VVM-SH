package com.vvm.sh.ui.atividadesPendentes.relatorios;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityFormacaoBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

import butterknife.OnClick;

public class FormacaoActivity extends BaseDaggerActivity
        implements OnFormacaoListener {


    private ActivityFormacaoBinding activityFormacaoBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private FormacaoViewModel viewModel;




    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(FormacaoViewModel.class);

        activityFormacaoBinding = (ActivityFormacaoBinding) activityBinding;
        activityFormacaoBinding.setLifecycleOwner(this);
        activityFormacaoBinding.setListener(this);
        activityFormacaoBinding.setViewmodel(viewModel);

        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            viewModel.obterFormacao(bundle.getInt(getString(R.string.argumento_id_atividade)));
        }
        else{
            finish();
        }
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_formacao;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

    }

    @Override
    public void OnFormandoClick(Formando formando) {

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {

            Intent intent = new Intent(this, FormandoActivity.class);

            int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

            Bundle bundleFormacao = new Bundle();
            bundleFormacao.putInt(getString(R.string.argumento_id_atividade), idAtividade);

            if(formando != null){
                bundleFormacao.putInt(getString(R.string.argumento_id_formando), formando.resultado.id);
            }

            intent.putExtras(bundleFormacao);
            startActivity(intent);

        }
        else{
            finish();
        }
    }

    @Override
    public void OnSelecionadoCheck(Formando formando, boolean selecionado) {

        formando.resultado.selecionado = selecionado;
        viewModel.gravar(PreferenciasUtil.obterIdTarefa(this), formando.resultado);
    }



    //-------------------
    //Eventos
    //-------------------


    @OnClick({R.id.fab_adicionar_formando})
    public void fab_adicionar_formando_OnClickListener(View view) {

        activityFormacaoBinding.fabMenu.close(true);
        OnFormandoClick(null);
    }


    @OnClick({R.id.card_acao_formacao, R.id.fab_adicionar_acao_formacao})
    public void fab_adicionar_acao_formacao_OnClickListener(View view) {

        activityFormacaoBinding.fabMenu.close(true);
        Intent intent = new Intent(this, AcaoFormacaoActivity.class);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {

            int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

            Bundle bundleFormacao = new Bundle();
            bundleFormacao.putInt(getString(R.string.argumento_id_atividade), idAtividade);

            intent.putExtras(bundleFormacao);
            startActivity(intent);
        }
        else{
            finish();
        }

    }



}
