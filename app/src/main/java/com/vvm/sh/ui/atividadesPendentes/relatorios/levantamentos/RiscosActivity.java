package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityRiscosBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.adaptadores.OnLevantamentoListener;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.Risco;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

import butterknife.OnClick;

public class RiscosActivity extends BaseDaggerActivity
    implements OnLevantamentoListener.OnRiscoListener {


    private ActivityRiscosBinding activityRiscosBinding;




    private LevantamentosViewModel viewModel;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory_).get(LevantamentosViewModel.class);

        activityRiscosBinding = (ActivityRiscosBinding) activityBinding;
        activityRiscosBinding.setLifecycleOwner(this);
        activityRiscosBinding.setViewmodel(viewModel);
        activityRiscosBinding.setListener(this);
        activityRiscosBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));


        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            int id = bundle.getInt(getString(R.string.argumento_id_levantamento));
            viewModel.obteRiscos(id);
        }
        else{
            finish();
        }

    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_riscos;
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

                        dialogo.sucesso(recurso.messagem);
                        break;

                    case ERRO:

                        dialogo.erro(recurso.messagem);
                        break;

                }
            }
        });
    }


    //-----------------------
    //EVENTOS
    //-----------------------

    @OnClick({R.id.fab_adicionar})
    public void fab_adicionar_OnClickListener(View view) {

        Bundle bundle = getIntent().getExtras();
        Intent intent = new Intent(this, RiscoRegistoActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void OnRiscoClick(Risco registo) {

        Bundle bundle = getIntent().getExtras();
        bundle.putInt(getString(R.string.argumento_id), registo.resultado.id);
        Intent intent = new Intent(this, RiscoRegistoActivity.class);

        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void OnRemoverClick(Risco registo) {

        Bundle bundle = getIntent().getExtras();
        int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

        viewModel.removerRisco(PreferenciasUtil.obterIdTarefa(this), idAtividade, registo);
    }
}
