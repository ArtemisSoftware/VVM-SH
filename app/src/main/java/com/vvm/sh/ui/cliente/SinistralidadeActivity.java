package com.vvm.sh.ui.cliente;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.DecimalMin;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivitySinistralidadeBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.tarefa.TarefaViewModel;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.text.DecimalFormat;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;


public class SinistralidadeActivity extends BaseDaggerActivity
        implements Validator.ValidationListener {


    private ActivitySinistralidadeBinding activitySinistralidadeBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private TarefaViewModel viewModel;


    @DecimalMin(value = 4.0, message = "N.º de horas * Homem trabalhadas inválido")
    @NotEmpty(message = "Preenchimento obrigatório")
    @BindView(R.id.txt_inp_horas_ano_trabalhador)
    TextInputEditText txt_inp_horas_ano_trabalhador;


    private Validator validador;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(TarefaViewModel.class);

        activitySinistralidadeBinding = (ActivitySinistralidadeBinding) activityBinding;
        activitySinistralidadeBinding.setLifecycleOwner(this);
        activitySinistralidadeBinding.setViewmodel(viewModel);

        subscreverObservadores();

        viewModel.obterSinistralidade(PreferenciasUtil.obterIdTarefa(this));
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_sinistralidade;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

    }


    //----------------------
    //Metodos locais
    //----------------------


    /**
     * Metodo que permite calcular os indices
     */
    private void calculoIndices(){

        DecimalFormat formatoDecimal = new DecimalFormat("##.00");
        double totalHorasTrabalhadas = 0, horasAnoTrabalhador, faltasHora, acidentesComBaixa, diasUteisPerdidos;
        int totalTrabalhadores;

        activitySinistralidadeBinding.txtInpTotalHorasTrabalhadas.setText(getString(R.string.sem_dados));
        activitySinistralidadeBinding.txtInpFrequencia.setText(getString(R.string.sem_dados));
        activitySinistralidadeBinding.txtInpIncidencia.setText(getString(R.string.sem_dados));
        activitySinistralidadeBinding.txtInpGravidade.setText(getString(R.string.sem_dados));
        activitySinistralidadeBinding.txtInpAvaliacaoGravidade.setText(getString(R.string.sem_dados));

        try{
            acidentesComBaixa = Double.parseDouble(activitySinistralidadeBinding.txtInpAcidentesTrabalho.getText().toString());
            diasUteisPerdidos = Double.parseDouble(activitySinistralidadeBinding.txtInpDiasPerdidos.getText().toString());
            horasAnoTrabalhador = Double.parseDouble(txt_inp_horas_ano_trabalhador.getText().toString());
            totalTrabalhadores = Integer.parseInt(activitySinistralidadeBinding.txtInpTotalTrabalhadores.getText().toString());
            faltasHora = Double.parseDouble(activitySinistralidadeBinding.txtInpFaltas.getText().toString());

            totalHorasTrabalhadas = (horasAnoTrabalhador * totalTrabalhadores) - faltasHora;

            activitySinistralidadeBinding.txtInpTotalHorasTrabalhadas.setText(totalHorasTrabalhadas + "");

            activitySinistralidadeBinding.txtInpFrequencia.setText(formatoDecimal.format((acidentesComBaixa * 1000000) / totalHorasTrabalhadas));

            activitySinistralidadeBinding.txtInpIncidencia.setText(formatoDecimal.format((acidentesComBaixa * 1000) / totalTrabalhadores));

            activitySinistralidadeBinding.txtInpGravidade.setText(formatoDecimal.format((diasUteisPerdidos * 1000000) / totalHorasTrabalhadas));


            double indiceGravidade = Double.parseDouble(activitySinistralidadeBinding.txtInpGravidade.getText().toString().replaceAll(",","."));
            double indiceFrequencia = Double.parseDouble(activitySinistralidadeBinding.txtInpFrequencia.getText().toString().replaceAll(",","."));

            activitySinistralidadeBinding.txtInpAvaliacaoGravidade.setText(formatoDecimal.format((indiceGravidade * 1000) / indiceFrequencia));

        }
        catch(NumberFormatException | ArithmeticException e){
            acidentesComBaixa = 0;
        }









        //----------
//
//        try{
//            acidentesComBaixa = Double.parseDouble(txt_inp_acidentes_trabalho.getText().toString());
//        }
//        catch(NumberFormatException e){
//            acidentesComBaixa = 0;
//        }
//
//
//        try{
//            diasUteisPerdidos = Double.parseDouble(txt_inp_dias_perdidos.getText().toString());
//        }
//        catch(NumberFormatException e){
//            diasUteisPerdidos = 0;
//        }
//
//
//        try{
//            horasAnoTrabalhador = Double.parseDouble(txt_inp_horas_ano_trabalhador.getText().toString());
//        }
//        catch(NumberFormatException e){
//            horasAnoTrabalhador = 0;
//        }
//
//
//        try{
//            totalTrabalhadores = Integer.parseInt(txt_inp_total_trabalhadores.getText().toString());
//        }
//        catch(NumberFormatException e){
//            totalTrabalhadores = 0;
//        }
//
//
//        try{
//            faltasHora = Double.parseDouble(txt_inp_faltas.getText().toString());
//        }
//        catch(NumberFormatException e){
//            faltasHora = 0;
//        }
//
//
//        totalHorasTrabalhadas = (horasAnoTrabalhador * totalTrabalhadores) - faltasHora;
//
//        txt_inp_total_horas_trabalhadas.setText(totalHorasTrabalhadas + "");
//
//
//
//        //---------Indices
//
//
//        //iFrequencia
//
//        if(totalHorasTrabalhadas != 0){
//            txt_inp_frequencia.setText(formatoDecimal.format((acidentesComBaixa * 1000000) / totalHorasTrabalhadas));
//        }
//        else{
//            txt_inp_frequencia.setText(getString(R.string.sem_dados));
//        }
//
//
//        //incidencia
//
//        if(totalTrabalhadores != 0){
//            txt_inp_incidencia.setText(formatoDecimal.format((acidentesComBaixa * 1000) / totalTrabalhadores));
//        }
//        else{
//            txt_inp_incidencia.setText(getString(R.string.sem_dados));
//        }
//
//
//        //iGravidade
//
//        if(totalHorasTrabalhadas != 0){
//            txt_inp_gravidade.setText(formatoDecimal.format((diasUteisPerdidos * 1000000) / totalHorasTrabalhadas));
//        }
//        else{
//            txt_inp_gravidade.setText(getString(R.string.sem_dados));
//        }
//
//
//        //IndAVGravidade
//
//        try{
//
//            double indiceGravidade = Double.parseDouble(txt_inp_gravidade.getText().toString().replaceAll(",","."));
//            double indiceFrequencia = Double.parseDouble(txt_inp_frequencia.getText().toString().replaceAll(",","."));
//
//            txt_inp_avaliacao_gravidade.setText(formatoDecimal.format((indiceGravidade * 1000) / indiceFrequencia));
//
//        }
//        catch(NumberFormatException | ArithmeticException w){
//            txt_inp_avaliacao_gravidade.setText(getString(R.string.sem_dados));
//        }

    }



    //-----------------------
    //EVENTOS
    //-----------------------


    @OnClick(R.id.fab_gravar)
    public void fab_calendario_OnClickListener(View view) {
        validador.validate();
    }



    @OnTextChanged(value = {
            R.id.txt_inp_acidentes_trabalho, R.id.txt_inp_dias_perdidos, R.id.txt_inp_total_trabalhadores,
            R.id.txt_inp_horas_ano_trabalhador, R.id.txt_inp_faltas
    })
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
