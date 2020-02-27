package com.vvm.sh.ui.atividadesPendentes;

import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Select;
import com.vvm.sh.R;
import com.vvm.sh.ui.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AcaoFormacaoActivity extends BaseActivity implements Validator.ValidationListener {


    @Select
    @BindView(R.id.spnr_designacao)
    Spinner spnr_designacao;


    @NotEmpty(message = "Preenchimento obrigatório")
    @BindView(R.id.txt_inp_local)
    TextInputEditText txt_inp_local;

    @NotEmpty(message = "Preenchimento obrigatório")
    @BindView(R.id.txt_inp_data)
    TextInputEditText txt_inp_data;

    @NotEmpty(message = "Preenchimento obrigatório")
    @BindView(R.id.txt_inp_inicio)
    TextInputEditText txt_inp_inicio;

    @NotEmpty(message = "Preenchimento obrigatório")
    @BindView(R.id.txt_inp_fim)
    TextInputEditText txt_inp_fim;



    private Validator validador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acao_formacao);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Validation
        validador = new Validator(this);
        validador.setValidationListener(this);

        subscreverObservadores();
        obterRegistos();
    }


    //------------------------
    //Metodos locais
    //------------------------


    private void obterRegistos(){

        //--TESTE (apagar quando houver dados)
/*
        Sinistralidade t1 = new Sinistralidade("1","2","3", "4","5");
        txt_inp_acidentes_trabalho.setText(t1.obterAcidentesComBaixa());
*/
        //TODO: chamar metodo do viewmodel
    }


    /**
     * Metodo que permite subscrever observadores
     */
    private void subscreverObservadores(){

        //TODO: subscrever observadores do viewmodel
    }



    //----------------------
    //Eventos
    //----------------------


    @OnClick(R.id.crl_btn_data)
    public void crl_btn_data_OnClickListener(View view) {
        //MetodosDatas.escolherData(view, txt_inp_data);
    }

    @OnClick(R.id.crl_btn_inicio)
    public void crl_btn_inicio_OnClickListener(View view) {

        //MetodosDatas.escolherHora(arg0, txt_inp_inicio, 1);
    }

    @OnClick(R.id.crl_btn_fim)
    public void crl_btn_fim_OnClickListener(View view) {
        //MetodosDatas.escolherHora(arg0, txt_inp_fim, 1);
    }


    @OnClick(R.id.fab_gravar)
    public void fab_gravar_OnClickListener(View view) {
        validador.validate();
    }


    @Override
    public void onValidationSucceeded() {

        //valido = MetodosValidacao.validarHorario(txt_inp_inicio, txt_inp_fim) & valido;
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

            if (view instanceof Spinner) {
                ((TextView) ((Spinner) view).getSelectedView()).setError(message);
            }
        }
    }
}
