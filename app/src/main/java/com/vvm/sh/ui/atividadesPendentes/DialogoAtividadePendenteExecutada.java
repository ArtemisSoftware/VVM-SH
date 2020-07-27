package com.vvm.sh.ui.atividadesPendentes;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.vvm.sh.R;
import com.vvm.sh.databinding.DialogoAtividadePendenteExecutadaBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerDialogoPersistenteFragment;
import com.vvm.sh.ui.atividadesPendentes.adaptadores.OnAtividadePendenteListener;
import com.vvm.sh.util.BaseDialogoPersistenteFragment;
import com.vvm.sh.util.constantes.Sintaxe;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class DialogoAtividadePendenteExecutada extends BaseDaggerDialogoPersistenteFragment
        implements Validator.ValidationListener{



    private DialogoAtividadePendenteExecutadaBinding binding;

    @Inject
    ViewModelProviderFactory providerFactory;

    private AtividadesPendentesViewModel viewModel;


    private OnAtividadePendenteListener listener;


    private Validator validador;


    private static final String ARGUMENTO_ID_ATIVIDADE = "id";


    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_minutos)
    TextInputEditText txt_inp_minutos;

    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_data_execucao)
    TextInputEditText txt_inp_data_execucao;




    public DialogoAtividadePendenteExecutada() {
        // Empty constructor required for DialogFragment
    }


    public static DialogoAtividadePendenteExecutada newInstance(int id) {
        DialogoAtividadePendenteExecutada frag = new DialogoAtividadePendenteExecutada();

        Bundle args = new Bundle();
        args.putInt(ARGUMENTO_ID_ATIVIDADE, id);
        frag.setArguments(args);
        return frag;
    }




    @Override
    protected void iniciarDialogo() {

        listener = (OnAtividadePendenteListener) getContext();

        viewModel = ViewModelProviders.of(this, providerFactory).get(AtividadesPendentesViewModel.class);
        binding = (DialogoAtividadePendenteExecutadaBinding) activityBaseBinding;
        binding.setViewmodel(viewModel);


        if(verificarArgumentos(ARGUMENTO_ID_ATIVIDADE) == true){
            validador = new Validator(this);
            validador.setValidationListener(this);

            viewModel.obterAtividadesExecutada(getArguments().getInt(ARGUMENTO_ID_ATIVIDADE));
        }
        else{
            terminarDialogo();
        }

    }



    @Override
    protected int obterLayout() {
        return R.layout.dialogo_atividade_pendente_executada;
    }

    @Override
    protected int obterTitulo() {
        return R.string.concluir_atividade;
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

        int idAtividade = getArguments().getInt(ARGUMENTO_ID_ATIVIDADE);
        String minutos = txt_inp_minutos.getText().toString();
        String data = txt_inp_data_execucao.getText().toString();

        listener.OnGravarAtividadeExecutada(idAtividade, minutos, data);
        terminarDialogo();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getContext());

            // Display error messages

            if (view instanceof TextInputEditText) {
                ((TextInputEditText) view).setError(message);
            }

        }
    }



    //-------------------
    //Eventos
    //-------------------


    @OnClick(R.id.crl_btn_data_execucao)
    public void crl_btn_data_execucao_OnClickListener(View view) {

    }


}
