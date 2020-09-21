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


    private RelatorioAmbiental relatorio;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(AvaliacaoAmbientalViewModel.class);

        activityRelatorioAvaliacaoAmbientalBinding = (ActivityRelatorioAvaliacaoAmbientalBinding) activityBinding;
        activityRelatorioAvaliacaoAmbientalBinding.setLifecycleOwner(this);
        activityRelatorioAvaliacaoAmbientalBinding.setViewmodel(viewModel);

        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            formatarRelatorio(bundle.getInt(getString(R.string.argumento_tipo_relatorio)));

            int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));
            int tipo = bundle.getInt(getString(R.string.argumento_tipo_relatorio));

            activityRelatorioAvaliacaoAmbientalBinding.setTipo(tipo);

            viewModel.obterValidadeRelatorio(idAtividade, tipo);
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


        viewModel.observarRelatorio().observe(this, new Observer<RelatorioAmbiental>() {
            @Override
            public void onChanged(RelatorioAmbiental registo) {

                relatorio = registo;

                if(relatorio.idRelatorio == 0){
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
     * @param tipo o tipo de relatorio
     */
    private void formatarRelatorio(int tipo) {

        switch (tipo){

            case Identificadores.Relatorios.ID_RELATORIO_ILUMINACAO:

                activityRelatorioAvaliacaoAmbientalBinding.imgBanner.setImageResource(R.drawable.iluminacao_banner);
                activityRelatorioAvaliacaoAmbientalBinding.txtTitulo.setText(getString(R.string.iluminacao));
                break;

            case Identificadores.Relatorios.ID_RELATORIO_TEMPERATURA_HUMIDADE:

                activityRelatorioAvaliacaoAmbientalBinding.imgBanner.setImageResource(R.drawable.termico_banner);
                activityRelatorioAvaliacaoAmbientalBinding.txtTitulo.setText(getString(R.string.temperatura_humidade));
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

            bundle.putInt(getString(R.string.argumento_id_relatorio), relatorio.idRelatorio);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else{
            finish();
        }
    }
}
