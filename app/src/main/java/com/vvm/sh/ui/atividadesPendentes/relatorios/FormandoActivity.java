package com.vvm.sh.ui.atividadesPendentes.relatorios;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.FormandoResultado;
import com.vvm.sh.databinding.ActivityFormandoBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.AssinaturaActivity;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.base.BaseDatePickerDialog;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.DatasUtil;
import com.vvm.sh.util.metodos.ImagemUtil;
import com.vvm.sh.util.metodos.Preferencias;
import com.vvm.sh.util.viewmodel.BaseViewModel;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class FormandoActivity extends BaseDaggerActivity
        implements Validator.ValidationListener, DatePickerDialog.OnDateSetListener{


    private ActivityFormandoBinding activityFormandoBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private FormacaoViewModel viewModel;


    private Validator validador;


    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_nome)
    TextInputEditText txt_inp_nome;

    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_identificacao)
    TextInputEditText txt_inp_identificacao;

    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_data_nascimento)
    TextInputEditText txt_inp_data_nascimento;

    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_niss)
    TextInputEditText txt_inp_niss;

    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_naturalidade)
    TextInputEditText txt_inp_naturalidade;

    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_nacionalidade)
    TextInputEditText txt_inp_nacionalidade;


    private boolean assinado = false;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        validador = new Validator(this);
        validador.setValidationListener(this);

        viewModel = ViewModelProviders.of(this, providerFactory).get(FormacaoViewModel.class);

        activityFormandoBinding = (ActivityFormandoBinding) activityBinding;
        activityFormandoBinding.setLifecycleOwner(this);
        activityFormandoBinding.setViewmodel(viewModel);

        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            int idFormando = bundle.getInt(getString(R.string.argumento_id_formando));
            viewModel.obterFormando(idFormando);
        }
        else{
            finish();
        }
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_formando;
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

                        dialogo.sucesso(recurso.messagem, listenerActivity);
                        break;

                    case ERRO:

                        dialogo.erro(recurso.messagem);
                        break;

                }
            }
        });
    }


    //---------------------
    //Metodos locais
    //---------------------


    /**
     * Metodo que permite ativar a validacao de campos especificos
     * @param ativar true para ativar ou false caso contrario
     */
    private void ativarValidacao(boolean ativar){
        activityFormandoBinding.txtInpDataNascimento.setEnabled(ativar);
    }


    //---------------------
    //Eventos
    //---------------------


    @Override
    public void onValidationSucceeded() {

        ativarValidacao(false);

        Bundle bundle = getIntent().getExtras();
        int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));


        String nome = activityFormandoBinding.txtInpNome.getText().toString();

        Tipo genero = (Tipo) activityFormandoBinding.spnrGenero.getItems().get(activityFormandoBinding.spnrGenero.getSelectedIndex());

        String identificacao = activityFormandoBinding.txtInpIdentificacao.getText().toString();
        Date dataNascimento = DatasUtil.converterString(activityFormandoBinding.txtInpDataNascimento.getText().toString(), DatasUtil.FORMATO_DD_MM_YYYY);
        String niss = activityFormandoBinding.txtInpNiss.getText().toString();
        String naturalidade = activityFormandoBinding.txtInpNaturalidade.getText().toString();
        String nacionalidade = activityFormandoBinding.txtInpNacionalidade.getText().toString();


        FormandoResultado formando;

        int idFormando = bundle.getInt(getString(R.string.argumento_id_formando));
        byte[] imagem = null;

        if(idFormando == 0){
            formando = new FormandoResultado(idAtividade, nome, identificacao, genero.codigo, niss, dataNascimento, naturalidade, nacionalidade);
        }
        else{
            formando = new FormandoResultado(idAtividade, idFormando, nome, identificacao, genero.codigo, niss, dataNascimento, naturalidade, nacionalidade);
        }


        if(assinado == true){
            imagem = ImagemUtil.converter(((BitmapDrawable) activityFormandoBinding.imgAssinatura.getDrawable()).getBitmap());
        }

        viewModel.gravar(Preferencias.obterIdTarefa(this), formando, imagem);
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





    @OnClick(R.id.crl_btn_data)
    public void crl_btn_data_OnClickListener(View view) {

        BaseDatePickerDialog dialogo = new BaseDatePickerDialog(this);
        dialogo.obterDatePickerDialog().show(getSupportFragmentManager(), "Datepickerdialog");
    }


    @OnClick(R.id.fab_gravar)
    public void fab_gravar_OnClickListener(View view) {
        ativarValidacao(true);
        validador.validate();
    }


    @OnClick(R.id.img_assinatura)
    public void img_assinatura_OnClickListener(View view) {

        Intent intent = new Intent(this, AssinaturaActivity.class);
        startActivityForResult(intent, Identificadores.CodigoAtividade.ASSINATURA);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        activityFormandoBinding.txtInpDataNascimento.setText(DatasUtil.converterData(year, monthOfYear, dayOfMonth, DatasUtil.FORMATO_DD_MM_YYYY));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Identificadores.CodigoAtividade.ASSINATURA) {

            if(resultCode == RESULT_OK){

                Bitmap bitmap = ImagemUtil.converter(data.getByteArrayExtra(getString(R.string.resultado_imagem)));

                activityFormandoBinding.imgAssinatura.setImageBitmap(Bitmap.createScaledBitmap(bitmap, activityFormandoBinding.imgAssinatura.getWidth(),
                        activityFormandoBinding.imgAssinatura.getHeight(), false));

                assinado = true;
            }
        }
    }
}
