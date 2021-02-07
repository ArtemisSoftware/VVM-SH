package com.vvm.sh.ui.informacaoSst;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.EmailResultado;
import com.vvm.sh.baseDados.entidades.InformacaoSstResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.databinding.DialogoEmailBinding;
import com.vvm.sh.databinding.DialogoResponsavelBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerDialogoPersistenteFragment;
import com.vvm.sh.ui.tarefa.TarefaViewModel;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.constantes.TiposConstantes;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class DialogoResponsavel extends BaseDaggerDialogoPersistenteFragment implements Validator.ValidationListener {


    private DialogoResponsavelBinding binding;

    @Inject
    ViewModelProviderFactory providerFactory;

    private InformacaoSstViewModel viewModel;



    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_responsavel)
    TextInputEditText txt_inp_responsavel;


    private Validator validador;

    private static final String ARGUMENTO_RESPONSAVEL= "responsavel";

    public DialogoResponsavel() {
        // Empty constructor required for DialogFragment
    }


    public static DialogoResponsavel newInstance(String responsavel) {
        DialogoResponsavel fragmento = new DialogoResponsavel();
        Bundle args = new Bundle();
        args.putString(ARGUMENTO_RESPONSAVEL, responsavel);
        fragmento.setArguments(args);
        return fragmento;
    }



    @Override
    protected void iniciarDialogo() {

        validador = new Validator(this);
        validador.setValidationListener(this);

        viewModel = ViewModelProviders.of(this, providerFactory).get(InformacaoSstViewModel.class);

        binding = (DialogoResponsavelBinding) activityBaseBinding;
        binding.setViewmodel(viewModel);

        binding.txtInpResponsavel.setText(getArguments().getInt(ARGUMENTO_RESPONSAVEL));
    }


    @Override
    protected int obterLayout() {
        return R.layout.dialogo_responsavel;
    }

    @Override
    protected int obterTitulo() {
        return R.string.responsavel;
    }


    @Override
    protected void subscreverObservadores() {

        viewModel.observarMessagem().observe(this, new Observer<Recurso>() {
            @Override
            public void onChanged(Recurso recurso) {

                switch (recurso.status){

                    case SUCESSO:

                        dialogo.sucesso(recurso.messagem, listener);
                        break;

                    case ERRO:

                        dialogo.erro(recurso.messagem);
                        break;
                    default:
                        break;
                }

            }
        });

    }


    @Override
    protected void clickPositivo() {

        validador.validate();
    }


    //-----------------------
    //Metodos locais
    //-----------------------

    @Override
    public void onValidationSucceeded() {

        InformacaoSstResultado resultado = new InformacaoSstResultado(PreferenciasUtil.obterIdTarefa(getContext()), binding.txtInpResponsavel.getText().toString(), false);
        viewModel.gravar(resultado);
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {

        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getContext());

            // Display error messages ;)
            if (view instanceof TextInputEditText) {
                ((TextInputEditText) view).setError(message);
            }
        }
    }
}