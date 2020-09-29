package com.vvm.sh.ui.planoAccao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityPlanoAccaoBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.planoAccao.adaptadores.AnuidadePagerAdapter;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

public class PlanoAccaoActivity extends BaseDaggerActivity {


    ActivityPlanoAccaoBinding activityPlanoAccaoBinding;


//    @Inject
//    ViewModelProviderFactory providerFactory;
//
//    private PlanoAccaoViewModel viewModel;




    private static final int ANUIDADE_1_FRAGMENT = 0;
    private AnuidadeFragment anuidadeFragment_1;

    private static final int ANUIDADE_2_FRAGMENT = 1;
    private AnuidadeFragment anuidadeFragment_2;



    @Override
    protected void intActivity(Bundle savedInstanceState) {


//        viewModel = ViewModelProviders.of(this, providerFactory).get(PlanoAccaoViewModel.class);
        activityPlanoAccaoBinding = (ActivityPlanoAccaoBinding) activityBinding;
        activityPlanoAccaoBinding.setLifecycleOwner(this);
        //activityPlanoAccaoBinding.setViewmodel(viewModel);
        activityPlanoAccaoBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));


        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            //--viewModel.obterRegistos(pesquisa.metodo, pesquisa.registosSelecionados);
        }
        else{
            finish();
        }

        setupViewPager();
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_plano_accao;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return null;
    }

    @Override
    protected void subscreverObservadores() {

//
//        viewModel.observarMessagem().observe(this, new Observer<Recurso>() {
//            @Override
//            public void onChanged(Recurso recurso) {
//
//                switch (recurso.status){
//
//                    case SUCESSO:
//
//                        dialogo.sucesso(recurso.messagem);
//                        break;
//
//                    case ERRO:
//
//                        dialogo.erro(recurso.messagem);
//                        break;
//
//                }
//            }
//        });


        //((InfoPagerAdapter) activityPokemonBinding.viewpagerContainer.getAdapter()).update((PokemonResponse) resource.data);
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

//        activityPlanoAccaoBinding.tab.getTabAt(ANUIDADE_1_FRAGMENT).setText(getString(R.string.tag_fragment_about));
//        activityPlanoAccaoBinding.tab.getTabAt(ANUIDADE_2_FRAGMENT).setText(getString(R.string.tag_fragment_notes));

    }



}
