package com.vvm.sh.ui.transferencias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.vvm.sh.MainActivity;
import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityAtualizacaoTiposBinding;
import com.vvm.sh.databinding.ActivityCarregamentoBinding;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.apresentacao.ApresentacaoActivity;
import com.vvm.sh.ui.autenticacao.AutenticacaoActivity;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import butterknife.OnClick;

public class AtualizacaoTiposActivity extends BaseDaggerActivity {


    private ActivityAtualizacaoTiposBinding activityAtualizacaoTiposBinding;

    private TransferenciasViewModel viewModel;

    private AtualizacaoUI.Comunicado comunicado;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory_).get(TransferenciasViewModel.class);

        activityAtualizacaoTiposBinding = (ActivityAtualizacaoTiposBinding) activityBinding;
        activityAtualizacaoTiposBinding.setLifecycleOwner(this);
        activityAtualizacaoTiposBinding.setViewmodel(viewModel);

        subscreverObservadores();

        atualizar();
    }



    @Override
    protected int obterLayout() {
        return R.layout.activity_atualizacao_tipos;
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

                    case ERRO:

                        terminarAtualizacao();
                        dialogo.erro(getString(R.string.erro), recurso.messagem);
                        break;

                }
            }
        });
    }



    final Handler handlerNotificacoesUI = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            AtualizacaoUI.Comunicado comunicado = (AtualizacaoUI.Comunicado) msg.obj;

            switch (comunicado.obterCodigo()) {

                case PROCESSAMENTO_TIPOS:

                    activityAtualizacaoTiposBinding.setComunicado(comunicado);
                    break;

                case PROCESSAMENTO_TIPOS_CONCLUIDO:

                    terminarAtualizacao();
                    break;



                default:
                    break;
            }

            super.handleMessage(msg);
        }
    };



    //----------------------------------------
    //Metodos locais
    //----------------------------------------


    private void atualizar() {
        if(PreferenciasUtil.obterPrimeiraUtilizacao(this) == true) {
            viewModel.atualizarTipos(this, handlerNotificacoesUI, PreferenciasUtil.obterPrimeiraUtilizacao(this));
        }
        else{
            viewModel.atualizarTipos(this, handlerNotificacoesUI);
        }
    }

    /**
     * Metod que permite terminar a atualizacao
     */
    private void terminarAtualizacao() {
        activityAtualizacaoTiposBinding.txtTitulo.setText(getString(R.string.atualizacao_concluida));
        activityAtualizacaoTiposBinding.txtDescricao.setVisibility(View.INVISIBLE);
        activityAtualizacaoTiposBinding.txtSubTitulo.setVisibility(View.VISIBLE);
        activityAtualizacaoTiposBinding.crlProsseguir.setVisibility(View.VISIBLE);

        activityAtualizacaoTiposBinding.progressBar.setVisibility(View.INVISIBLE);
        activityAtualizacaoTiposBinding.circle.setVisibility(View.INVISIBLE);
        activityAtualizacaoTiposBinding.resultImage.setVisibility(View.INVISIBLE);
        activityAtualizacaoTiposBinding.imgLogo.setVisibility(View.VISIBLE);
    }


    //--------------------
    //EVENTOS
    //--------------------


    @OnClick({R.id.crl_prosseguir})
    public void crl_prosseguir_ButtonClick(View view) {

        if(PreferenciasUtil.obterPrimeiraUtilizacao(this) == true){
            finish();
            Intent intent = new Intent(this, ApresentacaoActivity.class);
            startActivity(intent);
        }
        else{
            finish();
        }

    }
}