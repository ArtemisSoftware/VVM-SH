package com.vvm.sh.ui.planoAccao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.PlanoAccaoResultado;
import com.vvm.sh.databinding.ActivityPlanoAccaoBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.planoAccao.adaptadores.AnuidadePagerAdapter;
import com.vvm.sh.ui.planoAccao.adaptadores.OnPlanoAtividadeListener;
import com.vvm.sh.ui.planoAccao.modelo.AtividadeRegisto;
import com.vvm.sh.ui.planoAccao.modelo.Plano;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

public class PlanoAccaoActivity extends BaseDaggerActivity implements OnPlanoAtividadeListener {


    ActivityPlanoAccaoBinding activityPlanoAccaoBinding;


    @Inject
    ViewModelProviderFactory providerFactory;

    private PlanoAccaoViewModel viewModel;




    private static final int ANUIDADE_1_FRAGMENT = 0;
    private AnuidadeFragment anuidadeFragment_1;

    private static final int ANUIDADE_2_FRAGMENT = 1;
    private AnuidadeFragment anuidadeFragment_2;



    @Override
    protected void intActivity(Bundle savedInstanceState) {


        viewModel = ViewModelProviders.of(this, providerFactory).get(PlanoAccaoViewModel.class);
        activityPlanoAccaoBinding = (ActivityPlanoAccaoBinding) activityBinding;
        activityPlanoAccaoBinding.setLifecycleOwner(this);
        activityPlanoAccaoBinding.setViewmodel(viewModel);
        activityPlanoAccaoBinding.setListener(this);
        activityPlanoAccaoBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));


        subscreverObservadores();

        setupViewPager();

        viewModel.obterAtividades(PreferenciasUtil.obterIdTarefa(this));


    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_plano_accao;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {


        viewModel.observarAtividades().observe(this, new Observer<List<AtividadeRegisto>>() {
            @Override
            public void onChanged(List<AtividadeRegisto> atividadeRegistos) {
               ((AnuidadePagerAdapter) activityPlanoAccaoBinding.viewpagerContainer.getAdapter()).atualizar(atividadeRegistos, ANUIDADE_1_FRAGMENT);
               ((AnuidadePagerAdapter) activityPlanoAccaoBinding.viewpagerContainer.getAdapter()).atualizar(atividadeRegistos, ANUIDADE_2_FRAGMENT);
            }
        });


        viewModel.observarPlano().observe(this, new Observer<Plano>() {
            @Override
            public void onChanged(Plano plano) {

                int ano = Integer.parseInt(plano.cliente.dataContrato.split("-")[0]);

                activityPlanoAccaoBinding.tab.getTabAt(ANUIDADE_1_FRAGMENT).setText(getString(R.string.anuidade) + " - " + ano);
                ((AnuidadePagerAdapter) activityPlanoAccaoBinding.viewpagerContainer.getAdapter()).atualizar(ANUIDADE_1_FRAGMENT, ano);

                activityPlanoAccaoBinding.tab.getTabAt(ANUIDADE_2_FRAGMENT).setText(getString(R.string.anuidade) + " - " + (ano + 1));
                ((AnuidadePagerAdapter) activityPlanoAccaoBinding.viewpagerContainer.getAdapter()).atualizar(ANUIDADE_2_FRAGMENT, ano + 1);


            }
        });

    }



    //----------------------
    //Metodos locais
    //----------------------


    private void setupViewPager(){

        AnuidadePagerAdapter adapter = new AnuidadePagerAdapter(getSupportFragmentManager());

        anuidadeFragment_1 = new AnuidadeFragment();
        anuidadeFragment_2 = new AnuidadeFragment();

        adapter.adicionarFragmento(anuidadeFragment_1);
        adapter.adicionarFragmento(anuidadeFragment_2);


        activityPlanoAccaoBinding.viewpagerContainer.setAdapter(adapter);
        activityPlanoAccaoBinding.tab.setupWithViewPager(activityPlanoAccaoBinding.viewpagerContainer);

        activityPlanoAccaoBinding.tab.getTabAt(ANUIDADE_1_FRAGMENT).setText(getString(R.string.anuidade));
        activityPlanoAccaoBinding.tab.getTabAt(ANUIDADE_2_FRAGMENT).setText(getString(R.string.anuidade));
    }


    @Override
    public void OnAtividadeClick(AtividadeRegisto registo) {

    }

    @Override
    public void OnDataClick(AtividadeRegisto registo, int mes) {

        int ano = Integer.parseInt(viewModel.plano.getValue().cliente.dataContrato.split("-")[0]);
        ano = ano + activityPlanoAccaoBinding.tab.getSelectedTabPosition();

        String data = "";

        if((mes + "").length() == 1){
            data =  ano + "-" + "0" + mes + "-01";
        }
        else{
            data = ano + "-" + mes + "-01";
        }

        PlanoAccaoResultado resultado = new PlanoAccaoResultado(PreferenciasUtil.obterIdTarefa(this), registo.atividade.id, registo.atividade.servId, data);
        viewModel.gravar(PreferenciasUtil.obterIdTarefa(this), registo.resultado, resultado);

    }
}
