package com.vvm.sh.ui.quadroPessoal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.ColaboradorResultado;
import com.vvm.sh.databinding.ActivityQuadroPessoalBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
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
    implements OnColaboradorListener, OnOpcoesColaboradorListener {


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


        viewModel.obterQuadroPessoal(PreferenciasUtil.obterIdTarefa(this));
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


    @OnClick({R.id.fab_adicionar})
    public void fab_adicionar_OnClickListener(View view) {

        Intent intent =  new Intent(this, ColaboradorActivity.class);
        startActivity(intent);
    }
}
