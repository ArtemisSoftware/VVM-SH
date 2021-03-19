package com.vvm.sh.ui.transferencias.atualizacaoApp;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.api.modelos.pedido.IVersaoApp;
import com.vvm.sh.databinding.ActivityAtualizacaoAppBinding;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.agenda.DialogoOpcoesTrabalhoFragment;
import com.vvm.sh.ui.autenticacao.PerfilActivity;
import com.vvm.sh.ui.contaUtilizador.DefinicoesActivity;
import com.vvm.sh.ui.contaUtilizador.OpcoesAvancadasActivity;
import com.vvm.sh.ui.opcoes.OpcoesViewModel;
import com.vvm.sh.ui.transferencias.UploadTrabalhoActivity;
import com.vvm.sh.ui.transferencias.adaptadores.OnTransferenciaListener;
import com.vvm.sh.util.AtualizacaoUI_;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.interfaces.OnPermissaoConcedidaListener;
import com.vvm.sh.util.metodos.DiretoriasUtil;
import com.vvm.sh.util.metodos.PermissoesUtil;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import butterknife.OnClick;

public class AtualizacaoAppActivity extends BaseDaggerActivity implements OnTransferenciaListener {

    private ActivityAtualizacaoAppBinding activityAtualizacaoAppBinding;

    private AtualizacaoAppViewModel viewModel;



    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory_).get(AtualizacaoAppViewModel.class);

        activityAtualizacaoAppBinding = (ActivityAtualizacaoAppBinding) activityBinding;
        activityAtualizacaoAppBinding.setLifecycleOwner(this);
        activityAtualizacaoAppBinding.setViewmodel(viewModel);

        subscreverObservadores();

        viewModel.obterAtualizacao(PreferenciasUtil.obterIdUtilizador(this));
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_atualizacao_app;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

        viewModel.versaoApp.observe(this, new Observer<IVersaoApp>() {
            @Override
            public void onChanged(IVersaoApp iVersaoApp) {

                if(iVersaoApp.atualizar == false){
                    dialogo.alerta(getString(R.string.atualizacao), getString(R.string.app_atualizada), listenerActivity);
                }
            }
        });


        viewModel.observarMessagem().observe(this, new Observer<Recurso>() {
            @Override
            public void onChanged(Recurso recurso) {

                switch (recurso.status){

                    case ERRO:

                        dialogo.erro(recurso.messagem, (String) recurso.dados);
                        break;

                    default:
                        break;
                }

            }
        });

    }


//    //----------------------
//    //Eventos
//    //----------------------
//
//    @OnClick(R.id.btn_cancelar)
//    public void btn_cancelar_OnClickListener(View view) {
//
//        VersaoApp versaoApp = viewModel.versaoApp.getValue();
//
//        if(versaoApp.atualizar == true) {
//            NotificacaoUtil.notificarAtualizacaoApp(getApplication(), versaoApp.versao);
//        }
//
//        finish();
//    }

    @OnClick(R.id.btn_atualizar)
    public void btn_atualizar_OnClickListener(View view) {

        activityAtualizacaoAppBinding.progressBarProgresso.setProgress(0);
        activityAtualizacaoAppBinding.txtProgresso.setText(Sintaxe.SEM_TEXTO);


        OnPermissaoConcedidaListener listener = new OnPermissaoConcedidaListener() {
            @Override
            public void executar() {

                if(DiretoriasUtil.criarDirectoria(DiretoriasUtil.DOWNLOAD) == true){
                    viewModel.downloadApp(AtualizacaoAppActivity.this, AtualizacaoAppActivity.this);
                }
            }
        };

        PermissoesUtil.pedirPermissoesEscritaLeitura(this, listener);
    }


    @Override
    public void atualizarTransferencia(AtualizacaoUI_ atualizacaoUI) {

        switch (atualizacaoUI.estado){

            case PROCESSAMENTO_DOWNLOAD_ATUALIZACAO_APP:

                activityAtualizacaoAppBinding.setAtualizacao(atualizacaoUI);
                break;

            case ERRO_INSTALACAO_ATUALIZACAO_APP:
            case ERRO_DOWNLOAD_ATUALIZACAO_APP:

                dialogo.erro(atualizacaoUI.mensagem);
                break;
        }

    }

    @Override
    public void terminarTransferencia() {
        viewModel.instalarApp(this, this);
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.item_copiar_link:


                break;


            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }



}
