package com.vvm.sh.autenticacao;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;

import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.vvm.sh.MainActivity;
import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityAtualizacaoAppBinding;
import com.vvm.sh.databinding.ActivityAutenticacaoBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.contaUtilizador.DefinicoesActivity;
import com.vvm.sh.ui.opcoes.AtualizacaoAppActivity;
import com.vvm.sh.ui.opcoes.OpcoesViewModel;
import com.vvm.sh.util.adaptadores.SpinnerComboBox;
import com.vvm.sh.util.constantes.Testes;
import com.vvm.sh.util.metodos.Permissoes;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import at.markushi.ui.CircleButton;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class AutenticacaoActivity extends BaseDaggerActivity implements Validator.ValidationListener {

/*
    @BindView(R.id.spnr_utilizadores_teste)
    SpinnerComboBox spnr_utilizadores_teste;





    @BindView(R.id.crl_btn_autenticacao)
    CircleButton crl_btn_autenticacao;

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

        //TODO: subscrever observadores do viewmodel
    }


    private void iniciarApp(){



        /*
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("token", "string value");
        editor.commit();
        */
    }


    //------------------------
    //Metodos locais
    //------------------------


    private void obterRegistos(){

        //--TESTE (apagar quando houver dados)

        //--spnr_utilizadores_teste.setAdapter(Testes.obterUtilizadores(this));


        //TODO: chamar metodo do viewmodel
    }



    //--------------------
    //Eventos
    //--------------------


    public void onAutenticarClick() {

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
            }
        }
    }






    //@OnItemSelected(R.id.spnr_utilizadores_teste)
//    public void spnr_utilizadores_teste_ItemSelected(Spinner spinner, int position) {
//
//        if(spnr_utilizadores_teste.selecionado() == true) {
//            txt_inp_identificador.setText(spnr_utilizadores_teste.obterItem().obterId() + "");
//            txt_inp_palavra_chave.setText(spnr_utilizadores_teste.obterItem().obterCodigo());
//        }
//    }







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
