package com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityRelatorioAvaliacaoAmbientalBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.modelos.RelatorioAmbiental;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

import butterknife.OnClick;

public class RelatorioAvaliacaoAmbientalActivity extends BaseDaggerActivity {


    private ActivityRelatorioAvaliacaoAmbientalBinding activityRelatorioAvaliacaoAmbientalBinding;

    @Inject
    ViewModelProviderFactory providerFactory;


    private AvaliacaoAmbientalViewModel viewModel;



    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(AvaliacaoAmbientalViewModel.class);

        activityRelatorioAvaliacaoAmbientalBinding = (ActivityRelatorioAvaliacaoAmbientalBinding) activityBinding;
        activityRelatorioAvaliacaoAmbientalBinding.setLifecycleOwner(this);
        activityRelatorioAvaliacaoAmbientalBinding.setViewmodel(viewModel);

        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));
            int tipo = bundle.getInt(getString(R.string.argumento_tipo_relatorio));

            activityRelatorioAvaliacaoAmbientalBinding.setTipo(tipo);

            formatarRelatorio(idAtividade, tipo);
        }
        else{
            finish();
        }
    }



    @Override
    protected int obterLayout() {
        return R.layout.activity_relatorio_avaliacao_ambiental;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

        viewModel.observarRelatorio().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean registo) {

                if(registo == false){
                    card_geral_OnClickListener(null);
                }
            }
        });
    }


    //----------------------
    //Metodos locais
    //----------------------

    /**
     * Metodo que permite formatar a apresentacao do relatorio
     * @param idAtividade o identificador da atividade
     * @param tipo o tipo de relatorio
     */
    private void formatarRelatorio(int idAtividade, int tipo) {

        Bundle bundle = getIntent().getExtras();

        switch (tipo){

            case Identificadores.Relatorios.ID_RELATORIO_ILUMINACAO:

                getIntent().putExtra(getString(R.string.argumento_origem_relatorio), Identificadores.Origens.ORIGEM_RELATORIO_ILUMINACAO);
                viewModel.obterRelatorio(idAtividade, Identificadores.Origens.ORIGEM_RELATORIO_ILUMINACAO);
                break;

            case Identificadores.Relatorios.ID_RELATORIO_TEMPERATURA_HUMIDADE:

                getIntent().putExtra(getString(R.string.argumento_origem_relatorio), Identificadores.Origens.ORIGEM_RELATORIO_TEMPERATURA_HUMIDADE);
                viewModel.obterRelatorio(idAtividade, Identificadores.Origens.ORIGEM_RELATORIO_TEMPERATURA_HUMIDADE);
                break;

            default:
                finish();
                break;

        }
    }


    //--------------------
    //EVENTOS
    //--------------------


    @OnClick({R.id.card_geral})
    public void card_geral_OnClickListener(View view) {

        Intent intent = new Intent(this, AvaliacaoGeralActivity.class);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {

            intent.putExtras(bundle);
            startActivity(intent);
        }
        else{
            finish();
        }
    }

    @OnClick({R.id.card_avaliacoes})
    public void card_avaliacoes_OnClickListener(View view) {

        Intent intent = new Intent(this, AvaliacoesAmbientaisActivity.class);
        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            int tipo = bundle.getInt(getString(R.string.argumento_tipo_relatorio));
            bundle.putInt(getString(R.string.argumento_tipo_relatorio), tipo);
            bundle.putInt(getString(R.string.argumento_id_relatorio), viewModel.relatorio.getValue().idRelatorio);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else{
            finish();
        }
    }
}
