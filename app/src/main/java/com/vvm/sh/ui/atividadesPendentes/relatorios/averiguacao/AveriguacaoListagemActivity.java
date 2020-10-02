package com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityAveriguacaoListagemBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.adaptadores.OnAveriguacaoListener;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.modelos.Averiguacao;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.modelos.AveriguacaoRegisto;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class AveriguacaoListagemActivity extends BaseDaggerActivity
        implements OnAveriguacaoListener.OnAveriguacaoItemListener {



    private ActivityAveriguacaoListagemBinding activityAveriguacaoListagemBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    //--private AveriguacaoViewModel viewModel;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        //--viewModel = ViewModelProviders.of(this, providerFactory).get(AveriguacaoViewModel.class);

        activityAveriguacaoListagemBinding = (ActivityAveriguacaoListagemBinding) activityBinding;
        activityAveriguacaoListagemBinding.setLifecycleOwner(this);
        activityAveriguacaoListagemBinding.setListener(this);
        //activityAveriguacaoBinding.setViewmodel(viewModel);

        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            //viewModel.obterRegistos(bundle.getInt(getString(R.string.argumento_id_relatorio)));
        }
        else{
            finish();
        }
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_averiguacao_listagem;
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
