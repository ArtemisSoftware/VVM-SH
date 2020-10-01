package com.vvm.sh.ui.atividadesPendentes.relatorios.equipamentos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.databinding.ActivityEquipamentosBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.pesquisa.OnPesquisaListener;
import com.vvm.sh.ui.pesquisa.PesquisaViewModel;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.metodos.TiposUtil;
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



    private List<Integer> registosSelecionados;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(PesquisaViewModel.class);

        activityEquipamentosBinding = (ActivityEquipamentosBinding) activityBinding;
        activityEquipamentosBinding.setLifecycleOwner(this);
        activityEquipamentosBinding.setViewmodel(viewModel);
        activityEquipamentosBinding.setListenerRegisto(this);
        activityEquipamentosBinding.setListenerSelecionado(this);
        activityEquipamentosBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            registosSelecionados = new ArrayList<>();

            int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));
            viewModel.obterEquipamentos(idAtividade);
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

    }


    //--------------------
    //Metodos locais
    //--------------------

    @Override
    public void OnSelecionarClick(Equipamento registo) {

        Bundle bundle = getIntent().getExtras();
        int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

        registosSelecionados.add(registo.id);
        viewModel.obterEquipamentos(idAtividade, registosSelecionados);
    }

    @Override
    public void OnRemoverSelecao(Equipamento registo) {

        Bundle bundle = getIntent().getExtras();
        int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

        registosSelecionados.remove(registosSelecionados.indexOf(registo.id));
        viewModel.obterEquipamentos(idAtividade, registosSelecionados);
    }


    @OnTextChanged(value = R.id.txt_inp_pesquisa, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void txt_inp_pesquisa_OnTextChanged(CharSequence text) {

        Bundle bundle = getIntent().getExtras();
        int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

        viewModel.pesquisarEquipamento(idAtividade, registosSelecionados, text.toString());
    }



    @OnClick(R.id.crl_btn_limpar)
    public void crl_btn_limpar_OnClickListener(View view) {

        Bundle bundle = getIntent().getExtras();
        int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));


        registosSelecionados.clear();
        viewModel.obterEquipamentos(idAtividade, registosSelecionados);
    }



    @OnClick(R.id.fab_gravar)
    public void fab_gravar_OnClickListener(View view) {


        activityEquipamentosBinding.fabMenu.close(true);

        Bundle bundle = getIntent().getExtras();
        int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

        //--viewModel.gravar(idAtividade, registosSelecionados);
    }


    @OnClick(R.id.fab_adicionar_equipamento)
    public void fab_adicionar_equipamento_OnClickListener(View view) {


        activityEquipamentosBinding.fabMenu.close(true);

        Bundle bundle = getIntent().getExtras();
        int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));


        //DialogoEquipamento dialogo = DialogoEquipamento.newInstance();
    }
}
