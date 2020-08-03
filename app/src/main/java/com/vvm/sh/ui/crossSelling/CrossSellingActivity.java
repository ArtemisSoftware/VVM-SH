package com.vvm.sh.ui.crossSelling;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityCrossSellingBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.crossSelling.adaptadores.OnCrossSellingListener;
import com.vvm.sh.ui.crossSelling.modelos.CrossSelling;
import com.vvm.sh.baseDados.entidades.CrossSellingResultado;
import com.vvm.sh.ui.opcoes.modelos.Tipo;
import com.vvm.sh.util.metodos.Preferencias;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;


public class CrossSellingActivity extends BaseDaggerActivity
        implements OnCrossSellingListener, MaterialSpinner.OnItemSelectedListener {


    private ActivityCrossSellingBinding binding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private CrossSellingViewModel viewModel;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(CrossSellingViewModel.class);

        binding = (ActivityCrossSellingBinding) activityBinding;
        binding.setLifecycleOwner(this);
        binding.setListener(this);
        binding.setViewmodel(viewModel);

        binding.spnrAreaRecomendacao.setOnItemSelectedListener(this);

        subscreverObservadores();

        viewModel.obterProdutos();
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_cross_selling;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

        //TODO: subscrever observadores do viewmodel
    }



    //---------------------
    //Eventos
    //---------------------

    @Override
    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

        Tipo tipo = (Tipo) item;
        viewModel.obterCrossSelling(tipo);
    }



    @Override
    public void onItemChecked(CrossSelling crossSelling, boolean selecao) {

        boolean sinaletica = Boolean.parseBoolean(((Tipo) binding.spnrAreaRecomendacao.getItems().get(binding.spnrAreaRecomendacao.getSelectedIndex())).detalhe);
        Tipo area = (Tipo) binding.spnrAreaRecomendacao.getItems().get(binding.spnrAreaRecomendacao.getSelectedIndex());

        if(selecao == true & sinaletica == false){


            CrossSellingResultado registo = new CrossSellingResultado(Preferencias.obterIdTarefa(this), area.id, crossSelling.id);
            viewModel.gravar(registo);
        }
        else if(selecao == true & sinaletica == true){

            DialogoSinaletica dialogo = DialogoSinaletica.newInstance(area.id, crossSelling.id);
            dialogo.show(getSupportFragmentManager(), "example dialog");
        }
        else{

            viewModel.remover(crossSelling);
        }
    }


}
