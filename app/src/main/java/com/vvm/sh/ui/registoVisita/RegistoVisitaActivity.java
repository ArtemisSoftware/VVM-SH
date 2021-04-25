package com.vvm.sh.ui.registoVisita;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityRegistoVisitaBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.documentos.OnDocumentoListener;
import com.vvm.sh.ui.AssinaturaActivity;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.informacaoSst.InformacaoSstActivity;
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

import javax.inject.Inject;

import butterknife.OnClick;

public class RegistoVisitaActivity extends BaseDaggerActivity {


    private ActivityRegistoVisitaBinding activityRegistoVisitaBinding;

    private RegistoVisitaViewModel viewModel;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory_).get(RegistoVisitaViewModel.class);

        activityRegistoVisitaBinding = (ActivityRegistoVisitaBinding) activityBinding;
        activityRegistoVisitaBinding.setLifecycleOwner(this);
        activityRegistoVisitaBinding.setViewmodel(viewModel);

        activityRegistoVisitaBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));

        subscreverObservadores();

        viewModel.obterRelatorioRegistoVisita(PreferenciasUtil.obterIdTarefa(this));
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_registo_visita;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

        viewModel.observarRelatorio().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

                if(aBoolean == false){
                    activityRegistoVisitaBinding.fabEnviar.setVisibility(View.GONE);
                    activityRegistoVisitaBinding.fabPreVisualizar.setVisibility(View.GONE);
                    card_dados_cliente_OnClickListener(null);
                }
            }
        });


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

                }
            }
        });
    }


    //--------------------
    //EVENTOS
    //--------------------


    @OnClick({R.id.card_dados_cliente})
    public void card_dados_cliente_OnClickListener(View view) {

        Intent intent = new Intent(this, DadosClienteActivity.class);
        startActivity(intent);
    }


    @OnClick({R.id.card_trabalhos_realizados})
    public void card_trabalhos_realizados_OnClickListener(View view) {

        Intent intent = new Intent(this, TrabalhoRealizadoActivity.class);
        startActivity(intent);
    }


    @OnClick({R.id.card_assinatura})
    public void card_assinatura_OnClickListener(View view) {

        OnDialogoListener listener = new OnDialogoListener() {
            @Override
            public void onExecutar() {
                Intent intent = new Intent(RegistoVisitaActivity.this, AssinaturaRegistoVisitaActivity.class);
                startActivityForResult(intent, Identificadores.CodigoAtividade.ASSINATURA);
            }
        };
        dialogo.alerta(getString(R.string.assinatura), getString(R.string.assinatura_bloquea_edicao_relatorio), listener);
    }


    @OnClick({R.id.fab_pre_visualizar})
    public void fab_pre_visualizar_OnClickListener(View view) {

        activityRegistoVisitaBinding.fabMenu.close(true);

        OnPermissaoConcedidaListener listener = new OnPermissaoConcedidaListener() {
            @Override
            public void executar() {

                if(DiretoriasUtil.criarDirectoria(DiretoriasUtil.DIRETORIA_PDF) == true){
                    viewModel.executarPdf(RegistoVisitaActivity.this, PreferenciasUtil.obterIdTarefa(getApplicationContext()), PreferenciasUtil.obterIdUtilizador(getApplicationContext()), OnDocumentoListener.AcaoDocumento.PRE_VISUALIZAR_PDF);
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

        activityRegistoVisitaBinding.fabMenu.close(true);

        OnPermissaoConcedidaListener listener = new OnPermissaoConcedidaListener() {
            @Override
            public void executar() {

                if(DiretoriasUtil.criarDirectoria(DiretoriasUtil.DIRETORIA_PDF) == true){
                    viewModel.executarPdf(RegistoVisitaActivity.this, PreferenciasUtil.obterIdTarefa(getApplicationContext()), PreferenciasUtil.obterIdUtilizador(getApplicationContext()), OnDocumentoListener.AcaoDocumento.ENVIAR_PDF);
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

                boolean homologado = data.getBooleanExtra(getString(R.string.resultado_homologado), false);

                viewModel.gravar(PreferenciasUtil.obterIdTarefa(this), imagem, homologado);
            }
        }
    }
}
