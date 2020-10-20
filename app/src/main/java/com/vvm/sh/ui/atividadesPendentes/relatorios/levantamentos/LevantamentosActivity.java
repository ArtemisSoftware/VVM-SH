package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.LevantamentoRiscoResultado;
import com.vvm.sh.databinding.ActivityLevantamentosBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.adaptadores.OnLevantamentoListener;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.Levantamento;
import com.vvm.sh.ui.pesquisa.PesquisaActivity;
import com.vvm.sh.ui.pesquisa.modelos.Pesquisa;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.metodos.TiposUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.OnClick;

public class LevantamentosActivity extends BaseDaggerActivity
    implements OnLevantamentoListener.OnLevantamentoRegistoListener{


    private ActivityLevantamentosBinding activityLevantamentosBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private LevantamentosViewModel viewModel;


    private int idModelo;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(LevantamentosViewModel.class);

        activityLevantamentosBinding = (ActivityLevantamentosBinding) activityBinding;
        activityLevantamentosBinding.setLifecycleOwner(this);
        activityLevantamentosBinding.setViewmodel(viewModel);
        activityLevantamentosBinding.setListener(this);
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

        activityLevantamentosBinding.fabMenu.setVisibility(View.GONE);

        int idAtividade = getIntent().getExtras().getInt(getString(R.string.argumento_id_atividade));
        Bundle bundle = new Bundle();
        bundle.putInt(getString(R.string.argumento_id_atividade), idAtividade);

        if(registo != null){
            bundle.putInt(getString(R.string.argumento_id_levantamento), registo.id);


            Intent intent = new Intent(this, RelatorioLevantamentoActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else {

            bundle.putInt(getString(R.string.argumento_id_levantamento), 0);
            Intent intent = new Intent(this, PerigoTarefaActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);

        }


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

    @Override
    public void OnDuplicarClick(Levantamento levantamento) {
        viewModel.duplicarLevantamento(PreferenciasUtil.obterIdTarefa(this), levantamento.resultado);
    }

    @Override
    public void OnRemoverClick(Levantamento levantamento) {
        viewModel.removerLevantamento(PreferenciasUtil.obterIdTarefa(this), levantamento);
    }

    @Override
    public void OnGaleriaClick(Levantamento levantamento) {

    }

    @Override
    public void dialogoCategoriasProfissionais(int idModelo) {

       this.idModelo = idModelo;
        Pesquisa pesquisa = new Pesquisa(true, TiposUtil.MetodosTipos.CATEGORIAS_PROFISSIONAIS/*, viewModel.obterRegistosSelecionados()*/);

        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.argumento_configuracao_pesquisa), pesquisa);

        Intent intent = new Intent(this, PesquisaActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, Identificadores.CodigoAtividade.PESQUISA);

    }

    //-----------------------
    //EVENTOS
    //-----------------------

    @OnClick({R.id.fab_adicionar_levantamento})
    public void fab_adicionar_levantamento_OnClickListener(View view) {

        initLevantamento(null);

        activityLevantamentosBinding.fabMenu.close(true);
    }


    @OnClick({R.id.fab_adicionar_modelo})
    public void fab_adicionar_modelo_OnClickListener(View view) {

        Bundle bundle = getIntent().getExtras();
        int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

        DialogoModelos dialogo = DialogoModelos.newInstance(idAtividade);
        dialogo.show(getSupportFragmentManager(), "Dialogo");

        activityLevantamentosBinding.fabMenu.close(true);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Identificadores.CodigoAtividade.PESQUISA) {

            if(resultCode == RESULT_OK){

                ArrayList<Integer> resultado = data.getIntegerArrayListExtra(getString(R.string.resultado_pesquisa));

                Bundle bundle = getIntent().getExtras();
                int id = bundle.getInt(getString(R.string.argumento_id_levantamento));
                int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

                DialogoModeloCategoriasProfissionais dialogo = DialogoModeloCategoriasProfissionais.newInstance(idAtividade, this.idModelo , resultado);
                dialogo.show(getSupportFragmentManager(), "Dialogo");
            }
        }
    }

}
