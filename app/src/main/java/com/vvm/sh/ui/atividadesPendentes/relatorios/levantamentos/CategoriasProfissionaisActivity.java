package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.CategoriaProfissionalResultado;
import com.vvm.sh.databinding.ActivityCategoriasProfissionaisBinding;
import com.vvm.sh.databinding.ActivityPerigoTarefaBinding;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

public class CategoriasProfissionaisActivity extends BaseDaggerActivity
        implements OnLevantamentoListener.OnCategoriaProfissionalListener{


    private ActivityCategoriasProfissionaisBinding activityCategoriasProfissionaisBinding;

    private LevantamentosViewModel viewModel;



    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory_).get(LevantamentosViewModel.class);

        activityCategoriasProfissionaisBinding = (ActivityCategoriasProfissionaisBinding) activityBinding;
        activityCategoriasProfissionaisBinding.setLifecycleOwner(this);
        activityCategoriasProfissionaisBinding.setViewmodel(viewModel);
        activityCategoriasProfissionaisBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));


        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            int id = bundle.getInt(getString(R.string.argumento_id));
            viewModel.obterCategoriasProfissionais(id);
        }
        else{
            finish();
        }

    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_categorias_profissionais;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

    }

    @Override
    public void OnCategoriaProfissionalClick(CategoriaProfissionalResultado registo) {

    }
}
