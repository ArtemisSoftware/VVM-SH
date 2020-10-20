package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityLevantamentosBinding;
import com.vvm.sh.databinding.ActivityRelatorioLevantamentoBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

import butterknife.OnClick;

public class RelatorioLevantamentoActivity extends BaseDaggerActivity {


    private ActivityRelatorioLevantamentoBinding activityRelatorioLevantamentoBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private LevantamentosViewModel viewModel;




    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(LevantamentosViewModel.class);

        activityRelatorioLevantamentoBinding = (ActivityRelatorioLevantamentoBinding) activityBinding;
        activityRelatorioLevantamentoBinding.setLifecycleOwner(this);
        activityRelatorioLevantamentoBinding.setViewmodel(viewModel);
        activityRelatorioLevantamentoBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));

        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            int id = bundle.getInt(getString(R.string.argumento_id_levantamento), 0);

            if(id == 0){
                card_perigo_tarefa_OnClickListener(null);
            }
            else {
                viewModel.obterRelatorio(id);
            }
        }
        else{
            finish();
        }

    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_relatorio_levantamento;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

    }





    @OnClick({R.id.card_perigo_tarefa})
    public void card_perigo_tarefa_OnClickListener(View view) {

        Bundle bundle = getIntent().getExtras();
        
        Intent intent = new Intent(this, PerigoTarefaActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    @OnClick({R.id.card_categorias_profissionais})
    public void card_categorias_profissionais_OnClickListener(View view) {

        Bundle bundleOriginal = getIntent().getExtras();
        int idAtividade = bundleOriginal.getInt(getString(R.string.argumento_id_atividade));

        Bundle bundle = new Bundle();
        int id = Integer.parseInt(activityRelatorioLevantamentoBinding.txtIdLevantamento.getText().toString());
        bundle.putInt(getString(R.string.argumento_id_levantamento), id);
        bundle.putInt(getString(R.string.argumento_id_atividade), idAtividade);

        Intent intent = new Intent(this, CategoriasProfissionaisActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }



    @OnClick({R.id.card_riscos})
    public void card_riscos_OnClickListener(View view) {

        Bundle bundleOriginal = getIntent().getExtras();
        int idAtividade = bundleOriginal.getInt(getString(R.string.argumento_id_atividade));

        Bundle bundle = new Bundle();
        int id = Integer.parseInt(activityRelatorioLevantamentoBinding.txtIdLevantamento.getText().toString());
        bundle.putInt(getString(R.string.argumento_id_levantamento), id);
        bundle.putInt(getString(R.string.argumento_id_atividade), idAtividade);

        Intent intent = new Intent(this, RiscosActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }


}