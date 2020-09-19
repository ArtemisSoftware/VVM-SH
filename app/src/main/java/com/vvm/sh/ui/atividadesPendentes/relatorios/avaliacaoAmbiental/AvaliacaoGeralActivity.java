package com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.RelatorioAmbientalResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.databinding.ActivityAvaliacaoGeralBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.base.BaseDatePickerDialog;
import com.vvm.sh.util.base.BaseTimePickerDialog;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.interfaces.OnDialogoListener;
import com.vvm.sh.util.metodos.ConversorUtil;
import com.vvm.sh.util.metodos.DatasUtil;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class AvaliacaoGeralActivity extends BaseDaggerActivity
        implements Validator.ValidationListener, DatePickerDialog.OnDateSetListener{


    private ActivityAvaliacaoGeralBinding activityAvaliacaoGeralBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private AvaliacaoAmbientalViewModel viewModel;

    private Validator validador;



    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_marca)
    TextInputEditText txt_inp_marca;

    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_numero_serie)
    TextInputEditText txt_inp_numero_serie;

    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_data)
    TextInputEditText txt_inp_data;

    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_inicio)
    TextInputEditText txt_inp_inicio;

    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_termino)
    TextInputEditText txt_inp_termino;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        validador = new Validator(this);
        validador.setValidationListener(this);

        viewModel = ViewModelProviders.of(this, providerFactory).get(AvaliacaoAmbientalViewModel.class);

        activityAvaliacaoGeralBinding = (ActivityAvaliacaoGeralBinding) activityBinding;
        activityAvaliacaoGeralBinding.setLifecycleOwner(this);
        activityAvaliacaoGeralBinding.setViewmodel(viewModel);
        activityAvaliacaoGeralBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));

        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));
            int tipo = bundle.getInt(getString(R.string.argumento_tipo_relatorio));

            activityAvaliacaoGeralBinding.setTipo(tipo);

            viewModel.obterGeral(idAtividade, tipo);
        }
        else{
            finish();
        }
    }



    @Override
    protected int obterLayout() {
        return R.layout.activity_avaliacao_geral;
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

                        avancarRelatorio(recurso);
                        break;

                    case ERRO:

                        dialogo.erro(recurso.messagem);
                        break;

                }
            }
        });
    }



    //--------------------
    //Metodos locais
    //--------------------



    /**
     * Metodo que permite ativar a validacao de campos especificos
     * @param ativar true para ativar ou false caso contrario
     */
    private void ativarValidacao(boolean ativar){
        activityAvaliacaoGeralBinding.txtInpData.setEnabled(ativar);
        activityAvaliacaoGeralBinding.txtInpInicio.setEnabled(ativar);
        activityAvaliacaoGeralBinding.txtInpTermino.setEnabled(ativar);
    }


    /**
     * Metodo que permite avancar no relatorio
     * @param recurso os dados que indicam para onde o relatorio deve ir
     */
    private void avancarRelatorio(Recurso recurso) {

        if(recurso.dados == null) {
            dialogo.sucesso(recurso.messagem, listenerActivity);
        }
        else{

            OnDialogoListener avancarListener = new OnDialogoListener() {
                @Override
                public void onExecutar() {

                    Intent intent = new Intent(AvaliacaoGeralActivity.this, AvaliacoesAmbientaisActivity.class);
                    Bundle bundle = getIntent().getExtras();
                    bundle.putInt(getString(R.string.argumento_id_relatorio), ConversorUtil.converter_long_Para_int((long)recurso.dados));
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            };

            dialogo.sucesso(recurso.messagem, avancarListener);

        }
    }



    //---------------------
    //EVENTOS
    //---------------------

    @OnClick(R.id.crl_btn_data)
    public void crl_btn_data_OnClickListener(View view) {

        BaseDatePickerDialog dialogo = new BaseDatePickerDialog(this);
        dialogo.obterDatePickerDialog().show(getSupportFragmentManager(), "Datepickerdialog");
    }


    @OnClick({R.id.crl_btn_inicio})
    public void crl_btn_inicio_OnClickListener(View view) {

        BaseTimePickerDialog dialogo = new BaseTimePickerDialog(activityAvaliacaoGeralBinding.txtInpInicio);
        dialogo.show(getSupportFragmentManager(), "Timepickerdialog");
    }

    @OnClick({R.id.crl_btn_termino})
    public void crl_btn_termino_OnClickListener(View view) {

        BaseTimePickerDialog dialogo = new BaseTimePickerDialog(activityAvaliacaoGeralBinding.txtInpTermino);
        dialogo.show(getSupportFragmentManager(), "Timepickerdialog");
    }


    @OnClick(R.id.fab_gravar)
    public void fab_gravar_OnClickListener(View view) {

        ativarValidacao(true);
        validador.validate();
    }

    @Override
    public void onValidationSucceeded() {

        ativarValidacao(false);


        if(DatasUtil.validarHorario(activityAvaliacaoGeralBinding.txtInpInicio, activityAvaliacaoGeralBinding.txtInpTermino) == true) {

            Bundle bundle = getIntent().getExtras();
            int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));
            int tipo = bundle.getInt(getString(R.string.argumento_tipo_relatorio));
            int idRelatorio = bundle.getInt(getString(R.string.argumento_id_relatorio),0);

            String marca = activityAvaliacaoGeralBinding.txtInpMarca.getText().toString();;
            String numeroSerie = activityAvaliacaoGeralBinding.txtInpNumeroSerie.getText().toString();;

            Tipo nebulosidade = (Tipo) activityAvaliacaoGeralBinding.spnrNebulosidade.getItems().get(activityAvaliacaoGeralBinding.spnrNebulosidade.getSelectedIndex());

            Date data = DatasUtil.converterString(activityAvaliacaoGeralBinding.txtInpData.getText().toString(), DatasUtil.FORMATO_DD_MM_YYYY);
            Date inicio = DatasUtil.converterString(activityAvaliacaoGeralBinding.txtInpData.getText().toString() + " " + activityAvaliacaoGeralBinding.txtInpInicio.getText().toString(), DatasUtil.DATA_FORMATO_DD_MM_YYYY__HH_MM);
            Date termino = DatasUtil.converterString(activityAvaliacaoGeralBinding.txtInpData.getText().toString() + " " + activityAvaliacaoGeralBinding.txtInpTermino.getText().toString(), DatasUtil.DATA_FORMATO_DD_MM_YYYY__HH_MM);

            RelatorioAmbientalResultado registo = new RelatorioAmbientalResultado(idAtividade, tipo, marca, numeroSerie, data, inicio, termino, nebulosidade);

            viewModel.gravar(idRelatorio, registo);
        }
        else{
            activityAvaliacaoGeralBinding.txtInpInicio.setError(Sintaxe.Alertas.HORARIO_INVALIDO);
            activityAvaliacaoGeralBinding.txtInpTermino.setError(Sintaxe.Alertas.HORARIO_INVALIDO);
        }
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

        ativarValidacao(false);
    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        activityAvaliacaoGeralBinding.txtInpData.setText(DatasUtil.converterData(year, monthOfYear, dayOfMonth, DatasUtil.FORMATO_DD_MM_YYYY));
    }

}

