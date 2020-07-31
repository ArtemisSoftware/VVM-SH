package com.vvm.sh.ui.atividadesPendentes;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Select;
import com.vvm.sh.R;
import com.vvm.sh.databinding.DialogoAtividadePendenteNaoExecutadaBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerDialogoPersistenteFragment;
import com.vvm.sh.ui.atividadesPendentes.adaptadores.OnAtividadePendenteListener;
import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendenteResultado;
import com.vvm.sh.ui.opcoes.modelos.Tipo;
import com.vvm.sh.util.BaseDialogoPersistenteFragment;
import com.vvm.sh.util.MensagensUtil;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.metodos.Preferencias;

import org.angmarch.views.NiceSpinner;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class DialogoAtividadePendenteNaoExecutada extends BaseDaggerDialogoPersistenteFragment{


    private DialogoAtividadePendenteNaoExecutadaBinding binding;

    @Inject
    ViewModelProviderFactory providerFactory;

    private AtividadesPendentesViewModel viewModel;




    private static final String ARGUMENTO_ID_ATIVIDADE = "id";


    public DialogoAtividadePendenteNaoExecutada() {
        // Empty constructor required for DialogFragment
    }


    public static DialogoAtividadePendenteNaoExecutada newInstance(int id) {
        DialogoAtividadePendenteNaoExecutada frag = new DialogoAtividadePendenteNaoExecutada();

        Bundle args = new Bundle();
        args.putInt(ARGUMENTO_ID_ATIVIDADE, id);
        frag.setArguments(args);
        return frag;
    }




    @Override
    protected void iniciarDialogo() {

        viewModel = ViewModelProviders.of(this, providerFactory).get(AtividadesPendentesViewModel.class);
        binding = (DialogoAtividadePendenteNaoExecutadaBinding) activityBaseBinding;
        binding.setViewmodel(viewModel);


        if(verificarArgumentos(ARGUMENTO_ID_ATIVIDADE) == true){

            viewModel.obterAtividade(getArguments().getInt(ARGUMENTO_ID_ATIVIDADE));
        }
        else{
            terminarDialogo();
        }
    }


    @Override
    protected int obterLayout() {
        return R.layout.dialogo_atividade_pendente_nao_executada;
    }

    @Override
    protected int obterTitulo() {
        return R.string.concluir_atividade;
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
                        terminarDialogo();
                        break;

                    default:
                        break;
                }

            }
        });
    }


    @Override
    protected void clickPositivo() {

        int idAtividade = getArguments().getInt(ARGUMENTO_ID_ATIVIDADE);
        int idAnomalia = ((Tipo) binding.spnrAnomalias.getItems().get(binding.spnrAnomalias.getSelectedIndex())).id;
        String observacao = binding.txtInpObservacao.getText().toString();

        AtividadePendenteResultado atividade = new AtividadePendenteResultado(idAtividade, idAnomalia, observacao);
        viewModel.gravarAtividade(Preferencias.obterIdTarefa(getContext()), atividade);
    }

}
