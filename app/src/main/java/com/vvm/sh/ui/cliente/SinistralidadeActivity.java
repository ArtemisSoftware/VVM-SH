package com.vvm.sh.ui.cliente;

import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.DecimalMin;
import com.mobsandgeeks.saripaar.annotation.Digits;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.vvm.sh.R;
import com.vvm.sh.ui.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;


public class SinistralidadeActivity extends BaseActivity implements Validator.ValidationListener {

    private Validator mValidator;

    @NotEmpty(message = "Please enter Name")
    @BindView(R.id.errorEditText)
    TextInputEditText errorEditText;

    @BindView(R.id.txt_inp_acidentes_trabalho)
    TextInputLayout txt_inp_acidentes_trabalho;


    @BindView(R.id.txt_inp_dias_perdidos)
    TextInputLayout txt_inp_dias_perdidos;


    @BindView(R.id.txt_inp_total_trabalhadores)
    TextInputLayout txt_inp_total_trabalhadores;

    @BindView(R.id.txt_inp_horas_ano_trabalhador)
    TextInputLayout txt_inp_horas_ano_trabalhador;

    @BindView(R.id.txt_inp_faltas)
    TextInputLayout txt_inp_faltas;


    @BindView(R.id.txt_inp_total_horas_trabalhadas)
    TextInputLayout txt_inp_total_horas_trabalhadas;

    @BindView(R.id.txt_inp_frequencia)
    TextInputLayout txt_inp_frequencia;

    @BindView(R.id.txt_inp_incidencia)
    TextInputLayout txt_inp_incidencia;

    @BindView(R.id.txt_inp_gravidade)
    TextInputLayout txt_inp_gravidade;

    @BindView(R.id.txt_inp_avaliacao_gravidade)
    TextInputLayout txt_inp_avaliacao_gravidade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinistralidade);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Validation
        mValidator = new Validator(this);
        mValidator.setValidationListener(this);

        //txt_inp_acidentes_trabalho.getEditText().setText("lolo");
    }


    @OnClick(R.id.fab_gravar)
    public void fab_calendario_OnClickListener(View view) {
        mValidator.validate();
    }


    @Override
    public void onValidationSucceeded() {

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {

        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof TextInputEditText) {
                ((TextInputEditText) view).setError(message);
            } else {

            }
        }
    }

}
