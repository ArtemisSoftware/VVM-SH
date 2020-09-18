package com.vvm.sh.ui.quadroPessoal;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.ColaboradorResultado;
import com.vvm.sh.baseDados.entidades.Morada;
import com.vvm.sh.databinding.DialogoColaboradorBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerDialogoPersistenteFragment;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import javax.inject.Inject;

public class DialogoColaborador extends BaseDaggerDialogoPersistenteFragment {


    private DialogoColaboradorBinding binding;

    @Inject
    ViewModelProviderFactory providerFactory;

    private QuadroPessoalViewModel viewModel;


    private static final String ARGUMENTO_ID= "id";


    public DialogoColaborador() {
        // Empty constructor required for DialogFragment
    }


    public static DialogoColaborador newInstance(int id) {
        DialogoColaborador fragmento = new DialogoColaborador();

        Bundle args = new Bundle();
        args.putInt(ARGUMENTO_ID, id);
        fragmento.setArguments(args);
        return fragmento;
    }




    @Override
    protected void iniciarDialogo() {

        viewModel = ViewModelProviders.of(this, providerFactory).get(QuadroPessoalViewModel.class);
        binding = (DialogoColaboradorBinding) activityBaseBinding;
        binding.setViewmodel(viewModel);


        if(verificarArgumentos(ARGUMENTO_ID) == true){

            viewModel.obterColaborador(PreferenciasUtil.obterIdTarefa(getContext()), getArguments().getInt(ARGUMENTO_ID), Identificadores.Origens.ORIGEM_WS);
        }
        else{
            terminarDialogo();
        }
    }

    @Override
    protected int obterLayout() {
        return R.layout.dialogo_colaborador;
    }

    @Override
    protected int obterTitulo() {
        return R.string.colaborador;
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

        int id = getArguments().getInt(ARGUMENTO_ID);
        String idMorada = ((Morada) binding.spnrMoradas.getItems().get(binding.spnrMoradas.getSelectedIndex())).id;
        String posto = binding.txtInpPosto.getText().toString();
        int origem = Identificadores.Origens.ORIGEM_WS;
        String idRegisto = viewModel.colaborador.getValue().idRegisto;

        ColaboradorResultado resultado = new ColaboradorResultado(PreferenciasUtil.obterIdTarefa(getContext()), id, idMorada, posto, origem);

        viewModel.gravar(viewModel.colaborador.getValue().idResultado, resultado);
    }
}
