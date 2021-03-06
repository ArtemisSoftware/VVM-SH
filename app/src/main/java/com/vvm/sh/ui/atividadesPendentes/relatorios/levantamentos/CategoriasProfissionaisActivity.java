package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityCategoriasProfissionaisBinding;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.adaptadores.OnLevantamentoListener;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.CategoriaProfissional;
import com.vvm.sh.ui.pesquisa.modelos.Pesquisa;
import com.vvm.sh.ui.pesquisa.PesquisaActivity;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.metodos.TiposUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;

import butterknife.OnClick;

public class CategoriasProfissionaisActivity extends BaseDaggerActivity
        implements OnLevantamentoListener.OnCategoriaProfissionalListener{


    private ActivityCategoriasProfissionaisBinding activityCategoriasProfissionaisBinding;

    private LevantamentosViewModel viewModel;



    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory_).get(LevantamentosViewModel.class);

        activityCategoriasProfissionaisBinding = (ActivityCategoriasProfissionaisBinding) activityBinding;
        activityCategoriasProfissionaisBinding.setLifecycleOwner(this);
        activityCategoriasProfissionaisBinding.setViewmodel(viewModel);
        activityCategoriasProfissionaisBinding.setListener(this);
        activityCategoriasProfissionaisBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));


        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            int id = bundle.getInt(getString(R.string.argumento_id_levantamento));
            viewModel.obterCategoriasProfissionais(id);
        }
        else{
            finish();
        }

    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_categorias_profissionais;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

    }

    @Override
    public void OnCategoriaProfissionalClick(CategoriaProfissional registo) {

        Bundle bundle = getIntent().getExtras();
        int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));
        DialogoCategoriasProfissionais dialogo = DialogoCategoriasProfissionais.newInstance(idAtividade, registo);
        dialogo.show(getSupportFragmentManager(), "Dialogo");
    }


    @Override
    public void OnRemoverClick(CategoriaProfissional categoria) {

        Bundle bundle = getIntent().getExtras();
        int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));
        viewModel.remover(PreferenciasUtil.obterIdTarefa(this), idAtividade, categoria.categoria);
    }


    //-----------------------
    //EVENTOS
    //-----------------------

    @OnClick({R.id.fab_adicionar})
    public void fab_adicionar_levantamento_OnClickListener(View view) {

        Pesquisa pesquisa = new Pesquisa(true, TiposUtil.MetodosTipos.CATEGORIAS_PROFISSIONAIS/*, viewModel.obterRegistosSelecionados()*/);

        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.argumento_configuracao_pesquisa), pesquisa);

        Intent intent = new Intent(this, PesquisaActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, Identificadores.CodigoAtividade.PESQUISA);
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
                viewModel.gravarCategoriasProfissionais(PreferenciasUtil.obterIdTarefa(this), idAtividade, id, resultado);
            }
        }
    }


}
