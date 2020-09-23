package com.vvm.sh.ui.atividadesPendentes.relatorios;

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
import com.vvm.sh.baseDados.entidades.AnomaliaResultado;
import com.vvm.sh.baseDados.entidades.ProcessoProdutivoResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.databinding.DialogoAnomaliaBinding;
import com.vvm.sh.databinding.DialogoProcessoProdutivoBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerDialogoPersistenteFragment;
import com.vvm.sh.ui.anomalias.AnomaliasViewModel;
import com.vvm.sh.ui.atividadesPendentes.AtividadesPendentesViewModel;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.DatasUtil;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class DialogoProcessoProdutivo extends BaseDaggerDialogoPersistenteFragment
        implements Validator.ValidationListener{



    private DialogoProcessoProdutivoBinding binding;

    @Inject
    ViewModelProviderFactory providerFactory;

    private AtividadesPendentesViewModel viewModel;




    private Validator validador;


    private static final String ARGUMENTO_ID_ATIVIDADE = "id";


    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_processo_produtivo)
    TextInputEditText txt_inp_processo_produtivo;



    private static final String ARGUMENTO_ID= "id";


    public DialogoProcessoProdutivo() {
        // Empty constructor required for DialogFragment
    }


    public static DialogoProcessoProdutivo newInstance(int id) {
        DialogoProcessoProdutivo fragmento = new DialogoProcessoProdutivo();

        Bundle args = new Bundle();
        args.putInt(ARGUMENTO_ID, id);
        fragmento.setArguments(args);
        return fragmento;
    }


    @Override
    protected void iniciarDialogo() {

        viewModel = ViewModelProviders.of(this, providerFactory).get(AtividadesPendentesViewModel.class);
        binding = (DialogoProcessoProdutivoBinding) activityBaseBinding;
        binding.setViewmodel(viewModel);


        if(verificarArgumentos(ARGUMENTO_ID_ATIVIDADE) == true){
            validador = new Validator(this);
            validador.setValidationListener(this);

            viewModel.obterProcessoProdutivo(getArguments().getInt(ARGUMENTO_ID));
        }
        else{
            terminarDialogo();
        }

    }


    @Override
    protected int obterLayout() {
        return R.layout.dialogo_processo_produtivo;
    }

    @Override
    protected int obterTitulo() {
        return R.string.anomalia;
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

        int idAtividade = getArguments().getInt(ARGUMENTO_ID_ATIVIDADE);
        String processo = txt_inp_processo_produtivo.getText().toString();

        ProcessoProdutivoResultado registo = new ProcessoProdutivoResultado(idAtividade, processo);
        viewModel.gravarAtividade(registo);

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
