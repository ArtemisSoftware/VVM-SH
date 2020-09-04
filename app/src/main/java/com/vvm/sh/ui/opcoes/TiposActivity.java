package com.vvm.sh.ui.opcoes;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityTiposBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.opcoes.adaptadores.OnTipoListener;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class TiposActivity extends BaseDaggerActivity
        implements OnTipoListener {

    private ActivityTiposBinding activityTiposBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private OpcoesViewModel viewModel;



    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(OpcoesViewModel.class);

        activityTiposBinding = (ActivityTiposBinding) activityBinding;
        activityTiposBinding.setLifecycleOwner(this);
        activityTiposBinding.setListener(this);
        activityTiposBinding.setViewmodel(viewModel);

        subscreverObservadores();

        viewModel.obterTipos();

    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_tipos;
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

                        dialogo.sucesso(Sintaxe.Palavras.ATUALIZACAO, recurso.messagem);
                        break;

                    case ERRO:

                        dialogo.erro(recurso.messagem);
                        break;

                    default:
                        break;
                }

            }
        });
    }



    //---------------------
    //Eventos
    //---------------------


    @Override
    public void OnTipoLongPressListener(String metodo) {
        viewModel.atualizarTipo(metodo);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tipos, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){


            case R.id.item_recarregar_geral:

                viewModel.recarregarTipos();
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}
