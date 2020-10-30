package com.vvm.sh.ui.pesquisa;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.databinding.ActivityPesquisaBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.pesquisa.adaptadores.OnPesquisaListener;
import com.vvm.sh.ui.pesquisa.adaptadores.PesquisaMedidaRecyclerAdapter;
import com.vvm.sh.ui.pesquisa.adaptadores.PesquisaRecyclerAdapter;
import com.vvm.sh.ui.pesquisa.modelos.Pesquisa;
import com.vvm.sh.ui.quadroPessoal.adaptadores.ColaboradorRecyclerAdapter;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.OnClick;
import butterknife.OnTextChanged;

public class PesquisaActivity extends BaseDaggerActivity
    implements OnPesquisaListener.OnPesquisaSelecionadoListener, OnPesquisaListener.OnPesquisaRegistoListener{

    private ActivityPesquisaBinding activityPesquisaBinding;

    @Inject
    ViewModelProviderFactory providerFactory;


    private PesquisaViewModel viewModel;

    private Pesquisa pesquisa;




    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(PesquisaViewModel.class);

        activityPesquisaBinding = (ActivityPesquisaBinding) activityBinding;
        activityPesquisaBinding.setLifecycleOwner(this);
        activityPesquisaBinding.setViewmodel(viewModel);
        activityPesquisaBinding.setListenerRegisto(this);
        activityPesquisaBinding.setListenerSelecionado(this);
        activityPesquisaBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            pesquisa = bundle.getParcelable(getString(R.string.argumento_configuracao_pesquisa));
            activityPesquisaBinding.setPesquisa(pesquisa);
            activityPesquisaBinding.rclRegistos.addOnScrollListener(rcl_registos_scroll_listener);

            subscreverObservadores();

            viewModel.obterRegistos(pesquisa, false);
        }
        else{
            finish();
        }
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_pesquisa;
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

                    case LOADING:

                        if(((PesquisaRecyclerAdapter)activityPesquisaBinding.rclRegistos.getAdapter()) != null) {
                            ((PesquisaRecyclerAdapter) activityPesquisaBinding.rclRegistos.getAdapter()).displayLoading();
                        }
                        break;

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
    //Metodos locais
    //--------------------


    @Override
    public void OnSelecionarClick(Tipo registo) {

        if(pesquisa.escolhaMultipla == true) {
            pesquisa.registosSelecionados.add(registo.id);
        }
        else{
            pesquisa.descricao = registo.descricao;
            pesquisa.registosSelecionados.clear();
            pesquisa.registosSelecionados.add(registo.id);
        }
        viewModel.obterRegistos(pesquisa, false);
    }

    @Override
    public void OnRemoverSelecao(Tipo registo) {

        if(pesquisa.escolhaMultipla == true) {
            pesquisa.registosSelecionados.remove(pesquisa.registosSelecionados.indexOf(registo.id));
        }
        else{
            pesquisa.registosSelecionados.clear();
        }

        viewModel.obterRegistos(pesquisa, false);
    }


    //-----------------------
    //EVENTOS
    //-----------------------

    RecyclerView.OnScrollListener rcl_registos_scroll_listener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            if (!recyclerView.canScrollVertically(1) & newState == 2) {

                if(((PesquisaRecyclerAdapter)activityPesquisaBinding.rclRegistos.getAdapter()) != null) {
                    ((PesquisaRecyclerAdapter) activityPesquisaBinding.rclRegistos.getAdapter()).displayLoading();
                }


                if (activityPesquisaBinding.txtInpPesquisa.getText().toString().equals(Sintaxe.SEM_TEXTO) == true) {
                    viewModel.carregarRegistos(pesquisa);
                } else {
                    viewModel.carregarPesquisa(pesquisa, activityPesquisaBinding.txtInpPesquisa.getText().toString());
                }
            }
        };
    };

    @OnTextChanged(value = R.id.txt_inp_pesquisa, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void txt_inp_pesquisa_OnTextChanged(CharSequence text) {

        if(text.toString().equals(Sintaxe.SEM_TEXTO) == true){
            viewModel.obterRegistos(pesquisa, true);
        }
        else {
            viewModel.obterPesquisa(pesquisa, text.toString(), true);
        }
    }



    @OnClick(R.id.crl_btn_limpar)
    public void crl_btn_limpar_OnClickListener(View view) {

        pesquisa.registosSelecionados.clear();
        viewModel.obterRegistos(pesquisa, true);
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
