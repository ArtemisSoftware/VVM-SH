package com.vvm.sh.ui.atividadesPendentes.relatorios.certificadoTratamento;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityCertificadoTratamentoBinding;
import com.vvm.sh.ui.AssinaturaActivity;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.interfaces.OnPermissaoConcedidaListener;
import com.vvm.sh.util.metodos.DiretoriasUtil;
import com.vvm.sh.util.metodos.ImagemUtil;
import com.vvm.sh.util.metodos.PermissoesUtil;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import butterknife.OnClick;

public class CertificadoTratamentoActivity extends BaseDaggerActivity {

    private ActivityCertificadoTratamentoBinding activityCertificadoTratamentoBinding;


    private CertificadoTratamentoViewModel viewModel;

    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory_).get(CertificadoTratamentoViewModel.class);

        activityCertificadoTratamentoBinding = (ActivityCertificadoTratamentoBinding) activityBinding;
        activityCertificadoTratamentoBinding.setLifecycleOwner(this);
        activityCertificadoTratamentoBinding.setViewmodel(viewModel);

        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {


            activityCertificadoTratamentoBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));

            subscreverObservadores();

            viewModel.obterRelatorio(bundle.getInt(getString(R.string.argumento_id_atividade)));
        }
        else{
            finish();
        }
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_certificado_tratamento;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

    }


    //--------------------
    //EVENTOS
    //--------------------

    @OnClick({R.id.card_certificado})
    public void card_certificado_OnClickListener(View view) {

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {

            int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

            Bundle bundleCertificado = new Bundle();
            bundleCertificado.putInt(getString(R.string.argumento_id_atividade), idAtividade);

            Intent intent = new Intent(this, CertificadoActivity.class);
            intent.putExtras(bundleCertificado);
            startActivity(intent);
        }
    }


    @OnClick({R.id.card_assinatura})
    public void card_assinatura_OnClickListener(View view) {

        Intent intent = new Intent(this, AssinaturaActivity.class);
        startActivityForResult(intent, Identificadores.CodigoAtividade.ASSINATURA);
    }


    @OnClick({R.id.fab_pre_visualizar})
    public void fab_pre_visualizar_OnClickListener(View view) {

        activityCertificadoTratamentoBinding.fabMenu.close(true);

        OnPermissaoConcedidaListener listener = new OnPermissaoConcedidaListener() {
            @Override
            public void executar() {

                Bundle bundle = getIntent().getExtras();
                int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

                if(DiretoriasUtil.criarDirectoria(DiretoriasUtil.DIRETORIA_PDF) == true){

                    viewModel.preVisualizarPdf(CertificadoTratamentoActivity.this, PreferenciasUtil.obterIdTarefa(getApplicationContext()), idAtividade, PreferenciasUtil.obterIdUtilizador(getApplicationContext()));
                }
            }
        };


        if(viewModel.relatorio.getValue().valido == false){
            dialogo.alerta(Sintaxe.Palavras.PDF, Sintaxe.Alertas.DADOS_INCOMPLETOS_PDF);
        }
        else {
            PermissoesUtil.pedirPermissoesEscritaLeitura(this, listener);
        }
    }



    @OnClick({R.id.fab_enviar})
    public void fab_enviar_OnClickListener(View view) {


        activityCertificadoTratamentoBinding.fabMenu.close(true);

        OnPermissaoConcedidaListener listener = new OnPermissaoConcedidaListener() {
            @Override
            public void executar() {

                Bundle bundle = getIntent().getExtras();
                int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

                if(DiretoriasUtil.criarDirectoria(DiretoriasUtil.DIRETORIA_PDF) == true){
                    //viewModel.enviarPdf(getApplicationContext(), PreferenciasUtil.obterIdTarefa(getApplicationContext()), idAtividade, PreferenciasUtil.obterIdUtilizador(getApplicationContext()));
                }
            }
        };

        if(viewModel.relatorio.getValue().valido == false){
            dialogo.alerta(Sintaxe.Palavras.PDF, Sintaxe.Alertas.DADOS_INCOMPLETOS_PDF);
        }
        else if(viewModel.relatorio.getValue().email.equals("") == true){
            dialogo.alerta(Sintaxe.Palavras.PDF, Sintaxe.Alertas.EMAIL_NAO_ASSOCIADO);
        }
        else {
            PermissoesUtil.pedirPermissoesEscritaLeitura(this, listener);
        }
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Identificadores.CodigoAtividade.ASSINATURA) {

            if(resultCode == RESULT_OK){

                Bundle bundle = getIntent().getExtras();
                int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

                Bitmap bitmap = ImagemUtil.converter(data.getByteArrayExtra(getString(R.string.resultado_imagem)));
                byte[] imagem = ImagemUtil.converter(bitmap);

                viewModel.gravar(PreferenciasUtil.obterIdTarefa(this), idAtividade, imagem);
            }
        }
    }


}