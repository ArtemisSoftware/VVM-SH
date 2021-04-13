package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.jaredrummler.materialspinner.MaterialSpinner;
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
        binding.spnrModelo.setOnItemSelectedListener(spnr_modelo_ItemSelected);

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

                        if((int)recurso.dados == 1) {


                            listener = new OnDialogoListener() {
                                @Override
                                public void onExecutar() {
                                    Tipo modelo = (Tipo) binding.spnrModelo.getItems().get(binding.spnrModelo.getSelectedIndex());
                                    listenerAtividade.dialogoCategoriasProfissionais(modelo.id);
                                    terminarDialogo();
                                }
                            };
                        }
                        else{
                            listener = new OnDialogoListener() {
                                @Override
                                public void onExecutar() {
                                    terminarDialogo();
                                }
                            };
                        }
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

        AlertDialog d = (AlertDialog) getDialog();
        int acao = (int) d.getButton(DialogInterface.BUTTON_POSITIVE).getTag();

        if(acao == 1) {
            viewModel.inserirModelo(PreferenciasUtil.obterIdTarefa(getContext()), idAtividade, modelo.id);
        }
        else{
            viewModel.removerModelo(PreferenciasUtil.obterIdTarefa(getContext()), idAtividade, modelo.id);
        }
    }


    MaterialSpinner.OnItemSelectedListener spnr_modelo_ItemSelected = new MaterialSpinner.OnItemSelectedListener() {

        @Override
        public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

            AlertDialog d = (AlertDialog) getDialog();
            Tipo modelo = (Tipo) binding.spnrModelo.getItems().get(position);

            viewModel.obterDadosModelo(modelo.id);

            if(modelo.detalhe.equals("0") == true){

                d.getButton(DialogInterface.BUTTON_POSITIVE).setTag(0);
                d.getButton(DialogInterface.BUTTON_POSITIVE).setText("Remover");

            }
            else{
                d.getButton(DialogInterface.BUTTON_POSITIVE).setTag(1);
                d.getButton(DialogInterface.BUTTON_POSITIVE).setText("Gravar");
            }
        }
    };
}
