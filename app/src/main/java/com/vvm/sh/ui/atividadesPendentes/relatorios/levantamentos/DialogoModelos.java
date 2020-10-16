package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.CategoriaProfissionalResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.databinding.DialogoCategoriaProfissionalBinding;
import com.vvm.sh.databinding.DialogoModeloLevantamentoBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerDialogoPersistenteFragment;
import com.vvm.sh.ui.atividadesPendentes.adaptadores.OnAtividadePendenteListener;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.adaptadores.OnLevantamentoListener;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.CategoriaProfissional;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.interfaces.OnDialogoListener;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import javax.inject.Inject;

public class DialogoModelos extends BaseDaggerDialogoPersistenteFragment {


    private DialogoModeloLevantamentoBinding binding;

    @Inject
    ViewModelProviderFactory providerFactory;

    private LevantamentosViewModel viewModel;



    private OnLevantamentoListener.OnLevantamentoRegistoListener listenerAtividade;


    private static final String ARGUMENTO_ID = "id";



    public DialogoModelos() {
        // Empty constructor required for DialogFragment
    }


    public static DialogoModelos newInstance(int idAtividade) {
        DialogoModelos fragmento = new DialogoModelos();

        Bundle args = new Bundle();
        args.putInt(ARGUMENTO_ID, idAtividade);
        fragmento.setArguments(args);
        return fragmento;
    }




    @Override
    protected void iniciarDialogo() {

        viewModel = ViewModelProviders.of(this, providerFactory).get(LevantamentosViewModel.class);
        binding = (DialogoModeloLevantamentoBinding) activityBaseBinding;
        binding.setViewmodel(viewModel);

        listenerAtividade = (OnLevantamentoListener.OnLevantamentoRegistoListener) getContext();

        if(verificarArgumentos(ARGUMENTO_ID) == true){

           viewModel.obterModelos(getArguments().getInt(ARGUMENTO_ID));
        }
        else{
            terminarDialogo();
        }

    }

    @Override
    protected int obterLayout() {
        return R.layout.dialogo_modelo_levantamento;
    }

    @Override
    protected int obterTitulo() {
        return R.string.modelo;
    }

    @Override
    protected void subscreverObservadores() {

        viewModel.observarMessagem().observe(this, new Observer<Recurso>() {
            @Override
            public void onChanged(Recurso recurso) {

                switch (recurso.status){

                    case SUCESSO:


                        listener = new OnDialogoListener() {
                            @Override
                            public void onExecutar() {
                                Tipo modelo = (Tipo) binding.spnrModelo.getItems().get(binding.spnrModelo.getSelectedIndex());
                                listenerAtividade.dialogoCategoriasProfissionais(modelo.id);
                                terminarDialogo();
                            }
                        };
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

        int idAtividade = getArguments().getInt(ARGUMENTO_ID);
        Tipo modelo = (Tipo) binding.spnrModelo.getItems().get(binding.spnrModelo.getSelectedIndex());

        viewModel.inserirModelo(PreferenciasUtil.obterIdTarefa(getContext()), idAtividade, modelo.id);

    }
}
