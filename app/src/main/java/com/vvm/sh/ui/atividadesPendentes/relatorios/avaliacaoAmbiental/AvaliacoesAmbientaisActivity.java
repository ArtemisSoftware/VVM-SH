package com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.AvaliacaoAmbientalResultado;
import com.vvm.sh.databinding.ActivityAvaliacoesAmbientaisBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.adaptadores.OnAvaliacaoAmbientalListener;
import com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.modelos.AvaliacaoAmbiental;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.OnClick;

public class AvaliacoesAmbientaisActivity extends BaseDaggerActivity
        implements OnAvaliacaoAmbientalListener {


    private ActivityAvaliacoesAmbientaisBinding activityAvaliacoesAmbientaisBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private AvaliacaoAmbientalViewModel viewModel;



    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(AvaliacaoAmbientalViewModel.class);

        activityAvaliacoesAmbientaisBinding = (ActivityAvaliacoesAmbientaisBinding) activityBinding;
        activityAvaliacoesAmbientaisBinding.setLifecycleOwner(this);
        activityAvaliacoesAmbientaisBinding.setViewmodel(viewModel);
        activityAvaliacoesAmbientaisBinding.setListener(this);
        activityAvaliacoesAmbientaisBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));

        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            int id = bundle.getInt(getString(R.string.argumento_id_relatorio));
            int tipo = bundle.getInt(getString(R.string.argumento_tipo_relatorio));
            int origem = bundle.getInt(getString(R.string.argumento_origem_relatorio));

            activityAvaliacoesAmbientaisBinding.setTipo(tipo);
            viewModel.obterAvalicoes(id, origem);
        }
        else{
            finish();
        }

    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_avaliacoes_ambientais;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

        viewModel.observarAvaliacoes().observe(this, new Observer<List<AvaliacaoAmbiental>>() {
            @Override
            public void onChanged(List<AvaliacaoAmbiental> registos) {

                if(registos.size() == 0){
                    iniciarAvaliacao(getIntent().getExtras());
                }
            }
        });
    }


    //--------------------
    //Metodos locais
    //--------------------


    /**
     * Metodo que permite iniciar a avaliação
     */
    private void iniciarAvaliacao(Bundle bundle) {
        Intent intent = null;

        if(bundle != null) {

            int tipo = bundle.getInt(getString(R.string.argumento_tipo_relatorio));
            int id = bundle.getInt(getString(R.string.argumento_id_relatorio));
            int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

            switch (tipo){

                case Identificadores.Relatorios.ID_RELATORIO_ILUMINACAO:

                    intent = new Intent(this, AvaliacaoIluminacaoRegistoActivity.class);
                    break;

                case Identificadores.Relatorios.ID_RELATORIO_TEMPERATURA_HUMIDADE:

                    intent = new Intent(this, AvaliacaoTemperaturaHumidadeRegistoActivity.class);
                    break;

                default:
                    finish();
                    break;

            }

            if(intent != null) {
                intent.putExtra(getString(R.string.argumento_id_relatorio), id);
                intent.putExtra(getString(R.string.argumento_id_atividade), idAtividade);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
        else{
            finish();
        }
    }





    //--------------------
    //EVENTOS
    //--------------------

    @Override
    public void OnAvaliacaoClick(AvaliacaoAmbientalResultado registo) {


        Bundle bundle = getIntent().getExtras();
        bundle.putInt(getString(R.string.argumento_id_avaliacao), registo.id);

        iniciarAvaliacao(bundle);
    }

    @Override
    public void onRemoverClick(AvaliacaoAmbientalResultado registo) {

        Bundle bundle = getIntent().getExtras();
        int origem = bundle.getInt(getString(R.string.argumento_origem_relatorio));
        int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

        viewModel.remover(PreferenciasUtil.obterIdTarefa(this), idAtividade, registo, origem);
    }


    @OnClick({R.id.fab_adicionar})
    public void fab_adicionar_OnClickListener(View view) {

        iniciarAvaliacao(getIntent().getExtras());
    }



}
