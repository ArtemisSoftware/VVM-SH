package com.vvm.sh.ui.opcoes;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityTiposBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.opcoes.adaptadores.OnTipoListener;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class TiposActivity extends BaseDaggerActivity implements OnTipoListener {

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

        //TODO: subscrever observadores do viewmodel
    }

    //------------------------
    //Metodos locais
    //------------------------




    //---------------------
    //Eventos
    //---------------------


    @Override
    public void OnTipoLongPressListener(String metodo) {
        viewModel.atualizarTipo(metodo);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);

/*
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        String titulo = colecaoRecyclerAdapter.obterRegisto(info.position).obterDescricao();
        menu.setHeaderTitle(titulo);

        menu.add(Menu.NONE, 1, Menu.NONE, getString(R.string.recarregar_tipo));
        */

    }

    @Override
    public boolean onContextItemSelected(MenuItem item){

        switch (item.getItemId()){

            case 1:
                break;

            default:
                break;
        }

        return true;
    }


}
