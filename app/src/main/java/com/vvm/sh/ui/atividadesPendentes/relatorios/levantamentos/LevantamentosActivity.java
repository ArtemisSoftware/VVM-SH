package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.LevantamentoRiscoResultado;
import com.vvm.sh.databinding.ActivityLevantamentosBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.Levantamento;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

import butterknife.OnClick;

public class LevantamentosActivity extends BaseDaggerActivity
    implements OnLevantamentoListener.OnLevantamentoRegistoListener{


    private ActivityLevantamentosBinding activityLevantamentosBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private LevantamentosViewModel viewModel;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(LevantamentosViewModel.class);

        activityLevantamentosBinding = (ActivityLevantamentosBinding) activityBinding;
        activityLevantamentosBinding.setLifecycleOwner(this);
        activityLevantamentosBinding.setViewmodel(viewModel);
        activityLevantamentosBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));

        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));
            viewModel.obterLevantamentos(idAtividade);
        }
        else{
            finish();
        }
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_levantamentos;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

    }




    /**
     * Metodo que inicia o levantamento
     */
    private void initLevantamento(LevantamentoRiscoResultado registo){




        activityLevantamentosBinding.rclRegistos.setVisibility(View.GONE);
        activityLevantamentosBinding.lnrLytLevantamento.setVisibility(View.VISIBLE);

        viewModel.obterRelatorio(registo.id);

        //int idLevantamento = Sintaxe.SEM_REGISTO;

        //Bundle bundle = getIntent().getExtras();

//

//        try{
//            idLevantamento = adaptador.obterRegistoSelecionado().obterId();
//        }
//        catch(NullPointerException w){
//            idLevantamento = acessoBdLevantamentos.copiar(((Levantamentos_Adaptador) adaptador).obterUltimoRegisto());
//
//            MetodosMensagens.gerarToast(this, SintaxeIF.DADOS_GRAVADOS_SUCESSO);
//
//            terminarRegisto();
//            atualizarEstatistica();
//        }

//        Intent intent = new Intent(this, IndiceLevantamentoActivity.class);
//        bundle.putString(BundleIF.ID_RELATORIO, idLevantamento);
//        intent.putExtras(bundle);
//        startActivityForResult(intent, CodigoAtividadeIF.LEVANTAMENTO_RISCOS);
    }


    @Override
    public void OnLevantamentoClick(Levantamento registo) {

        initLevantamento(registo.resultado);
    }

    //-----------------------
    //EVENTOS
    //-----------------------

    @OnClick({R.id.fab_adicionar_levantamento})
    public void fab_adicionar_levantamento_OnClickListener(View view) {

        initLevantamento(null);
    }

    @OnClick({R.id.crd_perigo_tarefa})
    public void crd_perigo_tarefa_OnClickListener(View view) {

        Bundle bundle = getIntent().getExtras();

        Intent intent = new Intent(this, PerigoTarefaActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
