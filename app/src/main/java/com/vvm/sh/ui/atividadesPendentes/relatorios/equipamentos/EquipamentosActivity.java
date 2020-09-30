package com.vvm.sh.ui.atividadesPendentes.relatorios.equipamentos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityEquipamentosBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.pesquisa.PesquisaViewModel;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.metodos.TiposUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class EquipamentosActivity extends BaseDaggerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipamentos);
    }

    private ActivityEquipamentosBinding activityEquipamentosBinding;

    @Inject
    ViewModelProviderFactory providerFactory;


    private PesquisaViewModel viewModel;



    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(PesquisaViewModel.class);

        activityEquipamentosBinding = (ActivityEquipamentosBinding) activityBinding;
        activityEquipamentosBinding.setLifecycleOwner(this);
        activityEquipamentosBinding.setViewmodel(viewModel);
        //activityPesquisaBinding.setListenerRegisto(this);
        //activityPesquisaBinding.setListenerSelecionado(this);
        activityEquipamentosBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));
            viewModel.obterEquipamentos(idAtividade);
        }
        else{
            finish();
        }
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_equipamentos;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

    }
}
