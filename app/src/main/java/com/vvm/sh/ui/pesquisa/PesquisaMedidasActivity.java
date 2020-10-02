package com.vvm.sh.ui.pesquisa;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.databinding.ActivityPesquisaMedidasBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.pesquisa.adaptadores.PesquisaViewModel;
import com.vvm.sh.ui.pesquisa.modelos.Medida;
import com.vvm.sh.ui.pesquisa.modelos.Pesquisa;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.OnClick;

public class PesquisaMedidasActivity extends BaseDaggerActivity implements OnPesquisaListener.OnPesquisaMedidaListener {


    private ActivityPesquisaMedidasBinding activityPesquisaMedidasBinding;

    @Inject
    ViewModelProviderFactory providerFactory;


    private PesquisaViewModel viewModel;

    private Pesquisa pesquisa;





    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(PesquisaViewModel.class);

        activityPesquisaMedidasBinding = (ActivityPesquisaMedidasBinding) activityBinding;
        activityPesquisaMedidasBinding.setLifecycleOwner(this);
        activityPesquisaMedidasBinding.setViewmodel(viewModel);
        activityPesquisaMedidasBinding.setListener(this);
        activityPesquisaMedidasBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            pesquisa = bundle.getParcelable(getString(R.string.argumento_configuracao_pesquisa));
            activityPesquisaMedidasBinding.setPesquisa(pesquisa);
            viewModel.obterMedidas(pesquisa.metodo, pesquisa.registosSelecionados);
        }
        else{
            finish();
        }
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_pesquisa_medidas;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

    }

    @Override
    public void OnSelecionarClick(Medida registo, boolean selecionado) {

        if(selecionado == true){
            pesquisa.registosSelecionados.add(registo.tipo.id);
        }
        else{
            pesquisa.registosSelecionados.remove(pesquisa.registosSelecionados.indexOf(registo.tipo.id));
        }

    }



    //-----------------------
    //EVENTOS
    //-----------------------


    @OnClick(R.id.crl_btn_limpar)
    public void crl_btn_limpar_OnClickListener(View view) {

        pesquisa.registosSelecionados.clear();
        viewModel.obterRegistos(pesquisa.metodo, pesquisa.registosSelecionados);
    }


    @OnClick(R.id.fab_gravar)
    public void fab_gravar_OnClickListener(View view) {

        if(pesquisa.registosSelecionados.size() > 0) {

            Intent returnIntent = new Intent();
            returnIntent.putIntegerArrayListExtra(getString(R.string.resultado_pesquisa), (ArrayList<Integer>) pesquisa.registosSelecionados);

            if(pesquisa.escolhaMultipla == false) {
                returnIntent.putExtra(getString(R.string.resultado_pesquisa_descricao), pesquisa.descricao);
            }
            setResult(RESULT_OK, returnIntent);
            finish();
        }
        else{

            dialogo.alerta(getString(R.string.pesquisa), getString(R.string.registos_nao_selecionados));

        }
    }



}
