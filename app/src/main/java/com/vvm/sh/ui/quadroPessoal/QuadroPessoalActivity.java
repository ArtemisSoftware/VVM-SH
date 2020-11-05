package com.vvm.sh.ui.quadroPessoal;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.SearchView;

import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.ColaboradorResultado;
import com.vvm.sh.databinding.ActivityQuadroPessoalBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.quadroPessoal.adaptadores.ColaboradorRecyclerAdapter;
import com.vvm.sh.ui.quadroPessoal.adaptadores.OnColaboradorListener;
import com.vvm.sh.ui.quadroPessoal.adaptadores.OnOpcoesColaboradorListener;
import com.vvm.sh.ui.quadroPessoal.modelos.ColaboradorRegisto;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

import butterknife.OnClick;

public class QuadroPessoalActivity extends BaseDaggerActivity
    implements OnColaboradorListener, OnOpcoesColaboradorListener, SearchView.OnQueryTextListener {


    private ActivityQuadroPessoalBinding activityQuadroPessoalBinding;

    @Inject
    ViewModelProviderFactory providerFactory;

    private QuadroPessoalViewModel viewModel;



    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(QuadroPessoalViewModel.class);

        activityQuadroPessoalBinding = (ActivityQuadroPessoalBinding) activityBinding;
        activityQuadroPessoalBinding.setLifecycleOwner(this);
        activityQuadroPessoalBinding.setListener(this);
        activityQuadroPessoalBinding.setViewmodel(viewModel);

        activityQuadroPessoalBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));
        activityQuadroPessoalBinding.nsv.setOnScrollChangeListener(nested_scroll_listener);

        subscreverObservadores();
        viewModel.obterRegistos(PreferenciasUtil.obterIdTarefa(this), true);

    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_quadro_pessoal;
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

                        dialogo.sucesso(recurso.messagem);
                        break;

                    case ERRO:

                        dialogo.erro(recurso.messagem);
                        break;
                }
            }
        });

    }


    //---------------------
    //Metodos locais
    //---------------------




    @Override
    public void OnColaboradorClick(ColaboradorRegisto registo) {

        DialogoOpcoesColaborador dialogo = DialogoOpcoesColaborador.newInstance(registo.id, registo.origem);
        dialogo.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void OnEditarColaborador(int id, int origem) {

        if(origem == Identificadores.Origens.ORIGEM_WS){
            DialogoColaborador dialogo = DialogoColaborador.newInstance(id);
            dialogo.show(getSupportFragmentManager(), "example dialog");
        }
        else {

            Intent intent = new Intent(this, ColaboradorActivity.class);
            intent.putExtra(getString(R.string.argumento_id_colaborador), id);
            startActivity(intent);
        }
    }

    @Override
    public void OnDemitirColaborador(ColaboradorRegisto registo) {

        int origem = Identificadores.Origens.ORIGEM_WS;

        ColaboradorResultado resultado = new ColaboradorResultado(PreferenciasUtil.obterIdTarefa(this), registo.id, Sintaxe.Palavras.DEMITIDO, origem);
        viewModel.gravar(registo.idResultado, resultado);
    }

    @Override
    public void OnReademitirColaborador(ColaboradorRegisto registo) {

        int origem = Identificadores.Origens.ORIGEM_WS;

        ColaboradorResultado resultado = new ColaboradorResultado(PreferenciasUtil.obterIdTarefa(this), registo.id, Sintaxe.Palavras.READEMITIDO, origem);
        viewModel.gravar(registo.idResultado, resultado);
    }

    @Override
    public void OnRemoverColaborador(int id) {
        viewModel.remover(PreferenciasUtil.obterIdTarefa(this), id);
    }

    @Override
    public void OnDetalheColaborador(int id) {

        //TODO: fazer
    }


    //----------------------
    //EVENTOS
    //----------------------


    NestedScrollView.OnScrollChangeListener nested_scroll_listener = new NestedScrollView.OnScrollChangeListener() {
        @Override
        public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            if(v.getChildAt(v.getChildCount() - 1) != null) {
                if ((scrollY >= (v.getChildAt(v.getChildCount() - 1).getMeasuredHeight() - v.getMeasuredHeight())) &&
                        scrollY > oldScrollY) {

                    if(((ColaboradorRecyclerAdapter)activityQuadroPessoalBinding.rclRegistos.getAdapter()) != null) {
                        ((ColaboradorRecyclerAdapter) activityQuadroPessoalBinding.rclRegistos.getAdapter()).displayLoading();
                    }

                    viewModel.carregarRegistos(PreferenciasUtil.obterIdTarefa(QuadroPessoalActivity.this));
                }
            }
        }
    };


    @OnClick({R.id.fab_adicionar})
    public void fab_adicionar_OnClickListener(View view) {

        Intent intent =  new Intent(this, ColaboradorActivity.class);
        startActivity(intent);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_quadro_pessoal, menu);
        MenuItem mSearch = menu.findItem(R.id.appSearchBar);
        SearchView mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setQueryHint(getString(R.string.pesquisa));
        mSearchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        viewModel.pesquisarQuadroPessoal(PreferenciasUtil.obterIdTarefa(this), query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        viewModel.pesquisarQuadroPessoal(PreferenciasUtil.obterIdTarefa(this), newText);
        return false;
    }

}
