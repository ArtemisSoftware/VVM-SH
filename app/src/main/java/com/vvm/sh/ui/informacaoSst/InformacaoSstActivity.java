package com.vvm.sh.ui.informacaoSst;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityInformacaoSstBinding;
import com.vvm.sh.databinding.ActivityObrigacoesLegaisBinding;
import com.vvm.sh.ui.AssinaturaActivity;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.registoVisita.DadosClienteActivity;
import com.vvm.sh.ui.registoVisita.RegistoVisitaActivity;
import com.vvm.sh.ui.registoVisita.TrabalhoRealizadoActivity;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
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

    }


    //--------------------
    //EVENTOS
    //--------------------


    @OnClick({R.id.card_obrigacoes_legais})
    public void card_card_obrigacoes_legais_OnClickListener(View view) {

        Intent intent = new Intent(this, ObrigacoesLegaisActivity.class);
        startActivity(intent);
    }


    @OnClick({R.id.card_assinatura})
    public void card_assinatura_OnClickListener(View view) {

        Intent intent = new Intent(this, AssinaturaActivity.class);
        startActivityForResult(intent, Identificadores.CodigoAtividade.ASSINATURA);
    }


    @OnClick({R.id.fab_pre_visualizar})
    public void fab_pre_visualizar_OnClickListener(View view) {

        activityInformacaoSstBinding.fabMenu.close(true);

        OnPermissaoConcedidaListener listener = new OnPermissaoConcedidaListener() {
            @Override
            public void executar() {

                if(DiretoriasUtil.criarDirectoria(DiretoriasUtil.DIRETORIA_PDF) == true){
                    viewModel.preVisualizarPdf(getApplicationContext(), PreferenciasUtil.obterIdTarefa(getApplicationContext()), PreferenciasUtil.obterIdUtilizador(getApplicationContext()));
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

//        OnPermissaoConcedidaListener listener = new OnPermissaoConcedidaListener() {
//            @Override
//            public void executar() {
//
//                if(DiretoriasUtil.criarDirectoria(DiretoriasUtil.DIRETORIA_PDF) == true){
//                    viewModel.enviarPdf(RegistoVisitaActivity.this, PreferenciasUtil.obterIdTarefa(RegistoVisitaActivity.this), PreferenciasUtil.obterIdUtilizador(RegistoVisitaActivity.this));
//                }
//            }
//        };
//
//        if(viewModel.relatorio.getValue().valido == false){
//            dialogo.alerta(Sintaxe.Palavras.PDF, Sintaxe.Alertas.DADOS_INCOMPLETOS_PDF);
//        }
//        else if(viewModel.relatorio.getValue().email.equals("") == true){
//            dialogo.alerta(Sintaxe.Palavras.PDF, Sintaxe.Alertas.EMAIL_NAO_ASSOCIADO);
//        }
//        else {
//            PermissoesUtil.pedirPermissoesEscritaLeitura(this, listener);
//        }
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