package com.vvm.sh.ui.atividadesPendentes;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.vvm.sh.R;
import com.vvm.sh.databinding.DialogoAtividadePendenteNaoExecutadaBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerDialogoPersistenteFragment;
import com.vvm.sh.baseDados.entidades.AtividadePendenteResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.metodos.Preferencias;

import javax.inject.Inject;

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

        int idAtividade = getArguments().getInt(ARGUMENTO_ID_ATIVIDADE);
        int idAnomalia = ((Tipo) binding.spnrAnomalias.getItems().get(binding.spnrAnomalias.getSelectedIndex())).id;
        String observacao = binding.txtInpObservacao.getText().toString();

        AtividadePendenteResultado atividade = new AtividadePendenteResultado(idAtividade, idAnomalia, observacao);
        viewModel.gravarAtividade(Preferencias.obterIdTarefa(getContext()), atividade);
    }

}
