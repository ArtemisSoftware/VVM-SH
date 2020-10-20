package com.vvm.sh.ui.registoVisita;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityRegistoVisitaBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.AssinaturaActivity;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.contaUtilizador.OpcoesAvancadasActivity;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.interfaces.OnPermissaoConcedidaListener;
import com.vvm.sh.util.metodos.BaseDadosUtil;
import com.vvm.sh.util.metodos.DiretoriasUtil;
import com.vvm.sh.util.metodos.ImagemUtil;
import com.vvm.sh.util.metodos.PermissoesUtil;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.io.File;

import javax.inject.Inject;

import butterknife.OnClick;

public class RegistoVisitaActivity extends BaseDaggerActivity {


    private ActivityRegistoVisitaBinding activityRegistoVisitaBinding;

    @Inject
    ViewModelProviderFactory providerFactory;


    private RegistoVisitaViewModel viewModel;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(RegistoVisitaViewModel.class);

        activityRegistoVisitaBinding = (ActivityRegistoVisitaBinding) activityBinding;
        activityRegistoVisitaBinding.setLifecycleOwner(this);
        activityRegistoVisitaBinding.setViewmodel(viewModel);

        activityRegistoVisitaBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));

        subscreverObservadores();

        viewModel.obterValidadeRegistoVisita(PreferenciasUtil.obterIdTarefa(this));
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

        Intent intent = new Intent(this, AssinaturaActivity.class);
        startActivityForResult(intent, Identificadores.CodigoAtividade.ASSINATURA);
    }


    @OnClick({R.id.fab_pre_visualizar})
    public void fab_pre_visualizar_OnClickListener(View view) {

        OnPermissaoConcedidaListener listener = new OnPermissaoConcedidaListener() {
            @Override
            public void executar() {

                if(DiretoriasUtil.criarDirectoria(DiretoriasUtil.DIRETORIA_PDF) == true){
                    viewModel.obterDadosPdf(PreferenciasUtil.obterIdTarefa(RegistoVisitaActivity.this));
                }
            }
        };

        PermissoesUtil.pedirPermissoesEscritaLeitura(this, listener);
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
