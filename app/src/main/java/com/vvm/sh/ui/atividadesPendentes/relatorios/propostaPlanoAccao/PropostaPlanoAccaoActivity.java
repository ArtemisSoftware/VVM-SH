package com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityPropostaPlanoAccaoBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao.adaptadores.OnPropostaPlanoAcaoListener;
import com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao.adaptadores.PropostaPagerAdapter;
import com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao.modelos.Proposta;
import com.vvm.sh.ui.planoAccao.AnuidadeFragment;
import com.vvm.sh.ui.planoAccao.adaptadores.AnuidadePagerAdapter;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

public class PropostaPlanoAccaoActivity extends BaseDaggerActivity implements OnPropostaPlanoAcaoListener, OnPropostaPlanoAcaoListener.OnPropostaListener {

    ActivityPropostaPlanoAccaoBinding activityPropostaPlanoAccaoBinding;


    @Inject
    ViewModelProviderFactory providerFactory;

    private PropostaPlanoAcaoViewModel viewModel;




    private static final int CONDICOES_ST_FRAGMENT = 0;
    private CondicoesStFragment condicoesStFragment;

    private static final int MEDIDAS_AVALIACAO_FRAGMENT = 1;
    private MedidasAvaliacaoFragment medidasAvaliacaoFragment;




    @Override
    protected void intActivity(Bundle savedInstanceState) {


        viewModel = ViewModelProviders.of(this, providerFactory).get(PropostaPlanoAcaoViewModel.class);

        activityPropostaPlanoAccaoBinding = (ActivityPropostaPlanoAccaoBinding) activityBinding;
        activityPropostaPlanoAccaoBinding.setLifecycleOwner(this);
        activityPropostaPlanoAccaoBinding.setViewmodel(viewModel);
        activityPropostaPlanoAccaoBinding.setListener(this);


        subscreverObservadores();

        setupViewPager();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            viewModel.obterPropostas(bundle.getInt(getString(R.string.argumento_id_atividade)));
        }
        else{
            finish();
        }

    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_proposta_plano_accao;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

        viewModel.observarCondicoesSt().observe(this, new Observer<List<Proposta>>() {
            @Override
            public void onChanged(List<Proposta> propostas) {
                ((PropostaPagerAdapter) activityPropostaPlanoAccaoBinding.viewpagerContainer.getAdapter()).atualizarCondicoesSt(propostas);
            }
        });


        viewModel.observarMedidasAvaliacao().observe(this, new Observer<List<Proposta>>() {
            @Override
            public void onChanged(List<Proposta> propostas) {
                ((PropostaPagerAdapter) activityPropostaPlanoAccaoBinding.viewpagerContainer.getAdapter()).atualizarMedidasAvaliacao(propostas);
            }
        });
    }



    //----------------------
    //Metodos locais
    //----------------------


    private void setupViewPager(){

        PropostaPagerAdapter adapter = new PropostaPagerAdapter(getSupportFragmentManager());

        condicoesStFragment = new CondicoesStFragment();
        medidasAvaliacaoFragment = new MedidasAvaliacaoFragment();

        adapter.adicionarFragmento(condicoesStFragment);
        adapter.adicionarFragmento(medidasAvaliacaoFragment);


        activityPropostaPlanoAccaoBinding.viewpagerContainer.setAdapter(adapter);
        activityPropostaPlanoAccaoBinding.tab.setupWithViewPager(activityPropostaPlanoAccaoBinding.viewpagerContainer);

        activityPropostaPlanoAccaoBinding.tab.getTabAt(CONDICOES_ST_FRAGMENT).setText(getString(R.string.condicoes_st));
        activityPropostaPlanoAccaoBinding.tab.getTabAt(MEDIDAS_AVALIACAO_FRAGMENT).setText(getString(R.string.medidas_avaliacao));
    }



    @Override
    public void OnCheckProposta(Proposta registo, boolean selecionado) {

        Bundle bundle = getIntent().getExtras();
        viewModel.selecionar(bundle.getInt(getString(R.string.argumento_id_atividade)), registo.resultado.id, selecionado);
    }

    @Override
    public void OnSelecionarTudo(boolean selecionado) {
        Bundle bundle = getIntent().getExtras();
        viewModel.selecionarTudo(bundle.getInt(getString(R.string.argumento_id_atividade)), selecionado);
    }
}
