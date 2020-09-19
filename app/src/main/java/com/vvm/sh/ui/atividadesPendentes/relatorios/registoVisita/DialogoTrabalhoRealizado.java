package com.vvm.sh.ui.atividadesPendentes.relatorios.registoVisita;

import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.vvm.sh.R;
import com.vvm.sh.databinding.DialogoTrabalhoRealizadoBinding;
import com.vvm.sh.ui.BaseDaggerDialogoPersistenteFragment;
import com.vvm.sh.util.constantes.Sintaxe;

import java.util.List;

import butterknife.BindView;

public class DialogoTrabalhoRealizado extends BaseDaggerDialogoPersistenteFragment
        implements Validator.ValidationListener{


    private DialogoTrabalhoRealizadoBinding binding;


    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_observacao)
    TextInputEditText txt_inp_observacao;

    private Validator validador;

//    @Inject
//    ViewModelProviderFactory providerFactory;
//
//    private RegistoVisitaViewModel viewModel;


    public DialogoTrabalhoRealizado() {
        // Empty constructor required for DialogFragment
    }


    @Override
    protected void iniciarDialogo() {

        validador = new Validator(this);
        validador.setValidationListener(this);

        binding = (DialogoTrabalhoRealizadoBinding) activityBaseBinding;
    }

    @Override
    protected int obterLayout() {
        return R.layout.dialogo_trabalho_realizado;
    }

    @Override
    protected int obterTitulo() {
        return R.string.trabalho_realizado;
    }

    @Override
    protected void subscreverObservadores() {

    }

    @Override
    protected void clickPositivo() {
        validador.validate();
    }

    @Override
    public void onValidationSucceeded() {

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
