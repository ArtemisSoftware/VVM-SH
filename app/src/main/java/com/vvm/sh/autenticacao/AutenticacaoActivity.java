package com.vvm.sh.autenticacao;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.vvm.sh.MainActivity;
import com.vvm.sh.R;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.ui.contaUtilizador.DefinicoesActivity;
import com.vvm.sh.ui.opcoes.AtualizacaoAppActivity;
import com.vvm.sh.util.adaptadores.SpinnerComboBox;
import com.vvm.sh.util.constantes.Testes;
import com.vvm.sh.util.metodos.Permissoes;

import java.util.List;

import at.markushi.ui.CircleButton;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class AutenticacaoActivity extends BaseActivity implements Validator.ValidationListener {


    @BindView(R.id.spnr_utilizadores_teste)
    SpinnerComboBox spnr_utilizadores_teste;


    @NotEmpty(message = "Preenchimento obrigatório")
    @BindView(R.id.txt_inp_identificador)
    TextInputEditText txt_inp_identificador;


    @NotEmpty(message = "Preenchimento obrigatório")
    @BindView(R.id.txt_inp_palavra_chave)
    TextInputEditText txt_inp_palavra_chave;


    @BindView(R.id.crl_btn_autenticacao)
    CircleButton crl_btn_autenticacao;



    private Validator validador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autenticacao);

        validador = new Validator(this);
        validador.setValidationListener(this);

        subscreverObservadores();
        obterRegistos();
        Permissoes.pedirPermissoesApp(this);
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

        spnr_utilizadores_teste.setAdapter(Testes.obterUtilizadores(this));


        //TODO: chamar metodo do viewmodel
    }

    /**
     * Metodo que permite subscrever observadores
     */
    private void subscreverObservadores(){

        //apresentarProgresso(true);

        //TODO: subscrever observadores do viewmodel
    }


    //--------------------
    //Eventos
    //--------------------


    @OnItemSelected(R.id.spnr_utilizadores_teste)
    public void spnr_utilizadores_teste_ItemSelected(Spinner spinner, int position) {

        if(spnr_utilizadores_teste.selecionado() == true) {
            txt_inp_identificador.setText(spnr_utilizadores_teste.obterItem().obterId() + "");
            txt_inp_palavra_chave.setText(spnr_utilizadores_teste.obterItem().obterCodigo());
        }
    }



    @OnClick(R.id.crl_btn_autenticacao)
    public void crl_btn_autenticacao_OnClickListener(View view) {

        validador.validate();

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


    @Override
    public void onValidationSucceeded() {

        //Todo: chamar view model


        Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra(AppConstants.PICTURE, pictureRecyclerAdapter.getSelectedPicture(position).getId());
        startActivity(intent);
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
}
