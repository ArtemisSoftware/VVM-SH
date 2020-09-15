package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.LevantamentoRiscoResultado;
import com.vvm.sh.databinding.ActivityLevantamentosBinding;
import com.vvm.sh.databinding.ActivityPerigoTarefaBinding;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import butterknife.OnClick;

public class PerigoTarefaActivity extends BaseDaggerActivity {

    private ActivityPerigoTarefaBinding activityPerigoTarefaBinding;

    private LevantamentosViewModel viewModel;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory_).get(LevantamentosViewModel.class);

        activityPerigoTarefaBinding = (ActivityPerigoTarefaBinding) activityBinding;
        activityPerigoTarefaBinding.setLifecycleOwner(this);
        activityPerigoTarefaBinding.setViewmodel(viewModel);
        activityPerigoTarefaBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));

        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            int id = bundle.getInt(getString(R.string.argumento_id));
            viewModel.obterLevantamento(id);
        }
        else{
            finish();
        }
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_perigo_tarefa;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

        viewModel.observarMessagem().observe(this, new Observer<Recurso>() {
            @Override
            public void onChanged(Recurso recurso) {

                switch (recurso.status){

                    case SUCESSO:

                        dialogo.sucesso(recurso.messagem, listenerActivity);
                        break;

                    case ERRO:

                        dialogo.erro(recurso.messagem);
                        break;

                }
            }
        });
    }


    //----------------------
    //Eventos
    //----------------------

    @OnClick(R.id.fab_gravar)
    public void fab_gravar_OnClickListener(View view) {

        Bundle bundle = getIntent().getExtras();
        int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

        String tarefa = activityPerigoTarefaBinding.txtInpTarefa.getText().toString();
        String perigo = activityPerigoTarefaBinding.txtInpPerigo.getText().toString();

        LevantamentoRiscoResultado registo = new LevantamentoRiscoResultado(idAtividade, tarefa, perigo);

        viewModel.gravar(registo);
    }

}
