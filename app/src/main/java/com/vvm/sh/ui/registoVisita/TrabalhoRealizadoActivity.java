package com.vvm.sh.ui.registoVisita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.TrabalhoRealizadoResultado;
import com.vvm.sh.databinding.ActivityTrabalhoRealizadoBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.registoVisita.adaptadores.OnRegistoVisitaListener;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class TrabalhoRealizadoActivity extends BaseDaggerActivity
    implements OnRegistoVisitaListener {


    private ActivityTrabalhoRealizadoBinding activityTrabalhoRealizadoBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private RegistoVisitaViewModel viewModel;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(RegistoVisitaViewModel.class);

        activityTrabalhoRealizadoBinding = (ActivityTrabalhoRealizadoBinding) activityBinding;
        activityTrabalhoRealizadoBinding.setLifecycleOwner(this);
        activityTrabalhoRealizadoBinding.setListener(this);
        activityTrabalhoRealizadoBinding.setViewmodel(viewModel);


        viewModel.obterTrabalhos(PreferenciasUtil.obterIdTarefa(this));

    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_trabalho_realizado;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

    }


    @Override
    public void onItemChecked(int idTrabalhoRealizado, boolean selecao) {

        if(selecao == true) {


            if(idTrabalhoRealizado == Identificadores.ID_FORMACAO_TRABALHO_REALIZADO) {

                DialogoTrabalhoRealizado dialogo = DialogoTrabalhoRealizado.newInstance(idTrabalhoRealizado);
                dialogo.show(getSupportFragmentManager(), "example dialog");
            }
            else{

                TrabalhoRealizadoResultado resultado = new TrabalhoRealizadoResultado(PreferenciasUtil.obterIdTarefa(this), idTrabalhoRealizado);

                viewModel.gravar(resultado);
            }
        }
        else{
            viewModel.remover(PreferenciasUtil.obterIdTarefa(this), idTrabalhoRealizado);
        }
    }


    //---------------------
    //Eventos
    //---------------------
}
