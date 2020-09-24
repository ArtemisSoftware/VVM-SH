package com.vvm.sh.ui.atividadesPendentes.relatorios.checklist;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.mobsandgeeks.saripaar.Validator;
import com.vvm.sh.R;
import com.vvm.sh.databinding.DialogoAreaChecklistBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerDialogoPersistenteFragment;
import com.vvm.sh.util.Recurso;

import javax.inject.Inject;

public class DialogoArea extends BaseDaggerDialogoPersistenteFragment {

    private DialogoAreaChecklistBinding binding;

    @Inject
    ViewModelProviderFactory providerFactory;

    private ChecklistViewModel viewModel;


    private static final String ARGUMENTO_ID_CHECKLIST = "idChecklist";
    private static final String ARGUMENTO_ID = "id";
    private static final String ARGUMENTO_DESCRICAO = "descricao";



    public DialogoArea() {
        // Empty constructor required for DialogFragment
    }


    public static DialogoArea newInstance(int idChecklist, int id, String descricao) {
        DialogoArea fragmento = new DialogoArea();

        Bundle args = new Bundle();
        args.putInt(ARGUMENTO_ID, id);
        args.putInt(ARGUMENTO_ID_CHECKLIST, idChecklist);
        args.putString(ARGUMENTO_DESCRICAO, descricao);
        fragmento.setArguments(args);
        return fragmento;
    }




    @Override
    protected void iniciarDialogo() {

        viewModel = ViewModelProviders.of(this, providerFactory).get(ChecklistViewModel.class);
        binding = (DialogoAreaChecklistBinding) activityBaseBinding;
        binding.setViewmodel(viewModel);


        if(verificarArgumentos(ARGUMENTO_ID) == true){

            binding.txtInpDescricaoArea.setText(getArguments().getString(ARGUMENTO_DESCRICAO));
            viewModel.obterAreasChecklist(getArguments().getInt(ARGUMENTO_ID_CHECKLIST));
        }
        else{
            terminarDialogo();
        }

    }



    @Override
    protected int obterLayout() {
        return R.layout.dialogo_area_checklist;
    }

    @Override
    protected int obterTitulo() {
        return R.string.area;
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

    }



    //---------------------
    //Metodos locais
    //---------------------

}
