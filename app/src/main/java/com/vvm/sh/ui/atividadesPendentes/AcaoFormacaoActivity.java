package com.vvm.sh.ui.atividadesPendentes;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Select;
import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityAcaoFormacaoBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.AcaoFormacao;
import com.vvm.sh.ui.atividadesPendentes.relatorios.FormacaoViewModel;
import com.vvm.sh.ui.opcoes.modelos.Tipo;
import com.vvm.sh.util.MensagensUtil;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.base.BaseDatePickerDialog;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.DatasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class AcaoFormacaoActivity extends BaseDaggerActivity
        implements Validator.ValidationListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {



    private ActivityAcaoFormacaoBinding activityAcaoFormacaoBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private FormacaoViewModel viewModel;


    private Validator validador;

    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_local)
    TextInputEditText txt_inp_local;

    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_data)
    TextInputEditText txt_inp_data;

    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_inicio)
    TextInputEditText txt_inp_inicio;

    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_fim)
    TextInputEditText txt_inp_fim;



    @Override
    protected void intActivity(Bundle savedInstanceState) {

        validador = new Validator(this);
        validador.setValidationListener(this);

        viewModel = ViewModelProviders.of(this, providerFactory).get(FormacaoViewModel.class);

        activityAcaoFormacaoBinding = (ActivityAcaoFormacaoBinding) activityBinding;
        activityAcaoFormacaoBinding.setLifecycleOwner(this);
        //activityFormandoBinding.setListener(this);
        activityAcaoFormacaoBinding.setViewmodel(viewModel);

        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            viewModel.obterAcaoFormacao(bundle.getInt(getString(R.string.argumento_id_atividade)));
        }
        else{
            finish();
        }
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_acao_formacao;
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

                        //TODO: Completar metodo
                        MensagensUtil.sucesso();
                        finish();
                        break;

                    default:
                        break;
                }

            }
        });
    }


    //----------------------
    //Eventos
    //----------------------


    @OnClick(R.id.crl_btn_data)
    public void crl_btn_data_OnClickListener(View view) {

        BaseDatePickerDialog dialogo = new BaseDatePickerDialog(this);
        dialogo.obterDatePickerDialog().show(getSupportFragmentManager(), "Datepickerdialog");
    }

    @OnClick(R.id.crl_btn_inicio)
    public void crl_btn_inicio_OnClickListener(View view) {

        TimePickerDialog dialogo = new TimePickerDialog();
        dialogo.show(getSupportFragmentManager(), "Timepickerdialog");

//        TimePickerDialog picker = new TimePickerDialog(this, this);
//        picker.show();
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


        //TODO:acrescentar esta validacao
        //valido = MetodosValidacao.validarHorario(txt_inp_inicio, txt_inp_fim) & valido;

        Bundle bundle = getIntent().getExtras();
        int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

        Tipo designacao = (Tipo) activityAcaoFormacaoBinding.spnrDesignacao.getItems().get(activityAcaoFormacaoBinding.spnrDesignacao.getSelectedIndex());
        String local = activityAcaoFormacaoBinding.txtInpLocal.getText().toString();
        Date data = DatasUtil.converterString(activityAcaoFormacaoBinding.txtInpData.getText().toString(), DatasUtil.FORMATO_DD_MM_YYYY);
        Date inicio = DatasUtil.converterString(activityAcaoFormacaoBinding.txtInpData.getText().toString() + " " + activityAcaoFormacaoBinding.txtInpInicio.getText().toString(), DatasUtil.DATA_FORMATO_DD_MM_YYYY__HH_MM);
        Date fim = DatasUtil.converterString(activityAcaoFormacaoBinding.txtInpData.getText().toString() + " " + activityAcaoFormacaoBinding.txtInpFim.getText().toString(), DatasUtil.DATA_FORMATO_DD_MM_YYYY__HH_MM);

        AcaoFormacao registo = new AcaoFormacao(idAtividade, designacao.id, local, data, inicio, fim);

        viewModel.gravar(registo);
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


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        activityAcaoFormacaoBinding.txtInpData.setText(DatasUtil.converterData(year, monthOfYear, dayOfMonth, DatasUtil.FORMATO_DD_MM_YYYY));
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {

    }
}
