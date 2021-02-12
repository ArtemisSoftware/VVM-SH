package com.vvm.sh.ui.informacaoSst;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityInformacaoSstBinding;
import com.vvm.sh.databinding.ActivityObrigacoesLegaisBinding;
import com.vvm.sh.documentos.OnDocumentoListener;
import com.vvm.sh.ui.AssinaturaActivity;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.certificadoTratamento.CertificadoTratamentoActivity;
import com.vvm.sh.ui.registoVisita.DadosClienteActivity;
import com.vvm.sh.ui.registoVisita.DialogoTrabalhoRealizado;
import com.vvm.sh.ui.registoVisita.RegistoVisitaActivity;
import com.vvm.sh.ui.registoVisita.TrabalhoRealizadoActivity;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.interfaces.OnDialogoListener;
import com.vvm.sh.util.interfaces.OnPermissaoConcedidaListener;
import com.vvm.sh.util.metodos.DiretoriasUtil;
import com.vvm.sh.util.metodos.ImagemUtil;
import com.vvm.sh.util.metodos.PermissoesUtil;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import butterknife.OnClick;

public class InformacaoSstActivity extends BaseDaggerActivity {

    private ActivityInformacaoSstBinding activityInformacaoSstBinding;

    private InformacaoSstViewModel viewModel;



    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory_).get(InformacaoSstViewModel.class);

        activityInformacaoSstBinding = (ActivityInformacaoSstBinding) activityBinding;
        activityInformacaoSstBinding.setLifecycleOwner(this);
        activityInformacaoSstBinding.setViewmodel(viewModel);

        activityInformacaoSstBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));
        subscreverObservadores();

        viewModel.obterRelatorio(PreferenciasUtil.obterIdTarefa(this));
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_informacao_sst;
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

                        dialogo.sucesso(recurso.messagem);
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


    //--------------------
    //EVENTOS
    //--------------------


    @OnClick({R.id.card_dados_cliente})
    public void card_dados_cliente_OnClickListener(View view) {

        DialogoResponsavel dialogo = DialogoResponsavel.newInstance(activityInformacaoSstBinding.txtResponsavel.getText().toString());
        dialogo.show(getSupportFragmentManager(), "example dialog");
    }


    @OnClick({R.id.card_obrigacoes_legais})
    public void card_card_obrigacoes_legais_OnClickListener(View view) {

        Intent intent = new Intent(this, ObrigacoesLegaisActivity.class);
        startActivity(intent);
    }


    @OnClick({R.id.card_assinatura})
    public void card_assinatura_OnClickListener(View view) {

        OnDialogoListener listener = new OnDialogoListener() {
            @Override
            public void onExecutar() {
                Intent intent = new Intent(InformacaoSstActivity.this, AssinaturaActivity.class);
                startActivityForResult(intent, Identificadores.CodigoAtividade.ASSINATURA);
            }
        };

        dialogo.alerta(getString(R.string.assinatura), getString(R.string.assinatura_bloquea_edicao_relatorio), listener);
    }


    @OnClick({R.id.fab_pre_visualizar})
    public void fab_pre_visualizar_OnClickListener(View view) {

        activityInformacaoSstBinding.fabMenu.close(true);

        OnPermissaoConcedidaListener listener = new OnPermissaoConcedidaListener() {
            @Override
            public void executar() {

                if(DiretoriasUtil.criarDirectoria(DiretoriasUtil.DIRETORIA_PDF) == true){
                    viewModel.executarPdf(InformacaoSstActivity.this, PreferenciasUtil.obterIdTarefa(getApplicationContext()), PreferenciasUtil.obterIdUtilizador(getApplicationContext()), OnDocumentoListener.AcaoDocumento.PRE_VISUALIZAR_PDF);
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

        activityInformacaoSstBinding.fabMenu.close(true);

        OnPermissaoConcedidaListener listener = new OnPermissaoConcedidaListener() {
            @Override
            public void executar() {

                if(DiretoriasUtil.criarDirectoria(DiretoriasUtil.DIRETORIA_PDF) == true){
                    viewModel.executarPdf(InformacaoSstActivity.this, PreferenciasUtil.obterIdTarefa(getApplicationContext()), PreferenciasUtil.obterIdUtilizador(getApplicationContext()), OnDocumentoListener.AcaoDocumento.ENVIAR_PDF__DADOS_FTP);
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





    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Identificadores.CodigoAtividade.ASSINATURA) {

            if(resultCode == RESULT_OK){

                Bitmap bitmap = ImagemUtil.converter(data.getByteArrayExtra(getString(R.string.resultado_imagem)));
                byte[] imagem = ImagemUtil.converter(bitmap);

                viewModel.gravar(PreferenciasUtil.obterIdTarefa(this), imagem);
            }
        }
    }


}