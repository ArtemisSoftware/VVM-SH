package com.vvm.sh.ui.cliente;

import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.DecimalMin;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.vvm.sh.R;
import com.vvm.sh.ui.BaseActivity;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;


public class SinistralidadeActivity extends BaseActivity implements Validator.ValidationListener {



    @BindView(R.id.txt_inp_acidentes_trabalho)
    TextInputEditText txt_inp_acidentes_trabalho;

    @BindView(R.id.txt_inp_dias_perdidos)
    TextInputEditText txt_inp_dias_perdidos;

    @BindView(R.id.txt_inp_total_trabalhadores)
    TextInputEditText txt_inp_total_trabalhadores;

    @DecimalMin(value = 4.0, message = "N.º de horas * Homem trabalhadas inválido")
    @NotEmpty(message = "Preenchimento obrigatório")
    @BindView(R.id.txt_inp_horas_ano_trabalhador)
    TextInputEditText txt_inp_horas_ano_trabalhador;

    @BindView(R.id.txt_inp_faltas)
    TextInputEditText txt_inp_faltas;



    @BindView(R.id.txt_inp_total_horas_trabalhadas)
    TextInputEditText txt_inp_total_horas_trabalhadas;

    @BindView(R.id.txt_inp_frequencia)
    TextInputEditText txt_inp_frequencia;

    @BindView(R.id.txt_inp_incidencia)
    TextInputEditText txt_inp_incidencia;

    @BindView(R.id.txt_inp_gravidade)
    TextInputEditText txt_inp_gravidade;

    @BindView(R.id.txt_inp_avaliacao_gravidade)
    TextInputEditText txt_inp_avaliacao_gravidade;


    private Validator validador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinistralidade);

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

        Sinistralidade t1 = new Sinistralidade("1","2","3", "4","5");
        txt_inp_acidentes_trabalho.setText(t1.obterAcidentesComBaixa());

        //TODO: chamar metodo do viewmodel
    }

    /**
     * Metodo que permite subscrever observadores
     */
    private void subscreverObservadores(){

        //TODO: subscrever observadores do viewmodel
    }


    /**
     * Metodo que permite calcular os indices
     */
    private void calculoIndices(){

        DecimalFormat formatoDecimal = new DecimalFormat("##.00");
        double totalHorasTrabalhadas = 0, horasAnoTrabalhador, faltasHora, acidentesComBaixa, diasUteisPerdidos;
        int totalTrabalhadores;

        try{
            acidentesComBaixa = Double.parseDouble(txt_inp_acidentes_trabalho.getText().toString());
        }
        catch(NumberFormatException e){
            acidentesComBaixa = 0;
        }


        try{
            diasUteisPerdidos = Double.parseDouble(txt_inp_dias_perdidos.getText().toString());
        }
        catch(NumberFormatException e){
            diasUteisPerdidos = 0;
        }


        try{
            horasAnoTrabalhador = Double.parseDouble(txt_inp_horas_ano_trabalhador.getText().toString());
        }
        catch(NumberFormatException e){
            horasAnoTrabalhador = 0;
        }


        try{
            totalTrabalhadores = Integer.parseInt(txt_inp_total_trabalhadores.getText().toString());
        }
        catch(NumberFormatException e){
            totalTrabalhadores = 0;
        }


        try{
            faltasHora = Double.parseDouble(txt_inp_faltas.getText().toString());
        }
        catch(NumberFormatException e){
            faltasHora = 0;
        }


        totalHorasTrabalhadas = (horasAnoTrabalhador * totalTrabalhadores) - faltasHora;

        txt_inp_total_horas_trabalhadas.setText(totalHorasTrabalhadas + "");



        //---------Indices


        //iFrequencia

        if(totalHorasTrabalhadas != 0){
            txt_inp_frequencia.setText(formatoDecimal.format((acidentesComBaixa * 1000000) / totalHorasTrabalhadas));
        }
        else{
            txt_inp_frequencia.setText(getString(R.string.sem_dados));
        }


        //incidencia

        if(totalTrabalhadores != 0){
            txt_inp_incidencia.setText(formatoDecimal.format((acidentesComBaixa * 1000) / totalTrabalhadores));
        }
        else{
            txt_inp_incidencia.setText(getString(R.string.sem_dados));
        }


        //iGravidade

        if(totalHorasTrabalhadas != 0){
            txt_inp_gravidade.setText(formatoDecimal.format((diasUteisPerdidos * 1000000) / totalHorasTrabalhadas));
        }
        else{
            txt_inp_gravidade.setText(getString(R.string.sem_dados));
        }


        //IndAVGravidade

        try{

            double indiceGravidade = Double.parseDouble(txt_inp_gravidade.getText().toString().replaceAll(",","."));
            double indiceFrequencia = Double.parseDouble(txt_inp_frequencia.getText().toString().replaceAll(",","."));

            txt_inp_avaliacao_gravidade.setText(formatoDecimal.format((indiceGravidade * 1000) / indiceFrequencia));

        }
        catch(NumberFormatException | ArithmeticException w){
            txt_inp_avaliacao_gravidade.setText(getString(R.string.sem_dados));
        }

    }


    //----------------------
    //Eventos
    //----------------------


    @OnClick(R.id.fab_gravar)
    public void fab_calendario_OnClickListener(View view) {
        validador.validate();
    }


    @OnTextChanged(value =  {R.id.txt_inp_acidentes_trabalho, R.id.txt_inp_dias_perdidos, R.id.txt_inp_total_trabalhadores, R.id.txt_inp_horas_ano_trabalhador, R.id.txt_inp_faltas})
    void TextWatcher_CalculoIndices(CharSequence s, int start, int before, int count) {
        calculoIndices();
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
            }
        }
    }

}
