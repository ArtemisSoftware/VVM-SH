package com.vvm.sh.ui.atividadesPendentes.relatorios.equipamentos;

import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.TipoNovo;
import com.vvm.sh.databinding.DialogoEquipamentoBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerDialogoPersistenteFragment;
import com.vvm.sh.ui.pesquisa.adaptadores.PesquisaViewModel;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.constantes.TiposConstantes;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class DialogoEquipamento extends BaseDaggerDialogoPersistenteFragment
        implements Validator.ValidationListener{


    private DialogoEquipamentoBinding binding;

    @Inject
    ViewModelProviderFactory providerFactory;

    private PesquisaViewModel viewModel;


    private Validator validador;



    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_equipamento)
    TextInputEditText txt_inp_equipamento;



    public DialogoEquipamento() {
        // Empty constructor required for DialogFragment
    }


    @Override
    protected void iniciarDialogo() {

        validador = new Validator(this);
        validador.setValidationListener(this);

        viewModel = ViewModelProviders.of(this, providerFactory).get(PesquisaViewModel.class);
        binding = (DialogoEquipamentoBinding) activityBaseBinding;
        binding.setViewmodel(viewModel);


    }

    @Override
    protected int obterLayout() {
        return R.layout.dialogo_equipamento;
    }

    @Override
    protected int obterTitulo() {
        return R.string.novo_equipamento;
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



    @Override
    public void onValidationSucceeded() {

        String equipamento = binding.txtInpEquipamento.getText().toString();

        TipoNovo resultado = new TipoNovo(TiposConstantes.TiposNovos.TIPOS_MAQUINA, equipamento);

        viewModel.gravar(resultado);

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
}
