package com.vvm.sh.ui.tarefa;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.thefinestartist.finestwebview.FinestWebView;
import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityTarefaBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.anomalias.AnomaliasActivity;
import com.vvm.sh.ui.atividadesExecutadas.AtividadesExecutadasActivity;
import com.vvm.sh.ui.atividadesPendentes.AtividadesPendentesActivity;
import com.vvm.sh.ui.planoAccao.PlanoAccaoActivity;
import com.vvm.sh.ui.registoVisita.RegistoVisitaActivity;
import com.vvm.sh.ui.cliente.InformacaoActivity;
import com.vvm.sh.ui.cliente.SinistralidadeActivity;
import com.vvm.sh.ui.crossSelling.CrossSellingActivity;
import com.vvm.sh.ui.ocorrencias.OcorrenciasActivity;
import com.vvm.sh.ui.parqueExtintores.ExtintoresActivity;
import com.vvm.sh.ui.quadroPessoal.QuadroPessoalActivity;
import com.vvm.sh.ui.tarefa.adaptadores.OnTarefaListener;
import com.vvm.sh.ui.tarefa.modelos.OpcaoCliente;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.constantes.Url;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

import butterknife.OnClick;

public class TarefaActivity extends BaseDaggerActivity
        implements OnTarefaListener {

    private ActivityTarefaBinding activityTarefaBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private TarefaViewModel viewModel;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(TarefaViewModel.class);

        activityTarefaBinding = (ActivityTarefaBinding) activityBinding;
        activityTarefaBinding.setLifecycleOwner(this);
        activityTarefaBinding.setListener(this);
        activityTarefaBinding.setViewmodel(viewModel);

        subscreverObservadores();

        viewModel.obterTarefa(PreferenciasUtil.obterIdTarefa(this));
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_tarefa;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

    }



    //---------------------
    //Eventos
    //---------------------


    @Override
    public void OnOpcaoItemListener(OpcaoCliente opcao) {


        Intent intent = null;

        switch (opcao.id){

            case Identificadores.OpcoesCliente.OPCAO_INFORMACAO:

                intent = new Intent(this, InformacaoActivity.class);
                break;

            case Identificadores.OpcoesCliente.OPCAO_CROSS_SELLING:

                intent = new Intent(this, CrossSellingActivity.class);
                break;

            case Identificadores.OpcoesCliente.OPCAO_SINISTRALIDADE:

                intent = new Intent(this, SinistralidadeActivity.class);
                break;


            case Identificadores.OpcoesCliente.OPCAO_PARQUE_EXTINTORES:

                intent = new Intent(this, ExtintoresActivity.class);
                break;


            case Identificadores.OpcoesCliente.OPCAO_QUADRO_PESSOAL:

                intent = new Intent(this, QuadroPessoalActivity.class);
                break;

            case Identificadores.OpcoesCliente.OPCAO_REGISTO_VISITA:

                intent = new Intent(this, RegistoVisitaActivity.class);
                break;


            case Identificadores.OpcoesCliente.OPCAO_PLANO_ACAO:

                intent = new Intent(this, PlanoAccaoActivity.class);
                break;

            case Identificadores.OpcoesCliente.OPCAO_EMAIL:

                DialogoEmail dialogo = new DialogoEmail();
                dialogo.show(getSupportFragmentManager(), "example dialog");
                break;


            default:
                break;
        }

        if(intent != null) {
            startActivity(intent);
        }

    }




    @OnClick(R.id.crd_atividades_pendentes)
    public void crd_atividades_pendentes_OnClickListener(View view) {
        Intent intent = new Intent(this, AtividadesPendentesActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.crd_atividades_executadas)
    public void crd_atividades_executadas_OnClickListener(View view) {
        Intent intent = new Intent(this, AtividadesExecutadasActivity.class);
        startActivity(intent);
    }



    @OnClick(R.id.crd_anomalias)
    public void crd_anomalias_OnClickListener(View view) {
        Intent intent = new Intent(this, AnomaliasActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.crd_ocorrencias)
    public void crd_ocorrencias_OnClickListener(View view) {
        Intent intent = new Intent(this, OcorrenciasActivity.class);
        startActivity(intent);
    }



    @OnClick(R.id.crd_conta_corrente)
    public void crd_conta_corrente_OnClickListener(View view) {

        String url = Url.URL_CONTA_CORRENTE + viewModel.tarefaDia.getValue().cliente.nif;

        new FinestWebView.Builder(getApplicationContext()).titleDefault(Sintaxe.Frases.CONTA_CORRENTE_NIF + viewModel.tarefaDia.getValue().cliente.nif)
                .showMenuShareVia(false)
                .showMenuCopyLink(false)
                .showMenuOpenWith(false)
                .show(url);
    }


}
