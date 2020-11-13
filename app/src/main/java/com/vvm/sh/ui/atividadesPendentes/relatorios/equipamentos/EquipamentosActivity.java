package com.vvm.sh.ui.atividadesPendentes.relatorios.equipamentos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.VerificacaoEquipamentoResultado;
import com.vvm.sh.databinding.ActivityEquipamentosBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.equipamentos.adaptadores.EquipamentoRecyclerHolder;
import com.vvm.sh.ui.atividadesPendentes.relatorios.equipamentos.modelos.Equipamento;
import com.vvm.sh.ui.pesquisa.adaptadores.OnPesquisaListener;
import com.vvm.sh.ui.pesquisa.PesquisaViewModel;
import com.vvm.sh.ui.pesquisa.adaptadores.PesquisaRecyclerAdapter;
import com.vvm.sh.ui.pesquisa.modelos.Pesquisa;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.OnClick;
import butterknife.OnTextChanged;

public class EquipamentosActivity extends BaseDaggerActivity
        implements OnPesquisaListener.OnPesquisaEquipamentoRegistoListener, OnPesquisaListener.OnPesquisaEquipamentoSelecionadoListener {


    private ActivityEquipamentosBinding activityEquipamentosBinding;

    @Inject
    ViewModelProviderFactory providerFactory;


    private PesquisaViewModel viewModel;



    private List<String> registosSelecionados;
    private List<Equipamento> registos;

    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(PesquisaViewModel.class);

        activityEquipamentosBinding = (ActivityEquipamentosBinding) activityBinding;
        activityEquipamentosBinding.setLifecycleOwner(this);
        activityEquipamentosBinding.setViewmodel(viewModel);
        activityEquipamentosBinding.setListenerRegisto(this);
        activityEquipamentosBinding.setListenerSelecionado(this);
        activityEquipamentosBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));


        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            registosSelecionados = new ArrayList<>();
            registos = new ArrayList<>();

            activityEquipamentosBinding.rclRegistos.addOnScrollListener(rcl_registos_scroll_listener);

            int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));
            viewModel.obterRegistadosEquipamentos(idAtividade);
        }
        else{
            finish();
        }
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_equipamentos;
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

                    default:
                        break;
                }

            }
        });

        viewModel.observarEquipamentos().observe(this, new Observer<List<String>>() {

            @Override
            public void onChanged(List<String> equipamentos) {

                registosSelecionados = equipamentos;
                viewModel.obterEquipamentos(registosSelecionados, false);
            }
        });

        viewModel.observarEquipamentosSelecionados().observe(this, new Observer<List<Equipamento>>() {

            @Override
            public void onChanged(List<Equipamento> equipamentos) {
                registos = equipamentos;
            }
        });
    }


    //--------------------
    //Metodos locais
    //--------------------

    /**
     * Metodo que permite obter os registos
     * @param reiniciar true caso se pretenda reiniciar a pesquisa
     */
    private void obterRegistos(boolean reiniciar){

        if (activityEquipamentosBinding.txtInpPesquisa.getText().toString().equals(Sintaxe.SEM_TEXTO) == true) {
            viewModel.obterEquipamentos(registosSelecionados, reiniciar);
        }
        else {
            viewModel.carregarEquipamentos(registosSelecionados, activityEquipamentosBinding.txtInpPesquisa.getText().toString());
        }
    }

    @Override
    public void OnSelecionarClick(Equipamento registo) {

        registosSelecionados.add(registo.descricao);
        registos.add(registo);
        viewModel.obterEquipamentos(registosSelecionados, false);
    }

    @Override
    public void OnRemoverSelecao(Equipamento registo) {

        int index = registosSelecionados.indexOf(registo.descricao);

        registosSelecionados.remove(index);
        registos.remove(index);
        viewModel.obterEquipamentos(registosSelecionados, false);
    }


    //------------------
    //Eventos
    //--------------------


    RecyclerView.OnScrollListener rcl_registos_scroll_listener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            if (!recyclerView.canScrollVertically(1) & newState == 2) {

                if(((EquipamentoRecyclerHolder)activityEquipamentosBinding.rclRegistos.getAdapter()) != null) {
                    ((EquipamentoRecyclerHolder) activityEquipamentosBinding.rclRegistos.getAdapter()).displayLoading();
                }

                if (activityEquipamentosBinding.txtInpPesquisa.getText().toString().equals(Sintaxe.SEM_TEXTO) == true) {
                    viewModel.carregarEquipamentos(registosSelecionados);
                } else {
                    viewModel.carregarEquipamentos(registosSelecionados, activityEquipamentosBinding.txtInpPesquisa.getText().toString());
                }
            }
        };
    };





    @OnTextChanged(value = R.id.txt_inp_pesquisa, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void txt_inp_pesquisa_OnTextChanged(CharSequence text) {

        Bundle bundle = getIntent().getExtras();
        int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

        obterRegistos(true);
    }



    @OnClick(R.id.crl_btn_limpar)
    public void crl_btn_limpar_OnClickListener(View view) {

        Bundle bundle = getIntent().getExtras();
        int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));
        activityEquipamentosBinding.txtInpPesquisa.setText("");

        viewModel.obterEquipamentos(registosSelecionados, true);
    }



    @OnClick(R.id.fab_gravar)
    public void fab_gravar_OnClickListener(View view) {

        activityEquipamentosBinding.fabMenu.close(true);

        Bundle bundle = getIntent().getExtras();
        int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

        List<VerificacaoEquipamentoResultado> itens = new ArrayList<>();

        for (Equipamento item : registos) {
            itens.add(new VerificacaoEquipamentoResultado(idAtividade, item.id, item.estado));
        }

        viewModel.gravar(PreferenciasUtil.obterIdTarefa(this), idAtividade, itens);
    }


    @OnClick(R.id.fab_adicionar_equipamento)
    public void fab_adicionar_equipamento_OnClickListener(View view) {

        activityEquipamentosBinding.fabMenu.close(true);

        DialogoEquipamento dialogo = new DialogoEquipamento();
        dialogo.show(getSupportFragmentManager(), "example dialog");
    }
}
