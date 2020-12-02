package com.vvm.sh.ui.autenticacao;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.vvm.sh.MainActivity;
import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityAutenticacaoBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.baseDados.entidades.Utilizador;
import com.vvm.sh.ui.apresentacao.ApresentacaoActivity;
import com.vvm.sh.ui.contaUtilizador.DefinicoesActivity;
import com.vvm.sh.ui.opcoes.AtualizacaoAppActivity;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.constantes.Testes;
import com.vvm.sh.util.metodos.PermissoesUtil;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class AutenticacaoActivity extends BaseDaggerActivity
        implements Validator.ValidationListener, MaterialSpinner.OnItemSelectedListener {


    @NotEmpty(message = "Preenchimento obrigatório")
    @BindView(R.id.txt_inp_identificador)
    TextInputEditText txt_inp_identificador;


    @NotEmpty(message = "Preenchimento obrigatório")
    @BindView(R.id.txt_inp_palavra_chave)
    TextInputEditText txt_inp_palavra_chave;



    private ActivityAutenticacaoBinding activityAutenticacaoBinding;

    @Inject
    ViewModelProviderFactory providerFactory;


    private AutenticacaoViewModel viewModel;


    private Validator validador;



    @Override
    protected void intActivity(Bundle savedInstanceState) {

        validador = new Validator(this);
        validador.setValidationListener(this);

        viewModel = ViewModelProviders.of(this, providerFactory).get(AutenticacaoViewModel.class);

        activityAutenticacaoBinding = (ActivityAutenticacaoBinding) activityBinding;
        activityAutenticacaoBinding.setLifecycleOwner(this);
        activityAutenticacaoBinding.setViewmodel(viewModel);
        activityAutenticacaoBinding.setActivity(this);

        activityAutenticacaoBinding.setUtilizadoresTestes(Testes.obterUtilizadores());
        activityAutenticacaoBinding.spnrUtilizadorTeste.setOnItemSelectedListener(this);

        subscreverObservadores();

        PermissoesUtil.pedirPermissoesApp(this);
        PreferenciasUtil.realizarApresentacaoApp(this);

    }


    @Override
    protected int obterLayout() {
        return R.layout.activity_autenticacao;
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

                        iniciarApp();
                        break;

                    case ERRO:

                        activityAutenticacaoBinding.crlBtnAutenticacao.setEnabled(true);
                        dialogo.erro(getString(R.string.erro), recurso.messagem);
                        break;

                }
            }
        });
    }



    //------------------------
    //Metodos locais
    //------------------------


    /**
     * Metodo que permite iniciar a aplicacao
     */
    private void iniciarApp(){

        PreferenciasUtil.fixarDadosUtilizador(this, activityAutenticacaoBinding.txtInpIdentificador.getText().toString());

        activityAutenticacaoBinding.crlBtnAutenticacao.setEnabled(true);
        activityAutenticacaoBinding.txtInpIdentificador.setText(Sintaxe.SEM_TEXTO);
        activityAutenticacaoBinding.txtInpPalavraChave.setText(Sintaxe.SEM_TEXTO);

        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



    //--------------------
    //Eventos
    //--------------------


    @OnClick(R.id.crl_btn_autenticacao)
    public void crl_btn_autenticacao_OnClickListener(View view) {
        activityAutenticacaoBinding.crlBtnAutenticacao.setEnabled(false);
        validador.validate();
    }


    @Override
    public void onValidationSucceeded() {
        viewModel.autenticar(activityAutenticacaoBinding.txtInpIdentificador.getText().toString(), activityAutenticacaoBinding.txtInpPalavraChave.getText().toString());
    }


    @Override
    public void onValidationFailed(List<ValidationError> errors) {

        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof TextInputEditText) {
                ((TextInputEditText) view).setError(message);
                activityAutenticacaoBinding.crlBtnAutenticacao.setEnabled(true);
            }
        }
    }

    @Override
    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
        Utilizador utilizador = (Utilizador)item;

        txt_inp_identificador.setText(utilizador.id);
        txt_inp_palavra_chave.setText(utilizador.palavraChave);
    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_autenticacao, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;

        switch (item.getItemId()){

            case R.id.item_definicoes:

                intent = new Intent(this, DefinicoesActivity.class);
                startActivity(intent);
                return true;


            case R.id.item_atualizar_app:

                intent = new Intent(this, AtualizacaoAppActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
