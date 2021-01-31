package com.vvm.sh.ui.atividadesPendentes.relatorios.certificadoTratamento;


import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.CertificadoTratamentoResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.databinding.ActivityCertificadoBinding;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import butterknife.OnClick;

public class CertificadoActivity  extends BaseDaggerActivity {

    private ActivityCertificadoBinding activityCertificadoBinding;

    private CertificadoTratamentoViewModel viewModel;

    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory_).get(CertificadoTratamentoViewModel.class);

        activityCertificadoBinding = (ActivityCertificadoBinding) activityBinding;
        activityCertificadoBinding.setLifecycleOwner(this);
        activityCertificadoBinding.setViewmodel(viewModel);

        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            viewModel.obterCertificado(bundle.getInt(getString(R.string.argumento_id_atividade)));
            activityCertificadoBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));

        }
        else{
            finish();
        }

    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_certificado;
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

                        dialogo.sucesso(recurso.messagem, listenerActivity);
                        break;

                    case ERRO:

                        dialogo.erro(recurso.messagem);
                        break;

                }
            }
        });

    }


    @OnClick(R.id.fab_gravar)
    public void fab_gravar_OnClickListener(View view) {

        Bundle bundle = getIntent().getExtras();
        int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

        Tipo praga = (Tipo) activityCertificadoBinding.spnrTipoPraga.getItems().get(activityCertificadoBinding.spnrTipoPraga.getSelectedIndex());
        Tipo visita = (Tipo) activityCertificadoBinding.spnrVisitas.getItems().get(activityCertificadoBinding.spnrVisitas.getSelectedIndex());

        Tipo produto = (Tipo) activityCertificadoBinding.spnrProdutoAplicado.getItems().get(activityCertificadoBinding.spnrProdutoAplicado.getSelectedIndex());
        Tipo condicoesHigiene = (Tipo) activityCertificadoBinding.spnrCondicoesHigiene.getItems().get(activityCertificadoBinding.spnrCondicoesHigiene.getSelectedIndex());
        Tipo manutencaoInstalacoes = (Tipo) activityCertificadoBinding.spnrManutencaoInstalacoes.getItems().get(activityCertificadoBinding.spnrManutencaoInstalacoes.getSelectedIndex());
        Tipo condicoesArmazenamento = (Tipo) activityCertificadoBinding.spnrCondicoesArmazenamento.getItems().get(activityCertificadoBinding.spnrCondicoesArmazenamento.getSelectedIndex());

        boolean chk_obs_1 = activityCertificadoBinding.chkObs1.isChecked();
        boolean chk_obs_2 = activityCertificadoBinding.chkObs2.isChecked();
        String observacao = activityCertificadoBinding.txtInpObservacao.getText().toString();

        CertificadoTratamentoResultado resultado = new CertificadoTratamentoResultado(idAtividade, praga.id, visita.id, produto.id, condicoesHigiene.id, manutencaoInstalacoes.id, condicoesArmazenamento.id, chk_obs_1, chk_obs_2, observacao);

        viewModel.gravar(resultado);

    }


}