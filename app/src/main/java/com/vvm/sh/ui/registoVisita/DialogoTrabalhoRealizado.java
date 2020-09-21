package com.vvm.sh.ui.registoVisita;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.TrabalhoRealizadoResultado;
import com.vvm.sh.databinding.DialogoTrabalhoRealizadoBinding;
import com.vvm.sh.ui.BaseDaggerDialogoPersistenteFragment;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.PreferenciasUtil;

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



    private static final String ARGUMENTO_ID= "id";


    public DialogoTrabalhoRealizado() {
        // Empty constructor required for DialogFragment
    }


    public static DialogoTrabalhoRealizado newInstance(int id) {
        DialogoTrabalhoRealizado fragmento = new DialogoTrabalhoRealizado();

        Bundle args = new Bundle();
        args.putInt(ARGUMENTO_ID, id);
        fragmento.setArguments(args);
        return fragmento;
    }

    @Override
    protected void iniciarDialogo() {

        validador = new Validator(this);
        validador.setValidationListener(this);

        //viewModel = ViewModelProviders.of(this, providerFactory).get(RegistoVisitaViewModel.class);
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
//        viewModel.observarMessagem().observe(this, new Observer<Recurso>() {
//            @Override
//            public void onChanged(Recurso recurso) {
//
//                switch (recurso.status){
//
//                    case SUCESSO:
//
//                        dialogo.sucesso(recurso.messagem, listener);
//                        break;
//
//                    case ERRO:
//
//                        dialogo.erro(recurso.messagem);
//                        break;
//
//                    default:
//                        break;
//                }
//
//            }
//        });
//
    }

    @Override
    protected void clickPositivo() {
        validador.validate();
    }

    @Override
    public void onValidationSucceeded() {

        int id = getArguments().getInt(ARGUMENTO_ID);

        TrabalhoRealizadoResultado resultado = new TrabalhoRealizadoResultado(PreferenciasUtil.obterIdTarefa(getContext()), id, binding.txtInpObservacao.getText().toString());
        //viewModel.gravar(resultado);
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
