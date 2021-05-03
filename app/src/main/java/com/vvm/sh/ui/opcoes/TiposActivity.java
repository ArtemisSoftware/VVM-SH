package com.vvm.sh.ui.opcoes;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityTiposBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.opcoes.adaptadores.OnTipoListener;
import com.vvm.sh.ui.opcoes.modelos.ResumoChecklist;
import com.vvm.sh.ui.opcoes.modelos.ResumoTipo;
import com.vvm.sh.ui.transferencias.adaptadores.OnTransferenciaListener;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.AtualizacaoUI_;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class TiposActivity extends BaseDaggerActivity
        implements OnTipoListener, OnTransferenciaListener {

    private ActivityTiposBinding activityTiposBinding;


    private OpcoesViewModel viewModel;



    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory_).get(OpcoesViewModel.class);

        activityTiposBinding = (ActivityTiposBinding) activityBinding;
        activityTiposBinding.setLifecycleOwner(this);
        activityTiposBinding.setListener(this);
        activityTiposBinding.setViewmodel(viewModel);

        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            viewModel.obterResumo(bundle.getInt(getString(R.string.argumento_id_tipo)));
        }
        else{
            finish();
        }

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

                        dialogo.erro(recurso.messagem, (String) recurso.dados);
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
    public void OnTipoLongPressListener(ResumoTipo resumo) {

        Bundle bundle = getIntent().getExtras();
        viewModel.recarregarItem(TiposActivity.this, resumo, bundle.getInt(getString(R.string.argumento_id_tipo)));
    }

    @Override
    public void OnTipoLongPressListener(ResumoChecklist resumo) {

        viewModel.recarregarItemChecklist(TiposActivity.this, resumo);
    }



    @Override
    public void atualizarTransferencia(AtualizacaoUI_ atualizacaoUI) {

    }

    @Override
    public void terminarTransferencia() {

    }

    @Override
    public void erroTransferencia() {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tipos, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Bundle bundle = getIntent().getExtras();

        switch (item.getItemId()){


            case R.id.item_recarregar_geral:

                viewModel.recarregarRegistos( bundle.getInt(getString(R.string.argumento_id_tipo)), TiposActivity.this);
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }



}
