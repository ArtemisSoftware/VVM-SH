package com.vvm.sh.ui.pesquisa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.databinding.ActivityPesquisaBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.OnClick;

public class PesquisaActivity extends BaseDaggerActivity
    implements OnPesquisaListener{

    private ActivityPesquisaBinding activityPesquisaBinding;

    @Inject
    ViewModelProviderFactory providerFactory;


    private PesquisaViewModel viewModel;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(PesquisaViewModel.class);

        activityPesquisaBinding = (ActivityPesquisaBinding) activityBinding;
        activityPesquisaBinding.setLifecycleOwner(this);
        activityPesquisaBinding.setViewmodel(viewModel);
        //--activityPesquisaBinding.setListener(this);


        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            Pesquisa pesquisa = bundle.getParcelable(getString(R.string.argumento_configuracao_pesquisa));
            viewModel.obterRegistos(pesquisa.metodo, pesquisa.registosSelecionados);
        }
        else{
            finish();
        }
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_pesquisa;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

    }



    @Override
    public void OnSelecionarClick(Tipo registo) {

    }

    @Override
    public void OnRemoverSelecao(Tipo registo) {

    }


    //-----------------------
    //EVENTOS
    //-----------------------


    @OnClick(R.id.btn_gravar)
    public void btn_gravar_OnClickListener(View view) {

        Intent returnIntent = new Intent();
        returnIntent.putIntegerArrayListExtra(getString(R.string.resultado_pesquisa), viewModel.obterRegistosSelecionados());
        setResult(RESULT_OK, returnIntent);
        finish();
    }


}
