package com.vvm.sh.ui.autenticacao;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.vvm.sh.MainActivity;
import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityAutenticacaoBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.baseDados.entidades.Utilizador;
import com.vvm.sh.ui.contaUtilizador.DefinicoesActivity;
import com.vvm.sh.ui.opcoes.AtualizacaoAppActivity;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.constantes.Testes;
import com.vvm.sh.util.metodos.Permissoes;
import com.vvm.sh.util.metodos.Preferencias;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class AutenticacaoActivity extends BaseDaggerActivity implements Validator.ValidationListener, OnSpinnerItemSelectedListener {

/*
    @BindView(R.id.spnr_utilizadores_teste)
    SpinnerComboBox spnr_utilizadores_teste;

*/



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
        activityAutenticacaoBinding.niceSpinner.setOnSpinnerItemSelectedListener(this);

        subscreverObservadores();

        Permissoes.pedirPermissoesApp(this);
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

                        //TODO: mensagem de erro
                        activityAutenticacaoBinding.crlBtnAutenticacao.setEnabled(false);
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

        Preferencias.fixarDadosUtilizador(this, activityAutenticacaoBinding.txtInpIdentificador.getText().toString());

        activityAutenticacaoBinding.crlBtnAutenticacao.setEnabled(true);
        activityAutenticacaoBinding.txtInpIdentificador.setText(Sintaxe.SEM_TEXTO);
        activityAutenticacaoBinding.txtInpPalavraChave.setText(Sintaxe.SEM_TEXTO);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



    //--------------------
    //Eventos
    //--------------------


    public void onAutenticarClick() {

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
    public void onItemSelected(NiceSpinner parent, View view, int position, long id) {

        Utilizador item = (Utilizador)parent.getItemAtPosition(position);

        txt_inp_identificador.setText(item.id);
        txt_inp_palavra_chave.setText(item.palavraChave);
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
