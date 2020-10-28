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
import com.vvm.sh.util.AtualizacaoUI;
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




    //----------------------------------------
    //HANDLER (notificacoes para o ui)
    //----------------------------------------


    final Handler handlerNotificacoesUI = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            AtualizacaoUI.Comunicado comunicado = (AtualizacaoUI.Comunicado) msg.obj;

            switch (comunicado.obterCodigo()) {

                case CONCLUIR_ATUALIZACAO_TIPO:

                    dialogo.sucesso(Sintaxe.Palavras.ATUALIZACAO, comunicado.obterDados());
                    break;


                default:
                    //TODO: alerta de erro

                    //--Alerta de erro
                    //if(comunicado.obterMensagem() != null)
                    //--AlertaUI.erro(dialogo, comunicado.obterMensagem())
                    break;
            }

            super.handleMessage(msg);
        }
    };




    //---------------------
    //Eventos
    //---------------------


    @Override
    public void OnTipoLongPressListener(String metodo) {
        viewModel.atualizarTipo(metodo, handlerNotificacoesUI);
    }

    @Override
    public void OnTipoLongPressListener(ResumoChecklist resumo) {

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

                viewModel.recarregarRegistos( bundle.getInt(getString(R.string.argumento_id_tipo)), TiposActivity.this, handlerNotificacoesUI);
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}
