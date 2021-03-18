package com.vvm.sh.ui.transferencias;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityAtualizacaoTiposBinding;
import com.vvm.sh.databinding.ActivityAtualizarDadosAppBinding;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.apresentacao.ApresentacaoActivity;
import com.vvm.sh.ui.apresentacao.modelos.Apresentacao;
import com.vvm.sh.ui.transferencias.adaptadores.OnTransferenciaListener;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.AtualizacaoUI_;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Apresentacoes;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import butterknife.OnClick;

public class AtualizacaoDadosActivity extends BaseDaggerActivity implements OnTransferenciaListener {


    private ActivityAtualizarDadosAppBinding activityAtualizacaoTiposBinding;

    private TransferenciasViewModel viewModel;



    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory_).get(TransferenciasViewModel.class);

        activityAtualizacaoTiposBinding = (ActivityAtualizarDadosAppBinding) activityBinding;
        activityAtualizacaoTiposBinding.setLifecycleOwner(this);
        activityAtualizacaoTiposBinding.setViewmodel(viewModel);

        subscreverObservadores();

        atualizar();
    }



    @Override
    protected int obterLayout() {
        return R.layout.activity_atualizar_dados_app;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() { }




    //----------------------------------------
    //Metodos locais
    //----------------------------------------


    private void atualizar() {

        activityAtualizacaoTiposBinding.setAtualizacaoTipos(new AtualizacaoUI_(getString(R.string.por_favor_aguarde)));
        activityAtualizacaoTiposBinding.setAtualizacaoActivPlaneaveis(new AtualizacaoUI_(getString(R.string.por_favor_aguarde)));


        if(PreferenciasUtil.obterPrimeiraUtilizacao(this) == true) {
            viewModel.atualizarDados(this, true);
        }
        else{

            switch (Apresentacoes.ATUALIZACAO.tipoAtualizacao){

                case RECARRECAR_DADOS:

                    viewModel.recarregarDados(this);
                    break;

                case ATUALIZAR_DADOS:

                    viewModel.atualizarDados(this, false);
                    break;


                default:
                    break;

            }
        }
    }


    //--------------------
    //EVENTOS
    //--------------------

    @Override
    public void atualizarTransferencia(AtualizacaoUI_ atualizacaoUI) {

        switch (atualizacaoUI.estado){

            case PROCESSAMENTO_TIPOS:

                activityAtualizacaoTiposBinding.setAtualizacaoTipos(atualizacaoUI);
                break;

            case PROCESSAMENTO_ATIVIDADES_PLANEAVEIS:

                activityAtualizacaoTiposBinding.setAtualizacaoActivPlaneaveis(atualizacaoUI);
                break;

            default:
                break;

        }
    }

    @Override
    public void terminarTransferencia() {

        activityAtualizacaoTiposBinding.txtTitulo.setText(getString(R.string.atualizacao_concluida));
        activityAtualizacaoTiposBinding.txtDetalhe.setVisibility(View.INVISIBLE);
        activityAtualizacaoTiposBinding.txtSubTitulo.setVisibility(View.VISIBLE);
        activityAtualizacaoTiposBinding.crlProsseguir.setVisibility(View.VISIBLE);
    }



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